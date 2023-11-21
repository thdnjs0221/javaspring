package kr.or.ddit.others.image;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URLConnection;
import java.util.Optional;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Slf4j
public class ThumbnailatorTest {

	
	@Test
	void generateThumbnail1() {
		assertDoesNotThrow(()->{
			File targetFile = new File("src/main/webapp/resources/images/cat1.jpg");
			log.info("파일 위치 : {}", targetFile.getCanonicalPath());
			File destFile = new File("src/test/resources/kr/or/ddit/others/image/cat1_thumbnail1.jpg");
			
			Thumbnails.of(targetFile)
					.size(100, 100)
					.toFile(destFile);
			log.info("생성된 썸네일 위치 : {}", destFile.getCanonicalPath());
		});	
	}
	@Test
	void generateThumbnail2() {
		assertDoesNotThrow(()->{
			File targetFile = new File("src/main/webapp/resources/images/cat1.jpg");
			File watermark = new File("src/main/webapp/resources/images/watermark.jpg");
			log.info("파일 위치 : {}", targetFile.getCanonicalPath());
			File destFile = new File("src/test/resources/kr/or/ddit/others/image/cat1_thumbnail2.jpg");
			
			Thumbnails.of(targetFile)
			.size(300, 300)
			.rotate(0)
			.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(watermark), 0.5f)
			.toFile(destFile);
			log.info("생성된 썸네일 위치 : {}", destFile.getCanonicalPath());
		});	
	}
	
	@Test
	void generateThumbnail3() {
		assertDoesNotThrow(()->{
			File targetFile = new File("src/main/webapp/resources/images/cat1.jpg");
			log.info("파일 위치 : {}", targetFile.getCanonicalPath());
			File destFile = new File("src/test/resources/kr/or/ddit/others/image/cat1_thumbnail3.png");
			
			Thumbnails.of(targetFile)
					.size(100, 50) // 더 작은 사이즈를 기준으로 비율을 유지함.
					.outputFormat("png")
					.toFile(destFile);
			log.info("생성된 썸네일 위치 : {}", destFile.getCanonicalPath());
		});	
	}
	
	@Test
	void generateThumbnail4() {
		assertDoesNotThrow(()->{
			File targetFile = new File("src/main/webapp/resources/images/cat1.jpg");
			log.info("파일 위치 : {}", targetFile.getCanonicalPath());
			File destFile = new File("src/test/resources/kr/or/ddit/others/image/cat1_thumbnail4.jpg");
			
			Thumbnails.of(targetFile)
			.size(100, 50)
			.keepAspectRatio(false) // 비율 유지 여부 결정
			.toFile(destFile);
			log.info("생성된 썸네일 위치 : {}", destFile.getCanonicalPath());
		});	
	}
	
	@Test
	void generateThumbnail5() {
		assertDoesNotThrow(()->{
			File targetFile = new File("src/main/webapp/resources/images/cat1.jpg");
			log.info("파일 위치 : {}", targetFile.getCanonicalPath());
			File destFile = new File("src/test/resources/kr/or/ddit/others/image/cat1_thumbnail5.jpg");
			
			Thumbnails.of(targetFile)
			.scale(0.75)
			.toFile(destFile);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Thumbnails.of(targetFile)
			.size(100, 100)
			.toOutputStream(baos);
			
			log.info("생성된 썸네일 위치 : {}", destFile.getCanonicalPath());
			log.info("생성된 썸네일 : {}", baos.toByteArray().length);
		});	
	}
	
	@Test
	void generateThumbnail6() {
		assertDoesNotThrow(()->{
			File targetFolder = new File("src/main/webapp/resources/images/");
			log.info("파일 위치 : {}", targetFolder.getCanonicalPath());
			File destFolder = new File("src/test/resources/kr/or/ddit/others/image/");
			
			Thumbnails.of(targetFolder.listFiles((d,n)->{
					log.info("name : {}, mime1 : {}, mime2 : {}" 
								, n, MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(n)
								, URLConnection.guessContentTypeFromName(n) );
					return Optional.ofNullable(URLConnection.guessContentTypeFromName(n))
									.filter(mt->mt.startsWith("image/"))
									.isPresent();
				})).scale(0.25)
					.toFiles(destFolder, Rename.PREFIX_HYPHEN_THUMBNAIL);
			log.info("생성된 썸네일 위치 : {}", destFolder.getCanonicalPath());
			
			
		});	
	}
}
