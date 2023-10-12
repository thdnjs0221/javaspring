package kr.or.ddit.mvc.annotation.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import kr.or.ddit.mvc.annotation.RequestMethod;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
	String value();  //annotaion에서 선언하는 방식 , 특정 커맨드 핸들러 메소드가 처리할 수 있는 요청의 URI
	RequestMethod method() default RequestMethod.GET;  //생략이 가능한 구조,특정 커맨드 핸들러 메소드가 처리할 수 있는 요청의 method
	
}
