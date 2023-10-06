package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

//@WebFilter("/*") //필터는 @WebFilter 안쓰는게 좋음 체인 내에서 순서를 제한을 못하니까..web.xml에 등록하기
@Slf4j
public class CharacterEncodingFilter implements Filter{
	private String encoding;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("{} 필터 초기화",this.getClass().getSimpleName());
		encoding = filterConfig.getInitParameter("encoding");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		req.setCharacterEncoding(encoding);
		log.info("{} 요청 필터링",req.getRequestURI());
		chain.doFilter(request, response);  // 필터에서 꼭 필요한 코드! 이 코드위에서 하면 컨트롤러보다 먼저 실행
		log.info("{} 응답 필터링",resp.getContentType());
		
	}

	@Override
	public void destroy() {
		log.info("{} 필터 소멸",this.getClass().getSimpleName());
		
	}

	

}
