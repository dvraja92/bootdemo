package com.example.bootdemo.rest.controller;

import com.example.bootdemo.rest.response.APIResponse;
import com.example.bootdemo.service.CommonService;
import com.example.bootdemo.service.impl.CommonServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthenticationControllerTest {
    private AuthenticationController authenticationController;
    private CommonServiceImpl commonService;
    MockMvc mockMvc;

    @Before
    public void setUp()throws Exception{
        commonService = mock(CommonServiceImpl.class);
        authenticationController = spy(new AuthenticationController (commonService));
        mockMvc = MockMvcBuilders.standaloneSetup (authenticationController ).build ();
    }
    @Test
    public void getUserEmail() throws Exception {
        APIResponse<String> apiResponse = new APIResponse<> ();
        apiResponse.setCode ( HttpStatus.OK.value ());
        apiResponse.setDesc ("success");
        apiResponse.setPayload ("demo@demo.com");
        when(commonService.getUserEmail ()).thenReturn(apiResponse);
        mockMvc.perform (get("/bootdemo/api/v1/public/get-userEmail")).andExpect(status().isOk());

    }

}