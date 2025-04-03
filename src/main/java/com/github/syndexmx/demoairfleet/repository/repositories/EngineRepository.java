package com.github.syndexmx.demoairfleet.repository.repositories;

import com.github.syndexmx.demoairfleet.repository.entities.EngineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineRepository extends JpaRepository<EngineEntity, Long> {
}
