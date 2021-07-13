package com.Keep.Password.Service;


import com.Keep.Password.Model.User.ReceivedUserData;
import com.Keep.Password.Model.User.UserData;
import com.Keep.Password.dataAccess.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserDataService {

    private final UserRepository userRepository;
    public UserData loggedInUser = null;


    @Autowired
    public UserDataService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int addUser(ReceivedUserData receivedUserData) {
        if (receivedUserData.getPassword().isEmpty()
                || !receivedUserData.getConfirm().equals(receivedUserData.getPassword())) {
            return 0;
        }

        if (receivedUserData.getUserName().isEmpty() || isUserNameExist(receivedUserData.getUserName())) {
            return 0;
        }

        if (!isEmailValid(receivedUserData.getEmail()) || isEmailExist(receivedUserData.getEmail())) {
            return 0;
        }

        UserData userData = new UserData(receivedUserData.getEmail(),
                receivedUserData.getUserName(),
                receivedUserData.getPassword());
        userRepository.save(userData);
        return 1;
    }

    private boolean isEmailValid(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z|a-z|0-9]+((\\.|_)[A-Z|a-z|0-9]+)*@[A-Z|a-z|0-9]+(\\.[A-Z|a-z|0-9]+)+$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.matches();
    }

    private boolean isUserNameExist(String userName) {
        UserData userData = userRepository.findByUserName(userName);
        return userData != null;
    }

    private boolean isEmailExist(String email) {
        UserData userData = userRepository.findByEmail(email);
        return userData != null;
    }

    public UserData getUserById(UUID userId){
        return userRepository.findById(userId).orElse(null);
    }

    public List<UserData> getAllUsers() {
        return userRepository.findAll();
    }

    public int logIn(UserData userData) {
        if (loggedInUser != null) {
            return 0;
        }
        UserData tempUser = null;
        if (userData.getUserName() != null) {
            tempUser = userRepository.findByUserName(userData.getUserName());
        } else if (userData.getEmail() != null) {
            tempUser = userRepository.findByEmail(userData.getEmail());
        }

        if (tempUser != null && userData.getPassword().equals(tempUser.getPassword())) {
            loggedInUser = tempUser;
            return 1;
        } else
            return 0;
    }

    public int logOut() {
        if (loggedInUser == null)
            return 0;
        else {
            loggedInUser = null;
            return 1;
        }
    }

    public int removeAccount(String password) {
        if (loggedInUser == null || !loggedInUser.getPassword().equals(password)) {
            return 0;
        }
        logOut();
        userRepository.deleteById(loggedInUser.getUserId());
        return 1;
    }
}
