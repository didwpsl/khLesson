<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	request의 속성에 저장된 person객체(Person타입)을 가져오기 
	-id는 속성의 key 값 
	-해당 스코프에 sinsa key 값의 Person 객체가 없다면 새로 생성 
	-필드의 타입이 String/기본혀이 아니라면 표준 액션에서 사용시 제약이 있으므로 scriptlet을 사용할 것 
 --%>
<jsp:useBean id="person" class="com.kh.action.person.model.vo.Person" scope="request"></jsp:useBean>
<!-- Person sinsa = new Person();과 같다 -->
<jsp:useBean id="sinsa" class="com.kh.action.person.model.vo.Person" scope="request"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp - useBean</title>
</head>
<body>
	<h1>useBean</h1>
	<h2>person</h2>
	<p>성명: <jsp:getProperty property="name" name="person"/></p>
	<p>성별: <jsp:getProperty property="gender" name="person"/></p>
	<p>나이: <jsp:getProperty property="age" name="person"/></p>
	<p>결혼 여부: <jsp:getProperty property="married" name="person"/></p>
	
	<h2>sinsa</h2>
	<!-- sinsa 객체의 property 설정 -->
	<jsp:setProperty property="name" name="sinsa" value="신사임당" />
	<jsp:setProperty property="gender" name="sinsa" value="F" />
	<jsp:setProperty property="age" name="sinsa" value="48" />
	<jsp:setProperty property="married" name="sinsa" value="true" />
	
	
	<p>성명: <jsp:getProperty property="name" name="sinsa"/></p>
	<p>성별: <jsp:getProperty property="gender" name="sinsa"/></p>
	<p>나이: <jsp:getProperty property="age" name="sinsa"/></p>
	<p>결혼 여부: <jsp:getProperty property="married" name="sinsa"/></p>
</body>
</html>