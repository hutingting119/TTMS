package com.mec.volunteer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mec.volunteer.ISpringComponentScan;

@Configuration
@ComponentScan(basePackageClasses={ISpringComponentScan.class},
	excludeFilters={
		@Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)
	})
public class RootConfig {
}
