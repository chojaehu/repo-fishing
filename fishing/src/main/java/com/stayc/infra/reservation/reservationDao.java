package com.stayc.infra.reservation;

import java.util.List;

public interface reservationDao {

	public List<reservationDto> selectList(reservationDto dto);
}
