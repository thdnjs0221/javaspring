package kr.or.ddit.case1;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.case1.presentation.Case1SamplePresentaion;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContainerDesc {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
				new GenericXmlApplicationContext("classpath:kr/or/ddit/case1/conf/Container-Desc.xml");
		context.registerShutdownHook();
		
//		Case1SamplePresentaion presentaion = context.getBean(Case1SamplePresentaion.class);
//		Case1SamplePresentaion presentaion2 = context.getBean(Case1SamplePresentaion.class);
//		log.info("주입된 객체 비교 (==) :{}",presentaion == presentaion2 );
	}
}
