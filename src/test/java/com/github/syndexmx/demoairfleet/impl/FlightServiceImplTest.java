package com.github.syndexmx.demoairfleet.impl;


import com.github.syndexmx.demoairfleet.domain.Flight;
import com.github.syndexmx.demoairfleet.domain.FlightTestSupplierKit;
import com.github.syndexmx.demoairfleet.repository.entities.FlightEntity;
import com.github.syndexmx.demoairfleet.repository.mappers.FlightEntityMapper;
import com.github.syndexmx.demoairfleet.repository.repositories.FlightRepository;
import com.github.syndexmx.demoairfleet.services.impl.FlightServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class FlightServiceImplTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightServiceImpl underTest;

    @Test
    public void testThatFlightIsCreated() {
        Flight flight = FlightTestSupplierKit.getTestFlight();
        FlightEntityMapper flightEntityMapper = new FlightEntityMapper(); // TO DO: make it work
        FlightEntity flightEntity = flightEntityMapper.flightToFlightEntity(flight);
        when(flightRepository.save(any())).thenReturn(flightEntity);
        final Flight savedFlight = underTest.create(flight);
        flight.setId(savedFlight.getId());
        assertEquals(flight, savedFlight);
    }

    @Test
    public void testThatFlightIsSaved() {
        final Flight flight = FlightTestSupplierKit.getTestFlight();
        FlightEntityMapper flightEntityMapper = new FlightEntityMapper(); // TO DO: make it work
        final FlightEntity flightEntity = flightEntityMapper.flightToFlightEntity(flight);
        when(flightRepository.save(eq(flightEntity))).thenReturn(flightEntity);
        final Flight savedFlight = underTest.save(flight);
        assertEquals(flight, savedFlight);
    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoEntity() {
        final Flight nonExistentFlight = FlightTestSupplierKit.getTestNonExistentFlight();
        final String nonExistentId = nonExistentFlight.getId().toString();
        when(flightRepository.findById(eq(UUID.fromString(nonExistentId)))).thenReturn(Optional.empty());
        final Optional<Flight> foundFlight = underTest.findById(nonExistentId);
        assertEquals(Optional.empty(), foundFlight);
    }

    @Test
    public void testThatFindByIdReturnsEntityWhenPresent() {
        final Flight flight = FlightTestSupplierKit.getTestFlight();
        FlightEntityMapper flightEntityMapper = new FlightEntityMapper(); // TO DO: make it work
        final FlightEntity flightEntity = flightEntityMapper.flightToFlightEntity(flight);
        final String idString = flight.getId().toString();
        when(flightRepository.findById(eq(UUID.fromString(idString)))).thenReturn(Optional.of(flightEntity));
        final Optional<Flight> foundFlight = underTest.findById(idString);
        assertEquals(Optional.of(flight), foundFlight);
    }

    @Test
    public void testListFlightsReturnsEmptyListWhenAbsent() {
        when(flightRepository.findAll()).thenReturn(new ArrayList<FlightEntity>());
        final List<Flight> result = underTest.listAll();
        assertEquals(0, result.size());
    }

    @Test
    public void testListFlightsReturnsListWhenExist() {
        final Flight flight = FlightTestSupplierKit.getTestFlight();
        FlightEntityMapper flightEntityMapper = new FlightEntityMapper(); // TO DO: make it work
        final FlightEntity flightEntity = flightEntityMapper.flightToFlightEntity(flight);
        List<FlightEntity> listOfExisting = new ArrayList<>(List.of(flightEntity));
        when(flightRepository.findAll()).thenReturn(listOfExisting);
        final List<Flight> result = underTest.listAll();
        assertEquals(listOfExisting.size(), result.size());
    }

    @Test
    public void testThatIsPresentReturnsFalseWhenAbsent() {
        when(flightRepository.existsById(any())).thenReturn(false);
        final Flight nonExistentFlight = FlightTestSupplierKit.getTestNonExistentFlight();
        final String nonExistentUuid = nonExistentFlight.getId().toString();
        boolean result = underTest.isPresent(nonExistentUuid);
        assertFalse(result);
    }

    @Test
    public void testThatIsPresentReturnsTrueWhenExists() {
        final Flight flight = FlightTestSupplierKit.getTestFlight();
        final String idString = flight.getId().toString();
        when(flightRepository.existsById(UUID.fromString(idString))).thenReturn(true);
        boolean result = underTest.isPresent(idString);
        assertTrue(result);
    }

    @Test
    public void testThatFlightIsPresentReturnsFalseWhenAbsent() {
        when(flightRepository.existsById(any())).thenReturn(false);
        final Flight nonExistentFlight = FlightTestSupplierKit.getTestNonExistentFlight();
        boolean result = underTest.isPresent(nonExistentFlight);
        assertFalse(result);
    }

    @Test
    public void testThatFlightIsPresentReturnsTrueWhenExists() {
        final Flight flight = FlightTestSupplierKit.getTestFlight();
        final String idString = flight.getId().toString();
        when(flightRepository.existsById(UUID.fromString(idString))).thenReturn(true);
        boolean result = underTest.isPresent(flight);
        assertTrue(result);
    }

    @Test
    public void testThatDeleteFlightDeletesFlight() {
        final Flight flight = FlightTestSupplierKit.getTestFlight();
        final String idString = flight.getId().toString();
        underTest.deleteById(idString);
        verify(flightRepository).deleteById(eq(UUID.fromString(idString)));
    }
}
