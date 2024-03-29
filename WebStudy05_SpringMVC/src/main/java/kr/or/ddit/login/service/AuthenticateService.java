package kr.or.ddit.login.service;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;


/**
 * 사용자 인증을 담당하는 비즈니스 로직 레이어
 *
 */
public interface AuthenticateService {
	
	
	
	/** 사용자 인증
	 * @param inputData
	 * @return NOTEXIST, INVALIDPASSWORD ,OK
	 */
	public ServiceResult authenticate(MemberVO inputData);

}
