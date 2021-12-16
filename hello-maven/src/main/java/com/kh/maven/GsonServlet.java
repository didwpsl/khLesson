package com.kh.maven;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


public class GsonServlet extends HttpServlet implements Servlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<>();
		map.put("msg", "hello world!");
		map.put("today", new Date());
		
		//java 객체를 json 문자열로 변환해 응답 메세지에 출력
		response.setContentType("application/json; charset=utf-8");
		//gson 의존 라이브러리를 pom.xml에 dependency 태그로 등록 후 사용
		new Gson().toJson(map, response.getWriter());
	}
}
