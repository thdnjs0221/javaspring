package kr.or.ddit.logging;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class LoggerTest {
	//private static final Logger log = LoggerFactory.getLogger(LoggerTest.class);  //@Slf4j롬복이 지원해줌 

	@Test
	void test() {
//		System.out.println("출력 로그");
		log.debug("debug 메세지");
		log.info("info 메세지");
		log.error("error 메세지");
		
		int number = 34;
		log.info("number ={}",number);
		try {
			if(1==1)
				throw new NullPointerException("강제 발생 예외");
		}catch (NullPointerException e) {
			log.error(e.getMessage(),e);
		}
	}

}
