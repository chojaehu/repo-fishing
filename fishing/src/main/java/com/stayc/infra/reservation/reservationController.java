package com.stayc.infra.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stayc.common.constants.Constants;

@Controller
public class reservationController {

	@Autowired
	reservationService service;
//	예약장소 리스트
	@RequestMapping(value = "/seatList")
	public String seatList(reservationDto dto, Model model) throws Exception {
		
		model.addAttribute("list", service.selectList(dto));
		
		return Constants.PATH_RESERVE + "seatList";
	}
//	예약상세 리스트
	@RequestMapping(value = "/seat")
	public String seat(reservationDto dto, Model model) throws Exception {
		
		model.addAttribute("reservDD", dto.getReservDD());
		model.addAttribute("reservMM", dto.getReservMM());
		model.addAttribute("reservYY", dto.getReservYY());
		model.addAttribute("item", service.selectOne(dto));
		
		return Constants.PATH_RESERVE + "seat";
	}
//	결제페이지
	@RequestMapping(value = "/checkout")
	public String checkout(reservationDto dto, Model model) throws Exception {
		
	    model.addAttribute("plcSeq", dto.getPlcSeq());
	    model.addAttribute("plcName", dto.getPlcName());
	    model.addAttribute("plcTypeCd", dto.getPlcTypeCd());
	    model.addAttribute("plcAmount", dto.getPlcAmount());
	    model.addAttribute("revSeat", dto.getRevSeat());
	    model.addAttribute("reservDD", dto.getReservDD());
		model.addAttribute("reservMM", dto.getReservMM());
		model.addAttribute("reservYY", dto.getReservYY());
	    
		return Constants.PATH_RESERVE + "checkout";
	}
	
//	결제완료 insert
	@RequestMapping(value = "/insert")
	public String insert(reservationDto dto) {
		
		service.insert(dto);
		
		return "redirect:/index";
	}
}
