package com.Keep.Password.api;


import com.Keep.Password.Model.User.ReceivedUserData;
import com.Keep.Password.Model.User.UserData;
import com.Keep.Password.Service.InfoAppService;
import com.Keep.Password.Service.UserDataService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/userData")
@RestController
public class UserDataApi {
    private final UserDataService userDataService;

    @Autowired
    public UserDataApi(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @PostMapping(path = "/registration")
    public int registration(@NotNull @Validated @RequestBody ReceivedUserData receivedUserData) {
        return userDataService.addUser(receivedUserData);
    }

    @PostMapping(path = "/LogIn")
    //Validated check for each item in the received object
    //NotNull check for received object
    public int logIn(@NotNull @Validated @RequestBody UserData userData) {
        return userDataService.logIn(userData);
    }

    @GetMapping
    public List<UserData> getAllUsers() {
        return userDataService.getAllUsers();
    }

    @PostMapping(path = "/logOut")
    public int logOut() {
        return userDataService.logOut();
    }

    @PostMapping(path = "/removeAccount")
    public int removeAccount(@NotNull @RequestBody String password){
        return userDataService.removeAccount(password);
    }

}
