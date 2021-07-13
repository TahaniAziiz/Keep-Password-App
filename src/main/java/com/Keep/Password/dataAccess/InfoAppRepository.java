package com.Keep.Password.dataAccess;


import com.Keep.Password.Model.InfoApp.InfoApp;
import com.Keep.Password.Model.InfoApp.SavedInfoApp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("InfoAppRepository")
public interface InfoAppRepository extends MongoRepository<SavedInfoApp, UUID> {

}
