package com.springvaadin.springvaadin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages="com.springvaadin.springvaadin")
//@EntityScan(basePackages= {"com.xor"})
//@EnableJpaRepositories(repositoryBaseClass=GWPagingAndSortingRepoImpl.class,basePackages="com.xor")
////@EnableJpaAuditing(auditorAwareRef="auditorAwar")
public class SpringvaadinApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringvaadinApplication.class, args);
	}
}
