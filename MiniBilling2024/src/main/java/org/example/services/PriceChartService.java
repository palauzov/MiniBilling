package org.example.services;

import org.example.entities.PriceChart;
import org.example.repositories.PriceChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceChartService {
    @Autowired
    PriceChartRepository priceChartRepository;


    public void save(List<PriceChart> priceCharts){
        priceChartRepository.saveAllAndFlush(priceCharts);
    }
}
