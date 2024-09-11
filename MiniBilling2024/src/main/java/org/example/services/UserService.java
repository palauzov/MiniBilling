package org.example.services;

import org.example.entities.PriceChart;
import org.example.entities.Reading;
import org.example.entities.User;
import org.example.repositories.PriceChartRepository;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PriceChartRepository priceChartRepository;
    Scanner scanner = new Scanner(System.in);



    public User getUserByClientId(List<User> users, Reading reading){
        for (int i = 0; i < users.size(); i++) {
            if (reading.getUser().getClientId() == users.get(i).getClientId()){
                return users.get(i);
            }
        }
        return null;
    }

    public void save(List<User> users){
        userRepository.saveAllAndFlush(users);
    }

    public void createUser(){
        System.out.print("Name: ");
        String name = scanner.nextLine();
        PriceChart priceChart = priceChartRepository.findById(1L).orElseThrow();

        User user = new User(name, priceChart);
        userRepository.saveAndFlush(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow();
    }

}
