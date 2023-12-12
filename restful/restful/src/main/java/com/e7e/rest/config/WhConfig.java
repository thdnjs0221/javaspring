package com.e7e.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class WhConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.debug("요기가 실행되었는지 check?");
		// 의미만 파악하면 됨!, 설정은 시간이 지나면 자꾸만 더 편해지니까 외울필요는 없음
		registry.addResourceHandler("/upload/**")             // 웹 접근 경로 
		        .addResourceLocations("file:///d:/upload/");  // 서버내 실제 경로
	}
}
