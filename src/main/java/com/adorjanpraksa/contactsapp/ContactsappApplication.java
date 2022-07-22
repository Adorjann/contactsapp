package com.adorjanpraksa.contactsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ContactsappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactsappApplication.class, args);
    }

}
