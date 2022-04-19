package com.map.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.Address;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/")
public class MyController {	
	

		@GetMapping("/test01")
		public String MapTest01() {
			return "/view/test01";
		}
		
		@GetMapping("/test02")
		public String MapTest02() {
			return "/view/test02";
		}
		
		@GetMapping("/test03")
		public String MapTest03() {
			return "/view/test03";
		}
		@GetMapping("/test04")
		public String MapTest04() {
			return "/view/test04";
		}
		@GetMapping("/test05")
		public String MapTest05() {
			return "/view/test05";
		}
		@GetMapping("/test06")
		public String MapTest06() {
			return "/view/test06";
		}
		@GetMapping("/test07")
		public String MapTest07() {
			return "/view/test07";
		}
		@GetMapping("/")
		public String MapTest0() {
			return "/view/index";
		}
		@GetMapping("/test08")
		public String MapTest08(@RequestParam("addr") String ad, HttpServletRequest req) {
			
			HttpSession session = req.getSession();
	        session.setAttribute("model", ad); 
			return "/view/test08";
		}
		
}
