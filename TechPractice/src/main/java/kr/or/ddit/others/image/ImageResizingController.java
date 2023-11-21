package kr.or.ddit.others.image;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/others/image")
public class ImageResizingController {
	
	@GetMapping
	public String resizeUI() {
		return "others/image/imageUI";
	}
	
	private byte[] makeThumbnail(MultipartFile targetFile) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Thumbnails.of(ImageIO.read(targetFile.getInputStream()))
				.scale(0.5)
				.outputFormat("png")
				.toOutputStream(baos);
		return baos.toByteArray();
	}
	
	@PostMapping
	public String resizeProcessSync(MultipartFile targetFile, RedirectAttributes redirectAttributes) throws IOException {
		
		byte[] thumbnail = makeThumbnail(targetFile);
		byte[] original = targetFile.getBytes();
		
		String orgEncode = Base64.getEncoder().encodeToString(original);
		String thumbEncoded = Base64.getEncoder().encodeToString(thumbnail);
		String orgDataScheme = String.format("data:%s;base64,%s", targetFile.getContentType(), orgEncode);
		String thumbDataScheme = String.format("data:%s;base64,%s", targetFile.getContentType(), thumbEncoded);
		
		redirectAttributes.addFlashAttribute("orgImg", orgDataScheme);
		redirectAttributes.addFlashAttribute("thumbImg", thumbDataScheme);
		
		return "redirect:/others/image";
	}
	
	@PostMapping(produces = "image/*")
	public ResponseEntity<Resource> resizeProcessAsync(MultipartFile targetFile) throws IOException {
		byte[] thumbnail = makeThumbnail(targetFile);
		ByteArrayResource resource = new ByteArrayResource(thumbnail);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(resource);
	}
}
