package com.demo.example;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController implements ErrorController {

	@RequestMapping(value = {"/", "/error"})
	public String index() {
		return "/dist/index.html";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
	
}
