package com.stayc.infra.reservation;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stayc.common.constants.Constants;
import com.stayc.common.fileupload.FileUpLoadDto;
import com.stayc.common.util.UtilFunction;

import jakarta.servlet.http.HttpSession;
@Controller
public class reservationController {

	@Autowired
	reservationService service;
//	예약장소 리스트
	@RequestMapping(value = "/seatList")
	public String seatList(@ModelAttribute("vo") reservationVo vo, Model model) throws Exception {
		UtilFunction.setSearch(vo);
		
		int rowCount = service.selectOneCount(vo);
		
		if(rowCount > 0) {			
			vo.setPagingVo(rowCount);		
			model.addAttribute("list", service.selectList(vo));
		};
		
		return Constants.PATH_RESERVE + "seatList";
	}

//	페이징
	@RequestMapping(value = "/seatListPaging")
	public String boardListPaging(@ModelAttribute("vo") reservationVo vo, Model model) throws Exception {
		int rowCount = service.selectOneCount(vo);
		
		if(rowCount > 0) {			
			vo.setPagingVo(rowCount);
			model.addAttribute("list", service.selectList(vo));
		};
		
		return Constants.PATH_RESERVE + "seatListAjax";
	}	
	
//	예약상세 리스트
	@RequestMapping(value = "/seat")
	public String seat(reservationDto dto, Model model) throws Exception {
		
		model.addAttribute("reservDD", dto.getReservDD());
		model.addAttribute("reservMM", dto.getReservMM());
		model.addAttribute("reservYY", dto.getReservYY());
		model.addAttribute("item", service.selectOne(dto));
		model.addAttribute("fisList", service.fisList(dto));
		model.addAttribute("prpList", service.prpList(dto));
		
		return Constants.PATH_RESERVE + "seat";
	}
	
//	결제페이지
	@RequestMapping(value = "/checkout")
	public String checkout(reservationDto dto, Model model) throws Exception {
	    // 다른 모든 속성을 모델에 추가
	    model.addAttribute("plcSeq", dto.getPlcSeq());
	    model.addAttribute("plcName", dto.getPlcName());
	    model.addAttribute("plcTypeCd", dto.getPlcTypeCd());
	    model.addAttribute("plcAmount", dto.getPlcAmount());
	    model.addAttribute("revSeat", dto.getRevSeat());
	    model.addAttribute("reservDD", dto.getReservDD());
	    model.addAttribute("reservMM", dto.getReservMM());
	    model.addAttribute("reservYY", dto.getReservYY());

	    // reservYY, reservMM, reservDD를 사용하여 LocalDate 객체 생성
	    String year = dto.getReservYY();
	    String month = dto.getReservMM();
	    String day = dto.getReservDD();

	    // null 체크 후 String을 int로 변환
	    int yearInt = (year != null && !year.isEmpty()) ? Integer.parseInt(year) : 0;
	    int monthInt = (month != null && !month.isEmpty()) ? Integer.parseInt(month) : 1; // 기본값으로 1월 설정
	    int dayInt = (day != null && !day.isEmpty()) ? Integer.parseInt(day) : 1; // 기본값으로 1일 설정

	    // LocalDate 객체 생성
	    LocalDate reservationDate = LocalDate.of(yearInt, monthInt, dayInt);

	    // LocalDate 객체를 모델에 추가
	    model.addAttribute("reservationDate", reservationDate);

	    return Constants.PATH_RESERVE + "checkout";
	}

//	예약상세 리스트
	@RequestMapping(value = "/upload")
	public String upload(reservationDto dto, Model model) throws Exception {
		
		return Constants.PATH_RESERVE + "upload";
	}
	
//	결제완료 insert
	@RequestMapping(value = "/insert")
	public String insert(reservationDto dto,HttpSession httpSession) throws Exception {

		dto.setMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
		
		service.insert(dto);
		service.payInsert(dto);
		
		return "redirect:/index";
	}
//	이미지 업로드
	@RequestMapping(value = "/fileUploadsS3")
	public String fileUploadsS3(FileUpLoadDto dto, FileUpLoadDto fDto,reservationDto dto2) throws Exception{
		
		service.fileUploadsS3(dto2.getUploadFiles(), dto, fDto);
		
		return "redirect:/upload";
		
	}
}