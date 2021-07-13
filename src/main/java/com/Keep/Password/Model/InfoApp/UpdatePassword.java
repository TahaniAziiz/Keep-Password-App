package com.Keep.Password.Model.InfoApp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.NotNull;

public class UpdatePassword {

    @NotNull
    public final String currentPassword;
    @NotNull
    public final String newPassword;
    @NotNull
    public final String confirm;


    public UpdatePassword(@JsonProperty("Current") String currentPassword,
                          @JsonProperty("New")String newPassword,
                          @JsonProperty("Confirm") String confirm) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirm = confirm;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirm() {
        return confirm;
    }
}
