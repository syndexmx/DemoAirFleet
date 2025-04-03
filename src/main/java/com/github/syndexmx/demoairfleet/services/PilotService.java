package com.github.syndexmx.demoairfleet.services;


import com.github.syndexmx.demoairfleet.domain.Pilot;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface PilotService {

    Pilot create(Pilot pilot);

    Pilot save(Pilot pilot);

    Optional<Pilot> findById(String pilotId);

    List<Pilot> listAll();

    boolean isPresent(String pilotId);

    boolean isPresent(Pilot pilot);

    void deleteById(String pilotId);

}
