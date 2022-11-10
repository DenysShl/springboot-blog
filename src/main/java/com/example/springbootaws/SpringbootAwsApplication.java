package com.example.springbootaws;

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootAwsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAwsApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        Boolean[] trufs = {true, true, false, true, true, false, true};
        return args -> {
            long count = Arrays.stream(trufs)
                    .filter(x -> x.equals(true))
                    .count();
            System.out.println("count = " + count);
        };
    }
}
