package kr.or.ddit.prod.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.paging.BootstrapPaginationRender;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdListController {
	
	@Inject
	private ProdService service ;
	
	@Inject
	private OthersDAO othersdao ;

	@ModelAttribute("lprodList")  // = model.addAttribute(model);
	public List<LprodVO> lprodList(){
		return othersdao.selectLprodList();
	}
	
	@ModelAttribute("buyerList")
	public List<BuyerVO>buyerList(){
		return othersdao.selectBuyerList(null);
	}
	
	

	//HttpServletRequest req 사용금지 Model
	@RequestMapping( value = "/prod/prodList.do", method = RequestMethod.GET)
	public String prodList(@ModelAttribute("detailCondition") ProdVO detailCondition
			, @RequestParam(value = "page", required = false, defaultValue = "1") int currentPage							
			, Model model)  {
		
		
		//@ModelAttribute이 해준 일 : 
		//ProdVO detailCondition = new ProdVO();
		//PopulateUtils.populate(detailCondition, req.getParameterMap());
		//	req.setAttribute("detailCondition", detailCondition);
		
		//@RequestParam이 해준 일 :
//		String pageParam = req.getParameter("page");
//		int currentPage = 1;
//		if (StringUtils.isNumeric(pageParam)) {
//			currentPage = Integer.parseInt(pageParam);
//		}

		listData(detailCondition, currentPage, model);

		// 뷰이동
		return "prod/prodList";

	}

	@RequestMapping("/prod/ajax/prodListUI.do")
	public String listUI() {
	
		// ui
		return "prod/prodListUI";

	}

	@RequestMapping("/prod/ajax/prodListData.do")
	public String listData(
			@ModelAttribute("detailCondition") ProdVO detailCondition 
			,@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage 
			,Model model
	) {
		PaginationInfo<ProdVO> paging = new PaginationInfo<>(3, 2);
		// paging.setSimpleCondition(simpleCondition); //키워드 검색 조건
		paging.setCurrentPage(currentPage);
		paging.setDetailCondition(detailCondition); // 검색 상세조건
		// 검색 조건, 페이징 완성

		service.retrieveProdList(paging);

		paging.setRenderer(new BootstrapPaginationRender());

		model.addAttribute("paging", paging);
		// 뷰이동
		return "jsonView";

	}
}
