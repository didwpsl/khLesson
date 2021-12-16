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
        //1. url-command.properties -> Properties 객체 
    	Properties prop = new Properties();
    	//Properties 파일의 경로를 문자열로 가져오기 
    	//소스 폴더는 실제 build path로 자동 배포하며 target-claseses
    	//톰캣은 빌드패스를 대상으로만 실행된다 소스 폴더는 읽을 수 없다 
    	String filepath = DispatcherServlet.class.getResource("/url-command.properties").getPath();
    	prop.load(new FileReader(filepath)); //예외 전부 던지기 던진 예외는 톰캣이 처리 
    	
    	//2. Properties 값 -> urlCommandMap에 옮기기 
    	//properties 객체의 키 값 가져오기
    	Set<String> urlSet = prop.stringPropertyNames();
    	//하나씩 순회하며 값 읽기 (전부)
    	for(String url : urlSet) {
    		String className = prop.getProperty(url); //Controller 클래스의 full 경로가 나온다(문자열)
    		//com.kh.student.controller.StudentEnrollController
    		
    		//reflection API 
    		Class<?> clz = Class.forName(className); 
    		//기본 생성자 가져오기 값으로 null 전달 시 기본 생성자가 됨 
    		Class<?>[] param = null; //생성자 인자로 전달될 타입 
    		Constructor<?> constructor = clz.getDeclaredConstructor(param); 
    		//생성자의 타입이 nulil > 기본 생성자 
    		Object[] args = null;
    		//객체 만들기 부모 타입으로 제어 
    		AbstractController controller = (AbstractController) constructor.newInstance(args);
    		//newInstance의 타입을 설정할 수 없어 Object로 열어 둔 것 현 시점에서 검증 불가 
    		//AbstractController에 다른 잘못된 값을 넣으면 실행 시 바로 오류가 발생할 것 
    		urlCommandMap.put(url, controller); //url= String(/student/studentEnroll.do) controller instance
    	}
    	System.out.println("[DispatcherServlet] urlCommandMap = " + urlCommandMap);
    }
    //요청이 들어왔을 때
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 요청 주소 가져오기 
		String uri = request.getRequestURI(); //context가 포함된 주소 리턴 
		// /mybatis/student/studentEnroll.do 
		//context 날리기 
		String url = uri.replace(request.getContextPath(), "");
		System.out.println("[DispatcherServlet.doGet] url = " + url);
		//controller 객체 가져오기 
		AbstractController controller = urlCommandMap.get(url);
		//url이 잘못되면 해당 컨트롤러가 없을 것 (ControllerNotFoundException 클래스 생성)
		if(controller == null) throw new ControllerNotFoundException(url + "에 매핑된 컨트롤러가 없습니다");
		
		//2. controller의 doGet 또는 doPost 호출
		//request의 메소드가 get인지 post인지 확인 (이 외에도 많지만 현재는 두가지만 확인)
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
