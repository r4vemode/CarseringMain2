package com.example.carseringmain2;

import com.example.carseringmain2.Entity.UserEntity;
import com.example.carseringmain2.exception.UserAlreadyExistException;
import com.example.carseringmain2.exception.UserNotFoundException;
import com.example.carseringmain2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user) {
        UserEntity _user = new UserEntity(); //создание юзера в контроллере
        _user.setUsername(user.getUsername());
        _user.setPassword(user.getPassword());
        try {
            userService.registration(_user);
            return ResponseEntity.ok("User verified");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Server error ");
        }
    }


    @GetMapping
    public ResponseEntity getOneUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(userService.getOne(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

}
