<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
		<meta name="generator" content="Hugo 0.88.1">
		<meta name="theme-color" content="#7952b3">

		<title><tiles:getAsString name="title"/></title>
		
		<tiles:insertAttribute name="preScript"/>
		
		
		
		
		<style>
			.bd-placeholder-img {
				font-size: 1.125rem;
				text-anchor: middle;
				-webkit-user-select: none;
				-moz-user-select: none;
				user-select: none;
			}
			
			@media ( min-width : 768px) {
				.bd-placeholder-img-lg {
					font-size: 3.5rem;
				}
			}
		</style>
		
		<!-- Custom styles for this template -->
		<link href="<%=request.getContextPath()%>/resources/css/dashboard.css" rel="stylesheet">
	</head>
	
<!-- 	바디 데이터 pageContext을 속성을 만들고 스크립트에서 꺼내서 사용하기 (절대 경롤)-->
	<body class="d-flex flex-column vh-100" data-context-path="${pageContext.request.contextPath }">
		<header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
			<tiles:insertAttribute name="headerMenu"/>
		</header>
	
		<div class="container-fluid">
			<div class="row">
				<tiles:insertAttribute name="leftMenu"/>
				<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
					
					<!-- Main Content Area start -->
						<tiles:insertAttribute name="content"/>
					<!-- Main Content Area end -->
					
				</main>
			</div>
		</div>
	
		<footer class="footer mt-auto py-3 bg-dark col-md-9 ms-sm-auto col-lg-10">
			<tiles:insertAttribute name="footer"/>
		</footer>
		
		<tiles:insertAttribute name="postScript"/>
	</body>
</html>
