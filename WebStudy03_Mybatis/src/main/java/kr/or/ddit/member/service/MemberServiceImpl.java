package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.login.service.AthenticateServiceImpl;
import kr.or.ddit.login.service.AuthenticateService;

import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements MemberService{
   
   private MemberDAO dao = new MemberDAOImpl();
   private AuthenticateService authService = new AthenticateServiceImpl();

 
   @Override
   public ServiceResult CreateMember(MemberVO member) {
   	// TODO Auto-generated method stub
   	return null;
   }

   @Override
   public MemberVO retrieveMember(String memId) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public List<MemberVO> retrieveMemberList() {
		return dao.selectMemberList();
   }

   @Override
   public ServiceResult modifyMember(MemberVO member) {
      boolean authenticated = authService.authenticate(member);
      ServiceResult result = null;
      if(authenticated) {
         int rowcnt = dao.updateMember(member);
         result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
      }else {
         result = ServiceResult.INVALIDPASSWORD;
      }
      return null;
   }

   @Override
   public ServiceResult removeMember(MemberVO member) {
      // TODO Auto-generated method stub
      return null;
   }



}