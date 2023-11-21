package kr.or.ddit.uiplugin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.ProdVO;

@Mapper
public interface ProdDAO {
	public int selectTotalRecord(PaginationInfo<ProdVO> pagingVO);
	public List<ProdVO> selectProdList(PaginationInfo<ProdVO> pagingVO);
}
