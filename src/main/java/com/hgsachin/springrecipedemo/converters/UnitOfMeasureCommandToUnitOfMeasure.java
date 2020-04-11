package com.hgsachin.springrecipedemo.converters;

import com.hgsachin.springrecipedemo.commands.UnitOfMeasureCommand;
import com.hgsachin.springrecipedemo.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
        if (null == unitOfMeasureCommand) {
            return null;
        }
        final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(unitOfMeasureCommand.getId());
        unitOfMeasure.setUnitOfMeasure(unitOfMeasureCommand.getUnitOfMeasure());
        return unitOfMeasure;
    }
}
