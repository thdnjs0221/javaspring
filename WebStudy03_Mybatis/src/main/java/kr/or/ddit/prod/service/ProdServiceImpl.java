package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.login.service.AthenticateServiceImpl;
import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDAOIMpl;
import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements ProdService {
	private ProdDAO dao = new ProdDAOIMpl();
	private AuthenticateService authService = new AthenticateServiceImpl();



	@Override
	public ProdVO retrieveProd(String prodId) {
		return dao.selectProd(prodId);
	}



	//@
	@Override
	public void retrieveProdList(PaginationInfo<ProdVO> paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<ProdVO> dataList =dao.selectProdList(paging);
		paging.setDataList(dataList);
		
		
	}



	@Override
	public ServiceResult createProd(ProdVO prod) {
		int rowcnt = dao.insertProd(prod);
	      return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;

		
	}

}
