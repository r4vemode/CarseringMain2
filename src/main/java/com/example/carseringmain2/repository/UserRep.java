package com.example.carseringmain2.repository;

import com.example.carseringmain2.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

public interface UserRep extends CrudRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
