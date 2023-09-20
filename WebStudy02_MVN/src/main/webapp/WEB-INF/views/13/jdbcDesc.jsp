<%@page import="kr.or.ddit.vo.DataBasePropertyVO"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>13/jdbcDesc.jsp</title>
</head>
<%
   String message = (String)request.getAttribute("message");
%>
<body>
<h4>JDBC(Java DataBase Connectivity)</h4>
<pre>
   1. 드라이버를 빌드패스에 추가
   2. 드라이버(클래스) 로딩
   3. Connection 생성
   4. 쿼리 객체 생성
    - Statement : 쿼리 객체 생성시 뭐리가 고정되지 않기 때문에, runtime에 동적쿼리 실행 가능
    - PerparedStatement(선컴파일된 쿼리 객체) :  쿼리를 미리 컴파일 하고 뭐리 객체를 생성함.
    					runtime에 쿼리에 사용되는 literal(값)을 변경하여 쿼리를 재사용함.
    - CallableStatement : 절차적 코드집합인 function/procedure를 호출할때 사용함.

   5. 쿼리 실행
   6. 결과 집합 핸들링(select..)
   7. close(***) - try with resource 구문 활용
</pre>
<%
/*
   //model1구조
   
   List<Map<String, Object>> list = new ArrayList<>();         
   
   try(      
      Connection conn = ConnectionFactory.getConnection();
      Statement stmt =  conn.createStatement(); //4. 쿼리객체 생성
   ){
      StringBuffer sql = new StringBuffer();
      sql.append(" select property_name, property_value, description");
      sql.append(" from database_properties                         ");
      ResultSet rs = stmt.executeQuery(sql.toString());//5.쿼리실행
      
      while(rs.next()){
         Map<String, Object> record = new HashMap<>();
         list.add(record);
         record.put("propertyName", rs.getString("PROPERTY_NAME"));
         record.put("propertyValue", rs.getString("PROPERTY_VALUE"));
         record.put("description", rs.getString("DESCRIPTION"));
      }
   }
*/
   List<DataBasePropertyVO> list = (List<DataBasePropertyVO>) request.getAttribute("list");
%>
<table>
   <thead>
      <tr>
         <th>PROPERTY_NAME</th>
         <th>PROPERTY_VALUE</th>
         <th>DESCRIPTION</th>
      </tr>
   </thead>
   <tbody>
      <%
      if(list.isEmpty()){
      %>
         <tr>
            <td colspan="3">조회 결과 없음.</td>
         </tr>
      <%
      }else{
         for(DataBasePropertyVO record : list){
            %>
            <tr>
               <td><%=record.getPropertyName() %></td>
               <td><%=record.getPropertyValue() %></td>
               <td><%=record.getDescription() %></td>
            </tr>
            <%
         }
      }
   %>
   </tbody>
</table>

</body>
</html>