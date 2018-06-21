<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/main.css"/>
		<title>Login Page</title>
	</head>
	<body id="body">
		<div class="navbar navbar-inverse navbar-fixed-top">
				<a class="navbar-brand" href="${contextPath}/home">NoNAME LAbo</a>
		</div>
		<div class="container">
			<spring:form action="${contextPath}/login" method="post">
				<table class="login_table">
					<tr>
						<td colspan="2" align="center">
							<h3><b>Авторизация пользователя</b></h3>
						</td>
					</tr>
					
					<tr>
						<td class="l1">
							<label><b>Логин:</b></label><br>
						</td>
						<td>
							<input type="text" name="username" size="30" required/>
						</td>
					</tr>
					<tr>
						<td class="l1">
							<label><b>Пароль:</b></label><br>						
						</td>
						<td>
							<input type="password" name="password" size="30" required/>
						</td>
					</tr>
					<tr align="center">
						<td colspan="2">
							<input type="submit" name="login" value="войти" class="button1"/>
						</td>
					</tr>
				</table>
			</spring:form>
		</div>
		
		<script src="${contextPath}/resources/js/jquery-3.3.1.js"></script>
    	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	</body>
</html>