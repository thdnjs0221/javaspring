package kr.or.ddit.db;

import java.io.Reader;

import javax.annotation.Resource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CustomSqlSessionFactoryBuilder {

	// 싱글톤으로
	//private static SqlSession sqlSession;
	private static SqlSessionFactory sqlSessionFactory;
	static {
		String configFile = "kr/or/ddit/mybatis/Configuration.xml";

		try (
				Reader reader = Resources.getResourceAsReader(configFile);
		
		) {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

}
