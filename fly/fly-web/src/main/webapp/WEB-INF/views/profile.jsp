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
		<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/ggg.css"/>
		<title>Profile</title>
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
		<div class="container prof">
			<div id="main">	
				<div class="row" id="real-estates-detail">
					<div class="col-lg-4 col-md-4 col-xs-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<header class="panel-title">
									<div class="text-center">
										<strong>Пользователь</strong>
									</div>
								</header>
							</div>
							<div class="panel-body">
								<div class="text-center" id="author">
									<img src="${contextPath}/resources/images/hqdefault2.jpg">
									<h3>жирафичек</h3>
									<p>Здесь будет статус пользователя</p>
									<!-- <p class="sosmed-author">
									<a href="#"><i class="fa fa-facebook" title="Facebook"></i></a>
									<a href="#"><i class="fa fa-twitter" title="Twitter"></i></a>
									<a href="#"><i class="fa fa-google-plus" title="Google Plus"></i></a>
									<a href="#"><i class="fa fa-linkedin" title="Linkedin"></i></a>
									</p> -->
								</div>
							</div>
						 </div>
					 </div>
					 <div class="col-lg-8 col-md-8 col-xs-12">
						<h4>История профиля</h4>
						<table class="table table-th-block">
							<tbody>
								<tr>
									<td class="active">ID:</td>
									<td>${currentUser.id}</td>
								</tr>
								<tr>
									<td class="active">Имя:</td>
									<td>${currentUser.name}</td>
								</tr>
								<tr>
									<td class="active">Email:</td>
									<td>${currentUser.email}</td>
								</tr>				 
							</tbody>
						</table>
					 </div>
				 </div>
			</div>
		</div>
		<script src="${contextPath}/resources/js/jquery-3.3.1.js"></script>
    	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	</body>
</html>