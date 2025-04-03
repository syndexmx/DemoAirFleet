package com.github.syndexmx.demoairfleet.services;


import com.github.syndexmx.demoairfleet.domain.Engine;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface EngineService {

    Engine create(Engine engine);

    Engine save(Engine engine);

    Optional<Engine> findById(String engineId);

    List<Engine> listAll();

    boolean isPresent(String engineId);

    boolean isPresent(Engine engine);

    void deleteById(String engineId);

}
