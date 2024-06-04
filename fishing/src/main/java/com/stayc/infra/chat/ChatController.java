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
		httpSession.setAttribute("sessMbrSeq",  3);
		//httpSession.invalidate();
		model.addAttribute("list", service.roomList(dto));
		model.addAttribute("mylist", service.myroomList(dto));
		
		
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
	
	
	
	
	
//	채팅방 수정
	@RequestMapping(value ="/chatupdates")
	public String chatupdates(ChatDto dto, RedirectAttributes redirectAttributes) throws Exception
	{
		service.chatupdates(dto);
		 redirectAttributes.addAttribute("romSeq", dto.getRomSeq()); 
		return "redirect:/chatting";
	}
	
//	채팅방 만들기
	@RequestMapping(value = "/chatroominst")
	public String chatroominst(ChatDto dto,HttpSession httpSession)
	{
		dto.setMbrSeq((Integer) httpSession.getAttribute("sessMbrSeq"));
		service.chatroominst(dto);
		service.chatroommanager(dto);
	
		return "redirect:/chatroom";
	}
	
//	채팅방 참여
	@RequestMapping(value = "/roomcheckinst")
	public String roomcheckinst(ChatDto dto,HttpSession httpSession)
	{
		dto.setMbrSeq((Integer) httpSession.getAttribute("sessMbrSeq"));
		service.roomcheckinst(dto);
		
		return "redirect:/chatting" + "?romSeq=" + dto.getRomSeq();
	}
	
	
	
	
	
//	채팅방 참여여부 검사
	@ResponseBody
	@RequestMapping(value = "/checkroom")
	public Map<String, Object> checkroom(Model model,HttpSession httpSession,ChatDto dto) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		dto.setMbrSeq((Integer) httpSession.getAttribute("sessMbrSeq"));
		System.out.println(dto.getMbrSeq()+"-----------------------------");
		if(dto.getMbrSeq() != null)
		{
			ChatDto rDto = service.roomcheckinOne(dto);
			ChatDto rDto2 = service.roomOne(dto);
			if(rDto != null && rDto2.getRomPersonnel() >= rDto2.getCurrentStaff())
			{
				returnMap.put("rt", "success");
			}
			else if(rDto2.getRomPersonnel() <= rDto2.getCurrentStaff())
			{
				returnMap.put("rt", "full");
			}
			else
			{
				returnMap.put("rt", "success2");
				
			}
		}
		else 
		{
			returnMap.put("rt", "false");
			
		}
	
		return returnMap;
	}
	
	


}
