package kr.or.ddit.ui;

import kr.or.ddit.vo.PaginationInfo;

public interface PaginationRenderer {
	public default String getPagingFuncName() {
		return "fn_paging";
	}
	public default void setPagingFuncName(String funcName) {
	}
	
	public String renderPagination(PaginationInfo<?> pagingVO);
}
