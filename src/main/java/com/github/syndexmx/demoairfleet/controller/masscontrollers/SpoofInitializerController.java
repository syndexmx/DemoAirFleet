package com.github.syndexmx.demoairfleet.controller.masscontrollers;

import com.github.syndexmx.demoairfleet.controller.masscontrollers.spoofs.SpoofAircrafts;
import com.github.syndexmx.demoairfleet.controller.masscontrollers.spoofs.SpoofEngines;
import com.github.syndexmx.demoairfleet.controller.masscontrollers.spoofs.SpoofFlights;
import com.github.syndexmx.demoairfleet.controller.masscontrollers.spoofs.SpoofPilots;
import com.github.syndexmx.demoairfleet.domain.Aircraft;
import com.github.syndexmx.demoairfleet.domain.Engine;
import com.github.syndexmx.demoairfleet.domain.Flight;
import com.github.syndexmx.demoairfleet.domain.Pilot;
import com.github.syndexmx.demoairfleet.services.AircraftService;
import com.github.syndexmx.demoairfleet.services.EngineService;
import com.github.syndexmx.demoairfleet.services.FlightService;
import com.github.syndexmx.demoairfleet.services.PilotService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@Tag(name = "Spoof Initializer", description = "API Автоматического пробного заполнения")
public class SpoofInitializerController {

    private final String ROOT_API_PATH = "/api/v0/initializer";

    private final AircraftService aircraftService;
    private final EngineService engineService;
    private final FlightService flightService;
    private final PilotService pilotService;

    @Autowired
    public SpoofInitializerController(AircraftService aircraftService, EngineService engineService, FlightService flightService, PilotService pilotService) {
        this.aircraftService = aircraftService;
        this.engineService = engineService;
        this.flightService = flightService;
        this.pilotService = pilotService;
    }

    @PostMapping(ROOT_API_PATH)
    public ResponseEntity<String> create() {
        log.info("POST " + ROOT_API_PATH);
        StringBuilder response = new StringBuilder();

        final Engine leftEngine = engineService.create(SpoofEngines.getEngine(0L));
        response.append( leftEngine.toString() + "\n");
        final Engine rightEngine = engineService.create(SpoofEngines.getEngine(1L));
        response.append( rightEngine.toString() + "\n");
        final Aircraft aircraft = aircraftService.create(
                SpoofAircrafts.getAircraft(0L, List.of(leftEngine, rightEngine)));
        response.append( aircraft.toString() + "\n");
        final Pilot captain = pilotService.create(SpoofPilots.getPilot(0L));
        response.append( captain.toString() + "\n");
        final Pilot firstOfficer = pilotService.create(SpoofPilots.getPilot(1L));
        response.append( firstOfficer.toString() + "\n");
        final Flight flight = flightService.create(SpoofFlights.getFlight(0L,
                aircraft, captain, firstOfficer));
        response.append( flight.toString() + "\n");

        final ResponseEntity<String> responseEntity =
                new ResponseEntity<> (response.toString(), HttpStatus.CREATED);
        return responseEntity;
    }

}
