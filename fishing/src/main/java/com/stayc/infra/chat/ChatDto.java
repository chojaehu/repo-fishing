package com.stayc.infra.chat;

import java.util.Date;

public class ChatDto {

	
	//romSeq, romDate, romTitle, romPersonnel, romNote
	
	private Integer romSeq;
	private Date romDate;
	private String romTitle;
	private Integer romPersonnel;
	private String romNote;
	private Integer romDelNy;
	
	
	
	
	private Integer mbrSeq;
	private String mbrName;
	
	
//	채팅 메세지
	private String rtkNote;
	private Integer rckDelNY;
	private Integer rckmanagerNY;
	
	private Integer currentStaff;
	
	
	
	
	public Integer getRckDelNY() {
		return rckDelNY;
	}

	public void setRckDelNY(Integer rckDelNY) {
		this.rckDelNY = rckDelNY;
	}

	public Integer getRomDelNy() {
		return romDelNy;
	}

	public void setRomDelNy(Integer romDelNy) {
		this.romDelNy = romDelNy;
	}

	public Integer getCurrentStaff() {
		return currentStaff;
	}

	public void setCurrentStaff(Integer currentStaff) {
		this.currentStaff = currentStaff;
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

	public String getMbrName() {
		return mbrName;
	}

	public void setMbrName(String mbrName) {
		this.mbrName = mbrName;
	}

	public Integer getRckmanagerNY() {
		return rckmanagerNY;
	}

	public void setRckmanagerNY(Integer rckmanagerNY) {
		this.rckmanagerNY = rckmanagerNY;
	}

	public String getRtkNote() {
		return rtkNote;
	}

	public void setRtkNote(String rtkNote) {
		this.rtkNote = rtkNote;
	}
	
	
}
