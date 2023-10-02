package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리 Business Logic Layer
 *
 */
public interface ProdService {
	/**
	 * 상품 상세 조회
	 * @param prodId
	 * @return 존재하지 않으면, null 반환
	 */
	public ProdVO retrieveProd(String prodId);
}
