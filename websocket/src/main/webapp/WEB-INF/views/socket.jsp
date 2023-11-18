<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>난 소켓을 쓰는 페이지</h1>
<script>
    //웹소켓 연결, ws나 wss프로토콜 사용

    //페이지가 떠나가거나 새로고침 되면 webSocket 변수는 없어지거나 새로 만들어짐
    let webSocket = new WebSocket("ws://localhost:9080/socket/merong");


    //open 이벤트가 발생했을때 실행되는 함수
    const fOpen = () =>{
        console.log("접속이 성공했어요");

        webSocket.send("전 수진이 아니에요 ")
    }

    //message 이벤트가 발생했을 때 실행되는 함수
    const fMsg = () =>{
        console.log("서버에서 온 메세지:",event.data); //서버가 보낸 내용은  event data에 담겨 있음

    }
    //접속에 성공하면 open이벤트 발생
    webSocket.onopen = fOpen

    //서버에서 나에게 메세지를 보내면 messagae이벤트 발생
    webSocket.onmessage = fMsg

</script>
</body>
</html>