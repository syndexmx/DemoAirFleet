package com.github.syndexmx.demoairfleet.impl;


import com.github.syndexmx.demoairfleet.domain.Pilot;
import com.github.syndexmx.demoairfleet.domain.PilotTestSupplierKit;
import com.github.syndexmx.demoairfleet.repository.entities.PilotEntity;
import com.github.syndexmx.demoairfleet.repository.mappers.PilotEntityMapper;
import com.github.syndexmx.demoairfleet.repository.repositories.PilotRepository;
import com.github.syndexmx.demoairfleet.services.impl.PilotServiceImpl;
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
public class PilotServiceImplTest {

    @Mock
    private PilotRepository pilotRepository;

    @InjectMocks
    private PilotServiceImpl underTest;

    @Test
    public void testThatPilotIsCreated() {
        Pilot pilot = PilotTestSupplierKit.getTestPilot();
        PilotEntityMapper pilotEntityMapper = new PilotEntityMapper(); // TO DO: make it work
        PilotEntity pilotEntity = pilotEntityMapper.pilotToPilotEntity(pilot);
        when(pilotRepository.save(any())).thenReturn(pilotEntity);
        final Pilot savedPilot = underTest.create(pilot);
        pilot.setId(savedPilot.getId());
        assertEquals(pilot, savedPilot);
    }

    @Test
    public void testThatPilotIsSaved() {
        final Pilot pilot = PilotTestSupplierKit.getTestPilot();
        PilotEntityMapper pilotEntityMapper = new PilotEntityMapper(); // TO DO: make it work
        final PilotEntity pilotEntity = pilotEntityMapper.pilotToPilotEntity(pilot);
        when(pilotRepository.save(eq(pilotEntity))).thenReturn(pilotEntity);
        final Pilot savedPilot = underTest.save(pilot);
        assertEquals(pilot, savedPilot);
    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoEntity() {
        final Pilot nonExistentPilot = PilotTestSupplierKit.getTestNonExistentPilot();
        final String nonExistentId = nonExistentPilot.getId().toString();
        when(pilotRepository.findById(eq(UUID.fromString(nonExistentId)))).thenReturn(Optional.empty());
        final Optional<Pilot> foundPilot = underTest.findById(nonExistentId);
        assertEquals(Optional.empty(), foundPilot);
    }

    @Test
    public void testThatFindByIdReturnsEntityWhenPresent() {
        final Pilot pilot = PilotTestSupplierKit.getTestPilot();
        PilotEntityMapper pilotEntityMapper = new PilotEntityMapper(); // TO DO: make it work
        final PilotEntity pilotEntity = pilotEntityMapper.pilotToPilotEntity(pilot);
        final String idString = pilot.getId().toString();
        when(pilotRepository.findById(eq(UUID.fromString(idString)))).thenReturn(Optional.of(pilotEntity));
        final Optional<Pilot> foundPilot = underTest.findById(idString);
        assertEquals(Optional.of(pilot), foundPilot);
    }

    @Test
    public void testListPilotsReturnsEmptyListWhenAbsent() {
        when(pilotRepository.findAll()).thenReturn(new ArrayList<PilotEntity>());
        final List<Pilot> result = underTest.listAll();
        assertEquals(0, result.size());
    }

    @Test
    public void testListPilotsReturnsListWhenExist() {
        final Pilot pilot = PilotTestSupplierKit.getTestPilot();
        PilotEntityMapper pilotEntityMapper = new PilotEntityMapper(); // TO DO: make it work
        final PilotEntity pilotEntity = pilotEntityMapper.pilotToPilotEntity(pilot);
        List<PilotEntity> listOfExisting = new ArrayList<>(List.of(pilotEntity));
        when(pilotRepository.findAll()).thenReturn(listOfExisting);
        final List<Pilot> result = underTest.listAll();
        assertEquals(listOfExisting.size(), result.size());
    }

    @Test
    public void testThatIsPresentReturnsFalseWhenAbsent() {
        when(pilotRepository.existsById(any())).thenReturn(false);
        final Pilot nonExistentPilot = PilotTestSupplierKit.getTestNonExistentPilot();
        final String nonExistentUuid = nonExistentPilot.getId().toString();
        boolean result = underTest.isPresent(nonExistentUuid);
        assertFalse(result);
    }

    @Test
    public void testThatIsPresentReturnsTrueWhenExists() {
        final Pilot pilot = PilotTestSupplierKit.getTestPilot();
        final String idString = pilot.getId().toString();
        when(pilotRepository.existsById(UUID.fromString(idString))).thenReturn(true);
        boolean result = underTest.isPresent(idString);
        assertTrue(result);
    }

    @Test
    public void testThatPilotIsPresentReturnsFalseWhenAbsent() {
        when(pilotRepository.existsById(any())).thenReturn(false);
        final Pilot nonExistentPilot = PilotTestSupplierKit.getTestNonExistentPilot();
        boolean result = underTest.isPresent(nonExistentPilot);
        assertFalse(result);
    }

    @Test
    public void testThatPilotIsPresentReturnsTrueWhenExists() {
        final Pilot pilot = PilotTestSupplierKit.getTestPilot();
        final String idString = pilot.getId().toString();
        when(pilotRepository.existsById(UUID.fromString(idString))).thenReturn(true);
        boolean result = underTest.isPresent(pilot);
        assertTrue(result);
    }

    @Test
    public void testThatDeletePilotDeletesPilot() {
        final Pilot pilot = PilotTestSupplierKit.getTestPilot();
        final String idString = pilot.getId().toString();
        underTest.deleteById(idString);
        verify(pilotRepository).deleteById(eq(UUID.fromString(idString)));
    }
}
