package com.stayc.infra.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.stayc.infra.codegroup.CodeGroupService;

@Controller
public class CodeController {

	@Autowired
	CodeService service;
	
	@Autowired
	CodeGroupService codeGroupService;

	
}
