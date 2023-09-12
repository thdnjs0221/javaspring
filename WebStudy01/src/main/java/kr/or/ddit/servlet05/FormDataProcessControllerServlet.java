package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/09/model2/formDataProcess")
public class FormDataProcessControllerServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//컨트롤러의 역할
		req.setCharacterEncoding("UTF-8");
		String reqContentType = req.getContentType();
			// 클라이언트가 서버로 전송한 데이터가 파라미터로 넘어오는지 json으로 넘어오는지 확인
		Map reqContent = null;
		if(reqContentType.contains("json")) {
			// json payload가 전송됐을 때 -> 역직렬화와 마셜링 필요
			// request의 body에서 읽을 inputStream이 필요
			InputStream is = req.getInputStream();		//역직렬화 할 때 사용할 수 있는 입력 스트림
				// 역직렬화 통해 byte데이터 읽어들임->읽어들인 json데이터를 Java언어로 번역 : 마셜링
			reqContent = new ObjectMapper().readValue(is, HashMap.class);
							// 클라이언트가 서버에게 쓴 편지 - 역직렬화 + 언마셜링까지 해야함 => read
			//Jackson 라이브러리의 ObjectMapper를 사용하여 입력 스트림에서 JSON 데이터를 읽어와서 자바 객체로 변환합니다. 
			//이 과정은 JSON 데이터의 역직렬화(deserialization)에 해당하며, 자바 객체로 변환된 데이터를 reqContent 맵에 저장합니다.
		}else {
			// 데이터를 파라미터로 전송일때
			reqContent = req.getParameterMap();  //Map<String,String[]> map1 = request.getParameterMap();
			reqContent.forEach((k,v)->{
				System.out.printf("%s : %s\n", k, Arrays.toString((String[])v));
			});
		}
		
		//모델의 역할
		//Map<String, Object> target = new HashMap<String, Object>();
//		target.put("message", "파라미터 처리 완료");
//		target.putAll(reqContent);
		req.setAttribute("message", "파라미터 처리 완료");
		req.setAttribute("reqContent", reqContent);

		// 응답데이터를 어떤 형태로 보내야하는지 확인
		String accept = req.getHeader("Accept");
		
		int sc = 200;
		//뷰의 역할 - 뷰3개로 나누기 만들어내는 컨텐트 타입에 따라서
		// information -> content 로 바꿈 -> serialization:직렬화(응답으로 내보냄)
		String viewName = null;
		if(accept.contains("json")) {
			viewName= "/jsonView.view";
			// 1. Map에 있는 데이터를 Json으로 바꿈 -> 마셜링
			// 2. 네트워크를 통해 가려면 010101~~~(스트림)로 바뀌어야함 -> 직렬화
			// 3. 다시 Json/xml ->역직렬화
			// 4. 다시 자바 형태로 변환 -> 언마셜링
		}else if(accept.contains("xml")) {
			sc = HttpServletResponse.SC_NOT_ACCEPTABLE;
		}else {
			viewName ="/WEB-INF/views/09/fromDataView.jsp";
		}
		if(sc==200) {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}else {
			resp.sendError(sc);
		}
	
		
	}
}