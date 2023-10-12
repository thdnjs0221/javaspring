package kr.or.ddit.mvc.annotation.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//어디에 어노테이션을 사용할지 생각하기 (@Target) (클래스(Type) ? 메소드? 어노테이션? )
//어떤 대상에게 보여줄지 ? (@Retention)

@Target(ElementType.TYPE)  //클래스에만 사용
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {

}
