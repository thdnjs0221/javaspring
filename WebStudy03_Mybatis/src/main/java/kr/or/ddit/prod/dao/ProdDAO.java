package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리 Persistence Layer
 *
 */
public interface ProdDAO {
	/**
	 * 상품 상세 조회
	 * @param prodId
	 * @return 없으면, null 반환
	 */
	public ProdVO selectProd(String prodId);
}
