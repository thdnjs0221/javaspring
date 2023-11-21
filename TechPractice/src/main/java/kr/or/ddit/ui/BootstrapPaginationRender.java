package kr.or.ddit.ui;

import kr.or.ddit.vo.PaginationInfo;

public class BootstrapPaginationRender implements PaginationRenderer {

	@Override
	public String renderPagination(PaginationInfo<?> pagingVO) {
		String previousPtrn ="<li class='page-item %1$s'><a class='page-link paging' href='javascript:;' %2$s data-page='%3$d' onclick='return "+getPagingFuncName()+"(%3$d, event);'>Previous</a></li>";
		String nextPtrn =    "<li class='page-item %1$s'><a class='page-link paging' href='javascript:;' %2$s data-page='%3$d' onclick='return "+getPagingFuncName()+"(%3$d, event);'>Next</a></li>";
		
		String pagePtrn = "<li class='page-item'><a class='page-link paging' href='javascript:;' data-page='%1$d' onclick='return "+getPagingFuncName()+"(%1$d, event);'>%1$d</a></li>";
		String currentPtrn ="<li class='page-item active' aria-current='page'><a class='page-link paging' href='javascript:;'>%d</a></li>";
		
		int startPage = pagingVO.getStartPage();
		int endPage = pagingVO.getEndPage();
		int currentPage = pagingVO.getCurrentPage();
		int totalPage = pagingVO.getTotalPage();
		
		StringBuffer html = new StringBuffer();
		html.append("<nav aria-label='...'>");
		html.append("<ul class='pagination'>");
		
		boolean disabled =  startPage <= 1;
		html.append(
			String.format(previousPtrn
						, disabled?"disabled":"" 
						, disabled?"":"href='javascript:;'"
						, disabled?1:startPage-1	
					)	
		);
		
		for(int page=startPage; page<=endPage; page++) {
			if(page==currentPage) {
				html.append(
					String.format(currentPtrn, page)
				);
			}else {
				html.append(
					String.format(pagePtrn, page)
				);
			}
		}
		
		disabled = endPage >= totalPage;
		html.append(
			String.format(nextPtrn
					, disabled?"disabled":""
					, disabled?"":"href='javascript:;'"
					, disabled?totalPage:endPage+1
			)	
		);
		
		
		html.append("</ul>");
		html.append("</nav>");
		
		return html.toString();
	}

}

















