<%@page import="java.time.temporal.WeekFields"%>
<%@page import="java.time.Month"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.text.MessageFormat"%>
<%@page import="java.util.stream.Stream"%>
<%@page import="java.time.Year"%>
<%@page import="java.util.Optional"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.YearMonth"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.TextStyle"%>
<%@page import="java.time.DayOfWeek"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--UI만 가지고 있음  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<style>
   .before,.after{
      color: silver;
   }
   table{
      border-collapse: collapse;
      width : 100%;
      min-height : 500px;
      text-align: center;
      font-size: large;
   }
   th,td{
      border : 1px solid black;
   }
   .SUNDAY{
      color: red;
   }
   .SATURDAY{
      color: blue;
   }
</style>
</head>
<body>

<%!
   final String OPTPTRN = "<option value='%s'>%s</option>";
%>
<%
 	Locale locale = request.getLocale();

%>
<!-- html 요소들은 다 클라이언트사이드라 경로 <%=request.getContextPath() %>/calender -->
<form id="calForm"  method="post"
action="<%=request.getContextPath() %>/calender"
>
   <input type="number" name="year" />
   <select name="month">
   <%=
      Stream.of(Month.values())
         .map((m)->{
            String display = m.getDisplayName(TextStyle.FULL,locale);
            return String.format(OPTPTRN, m.getValue(), display);
            })
         .collect(Collectors.joining("\n"))
   %>
   </select>
   <select name="locale" >
      <%=
         //Locale -> Option Tah String : map
         //element collection : collect(Collectors)         
            Stream.of(Locale.getAvailableLocales())
            .filter((l)->!l.getDisplayName(locale).isEmpty())
                .map((l)->{
                   return String.format(OPTPTRN, l.toLanguageTag(),  l.getDisplayName(l));
                }).collect(Collectors.joining("\n"))
         %>
   </select>
</form>
<div id="resultArea">

</div>

<script>
//selector : ex) $("#calForm") - htmlelement를 jquery 객체로 wrapping 함.
// Integer : wrapper class , int -> new Integer(3), adapter design pattern
// 	console.log(calForm);
// 	console.log($(calForm));
	$(":input[name]").on("change",function(event){  //:input - >모든 input태그 포함  :input[name] name속성이 있으면
		//서버 전송(submit)
		this.form.requestSubmit();
	});  
	$(calForm).on("submit",function(event){
		event.preventDefault(); //폼의 동기 요청 중단
		console.log(event.target);
		console.log(this);
		console.log($(this));
// 		동기요청 -> 비동기 요청으로 변환 (요청의 기본적인구조(form)는 달라지지않음)
	let url = this.action;  //다음번의 요청방향을 받아낸것 (비동기여야함)
	let method = this.method; //다음번의 요청방향을 받아낸것 (비동기여야함)
	let data = $(this).serialize(); //
// 	ex) year =2023&month=8&locale=ko-KR
	let settings={
		url:url,
		method:method,
		data:data,
		dataType:"html",
		success:function(resp){   //resp은 dataType에 따라 달라진다
			$(resultArea).html(resp);
		}
	};
	
	$.ajax(settings);
});

	$("#resultArea").on("click","a",function(event){ //핸들러는 정적이지만 자식 a 클릭했을떄 동작하게 하는것!!(동적 엘리먼트 사용할때 이렇게 쓰기)
		console.log("a tag click!!!!");
		//this -> a태그 
		let year = $(this).data("year");
		let month = $(this).data("month") //제이 쿼리는 보통 data를 쓰고 , dataset은 스크립트	     
	    calForm.year.value = year;
	    calForm["month"]["value"] = month;
	    $(calForm).submit(); //이건 제이쿼리 함수 이벤트함수도 발생함
	   // calForm.requestSubmit();  //서브밋일때 그냥 전송함 이벤트 발생 x / requestSubmit때는 이벤트 발생
	});

   
   
</script>
</body>
</html>
