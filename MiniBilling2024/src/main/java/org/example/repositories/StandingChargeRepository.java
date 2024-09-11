package org.example.repositories;

import org.example.entities.StandingCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StandingChargeRepository extends JpaRepository<StandingCharge, Long> {


}
