package com.Keep.Password.Model;

import java.util.UUID;

public class SavedInfoApp extends InfoApp{

    public UUID getId() {
        return id;
    }

    private final UUID id;


    public SavedInfoApp(String applicationName, String userName, String password , UUID id) {
        super(applicationName, userName, password);
        this.id = id;
    }
}
