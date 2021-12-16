package com.kh.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;

//규격인 AbstractController을 상속 
public class StudentSelectListController extends AbstractController{
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "student/selectList"; //목록이 나와야하므로 jsp/html 연결 필요 (jsp 경로)
	}
}
