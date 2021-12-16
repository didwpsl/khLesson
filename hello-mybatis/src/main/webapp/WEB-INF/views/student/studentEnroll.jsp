<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis 실습</title>
<style>
div.enroll-container{text-align:center;}
table#tbl-student{margin:0 auto;border:1px solid; border-collapse:collapse;}
table#tbl-student th,table#tbl-student td{
	border:1px solid;
	padding:5px;
}
table#tbl-student th{text-align:right;}
table#tbl-student td{text-align:left;}
table#tbl-student tr:last-of-type td{text-align:center;}
</style>
</head>
<body>
	<div class="enroll-container">
		<h2>회원등록(VO)</h2>
		<form method="post">
			<table id="tbl-student">
				<tr>
					<th>학생이름</th>
					<td>
						<input type="text" name="name" required/>
					</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>
						<input type="tel" name="tel" maxlength="11" required/>
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<input type="email" name="email"/> 
					</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>
						<input type="text" name="addr"/>
					</td>
				</tr>		
				<tr>
					<td colspan="2">
						<input type="submit" value="등록" />
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>
