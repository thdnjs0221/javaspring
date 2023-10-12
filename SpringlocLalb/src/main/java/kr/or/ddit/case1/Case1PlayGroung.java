package kr.or.ddit.case1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.case1.presentation.Case1SamplePresentaion;

//전략의 주입자(모든 결합력의 책임)
public class Case1PlayGroung {
	public static void main(String[] args) {
		
		
		ApplicationContext context =
				new ClassPathXmlApplicationContext("kr/or/ddit/case1/conf/Case1-Context.xml");
		
		Case1SamplePresentaion presentaion  = context.getBean(Case1SamplePresentaion.class);
		
		String pk = "a001";
		String content = presentaion.makeContent(pk);
		
		System.out.println(content);
		
	}
}
 