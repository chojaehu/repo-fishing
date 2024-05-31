package com.stayc.infra.chattalk;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;


@Controller
public class ChattalkController {
	
	
	@Autowired
	ChattalkService service;
	
	
	@ResponseBody
	@RequestMapping(value = "/chattinginsert")
	public Map<String, Object> chattinginsert(ChattalkDto dto,HttpSession httpSession)throws Exception
	{
		Map<String, Object> returnMap = new HashMap<String, Object>();
		dto.setMbrSeq(String.valueOf((Integer) httpSession.getAttribute("sessMbrSeq")));
		service.talkinsert(dto);
		service.chattinginst(dto);
		
		returnMap.put("rt", "success");
		return returnMap;
	}
}
