package com.stayc.infra.chat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class ChatService {

	@Autowired
	ChatDao dao;
	
	@Autowired
    private Firestore firestore;
	
	
//	채팅방목록 리스트
	public List<ChatDto> roomList(ChatVo vo)
	{
		return dao.roomList(vo);
	}
	public int roomCount()
	{
		return dao.roomCount();
	}
//	참여한 채팅방 리스트
	public List<ChatDto> myroomList(ChatDto dto)
	{
		return dao.myroomList(dto);
	}
//	채팅방 참여인원 리스트
	public List<ChatDto> roomMember(ChatDto dto)
	{
		return dao.roomMember(dto);
	}
	
	
//	채팅방 참여 확인

	public ChatDto roomcheckinOne(ChatDto dto)
	{
		return dao.roomcheckinOne(dto);
	}
	
//	채빙방
	public ChatDto roomOne(ChatDto dto)
	{
		return dao.roomOne(dto);
	}
	
	
//	채팅방 가입
	public int roomcheckinst(ChatDto dto)
	{
		return dao.roomcheckinst(dto);
	}
	
//	채팅방 생성
	public int chatroominst(ChatDto dto)
	{
		dao.chatroominst(dto);
//		String roomSeq = String.valueOf(dto.getRomSeq());
//		String memberSeq = String.valueOf(dto.getMbrSeq());
//		CollectionReference apiFuture = firestore.collection(roomSeq);
//		DocumentReference documentReference = apiFuture.document(memberSeq);
		
		return 1; 
	}
//	채팅방 개설시 방장 인설트
	public int chatroommanager(ChatDto dto )
	{
		return dao.chatroommanager(dto);
	}
	
//	채팅방 수정
	public int chatupdates(ChatDto dto)
	{
		return dao.chatupdates(dto);
	}
	
//	채팅방 삭제 및 참여인원 삭제
	public int roomdelete(ChatDto dto)
	{
		//채팅방 인원 삭제
		dao.roommemberdelete(dto);
		//채팅방 삭제
		dao.roomdelete(dto);
		return 1;
	}
	
//	채팅방 나가기 
	public int memberdelete(ChatDto dto)
	{
		return dao.memberdelete(dto);
	}
	
	

}
