package com.example.SpringAi.Service;

import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class ImageService {
    public String generatePollinationsUrl(String prompt) {
        String encodedPrompt = URLEncoder.encode(prompt, StandardCharsets.UTF_8);
        return "https://image.pollinations.ai/prompt/" + encodedPrompt;
    }
}