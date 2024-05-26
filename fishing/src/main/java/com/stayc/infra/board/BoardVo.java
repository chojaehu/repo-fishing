package com.stayc.infra.board;

import com.stayc.common.base.BaseVo;

public class BoardVo extends BaseVo {
	// 조회용 게시판 순번
	private String shBrdSeq;
	// 조회용 게시판 타이틀
	private String shBrdTitle;

	public String getShBrdSeq() {
		return shBrdSeq;
	}

	public void setShBrdSeq(String shBrdSeq) {
		this.shBrdSeq = shBrdSeq;
	}

	public String getShBrdTitle() {
		return shBrdTitle;
	}

	public void setShBrdTitle(String shBrdTitle) {
		this.shBrdTitle = shBrdTitle;
	}
	
}
