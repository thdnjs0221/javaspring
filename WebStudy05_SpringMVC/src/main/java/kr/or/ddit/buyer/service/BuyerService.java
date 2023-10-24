package kr.or.ddit.buyer.service;



import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.ProdVO;

public interface BuyerService {
	
 /**
  * 제조사 상세 조회
 * @param buyerId
 * @return 존재하지 않으면, null반환  (예외 정해서 쓰기)
 */

public BuyerVO	retrieveBuyer(String buyerId);

/**
 * 제조사 추가
 * @param vo
 * @return
 */
public ServiceResult createBuyer(BuyerVO vo);

/**
 * 제조사 수정 
 * @param vo
 * @return
 */
public ServiceResult modifyBuyer(BuyerVO vo);


public void retrieveBuyerList(PaginationInfo<BuyerVO>paging);

}
