<%@page import="java.util.Arrays"%>
<%@page import="java.sql.Array"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
   table{
      border-collapse: collapse;
   }
   th,td{
      border: 1px solid black;
   }
   .red{
      background-color: red;
   }
</style>
</head>
<body>
   <h4>HttpServletRequest : request packaging</h4>
   <pre>
      1. Request Line : URL(URI), Request Method(Get/Post)
         Request Method : 요청의 목적이자 의도이며, 포장 규칙을 정의하는 단어
          - GET(default) : client의 주 역할
          - POST(form 기반의 요청) : request body가 필요함
          - PUT/PATCH(자원의 갱신이 목적)
          - DELETE(자원의 삭제가 목적)
          - OPTION(preFlight 요청) : 본 요청을 보내기 전에 그 요청을 사용할 수 있는지(PUT이나 DELETE요청 등) 사전에 물어보는 요청
          - HEAD(response content가 없음) : 로그인 시간 연장할 때 등에 사용
          - TRACE(server tracking/debugging)
          
          <%
             StringBuffer requestURL = request.getRequestURL();
            String requestURI = request.getRequestURI();
            String requestMethod = request.getMethod();
          %>
          URL : <%=requestURL%>
          URI : <%=requestURI%>
          Method : <%=requestMethod %>
          
      2. Request Header : Meta Data(client + content), name(String)/value(String)의 쌍으로 이루어져 있다.
         <%
            String userAgent = request.getHeader("user-agent");
         %>
         UserAgent : <%=userAgent %>
      
      3. Request Body(Content Body, Message Body)
      		1)parameter (String)
      				getParameter(name),String[] getParameter(name),getParameterMap()
      			-query string 형태 전송(보안 취약)
      			-content body 전송
      		2)multipart (stream)
      		3)payload(JSON/XML...,unmarshalling...)
      		 
   </pre>
   <div>
   
   		<a href="?param1=value1&param2=한글값">Query String 형태 전송</a>
   		<form method="post">
   			<input type="text" name="param3" value="value3"/> 
   			<input type="text" name="param3" value="value3-1"/>  			
   			<input type="text" name="param4" value="value4"/> 
   			<input type="submit" value="전송"/>
   			<!--body가 없음 get  / method="post"으로 써주기-->
   		</form>
   </div>
   <table>
      <thead>
         <tr>
            <th>파라미터 이름</th>
            <th>파라미터 값</th>
         </tr>
      </thead>  
      <tbody>
       <%
       		//파라미터를 확보하기 전에 미리 설정(POST 요청의 request body에 적용됨).
       		//request.setCharacterEncoding("utf-8");
       		String ptrn = "<tr class='%3$s'><td>%1$s</td><td>%2$s</td></tr>";
       	
       		Map<String,String[]> paramMap= request.getParameterMap();
       		if(paramMap.isEmpty()){
       			out.println("<tr><td colspan='2'>파라미터 없음 </td></tr>");
       		}else{
       			
       			for(Entry<String,String[]> entry:paramMap.entrySet()){
       				String paramName=entry.getKey();
       				String[] paramValues=entry.getValue();
       				
       				out.println(
       						String.format(ptrn, paramName,Arrays.toString(paramValues) ,"")
       						);    				
       			}
       		}
  
         %>
      
      
      </tbody>
   </table>
   <hr/>
   <table>
      <thead>
         <tr>
            <th>헤더 이름</th>
            <th>헤더 값</th>
         </tr>
      </thead>
      <tbody>
         <%
         
            
            Enumeration<String> headerNames = request.getHeaderNames();

            while(headerNames.hasMoreElements()){
               String name = headerNames.nextElement();
               String value = request.getHeader(name);
               String clzValue = "user-agent".equals(name) ? "red" : "etc";
               out.println(
                  String.format(ptrn, name, value, clzValue)      
               );
            }
            
         %>
      </tbody>
   </table>

</body>
</html>

