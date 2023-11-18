<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<body>
	<c:set value="등록" var="text"/>
	<c:if test="${status eq 'u' }">
		<c:set value="수정" var="text"/>
	</c:if>
	<h1>board ${text}</h1>
	<hr />
	<form action="/board/insert.do " method="post" id="insertForm">
		<c:if test="${status eq 'u' }">
			<input type="hidden" name="boNo" value="${board.boNo }"/>
		</c:if>
	<table border="1">
		<tr>
			<td>제목</td>
			<td>
			<input type="text" name="boTitle" id="boTitle" value="${board.boTitle }"/>
			</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>
			<input type="text" name="boWriter" id="boWriter" value="${board.boWriter }"/>
			</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
			<textarea rows="30" cols="50" name="boContent" id="boContent">${board.boContent }</textarea>
			</td>
		</tr>
	</table>
	</form>
	<button type="button" id="addBtn">${text}</button>
	<button type="button" id="listBtn" onclick="javascript:location.href='/board/list.do'">목록</button>
</body>
<script type="text/javascript">
$(function(){
	var addBtn = $("#addBtn");
	var listBtn = $("#listBtn");
	var insertForm = $("#insertForm");
	
	addBtn.on('click',function(){
		//제목 작성자 내용 데이터를 가져와서 validation 진행
		//데이터 기본 유효성 검사를 활용하여 (공백인지 숫자인지 등등)
		
		var title = $("#boTitle").val();  //제목
		var writer = $("#boWriter").val(); //작성자
		var content = $("#boContent").val(); //내용
		
		if(title == null || title==""){
			alert("제목을 입력해주세요");
			$("#boTitle").focus();
			return false;
		}
		
		if(writer == null || writer==""){
			alert("작성자를 입력해주세요");
			$("#boWriter").focus();
			return false;
		}
		
		if(content == null || content==""){
			alert("내용을 입력해주세요");
			$("#boContent").focus();
			return false;
		}
		
		if($(this).text()=="수정"){
		 	insertForm.attr("action","/board/update.do");	
		}
		
		insertForm.submit();
	});
	
	listBtn.on('click',function(){
		
		
	});
});








</script>
</html>