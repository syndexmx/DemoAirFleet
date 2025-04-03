package com.github.syndexmx.demoairfleet.repository.repositories;


import com.github.syndexmx.demoairfleet.repository.entities.AircraftEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AircraftRepository extends JpaRepository<AircraftEntity, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "aircraft_entity-graph")
    List<AircraftEntity> findAll();

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "aircraft_entity-graph")
    Optional<AircraftEntity> findById(Long id);

}
