package com.stayc.infra.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardImageController {
	@Value("${file_upload_type}")
	private String fileUploadType;
	
	@Autowired
	BoardService service;
	
	// 이미지조회
	@RequestMapping(value = "/boardLoadImage")
	public List<BoardDto> boardLoadImage(BoardDto dto, BoardDto dto2) throws Exception {
		
		// 파일갯수확인
		dto2 = service.selectOneImageCount(dto);
		
		if(dto2 != null) {
			List<BoardDto> returnList;
			if(fileUploadType.equals("1")) { // nas
				returnList = service.getBase64ExternalImage(dto);				
			} else {
				returnList = service.selectListImages(dto);
			}
			
			return returnList;
		} else {
			return null;
		}
	}

}
