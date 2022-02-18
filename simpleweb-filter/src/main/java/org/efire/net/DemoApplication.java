package org.efire.net;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

//Reference: https://dzone.com/articles/working-with-filters-in-spring

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<CakesFilter> cakesFilter()
	{
		FilterRegistrationBean<CakesFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new CakesFilter());
		registrationBean.addUrlPatterns("/cakes/*");
		return registrationBean;
	}
}
