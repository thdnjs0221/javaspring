package kr.or.ddit.uiplugin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PaginationInfo;

@Mapper
public interface BuyerDAO {
	public int selectTotalRecord(PaginationInfo<BuyerVO> pagingVO);
	public List<BuyerVO> selectBuyerList(PaginationInfo<BuyerVO> pagingVO);
}
