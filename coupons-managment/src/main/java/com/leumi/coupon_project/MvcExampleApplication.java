package com.leumi.coupon_project;

import com.leumi.coupon_project.helpers.SimpleTokenManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2

public class MvcExampleApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(MvcExampleApplication.class, args);

		SimpleTokenManager simpleTokenManager = ctx.getBean(SimpleTokenManager.class);

		simpleTokenManager.initThread();
		}
}
