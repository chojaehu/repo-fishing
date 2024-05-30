package com.stayc.infra.board;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

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
	
	// 전체자료건수
	public int getCount() {
		return dao.getCount();
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

		if(fileUploadType.equals("0")) { //aws
			// 파일첨부:4개파일을 멀티로 선택했을 경우
			baseService.fileUploadsS3(dto.getUploadFiles(), fDto, fDto);		
		} else if(fileUploadType.equals("1")) { //nas
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

		if(fileUploadType.equals("0")) { //aws
			// AWS S3 파일첨부
			baseService.fileUploadsS3(dto.getUploadFiles(), fDto, fDto);		
		} else if(fileUploadType.equals("1")) { //nas
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
	
	//이미지갯수
	public BoardDto selectOneImageCount(BoardDto dto) throws Exception {
		dto.setXstorage(fileUploadType);
		return dao.selectOneImageCount(dto);
	};
	
	//이미지조회
	public List<BoardDto> selectListImages(BoardDto dto) throws Exception {
		dto.setXstorage(fileUploadType);
		return dao.selectListImages(dto);
	};
	
	public List<BoardDto> getBase64ExternalImage(BoardDto dto) throws Exception {
    	List<BoardDto> returnList = new ArrayList<>();
    	
    	// 이미지 확장자
    	String ext = null;
    	
    	// 이미지파일조회
    	List<BoardDto> listDto = selectListImages(dto);
    	
    	for(BoardDto forDto : listDto) {
    		// 이미지 확장자
    		ext = forDto.getXext().toLowerCase();
    		
        	// 이미지 파일을 파일 시스템에서 로드
            File imgPath = new File(forDto.getXpathUpload());
            BufferedImage bufferedImage = ImageIO.read(imgPath);

            // 이미지를 byte 배열로 변환
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, ext, outputStream);
            byte[] imageBytes = outputStream.toByteArray();

            BoardDto dto2 = new BoardDto();
            
            // byte 배열을 Base64 문자열로 인코딩하여 반환
            dto2.setXpathUpload(Base64.getEncoder().encodeToString(imageBytes));
            dto2.setXfileName(forDto.getXfileName());
            dto2.setXext(ext);
            
            returnList.add(dto2);		
    	}
        
        return returnList;
    }
}
