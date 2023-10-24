package kr.or.ddit.file;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class FileUploadController{
	
	@Value("#{appInfo.imagesUrl}")
	private String imagesUrl;
	
	@Value("#{appInfo.imagesUrl}")
	private Resource images;

	private File imageFolder;
	
	@PostConstruct
	public void init() throws IOException {
		imageFolder = images.getFile();
		log.info("이미지 파일 저장 위치-논리 : {}", imagesUrl);
		log.info("이미지 파일 저장 위치-물리 : {}", imageFolder.getCanonicalPath());
	}
	
	@GetMapping("/15/fileUpload.do")
	public String uploadForm() {
		return "15/fileUploadForm";
	}
	
	@PostMapping("/15/fileUpload.do")
	public String doPost(
		FileUploadVO uploadVO
		, RedirectAttributes redirectAttributes	
	) throws IllegalStateException, IOException{
		if(!uploadVO.getUploadFile().getContentType().startsWith("image/")) {
			throw new BadRequestException("이미지가 아닌 파일 업로드 불가");
		}
		
		log.info("upload command object : {}", uploadVO);
		
		uploadVO.saveTo(imageFolder);
		uploadVO.makeFileUrl(imagesUrl);
		
		redirectAttributes.addFlashAttribute("uploadVO", uploadVO);
		
		return "redirect:/15/fileUpload.do";
	}
}






















