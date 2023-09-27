package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.TilesViewResolver;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberInsert.do")
public class MemberInsertControllerServlet extends HttpServlet {

	
	private MemberService service = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ui

		String viewName = "member/memberInsert";
		new ViewResolverComposite().resolveView(viewName, req, resp);
		
		}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 회원가입

		// 1. 디코딩 설정
		req.setCharacterEncoding("utf-8");
		// 2. 파라미터 확보 --> MemberVO
		MemberVO member = new MemberVO(); //기존 입력데이터
		req.setAttribute("member", member);
		Map<String, String[]> paramterMap = req.getParameterMap();
//		String memId = req.getParameter("memId");
//		member.setMemId(memId);
		
		//중복코드 해결하기 위한 
		PopulateUtils.populate(member, paramterMap);

		Map<String, List<String>> errors = new HashMap<>(); //에러메세지
		req.setAttribute("errors", errors);
		// 3. 검증 (대상:MemberVO)
		boolean valid = ValidationUtils.validate(member, errors, InsertGroup.class); //검증 별도의 유틸리티로
		
		String viewName = null;

		if(valid) {
			// 검증 통과
			// 4.createMember 등록 처리
			ServiceResult result = service.CreateMember(member);
			switch (result) {
			case PKDUPLICATED:
				// 1)PKDUPLICATED (PK중복) -->
//					memberInesrt으로 이동(기존 입력 데이터, 메시지, dispatch)
				req.setAttribute("message", "아이디 중복");
				viewName = "member/memberInsert";

				break;
			case OK:
				// 2) OK
//					welcome page로 이동 (redirect)
				viewName ="redirect:/";
				
				break;

			default:
				// 3) FAIL
//					memberInesrt으로 이동(기존 입력 데이터, 메시지, dispatch)		
				req.setAttribute("message", "서버오류, 조금 이따 다시 해보세요");
				viewName = "member/memberInsert";
				break;
			}
			
			
		}else{
			// 검증 불통
//			memberInesrt으로 이동(기존 입력 데이터, 검증의 결과 메시지들..., dispatch)	
			viewName = "member/memberInsert";
			
		}
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}

	
}