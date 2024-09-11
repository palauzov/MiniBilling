package org.example.services;

import org.example.entities.Reading;
import org.example.entities.StandingCharge;
import org.example.repositories.StandingChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class StandingChargeService {

      StandingChargeRepository standingChargeRepository;

      @Autowired
      public StandingChargeService(StandingChargeRepository standingChargeRepository){
            this.standingChargeRepository = standingChargeRepository;
      }
      public StandingCharge createStandingCharge(Reading lastReading, Reading newReading, double price){
            LocalDateTime startDate = lastReading.getDate();
            LocalDateTime endDate = newReading.getDate();
            Duration duration = Duration.between(startDate, endDate);
            int quantity = (int) duration.toDays();
            double amountWithoutVat = quantity * price;
            double vat = 0.2 * amountWithoutVat;
            double amountWithVat = amountWithoutVat + vat;
            return new StandingCharge(quantity, price,  startDate, endDate, amountWithoutVat, vat, amountWithVat);
      }

      public void save(StandingCharge standingCharge){
            standingChargeRepository.saveAndFlush(standingCharge);
      }
}
