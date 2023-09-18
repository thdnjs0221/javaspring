package kr.or.ddit.calculate.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.calculate.NumericOpertorType;
import kr.or.ddit.vo.CalculateVO;

@WebServlet("/calculate/case4")
public class CalculateControllerServlet_Case4 extends HttpServlet{
	
	/**
	 *UI제공
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String goPage = "/WEB-INF/views/calculate/case4/calForm.jsp";
		
		if(goPage.startsWith("redirect:")) { //Redirect
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
			
		}else {//Dispatcher로 이동
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
	
	//검증
	
	
	/**
	 *UI를 통해 입력한 데이터(parameter) 처리
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.utf-8
		req.setCharacterEncoding("utf-8");
		//역직렬화 -> 언마셜링(ObjectMapper로 해주기 마셜링도ObjectMapper로 가능 )
		boolean valid=true;
		
		try(
		 InputStream is = req.getInputStream(); //request 바디에 있는거 읽어 들이기 위한 getInputStream()
			){
			ObjectMapper mapper = new ObjectMapper();
			CalculateVO calVO = mapper.readValue(is, CalculateVO.class); 
			req.setAttribute("calVO", calVO);
		}catch (Exception e) {
			//검증불통과 (언마셜링에서 문제 생겼을떄)
			req.setAttribute("errors", e.getMessage());
			valid = false;  
		}
		
		
		String goPage = null;
		
		if(valid) {
			
			goPage = "/WEB-INF/views/calculate/case4/calculateView.jsp";
			
		}else {
			//검증 불통과 calForm 으로 이동		
			//검증 결과 뷰로 이동하기!
			goPage="/WEB-INF/views/calculate/case4/messageView.jsp";
			
		}
	
		//모든 컨트롤러의 마지막은  페이지 이동
		
		if(goPage.startsWith("redirect:")) { //Redirect
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
			
		}else {//Dispatcher로 이동
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
	

}
