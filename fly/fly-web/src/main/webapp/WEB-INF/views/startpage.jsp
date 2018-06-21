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
		<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/main.css"/>
		<title>Start Page</title>
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
					<li><a href="${contextPath}/registration">Регистрация</a></li>
					<li><a href="${contextPath}/login">Авторизация</a></li>
				</ul>				
			</div>
		</div>
		<div id="headerwrap">
			<div class="container">
				<div class="row centered">
					<div class="col-lg-12">
						<h1>Fly-Express delivery</h1>
						<h2><b>Доставим что угодно куда угодно!</b></h2>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row c">
				<h3>Список доступных заказов</h3><br>
		</div>
		
		<c:if test="${!empty orderList}">
			<div class="container">
				<div class="row centered">
					<div class="table-responsive">
						<table class="table table-hover table-bordered" style="margin: auto;">
							<thead>
								<tr>
									<th width="20">№ заказа</th>
									<th width="150">заказчик</th>
									<th width="150">дата заказа</th>
									<th>адрес доставки</th>
									<th width="50">тип посылки</th>
									<th width="50">хрупкий</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="order" items="${orderList}">
									<tr>
										<td width="20">${order.id}</td>
										<td width="150">${order.userName}</td>
										<td width="150"><fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${order.date}"/></td>
										<td>${order.deliveryAddress}</td>
										<td width="50">${order.packingType}</td>
										<td width="50">
											<c:if test="${order.fragile == true}"><span class="glyphicon glyphicon-plus"></span></c:if>
											<c:if test="${order.fragile == false}"><span class="glyphicon glyphicon-minus"></span></c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</c:if>
		<br><br><br><br>
		<script src="${contextPath}/resources/js/jquery-3.3.1.js"></script>
    	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	</body>
</html>