package com.example.blog;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Log4j2
@SpringBootApplication
public class SpringbootAwsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAwsApplication.class, args);
        log.info("Start app at " + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        Boolean[] array = {true, true, false, true, true, false, true};
        return args -> {
            long count = Arrays.stream(array)
                    .filter(x -> x.equals(true))
                    .count();

            log.info("Count 'true' = " + count);
        };
    }
}
