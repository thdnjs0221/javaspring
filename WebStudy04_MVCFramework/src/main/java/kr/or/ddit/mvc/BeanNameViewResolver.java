package kr.or.ddit.mvc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.common.view.JsonView;

public class BeanNameViewResolver implements ViewResolver {

	private Map<String, View> container;
	{
		
		container = new HashMap<>();
		container.put("jsonView", new JsonView());
	}
	
	//ex) jsonView	
	@Override
	public void resolveView(String viewName, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		//
		if(container.containsKey(viewName)) {
			View view = container.get(viewName);
			try {
				view.render(req, resp);
			} catch (Exception e) {
				throw new ServletException();
			}
			
		}else {
			//json view아닐 경우(xml) , tiles에게 넘겨줄 경우 
			throw new IOException(String.format("%s 에 해당하는 view 를 찾지 못했음", viewName));
			
		}
		
	}

}
