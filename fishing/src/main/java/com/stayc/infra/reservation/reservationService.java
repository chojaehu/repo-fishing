package com.stayc.infra.reservation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class reservationService {

	@Autowired
	reservationDao dao;
	
//	예약리스트
	public List<reservationDto> selectList(reservationVo vo) {
		return dao.selectList(vo);
	}
	
//	예약상세 리스트
	public reservationDto selectOne(reservationDto dto) {
		return dao.selectOne(dto);
	}
	
//	결제완료 insert
	public int insert(reservationDto dto) {
		return dao.insert(dto);
	}
	
//	어종 리스트
	public List<reservationDto> fisList(reservationDto dto) {
		return dao.fisList(dto);
	}
	
//	채집도구 리스트
	public List<reservationDto> prpList(reservationDto dto) {
		return dao.prpList(dto);
	}
	
	// 낚시터 갯수
	public int selectOneCount(reservationVo vo) {
		return dao.selectOneCount(vo);
	};
}
