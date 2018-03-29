package com.example.demovaadinstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.xor.groundwork.springboot.data.repository.GWPagingAndSortingRepoImpl;

@SpringBootApplication(scanBasePackages="com.exmaple")
@EntityScan(basePackages= {"com.example"})
@EnableJpaRepositories(repositoryBaseClass=GWPagingAndSortingRepoImpl.class,basePackages="com.example.demovaadinstart")
//@EnableJpaAuditing(auditorAwareRef="auditorAware")
public class DemoVaadinStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoVaadinStartApplication.class, args);
	}
}
