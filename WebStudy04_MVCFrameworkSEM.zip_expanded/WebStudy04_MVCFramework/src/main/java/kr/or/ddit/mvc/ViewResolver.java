package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 컨트롤러에서 view layer 를 선택할때 사용할 수 있는 전략.
 *
 */
public interface ViewResolver {
	public default void setPrefix(String prefix) {}
	public default void setSuffix(String suffix) {}
	
	public void resolveView(String viewName, HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
	;
}
