package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

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

	private static BasicDataSource dataSource;

	static{
		Properties dbInfo = new Properties();
//		classpath resourse 형태 자원 읽기
//		kr/or/ddit/db/dbinfo.properties  //논리경로 
		
		try(
				InputStream is =  ConnectionFactory.class.getResourceAsStream("dbinfo.properties"); //상대경로  , try 안에 넣으면 자동으로 닫힘	
		) {
			dbInfo.load(is);
			String driverClassName = dbInfo.getProperty("driverClassName");
			//Class.forName(driverClassName);
			String url = dbInfo.getProperty("url");
			String user = dbInfo.getProperty("user");
			String password = dbInfo.getProperty("password");
			
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName(driverClassName);
			dataSource.setUrl(url);
			dataSource.setUsername(user);
			dataSource.setPassword(password);
//			initialSize=2
//			maxIdle=2
//			maxTotal=3
//			maxWait=2000
					
			int initialSize = Integer.parseInt(dbInfo.getProperty("initialSize")) ;
			int maxIdle = Integer.parseInt(dbInfo.getProperty("maxIdle")) ;
			int maxTotal = Integer.parseInt(dbInfo.getProperty("maxTotal")) ;
			int maxWait = Integer.parseInt(dbInfo.getProperty("maxWait")) ;
			
			dataSource.setInitialSize(initialSize);
			dataSource.setMaxTotal(maxTotal);
			dataSource.setMaxWaitMillis(maxWait);
			dataSource.setMaxIdle(maxIdle);
			
			
			dataSource.setInitialSize(2);
			dataSource.setMaxTotal(3);
			dataSource.setMaxWaitMillis(2000);
			dataSource.setMaxIdle(2);
			
			
			
		} catch ( IOException e) {
			throw new RuntimeException(e);			
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

}
