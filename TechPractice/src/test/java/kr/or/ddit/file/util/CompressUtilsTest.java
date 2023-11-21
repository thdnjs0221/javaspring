package kr.or.ddit.file.util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

 class CompressUtilsTest {

	@Test
	void testCompress() {
		fail("Not yet implemented");
	}

	@Test
	void testCompressFolder(){
		File folder = new File("D:\\00.medias");
		File zipFile = new File("d:/media.zip");
		assertDoesNotThrow(()->{
			FileOutputStream fos = new FileOutputStream(zipFile);	
			CompressUtils.compressFolder(folder, fos);
		});
	}

}
