package com.stayc.infra.chat;

import java.util.List;

public interface ChatDao {
	
	
	
	public List<ChatDto> roomList(ChatDto dto);
	
	public ChatDto roomOne(ChatDto dto);
	
	
	public int chatroominst(ChatDto dto);
	public int chatupdates(ChatDto dto);

}
