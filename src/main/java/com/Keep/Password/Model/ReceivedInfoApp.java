package com.Keep.Password.Model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ReceivedInfoApp extends InfoApp{

    private String confirm;


    public ReceivedInfoApp(@JsonProperty("AppName") String applicationName,
                           @JsonProperty("userName") String userName,
                           @JsonProperty("password") String password ,
                           @JsonProperty("confirm") String confirm) {
        super(applicationName, userName, password);
        this.confirm = confirm;
    }

    public String getConfirm() {
        return confirm;
    }
}
