package com.stayc.infra.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stayc.common.constants.Constants;

@Controller
public class ChatController {
	
	
	@Autowired
	ChatService service;
	
	@RequestMapping(value = "/chatroom")
	public String chatroom(ChatDto dto,Model model) {
		
		model.addAttribute("list", service.roomList(dto));
		
		return Constants.PATH_CHAT + "chatroom";
	}
	
	@RequestMapping(value = "/chatting")
	public String chatting(ChatDto dto, Model model) {
		
		model.addAttribute("seq", dto.getRomSeq());
		return Constants.PATH_CHAT + "chatting";
	}
	
	@RequestMapping(value = "/chatcreate")
	public String chatcreate() {
		
		
		
		return Constants.PATH_CHAT + "chatcreate";
	}
	
	@RequestMapping(value="/chatupdate")
	public String chatupdate(ChatDto dto, Model model)
	{
		model.addAttribute("item", service.roomOne(dto));
		return Constants.PATH_CHAT + "chatupdate";
	}
	
	
	@RequestMapping(value ="/chatupdates")
	public String chatupdates(ChatDto dto) throws Exception
	{
		return "redirect:/chatroom";
	}
	
	
	@RequestMapping(value = "/chatroominst")
	public String chatroominst(ChatDto dto)
	{
		service.chatroominst(dto);
	
		return "redirect:/chatroom";
	}
	
	
//	@RequestMapping(value="/chatupdate")
//	public String chatupdate(ChatDto dto)
//	{
//		return "redirect:chatupdate";
//	}


}
