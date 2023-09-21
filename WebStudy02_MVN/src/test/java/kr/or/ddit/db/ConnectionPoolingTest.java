package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

class ConnectionPoolingTest {
	private static String url;
	private static String user;
	private static String password;
	private static String driverClassName;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		Properties dbInfo = new Properties();
//		classpath resourse 형태 자원 읽기
//		kr/or/ddit/db/dbinfo.properties  //논리경로 
		
		try(
				InputStream is =  ConnectionFactory.class.getResourceAsStream("dbinfo.properties"); //상대경로  , try 안에 넣으면 자동으로 닫힘	
		) {
			dbInfo.load(is);
			driverClassName = dbInfo.getProperty("driverClassName");
			Class.forName(driverClassName);
			url = dbInfo.getProperty("url");
			user = dbInfo.getProperty("user");
			password = dbInfo.getProperty("password");
		} catch (ClassNotFoundException | IOException e) {
			throw new RuntimeException(e);			
		}
	}
	@Test
	void test3()  throws SQLException {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		
		for(int i=0; i<100; i++) {
			try(
			Connection conn = dataSource.getConnection();
			){}
			
			}
		
	}
	@Test
	void test4()  throws SQLException {
		for(int i=0; i<100; i++) {
			try(
			Connection conn = ConnectionFactory.getConnection();
			){}
			
			}
	
	}
	
	@Test
	void test2()  throws SQLException {
		OracleConnectionPoolDataSource dataSource = new OracleConnectionPoolDataSource();
		dataSource.setURL(url);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		
		for(int i=0; i<100; i++) {
			try(
			Connection conn = dataSource.getConnection();
			){}
			
			}
	}
	

	@Test
	void test1() throws SQLException {
		for(int i=0; i<100; i++) {
		try(
		Connection conn = DriverManager.getConnection(url, user, password);
		){}
		}
	}

}
