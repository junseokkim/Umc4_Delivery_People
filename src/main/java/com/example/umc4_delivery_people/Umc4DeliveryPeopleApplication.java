package com.example.umc4_delivery_people;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Umc4DeliveryPeopleApplication {

    public static void main(String[] args) {
        SpringApplication.run(Umc4DeliveryPeopleApplication.class, args);
    }

}
