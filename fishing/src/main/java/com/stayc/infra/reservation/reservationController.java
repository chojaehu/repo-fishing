package com.stayc.infra.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class reservationController {

	@Autowired
	reservationService service;
//	예약장소 리스트
	@RequestMapping(value = "/seatList")
	public String seatList(reservationDto dto, Model model) throws Exception {
		
		model.addAttribute("list", service.selectList(dto));
		
		return "infra/reserve/seatList";
	}
//	예약상세 리스트
	@RequestMapping(value = "/seat")
	public String seat(reservationDto dto, Model model) throws Exception {
		
		model.addAttribute("item", service.selectOne(dto));
		
		return "infra/reserve/seat";
	}
	
	@RequestMapping(value = "/checkout")
	public String checkout(reservationDto dto, Model model) throws Exception {
		
		
		return "infra/reserve/checkout";
	}
}
