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
		<title>Registration Page</title>
	</head>
	<body id="body">
		<div class="navbar navbar-inverse navbar-fixed-top">
				<a class="navbar-brand" href="${contextPath}/home">NoNAME LAbo</a>
		</div>
		<br><br><br>
		<div class="container">
			<div id="registration_form"class="col-lg-4">
				<spring:form action="${contextPath}/registration" modelAttribute="user" method="POST">
						<div>
							<h2 align="center">Регистрация</h2>
						</div>
						<div class="form-group">
							<spring:label path="name">Логин</spring:label>
							<spring:input path="name" class="form-control" />
							<spring:errors path="name" cssClass="error" />
						</div>
						<div class="form-group">
							<spring:label path="email">E-mail</spring:label>
							<spring:input path="email" class="form-control" />
							<spring:errors path="email" cssClass="error" />
						</div>
						<div class="form-group">
							<spring:label path="password">Пароль</spring:label>
							<spring:password path="password" class="form-control" />
							<spring:errors path="password" cssClass="error" />
						</div>
						<div class="form-check">
							<spring:radiobutton id="reg_customer_radio" path="role" value="customer" label="заказчик" checked="checked"/>
							<spring:radiobutton id="reg_courier_radio" path="role" value="courier" label="курьер" />						
						</div>
						<br>
						<div class="form-group" align="center">
							<spring:button type="submit" class="btn btn-success">Зарегистрироваться</spring:button>
						</div>
				</spring:form>
			</div>
		</div>
		
		<script src="${contextPath}/resources/js/jquery-3.3.1.js"></script>
    	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	</body>
</html>