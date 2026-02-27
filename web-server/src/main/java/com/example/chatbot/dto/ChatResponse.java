package com.example.chatbot.dto;

public class ChatResponse {

  private String sessionId;
  private String answer;

  public ChatResponse() {}

  public ChatResponse(String sessionId, String answer) {
    this.sessionId = sessionId;
    this.answer = answer;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }
}
