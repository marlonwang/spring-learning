package net.logvv.shiro.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
    private int accountId;
    private String name;
    private String address;

    public Account(String name,String address){
        this.name = name;
        this.address = address;
    }

}
