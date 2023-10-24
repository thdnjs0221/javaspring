package kr.or.ddit.common.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 어노테이션 만들때 필요한것
// target
// Retention 언제까지?
@Target(ElementType.TYPE)  //클래스 
@Retention(RetentionPolicy.RUNTIME)
public @interface Preparer {

}
