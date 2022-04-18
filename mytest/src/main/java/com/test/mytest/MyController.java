package com.test.mytest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController // 화면 구성을 위한 view로 리턴되지 않고 결과를 데이터 직렬화로 http response에 직접 쓴다.
				// @Controller and @ResponseBody. 	json, xml 데이터
public class MyController {
	@Autowired
	private MyService myservice;
	
	// 라우팅 = 맵핑    
	// request get 방식으로 연동해 주는 spring webMvc		/ produces는 리턴이 반환되는 미디어타입
	// 0 -N으로 비동기 통신을 하겠다. Address를 출력하는 웹 브라우저 리턴 페이지를!
	@GetMapping(value="/server", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<Address> serviceAddress() {
		return this.myservice.getAllAddress();
	}
}
