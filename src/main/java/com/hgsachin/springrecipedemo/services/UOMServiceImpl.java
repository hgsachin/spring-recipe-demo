package com.hgsachin.springrecipedemo.services;

import com.hgsachin.springrecipedemo.commands.UnitOfMeasureCommand;
import com.hgsachin.springrecipedemo.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.hgsachin.springrecipedemo.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UOMServiceImpl implements UOMService {

    private final UnitOfMeasureRepository repository;
    private final UnitOfMeasureToUnitOfMeasureCommand toUnitOfMeasureCommand;

    public UOMServiceImpl(UnitOfMeasureRepository repository,
                          UnitOfMeasureToUnitOfMeasureCommand toUnitOfMeasureCommand) {
        this.repository = repository;
        this.toUnitOfMeasureCommand = toUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> getAllUOMs() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(toUnitOfMeasureCommand::convert)
                .collect(Collectors.toSet());
    }
}
