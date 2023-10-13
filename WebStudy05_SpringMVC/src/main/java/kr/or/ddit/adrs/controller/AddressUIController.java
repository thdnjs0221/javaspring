package kr.or.ddit.adrs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;


//@WebServlet("/adrs/view")
@Controller
public class AddressUIController {
	
	@RequestMapping("/adrs/view")
	public String doGet() {
		return "adrs/adrsView";

		

	}

}
