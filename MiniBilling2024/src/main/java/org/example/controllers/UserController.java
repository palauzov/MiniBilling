package org.example.controllers;

import org.example.JsonToFile;
import org.example.entities.User;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    UserService userService;
    JsonToFile jsonToFile;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
         this.jsonToFile = new JsonToFile();
    }

  @GetMapping("/users/{id}")
  public void getInfoForUser(@PathVariable Long id){
        User user = userService.getUserById(id);
        String output = jsonToFile.toJson(user);
        System.out.println(output);
  }



   @PostMapping("/users")
  public void addUser(){
        userService.createUser();
  }
}
