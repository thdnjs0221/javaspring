package kr.or.ddit.db;

import static org.junit.jupiter.api.Assertions.*;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import lombok.extern.slf4j.Slf4j;
//@ContextConfiguration("file:src/main/resources/kr/or/ddit/spring/datasource-context.xml")
@SpringJUnitWebConfig(locations = "file:src/main/resources/kr/or/ddit/spring/*-context.xml") //웹용 테스트
@Slf4j
class DataSourceTest {

	@Inject
	private DataSource dataSource;
	
	@Inject
	private SqlSessionTemplate sqlSession;
	
	@Test
	void test() {
		log.info("주입된 객체{}",dataSource);
	}
	
	@Test
	void test2() {
		log.info("주입된 객체{}",sqlSession);
	}

}
