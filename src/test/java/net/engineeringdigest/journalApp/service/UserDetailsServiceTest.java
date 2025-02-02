package net.engineeringdigest.journalApp.service;


import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserEntryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.Mockito.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;


public class UserDetailsServiceTest {

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    private UserDetailsService userDetailsService;

    @Mock
    private UserEntryRepository userEntryRepository;

    @Test
    public void loadUserByUsernameTest() {
        when(userEntryRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("test").password("testpassword").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("jitesh");
        Assertions.assertNotNull(user);
    }
}
