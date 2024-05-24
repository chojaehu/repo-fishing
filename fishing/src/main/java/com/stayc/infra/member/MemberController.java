package com.stayc.infra.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stayc.common.base.BaseController;
import com.stayc.common.constants.Constants;

@Controller
public class MemberController extends BaseController {
	@Autowired
	MemberService service;
	
	@RequestMapping(value = "/member")
	public String member() throws Exception {
		return Constants.PATH_MEMBER + "member";
	}
	
	@ResponseBody
	@RequestMapping(value = "/memberInsert")
	public Map<String, Object> memberInsert(MemberDto dto, MemberDto rtDto) throws Exception {
		Map<String, Object> returnMap = new HashMap<>();
		
		// 암호화
		dto.setMbrPw(encodeBcrypt(dto.getMbrPw(), 10));
		rtDto.setMbrPw(dto.getMbrPw());
		
		rtDto = service.selectOneLogin(dto);
		
		// 이메일 존재확인
		if(rtDto != null) {
			returnMap.put("rt", "email");
		} else {
			service.insert(dto);
			returnMap.put("rt", "success");
		}
		
		return returnMap;
	}
}
