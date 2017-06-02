package com.mec.volunteer.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mec.volunteer.ISpringComponentScan;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses={ISpringComponentScan.class})
@EnableTransactionManagement
public class WebConfig extends WebMvcConfigurerAdapter {
	@Bean
	public ComboPooledDataSource dataSource() {
		System.out.println("开始配置comboPooledDataSource... ...");
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		
		return comboPooledDataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactoryBean(ComboPooledDataSource dataSource) {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		System.out.println("开始配置sessionFactoryBean... ...");
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setPackagesToScan("com.mec.volunteer");
		System.out.println("sessionFactoryBean配置完毕！");
		
		return sessionFactoryBean;
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/");
		resolver.setSuffix(".action");
		resolver.setExposeContextBeansAsAttributes(true);
		
		return resolver;
	}
	
	@Bean
	public PlatformTransactionManager getPlatformTransactionManager(SessionFactory sessionFactory) {
		PlatformTransactionManager platformTransactionManager = new HibernateTransactionManager(sessionFactory);
		return platformTransactionManager;
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}
