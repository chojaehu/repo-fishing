package com.stayc.infra.chat;

import java.util.List;

public interface ChatDao {
	
	
//	채팅방목록 리스트
	public List<ChatDto> roomList(ChatVo vo);
	
//	채팅방 카운트 
	public int roomCount();
//	참여한 채팅방 리스트
	public List<ChatDto> myroomList(ChatDto dto);
//	채팅방 참여 인원 리스트
	public List<ChatDto> roomMember(ChatDto dto);
	
//	채팅방 참여 확인
	public ChatDto roomcheckinOne(ChatDto dto);
//	채빙방
	public ChatDto roomOne(ChatDto dto);
	
	
//	채팅방 가입
	public int roomcheckinst(ChatDto dto);
//	채팅방 생성
	public int chatroominst(ChatDto dto);
	
//	채팅방 개설시 방장 인설트
	public int chatroommanager(ChatDto dto );
	
//	채팅방 수정
	public int chatupdates(ChatDto dto);
	
//	채팅방 삭제
	public int roomdelete(ChatDto dto);
	
//	채팅방 참여원 삭제
	public int roommemberdelete(ChatDto dto);
	
//	채팅방 나가기
	public int memberdelete(ChatDto dto);
	

}
