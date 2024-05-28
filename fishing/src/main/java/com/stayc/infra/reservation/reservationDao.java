package com.stayc.infra.reservation;

import java.util.List;

public interface reservationDao {
//	예약장소 리스트
	public List<reservationDto> selectList(reservationDto dto);
	
//	예약상세 리스트
	public reservationDto selectOne(reservationDto dto);
}
