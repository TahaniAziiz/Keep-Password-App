package com.Keep.Password.dataAccess;

import com.Keep.Password.Model.User.UserData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.UUID;

@org.springframework.stereotype.Repository("UserRepository")
public interface UserRepository extends MongoRepository<UserData, UUID> {

    @Query(value = "{'email'}:?0")
    UserData findByEmail(String email);
    @Query(value = "{'userName'}:?0")
    UserData findByUserName(String userName);
}
