package com.Keep.Password.Model.InfoApp;


import com.Keep.Password.Model.InfoApp.InfoApp;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.NotNull;

public class ReceivedInfoApp extends InfoApp {

    @NotNull
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
