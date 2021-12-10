<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	EL Expression Language
	${key}
	
	1. 스코프별 내장객체의 속을 map에 보관
		-생략시에는 다음 순으로 속성을 조회해서 가져온다 (생략 가능)
		-생략시에는 다음 순으로 속성 조회 
		pageScope
		requestScope
		ssessionScope
		applicationScope
		
	2. 사용자 입력값
		param
		paramValues
		
	3. 요청 header
		header
		headerValues
		cookei
		
	4. 초기파라미터 : application 초기값을 key-value로 저장
		initParam
		
	5. 유일한 포인터객체 (실제 객체를 가리킨다(유일) 나머지는 Map)
		pageContext

 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL Basics</title>
</head>
<body>
	<h1>El Basics</h1>
	<h2>request</h2>
	<ul>
		<li>${coffee}</li>
		<li>${num}</li>
		<li>${person}
		<ul>
		<li>${person.name}</li>
		<li>${person.gender}</li>
		<li>${person.married}</li>
	</ul>
	</li>
	<li>${items}
		<ol>
			<li>${items[0]}</li>
			<li>${items[1]}</li>
			<li>${items[2]}</li>
			<li>${items[3]}</li>
			<li>${items[4]}</li>
		</ol>
	</li>
	<li>%{map}</li>
		<ul>
			<li>${map.name}</li><!-- Dot Notation -->
			<li>${map.today}</li>
			<li>${map.Dr.Zang}</li><!--EL은 NullpointerException을 발생시키지 않는다 출력값이 null인 경우 ""문자열 출력 -->
			<li>${map['Dr.Zang']}
				<!-- Bracket Notation사용 가능. '', "" 모두 문자열 처리  -->
				<ul>
					<li>${map['Dr.Zang']['name']}</li>
					<li>${map['Dr.Zang'].gender}</li>
					<li>${map['Dr.Zang'].age}</li>
					<li>${map['Dr.Zang'].married}</li>
				</ul>
			</li>
		</ul>
	<h2>session</h2>
	<ul>
		<li>${book}</li>
	</ul>
	
	<h2>application</h2>
	<ul>
		<li>${movie}</li>
	</ul>
	
	<h2>사용자 입력값</h2>
	<ul>
		<li>pid : ${param.pid}</li>
		<li>option : ${paramValues.option[0]}</li>
		<li>option : ${paramValues.option[1]}</li>
	</ul>
	
	<h2>header</h2>
	<ul>
		<li>Host : ${header.Host}</li>
		<li>Referer : ${header.Referer}</li>
		<li>User-agent : ${header['User-agent']}</li>
		<li>cookie.JSESSIONID : ${cookie.JSESSIONID.value}</li>
	</ul>
	
	<h2>initParam</h2>
	<ul>
		<li>tel : ${initParam.tel}</li>
		<li>email : ${initParam.email}</li>
	</ul>
	
<%
	String method = request.getMethod();
	System.out.println(method); // GET POST
	
	String contextPath = request.getContextPath();
	System.out.println(contextPath); // /action
	
	HttpServletRequest _request = (HttpServletRequest)pageContext.getRequest();
	String _method = _request.getMethod();
	System.out.println(_method); //GET
%>
	
	<h2>pageContext</h2>
	<ul>
		<li>${pageContext.request.method}</li>
		<li>${pageContext.request.contextPath}</li>
	</ul>
	
</body>
</html>
