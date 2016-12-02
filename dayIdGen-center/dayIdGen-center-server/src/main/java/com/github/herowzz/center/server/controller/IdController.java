package com.github.herowzz.center.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/id")
public class IdController {

	@RequestMapping("/get")
	@ResponseBody
	public String get() {
		return "get!!!aaaaaaaa";
	}

}
