package com.example.SpringAi.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MedicalService {
    private final ChatModel chatModel;

    public String getMedicalDescription(String symptoms,
                                        String age,
                                        String gender,
                                        String conditionSeverity,
                                        String previousConditions) {

        String template = """
                I have the following symptoms: {symptoms}.
                My age is {age}, gender is {gender}, and I describe the condition as {conditionSeverity}.
                I also have the following previous health conditions: {previousConditions}.
                
                Based on this, provide a simple and clear explanation of what possible health issues could be occurring.
                Do not provide a diagnosis. This should be basic and educational only.
                """;

        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String, Object> params = Map.of(
                "symptoms", symptoms,
                "age", age,
                "gender", gender,
                "conditionSeverity", conditionSeverity,
                "previousConditions", previousConditions
        );

        Prompt prompt = promptTemplate.create(params);

        try {
            return chatModel.call(prompt).getResult().getOutput().getText();
        } catch (Exception e) {
            e.printStackTrace(); // log full stack trace
            return "An error occurred while generating the medical explanation: " + e.getMessage();
        }
    }
}
