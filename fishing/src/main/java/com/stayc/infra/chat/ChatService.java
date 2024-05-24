package com.stayc.infra.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

	@Autowired
	ChatDao dao;
	
	
	
	public List<ChatDto> roomList(ChatDto dto)
	{
		return dao.roomList(dto);
	}
	
	
	public ChatDto roomOne(ChatDto dto)
	{
		return dao.roomOne(dto);
	}
	
	public int chatroominst(ChatDto dto)
	{
		return dao.chatroominst(dto);
	}
	
	
	public int chatupdates(ChatDto dto)
	{
		return dao.chatupdates(dto);
	}
}
