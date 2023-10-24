<%@page import="kr.or.ddit.calculate.NumericOpertorType"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.Arrays"%>

<%@page import="java.util.Objects"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
<script src="${pageContext.request.contextPath}/resources/js/app/calculate/calForm_case8.js"></script>
</head>
<body>
<div style="border:1px solid black;">
   <h4>Request Content Type</h4>
   <input type="radio" name="contentType" checked />PARAMETER <!-- data-content-type="application/x-www-form-urlendcoded" 이 속성을 사용하지 않아도 자동으로 -->
   <input type="radio" name="contentType" data-content-type="application/json;charset=UTF-8"/>JSON
</div>
<div style="border:1px solid black;">
   <h4>Response Content Type</h4>
   <input type="radio" name="accept" value="html" />HTML
   <input type="radio" name="accept" checked  value="json"/>JSON
</div>
<form id="calForm" method="post">
   <input type="number" name="leftOp" required value="${param.leftOp}" />
   <select name="operator" required data-init-value="${param.operator}">
      <option value>연산자</option>
      <%=
         Arrays.stream(NumericOpertorType.values())
              .map(ot->String.format("<option value='%s'>%c<option>",ot.name(),ot.getSign()))
              .collect(Collectors.joining("\n"))
      %>
   </select> 
   <input type="number" name="rightOp" required value="${param.rightOp}"/> 
   <input type="submit" value="="/>
</form>
<div id="resultArea"></div>
</body>
</html>