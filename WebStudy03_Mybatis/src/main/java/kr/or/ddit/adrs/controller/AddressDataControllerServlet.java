package kr.or.ddit.adrs.controller;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.adrs.service.AddressService;
import kr.or.ddit.adrs.service.AddressServiceImpl;
import kr.or.ddit.vo.AddressVO;

@WebServlet({ "/adrs/address", "/adrs/address/*" }) // 주소록 하나만을 표현 -> crud 중 어떤걸 할지는 메소드로
public class AddressDataControllerServlet extends HttpServlet {
   private AddressService service = new AddressServiceImpl();

   // do계열의 메소드로 crud 처리
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String uri = StringUtils.substringAfter(req.getRequestURI(), req.getContextPath());
      //substringAfter메서드로 요청의URI에서 req.getRequestURI()부분 제외한 부분 추출
      System.out.println(uri);
      int lastIdx = uri.lastIndexOf("/");  //마지막 / 인덱스
      int uriLen = uri.length();
      int baseLen = "/adrs/address".length();
      boolean valid = lastIdx > baseLen && lastIdx < uriLen - 1; // 크거나 같으면 검증 통과 , lastIdx가 마지막 글자가 되서는 안됨
      System.out.printf("%s : %b\n", uri, valid);

      req.setCharacterEncoding("Utf-8");

      Principal principal =  req.getUserPrincipal();
	
	 String memId = principal.getName(); //userid 존재

      
      List<AddressVO> adrsList = service.retriveAddressList(memId);

      req.setAttribute("adrsList", adrsList);

      
      //뷰 이동
      String goPage = "/jsonView.view";

      if (goPage.startsWith("redirect:")) {
         String location = req.getContextPath() + goPage.substring("redirect:".length());
         resp.sendRedirect(location);
      } else {
         req.getRequestDispatcher(goPage).forward(req, resp);
      }
   }

   
   //ObjectMapper : json 직렬화, 역직렬화
   private ObjectMapper mapper = new ObjectMapper();

   private boolean validate(AddressVO vo, Map<String, String> errors) {
      boolean valid = true;
      if (StringUtils.isBlank(vo.getAdrsName())) { // 이름(필수파라미터1) 누락된 경우  StringUtils.isBlank: 공백 혹은 null
         valid = false;
         errors.put("adrsName", "이름 누락");
      }
      if (StringUtils.isBlank(vo.getAdrsHp())) { // 연락처(필수파라미터2) 누락된 경우
         errors.put("adrsHp", "휴대폰 번호 누락");
         valid = false;
      }
      return valid;

   }
   
   

   //추가
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.setCharacterEncoding("UTF-8");

      // 조건 : json parameter로 데이터 받기

      // json 객체로 받아온 데이터를 언마샬링 해야함 -> objectMapper
      try (
            InputStream is = req.getInputStream();
      ) {
         AddressVO vo = mapper.readValue(is, AddressVO.class);  
         //json데이터를 언마샬링하기 json->java
         
         req.setAttribute("originalData", vo);
         String authId = (String) req.getSession().getAttribute("authId");
         vo.setMemId(authId);
         
         Map<String, String> errors = new HashMap<>();
         req.setAttribute("errors", errors); // 상황에 따라 엔트리가 있거나 없거나
         
         // 검증과정
         boolean valid = validate(vo, errors);
         
         // 성공여부
         boolean success = false;
         String message = null;
         if (valid) { // 검증통과
            if (service.createAddress(vo)) {
               success = true;
            } else {
               message = "등록실패";
            }
         } else { // 검증실패
            message = "필수 파라미터 누락";
         }

         req.setAttribute("success", success);
         req.setAttribute("message", message);
      }

      String goPage = "/jsonView.view";

      if (goPage.startsWith("redirect:")) {
         String location = req.getContextPath() + goPage.substring("redirect:".length());
         resp.sendRedirect(location);
      } else {
         req.getRequestDispatcher(goPage).forward(req, resp);
      }
   }

   @Override
   protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String uri = StringUtils.substringAfter(req.getRequestURI(), req.getContextPath());
      int lastIdx = uri.lastIndexOf("/");
      int uriLen = uri.length();
      int baseLen = "/adrs/address".length();
      boolean valid = lastIdx >= baseLen && lastIdx < uriLen - 1;
      String adrsnoPart = uri.substring(lastIdx + 1); // /이후의 것들

//         if(valid) {
//          
//            adrsnoPart = uri.substring(lastIdx+1);  // /이후의 것들
//            valid = StringUtils.isNumeric(adrsnoPart);
//         }
      int adrsNo = 1;
      try {
         adrsNo = Integer.parseInt(adrsnoPart);
      } catch (NumberFormatException e) {
         valid = false;
      }
      if (!valid) {
         resp.sendError(400, "주소록 번호가 없음");
         return;
      }
      boolean success = service.removeAddress(adrsNo);
      req.setAttribute("success", success);
      if(!success) {
         req.setAttribute("message", "삭제 실패");
      }
      
      if (service.removeAddress(adrsNo)) {

      }
      
      String goPage = "/jsonView.view";

      if (goPage.startsWith("redirect:")) {
         String location = req.getContextPath() + goPage.substring("redirect:".length());
         resp.sendRedirect(location);
      } else {
         req.getRequestDispatcher(goPage).forward(req, resp);
      }
      
   }
}