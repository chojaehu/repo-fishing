package com.stayc.infra.reservation;

import java.util.Date;

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
		
		model.addAttribute("item", service.selectOne(dto));
		
		return Constants.PATH_RESERVE + "seat";
	}
//	결제페이지
	@RequestMapping(value = "/checkout")
	public String checkout(reservationDto dto, Model model) throws Exception {
		
		String plcSeq = dto.getPlcSeq();
		String plcName = dto.getPlcName();
		String plcTypeCd = dto.getPlcTypeCd();
	    Double plcAmount = dto.getPlcAmount();
	    int revSeat = dto.getRevSeat();
	    Date revDate = dto.getRevDate();
		
	    model.addAttribute("plcSeq", plcSeq);
	    model.addAttribute("plcName", plcName);
	    model.addAttribute("plcTypeCd", plcTypeCd);
	    model.addAttribute("plcAmount", plcAmount);
	    model.addAttribute("revSeat", revSeat);
	    model.addAttribute("revDate", revDate);
	    
		return Constants.PATH_RESERVE + "checkout";
	}
	
//	결제완료 insert
	@RequestMapping(value = "/insert")
	public String insert(reservationDto dto) {
		
		service.insert(dto);
		
		return "redirect:/index";
	}
}
