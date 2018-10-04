package com.example.bootdemo.rest.security.token;

import com.example.bootdemo.rest.request.LoginUserDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SecuredAccessToken extends Token {

    public SecuredAccessToken(@JsonProperty("currentLoggedInUser") LoginUserDTO currentLoggedInUser, @JsonProperty("createdDate") Date createdDate, @JsonProperty("expiryTime") Date expiryTime) {
        super(currentLoggedInUser, createdDate, expiryTime);
    }
}