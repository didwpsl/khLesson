package com.kh.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;

public class StudentEnrollController extends AbstractController{

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
		return "redirect:/";
	}
	
}
