package com.revature.bigballerbank;

import com.revature.bigballerbank.configs.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@Import({AppConfig.class})
public class Driver {

    public static void main(String[] args) {
        System.out.println("Initializing Big Baller Bank application...");
        SpringApplication.run(Driver.class,args);
        System.out.println("Initialized Big Baller Bank application...");

    }

}
