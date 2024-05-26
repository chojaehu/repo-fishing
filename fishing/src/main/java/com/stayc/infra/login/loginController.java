package com.stayc.infra.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stayc.common.base.BaseController;
import com.stayc.common.constants.Constants;
import com.stayc.infra.member.MemberDto;
import com.stayc.infra.member.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class loginController extends BaseController {
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/login")
	public String login() {
		return Constants.PATH_LOGIN + "login";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession httpSession) {
		// 세션종료
		httpSession.invalidate(); 
		return Constants.PATH_HOME + "index";
	}	
	
	// 로그인 아이디, 비밀번호 확인용
	@ResponseBody
	@RequestMapping(value = "/loginConfirm")
	public Map<String, Object> memberSdmLoginConfirm(MemberDto dto, MemberDto rtDto, HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		String loginPassword = dto.getMbrPw();
		rtDto = memberService.selectOneLogin(dto);
		if(rtDto != null) {
			// 비밀번호 비교
			if(matchesBcrypt(loginPassword, rtDto.getMbrPw(), 10)) {
				returnMap.put("rt", "success");
				
				httpSession.setMaxInactiveInterval(60 * Constants.SESSION_MINUTE_SDM); // 60second * 30 = 30minute
				httpSession.setAttribute("sessMbrSeq",   rtDto.getMbrSeq());
				httpSession.setAttribute("sessMbrEmail", rtDto.getMbrEmail());
				httpSession.setAttribute("sessMbrName",  rtDto.getMbrName());
			} else {
				returnMap.put("rt", "password");
			}
		} else {
			returnMap.put("rt", "id");
		}

		return returnMap;
	}
	
	// 로그인 확인
	@ResponseBody
	@RequestMapping(value = "/loginCheck")
	public Map<String, Object> loginCheck(HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String check = (String) httpSession.getAttribute("sessMbrSeq");
		
		System.out.println("======================================================check: "+check);
		if(check == null || check == "") {
			returnMap.put("rt", "login");
		} else {
			returnMap.put("rt", "success");			
		}
		
		return returnMap;
	}
}
