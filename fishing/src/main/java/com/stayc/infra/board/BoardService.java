package com.stayc.infra.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stayc.common.base.BaseService;
import com.stayc.common.fileupload.FileUpLoadDto;

@Service
public class BoardService {
	@Value("${file_upload_type}")
	private String fileUploadType;
	
	@Autowired
	BaseService baseService;
	
	@Autowired
	BoardDao dao;
	
	// 전체조회
	public List<BoardDto> selectList(BoardVo vo) throws Exception {
		return dao.selectList(vo);
	};
	
	// 단일 조회
	public BoardDto selectOne(BoardDto dto) throws Exception {
		return dao.selectOne(dto);
	};
	
	// 등록
	public int insert(BoardDto dto, FileUpLoadDto fDto) throws Exception {
		dao.insert(dto);
		fDto.setCategory("1"); // 1: 갤러리 2: 홍보
		fDto.setType("1"); // 1: t_borad file 2: t_board_reply file
		fDto.setPseq(dto.getBrdSeq());

		if(fileUploadType.toLowerCase().equals("aws")) {
			// 파일첨부:4개파일을 멀티로 선택했을 경우
			baseService.fileUploadsS3(dto.getUploadFiles(), fDto, fDto);		
		} else if(fileUploadType.toLowerCase().equals("nas")) {
			// NAS 파일첨부
			baseService.fileUploadsNas(dto.getUploadFiles(), fDto, fDto);				
		}
		
		return 1;
	};
	
	// 수정
	public int update(BoardDto dto, FileUpLoadDto fDto) throws Exception {
		dao.update(dto);
		
		fDto.setCategory("1"); // 1: 갤러리 2: 홍보
		fDto.setType("1"); // 1: t_borad file 2: t_board_reply file
		fDto.setPseq(dto.getBrdSeq());

		if(fileUploadType.toLowerCase().equals("aws")) {
			// AWS S3 파일첨부
			baseService.fileUploadsS3(dto.getUploadFiles(), fDto, fDto);		
		} else if(fileUploadType.toLowerCase().equals("nas")) {
			// NAS 파일첨부
			baseService.fileUploadsNas(dto.getUploadFiles(), fDto, fDto);			
		}
		
		return 1;
	};
	
	// 수정 열람횟수
	public int updateOpen(BoardDto dto) throws Exception {
		return dao.updateOpen(dto);
	};
	
	// 삭제
	public int delete(BoardDto dto) throws Exception {
		return dao.delete(dto);
	};
	
	// 댓글조회
	public List<BoardDto> selectListReview(BoardDto dto) throws Exception {
		return dao.selectListReview(dto);
	};
	
	// 댓글등록
	public int insertReview(BoardDto dto) throws Exception {
		return dao.insertReview(dto);
	};
	
	//이미지조회
	public List<BoardDto> selectListImages(BoardDto dto) throws Exception {
		return dao.selectListImages(dto);
	};
}
