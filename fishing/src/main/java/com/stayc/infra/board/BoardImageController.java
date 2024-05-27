package com.stayc.infra.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardImageController {
	@Autowired
	BoardService boardService;
	
	// 이미지조회
	@RequestMapping(value = "/boardLoadImage")
	public List<BoardDto> productLoadImage(BoardDto dto) throws Exception {
		List<BoardDto> resultList = boardService.selectListImages(dto);
		
		List<BoardDto> returnList = new ArrayList<>();
		for(BoardDto dto2 : resultList) {
			BoardDto Images = new BoardDto();
			Images.setXpath(dto2.getXpath());
			Images.setXdefaultNy(dto2.getXdefaultNy());
			Images.setXoriginalName(dto2.getXoriginalName());
			returnList.add(Images);
		}
		return returnList;
	}
}
