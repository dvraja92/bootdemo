package com.example.bootdemo.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class APIResponse<T> {

    private int code;

    private String desc;

    private T payload;

    /**
     * Getter
     *
     * @return : code
     */
    public int getCode() {
        return code;
    }

    /**
     * Setter
     *
     * @param code : code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Getter
     *
     * @return : desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Setter
     *
     * @param desc : desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Getter
     *
     * @return : payload
     */
    public T getPayload() {
        return payload;
    }

    /**
     * Setter
     *
     * @param payload : payload
     */
    public void setPayload(T payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "APIResponse [code=" + code + ", desc=" + desc + ", payload=" + payload + "]";
    }
}