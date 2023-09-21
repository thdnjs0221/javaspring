package kr.or.ddit.member.dao;

import kr.or.ddit.vo.MemberVO;

/**
 * 사용자 인증정보와 회원정보를 관리하기 위한 Persistence Layer
 *
 */
public interface MemberDAO {

	/**
	 * 
	 * id/ pass 를 기반으로 사용자의 기본 정보를 조회 
	 * @param inputData 검색 조건으로 사용할 id/ pass
	 * @return 검색 결과 객체로 존재하지 않은경우 , null반환
	 */
	public MemberVO selectMemberForAuth(MemberVO inputData);

}
