package com.github.syndexmx.demoairfleet.impl;


import com.github.syndexmx.demoairfleet.domain.Aircraft;
import com.github.syndexmx.demoairfleet.domain.AircraftTestSupplierKit;
import com.github.syndexmx.demoairfleet.repository.entities.AircraftEntity;
import com.github.syndexmx.demoairfleet.repository.mappers.AircraftEntityMapper;
import com.github.syndexmx.demoairfleet.repository.repositories.AircraftRepository;
import com.github.syndexmx.demoairfleet.services.impl.AircraftServiceImpl;
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
public class AircraftServiceImplTest {

    @Mock
    private AircraftRepository aircraftRepository;

    @InjectMocks
    private AircraftServiceImpl underTest;

    @Test
    public void testThatAircraftIsCreated() {
        Aircraft aircraft = AircraftTestSupplierKit.getTestAircraft();
        AircraftEntityMapper aircraftEntityMapper = new AircraftEntityMapper(); // TO DO: make it work
        AircraftEntity aircraftEntity = aircraftEntityMapper.aircraftToAircraftEntity(aircraft);
        when(aircraftRepository.save(any())).thenReturn(aircraftEntity);
        final Aircraft savedAircraft = underTest.create(aircraft);
        aircraft.setId(savedAircraft.getId());
        assertEquals(aircraft, savedAircraft);
    }

    @Test
    public void testThatAircraftIsSaved() {
        final Aircraft aircraft = AircraftTestSupplierKit.getTestAircraft();
        AircraftEntityMapper aircraftEntityMapper = new AircraftEntityMapper(); // TO DO: make it work
        final AircraftEntity aircraftEntity = aircraftEntityMapper.aircraftToAircraftEntity(aircraft);
        when(aircraftRepository.save(eq(aircraftEntity))).thenReturn(aircraftEntity);
        final Aircraft savedAircraft = underTest.save(aircraft);
        assertEquals(aircraft, savedAircraft);
    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoEntity() {
        final Aircraft nonExistentAircraft = AircraftTestSupplierKit.getTestNonExistentAircraft();
        final Long nonExistentId = nonExistentAircraft.getId();
        when(aircraftRepository.findById(eq(nonExistentId))).thenReturn(Optional.empty());
        final Optional<Aircraft> foundAircraft = underTest.findById(nonExistentId);
        assertEquals(Optional.empty(), foundAircraft);
    }

    @Test
    public void testThatFindByIdReturnsEntityWhenPresent() {
        final Aircraft aircraft = AircraftTestSupplierKit.getTestAircraft();
        AircraftEntityMapper aircraftEntityMapper = new AircraftEntityMapper(); // TO DO: make it work
        final AircraftEntity aircraftEntity = aircraftEntityMapper.aircraftToAircraftEntity(aircraft);
        final Long idString = aircraft.getId();
        when(aircraftRepository.findById(eq(idString))).thenReturn(Optional.of(aircraftEntity));
        final Optional<Aircraft> foundAircraft = underTest.findById(idString);
        assertEquals(Optional.of(aircraft), foundAircraft);
    }

    @Test
    public void testListAircraftsReturnsEmptyListWhenAbsent() {
        when(aircraftRepository.findAll()).thenReturn(new ArrayList<AircraftEntity>());
        final List<Aircraft> result = underTest.listAll();
        assertEquals(0, result.size());
    }

    @Test
    public void testListAircraftsReturnsListWhenExist() {
        final Aircraft aircraft = AircraftTestSupplierKit.getTestAircraft();
        AircraftEntityMapper aircraftEntityMapper = new AircraftEntityMapper(); // TO DO: make it work
        final AircraftEntity aircraftEntity = aircraftEntityMapper.aircraftToAircraftEntity(aircraft);
        List<AircraftEntity> listOfExisting = new ArrayList<>(List.of(aircraftEntity));
        when(aircraftRepository.findAll()).thenReturn(listOfExisting);
        final List<Aircraft> result = underTest.listAll();
        assertEquals(listOfExisting.size(), result.size());
    }

    @Test
    public void testThatIsPresentReturnsFalseWhenAbsent() {
        when(aircraftRepository.existsById(any())).thenReturn(false);
        final Aircraft nonExistentAircraft = AircraftTestSupplierKit.getTestNonExistentAircraft();
        final Long nonExistentUuid = nonExistentAircraft.getId();
        boolean result = underTest.isPresent(nonExistentUuid);
        assertFalse(result);
    }

    @Test
    public void testThatIsPresentReturnsTrueWhenExists() {
        final Aircraft aircraft = AircraftTestSupplierKit.getTestAircraft();
        final Long idString = aircraft.getId();
        when(aircraftRepository.existsById(idString)).thenReturn(true);
        boolean result = underTest.isPresent(idString);
        assertTrue(result);
    }

    @Test
    public void testThatAircraftIsPresentReturnsFalseWhenAbsent() {
        when(aircraftRepository.existsById(any())).thenReturn(false);
        final Aircraft nonExistentAircraft = AircraftTestSupplierKit.getTestNonExistentAircraft();
        boolean result = underTest.isPresent(nonExistentAircraft);
        assertFalse(result);
    }

    @Test
    public void testThatAircraftIsPresentReturnsTrueWhenExists() {
        final Aircraft aircraft = AircraftTestSupplierKit.getTestAircraft();
        final Long id = aircraft.getId();
        when(aircraftRepository.existsById(id)).thenReturn(true);
        boolean result = underTest.isPresent(aircraft);
        assertTrue(result);
    }

    @Test
    public void testThatDeleteAircraftDeletesAircraft() {
        final Aircraft aircraft = AircraftTestSupplierKit.getTestAircraft();
        final Long idString = aircraft.getId();
        underTest.deleteById(idString);
        verify(aircraftRepository).deleteById(eq(idString));
    }
}
