package com.lh.news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BootStrapController {

	@RequestMapping("bootstrap")
	public String bootstrap() {
		return "bootstrap";
	}
}
