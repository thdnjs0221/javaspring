package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.login.service.AthenticateServiceImpl;
import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.member.UserNotFoundException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PaginationInfo;

public class MemberServiceImpl implements MemberService {

	private MemberDAO dao = new MemberDAOImpl();
	private AuthenticateService authService = new AthenticateServiceImpl();

	@Override
	public ServiceResult CreateMember(MemberVO member) {
		ServiceResult result = null;
		// db에서 아이디 조회 (중복)
		if (dao.selectMember(member.getMemId()) == null) {
			// 가입 o
			int rowcnt = dao.insertMember(member);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		} else {
			// 아이디 중복 pk중복
			result = ServiceResult.PKDUPLICATED;

		}
		return result;
	}

	@Override
	public MemberVO retrieveMember(String memId) {

		MemberVO member = dao.selectMember(memId);
		if (member == null)
			throw new UserNotFoundException(memId);
		return member;

	}

	@Override
	public List<MemberVO> retrieveMemberList(PaginationInfo paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord); //페이지를 결정하는 
		List<MemberVO> dataList =dao.selectMemberList(paging);
		paging.setDataList(dataList); //페이지를 결정하는 
		return dataList;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		ServiceResult authenticated = authService.authenticate(member);
		ServiceResult result = null;
		if (authenticated==ServiceResult.OK) {
			int rowcnt = dao.updateMember(member);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		} else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	// 삭제
	@Override
	public ServiceResult removeMember(MemberVO member) {
		ServiceResult result = authService.authenticate(member);
		if (result==ServiceResult.OK) {
			int rowcnt = dao.deleteMember(member.getMemId());
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		} else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

}