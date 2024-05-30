package com.stayc.infra.board;

import java.util.Date;

import com.stayc.common.base.BaseDto;

public class BoardDto extends BaseDto {
	// 게시판
	private String  brdSeq;       // 순번
	private String  brdDefaultNy; // 0:공지, 1:일반
	private Date    brdDate;      // 게시일시
	private String  mbrSeq;       // 회원순번
	private String  brdTitle;     // 제목
	private String  brdNote;      // 내용
	private Integer brdDelNy;     // 0:미삭제, 1:삭제
	private Integer brdOpen;      // 열람횟수
	private Date    brdRegDt;     // 등록일시
	private Date    brdModDt;     // 수정일시
	
	// 게시판 리뷰
	private String brvSeq;   // 리뷰순번
	private Date   brvDate;  // 작성일자
	private String brvNote;  // 리뷰내용
	private String brvDelNy; // 삭제여부
	private Date   brvRegDt; // 등록일시
	private Date   brvModDt; // 수정일시
	
	// 추가필드
	private Integer xCount;
	private String  xrowNum;      // 레코드순번
	private String  xmbrName;     // 회원성명
	
	// 이미지필드
	private Integer xrowSeq;
	private String  xfileName;
	private String  xpathUpload;
	private String  xpathLoad;
	private String  xuuidName;
	private String  xext;
	private String  xdefaultNy;
	private String  xstorage;
	
	public String getBrdSeq() {
		return brdSeq;
	}
	public void setBrdSeq(String brdSeq) {
		this.brdSeq = brdSeq;
	}
	public String getBrdDefaultNy() {
		return brdDefaultNy;
	}
	public void setBrdDefaultNy(String brdDefaultNy) {
		this.brdDefaultNy = brdDefaultNy;
	}
	public Date getBrdDate() {
		return brdDate;
	}
	public void setBrdDate(Date brdDate) {
		this.brdDate = brdDate;
	}
	public String getMbrSeq() {
		return mbrSeq;
	}
	public void setMbrSeq(String mbrSeq) {
		this.mbrSeq = mbrSeq;
	}
	public String getBrdTitle() {
		return brdTitle;
	}
	public void setBrdTitle(String brdTitle) {
		this.brdTitle = brdTitle;
	}
	public String getBrdNote() {
		return brdNote;
	}
	public void setBrdNote(String brdNote) {
		this.brdNote = brdNote;
	}
	public Integer getBrdDelNy() {
		return brdDelNy;
	}
	public void setBrdDelNy(Integer brdDelNy) {
		this.brdDelNy = brdDelNy;
	}
	public Date getBrdRegDt() {
		return brdRegDt;
	}
	public void setBrdRegDt(Date brdRegDt) {
		this.brdRegDt = brdRegDt;
	}
	public Date getBrdModDt() {
		return brdModDt;
	}
	public void setBrdModDt(Date brdModDt) {
		this.brdModDt = brdModDt;
	}
	public String getXmbrName() {
		return xmbrName;
	}
	public void setXmbrName(String xmbrName) {
		this.xmbrName = xmbrName;
	}
	public Integer getBrdOpen() {
		return brdOpen;
	}
	public void setBrdOpen(Integer brdOpen) {
		this.brdOpen = brdOpen;
	}
	public String getXrowNum() {
		return xrowNum;
	}
	public void setXrowNum(String xrowNum) {
		this.xrowNum = xrowNum;
	}
	public String getBrvSeq() {
		return brvSeq;
	}
	public void setBrvSeq(String brvSeq) {
		this.brvSeq = brvSeq;
	}
	public Date getBrvDate() {
		return brvDate;
	}
	public void setBrvDate(Date brvDate) {
		this.brvDate = brvDate;
	}
	public String getBrvNote() {
		return brvNote;
	}
	public void setBrvNote(String brvNote) {
		this.brvNote = brvNote;
	}
	public String getBrvDelNy() {
		return brvDelNy;
	}
	public void setBrvDelNy(String brvDelNy) {
		this.brvDelNy = brvDelNy;
	}
	public Date getBrvRegDt() {
		return brvRegDt;
	}
	public void setBrvRegDt(Date brvRegDt) {
		this.brvRegDt = brvRegDt;
	}
	public Date getBrvModDt() {
		return brvModDt;
	}
	public void setBrvModDt(Date brvModDt) {
		this.brvModDt = brvModDt;
	}
	public Integer getXrowSeq() {
		return xrowSeq;
	}
	public void setXrowSeq(Integer xrowSeq) {
		this.xrowSeq = xrowSeq;
	}
	public String getXfileName() {
		return xfileName;
	}
	public void setXfileName(String xfileName) {
		this.xfileName = xfileName;
	}
	public String getXpathUpload() {
		return xpathUpload;
	}
	public void setXpathUpload(String xpathUpload) {
		this.xpathUpload = xpathUpload;
	}
	public String getXpathLoad() {
		return xpathLoad;
	}
	public void setXpathLoad(String xpathLoad) {
		this.xpathLoad = xpathLoad;
	}
	public String getXuuidName() {
		return xuuidName;
	}
	public void setXuuidName(String xuuidName) {
		this.xuuidName = xuuidName;
	}
	public Integer getxCount() {
		return xCount;
	}
	public void setxCount(Integer xCount) {
		this.xCount = xCount;
	}
	public String getXdefaultNy() {
		return xdefaultNy;
	}
	public void setXdefaultNy(String xdefaultNy) {
		this.xdefaultNy = xdefaultNy;
	}
	public String getXext() {
		return xext;
	}
	public void setXext(String xext) {
		this.xext = xext;
	}
	public String getXstorage() {
		return xstorage;
	}
	public void setXstorage(String xstorage) {
		this.xstorage = xstorage;
	}
	
}
