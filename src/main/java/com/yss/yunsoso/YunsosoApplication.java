package com.yss.yunsoso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement//开启事务管理
public class YunsosoApplication {

	//@Override
	//protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 此处Application.class替换为springboot默认启动类
	//	return builder.sources(YunsosoApplication.class);
	//}

	public static void main(String[] args) {
		SpringApplication.run(YunsosoApplication.class, args);
	}
}
