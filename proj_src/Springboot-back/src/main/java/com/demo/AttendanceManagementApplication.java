package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.demo.mapper") //扫描的mapper
@SpringBootApplication
public class AttendanceManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceManagementApplication.class, args);
		System.out.println("程序正在运行！");
	}

}






