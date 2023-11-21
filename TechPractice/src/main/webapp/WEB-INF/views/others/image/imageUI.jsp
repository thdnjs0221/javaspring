<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form:form method="post" enctype="multipart/form-data" id="uploadForm" class="input-group mt-3">
	<input type="file" class="form-control" name="targetFile" required/>
	<input type="submit" class="btn btn-success" value="동기 전송"/>
	<input id="sendBtn" type="button" class="btn btn-primary" value="비동기 전송"/>
</form:form>

<div id="resultArea">
	<c:if test="${not empty thumbImg }">
		<p>썸네일</p>
		<img src="${thumbImg }" />
	</c:if>
	<hr />
	<c:if test="${not empty orgImg }">
		<p>원본</p>
		<img src="${orgImg }" />
	</c:if>
</div>

<script src="<c:url value='/resources/packages/others/imageUI.js' />"></script>