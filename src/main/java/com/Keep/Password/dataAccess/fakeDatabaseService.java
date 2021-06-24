package com.Keep.Password.dataAccess;

import com.Keep.Password.Model.InfoApp;
import com.Keep.Password.Model.SavedInfoApp;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDatabase")
public class fakeDatabaseService implements InfoAppDao{

    //add more save account
    private static List<SavedInfoApp> DB = new ArrayList<>();

    @Override
    public int addInfo(InfoApp infoApp) {
        DB.add(new SavedInfoApp(infoApp.getApplicationName(),
                infoApp.getUserName(),
                infoApp.getPassword(),
                UUID.randomUUID()));
        return 1;
    }

    @Override
    public List<SavedInfoApp> getAllInfo() {
        return DB;
    }

    @Override
    public int removeInfo(UUID id) {
        Optional<SavedInfoApp> tempInfo =
                DB.stream().filter(info -> info.getId().equals(id)).findFirst();
        if (!tempInfo.isPresent()){
            return 0;
        }
        DB.remove(tempInfo.get());
        return 1;
    }

}
