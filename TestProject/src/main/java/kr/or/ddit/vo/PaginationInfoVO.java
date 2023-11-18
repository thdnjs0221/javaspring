package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;

@Data
public class PaginationInfoVO<T> {
   private int totalRecord;      // 총 게시글 수
   private int totalPage;         // 총 페이지 수
   private int currentPage;      // 현재 페이지
   private int screenSize = 10;         // 페이지 당 게시글 수
   private int blockSize = 5;         // 페이지 블록 수
   private int startRow;         // 시작 row
   private int endRow;            // 끝 row
   private int startPage;         // 시작 페이지
   private int endPage;         // 끝 페이지
   private List<T> dataList ;      // 결과를 넣을 데이터 리스트
   private String searchWord;         // 검색 단어
   private String searchType;         // 검색 타입
   
   public PaginationInfoVO() {
      super();
   }
   
   public PaginationInfoVO(int screenSize, int blockSize) {
      super();
      this.screenSize = screenSize;
      this.blockSize = blockSize;
   }
   
   public void setTotalRecord(int totalRecord) {
      this.totalRecord = totalRecord;
      // ceil은 올림.
      totalPage = (int)Math.ceil(totalRecord / (double)screenSize);
   }
   
   public void setCurrentPage(int currentPage) {
      this.currentPage = currentPage;
      endRow = currentPage * screenSize;      // 끝Row = 현재 페이지 * 한 페이지 당 게시글 수(row = 1* 10)
      startRow = endRow - (screenSize -1);   // 시작Row = 끝Row - (한 페이지당 게시글 수 -1)
      // 마지막 페이지 = (현재 페이지 + (페이지 블록 사이즈 -1)) / 페이지 블록 사이즈 * 페이지 블록 사이즈
      // 마지막 페이지 = (1 + 5-1) / 5 * 5
      endPage = (currentPage + (blockSize - 1)) / blockSize * blockSize;
      startPage = endPage - (blockSize -1);
   }
   
   // 페이징 html 작성
   public String getPagingHTML() {
      StringBuffer html = new StringBuffer();
      html.append("<ul class='pagination pagination-sm m-0 float-right'>");
      if(startPage > 1) {
         html.append("   <li class='page-item'><a href='' class-'page-link' tabindex='-1' data-page='"+
                  (startPage - blockSize) + "'>Prev</a></li>");
      }
      for(int i= startPage; i<=(endPage < totalPage ? endPage : totalPage); i++) {
         if(i == currentPage) {
            html.append("<li class='page-item active'><span class='page-link'>" + i + "</span></li>");
         } else {
            html.append("<li class='page-item'><a href='' class='page-link' data-page='" + 
                  i + "'>" + i + "</a></li>");
         }
      }
      if(endPage < totalPage) {
         html.append("<li class='page-item'><a href='' class='page-link' data-page'" + 
               (endPage + 1) + "'>Next</a></li>");
      }
      html.append("</ul>");
      return html.toString();
   }
}