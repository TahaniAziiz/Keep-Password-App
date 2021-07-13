//package com.Keep.Password.dataAccess;
//
//import com.Keep.Password.Model.InfoApp.InfoApp;
//import com.Keep.Password.Model.InfoApp.SavedInfoApp;
//import com.Keep.Password.Model.User.UserData;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Repository("fakeDatabase")
//public class fakeDatabaseService implements InfoAppDao, UserDataDao {
//
//    //add more save account
//    private static final List<UserData> userDB = new ArrayList<>();
//
//    @Override
//    public int addUser(UserData userData) {
//        userDB.add(userData);
//        return 1;
//    }
//
//    @Override
//    public UserData getUserDataByUserName(String userName) {
//        Optional<UserData> tempUser =
//                userDB.stream().filter(user -> user.getUserName().equals(userName)).findFirst();
//        return tempUser.orElse(null);
//    }
//
//    @Override
//    public UserData getUserDataByEmail(String email) {
//        Optional<UserData> tempUser =
//                userDB.stream().filter(user -> user.getEmail().equals(email)).findFirst();
//        return tempUser.orElse(null);
//    }
//
//    @Override
//    public int removeAccount(UUID userId) {
//        Optional<UserData> tempUser =
//                userDB.stream().filter(user -> user.getUserId().equals(userId)).findFirst();
//        if (!tempUser.isPresent()) {
//            return 0;
//        }
//        userDB.remove(tempUser.get());
//        return 1;
//    }
//
//    @Override
//    public List<UserData> getAllUsers() {
//        return userDB;
//    }
//
//    private List<SavedInfoApp> getUserApps(UUID userId) {
//        Optional<UserData> tempUser =
//                userDB.stream().filter(user -> user.getUserId().equals(userId)).findFirst();
//        return tempUser.map(UserData::getUserApplicationsList).orElse(null);
//    }
//
//    @Override
//    public int addInfo(InfoApp infoApp, UUID userId) {
//        List<SavedInfoApp> userAppDB = getUserApps(userId);
//        if (userAppDB == null) {
//            return 0;
//        }
//        userAppDB.add(new SavedInfoApp(infoApp.getApplicationName(),
//                infoApp.getUserName(),
//                infoApp.getPassword(),
//                UUID.randomUUID()));
//        return 1;
//    }
//
//    @Override
//    public List<SavedInfoApp> getAllInfo(UUID userId) {
//        return getUserApps(userId);
//    }
//
//    @Override
//    public int removeInfo(UUID appId, UUID userId) {
//        List<SavedInfoApp> userAppDB = getUserApps(userId);
//        if (userAppDB == null) {
//            return 0;
//        }
//        Optional<SavedInfoApp> tempInfo =
//                userAppDB.stream().filter(info -> info.getId().equals(appId)).findFirst();
//        if (!tempInfo.isPresent()) {
//            return 0;
//        }
//        userAppDB.remove(tempInfo.get());
//        return 1;
//    }
//
//    @Override
//    public SavedInfoApp getSpecificItem(UUID appId, UUID userId) {
//        List<SavedInfoApp> userAppDB = getUserApps(userId);
//        if (userAppDB == null) {
//            return null;
//        }
//        Optional<SavedInfoApp> tempInfo =
//                userAppDB.stream().filter(info -> info.getId().equals(appId)).findFirst();
//        return tempInfo.orElse(null);
//    }
//
//    @Override
//    public int updateUserName(UUID id, String userName, UUID userId) {
//        List<SavedInfoApp> userAppDB = getUserApps(userId);
//        if (userAppDB == null) {
//            return 0;
//        }
//        Optional<SavedInfoApp> tempInfo =
//                userAppDB.stream().filter(info -> info.getId().equals(id)).findFirst();
//        if (!tempInfo.isPresent()) {
//            return 0;
//        }
//        int index = userAppDB.indexOf(tempInfo.get());
//        if (index < 0)
//            return 0;
//
//        userAppDB.set(index, new SavedInfoApp(tempInfo.get().getApplicationName(),
//                userName,
//                tempInfo.get().getPassword(),
//                tempInfo.get().getId()
//        ));
//        return 1;
//    }
//
//    @Override
//    public int updatePassword(UUID id, String newPassword, UUID userId) {
//        List<SavedInfoApp> userAppDB = getUserApps(userId);
//        if (userAppDB == null) {
//            return 0;
//        }
//        Optional<SavedInfoApp> tempInfo =
//                userAppDB.stream().filter(info -> info.getId().equals(id)).findFirst();
//        if (!tempInfo.isPresent()) {
//            return 0;
//        }
//        int index = userAppDB.indexOf(tempInfo.get());
//        if (index < 0)
//            return 0;
//
//        userAppDB.set(index, new SavedInfoApp(tempInfo.get().getApplicationName(),
//                tempInfo.get().getUserName(),
//                newPassword,
//                tempInfo.get().getId()
//        ));
//        return 1;
//    }
//
//}
