package kr.or.ddit.adrs.service;

import java.util.List;

import kr.or.ddit.adrs.dao.AddressDAO;
import kr.or.ddit.adrs.dao.AddressDAOImpl;
import kr.or.ddit.vo.AddressVO;

public class AddressServiceImpl implements AddressService {
	// 결합력 최상구조
	private AddressDAO dao = new AddressDAOImpl();

	@Override
	public boolean createAddress(AddressVO adrsVO) {
		int rowcnt = dao.insertAddress(adrsVO);
		return rowcnt >= 1;
	}

	@Override
	public List<AddressVO> retriveAddressList(String memId) {
		return dao.selectAddressList(memId);
	}

	@Override
	public boolean modifyAddress(AddressVO adrsVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAddress(int adrsNo) {
		return dao.deleteAddress(adrsNo) > 0;
	}

}
















