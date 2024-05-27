package com.stayc.infra.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class reservationController {

	@Autowired
	reservationService service;
	
	@RequestMapping(value = "/seatList")
	public String seatList(reservationDto dto, Model model) throws Exception {
		
		model.addAttribute("list", service.selectList(dto));
		
		return "infra/reserve/seatList";
	}
}
