package com.hgsachin.springrecipedemo.repositories;

import com.hgsachin.springrecipedemo.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByUnitOfMeasure(String unitOfMeasure);
}
