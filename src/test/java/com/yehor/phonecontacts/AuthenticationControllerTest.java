package com.yehor.phonecontacts;

import com.yehor.phonecontacts.api.controller.AuthenticationController;
import com.yehor.phonecontacts.api.dto.AuthenticationRequest;
import com.yehor.phonecontacts.api.dto.AuthenticationResponse;
import com.yehor.phonecontacts.api.dto.RegisterRequest;
import com.yehor.phonecontacts.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        RegisterRequest registerRequest = new RegisterRequest();
        AuthenticationResponse expectedResponse = new AuthenticationResponse("token");

        when(authenticationService.register(registerRequest)).thenReturn(expectedResponse);

        ResponseEntity<AuthenticationResponse> response = authenticationController.register(registerRequest);

        verify(authenticationService).register(registerRequest);

        assertSame(HttpStatus.OK, response.getStatusCode());
        assertSame(expectedResponse, response.getBody());
    }

    @Test
    void testAuthenticate() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        AuthenticationResponse expectedResponse = new AuthenticationResponse("token");

        when(authenticationService.authenticate(authenticationRequest)).thenReturn(expectedResponse);

        ResponseEntity<AuthenticationResponse> response = authenticationController.authenticate(authenticationRequest);

        verify(authenticationService).authenticate(authenticationRequest);

        assertSame(HttpStatus.OK, response.getStatusCode());
        assertSame(expectedResponse, response.getBody());
    }
}
