package org.example;

import org.example.entities.PriceChart;
import org.example.entities.Reading;
import org.example.entities.User;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Measurement {

   public double calculateAmount(int quantity, Reading lastReading, Reading newReading, List<PriceChart> priceChartList){
       LocalDateTime startDate = lastReading.getDate();
       LocalDateTime endDate = newReading.getDate();
       Duration duration = Duration.between(startDate, endDate);
       long allDays = duration.toDays();
       double amount = 0;
       for (int i = 0; i < priceChartList.size(); i++) {
           if (startDate.isAfter(priceChartList.get(i).getStartDate().atTime(0, 0, 0)) && startDate.isBefore(priceChartList.get(i).getEndDate().atTime(0,0,0))){
               if (endDate.isBefore(priceChartList.get(i).getEndDate().atTime(0,0,0))){
                   amount = quantity * priceChartList.get(i).getPrice();
               }else {
                   long firstPeriodDays = Duration.between(startDate, priceChartList.get(i).getEndDate().atTime(0,0,0)).toDays();
                   long secondPeriodDays = allDays - firstPeriodDays;
                   double amountFirstPeriod = quantity * ((double)firstPeriodDays / allDays) * priceChartList.get(i).getPrice();
                   double amountSecondPeriod = quantity * ((double)secondPeriodDays / allDays) * priceChartList.get(i + 1).getPrice();
                   amount = amountFirstPeriod + amountSecondPeriod;
               }
           }
       }
       return Math.round(amount * 100) / 100.0;
   }
}
