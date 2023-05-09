package com.example.carseringmain2.service;

import com.example.carseringmain2.Entity.UserEntity;
import com.example.carseringmain2.repository.UserRep;
import com.example.carseringmain2.exception.UserAlreadyExistException;
import com.example.carseringmain2.exception.UserNotFoundException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRep userRep;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if (userRep.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("Пользователь с таким именем существует");
        }
        return userRep.save(user);
    }

    public UserEntity getOne(Long id) throws UserNotFoundException {
        UserEntity user = userRep.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return user;
    }
}
