package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PaginationInfo;
import lombok.extern.slf4j.Slf4j;


@Mapper
public interface BuyerDAO {
	
	/**
	 * 제조사 상세 조회
	 * @param buyerId
	 * @return
	 */
	public BuyerVO selectBuyer(String buyerId);
	
	/**
	 * 제조사 추가
	 * @param vo
	 * @return
	 */
	public int insertBuyer(BuyerVO vo);
	
	/**
	 * 제조사 수정 
	 * 
	 * @param vo
	 * @return
	 */
	public int updateBuyer(BuyerVO vo);
	
	public List<BuyerVO> selectBuyerList(PaginationInfo<BuyerVO> paging);
	
	public int selectTotalRecord(PaginationInfo<BuyerVO> paging);

}
