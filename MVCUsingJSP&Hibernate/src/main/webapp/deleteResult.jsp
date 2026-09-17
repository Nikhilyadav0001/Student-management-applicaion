<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result page</title>
</head>
<body bgcolor = "lightblue">
	<br>
	<br>
	<c:choose>
		<c:when test="${status eq 'success'}">
		<h1 style='color: green; text-align: center;'>RECORD DELETED
		SUCCESFULLY</h1>
		</c:when>
	
		<c:when test="${status eq 'failure'}">
		<h1 style='color: red; text-align: center;'>RECORD DELETION FAILED</h1>
		</c:when>	
		
		<c:otherwise>
		<h1 style='color: cyan; text-align: center;'>RECORD NOT FOUND FOR
		DELETION</h1>
		</c:otherwise>
		
	</c:choose>
</body>
</html>