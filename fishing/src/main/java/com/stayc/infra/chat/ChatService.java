package com.stayc.infra.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

	@Autowired
	ChatDao dao;
	
	
//	채팅방목록 리스트
	public List<ChatDto> roomList(ChatDto dto)
	{
		return dao.roomList(dto);
	}
//	채팅방 참여인원 리스트
	public List<ChatDto> roomMember(ChatDto dto)
	{
		return dao.roomMember(dto);
	}
	
//	채빙방
	public ChatDto roomOne(ChatDto dto)
	{
		return dao.roomOne(dto);
	}
//	채팅방 생성
	public int chatroominst(ChatDto dto)
	{
		return dao.chatroominst(dto);
	}
	
//	채팅방 수정
	public int chatupdates(ChatDto dto)
	{
		return dao.chatupdates(dto);
	}
}
