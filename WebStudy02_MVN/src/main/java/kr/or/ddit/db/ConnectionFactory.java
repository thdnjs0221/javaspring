package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * A a1 = new A();
 * A a2 = new A();
 * 1. A클래스가 로딩.
 * 2. instance 생성 (heap memory - GC대상)
 * 3. a1에 생성된 인스턴스의 참조 주소 할당
 * 
 * Factory Object[Method] Pattern
 * 	:인스턴스 생성에 대한 책임을 지는 factory 객체의 운영
 * 
 *   
 *   
 * */
public class ConnectionFactory {
	private static String url;
	private static String user;
	private static String password;
	static{
		Properties dbInfo = new Properties();
//		classpath resourse 형태 자원 읽기
//		kr/or/ddit/db/dbinfo.properties  //논리경로 
		
		try(
				InputStream is =  ConnectionFactory.class.getResourceAsStream("dbinfo.properties"); //상대경로  , try 안에 넣으면 자동으로 닫힘	
		) {
			dbInfo.load(is);
			String driverClassName = dbInfo.getProperty("driverClassName");
			Class.forName(driverClassName);
			url = dbInfo.getProperty("url");
			user = dbInfo.getProperty("user");
			password = dbInfo.getProperty("password");
		} catch (ClassNotFoundException | IOException e) {
			throw new RuntimeException(e);			
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

}
