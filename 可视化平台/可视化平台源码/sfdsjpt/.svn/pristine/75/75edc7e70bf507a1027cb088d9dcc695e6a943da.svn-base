package com.software;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(exclude = {
		SessionAutoConfiguration.class
})
@Configuration
@MapperScan("com.software.mapper")
public class SfdsjptApplication {
	public static void main(String[] args) {
		SpringApplication.run(SfdsjptApplication.class, args);
	}

}
