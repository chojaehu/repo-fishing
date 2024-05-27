package com.stayc.infra.reservation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class reservationService {

	@Autowired
	reservationDao dao;
	
	public List<reservationDto> selectList(reservationDto dto) {
		return dao.selectList(dto);
	}
}
