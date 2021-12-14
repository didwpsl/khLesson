<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>core - forEach</title>
<style>
	table{
		border: 1px 
		border-collasp:
	}
	th{
		border: 1px solid 
		
	}
</style>
</head>
<body>
	<h1>core - forEach</h1>
	<h2>list</h2>
	<ul>
		<c:forEach items="${names}" var="name" varStatus="vs">
			<li>${vs.index}${vs.count}${vs.first}${name}</li>
		</c:forEach>
	</ul>
	
	<p>
		<c:forEach items="${names}" var="name" varStatus="vs">>
			<!-- ${name}<c:if test="${not vs.last}">,</c:if> -->
			${name}${not vs.last ? "," : ""}
		</c:forEach>
	</p>
	
	<div>
		<h3>회원 목록</h3>
		<table>
			<tr>
				<th>No</th>
				<th>이름</th>
				<th>성별</th>
				<th>나이</th>
				<th>결혼여뷰</th>
			</tr>
			<c:forEach items="${persons}" var="p" varStatus="vs">
				<tr>
					<td></td>
					<td>${vs.count}</td>
					<td>${p.gender}</td>
					<td>
						<select name="" id="">
							<option value="M" ${p.gender eq 'M'.charAt(0) ? 'selected' : ''}>남</option>
						</select>
					</td>
					<td>${p.age}</td>
					<td>${p.married}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<h2>set</h2>
	<ul>
		<c:forEach items="${numbers}" var="n">
			<li>${n}</li>
		</c:forEach>
	</ul>
	
	<h2>map</h2>
		<c:forEach items="${map}" var="entry">
			<!--<p>${entry}</p>-->
			<p>${entry.key} : ${entry.value}</p>
		</c:forEach>

</body>
</html>