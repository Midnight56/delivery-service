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
		<title>My Page</title>
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
		<div class="return">
			<a href="${contextPath}/welcome">--> Вернуться на главную</a>
		</div>
		<div class="container">
			<div>
                <!-- Nav tabs -->
                <div class="admpage_admin_panel">
                    <ul class="nav nav-tabs" role="tablist">
	                    <li role="presentation" class="active"><a href="#users" aria-controls="users" role="tab" data-toggle="tab">Список пользователей</a></li>
	                    <li role="presentation"><a href="#orders" aria-controls="orders" role="tab" data-toggle="tab">Список заказов</a></li>
                    </ul>

                    <!-- Tab panes -->
                    <div class="tab-content">
	                <div role="tabpanel" class="tab-pane active" id="users">
	                 	<c:if test="${!empty userList}">
							<div class="row centered">
								<div class="table-responsive">
									<table class="table table-hover table-bordered" style="margin: auto;">
										<thead>
											<tr>
												<th width="50">ID пользователя</th>
												<th width="100">Имя пользователя</th>
												<th width="50">роль</th>
												<th width="50">статус</th>
												<th width="180">действия</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="user" items="${userList}">
												<tr>
													<td width="50">${user.id}</td>
													<td width="100">${user.name}</td>
													<td width="50">
														<c:forEach var="role" items="${user.roles}">
															${role.name}<br>
														</c:forEach>
													</td>
													<td width="50">
														<c:if test="${user.block == true}">забанен</c:if>
														<c:if test="${user.block == false}">активен</c:if>
													</td>
													<td width="180">
														<c:if test="${user.block == false && user.name != 'admin'}">
															<a href="${contextPath}/admin/users/${user.id}/block" class="btn btn-danger btn-md" >забанить</a>
														</c:if>
														<c:if test="${user.block == true && user.name != 'admin'}">
															<a href="${contextPath}/admin/users/${user.id}/unblock" class="btn btn-warning btn-md" >разбанить</a>
														</c:if>
														<c:if test="${user.roles.size() == 1 && user.name != 'admin'}">
															<a href="${contextPath}/admin/users/${user.id}/make-admin" class="btn btn-primary btn-md" style="margin-left: 20px;">назначить админом</a>
														</c:if>
														<c:if test="${user.roles.size() > 1 && user.name != 'admin'}">
															<a href="${contextPath}/admin/users/${user.id}/delete-admin" class="btn btn-primary btn-md" style="margin-left: 20px;">снять полномочия</a>
														</c:if>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>	
						</c:if>
					</div>
						<div role="tabpanel" class="tab-pane" id="orders">
	                    	<c:if test="${!empty orderList}">
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
													<th width="50">статус</th>
													<th>действия</th>
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
														<td width="50">
															<c:if test="${order.status == true}">доступен</c:if>
															<c:if test="${order.status == false}">закрыт</c:if>
														</td>
														<td>
															<c:if test="${order.status == true}">
																<a href="${contextPath}/admin/orders/${order.id}/close" class="btn btn-primary btn-md">Закрыть</a>
															</c:if>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>	
							</c:if>
	                	</div>
                	</div>
				</div>
        	</div>
		</div>
		<script src="${contextPath}/resources/js/jquery-3.3.1.js"></script>
    	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	</body>
</html>