package com.Keep.Password.Model.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.NotNull;

public class ReceivedUserData extends UserData{


    @NotNull
    private final String Confirm;

    public ReceivedUserData(@JsonProperty("email") String email,
                            @JsonProperty("userName") String userName,
                            @JsonProperty("password") String password,
                            @JsonProperty("confirm") String confirm) {
        super(email, userName, password);
        Confirm = confirm;
    }

    public String getConfirm() {
        return Confirm;
    }
}
