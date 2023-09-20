package kr.or.ddit.login.service;

import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO2;

public class AthenticateServiceImpl implements AuthenticateService {
	private MemberDAO memberDAO = new MemberDAOImpl();

	@Override
	public boolean authenticate(MemberVO2 inputData) {
	MemberVO2 saved = memberDAO.selectMemberForAuth(inputData);
		return saved!=null;
	}

}
