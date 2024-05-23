package com.stayc.infra.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stayc.common.constants.Constants;

@Controller
public class ChatController {
	
	@RequestMapping(value = "/chatroom")
	public String chatroom() {
		
		
		
		return Constants.PATH_CHAT + "chatroom";
	}
	
	@RequestMapping(value = "/chatting")
	public String chatting() {
		
		
		
		return Constants.PATH_CHAT + "chatting";
	}
	@RequestMapping(value = "/chatcreate")
	public String chatcreate() {
		
		
		
		return Constants.PATH_CHAT + "chatcreate";
	}


}
