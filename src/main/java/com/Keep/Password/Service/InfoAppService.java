package com.Keep.Password.Service;

import com.Keep.Password.Model.InfoApp;
import com.Keep.Password.Model.SavedInfoApp;
import com.Keep.Password.dataAccess.InfoAppDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InfoAppService {
    private final InfoAppDao infoAppDao;


    @Autowired
    public InfoAppService(@Qualifier("fakeDatabase") InfoAppDao infoAppDao) {
        this.infoAppDao = infoAppDao;
    }

    public int addInfo(String nameOfApp, String userName, String password, String confirm){
        if (password != null && !password.isEmpty() && password.equals(confirm))
            return infoAppDao.addInfo(new InfoApp(nameOfApp, userName, password));
        else
            return 0;
    }

    public List<SavedInfoApp> getAllInfo() {

        return infoAppDao.getAllInfo();
    }

    public int removeInfo(UUID id) {
        if (id != null)
        return infoAppDao.removeInfo(id);
        else
            return 0;
    }

}
