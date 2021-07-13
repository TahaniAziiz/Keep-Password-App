package com.Keep.Password.Model.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document(collation = "UserData")
public class UserData {


    @Indexed(unique = true)
    private final String email;
    @Indexed(unique = true)
    private final String userName;//the user can log in by userName or email
    @NotNull
    private final String password;
    private final Set<UUID> userApplicationsList;
    @Id
    @Indexed(unique = true)
    private final String userId;

    public UserData(@JsonProperty("email") String email,
                    @JsonProperty("userName") String userName,
                    @JsonProperty("password") String password) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.userApplicationsList = new HashSet<>();
        this.userId = UUID.randomUUID().toString();
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Set<UUID> getUserApplicationsList() {
        return userApplicationsList;
    }

    public UUID getUserId() {
        return UUID.fromString(userId);
    }

    public void adduserApplicationsList(UUID id) {
        this.userApplicationsList.add(id);
    }

    public void removeUserApplicationsList(UUID id) {
        this.userApplicationsList.remove(id);
    }

}
