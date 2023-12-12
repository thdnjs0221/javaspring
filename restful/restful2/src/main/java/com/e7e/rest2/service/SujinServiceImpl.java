package com.e7e.rest2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e7e.rest2.mapper.SujinMapper;
import com.e7e.rest2.vo.SujinVO;

@Service
public class SujinServiceImpl implements SujinService{

	// 서비스는 맵퍼를 부른당!
	@Autowired
	private SujinMapper sujinMapper;
	
	@Override
	public List<SujinVO> getSujins() {
		return sujinMapper.getSujins();
	}

	@Override
	public SujinVO getSujin(SujinVO sujinVO) {
		return sujinMapper.getSujin(sujinVO);
	}

	@Override
	public int insSujin(SujinVO sujinVO) {
		return sujinMapper.insSujin(sujinVO);
	}

	@Override
	public int updateSujin(SujinVO sujinVO) {
		return sujinMapper.updateSujin(sujinVO);
	}

	@Override
	public int delSujin(SujinVO sujinVO) {
		return sujinMapper.delSujin(sujinVO);
	}

}
