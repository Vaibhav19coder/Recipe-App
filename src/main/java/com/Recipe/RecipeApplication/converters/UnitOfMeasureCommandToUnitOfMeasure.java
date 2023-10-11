package com.Recipe.RecipeApplication.converters;

import com.Recipe.RecipeApplication.commands.UnitOfMeasureCommand;
import com.Recipe.RecipeApplication.domains.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if(source==null)
            return null;
        UnitOfMeasure unitOfMeasure=new UnitOfMeasure();
        unitOfMeasure.setUnitOfMeasure(source.getUnitOfMeasure());
        unitOfMeasure.setId(source.getId());
        return unitOfMeasure;
    }
}
