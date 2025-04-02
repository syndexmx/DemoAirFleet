package com.github.syndexmx.demoairfleet.repositories;


import com.github.syndexmx.demoairfleet.repositories.entities.EngineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;


@Repository
public interface EngineRepository extends JpaRepository<EngineEntity, UUID> {
}
