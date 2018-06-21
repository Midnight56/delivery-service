<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/font-awesome.min.css"/>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/main.css"/>
		<title>Create order</title>
	</head>
	<body>
		<div class="navbar navbar-inverse navbar-fixed-top">
			<a class="navbar-brand" href="${contextPath}/home">NoNAME LAbo</a>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li>
						<form class="navbar-form" role="search">
				        	<div class="form-group">
				          		<input type="text" class="form-control" placeholder="Поиск">
				        	</div>
				        	<!-- <button type="submit" class="btn btn-default">Ok!</button> -->
				      	</form>
					</li>
					<li>
						<div class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								<sec:authentication property="principal.user.name" /><b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="${contextPath}/users/profile">профиль</a></li>
								<li><a class="dropdown-item" href="${contextPath}/logout">выйти</a></li>
							</ul>
						</div>
					</li>
					<li>
						<div class="dropdown">
	                		<a href="#" class="dropdown-toggle" data-toggle="dropdown">Locale <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#dropdown1" data-toggle="tab">@fat</a></li>
								<li><a href="#dropdown2" data-toggle="tab">@mdo</a></li>
							</ul>
						</div>
					</li>	
				</ul>				
			</div>
		</div>
		<br>
		<div class="col-lg-4" id="new-ordertable">
			<spring:form role="form" action="${contextPath}/orders/create-order" modelAttribute="newOrder" method="post">
				<div class="header">
					<h2 align="center">Оформление заказа</h2>
				</div>
				<br>
				<div class="form-group">
					<label>укажите адрес доставки:</label>
					<spring:input path="deliveryAddress" class="form-control" />
				</div>
				<div class="form-check">
					<label>тип упаковки:</label>
					<spring:radiobuttons id="newOrder_form_pack" path="packingType" items="${packingList}"/>							
				</div>
				<div class="form-check">
					<label>хрупкий товар?:</label>
					<spring:radiobutton id="fragile" path="fragile" value="true" label="да" />
					<spring:radiobutton id="fragile" path="fragile" value="false" label="нет" />						
				</div>
				<br>
				<div class="form-group">
					<button type="submit" class="btn btn-success">оформить</button>
				</div>
			</spring:form>
		</div>
		<script src="${contextPath}/resources/js/jquery-3.3.1.js"></script>
    	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	</body>
</html>
