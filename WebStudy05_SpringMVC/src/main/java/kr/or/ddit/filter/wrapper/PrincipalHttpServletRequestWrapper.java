package kr.or.ddit.filter.wrapper;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import kr.or.ddit.vo.MemberVO;

public class PrincipalHttpServletRequestWrapper extends HttpServletRequestWrapper{

   private HttpServletRequest request;
   
   public PrincipalHttpServletRequestWrapper(HttpServletRequest request) {
      super(request);
      this.request = request;
   }

   @Override
   public Principal getUserPrincipal() {
      HttpSession session = request.getSession(false);
      MemberVO authMember = null;
      if(session!=null && !session.isNew()) {
         authMember  = (MemberVO) session.getAttribute("authMember");         
      }
      if(authMember!=null) {
         MemberVOWrapper principal = new MemberVOWrapper(authMember);
         return principal;
      }else {
         return super.getUserPrincipal();
      }
   }
   
}