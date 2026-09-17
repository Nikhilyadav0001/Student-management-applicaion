<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Output page</title>
</head>
<body bgcolor="lightblue">
	<br>
	<br>
	<c:choose>
		<c:when test="${student ne null || !empty student}">
		<!-- present table data -->
			<table border='1' align ="center">
				<tr>
					<th>ID</th>
					<th>NAME</th>
					<th>AGE</th>
					<th>ADDRESS</th>
				</tr>
				<tr>
					<td>${student.sid}</td>
					<td>${student.sname}</td>
					<td>${student.sage}</td>
					<td>${student.saddress}</td>
				</tr>
			</table>
		</c:when>
		<c:otherwise>
		<!-- print data not found -->
		<h1 style="color: red;text-align: center;">
			record not found to display
		</h1>
		</c:otherwise>
	</c:choose>
</body>
</html>