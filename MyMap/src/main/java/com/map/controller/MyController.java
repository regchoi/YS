package com.map.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
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
		
}
