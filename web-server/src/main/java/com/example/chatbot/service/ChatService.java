package com.example.chatbot.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.chatbot.dto.ChatRequest;

@Service
public class ChatService {

  private final RestTemplate restTemplate = new RestTemplate();

  private final String FASTAPI_URL = "http://localhost:8000/chat";

  public String sendMessage(ChatRequest request) {

    // Build request body for FastAPI
    Map<String, String> fastApiRequest = new HashMap<>();
    fastApiRequest.put("session_id", request.getSessionId());
    fastApiRequest.put("message", request.getMessage());

    // Call FastAPI
    Map response = restTemplate.postForObject(FASTAPI_URL, fastApiRequest, Map.class);

    // Extract response
    String sessionId = (String) response.get("session_id");
    String answer = (String) response.get("answer");

    return answer;
  }
}
