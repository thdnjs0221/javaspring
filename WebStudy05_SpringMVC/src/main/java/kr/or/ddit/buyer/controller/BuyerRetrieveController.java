package kr.or.ddit.buyer.controller;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.paging.BootstrapPaginationRender;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import kr.or.ddit.vo.PaginationInfo;

/**
 * [ uri 설계 ]
 * 
 * GET/POST
 * 
 * /buyer/buyerView.do
 * /buyer/buyerList.do
 * /buyer/buyerInsert.do
 * /buyer/buyerUpdate.do
 * /buyer/buyerDelete.do
 *
 *	Restful URI (GET/POST/PUT/DELETE) : 명사(URI), 동사(method)
 *	/buyer/form (GET) : 새로운 제조사 등록 양식 
 *	/buyer (POST) : 새로운 제조사 등록 
 *
 *	/buyer (GET) : 제조사 전체 목록 조회
 *	/buyer/P10101 (GET) : 제조사 한건 상세 조회
 *
 *	/buyer/P10101/form (GET) : 제조사 수정 양식
 *	/buyer/P10101 (PUT) : 제조사 한건 정보 수정
 *
 *	/buyer/P10101 (DELETE) : 제조사 삭제
 *	
 *
 */
@Controller
@RequestMapping("/buyer")
public class BuyerRetrieveController {
	
	@Inject
	private BuyerService service;
	
	@Inject
	private OthersDAO othersdao;
	
	@ModelAttribute("lprodList")  // = model.addAttribute(model);
	public List<LprodVO> lprodList(){
		return othersdao.selectLprodList();
	}
	
	// 여기페이징 검색 하기 
	@GetMapping
	public String buyerListResful(
			@ModelAttribute("detailCondition")BuyerVO detailCondition
			,@RequestParam(value = "page", required =  false, defaultValue = "1") int currentPage
			,Model model
			) {
		
		PaginationInfo<BuyerVO> paging = new PaginationInfo<>(5, 2);
		paging.setCurrentPage(currentPage);
		paging.setDetailCondition(detailCondition); // 검색 상세조건
		
		// 검색 조건, 페이징 완성
		paging.setRenderer(new BootstrapPaginationRender());

		service.retrieveBuyerList(paging);


		model.addAttribute("paging", paging);
		
		
		
		return "buyer/buyerList";
		
		//return "buyer/buyerList";
	}
	
	// 요청 파라미터를 경로변수로 받기
	// required 생략시 true 반드시 있어야함
	// 리턴타입은 String
	// '/'는 스프링이 알아서 생성해준다 생략가능
	@GetMapping("{buyerId}")
	public String  buyerViewRestful(@PathVariable String buyerId, Model model ) {
		BuyerVO buyer = service.retrieveBuyer(buyerId);
		model.addAttribute("buyer",buyer);
		return "buyer/buyerView";
	
	}
	//@GetMapping("/buyer/buyerView.do")
	
//	RequestToViewNameTranslator 전략 객체 동작
//void로 쓸수 있긴함(권장하진 않음) -> 어댑터는 request uri로 찾음 @GetMapping("/buyer/buyerView.do")
//	public String buyerView(
//			@RequestParam String what
//			,Model model
//			
//			) {
//	public void buyerView(
//				@RequestParam String what
//				,Model model
//				
//				) {
	public ModelAndView buyerView(@RequestParam String what) {
		BuyerVO buyer = service.retrieveBuyer(what);
//		model.addAttribute("buyer", buyer);
//		return "buyer/buyerView";
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("buyer", buyer);
		mav.setViewName("buyer/buyerView");
		return mav;
	// 권장하진 않음..
	}
}
