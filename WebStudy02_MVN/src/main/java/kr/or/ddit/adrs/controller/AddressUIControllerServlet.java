package kr.or.ddit.adrs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adrs/view")
public class AddressUIControllerServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String goPage = "/WEB-INF/views/adrs/adrsView.jsp";

		// goPage로 이동
		if (goPage.startsWith("redirect:")) {

			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
		} else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}

	}

}
