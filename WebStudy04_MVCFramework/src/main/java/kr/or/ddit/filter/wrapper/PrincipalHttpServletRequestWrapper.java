package kr.or.ddit.filter.wrapper;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import kr.or.ddit.vo.MemberVO;

public class PrincipalHttpServletRequestWrapper extends  HttpServletRequestWrapper{
	
	
	private HttpServletRequest request;
	
//	HttpServletRequest 원본요청이면서 adaptee
	public PrincipalHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
		
	}

	@Override
	public Principal getUserPrincipal() {	
		MemberVO authMember =(MemberVO)getSession().getAttribute("authMember");
		if(authMember!=null) {
			MemberVOWrapper principal = new MemberVOWrapper(authMember);  
			//현재 로그인된principal 만들어짐
			return principal;
		}else {
			return super.getUserPrincipal();
		}
		
		
	}

}
