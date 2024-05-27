package com.stayc.infra.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stayc.common.constants.Constants;
import com.stayc.common.fileupload.FileUpLoadDto;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {
	@Value("${file_upload_type}")
	private String fileUploadType;
	
	@Autowired
	BoardService service;
	
	@Autowired
	BoardImageService imageService;
	
	// 게시판 리스트화면 호출
	@RequestMapping(value = "/boardList")
	public String boardList(@ModelAttribute("vo") BoardVo vo, Model model, BoardDto dto) throws Exception {
		List<BoardDto> list = service.selectList(vo);
		model.addAttribute("list", list);
		return Constants.PATH_BOARD + "boardList";
	}
	
	// 게시판 상세화면 호출
	@RequestMapping(value = "/boardForm")
	public String boardForm( Model model, BoardDto dto, BoardDto dto2) throws Exception {
		// 열람횟수 증가
		if(dto.getBrdSeq() != null) {
			service.updateOpen(dto);
			
			// 상세조회
			model.addAttribute("item", service.selectOne(dto));		
			
			// 댓글조회
			model.addAttribute("list", service.selectListReview(dto));
			
			model.addAttribute("uploadType", fileUploadType.toLowerCase());
			
			if(fileUploadType.toLowerCase().equals("nas")) {
				// NAS 파일
				/*
				String pathFile = null;
				if(service.getOs().equals("win")) {
					pathFile = Constants.UPLOADED_PATH_PREFIX_LOCAL+dto.getXuuidName();
				} else {
					pathFile = Constants.UPLOADED_PATH_PREFIX_LOCAL_MAC+dto.getXuuidName();
				}
				*/
				
				// 파일갯수확인
				dto2 = service.selectOneImageCount(dto);
				if(dto2 != null) {
					List<BoardDto> base64Image = imageService.getBase64ExternalImage(dto);
					model.addAttribute("imageUrl", base64Image);
				}			
			}			
		} else {
			// 상세조회
			model.addAttribute("item", null);
			// 댓글조회
			model.addAttribute("list", null);
		}

		return Constants.PATH_BOARD + "boardForm";
	}
	
	// 게시판 등록
	@RequestMapping(value = "/boardInsert")
	public String boardInsert(BoardDto dto, HttpSession httpSession, FileUpLoadDto fDto) throws Exception {
		dto.setMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
		dto.setBrdDefaultNy("1"); // 일반글
		service.insert(dto, fDto);
		return "redirect:/boardList";
	}
	
	// 게시판 댓글 등록
	@RequestMapping(value = "/boardInsertReview")
	public String boardInsertReview(BoardDto dto, HttpSession httpSession) throws Exception {
		dto.setMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
		service.insertReview(dto);
		return "redirect:/boardList";
	}	

	// 게시판 수정
	@RequestMapping(value = "/boardUpdate")
	public String boardUpdate(BoardDto dto, FileUpLoadDto fDto) throws Exception {
		service.update(dto, fDto);
		return "redirect:/boardList";
	}
}
