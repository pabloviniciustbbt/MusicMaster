package com.pabloleal.musicmaster.services;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

public class ConsultaGemini {

    public static String obterDadosArtista(String nomeArtista){

        ChatLanguageModel gemini = GoogleAiGeminiChatModel.builder()
                .apiKey(System.getenv("GEMINI_AI_KEY"))
                .modelName("gemini-2.0-flash")
                .build();

        ChatResponse chatResponse = gemini.chat(ChatRequest.builder()
                .messages(UserMessage.from(
                        "Me fale sobre o Artista" + nomeArtista
                ))
                .build());

        String response = chatResponse.aiMessage().text();

        return response;
    }
}
