package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.vo.PaginationInfo;
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
	
	
	/**
	 *  페이징 처리 기반이 상품 목록 조회
	 *  페이징 처리의 결과로 totalRecord/dataList 프로퍼티를 만들어줘야함
	 *  @param currentPage를 비록한 프로퍼티를 가진{@link PaginationInfo }
	 */
	public void retrieveProdList(PaginationInfo<ProdVO>paging);
	
	
	
	/**
	 * 상품코드를 생성하고 신규상품을 등록함
	 * @param prod
	 * @return OK, FAIL
	 * 
	 * pk중복 필요없음 입력을 안받기 때문에
	 */
	public ServiceResult createProd(ProdVO prod);
	
	
	/**
	 * 상품 수정
	 * @param prod
	 * @return OK, FAIL
	 */
	public ServiceResult modifyProd(ProdVO prodId) ;
	
}
