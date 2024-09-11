package org.example.repositories;

import org.example.entities.PriceChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriceChartRepository extends JpaRepository<PriceChart, Long> {

}
