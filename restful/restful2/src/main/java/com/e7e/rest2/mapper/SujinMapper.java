package com.e7e.rest2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.e7e.rest2.vo.SujinVO;

@Mapper
public interface SujinMapper {
	
	// 똑똑하다면 기본적으로 5개가 아무도 시키지 않아도 메소드가 추가됨
	
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
