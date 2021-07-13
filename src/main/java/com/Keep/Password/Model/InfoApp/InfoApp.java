package com.Keep.Password.Model.InfoApp;

import com.sun.istack.internal.NotNull;


public class InfoApp {

    @NotNull
    private final String applicationName;
    @NotNull
    private String userName;
    @NotNull
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
