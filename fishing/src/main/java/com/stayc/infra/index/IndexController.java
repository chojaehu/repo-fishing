package com.stayc.infra.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stayc.common.constants.Constants;

import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {
	// 사용자메인
	@RequestMapping(value = "/index")
	public String indexUsr(Model model, HttpSession httpSession) throws Exception {
			
		return Constants.PATH_HOME + "index";
	}
	
}
