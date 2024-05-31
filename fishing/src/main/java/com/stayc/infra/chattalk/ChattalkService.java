package com.stayc.infra.chattalk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;

@Service
public class ChattalkService {
	
	@Autowired
    private Firestore firestore;
	
	
	@Autowired
	ChattalkDao dao;
	
//	채팅내용 저장
	public int talkinsert(ChattalkDto dto) 
	{
		return dao.talkinsert(dto);
	}
	
	
//	파이어 베이스 인설트 테스트
	public void chattinginst(ChattalkDto dto ) throws Exception {
		String roomSeq = String.valueOf(dto.getRomSeq());
		//ApiFuture<WriteResult> apiFuture = firestore.collection("fishing").document("user_3").set(dto);
		ApiFuture<DocumentReference> apiFuture = firestore.collection(roomSeq).document(dto.getMbrSeq()).collection("messages").add(dto);

	    // ApiFuture 결과 처리
	    DocumentReference documentReference = apiFuture.get(); // 블로킹 호출
	    System.out.println("Document added with ID: " + documentReference.getId());

    }

}
