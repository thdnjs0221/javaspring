package kr.or.ddit.filter.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.basic.BasicTreeUI.SelectionModelPropertyChangeHandler;

/**
 * multipart request를 {@link StandardMultipartHttpServletRequest}로 wrapping
 *
 */
public class MultipartFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String contentType = req.getContentType();
		
		if (contentType != null && contentType.startsWith("multipart/")) {
			StandardMultipartHttpServletRequest wrapperReq = 
					new StandardMultipartHttpServletRequest(req); // 기본생성자 없으니
			chain.doFilter(wrapperReq, response);
			
		} else {
			chain.doFilter(request, response); // 원본요청 그대로 넘기기
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
