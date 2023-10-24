package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.member.UserNotFoundException;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PaginationInfo;

/**
 * 회원 정보 관리를 위한 Business Logic Layer
 *
 */
public interface MemberService {
	/**
	 * 가입 처리
	 * @param member
	 * @return PKDUPLICATED, OK, FAIL
	 */
	public ServiceResult createMember(MemberVO member);
	/**
	 * 마이 페이지용
	 * @param memId
	 * @return 존재하지 않는 경우, {@link UserNotFoundException} 발생
	 */
	public MemberVO retrieveMember(String memId);
	/**
	 * 관리자 용도로 사용될 회원 목록 조회
	 * @param paging TODO
	 * @return
	 */
	public List<MemberVO> retrieveMemberList(PaginationInfo paging);
	/**
	 * 마이페이지에서부터 자기 정보를 수정할때 사용.
	 * @param member
	 * @return INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult modifyMember(MemberVO member);
	/**
	 * 탈퇴 처리
	 * @param member
	 * @return  INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult removeMember(MemberVO member);
}

















