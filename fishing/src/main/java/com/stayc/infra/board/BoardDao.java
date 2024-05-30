package com.stayc.infra.board;

import java.util.List;

public interface BoardDao {
	// 전체조회
	public List<BoardDto> selectList(BoardVo vo);
	// 전체자료건수
	public int getCount();
	// 단일조회
	public BoardDto selectOne(BoardDto dto);
	// 등록
	public int insert(BoardDto dto);
	// 수정
	public int update(BoardDto dto);
	// 수정 열람횟수
	public int updateOpen(BoardDto dto);
	// 삭제
	public int delete(BoardDto dto);
	// 댓글조회
	public List<BoardDto> selectListReview(BoardDto dto);
	// 댓글등록
	public int insertReview(BoardDto dto);
	//이미지갯수
	public BoardDto selectOneImageCount(BoardDto dto);
	//이미지조회
	public List<BoardDto> selectListImages(BoardDto dto);
}
