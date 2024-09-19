package net.lscrp.ucp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LscrpApplication {

    public static void main(String[] args) {
        SpringApplication.run(LscrpApplication.class, args);
    }

}
