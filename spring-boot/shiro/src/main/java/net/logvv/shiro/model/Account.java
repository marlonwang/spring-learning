package net.logvv.shiro.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Random;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
    private int accountId;
    private String firstname;
    private String lastname;

    public Account(String firstname, String lastname){
        this.accountId = new Random(2).nextInt(100);
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("{")
                .append("\"accountId\":").append(accountId).append(",")
                .append("\"firstname\":").append("\"").append(firstname).append("\",")
                .append("\"lastname\":").append("\"").append(lastname).append("\"}");

         return builder.toString();
    }
}
