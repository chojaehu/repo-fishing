package com.stayc.infra.reservation;

import java.util.Date;

public class reservationDto {
//	장소(place)
	private String plcSeq;			// 순번
	private String plcName;			// 장소명
	private String plcAreaCd;		// 지역
	private int plcPersonnel;		// 좌석인원
	private Double plcAmount;		// 이용금액
	private int plcOperatingFrom;	// 이용시작시간
	private int plcOperatingTo;		// 이용종료시간
	private String plcNote;			// 비고
	private Integer plcDelNy;		// 삭제여부
	private Date plcRegDt;			// 등록일시
	private Date plcModDt;			// 수정일시
//	장소(place)	

//	예약(reservation)
	private String revSeq;			// 순번
	private Date revDate;			// 예약일자
	private String revPlaceCd;		// 낚시터종류
	private int revSeat;			// 좌석번호
	private Integer revDelNy;		// 삭제여부
	private Date revRegDt; 			// 등록일시
	private Date revModDt;			// 수정일시
	private String mbrSeq;			// 회원순번
//	예약(reservation)
	
	public String getPlcSeq() {
		return plcSeq;
	}
	public void setPlcSeq(String plcSeq) {
		this.plcSeq = plcSeq;
	}
	public String getPlcName() {
		return plcName;
	}
	public void setPlcName(String plcName) {
		this.plcName = plcName;
	}
	public String getPlcAreaCd() {
		return plcAreaCd;
	}
	public void setPlcAreaCd(String plcAreaCd) {
		this.plcAreaCd = plcAreaCd;
	}
	public int getPlcPersonnel() {
		return plcPersonnel;
	}
	public void setPlcPersonnel(int plcPersonnel) {
		this.plcPersonnel = plcPersonnel;
	}
	public Double getPlcAmount() {
		return plcAmount;
	}
	public void setPlcAmount(Double plcAmount) {
		this.plcAmount = plcAmount;
	}
	public int getPlcOperatingFrom() {
		return plcOperatingFrom;
	}
	public void setPlcOperatingFrom(int plcOperatingFrom) {
		this.plcOperatingFrom = plcOperatingFrom;
	}
	public int getPlcOperatingTo() {
		return plcOperatingTo;
	}
	public void setPlcOperatingTo(int plcOperatingTo) {
		this.plcOperatingTo = plcOperatingTo;
	}
	public String getPlcNote() {
		return plcNote;
	}
	public void setPlcNote(String plcNote) {
		this.plcNote = plcNote;
	}
	public Integer getPlcDelNy() {
		return plcDelNy;
	}
	public void setPlcDelNy(Integer plcDelNy) {
		this.plcDelNy = plcDelNy;
	}
	public Date getPlcRegDt() {
		return plcRegDt;
	}
	public void setPlcRegDt(Date plcRegDt) {
		this.plcRegDt = plcRegDt;
	}
	public Date getPlcModDt() {
		return plcModDt;
	}
	public void setPlcModDt(Date plcModDt) {
		this.plcModDt = plcModDt;
	}
	public String getRevSeq() {
		return revSeq;
	}
	public void setRevSeq(String revSeq) {
		this.revSeq = revSeq;
	}
	public Date getRevDate() {
		return revDate;
	}
	public void setRevDate(Date revDate) {
		this.revDate = revDate;
	}
	public String getRevPlaceCd() {
		return revPlaceCd;
	}
	public void setRevPlaceCd(String revPlaceCd) {
		this.revPlaceCd = revPlaceCd;
	}
	public int getRevSeat() {
		return revSeat;
	}
	public void setRevSeat(int revSeat) {
		this.revSeat = revSeat;
	}
	public Integer getRevDelNy() {
		return revDelNy;
	}
	public void setRevDelNy(Integer revDelNy) {
		this.revDelNy = revDelNy;
	}
	public Date getRevRegDt() {
		return revRegDt;
	}
	public void setRevRegDt(Date revRegDt) {
		this.revRegDt = revRegDt;
	}
	public Date getRevModDt() {
		return revModDt;
	}
	public void setRevModDt(Date revModDt) {
		this.revModDt = revModDt;
	}
	public String getMbrSeq() {
		return mbrSeq;
	}
	public void setMbrSeq(String mbrSeq) {
		this.mbrSeq = mbrSeq;
	}
}
