package kr.or.ddit.paging.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.ui.BootstrapPaginationRender;
import kr.or.ddit.uiplugin.dao.ProdDAO;
import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/paging")
public class PagingSampleController {
	@Inject
	private ProdDAO prodDAO;
	
	@RequestMapping("simple")
	public String simpleView() {
		return "paging/simpleView";
	}
	
	@RequestMapping("spa")
	public String spaView() {
		return "paging/spaView";
	}
	
	@RequestMapping(value= {"simple", "spa"}, produces=MediaType.APPLICATION_JSON_VALUE)
	public String jsonData(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, ProdVO detailCondition, Model model
	) {
		PaginationInfo<ProdVO> pagingVO = new PaginationInfo<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailCondition(detailCondition);
		int totalRecord = prodDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<ProdVO> prodList = prodDAO.selectProdList(pagingVO);
		pagingVO.setDataList(prodList);
		
		BootstrapPaginationRender renderer = new BootstrapPaginationRender();
		String pagingHTML = renderer.renderPagination(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		model.addAttribute("pagingHTML", pagingHTML);
		
		return "jsonView";
	}
}

















