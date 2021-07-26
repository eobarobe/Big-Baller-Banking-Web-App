package com.revature.bigballerbank.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.revature.bigballerbank.repositories")
@EnableTransactionManagement
//@ComponentScan(basePackages = "com.revature")
@PropertySource("classpath:application.yml") // Will be changed to yaml in future
@Import({AOPConfig.class})
public class AppConfig {

}