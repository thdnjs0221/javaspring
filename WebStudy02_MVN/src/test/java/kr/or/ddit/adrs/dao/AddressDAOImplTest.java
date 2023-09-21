package kr.or.ddit.adrs.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.AddressVO;

class AddressDAOImplTest {
	
	AddressDAO dao= new AddressDAOImpl();

	@Test
	void testInsertAddress() {
		AddressVO vo = new AddressVO();
		vo.setMemId("a001");
		vo.setAdrsName("테스트");
		vo.setAdrsHp("000-0000-0000");
		vo.setAdrsAdd("대전 오류");
		int rowcnt = dao.insertAddress(vo);
		assertEquals(1, rowcnt);
	}

	@Test
	void testSelectAddressList() {
		List<AddressVO>adrsList = dao.selectAddressList("a001");
		assertNotNull(adrsList);
		assertNotEquals(0, adrsList.size());
	}

	@Test
	void testUpdateAddress() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteAddress() {
		int rowcnt = dao.deleteAddress(1);
		assertEquals(1, rowcnt);
	}

}
