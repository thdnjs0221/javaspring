package kr.or.ddit.prod.dao;

import static org.junit.jupiter.api.Assertions.*;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import kr.or.ddit.AbstractRootContextTest;


class ProdDAOTest extends AbstractRootContextTest{

	@Inject
	private ProdDAO prodDAO;
	
	
	@Test
	void testSelectProd() {
		prodDAO.selectProd("P101000001");
	}

	@Test
	void testSelectTotalRecord() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectProdList() {
		fail("Not yet implemented");
	}

	@Test
	void testInsertProd() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateProd() {
		fail("Not yet implemented");
	}

}
