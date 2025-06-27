package com.example.SpringAi.Controller;

import com.example.SpringAi.Service.ChatService;
import com.example.SpringAi.Service.ImageService;
import com.example.SpringAi.Service.MedicalService;
import com.example.SpringAi.Service.RecipeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
public class GeiAiController {


    private final ChatService chatService;
    private final RecipeService recipeService;
    private final ImageService imageService;
    private MedicalService medicalService;


    @GetMapping("generate-chat")
    public String getChatResponse(@RequestParam String prompt) {

        return chatService.getResponse(prompt);
    }

    @GetMapping("ask-ai-options")
    public String getChatResponseOptions(@RequestParam String prompt) {
        return chatService.getResponseOptions(prompt);
    }

//    @GetMapping("image-ai")
//    public void generateImage(HttpServletResponse Response, @RequestParam String prompt) throws IOException {
//        ImageResponse imageResponse = imageService.generateImage(prompt);
//        String ImageUrl = imageResponse.getResults().get(0).getOutput().getUrl();
//        Response.sendRedirect(ImageUrl);
//    }

//    alternative to the above commented method

    @GetMapping("generate-image")
    public ResponseEntity<Void> redirectToImage(@RequestParam String prompt) {
        String imageUrl = imageService.generatePollinationsUrl(prompt);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(imageUrl));
        return ResponseEntity.status(302).headers(headers).build();
    }

    @GetMapping("generate-recipe")
    public String recipeCreator(@RequestParam String ingredients,
                                @RequestParam(defaultValue = "any") String cuisine,
                                @RequestParam(defaultValue = "") String dietaryRestriction) {

        return recipeService.createRecipe(ingredients, cuisine, dietaryRestriction);
    }
    @GetMapping("generate-medical-concern")
    public String getMedicalDescription(@RequestParam  String symptoms,
                                        @RequestParam  String age,
                                        @RequestParam  String gender,
                                        @RequestParam  String conditionSeverity,
                                        @RequestParam  String previousConditions) {

        return medicalService.getMedicalDescription(symptoms, age,gender, conditionSeverity, previousConditions);
    }
}
