package com.stayc.infra.chattalk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

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
		String rtkSeq = String.valueOf(dto.getRtkSeq());
		ApiFuture<WriteResult> apiFuture = firestore.collection(roomSeq).document(rtkSeq).set(dto);

	    // ApiFuture 결과 처리
		 WriteResult writeResult = apiFuture.get(); // 블로킹 호출

        
        //DocumentReference documentReference = firestore.collection(roomSeq).document(rtkSeq);
        //System.out.println("Document added with ID: " + documentReference.getId());

    }

}
