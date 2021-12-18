package com.kh.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.common.AbstractController;
import com.kh.student.model.service.IStudentService;
import com.kh.student.model.vo.Student;

public class StudentEnrollController extends AbstractController{
	
	//의존 객체 
	private IStudentService studentService;
	
	//생성자에서 의존 객체를 외부로부터 받아 주입 
	public StudentEnrollController(IStudentService studentService) {
		this.studentService = studentService;
		System.out.println("[StudentEnrollController] studentService = " + studentService);
	}
	/*
	 * 학생 등록 폼 요청 (GET)
	 * HTML 필요 > forwarding
	 */
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "student/studentEnroll"; //forwarding 
	}
	
	/*
	 * 학생 DB 등록 요청 (POST)
	 */
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.사용자 입력 값 처리 
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		Student student = new Student(0, name, tel, null);
		System.out.println("[StudentEnrollController] student = " + student);
		
		//2.업무 로직 요청 
		int result = studentService.insertStudent(student);
		String msg = result > 0 ? "학생 등록 성공" : "학생 등록 실해";
		
		//3.사용자 피드백 (redirect)
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		
		return "redirect:/student/studentEnroll.do";
	}
	
}
