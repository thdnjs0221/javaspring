package com.anboyoung.socket.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.anboyoung.socket.vo.MsgVO;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ChatHandler extends TextWebSocketHandler {
	private static List<WebSocketSession> list = new ArrayList<WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("## 누군가 접속");
		list.add(session);
	}

	// 클라이언트 소켓과 통신(메세지를 주고받는) 하는 핵심 메소드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//String uMsg = message.getPayload();
	    System.out.println("메세지 누느로 확인:" + message.getPayload());
		
	    //json문자열을 VO로 바꾸깅
	    ObjectMapper shMapper = new ObjectMapper(); 
	    MsgVO msgVO = shMapper.readValue(message.getPayload(),MsgVO.class);
	    msgVO.setFrom("여긴 중계소얌");  // 그냥 괜히 바꾸어봄
	   
	    
	    //VO를 json문자열로 바꾸깅
	    String jsonStr = shMapper.writeValueAsString(msgVO);
	    
	    for (WebSocketSession webSocketSession : list) {
//			webSocketSession.sendMessage(new TextMessage("누구랑공 첨 듣는 이름인뎅!"));
			// 보낸이만 빼공 나머지 사람들에게 보내깅!
			if(webSocketSession != session ) {
				//webSocketSession.sendMessage(message);				
				webSocketSession.sendMessage(new TextMessage(jsonStr));				
			}
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("## 누군가 떠남");
		list.remove(session);
	}
}