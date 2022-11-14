package com.example.helloapp;

import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration(proxyBeanMethods =  false)
public class AlvCompatibilityConfiguration implements WebMvcConfigurer {

	@SuppressWarnings("deprecation")
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseTrailingSlashMatch(true);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/actuator/").setViewName("forward:/actuator");
		registry.addViewController("/actuator/httptrace").setViewName("forward:/actuator/httpexchanges");
		registry.addViewController("/actuator/httptrace/").setViewName("forward:/actuator/httpexchanges");
	}

	@Bean
	public HttpExchangeRepository httpTraceRepository() {
		return new InMemoryHttpExchangeRepository();
	};

}
