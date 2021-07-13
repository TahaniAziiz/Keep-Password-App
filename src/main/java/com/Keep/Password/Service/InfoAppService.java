package com.Keep.Password.Service;

import com.Keep.Password.Model.InfoApp.SavedInfoApp;
import com.Keep.Password.Model.InfoApp.UpdatePassword;
import com.Keep.Password.Model.User.UserData;
import com.Keep.Password.dataAccess.InfoAppRepository;
import com.Keep.Password.dataAccess.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InfoAppService {
    private final InfoAppRepository infoAppRepository;
    private final UserDataService userDataService;
    private final UserRepository userRepository;


    @Autowired
    public InfoAppService(InfoAppRepository infoAppRepository, UserDataService userDataService, UserRepository userRepository) {
        this.infoAppRepository = infoAppRepository;
        //Connect between user and apps
        //FIXME change
        this.userDataService = userDataService;
        this.userRepository = userRepository;
    }

    private UUID getUserId() {
        return userDataService.loggedInUser.getUserId();
    }

    public int updateUserName(UUID id, String userName) {
        if (!userName.isEmpty()) {
            Optional<SavedInfoApp> appInfo = infoAppRepository.findById(id);
            if (appInfo.isPresent()) {
                appInfo.get().setUserName(userName);
                infoAppRepository.save(appInfo.get());
                return 1;
            }
        }
        return 0;
    }

    public int updatePassword(UUID id, UpdatePassword updatePassword) {
        if (updatePassword.getNewPassword().isEmpty()
                || !updatePassword.getNewPassword().equals(updatePassword.getConfirm())) {
            return 0;
        }

        SavedInfoApp currentInfo = getSpecificItem(id);
        if (currentInfo == null || !currentInfo.getPassword().equals(updatePassword.getCurrentPassword())) {
            return 0;
        }

        Optional<SavedInfoApp> appInfo = infoAppRepository.findById(id);
        if (appInfo.isPresent()) {
            appInfo.get().setPassword(updatePassword.newPassword);
            infoAppRepository.save(appInfo.get());
            return 1;
        }
        return 0;
    }

    public int addInfo(String nameOfApp, String userName, String password, String confirm) {
        if (!password.isEmpty() && password.equals(confirm) && !userName.isEmpty()) {
            UUID newId = UUID.randomUUID();
            infoAppRepository.save(new SavedInfoApp(nameOfApp, userName, password, newId));

            Optional<UserData> tempAddUser = userRepository.findById(getUserId());
            if (tempAddUser.isPresent()) {
                tempAddUser.get().adduserApplicationsList(newId);
                userRepository.save(tempAddUser.get());
                return 1;
            }

        }
        return 0;
    }

    public int removeInfo(UUID id) {

        infoAppRepository.deleteById(id);
        Optional<UserData> tempRemove = userRepository.findById(getUserId());
        if (tempRemove.isPresent()) {
            tempRemove.get().removeUserApplicationsList(id);
            userRepository.save(tempRemove.get());
            return 1;
        }
        return 0;
    }

    public List<SavedInfoApp> getAllInfo() {
        Optional<UserData> tempGetAll = userRepository.findById(getUserId());
        if (tempGetAll.isPresent()) {
            return infoAppRepository.findAll()
                    .stream()
                    .filter(app ->
                            tempGetAll.orElse(null).getUserApplicationsList().contains(app.getId())
                    ).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public SavedInfoApp getSpecificItem(UUID id) {
        return getAllInfo()
                .stream()
                .filter(app -> app.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

}
