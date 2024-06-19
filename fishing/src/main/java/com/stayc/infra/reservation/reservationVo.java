package com.stayc.infra.reservation;

import com.stayc.common.base.BaseVo;

public class reservationVo extends BaseVo {
	private String shTypeCd = "2";
	private String mbrSeq;

	public String getShTypeCd() {
		return shTypeCd;
	}

	public void setShTypeCd(String shTypeCd) {
		this.shTypeCd = shTypeCd;
	}

	public String getMbrSeq() {
		return mbrSeq;
	}

	public void setMbrSeq(String mbrSeq) {
		this.mbrSeq = mbrSeq;
	}
	
}
