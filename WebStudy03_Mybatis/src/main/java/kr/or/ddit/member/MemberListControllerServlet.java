package kr.or.ddit.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.adrs.service.AddressService;
import kr.or.ddit.adrs.service.AddressServiceImpl;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

/**
 * 목록조회  member/memberList.do
 * 마이페이지 /mypage
 * 정보 수정 /member/memberUpdate.do
 * 탈퇴 /member/memberDelete.do
 * 가입처리  /member/memberInsert.do
 * 
 * 
 * 
 * */
@WebServlet("/member/memberList.do")
public class MemberListControllerServlet extends HttpServlet{
	 private MemberService service = new MemberServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		

		List<MemberVO>list = service.retrieveMemberList();
		
		req.setAttribute("list", list);
		
		
		
		//뷰이동
		String goPage = "/WEB-INF/views/member/memberList.jsp";

	    if (goPage.startsWith("redirect:")) {
	       String location = req.getContextPath() + goPage.substring("redirect:".length());
	       resp.sendRedirect(location);
	    } else {
	       req.getRequestDispatcher(goPage).forward(req, resp);
	    }

	}
	
	
}
