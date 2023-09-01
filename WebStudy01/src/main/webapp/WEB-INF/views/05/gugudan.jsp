<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
   int minDan = (Integer)request.getAttribute("minDan");
   int maxDan = (Integer)request.getAttribute("maxDan");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   table{
   border-collapse: collapse;
   }
   
   td, tr{
    border: 1px solid black;
   }
 
</style>
</head>
<body>

<form id="gugudanForm">
<input type="number" placeholder="min dan" name="minDan">
<input type="number" placeholder="max dan" name="maxDan">
<button type="submit">전송</button>
</form>

<h4>table 태그를 이용한 구구단 출력(<%=minDan %>단~<%=maxDan %>단, 승수 1~9)</h4>

<table>
   <%!
      /*
         container
         인스턴스에 대한 권한은 컨테이너가 가지고 있기 때문에
         인스턴스에 대한 레퍼런스를 가져올 수 없음
         
         전역변수를 통한 데이터 공유 안됨
         웹어플리케이션안에서 데이터를 공유하려면 scope, attribute 필요
      */
      //public 써봤자 밖에서 접근 못함 !
      private StringBuffer gugudan(int minDan, int maxDan){ 
      StringBuffer trTags = new StringBuffer();
      for(int dan=minDan; dan<=maxDan; dan++){
         trTags.append("<tr>");
         for(int mul=1; mul<=9; mul++){
            trTags.append(
                  String.format("<td>%d*%d=%d</td>", dan, mul, dan*mul)      
            );
         }
         trTags.append("</tr>");
      }
      return trTags;
   }
   %>
   <%=gugudan(minDan,maxDan) %>
</table>
<hr />
<table>
   <%
      StringBuffer trTags = new StringBuffer();
      for(int dan=minDan; dan<=maxDan; dan++){
         trTags.append("<tr>");
         for(int mul=1; mul<=9; mul++){
            trTags.append(
                  String.format("<td>%d*%d=%d</td>", dan, mul, dan*mul)      
            );
         }
         trTags.append("</tr>");
      }
   
   %>
   <%=trTags%>
</table>
<hr />
<table>
   <%
      for(int dan=minDan; dan<=maxDan; dan++){
         out.println("<tr>");
         for(int mul=1; mul<=9; mul++){
            out.println(
                  String.format("<td>%d*%d=%d</td>", dan, mul, dan*mul)      
            );
         }
         out.println("</tr>");
      }
   %>
</table>
<hr />
<table>
   <%
      for(int dan=minDan; dan<=maxDan; dan++){
   %>
      <tr>   
   <%      
         
         for(int mul=1; mul<=9; mul++){
   %>
         <td><%=String.format("%d*%d=%d", dan, mul, dan*mul)%></td>
   
   <%         
         }
   %>
      </tr>
   <%         
      }
   %>
</table>
</body>
</html>