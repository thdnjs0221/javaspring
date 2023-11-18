package kr.or.ddit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException.Gone;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.service.IBoardService;
import kr.or.ddit.vo.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardController {
   
   @Inject
   private IBoardService service; //의존성 주입을 통한 service 객체 사용
   
   @RequestMapping(value="/list.do", method = RequestMethod.GET)
   public String boardList(Model model) {
	   List<BoardVO> boardList =service.selectBoardList();
	  
	   model.addAttribute("boardList",boardList);
      return "board/list";
   }
   
   @RequestMapping(value="/insert.do", method = RequestMethod.POST)
   public String insertBoart(BoardVO boardVO, Model model) {
      String goPage="";
      Map<String, String> errors = new HashMap<String, String>();
      if(StringUtils.isBlank(boardVO.getBoTitle())) {
         errors.put("boTitle", "제목을 입력해주세요.");
      }
      if(StringUtils.isBlank(boardVO.getBoWriter())) {
         errors.put("boWirter", "작성자을 입력해주세요.");
      }
      if(StringUtils.isBlank(boardVO.getBoContent())) {
         errors.put("boContent", "내용을 입력해주세요.");
      }
      
      if(errors.size()>0) { //에러 발생
         model.addAttribute("board", boardVO);
         model.addAttribute("errors", errors);
         goPage = "board/form";
      }else { // 정상적인 데이터 넘어옴
         // 게시글 등록
         ServiceResult result =service.insertBoard(boardVO);
         if(result.equals(ServiceResult.OK)) {
        	 goPage = "redirect:/board/detail.do?boNo="+boardVO.getBoNo();
         }else {
        	 model.addAttribute("message","서버에러 다시시도해주세요");
        	 goPage="board/form";
         }
      }
      return goPage;
   }
   
   @RequestMapping(value = "/form.do", method = RequestMethod.GET)
   public String boradForm() {
      return "board/form";
   }
   
   @RequestMapping(value="/detail.do", method = RequestMethod.GET)
   public String boardDetail(int boNo, Model model) {
	   BoardVO boardVO=  service.selectBoard(boNo);
	   model.addAttribute("board",boardVO);
      return "board/detail";
   }
   @RequestMapping(value = "/update.do", method = RequestMethod.GET)
   public String boardUpdateForm(int boNo, Model model) {
	  BoardVO boardVO = service.selectBoard(boNo);
	  model.addAttribute("board",boardVO);
	  model.addAttribute("status","u");
	  return "board/form";
	  
	   
   }
   
   @RequestMapping (value = "/update.do", method = RequestMethod.POST)
   public String boardUpdate(BoardVO boardVO, Model model) {
	   String goPage="";
	      Map<String, String> errors = new HashMap<String, String>();
	      if(StringUtils.isBlank(boardVO.getBoTitle())) {
	         errors.put("boTitle", "제목을 입력해주세요.");
	      }
	      if(StringUtils.isBlank(boardVO.getBoWriter())) {
	         errors.put("boWirter", "작성자을 입력해주세요.");
	      }
	      if(StringUtils.isBlank(boardVO.getBoContent())) {
	         errors.put("boContent", "내용을 입력해주세요.");
	      }
	      if(errors.size()>0) { //에러 발생
	          model.addAttribute("board", boardVO);
	          model.addAttribute("errors", errors);
	          model.addAttribute("status", "u");
	          goPage = "board/form";
	      }else {
	    	  ServiceResult result=service.updateBoard(boardVO);
	    	  if(result.equals(ServiceResult.OK)) {
	    		  goPage="redirect:/board/detail.do?boNo="+boardVO.getBoNo();
	    	  }else {
	    		  model.addAttribute("message", "수정실패");
	    		  model.addAttribute("board", boardVO);
	    		  model.addAttribute("status", "u");
		          goPage="board/form";
	    	  }
	      }
	   return goPage;
   }
   @RequestMapping (value = "/delete.do", method = RequestMethod.POST)
   public String deleteBoard(int boNo,Model model) {
	   String goPage="";
	   ServiceResult result = service.deleteBoard(boNo);
	   if(result.equals(ServiceResult.OK)) {
		   goPage="redirect:/board/list.do";
	   }else {
		   goPage="redirect:/board/detail.do?boNo="+boNo;
	   }
	   return goPage; 
   }
}