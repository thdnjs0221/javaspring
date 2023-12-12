package com.e7e.rest2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e7e.rest2.service.SujinService;
import com.e7e.rest2.vo.SujinVO;

import lombok.extern.slf4j.Slf4j;

// 요기까지가 가장 많이 하는 기계적인 기본 사항!, 안되는 사람은 요걸 반복 연습해서 손에 익혀야 함!
@Slf4j
@RestController
@RequestMapping("/api")
public class SujinController {
	
	// 컨트롤러는 서비스를 부름
	@Autowired
	private SujinService sujinService;
	
	//리스트
	@GetMapping("/sujins")
	public List<SujinVO> getSujins(){	
		return sujinService.getSujins();
	}
	
	//1개
	@GetMapping("/sujins/{num}")
	public SujinVO getSujin(@PathVariable int num){
		SujinVO sujinVO = new SujinVO();
		sujinVO.setSujinNum(num);
		return sujinService.getSujin(sujinVO);
	}
	
	//insert
	@PostMapping("/sujins")
	public int insSujin(@RequestBody SujinVO sujinVO) {
		log.info("체킁11:{}",sujinVO);
		return sujinService.insSujin(sujinVO);
	}

	//update
	@PutMapping("/sujins")
	public int updateSujin(@RequestBody SujinVO sujinVO) {
		return sujinService.updateSujin(sujinVO);
	}
	
	//delete
	@DeleteMapping("/sujins/{num}")
	public int updateSujin(@PathVariable int num) {
		SujinVO sujinVO = new SujinVO();
		sujinVO.setSujinNum(num);
		return sujinService.delSujin(sujinVO);
	}

}
