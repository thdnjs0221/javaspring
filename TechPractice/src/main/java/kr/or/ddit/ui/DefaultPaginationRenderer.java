package kr.or.ddit.ui;

import kr.or.ddit.vo.PaginationInfo;

public class DefaultPaginationRenderer implements PaginationRenderer {

	@Override
	public String renderPagination(PaginationInfo<?> pagingVO) {
		String APATTERN = "<a href='javascript:;' data-page='%1$d' onclick='return "+getPagingFuncName()+"(%1$d, event);'>%2$s</a>";
		StringBuffer html = new StringBuffer();
		int startPage = pagingVO.getStartPage();
		int blockSize = pagingVO.getBlockSize();
		int endPage = pagingVO.getEndPage();
		int currentPage = pagingVO.getCurrentPage();
		int totalPage = pagingVO.getTotalPage();
		
		if(startPage > blockSize) {
			html.append(
				String.format(APATTERN, startPage-blockSize, "이전")	
			);
		}
		
		for(int page=startPage; page<=endPage; page++) {
			if(page==currentPage) {
				html.append(
					"<a href='#'>"+page+"</a>"
				);
			}else {
				html.append(
					String.format(APATTERN, page, page+"")
				);
			}
		}
		
		if(endPage<totalPage) {
			html.append(
					String.format(APATTERN, endPage+1, "다음")	
				);
		}
		
		return html.toString();
	}
}
