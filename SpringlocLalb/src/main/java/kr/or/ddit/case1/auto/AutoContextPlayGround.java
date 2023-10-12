package kr.or.ddit.case1.auto;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.case1.presentation.Case1SamplePresentaion;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class AutoContextPlayGround {
	public static void main(String[] args) {
		//컨테이너 생성 (classpath application context )
		// 차후에 종료될때 조건 
		//컨테이너 안에 presentatian 주입 받고 컨텐츠
		
			
	ConfigurableApplicationContext context = 
			new ClassPathXmlApplicationContext("kr/or/ddit/case1/conf/auto/auto-context.xml");
	context.registerShutdownHook();  //종료
	
	int count = context.getBeanDefinitionCount();
	String[] names = context.getBeanDefinitionNames(); //식별자 한번에 받기 
	
	log.info("빈 개수 :{}",count);
	log.info("빈 이름들 :{}",Arrays.toString(names) );  

	
	
	}

}
