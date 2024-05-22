package com.stayc.infra.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stayc.common.constants.Constants;

@Controller
public class IndexController {
	// 사용자메인
	@RequestMapping(value = "/index")
	public String index() throws Exception {
		return Constants.PATH_HOME + "index";
	}
	
}
