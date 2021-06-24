package com.Keep.Password.dataAccess;

import com.Keep.Password.Model.InfoApp;
import com.Keep.Password.Model.SavedInfoApp;

import java.util.List;
import java.util.UUID;

public interface InfoAppDao {

    int addInfo(InfoApp infoApp);

    List<SavedInfoApp> getAllInfo();

    int removeInfo(UUID id);
}
