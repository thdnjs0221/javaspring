package kr.or.ddit.member.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.login.service.AthenticateServiceImpl;
import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.member.UserNotFoundException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
	
	@Value("#{appInfo.memImagesUrl}")
	private String memImagesUrl;

	@Value("#{appInfo.memImagesUrl}")
	private Resource memImages;

	private File saveFolder;

	@PostConstruct // 생성자 이후에 실행
	public void init() throws IOException {
		saveFolder = memImages.getFile();
	}
	
	

	private final MemberDAO dao;
	
	@Inject
	private AuthenticateService authService;
	
//	private void processMemImage(MemberVO member)  {
//		member.saveTo(saveFolder);  //상품이미지 저장	
//}

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
		MemberVO inputData = new MemberVO();
		inputData.setMemId(member.getMemId());
		inputData.setMemPass(member.getMemPass());
		
		ServiceResult authenticated = authService.authenticate(inputData);
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