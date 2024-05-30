package com.stayc.infra.chat;

import java.util.Date;

public class ChatDto {

	
	//romSeq, romDate, romTitle, romPersonnel, romNote
	
	private Integer romSeq;
	private Date romDate;
	private String romTitle;
	private Integer romPersonnel;
	private String romNote;
	
	
	
	private Integer mbrSeq;
	private String mbrName;
	
//	채팅 메세지
	private String rtkNote;
	
	
	
	public String getRtkNote() {
		return rtkNote;
	}
	public void setRtkNote(String rtkNote) {
		this.rtkNote = rtkNote;
	}
	public String getMbrName() {
		return mbrName;
	}
	public void setMbrName(String mbrName) {
		this.mbrName = mbrName;
	}
	public Integer getRomSeq() {
		return romSeq;
	}
	public void setRomSeq(Integer romSeq) {
		this.romSeq = romSeq;
	}
	public Date getRomDate() {
		return romDate;
	}
	public void setRomDate(Date romDate) {
		this.romDate = romDate;
	}
	public String getRomTitle() {
		return romTitle;
	}
	public void setRomTitle(String romTitle) {
		this.romTitle = romTitle;
	}
	public Integer getRomPersonnel() {
		return romPersonnel;
	}
	public void setRomPersonnel(Integer romPersonnel) {
		this.romPersonnel = romPersonnel;
	}
	public String getRomNote() {
		return romNote;
	}
	public void setRomNote(String romNote) {
		this.romNote = romNote;
	}
	public Integer getMbrSeq() {
		return mbrSeq;
	}
	public void setMbrSeq(Integer mbrSeq) {
		this.mbrSeq = mbrSeq;
	}
	
}
