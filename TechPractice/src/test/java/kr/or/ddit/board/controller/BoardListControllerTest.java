package kr.or.ddit.board.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import kr.or.ddit.AbstractControllerTestCase;

class BoardListControllerTest extends AbstractControllerTestCase{

	@Test
	void testGetUI() {
		assertNotNull(mockMvc);
	}
}
