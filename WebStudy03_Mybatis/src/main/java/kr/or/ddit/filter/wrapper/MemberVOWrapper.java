package kr.or.ddit.filter.wrapper;

import java.security.Principal;

import kr.or.ddit.vo.MemberVO;

//어댑터는 기본생성자가 있으면 안된다.
// MemberVO가지면서 Principal 구현
public class MemberVOWrapper implements Principal{
	private MemberVO adaptee;

	public MemberVOWrapper(MemberVO adaptee) {
		super();
		this.adaptee = adaptee;
	}

	@Override
	public String getName() {
		// getName -> 식별자	
		return adaptee.getMemId();
	}
	public MemberVO getRealUser() {
		return adaptee;
	}

}
