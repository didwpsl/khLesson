package com.kh.common;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.student.model.dao.IStudentDao;
import com.kh.student.model.dao.StudentDao;
import com.kh.student.model.service.IStudentService;
import com.kh.student.model.service.StudentService;
/* 
 * servlet의 생명 주기 : tomcat이 관리 
 * 생성자 호출 - init - service(doGet/doPost) - destroy
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	//url-controller 연결을 담아 둘 Map 
	//커맨드 패턴 : 하나씩 짝 짓는 것 ex) ctrl + c : copy, ctrl + v : paste 
	private Map<String, AbstractController> urlCommandMap = new HashMap<>();
	/*
	 * 생성자에서 url-controller 연결 설정 
	 * - /student/studentEnroll.do ---> com.kh.student.controller.StudentEnrollController
	 */
	
	//톰캣에 의해서 객체가 만들어지고 그 때 딱 한번 url properties의 값을 읽어 Map에 세팅한다 
    public DispatcherServlet() throws Exception{
    	
    	// 0. 의존객체 준비
    	// 자식 객체를 부모 타입의 변수에 담아 제어 (다형성)
    	IStudentDao studentDao = new StudentDao();
    	IStudentService studentService = new StudentService(studentDao); // 의존 주입
    	
        //1. url-command.properties -> Properties 객체 
    	Properties prop = new Properties();
    	//Properties 파일의 경로를 문자열로 가져오기 
    	String filepath = DispatcherServlet.class.getResource("/url-command.properties").getPath();
    	prop.load(new FileReader(filepath)); //예외 전부 던지기 던진 예외는 톰캣이 처리 
    	
    	//2. Properties 값 -> urlCommandMap에 옮기기 
    	//properties 객체의 키 값 가져오기
    	Set<String> urlSet = prop.stringPropertyNames();
    	for(String url : urlSet) {
    		String className = prop.getProperty(url); //Controller 클래스의 full 경로가 나온다(문자열)
    		//com.kh.student.controller.StudentEnrollController
    		
    		//reflection API 
    		Class<?> clz = Class.forName(className); 
    		//IStudentService 타입을 받는 생성자 
    		Class<?>[] param ={IStudentService.class}; 
    		Constructor<?> constructor = clz.getDeclaredConstructor(param); 
    		//실제 생성자를 호출할 때 전달할 값들 (객체 리터럴로 선언)
    		Object[] args = {studentService};
    		//객체 만들기 부모 타입으로 제어 
    		AbstractController controller = (AbstractController) constructor.newInstance(args);
    		urlCommandMap.put(url, controller); //url= String(/student/studentEnroll.do) controller instance
    	}
    	System.out.println("[DispatcherServlet] urlCommandMap = " + urlCommandMap);
    }
    //요청이 들어왔을 때
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 요청 주소 가져오기 
		String uri = request.getRequestURI(); //context가 포함된 주소 리턴 
		// /mybatis/student/studentEnroll.do 
		String url = uri.replace(request.getContextPath(), "");
		System.out.println("[DispatcherServlet.doGet] url = " + url);
		//controller 객체 가져오기 
		AbstractController controller = urlCommandMap.get(url);
		//url이 잘못되면 해당 컨트롤러가 없을 것 (ControllerNotFoundException 클래스 생성)
		if(controller == null) throw new ControllerNotFoundException(url + "에 매핑된 컨트롤러가 없습니다");
		
		//2. controller의 doGet 또는 doPost 호출
		String method = request.getMethod();
		String viewName = null;
		switch(method) {
		case "GET" : viewName = controller.doGet(request, response); break;
		case "POST" : viewName = controller.doPost(request, response); break;
		default : throw new MethodNotAllowedException(method);
		}
		
		//3. jsp forwarding ( | redirect(redirect : ~) | pass(json처리시에는 아무것도 안함)
		// viewName : forwarding = student/studentEnroll, redirect = redirect : ~, pass = null
		if(viewName != null) {
			if(viewName.startsWith("redirect:")) {
				//redirect
				//location 값 완성 (전달되는 값 ex) redirect:/student/studentEnroll.do
				String location = request.getContextPath() + viewName.replace("redirect:", "");
				response.sendRedirect(location);
			}else {
				//forwarding
				//전달되는 값 ex) student/studentEnroll
				String prefix = "/WEB-INF/views/";
				String suffix = ".jsp";
				request.getRequestDispatcher(prefix + viewName + suffix).forward(request, response);
			}
		}else {
			//pass (json처리 할 경우)
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post로 들어와도 get으로 요청을 넘김 
		doGet(request, response);
	}

}
