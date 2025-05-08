package com.research_assistant.research_assistant.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.research_assistant.research_assistant.request_and_response.ResearchRequest;
import com.research_assistant.research_assistant.request_and_response.GeminiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class ResearchService {

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public ResearchService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.build();
        this.objectMapper = objectMapper;
    }

    public String processContent(ResearchRequest request) {
        String prompt = buildPrompt(request);

        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );

        String response = webClient.post()
                .uri(geminiApiUrl + geminiApiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return extractTextFromResponse(response);

    }

    private String extractTextFromResponse(String response) {
        try {
            GeminiResponse geminiResponse = objectMapper.readValue(response, GeminiResponse.class);
            if (geminiResponse.getCandidates() != null && !geminiResponse.getCandidates().isEmpty()) {
                GeminiResponse.Candidate firstCandidate = geminiResponse.getCandidates().get(0);
                if (firstCandidate.getContent() != null &&
                        firstCandidate.getContent().getParts() != null &&
                        !firstCandidate.getContent().getParts().isEmpty()) {
                    return firstCandidate.getContent().getParts().get(0).getText();
                }
            }
            return "No Content Found in Response";
        } catch (Exception e) {
            return "Error Parsing Response: " + e.getMessage();
        }
    }

    private String buildPrompt(ResearchRequest request) {
        StringBuilder prompt = new StringBuilder();
        switch (request.getOperation()) {
            case "summarize":
                prompt.append("Provide a clear and concise summary of the following text in a few sentences:\n\n");
                break;

            case "suggest":
                prompt.append("Based on the following content, suggest related topics and further readings. Format the response with clear headings and bullet points:\n\n");
                break;

            case "extract_keywords":
                prompt.append("Extract key terms and phrases from the content:\n\n");
                break;

            case "translate":
                prompt.append("Translate the following text into English:\n\n");
                break;

            case "eli5":
                prompt.append("Explain this in simple terms as if I were five years old:\n\n");
                break;

            case "find_similar":
                prompt.append("Find research papers related to:\n\n");
                break;

            case "generate questions":
                prompt.append("Generate research questions based on:\n\n");
                break;

            case "paraphrase":
                prompt.append("Reword the following text while keeping the original meaning:\n\n");
                break;

            case "spell_check":
                prompt.append("Check the spelling and suggest corrections for the following text:\n\n");
                break;

            case "grammar_check":
                prompt.append("Identify and correct grammatical errors in the following text:\n\n");
                break;

            case "tone_analysis":
                prompt.append("Analyze the tone of the following text and provide feedback:\n\n");
                break;

            case "sentiment_analysis":
                prompt.append("Determine the sentiment (positive, negative, neutral) of the following text:\n\n");
                break;

            case "summarize_bullets":
                prompt.append("Summarize the following text in bullet points:\n\n");
                break;

            case "convert_to_formal":
                prompt.append("Convert the following informal text into a formal style:\n\n");
                break;

            case "convert_to_casual":
                prompt.append("Make the following formal text sound more casual and conversational:\n\n");
                break;

            case "detect_language":
                prompt.append("Identify the language used in the following text:\n\n");
                break;

            default:
                throw new IllegalArgumentException("Unknown Operation: " + request.getOperation());
        }

        prompt.append(request.getContent());
        return prompt.toString();
    }
}
