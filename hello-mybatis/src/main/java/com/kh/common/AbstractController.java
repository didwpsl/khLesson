package com.kh.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//객체를 만들 수 없도록 추상 클래스로 만든다 (new AbstractController X) 
//여러 컨트롤러를 제어하기 위한 규격 (규격은 인터페이스도 가능하지만, 클래스도 가능하다)
public abstract class AbstractController {

	public String doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
			throw new MethodNotAllowedException("GET");
	}
	public String doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
			throw new MethodNotAllowedException("POST");
	}
}
