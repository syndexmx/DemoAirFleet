package com.github.syndexmx.demoairfleet.repositories;


import com.github.syndexmx.demoairfleet.repositories.entities.AircraftEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;


@Repository
public interface AircraftRepository extends JpaRepository<AircraftEntity, UUID> {
}
