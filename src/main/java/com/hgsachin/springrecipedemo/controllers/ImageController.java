package com.hgsachin.springrecipedemo.controllers;

import com.hgsachin.springrecipedemo.services.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(path = "/recipe/{recipeId}/image/upload")
    public String uploadImagePage(@PathVariable String recipeId, Model model) {
        model.addAttribute("recipeId", Long.parseLong(recipeId));
        return "recipe/image/imageForm";
    }

    @PostMapping(path = "/recipe/{recipeId}/image")
    public String saveImage(@PathVariable String recipeId, @RequestParam("imagefile") MultipartFile imageFile) {
        imageService.saveImage(Long.parseLong(recipeId), imageFile);
        return "redirect:/recipe/" + recipeId + "/show";
    }
}
