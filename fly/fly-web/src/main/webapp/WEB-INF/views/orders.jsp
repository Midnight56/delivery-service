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
		<title>User orders</title>
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
								<li><a href="#dropdown1" data-toggle="tab">@rus</a></li>
								<li><a href="#dropdown2" data-toggle="tab">@eng</a></li>
							</ul>
						</div>
					</li>		
				</ul>				
			</div>
		</div>
		<div class="return">
			<a href="${contextPath}/welcome">--> Вернуться на главную</a>
		</div>
		<div class="container">
			<div class="row centered">
				<div id="tablefix" class="table-responsive">
					<table class="table table-hover table-bordered" style="margin: auto;">
						<thead>
							<tr>
								<th width="20">№ заказа</th>
								<th width="150">дата заказа</th>
								<th>адрес доставки</th>
								<th width="50">тип посылки</th>
								<th width="50">хрупкий</th>
								<th width="50">статус</th>
								<sec:authorize access="hasAuthority('customer')">
									<th width="100">исполнитель</th>
								</sec:authorize>	
								<th width="210">действия</th>
							</tr>
						</thead>
						<c:if test="${!empty orderList}">
							<tbody>
								<c:forEach var="order" items="${orderList}">
									<tr>
										<td width="20">${order.id}</td>
										<td width="150"><fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${order.date}"/></td>
										<td>${order.deliveryAddress}</td>
										<td width="50">${order.packingType}</td>
										<td width="50">
											<c:if test="${order.fragile == true}"><span class="glyphicon glyphicon-plus"></span></c:if>
											<c:if test="${order.fragile == false}"><span class="glyphicon glyphicon-minus"></span></c:if>
										</td>
										<td width="50">
											<c:if test="${order.status == true}">активный</c:if>
											<c:if test="${order.status == false}">закрыт</c:if>
										</td>
										<sec:authorize access="hasAuthority('customer')">
											<td width="100">${order.employeeId}</td>
										</sec:authorize>
										<td width="210">
											<sec:authorize access="hasAuthority('customer')">
												<c:if test="${order.status != false}">
													<a href="${contextPath}/orders/${order.id}/close-order" class="btn btn-success btn-md">отменить</a>
												</c:if>
											</sec:authorize>
											<sec:authorize access="hasAuthority('courier')">
												<c:if test="${order.status != false}">
													<a href="${contextPath}/orders/${order.id}/close-order" class="btn btn-success btn-md">выполнено</a>
												</c:if>	
												<c:if test="${order.status != false}">
													<a href="${contextPath}/orders/${order.id}/cancel-order" class="btn btn-danger btn-md">отказаться</a>
												</c:if>	
											</sec:authorize>
										</td>	
									</tr>
								</c:forEach>
							</tbody>
						</c:if>						
					</table>
				</div>
			</div>	
		</div>
		<br><br><br><br>
		<script src="${contextPath}/resources/js/jquery-3.3.1.js"></script>
    	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	</body>
</html>