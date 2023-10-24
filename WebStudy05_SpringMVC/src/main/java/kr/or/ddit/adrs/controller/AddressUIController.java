package kr.or.ddit.adrs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



//@WebServlet("/adrs/view")
@Controller
public class AddressUIController {
	
	@RequestMapping("/adrs/view")
	public String doGet() {
		return "adrs/adrsView";

		

	}

}
