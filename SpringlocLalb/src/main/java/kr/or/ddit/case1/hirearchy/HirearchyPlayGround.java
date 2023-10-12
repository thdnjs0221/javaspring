package kr.or.ddit.case1.hirearchy;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.case1.presentation.Case1SamplePresentaion;
import kr.or.ddit.case1.service.Case1SampleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HirearchyPlayGround {
	public static void main(String[] args) {
		ConfigurableApplicationContext parent = 
				new GenericXmlApplicationContext("classpath:kr/or/ddit/case1/conf/hirearchy/parent-context.xml");
		ConfigurableApplicationContext child = 
			new ClassPathXmlApplicationContext(new String[] {
					"kr/or/ddit/case1/conf/hirearchy/child-context.xml"
			}, parent);
		
		Case1SamplePresentaion presentation = child.getBean(Case1SamplePresentaion.class);
		String content = presentation.makeContent("a001");
		log.info(content);
		
		Case1SampleService service = child.getBean(Case1SampleService.class);
		log.info("주입된 객체 : {}",service);
		
//		parent.getBean(Case1SamplePresentaion.class);
		
	}

}
