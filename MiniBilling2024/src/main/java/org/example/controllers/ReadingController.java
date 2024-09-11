package org.example.controllers;

import org.example.JsonToFile;
import org.example.entities.Reading;
import org.example.entities.User;
import org.example.services.ReadingService;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReadingController {
    ReadingService readingService;
    UserService userService;

    JsonToFile jsonToFile;

    @Autowired
    public ReadingController(ReadingService readingService, UserService userService){
        this.readingService = readingService;
        this.userService = userService;
        this.jsonToFile = new JsonToFile();
    }

    @PostMapping("users/{id}/readings")
    public void createReadingForUser(@PathVariable Long id){
        User user = userService.getUserById(id);
        Reading reading = readingService.createReading(user);
        readingService.save(reading);
    }

    @GetMapping("users/{id}/readings")
    public void getReadingsForUser(@PathVariable Long id){
        List<Reading> readingList = readingService.getReadingByUserId(id);
        String output = jsonToFile.toJson(readingList);
        System.out.println(output);
    }
}
