package kr.or.ddit.mvc.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ReflectionUtilsTest {

	@Test
	void test() {
		List<Class<?>> classes = ReflectionUtils.getAllClassesAtBasePackages("kr.or.ddit.prod.controller");
		log.info("classes {}",classes);
	}

}
