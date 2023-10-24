package kr.or.ddit.prod.controller;



import javax.inject.Inject;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdViewController  {
	@Inject
	private ProdService service;

	@RequestMapping("/prod/prodView.do")  //get메소드 생략되어있는
	public String prodView(@RequestParam("what") String prodId, Model model) {
		ProdVO prod = service.retrieveProd(prodId);
		model.addAttribute("prod", prod);
		
		// 뷰이동
		return "prod/prodView";
		
		
	}

}
