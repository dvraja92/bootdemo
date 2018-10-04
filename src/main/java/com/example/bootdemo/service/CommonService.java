package com.example.bootdemo.service;

import com.example.bootdemo.rest.request.LoginUserDTO;
import com.example.bootdemo.rest.response.APIResponse;

import javax.servlet.http.HttpServletResponse;

public interface CommonService {
    /**
     * @param userDTO : userDTO class object
     * @return : APIResponse class object
     * @implNote : check credential if true then return token
     */
    APIResponse<String> generateToken(LoginUserDTO userDTO, HttpServletResponse response);

    /**
     *
     * @return apiresponse class object
     */
    APIResponse<String> getUserEmail();
}
