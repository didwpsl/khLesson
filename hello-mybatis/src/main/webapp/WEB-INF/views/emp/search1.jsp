<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:setLocale value="ko_kr"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis실습</title>
<style>
div#emp-container{text-align:center;}
table.tbl-emp{
	margin:0 auto;
	border:1px solid; 
	border-collapse:collapse;
}
table.tbl-emp th, table.tbl-emp td{
	border:1px solid;
	padding:5px;
}
div#search-container{
	padding:15px 0;
}
</style>
</head>
<body>
<div id="emp-container">
	<h2>사원정보 </h2>
	<div id="search-container">
		<form name="empSearchFrm">
			<select name="searchType" required>
				<option value="">검색타입</option>
				<!-- required여부를 판단할 value="" 반드시 있어야함.-->
				<option value="emp_id" <c:if test="${'emp_id' eq param.searchType}">selected</c:if>>사번</option>
				<option value="emp_name" ${'emp_name' eq param.searchType? "selected" : ""}>사원명</option>
				<option value="email" ${'email' eq param.searchType? "selected" : ""}>이메일</option>
				<option value="phone" ${'phone' eq param.searchType? "selected" : ""}>전화번호</option>
			</select>
			<input type="search" name="searchKeyword" value="${param.searchKeyword}" required/>	
			<input type="submit" value="검색" />
		</form>
	</div>
	
	<table class="tbl-emp">
		<thead>
			<tr>
				<th></th><!-- 1부터 넘버링 처리 -->
				<th>사번</th>
				<th>사원명</th>
				<th>주민번호</th><!--뒷6자리는 ******처리-->
				<th>이메일</th>
				<th>전화번호</th>
				<th>부서코드</th>
				<th>직급코드</th>
				<th>급여레벨</th>
				<th>급여</th><!--원화기호, 세자리마다 콤마표시-->
				<th>보너스율</th><!--percent로 표시-->
				<th>매니져 사번</th>
				<th>입사일</th><!--날짜형식 yyyy/MM/dd-->
				<th>퇴사여부</th>
			</tr>
		</thead>
		<!-- 조회된 데이터가 있는 경우와 없는 경우를 분기처리 하세요 -->
		<tbody>
		
			<c:if test="${not empty list}">
				<c:forEach items="${list}" var="emp" varStatus="vs">
					<tr>
						<td>${vs.count}</td>
						<td>${emp.empId}</td>
						<td>${emp.empName}</td>
						<td>${fn:substring(emp.empNo, 0, 8)}******</td>
						<td>${emp.email}</td>
						<td>${emp.phone}</td>
						<td>${emp.deptCode}</td>
						<td>${emp.jobCode}</td>
						<td>${emp.salLevel}</td>
						<td><fmt:formatNumber value="${emp.salary}" type="currency"/></td>
						<td><fmt:formatNumber value="${emp.bonus}" type="percent"/></td>
						<td>${emp.managerId}</td>
						<td><fmt:formatDate value="${emp.hireDate}" pattern="yyyy/MM/dd"/></td>
						<td>${emp.quitYn}</td>
					</tr>							
				</c:forEach>
			</c:if>
			<c:if test="${empty list}">
				<tr>
					<td colspan="14">검색결과가 존재하지 않습니다.</td>
				</tr>			
			</c:if>
		</tbody>
	</table>
</div>

</body>
</html>
