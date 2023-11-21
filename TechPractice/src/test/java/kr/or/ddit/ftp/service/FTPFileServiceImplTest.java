package kr.or.ddit.ftp.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.AbstractRootTestCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 class FTPFileServiceImplTest extends AbstractRootTestCase{
	@Inject
	WebApplicationContext context;
	
	@Inject
	 FTPFileService ftpService;
	@Resource(name="ftpClientPool")
	private GenericObjectPool<FTPClient> ftpClientPool;
	
	@Test
	@Order(1)
	 void testTraversing() throws Exception {
		log.info("active : {}, idle : {}", ftpClientPool.getNumActive(), ftpClientPool.getNumIdle());
		ftpService.traversing("/");
		log.info("active : {}, idle : {}", ftpClientPool.getNumActive(), ftpClientPool.getNumIdle());
		ftpService.traversing("/group01");
		log.info("active : {}, idle : {}", ftpClientPool.getNumActive(), ftpClientPool.getNumIdle());
		ftpService.traversing("/cute5.jpg");
		log.info("active : {}, idle : {}", ftpClientPool.getNumActive(), ftpClientPool.getNumIdle());
	}
	
	@Test
	@Order(2)
	 void testTraversingException() throws Exception {
		assertThrows(IOException.class, ()->{
			ftpService.receiveFile("/notExist");
			log.info("active : {}, idle : {}", ftpClientPool.getNumActive(), ftpClientPool.getNumIdle());
		});
	}

	@Test
	@Order(3)
	 void testSendFile() throws Exception {
		assertDoesNotThrow(()->{
			org.springframework.core.io.Resource testFile = context.getResource("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml");
			MockMultipartFile file = new MockMultipartFile("test", testFile.getFilename(), "", testFile.getInputStream());
			ftpService.sendFile("/", file);
		});
	}
	
	@Test
	@Order(4)
	void testSendFileException(){
		org.springframework.core.io.Resource testFile = context.getResource("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml");
		InputStream is = assertDoesNotThrow(()->testFile.getInputStream());
		assertThrows(IOException.class, ()->{
			MockMultipartFile file = new MockMultipartFile("test", testFile.getFilename(), "", is);
			ftpService.sendFile("/notExist", file);
		});
	}

	@Test
	@Order(5)
	 void testReceiveFile() throws Exception {
		 File saveFile = ftpService.receiveFile("/group01");
		 log.info("저장 위치 : {}", saveFile.getCanonicalPath());
		 Arrays.stream(saveFile.listFiles()).forEach(System.out::println);
	}
	
	@Test
	@Order(6)
	 void testReceiveFileException() throws Exception {
		IOException exception = assertThrows(IOException.class, ()->{
									ftpService.receiveFile("/notExist");
								});
		
	}

	
	@Test
	@Order(7)
	 void testReceiveFiles() throws Exception{
		assertDoesNotThrow(()->{
			String[] paths = {
					"/cute5.jpg", "/group01/cute1.PNG", "/group01/cute3.jpg"	
			};
			File saveFile = ftpService.receiveFiles(paths);
			System.out.println(saveFile);
		});
	}
}
