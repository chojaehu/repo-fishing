package com.stayc.infra.chat;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stayc.common.constants.Constants;
import com.stayc.infra.member.MemberDto;

import jakarta.servlet.http.HttpSession;

@Controller
public class ChatController {
	
	
	@Autowired
	ChatService service;
	
	@RequestMapping(value = "/chatroom")
	public String chatroom(ChatDto dto ,Model model,HttpSession httpSession) {
		
		model.addAttribute("list", service.roomList(dto));
		
		
		return Constants.PATH_CHAT + "chatroom";
	}
	
	@RequestMapping(value = "/chatting")
	public String chatting(@RequestParam("romSeq") String romSeq, ChatDto dto, Model model) {
		dto.setRomSeq(Integer.parseInt(romSeq));
		model.addAttribute("item", service.roomOne(dto));
		model.addAttribute("mbrlist", service.roomMember(dto));
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
	public String chatupdates(ChatDto dto, RedirectAttributes redirectAttributes) throws Exception
	{
		service.chatupdates(dto);
		 redirectAttributes.addAttribute("romSeq", dto.getRomSeq()); 
		return "redirect:/chatting";
	}
	
	
	@RequestMapping(value = "/chatroominst")
	public String chatroominst(ChatDto dto)
	{
		service.chatroominst(dto);
		
	
		return "redirect:/chatroom";
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkroom")
	public Map<String, Object> checkroom(Model model,HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("rt", "success");
		
	
		return returnMap;
	}
	
	


}
