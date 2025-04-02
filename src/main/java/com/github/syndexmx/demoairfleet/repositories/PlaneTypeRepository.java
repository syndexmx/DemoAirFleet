package com.github.syndexmx.demoairfleet.repositories;


import com.github.syndexmx.demoairfleet.repositories.entities.PlaneTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;


@Repository
public interface PlaneTypeRepository extends JpaRepository<PlaneTypeEntity, UUID> {
}
