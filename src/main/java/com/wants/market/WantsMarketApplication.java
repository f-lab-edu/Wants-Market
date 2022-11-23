package com.wants.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@SpringBootApplication
public class WantsMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(WantsMarketApplication.class, args);
	}

}
