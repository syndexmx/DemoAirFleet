package com.github.syndexmx.demoairfleet.impl;


import com.github.syndexmx.demoairfleet.domain.Engine;
import com.github.syndexmx.demoairfleet.domain.EngineTestSupplierKit;
import com.github.syndexmx.demoairfleet.repository.entities.EngineEntity;
import com.github.syndexmx.demoairfleet.repository.mappers.EngineEntityMapper;
import com.github.syndexmx.demoairfleet.repository.repositories.EngineRepository;
import com.github.syndexmx.demoairfleet.services.impl.EngineServiceImpl;
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
public class EngineServiceImplTest {

    @Mock
    private EngineRepository engineRepository;

    @InjectMocks
    private EngineServiceImpl underTest;

    @Test
    public void testThatengineIsCreated() {
        Engine engine = EngineTestSupplierKit.getTestengine();
        EngineEntityMapper engineEntityMapper = new EngineEntityMapper(); // TO DO: make it work
        EngineEntity engineEntity = engineEntityMapper.engineToengineEntity(engine);
        when(engineRepository.save(any())).thenReturn(engineEntity);
        final Engine savedEngine = underTest.create(engine);
        engine.setId(savedEngine.getId());
        assertEquals(engine, savedEngine);
    }

    @Test
    public void testThatengineIsSaved() {
        final Engine engine = EngineTestSupplierKit.getTestengine();
        EngineEntityMapper engineEntityMapper = new EngineEntityMapper(); // TO DO: make it work
        final EngineEntity engineEntity = engineEntityMapper.engineToengineEntity(engine);
        when(engineRepository.save(eq(engineEntity))).thenReturn(engineEntity);
        final Engine savedEngine = underTest.save(engine);
        assertEquals(engine, savedEngine);
    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoEntity() {
        final Engine nonExistentEngine = EngineTestSupplierKit.getTestNonExistentengine();
        final String nonExistentId = nonExistentEngine.getId().toString();
        when(engineRepository.findById(eq(UUID.fromString(nonExistentId)))).thenReturn(Optional.empty());
        final Optional<Engine> foundengine = underTest.findById(nonExistentId);
        assertEquals(Optional.empty(), foundengine);
    }

    @Test
    public void testThatFindByIdReturnsEntityWhenPresent() {
        final Engine engine = EngineTestSupplierKit.getTestengine();
        EngineEntityMapper engineEntityMapper = new EngineEntityMapper(); // TO DO: make it work
        final EngineEntity engineEntity = engineEntityMapper.engineToengineEntity(engine);
        final String idString = engine.getId().toString();
        when(engineRepository.findById(eq(UUID.fromString(idString)))).thenReturn(Optional.of(engineEntity));
        final Optional<Engine> foundengine = underTest.findById(idString);
        assertEquals(Optional.of(engine), foundengine);
    }

    @Test
    public void testListenginesReturnsEmptyListWhenAbsent() {
        when(engineRepository.findAll()).thenReturn(new ArrayList<EngineEntity>());
        final List<Engine> result = underTest.listAll();
        assertEquals(0, result.size());
    }

    @Test
    public void testListenginesReturnsListWhenExist() {
        final Engine engine = EngineTestSupplierKit.getTestengine();
        EngineEntityMapper engineEntityMapper = new EngineEntityMapper(); // TO DO: make it work
        final EngineEntity engineEntity = engineEntityMapper.engineToengineEntity(engine);
        List<EngineEntity> listOfExisting = new ArrayList<>(List.of(engineEntity));
        when(engineRepository.findAll()).thenReturn(listOfExisting);
        final List<Engine> result = underTest.listAll();
        assertEquals(listOfExisting.size(), result.size());
    }

    @Test
    public void testThatIsPresentReturnsFalseWhenAbsent() {
        when(engineRepository.existsById(any())).thenReturn(false);
        final Engine nonExistentEngine = EngineTestSupplierKit.getTestNonExistentengine();
        final String nonExistentUuid = nonExistentEngine.getId().toString();
        boolean result = underTest.isPresent(nonExistentUuid);
        assertFalse(result);
    }

    @Test
    public void testThatIsPresentReturnsTrueWhenExists() {
        final Engine engine = EngineTestSupplierKit.getTestengine();
        final String idString = engine.getId().toString();
        when(engineRepository.existsById(UUID.fromString(idString))).thenReturn(true);
        boolean result = underTest.isPresent(idString);
        assertTrue(result);
    }

    @Test
    public void testThatengineIsPresentReturnsFalseWhenAbsent() {
        when(engineRepository.existsById(any())).thenReturn(false);
        final Engine nonExistentEngine = EngineTestSupplierKit.getTestNonExistentengine();
        boolean result = underTest.isPresent(nonExistentEngine);
        assertFalse(result);
    }

    @Test
    public void testThatengineIsPresentReturnsTrueWhenExists() {
        final Engine engine = EngineTestSupplierKit.getTestengine();
        final String idString = engine.getId().toString();
        when(engineRepository.existsById(UUID.fromString(idString))).thenReturn(true);
        boolean result = underTest.isPresent(engine);
        assertTrue(result);
    }

    @Test
    public void testThatDeleteengineDeletesengine() {
        final Engine engine = EngineTestSupplierKit.getTestengine();
        final String idString = engine.getId().toString();
        underTest.deleteById(idString);
        verify(engineRepository).deleteById(eq(UUID.fromString(idString)));
    }
}
