package com.skander.project.employee;

import org.springframework.boot.SpringApplication;    
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.skander.project.employee.repository.EmployeeRepository;
import com.skander.project.employee.services.EmployeeServices;

@SpringBootApplication
@ComponentScan(basePackages ={ "com.skander.project.employee.*"})
@EnableJpaRepositories (basePackageClasses  = {EmployeeServices.class,EmployeeRepository.class})
@EnableAutoConfiguration
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200");
			}
		};
	}
}
