package com.hgsachin.springrecipedemo.services;

import com.hgsachin.springrecipedemo.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UOMService {

    Set<UnitOfMeasureCommand> getAllUOMs();
}
