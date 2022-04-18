package com.test.mytest;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class MyService {
	private Random my_ran = new Random(); // 1. 랜덤 클래스를 생성해서 빈 객체를 선언한다.

	// 2. Address라는 도메인을 3개 객체 생성해서 배열로 담아 List로 리턴
	private List<Address> all = Arrays.asList(
		new Address("Hong-gil dong", "seoul", "02-000,000"),
		new Address("Jung-gil dong", "incheon", "032-000,000"),
		new Address("Pak-gil dong", "pusan", "051-000,000")
		);
	
	// 3. List 컬렉션인 all이라는 객체가 가진 지정된 요소를 찾아서 리턴하는 get()메소드로 하나이 요소를 랜더으로 리턴받는다.
	private Address randomAddress() {
		return all.get(my_ran.nextInt(all.size()));
	// all.get(0) -> new Address("Hong-gil dong", "seoul", "02-000,000"),

	}
	
	// 4. 컨트롤에서 리턴되어 출력되는 레코드를 지정한 시간 동안 무한 반복하면서 리턴된다.
	public Flux<Address>	getAllAddress() {
		// generate : 무한적으로 연속적인 데이터를 리턴하는 메소드
		return Flux.<Address> generate(sink -> sink.next(randomAddress()))
				.delayElements(Duration.ofMillis(250));
	}
	

}
