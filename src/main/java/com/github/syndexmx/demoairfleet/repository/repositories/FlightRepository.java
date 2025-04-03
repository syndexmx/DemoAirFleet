package com.github.syndexmx.demoairfleet.repository.repositories;

import com.github.syndexmx.demoairfleet.repository.entities.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, UUID> {
}
