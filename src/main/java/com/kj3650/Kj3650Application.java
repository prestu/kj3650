package com.kj3650;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author fanchengheng
 */
@EnableScheduling
@SpringBootApplication
public class Kj3650Application {

    public static void main(String[] args) {
        SpringApplication.run(Kj3650Application.class, args);
    }

}
