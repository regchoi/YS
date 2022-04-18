package com.test.mytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication	// 자동 설정(class path, 다양한 설정파일, 빈(특정빈 추가의존 파일 등) Flux), 컴포넌트 탐색
public class ProWebApplication {

	public static void main(String[] args) {	// 진입점
		SpringApplication.run(ProWebApplication.class, args);	// 애플리케이션 시작점을 등록하는 hookpoint
	}

}
