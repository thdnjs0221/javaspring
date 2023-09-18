package kr.or.ddit.calculate.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculate/case1")
public class CalculateControllerServlet_Case1 extends HttpServlet{
	
	/**
	 *UI제공
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String goPage = "/WEB-INF/views/calculate/case1/calForm.jsp";
		
		if(goPage.startsWith("redirect:")) { //Redirect
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
			
		}else {//Dispatcher로 이동
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
	
	//검증
	private boolean validate(HttpServletRequest req, Map<String, String> errors) {
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
		if(opParam==null || opParam.trim().isEmpty() || !opParam.matches("PLUS|MINUS|MULTIPLY|DIVIDE")) {
			//4개의 연산자 외에 다른 연산자가 들어갔는지 판단
			valid &= false;
			errors.put("operator", "우측 피연산자 오류");
			
		}
			return valid;
	}
	
	
	/**
	 *UI를 통해 입력한 데이터(parameter) 처리
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.utf-8
		req.setCharacterEncoding("utf-8");
		//2. 파라미터 받기 
		String leftParam = req.getParameter("leftOp");
		String rightParam = req.getParameter("rightOp");
		String opParam = req.getParameter("operator");
		
		
		String goPage = null;
		//3.검증(필수파라미터 누락, 숫자, 4개의 연사자중 하나의 값만 넘어왔는지) 
		Map<String, String>errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors); //에러메세지 담아 있음(콜 바이 래퍼런스 구조)
		if(validate(req, errors)) {
			//검증 통과 되었으면 숫자로 변경 
			int leftOp = Integer.parseInt(leftParam);
			int rightOp = Integer.parseInt(rightParam);
			int result = -1;
			String expression = null;
			char sign = '/';  //기호
			
			switch (opParam) {
			case "PLUS":
				result = leftOp + rightOp;
				sign = '+';
			
				break;
			case "MINUS":
				result = leftOp - rightOp;
				sign = '-';
				break;
			case "MULTIPLY":
				result = leftOp * rightOp;	
				sign = '*';
				break;
				
			default:
				result = leftOp / rightOp;		
				break;
			}
			
			expression = String.format("%d %c %d = %d",leftOp, sign ,rightOp, result);
			req.setAttribute("expression", expression); 
			goPage = "/WEB-INF/views/calculate/case1/calculateView.jsp";
			
		}else {
			//검증 불통과 calForm 으로 이동		
			goPage = "/WEB-INF/views/calculate/case1/calForm.jsp";
			
		}
	
		//모든 컨트롤러 의 마지막은  페이지 이동
		
		if(goPage.startsWith("redirect:")) { //Redirect
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
			
		}else {//Dispatcher로 이동
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
	

}
