package kr.or.ddit.servlet06.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.servlet06.dao.DataBasePropertyDAO;
import kr.or.ddit.servlet06.service.DataBasePropertyService;
import kr.or.ddit.servlet06.service.DataBasePropertyServiceImpl;
import kr.or.ddit.vo.DataBasePropertyVO;

@WebServlet("/13/jdbcDesc.do")
public class DataBasePropertiesControllerServlet extends HttpServlet {
	private DataBasePropertyService service = new DataBasePropertyServiceImpl();
	
	//private DataBasePropertyDAO dao = new DataBasePropertyDAO();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<DataBasePropertyVO> list = service.retrieveDBPropertyList();
		req.setAttribute("list", list);
		
		//vi ew 페이지 이동
		String goPage = "/WEB-INF/views/13/jdbcDesc.jsp";
		
		if (goPage.startsWith("redirect:")) { // Redirect
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);

		} else {// Dispatcher로 이동
			req.getRequestDispatcher(goPage).forward(req, resp);
		}

	}

}
