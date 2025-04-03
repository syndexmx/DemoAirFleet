package com.github.syndexmx.demoairfleet.services;

import com.github.syndexmx.demoairfleet.domain.Flight;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FlightService {

    Flight create(Flight flight);

    Flight save(Flight flight);

    Optional<Flight> findById(String flightId);

    List<Flight> listAll();

    boolean isPresent(String flightId);

    boolean isPresent(Flight flight);

    void deleteById(String flightId);

}
