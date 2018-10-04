package com.example.bootdemo.rest.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginUserDTO {

    @NotEmpty(message = "password must not be empty")
    private String password = "demo";

    @NotEmpty(message = "email must not be empty")
    private String email = "demo@demo.com";

    /**
     * Getter
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter
     *
     * @param password : password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter
     *
     * @return : email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter
     *
     * @param email : email
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
