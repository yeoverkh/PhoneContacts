package com.yehor.phonecontacts;

import com.yehor.phonecontacts.api.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
class UserDetailsServiceTest {

    @MockBean
    private UserDetailsService userDetailsService;

    @Test
    void testLoadUserByUsername() {
        User user = new User();
        user.setLogin("testuser");
        user.setPassword("password");

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "testuser", "password", Collections.emptyList());
        when(userDetailsService.loadUserByUsername("testuser")).thenReturn(userDetails);

        UserDetails loadedUserDetails = userDetailsService.loadUserByUsername("testuser");

        assertNotNull(loadedUserDetails);
        assertEquals("testuser", loadedUserDetails.getUsername());
        assertEquals("password", loadedUserDetails.getPassword());
    }
}
