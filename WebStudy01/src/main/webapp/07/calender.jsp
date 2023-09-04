<%@page import="java.time.LocalDate"%>
<%@page import="java.time.YearMonth"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.TextStyle"%>
<%@page import="java.time.DayOfWeek"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   Locale locale = request.getLocale(); // 언어의 기준은 client로 바뀌어야함!
   YearMonth targetMonth = YearMonth.now();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.before,.after{
		color: silver;
	}


</style>
</head>
<body>
<table>
	<thead>
		<tr>
		<%
		//$1 파라미터 넘어가는 순서
		String ptrn = "<td class='%s2$'>%1$s</td>";
		DayOfWeek[] weeks= DayOfWeek.values();
			for(int col=0; col<7; col++){
				out.println(
						String.format(ptrn, weeks[col].getDisplayName(TextStyle.SHORT,locale ),"")
						);
			
			}
		
		%>	
		</tr>
	</thead>
	<tbody>
		<%
			LocalDate firstDate = targetMonth.atDay(1);  // 9/1 객체 만듦
			int offset = firstDate.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue(); //9/1 금 - 월 ㄴ
			LocalDate date = firstDate.minusDays(offset);
			for(int row=0; row<6; row++){
				out.println("<tr>");
				for(int col=0; col<7; col++){
					//
					String clz="";
					out.println(
							String.format(ptrn, date.getDayOfMonth(), clz)
							);
					date = date.plusDays(1);   //9월 기준 금
				}
				
				out.println("</tr>");
			}
	
		%>

	</tbody>



</table>



</body>
</html>