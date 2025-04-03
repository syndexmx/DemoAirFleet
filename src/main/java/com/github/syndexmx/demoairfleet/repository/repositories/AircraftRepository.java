package com.github.syndexmx.demoairfleet.repository.repositories;


import com.github.syndexmx.demoairfleet.repository.entities.AircraftEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AircraftRepository extends JpaRepository<AircraftEntity, Long> {
}
