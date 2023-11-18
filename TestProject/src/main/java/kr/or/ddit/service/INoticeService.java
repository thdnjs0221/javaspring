package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface INoticeService {
	
	
	public NoticeVO selectNotice(int noNo);
	//리스트
	public List<NoticeVO> selectNoticeList();
	//추가
	public ServiceResult insertNotice(NoticeVO noticeVO);
	//수정
	public ServiceResult updateNotice(NoticeVO noticeVO);
	//삭제
	public ServiceResult deleteNotice(int noNo);
	
	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO);
	
	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO);
	
	
	
	
	
	
}