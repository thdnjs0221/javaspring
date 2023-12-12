package com.e7e.rest2.service;

import java.util.List;

import com.e7e.rest2.vo.SujinVO;

// 요기에 @Service 붙이면 여러사람이 미쳐용, 인터페이스는 객체화 안됨
public interface SujinService {
	//리스트
	List<SujinVO> getSujins();
	//1개
	SujinVO  getSujin(SujinVO sujinVO);
	//생성
	int insSujin(SujinVO sujinVO);
	//수정
	int updateSujin(SujinVO sujinVO);
	//삭제
	int delSujin(SujinVO sujinVO);
}
