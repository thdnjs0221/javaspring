package kr.or.ddit.service.Impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.mapper.IBoardMapper;
import kr.or.ddit.service.IBoardService;
import kr.or.ddit.vo.BoardVO;

@Service
public class BoardServiceImpl implements IBoardService {

	@Inject
	private IBoardMapper mapper;

	@Override
	public ServiceResult insertBoard(BoardVO boardVO) {
		ServiceResult result = null;
		int status = mapper.insertBoard(boardVO);
		if (status > 0) {
			result = ServiceResult.OK;

		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public BoardVO selectBoard(int boNo) {
		mapper.incremenHit(boNo);

		return mapper.selectBoard(boNo);
	}

	@Override
	public List<BoardVO> selectBoardList() {

		return mapper.selectBoardList();
	}

	@Override
	public ServiceResult updateBoard(BoardVO boardVO) {
		ServiceResult result = null;
		int status = mapper.updateBoard(boardVO);
		if (status > 0) {
			result = ServiceResult.OK;

		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult deleteBoard(int boNo) {
		ServiceResult result = null;
		int status = mapper.deleteBoard(boNo);
		if (status > 0) {
			result = ServiceResult.OK;

		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

}
