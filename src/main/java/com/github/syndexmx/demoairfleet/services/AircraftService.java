package com.github.syndexmx.demoairfleet.services;

import com.github.syndexmx.demoairfleet.domain.Aircraft;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AircraftService {

    Aircraft create(Aircraft aircraft);

    Aircraft save(Aircraft aircraft);

    Optional<Aircraft> findById(Long aircraftId);

    List<Aircraft> listAll();

    boolean isPresent(Long aircraftId);

    boolean isPresent(Aircraft aircraft);

    void deleteById(Long aircraftId);

}
