package com.github.syndexmx.demoairfleet.services;


import com.github.syndexmx.demoairfleet.domain.EngineType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface EngineTypeService {

    EngineType create(EngineType engineType);

    EngineType save(EngineType engineType);

    Optional<EngineType> findById(String enginetypeId);

    List<EngineType> listAll();

    boolean isPresent(String enginetypeId);

    boolean isPresent(EngineType engineType);

    void deleteById(String enginetypeId);

}
