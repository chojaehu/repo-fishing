package com.stayc.infra.chattalk;

import java.util.Date;

public class ChattalkDto {
	
	
	//rtkSeq, rtkDate, rtkNote, mbrSeq, romSeq
	
	private Integer rtkSeq;
	private Date rtkDate;
	private String rtkNote;
	private String mbrSeq;
	private Integer romSeq;
	public Integer getRtkSeq() {
		return rtkSeq;
	}
	public void setRtkSeq(Integer rtkSeq) {
		this.rtkSeq = rtkSeq;
	}
	public Date getRtkDate() {
		return rtkDate;
	}
	public void setRtkDate(Date rtkDate) {
		this.rtkDate = rtkDate;
	}
	public String getRtkNote() {
		return rtkNote;
	}
	public void setRtkNote(String rtkNote) {
		this.rtkNote = rtkNote;
	}
	public String getMbrSeq() {
		return mbrSeq;
	}
	public void setMbrSeq(String mbrSeq) {
		this.mbrSeq = mbrSeq;
	}
	public Integer getRomSeq() {
		return romSeq;
	}
	public void setRomSeq(Integer romSeq) {
		this.romSeq = romSeq;
	}
	
	
	
}
