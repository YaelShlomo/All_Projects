//package com.leumi.coupon_project.coupon_system;
//
//import com.example.coupon_system.helpers.SimpleTokenManager;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ConfigurableApplicationContext;
//
//
//@SpringBootApplication
//public class CouponSystemApplication {
//
//	public static void main(String[] args) {
//		ConfigurableApplicationContext ctx = SpringApplication.run(CouponSystemApplication.class, args);
//
//		//Singleton
//		SimpleTokenManager simpleTokenManager = ctx.getBean(SimpleTokenManager.class);
//
//		simpleTokenManager.initThread();
//	}
//
//}
