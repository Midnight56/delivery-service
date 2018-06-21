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
		<title>Successfull Registration</title>
	</head>
	<body id="body">
		<div class="navbar navbar-inverse navbar-fixed-top">
			<a class="navbar-brand" href="<c:url value="/home"/>">NoNAME LAbo</a>
		</div>
		<div id="reg">
			<div class="container">
				<div class="row centered">
						<h2>Поздравляем!! Вы успешно зарегистрировались!!</h2><br>
						<a class="ppc" href="${contextPath}/login">Авторизуйтесь для дальнейшей работы</a>	
				</div>
			</div>
		</div>
		
		<script src="${contextPath}/resources/js/jquery-3.3.1.js"></script>
    	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	</body>
</html>