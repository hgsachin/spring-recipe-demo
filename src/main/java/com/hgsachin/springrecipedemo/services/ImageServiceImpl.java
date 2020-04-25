package com.hgsachin.springrecipedemo.services;

import com.hgsachin.springrecipedemo.domain.Recipe;
import com.hgsachin.springrecipedemo.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImage(Long id, MultipartFile file) {
        try {
            Recipe recipe = recipeRepository.findById(id).orElse(null);
            if (null != recipe) {
                Byte[] bytes = new Byte[file.getBytes().length];
                int i = 0;
                for (byte b : file.getBytes()) {
                    bytes[i++] = b;
                }
                recipe.setImage(bytes);
                recipeRepository.save(recipe);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
