<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>board list</h1>
	<hr />

	<table border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
		
			<c:choose>
				<c:when test="${empty boardList }">
				<tr>
					<td colspan="5">조회하신 게시글이 존재하지 않습니다.</td>
				</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${boardList}" var="board">
						<tr>
							<td>${board.boNo }</td>
							<td><a href="/board/detail.do?boNo=${board.boNo}">${board.boTitle }</a></td>
							<td>${board.boWriter }</td>
							<td>${board.boDate }</td>
							<td>${board.boHit }</td>		
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>

		</tbody>
	</table>
	<button type="button" id="addBtn"
		onclick="javascript:location.href='/board/form.do' ">등록</button>
</body>
</html>