package com.example.bootdemo.rest.security.token;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticationToken {
    private String token;
    /**
     * Getter
     *
     * @return : token
     */
    public String getToken() {
        return token;
    }

    /**
     * Setter : token
     *
     * @param token : auth token
     */
    public void setToken(String token) {
        this.token = token;
    }

}
