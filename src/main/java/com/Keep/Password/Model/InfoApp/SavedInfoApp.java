package com.Keep.Password.Model.InfoApp;

import com.Keep.Password.Model.InfoApp.InfoApp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collation = "SavedInfoApp")
public class SavedInfoApp extends InfoApp {
    @Id
    @Indexed(unique = true)
    private final String id;

    public UUID getId() {
        return UUID.fromString(id);
    }

    public SavedInfoApp(String applicationName, String userName, String password, UUID id) {
        super(applicationName, userName, password);
        this.id = id.toString();
    }
}
