package com.github.syndexmx.demoairfleet.services;


import com.github.syndexmx.demoairfleet.domain.PlaneType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface PlaneTypeService {

    PlaneType create(PlaneType planeType);

    PlaneType save(PlaneType planeType);

    Optional<PlaneType> findById(String planetypeId);

    List<PlaneType> listAll();

    boolean isPresent(String planetypeId);

    boolean isPresent(PlaneType planeType);

    void deleteById(String planetypeId);

}
