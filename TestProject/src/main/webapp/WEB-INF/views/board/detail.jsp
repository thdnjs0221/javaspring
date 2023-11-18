<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<body>
<h1>board detail </h1>
<hr />
	<table>
		<tr>
			<td>${board.boTitle }</td>
			
		</tr>
		<tr>
			<td>${board.boWriter } ${board.boDate } ${board.boHit }</td>
			
		</tr>
		<tr>
			<td>${board.boContent }</td>
		</tr>
	</table>
	<form action="/board/delete.do" method="post" id="proForm">
		<input type="hidden" name="boNo" value="${board.boNo }"/>
	 </form>
	<button type="button" id="udtBtn">수정</button>
	<button type="button" id="delBtn">삭제</button>
	<button type="button" id="listBtn" onclick="javascript:location.href='/board/list.do'">목록</button>
</body>
<script type="text/javascript">
$(function(){
	var udtBtn = $("#udtBtn");
	var delBtn = $("#delBtn");
	var proForm = $("#proForm");
	
	udtBtn.on('click',function(){
		
		proForm.attr("action","/board/update.do");
		proForm.attr("method","get");
		proForm.submit();
		
	});
	
	delBtn.on('click',function(){
		if(confirm("정말로 삭제하시겠습니까?")){
			proForm.submit();
		}
		
	});
	
	
});

</script>
</html>