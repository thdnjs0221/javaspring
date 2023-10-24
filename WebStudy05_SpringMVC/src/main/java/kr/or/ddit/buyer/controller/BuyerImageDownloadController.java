package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.common.exception.BadRequestException;
import kr.or.ddit.vo.BuyerVO;

@Controller
public class BuyerImageDownloadController {
	
	@Value("#{appInfo.buyerImages}")  //bean에 접근하려면 el로 접근하기
	private Resource buyerImages;  //자바에서 파일을 Resource를 가지고 논다

	@Inject
	private BuyerService service;
	
	@GetMapping("/buyer/{buyerId}/buyerImage")
	public ResponseEntity<Resource> buyerImageDownload (@PathVariable String buyerId) throws IOException {
		
		BuyerVO buyer =  service.retrieveBuyer(buyerId);
		if(buyer==null) {
				//해당 제조사 없음
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("%s 제조사 없음", buyerId)); //5.0버전 사용가능
		
		}
		String filename = buyer.getBuyerImg();
		
		if(StringUtils.isBlank(filename)) {
			//사업장 등록증 사본이 없을 경우 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("%s 사업자 등록증 없음", buyer.getBuyerName())); //5.0버전 사용가능
			
		}
		
		//자바에서 파일은 Resource으로 가지고 논다.
		Resource imageFile = buyerImages.createRelative(filename);
		
//		Content-Disposition: attachment; filename="filename.jpg"
		ContentDisposition disposition = ContentDisposition.attachment()
								.filename(buyer.getBuyerName(),Charset.defaultCharset())  //업체명을 파일명으로 사용
								.build();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(disposition); 
		headers.setContentLength(imageFile.contentLength());
		
		return ResponseEntity.ok()// line 200
						.headers(headers) //header
						//.contentLength(imageFile.contentLength() ) //header ,길이
						.body(imageFile);  //body
		
	}
}
