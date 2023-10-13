package kr.or.ddit;

import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;



@SpringJUnitWebConfig(locations = "file:src/main/resources/kr/or/ddit/spring/*-context.xml") //웹용 테스트
public abstract class AbstractRootContextTest {

	
}
