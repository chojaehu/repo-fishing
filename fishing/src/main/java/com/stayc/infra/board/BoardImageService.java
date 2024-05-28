package com.stayc.infra.board;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardImageService {
	@Autowired
	BoardService boardService;
	
    public List<BoardDto> getBase64ExternalImage(BoardDto dto) throws Exception {
    	List<BoardDto> returnList = new ArrayList<>();
    	
    	// 이미지파일조회
    	List<BoardDto> listDto = boardService.selectListImages(dto);
    	
    	for(BoardDto forDto : listDto) {
        	// 이미지 파일을 파일 시스템에서 로드
            File imgPath = new File(forDto.getXpathUpload());
            BufferedImage bufferedImage = ImageIO.read(imgPath);

            // 이미지를 byte 배열로 변환
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, forDto.getXext(), outputStream); // 이미지 확장자:forDto.getxExt()
            byte[] imageBytes = outputStream.toByteArray();

            BoardDto dto2 = new BoardDto();
            
            // byte 배열을 Base64 문자열로 인코딩하여 반환
            dto2.setXpathUpload(Base64.getEncoder().encodeToString(imageBytes));
            dto2.setXfileName(forDto.getXfileName());
            dto2.setXext(forDto.getXext().toLowerCase());
            
            returnList.add(dto2);		
    	}
        
        return returnList;
    }
}
