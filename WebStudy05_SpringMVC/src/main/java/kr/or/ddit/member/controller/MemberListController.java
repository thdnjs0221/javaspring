package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.adrs.service.AddressService;
import kr.or.ddit.adrs.service.AddressServiceImpl;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.paging.BootstrapPaginationRender;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.SearchVO;

/**
 * 목록조회  member/memberList.do
 * 마이페이지 /mypage
 * 정보 수정 /member/memberUpdate.do
 * 탈퇴 /member/memberDelete.do
 * 가입처리  /member/memberInsert.do
 * 
 * 상세조회: /member/memebrView.do?who=a001
 * 
 * */
//@WebServlet("/member/memberList.do")
@Controller
public class MemberListController {
	
	@Inject
	 private MemberService service;
	
	@RequestMapping( value = "/member/memberList.do", method = RequestMethod.GET)
	public String list(
			  @ModelAttribute("simpleCondition") SearchVO simpleCondition
			, @RequestParam(value = "page", required = false, defaultValue = "1") int currentPage							
			, @RequestParam(value = "searchType", required = false)String searchType						
			, @RequestParam(value = "searchWord", required = false)String searchWord						
			, Model model)  {
		
		//검증의 대상이 아님 / searchForm에서 온 3개의 파라미터 
	
		
		
		PaginationInfo<MemberVO> paging = new PaginationInfo<>(5,2);  //PaginationInfo<MemberVO>니까  detailCondition MemberVO 
		paging.setSimpleCondition(simpleCondition);  //키워드 검색 조건
		paging.setCurrentPage(currentPage);
		//검색 조건, 페이징 완성
		paging.setRenderer(new BootstrapPaginationRender());
		
		service.retrieveMemberList(paging);
		model.addAttribute("paging", paging);
		
		//뷰이동
		return "member/memberList";
	

	}
	
	
}
