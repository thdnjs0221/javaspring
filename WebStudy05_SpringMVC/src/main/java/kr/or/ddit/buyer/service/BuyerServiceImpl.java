package kr.or.ddit.buyer.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import kr.or.ddit.buyer.dao.BuyerDAO;
import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class BuyerServiceImpl implements BuyerService {
	
	

	@Value("#{appInfo.buyerImages}")  //bean에 접근하려면 el로 접근하기
	private Resource buyerImages;  //자바에서 파일을 Resource를 가지고 논다
	
	
	private File saveFolder;

	@PostConstruct // 생성자 이후에 실행
	public void init() throws IOException {
		saveFolder = buyerImages.getFile();
	}
	
	private final BuyerDAO dao; //final로 생성자 주입해서 만드는 방법도 가능

	@Override
	public BuyerVO retrieveBuyer(String buyerId) {
		
		return dao.selectBuyer(buyerId);
	}

	
	//이진 데이터 저장하는 로직
	private void processBuyerImage(BuyerVO buyer) {
	
		//예외 전환으로 
		
		try {
			buyer.saveTo(buyerImages.getFile());
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		} // 상품이미지 저장

	}

	//추가
	@Override
	public ServiceResult createBuyer(BuyerVO vo) {
		
		int rowcnt = dao.insertBuyer(vo);
		ServiceResult result = null;
		if(rowcnt>0) {
			//예외 전환으로 
			processBuyerImage(vo);  //파일 저장
			result=ServiceResult.OK;
		
		}else {
			result = ServiceResult.FAIL;
		}
	     return result;
	}


	@Override
	public ServiceResult modifyBuyer(BuyerVO vo) {
		int rowcnt = dao.updateBuyer(vo);
		ServiceResult result = null;
		if(rowcnt>0) {
			//예외 전환으로 
			processBuyerImage(vo);  //파일 저장
			result=ServiceResult.OK;
		
		}else {
			result = ServiceResult.FAIL;
		}
	     return result;
	}




	@Override
	public void retrieveBuyerList(PaginationInfo<BuyerVO> paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<BuyerVO>dataList = dao.selectBuyerList(paging);
		paging.setDataList(dataList);
	}

}
