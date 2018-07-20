package com.dfs.springbitpay.dfsbitpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class DfsBitpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DfsBitpayApplication.class, args);
	}
}
