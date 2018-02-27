package com.gcit.lms.utils;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan("com.gcit.lms")
public class SpringConfig {

//	@Bean
//	public BasicDataSource dataSource() {
//		BasicDataSource bds = new BasicDataSource();
//		bds.setDriverClassName(driver);
//		bds.setUrl(url);
//		bds.setUsername(username);
//		bds.setPassword(password);
//		return bds;
//	}
//
//	@Bean
//	public JdbcTemplate jdbcTemplate() {
//		return new JdbcTemplate(dataSource());
//	}

	@Bean
	public DateManipulation DM() {
		return new DateManipulation();
	}
}
