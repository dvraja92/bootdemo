package com.example.bootdemo.rest.controller;

import com.example.bootdemo.Constants;
import com.example.bootdemo.rest.request.LoginUserDTO;
import com.example.bootdemo.rest.response.APIResponse;
import com.example.bootdemo.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(Constants.BASE_PUBLIC_API_URL)
public class AuthenticationController {

    private final CommonService commonService;

    @Autowired
    public AuthenticationController(CommonService commonService) {
        this.commonService = commonService;
    }
    /**
     * @param loginUser : loginUser
     * @return : token
     * @apiNote :validate username and password after that return token
     */
    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public APIResponse<String> register(@Valid @RequestBody LoginUserDTO loginUser, HttpServletResponse response) {
        return commonService.generateToken(loginUser, response);

    }

    /**
     * @return :user email
     *
     */
        @RequestMapping(value = "/get-userEmail", method = RequestMethod.GET)
    public APIResponse<String> getUserEmail(){
       return commonService.getUserEmail ();
    }

}
