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
import javax.servlet.annotation.MultipartConfig;
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
import kr.or.ddit.file.utils.MultipartFile;
import kr.or.ddit.filter.utils.StandardMultipartHttpServletRequest;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.TilesViewResolver;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.vo.MemberVO;

//@MultipartConfig
//@WebServlet("/member/memberInsert.do")
@Controller
public class MemberInsertController {

	
	private MemberService service = new MemberServiceImpl();

	@RequestMapping("/member/memberInsert.do")
	public String doGet(){
		// ui
		return "member/memberInsert";

		}

	@RequestMapping(value = "/member/memberInsert.do", method = RequestMethod.POST)
	public String doPost(
			@ModelAttribute("member")MemberVO member
			,HttpServletRequest req) throws IOException {

		// 2. 파라미터 확보 --> MemberVO
//		MemberVO member = new MemberVO(); //기존 입력데이터
//		req.setAttribute("member", member);
//		Map<String, String[]> paramterMap = req.getParameterMap();
//		String memId = req.getParameter("memId");
//		member.setMemId(memId);
		
		//중복코드 해결하기 위한 
		//PopulateUtils.populate(member, paramterMap);
		
		if(req instanceof StandardMultipartHttpServletRequest) {
			MultipartFile memImage = ((StandardMultipartHttpServletRequest) req).getFile("memImage");
			if(memImage!=null && !memImage.isEmpty()) {
				member.setMemImg(memImage.getBytes());  //이미지 업로드
			}
		}

		Map<String, List<String>> errors = new HashMap<>(); //에러메세지
		req.setAttribute("errors", errors);	
		String viewName = null;

	
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

	return viewName;
	}

	
}
