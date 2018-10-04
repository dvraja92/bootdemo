package com.example.bootdemo.service.impl;

import com.example.bootdemo.rest.request.LoginUserDTO;
import com.example.bootdemo.rest.response.APIResponse;
import com.example.bootdemo.rest.security.JwtTokenUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommonServiceImplTest {
    private CommonServiceImpl commonService;
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;


    @Before
    public void setUp()throws Exception{
        authenticationManager = mock(AuthenticationManager.class);
        jwtTokenUtil = mock(JwtTokenUtil.class);
        commonService = spy(new CommonServiceImpl(authenticationManager,jwtTokenUtil));


    }

    @Test
    public void getUserEmail() throws Exception {
        LoginUserDTO loginUserDTO = mock(LoginUserDTO.class);
        APIResponse<String> apiResponse=mock(APIResponse.class);
        when(loginUserDTO.getEmail ()).thenReturn ("demo@test.com");
        apiResponse.setCode ( HttpStatus.OK.value () );
        apiResponse.setDesc ("success");
        apiResponse.setPayload ( loginUserDTO.getEmail () );
        commonService.getUserEmail ();
        verify ( commonService ).getUserEmail ();
    }

}