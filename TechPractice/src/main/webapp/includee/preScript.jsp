<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<script type="text/javascript" src="${cPath}/resources/js/sweetalert2/sweetalert2.all.min.js"></script>
<link rel="stylesheet" href="${cPath}/resources/js/sweetalert2/sweetalert2.min.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/js/bootstrap-5.2.3-dist/css/bootstrap.min.css">    
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.6.1.min.js"></script>
<script>
	$.CPATH = "${cPath}";
	$(document).on('ajaxError', function(event, jqXHR, settings, error){
		console.log(`비동기 요청[\${settings.url}, \${settings.type}] 실패, 상태코드 : \${jqXHR.status}, 에러메시지 : \${error}`);
	});
</script>
