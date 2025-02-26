package net.engineeringdigest.journalApp.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    public void sendEmailTest() {
        emailService.sendMail("jiteshgadgilwar17@gmail.com", "Test mail from Spring Boot", "hello jitesh!");
    }
}
