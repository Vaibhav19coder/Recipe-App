package com.Recipe.RecipeApplication.converters;

import com.Recipe.RecipeApplication.commands.UnitOfMeasureCommand;
import com.Recipe.RecipeApplication.domains.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        if(source==null)
            return null;
        UnitOfMeasureCommand unitOfMeasureCommand=new UnitOfMeasureCommand();
        unitOfMeasureCommand.setUnitOfMeasure(source.getUnitOfMeasure());
        unitOfMeasureCommand.setId(source.getId());
        return unitOfMeasureCommand;
    }
}
