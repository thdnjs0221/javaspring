<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>adminlte 3 | simple tables</title>


  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=source+sans+pro:300,400,400i,700&display=fallback">
 
  <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/plugins/fontawesome-free/css/all.min.css">
 
  <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/dist/css/adminlte.min.css">
  
  <script src="${pageContext.request.contextPath}/resources/plugins/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
</head>

<body class="hold-transition sidebar-mini">
<div class="wrapper">
  
     
<!--   header 영역 -->
  <tiles:insertAttribute name="header" />
  <div class="content-wrapper">
 
<!--     content 영역 -->
 <tiles:insertAttribute name="content" />
   
  </div>

<!--   footer 영역-->
 <tiles:insertAttribute name="footer" />


 
</div>





<script src="${pageContext.request.contextPath}/resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>

<script src="${pageContext.request.contextPath}/resources/dist/js/adminlte.min.js"></script>
</body>
</html>
