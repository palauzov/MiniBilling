package org.example.services;

import org.example.Parsers.LocalDateTimeParser;
import org.example.entities.Reading;
import org.example.entities.User;
import org.example.repositories.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@Service
public class ReadingService {

    @Autowired
    ReadingRepository readingRepository;
    Scanner scanner = new Scanner(System.in);
    LocalDateTimeParser parser = new LocalDateTimeParser();

    public void saveAll(List<Reading> readings){
        readingRepository.saveAllAndFlush(readings);
    }

    public void save(Reading reading){readingRepository.saveAndFlush(reading);}

    public Reading createReading(User user) {
        System.out.print("Product: ");
        String product = scanner.nextLine();
        System.out.print("Value: ");
        double value = Double.parseDouble(scanner.nextLine());
        System.out.print("DateTime in format yyyy-MM--dd hh:mm:ss: ");
        String stringDateTime = scanner.nextLine();
        LocalDateTime dateTime = parser.parseToLocalDateTime(stringDateTime);
        return new Reading(user, product, dateTime, value);
    }

    public List<Reading> getReadingByUserId(Long id){
        return readingRepository.findAllReadingsByUserClientId(id).orElseThrow();
    }
}
