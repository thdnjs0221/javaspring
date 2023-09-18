package kr.or.ddit.calculate.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.calculate.NumericOpertorType;

@WebServlet("/calculate/case2")
public class CalculateControllerServlet_Case2 extends HttpServlet{
	
	/**
	 *UI제공
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String goPage = "/WEB-INF/views/calculate/case2/calForm.jsp";
		
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
			
			//switch case사용하지 않고 enum 활용하여 변경!
			//enum하나로 캡슐화해서 가져오기 
			NumericOpertorType operator = NumericOpertorType.valueOf(opParam);
			
			int result = operator.operate(leftOp, rightOp);  
			
			String expression = operator.getExpression(leftOp, rightOp);
			
			req.setAttribute("expression", expression); 
			goPage = "/WEB-INF/views/calculate/case2/calculateView.jsp";
			
		}else {
			//검증 불통과 calForm 으로 이동		
			//goPage = "/WEB-INF/views/calculate/case2/calForm.jsp"; // ->비동기라서 ui가 한번더 나옴 검증 결과 뷰로 이동하기!
			
			//검증 결과 뷰로 이동하기!
			goPage="/WEB-INF/views/calculate/case2/messageView.jsp";
			
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
