package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.login.service.AthenticateServiceImpl;
import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProdServiceImpl implements ProdService {
	
	@Value("#{appInfo.prodImagesUrl}")
	private String prodImagesUrl;

	@Value("#{appInfo.prodImagesUrl}")
	private Resource prodImages;

	private File saveFolder;

	@PostConstruct // 생성자 이후에 실행
	public void init() throws IOException {
		saveFolder = prodImages.getFile();
	}
	
	
	private final ProdDAO dao;
	private AuthenticateService authService ;



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

	private void processProdImage(ProdVO prod) {
			//예외 전환으로 
			try {
				prod.saveTo(saveFolder);  //상품이미지 저장
			}catch (IOException e) {
				throw new RuntimeException(e);
			}
		
	}


	@Override
	public ServiceResult createProd(ProdVO prod) {
		int rowcnt = dao.insertProd(prod);
		ServiceResult result = null;
		if(rowcnt>0) {
			//예외 전환으로 
			result=ServiceResult.OK;
			processProdImage(prod);
		
		}else {
			result = ServiceResult.FAIL;
		}
	     return result;

		
	}



	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		int rowcnt = dao.updateProd(prod);
		ServiceResult result = null;
		if(rowcnt>0) {
			//예외 전환으로 
			result=ServiceResult.OK;
			processProdImage(prod);
		
		}else {
			result = ServiceResult.FAIL;
		}
	     return result;

	}

}
