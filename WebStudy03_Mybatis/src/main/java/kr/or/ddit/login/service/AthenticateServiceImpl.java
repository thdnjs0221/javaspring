package kr.or.ddit.login.service;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class AthenticateServiceImpl implements AuthenticateService {
	private MemberDAO memberDAO = new MemberDAOImpl();

	@Override
	public ServiceResult authenticate(MemberVO inputData) {
		//있거나 없거나
	MemberVO saved = memberDAO.selectMemberForAuth(inputData);
	ServiceResult result =  null;
	if(saved!=null) {
		//회원이 있는 경우
		//비번 인증 
		String inputPass = inputData.getMemPass();
		String savedPass = saved.getMemPass();
		if(savedPass.equals(inputPass)) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
	}else {
		//회원이 없는 경우
		result = ServiceResult.NOTEXIST;
		
	}
		return result;
	}

}
