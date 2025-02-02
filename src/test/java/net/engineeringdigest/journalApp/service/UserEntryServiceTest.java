package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.repository.UserEntryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserEntryServiceTest {

    @Autowired
    private UserEntryRepository userEntryRepository;

    @ParameterizedTest
    @CsvSource({
            "Jitesh",
            "test",
            "loskesh"
    })
    public void test_findByUserName(String user) {
        assertNotNull(userEntryRepository.findByUserName(user));
    }

    @ParameterizedTest
    @CsvFileSource( resources = "/test.csv")
    public void test_add(int a, int b, int result) {
        assertEquals(result, a+b);
    }
}
