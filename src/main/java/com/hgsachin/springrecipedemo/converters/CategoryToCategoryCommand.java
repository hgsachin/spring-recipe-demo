package com.hgsachin.springrecipedemo.converters;

import com.hgsachin.springrecipedemo.commands.CategoryCommand;
import com.hgsachin.springrecipedemo.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category category) {
        if (category == null) {
            return null;
        }
        final CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setDescription(category.getDescription());
        categoryCommand.setId(category.getId());
        return categoryCommand;
    }
}
