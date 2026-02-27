package com.example.chatbot.controller;

import com.example.chatbot.dto.ChatRequest;
import com.example.chatbot.service.ChatService;
import java.io.IOException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin("*")
public class ChatController {
  private final ChatService chatService;

  public ChatController(ChatService chatService) {
    this.chatService = chatService;
  }

  @PostMapping("/stream")
  public SseEmitter chatStream(@RequestBody ChatRequest request) {

    SseEmitter emitter = new SseEmitter(0L); // no timeout

    new Thread(
            () -> {
              try {
                // 1️⃣ Simulate FastAPI call

                String answer = chatService.sendMessage(request);

                emitter.send(SseEmitter.event().name("answer").data(answer));

                // 2️⃣ Wait 800ms
                Thread.sleep(800);

                // 3️⃣ Send GIF
                emitter.send(
                    SseEmitter.event()
                        .name("gif")
                        .data(
                            "https://www.gif-vif.com/desi_gifs/good-question-you-are-a-good-question-pappi-ji-826e93f29f.gif"));

                emitter.complete();

              } catch (IOException | InterruptedException e) {
                emitter.completeWithError(e);
              }
            })
        .start();

    return emitter;
  }
}
