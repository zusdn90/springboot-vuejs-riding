package com.riding.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing	// 생성시간, 수정시간 자동화
@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "false");
		System.setProperty("spring.devtools.liveload.enabled", "true");
		SpringApplication.run(MainApplication.class, args);
	}

}
