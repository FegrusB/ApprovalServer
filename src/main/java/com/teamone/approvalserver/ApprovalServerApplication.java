package com.teamone.approvalserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApprovalServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApprovalServerApplication.class, "--debug");
    }

}
