package kr.or.ddit.common.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



//POJO(Plain Old Java Object)
@Controller
public class IndexController  {
	
	@RequestMapping("/index.do")
	public String index(Model model){
		String title="컨트롤러에서 만든 Model 타이틀";
		
		model.addAttribute("title", title);
		
		return "index";
	}
}
