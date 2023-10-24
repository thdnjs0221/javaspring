package kr.or.ddit.login.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class AthenticateServiceImpl implements AuthenticateService {
	private final MemberDAO memberDAO;;

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
			try {
				BeanUtils.copyProperties(inputData, saved);
				result = ServiceResult.OK;
				
			} catch (IllegalAccessException | InvocationTargetException e) {
				//membervo 잘못만들어짐(로그인 불가)
				throw new RuntimeException(e);
			}
			
			
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
