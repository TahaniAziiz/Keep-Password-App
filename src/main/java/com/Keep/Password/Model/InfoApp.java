package com.Keep.Password.Model;

import java.util.UUID;

public class InfoApp {

    private final String applicationName;
    private String userName;
    private String password;


    public InfoApp(String applicationName, String userName, String password) {
        this.applicationName = applicationName;
        this.userName = userName;
        this.password = password;
    }


    public String getApplicationName() {
        return applicationName;
    }

    public String getUserName() {
        return userName;
    }


    public String getPassword() {
        return password;
    }

}
