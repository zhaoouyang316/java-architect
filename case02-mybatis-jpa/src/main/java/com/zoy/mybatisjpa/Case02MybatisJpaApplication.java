package com.zoy.mybatisjpa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;


@SpringBootApplication
@MapperScan(basePackages = {"com.zoy.mybatisjpa.dao"})
public class Case02MybatisJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Case02MybatisJpaApplication.class, args);
	}

}
