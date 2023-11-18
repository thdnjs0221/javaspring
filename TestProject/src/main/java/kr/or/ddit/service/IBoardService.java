package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.BoardVO;

public interface IBoardService {

	public ServiceResult insertBoard(BoardVO boardVO);

	public BoardVO selectBoard(int boNo);

	public List<BoardVO> selectBoardList();

	public ServiceResult updateBoard(BoardVO boardVO);

	public ServiceResult deleteBoard(int boNo);

}
