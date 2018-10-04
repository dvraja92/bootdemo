package com.example.bootdemo.service.impl;

import com.example.bootdemo.rest.request.LoginUserDTO;
import com.example.bootdemo.rest.response.APIResponse;
import com.example.bootdemo.rest.security.JwtTokenUtil;
import com.example.bootdemo.rest.security.token.AuthenticationToken;
import com.example.bootdemo.service.CommonService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class CommonServiceImpl implements CommonService {
    private static final Logger LOGGER = LogManager.getLogger(CommonServiceImpl.class);

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public CommonServiceImpl(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil){
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public APIResponse<String> generateToken(LoginUserDTO userDTO, HttpServletResponse response) {
        AuthenticationToken authToken ;
        APIResponse<String> apiResponse = new APIResponse<>();
        String token = "";

        try {
            token = jwtTokenUtil.generateToken(userDTO);

        } catch (Exception e) {
            LOGGER.error("Username and password check time exception occur {}", e);
        }
        if (StringUtils.isNotBlank(token)) {
            apiResponse.setCode( HttpStatus.OK.value());
            apiResponse.setDesc(HttpStatus.OK.getReasonPhrase());
            response.setStatus(HttpStatus.OK.value());
            authToken = new AuthenticationToken();
            authToken.setToken(token);
            token = authToken.getToken();
            apiResponse.setPayload(token);

            }
         else {
            apiResponse.setCode(HttpStatus.UNAUTHORIZED.value());
            apiResponse.setDesc(HttpStatus.UNAUTHORIZED.getReasonPhrase());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            apiResponse.setPayload("Username and Password is invalid");
        }
        return apiResponse;
    }

    @Override
    public APIResponse<String> getUserEmail() {
        LoginUserDTO loginUserDTO = new LoginUserDTO ();
        String userEmail = loginUserDTO.getEmail ();
        APIResponse<String> apiResponse = new APIResponse<> ();
        apiResponse.setCode ( HttpStatus.OK.value () );
        apiResponse.setDesc ( HttpStatus.OK.getReasonPhrase () );
        apiResponse.setPayload ( userEmail );
        return apiResponse;
    }
}
