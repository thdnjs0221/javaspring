package kr.or.ddit.controller.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	

	@Inject  //의존성 주입
	private INoticeService service;
	
	
	@RequestMapping(value = "/list.do")
	public String notice (
			
			@RequestParam(name = "page", required = false, defaultValue = "1")int currentPage
			,@RequestParam(required = false, defaultValue = "title")String searchType
			,@RequestParam(required = false)String searchWord
			,Model model){
		
		PaginationInfoVO<NoticeVO>pagingVO = new PaginationInfoVO<NoticeVO>();
		
		//검색시 활용 
		
		//검색시 활용 end
		//startrow endrow startPage endpage  결정됨
		pagingVO.setCurrentPage(currentPage);
		
		int totalRecord = service.selectNoticeCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<NoticeVO>dataList = service.selectNoticeList(pagingVO);
		pagingVO.setDataList(dataList); //게시글 목록 정보 있음
		
		model.addAttribute("pagingVO",pagingVO);
		
		
		// (방법 1)일반적인 목록 가져오기
//		 List<NoticeVO>noticeList = service.selectNoticeList();
//		 model.addAttribute("noticeList",noticeList);
		//  (방법 1) end
		return "notice/list";
	}
	
	@RequestMapping(value = "/form.do" , method = RequestMethod.GET)
	public String noticeForm() {
		return "notice/form";
		
	}
	
	@RequestMapping(value = "/insert.do" , method = RequestMethod.POST)
	public String noticeInsert(NoticeVO noticeVO, Model model) {
		
		String goPage ="";
		Map<String , String>errors = new HashMap<String, String>();
		if(StringUtils.isBlank(noticeVO.getBoTitle())) {
			errors.put("boTitle", "제목을 입력해주세요");
		}
		if(StringUtils.isBlank(noticeVO.getBoContent())) {
			errors.put("boContent", "내용을 입력해주세요");
		}
		if(errors.size()>0) {
			model.addAttribute("errors",errors);
			model.addAttribute("notice",noticeVO);
			goPage="notice/form";
		}else {
			noticeVO.setBoWriter("a001");
			ServiceResult result =service.insertNotice(noticeVO);
			if(result.equals(ServiceResult.OK)) {
				goPage="redirect:/notice/detail.do?boNo="+noticeVO.getBoNo();
			}else {
				model.addAttribute("message","서버에러 , 다시 시도해주세요");
				goPage="notice/form";
			}
		}
		return goPage;
		
	}
	
	@RequestMapping(value = "/detail.do" , method = RequestMethod.GET)
	public String noticeDetail(int boNo , Model model) {
		NoticeVO noticeVO =service.selectNotice(boNo);
		model.addAttribute("notice",noticeVO);
		return "notice/detail";
		
	}
}
