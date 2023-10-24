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

@WebServlet("/calculate/case8")
public class CalculateControllerServlet_Case8 extends HttpServlet{
	
	/**
	 *UI제공
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String goPage = "/WEB-INF/views/calculate/case8/calForm.jsp";
		
		if(goPage.startsWith("redirect:")) { //Redirect
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
			
		}else {//Dispatcher로 이동
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
	
	private CalculateVO getCalculatorVOFromJson(HttpServletRequest req) throws Exception {
		try(
				 InputStream is = req.getInputStream(); //request 바디에 있는거 읽어 들이기 위한 getInputStream()
					){
					ObjectMapper mapper = new ObjectMapper();
					return mapper.readValue(is, CalculateVO.class); 
				
				}
	}
	
	private boolean validate(HttpServletRequest req, Map<String, String> errors) {
		//파라미터로 받을때 필요함
		
		boolean valid = true;
		String leftParam = req.getParameter("leftOp");
		String rightParam = req.getParameter("rightOp");
		String opParam = req.getParameter("operator");
		
		//3.검증
		if(leftParam == null || leftParam.trim().isEmpty() || !leftParam.matches("\\d+")) {
			//값이 누락되었는지 확인
			valid &= false;
			errors.put("leftOp", "좌측 피연산자 오류");
		}
		if(rightParam == null || rightParam.trim().isEmpty() || !rightParam.matches("\\d+")) {
			//값이 누락되었는지 확인
			valid &= false;
			errors.put("rightOp", "우측 피연산자 오류");
			
		}
		if(opParam==null || opParam.trim().isEmpty() ) {
			//4개의 연산자 외에 다른 연산자가 들어갔는지 판단
			valid &= false;
			errors.put("operator", "연산자 누락");
			
		}else {			
			//예외처리를 if문처럼 활용할수있음
			try {
				NumericOpertorType.valueOf(opParam);
			} catch (IllegalArgumentException e) {
				valid &= false;
				errors.put("operator", "연산자 종류 오류");
			}
		}
			return valid;
	}
	
	private CalculateVO getCalculatorVOFromParameters(HttpServletRequest req) throws Exception {
		Map<String, String>errors = new LinkedHashMap<>();
		if(validate(req, errors)) {
			//검증 통과
			String leftParam = req.getParameter("leftOp");
			String rightParam = req.getParameter("rightOp");
			String opParam = req.getParameter("operator");
			
			int leftOp = Integer.parseInt(leftParam);
			int rightOp = Integer.parseInt(rightParam);
			
			//switch case사용하지 않고 enum 활용하여 변경!
			//enum하나로 캡슐화해서 가져오기 
			NumericOpertorType operator = NumericOpertorType.valueOf(opParam);
			
			return new CalculateVO(leftOp, rightOp, operator);
		}else {
			//검증 통과x
			throw new Exception(errors.toString());
		}
		
		
	}
	
	
	/**
	 *UI를 통해 입력한 데이터(parameter) 처리
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.utf-8
		req.setCharacterEncoding("utf-8");
		
		String requestContentType= req.getContentType();
		String accept = req.getHeader("accept");  //내가 보내는 편지
		 
	
		//역직렬화 -> 언마셜링(ObjectMapper로 해주기 마셜링도ObjectMapper로 가능 )
		
		int sc = 200;
		String message = null;
		CalculateVO calVO = null;
		try{

			if(requestContentType.contains("json")) {
				calVO =getCalculatorVOFromJson(req);
			}else if(requestContentType.contains("xml")) { //클라이언트가 보내는 xml
				sc = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
			}else {
				calVO = getCalculatorVOFromParameters(req);
			}
				
		}catch (Exception e) {
			//검증불통과 
			sc = 400;
			message = e.getMessage();
		}
		if(sc!=200) {
			resp.sendError(sc,message);
			return;
		}
		
	
		req.setAttribute("calVO", calVO);
		
		//응답 json or html - 요청헤더에 따라서 
		String goPage = null;
		if(accept.contains("json")) {
			goPage = "/jsonView.view";
		}else if(accept.contains("xml")) { //내가 쓸수 없는 xml
			resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
			return;
			
		}else {
			goPage="/WEB-INF/views/calculate/case8/calculateView.jsp";
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
