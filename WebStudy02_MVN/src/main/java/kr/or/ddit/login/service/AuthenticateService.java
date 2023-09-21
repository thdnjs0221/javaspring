package kr.or.ddit.login.service;

import kr.or.ddit.vo.MemberVO;


/**
 * 사용자 인증을 담당하는 비즈니스 로직 레이어
 *
 */
public interface AuthenticateService {
	public boolean authenticate(MemberVO inputData);

}
