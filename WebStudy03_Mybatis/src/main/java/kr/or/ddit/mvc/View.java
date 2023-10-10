package kr.or.ddit.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//json servlet 대체
public interface View {
	public void render(HttpServletRequest req, HttpServletResponse resp) throws Exception;

	
}
