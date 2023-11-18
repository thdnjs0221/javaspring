package com.anboyoung.socket.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class ChatHandler extends TextWebSocketHandler {
	private static List<WebSocketSession> list = new ArrayList<WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//log.info("## 누군가 접속");
		System.out.println("## 누군가 접속");
		list.add(session);
	}

	//클라이언트 소켓과 통신(메세지를 주고 받는) 하는 핵심 메소드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//String uMsg = message.getPayload();
		System.out.println("메세지 확인:"+message.getPayload());
		
		
		
		for (WebSocketSession webSocketSession : list) {
			//보낸이만 뺴고 나머지 사람들에게 보내기 
			if(webSocketSession != session) {
				webSocketSession.sendMessage(message);
				
			}
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//log.info("## 누군가 떠남");
		System.out.println("## 누군가 떠남");
		list.remove(session);
	}
}