package org.example.repositories;

import org.example.entities.Line;
import org.example.entities.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.naming.RefAddr;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, Long> {


   Optional<List<Reading>> findAllReadingsByUserClientId(Long clientId);
}
