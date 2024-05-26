package com.stayc.infra.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	@Autowired
	BoardDao dao;
	
	// 전체조회
	public List<BoardDto> selectList(BoardVo vo) {
		return dao.selectList(vo);
	};
	
	// 단일 조회
	public BoardDto selectOne(BoardDto dto) {
		return dao.selectOne(dto);
	};
	
	// 등록
	public int insert(BoardDto dto) {
		return dao.insert(dto);
	};
	
	// 수정
	public int update(BoardDto dto) {
		return dao.update(dto);
	};
	
	// 수정 열람횟수
	public int updateOpen(BoardDto dto) {
		return dao.updateOpen(dto);
	};
	
	// 삭제
	public int delete(BoardDto dto) {
		return dao.delete(dto);
	};
	
	// 댓글조회
	public List<BoardDto> selectListReview(BoardDto dto) {
		return dao.selectListReview(dto);
	};
	
	// 댓글등록
	public int insertReview(BoardDto dto) {
		return dao.insertReview(dto);
	};
}
