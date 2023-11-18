package kr.or.ddit.mapper;

import java.util.List;

import kr.or.ddit.vo.BoardVO;

public interface IBoardMapper {

	public int insertBoard(BoardVO boardVO);

	public BoardVO selectBoard(int boNo);

	public void incremenHit(int boNo);

	public List<BoardVO>selectBoardList ();

	public int updateBoard(BoardVO boardVO);

	public int deleteBoard(int boNo);

}
