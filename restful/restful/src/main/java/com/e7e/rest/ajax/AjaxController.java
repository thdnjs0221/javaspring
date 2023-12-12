package com.e7e.rest.ajax;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.e7e.rest.vo.Data2VO;
import com.e7e.rest.vo.DataVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController  // Controller + ResponseBody   
@RequestMapping("/api")  // 상위 URL
public class AjaxController {

	@GetMapping("/get")  // 메소드 + 하우URL /api/get
	public String getAjax() {
		return "GET";
	}
	
	@PostMapping("/post")
	// {} 면 map이나 VO , []면 list 로 받음
	public List<Map<String, String>> postAjax(@RequestBody  List<Map<String, String>> cont) {
		log.debug("서버쪽체킁{}",cont);
		return cont;   // 받은 값을 그대로 리턴!
	}

	@PostMapping("/postvo")
	// {} 면 map이나 VO , []면 list 로 받음
	public List<DataVO> postvoAjax(@RequestBody  List<DataVO> cont) {
		log.debug("서버쪽체킁{}",cont);
		return cont;   // 받은 값을 그대로 리턴!
	}

	@PostMapping("/postfile")
	// {} 면 map이나 VO , []면 list 로 받음
	public String postfileAjax(MultipartFile whFile) throws Exception {
		log.debug("서버쪽체킁{}", whFile);
		
		whFile.transferTo(new File("d:/upload/" + whFile.getOriginalFilename()));
		
		return "/upload/" + whFile.getOriginalFilename() ;   // 웹경로 문자열 리턴!
	}


	@PostMapping("/postfile2")
	// {} 면 map이나 VO , []면 list 로 받음
	public List<String> postfile2Ajax(Data2VO jsData) throws Exception {
		log.debug("서버쪽체킁{}", jsData);
		
		List<MultipartFile> myFiles = jsData.getFiles();
		List<String> filePaths = new ArrayList<>();
		
		for (MultipartFile oneFile : myFiles) {
			oneFile.transferTo(new File("d:/upload/" + oneFile.getOriginalFilename()));
			filePaths.add("/upload/" + oneFile.getOriginalFilename());
		}
				
		return filePaths;
	}

	
	
	@PutMapping("/put")
	public String putAjax() {
		return "PUT";
	}

	@DeleteMapping("/delete")
	public String deleteAjax() {
		return "DELETE";
	}
	
	 
}
