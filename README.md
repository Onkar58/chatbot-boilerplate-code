# Full-Stack Chatbot Boilerplate (SSE Streaming)

A professional, modular foundation for building AI-powered chatbots with real-time streaming capabilities. This boilerplate handles the "plumbing" of a modern AI application, allowing you to focus entirely on your LLM logic or custom AI pipeline.

---

## 🏗️ Architecture Overview

The project is split into three distinct layers to ensure a clean separation of concerns and high maintainability.



1.  **Client (Vite + React):** A responsive UI that handles user input and consumes Server-Sent Events (SSE) to display responses word-by-word.
2.  **Web Server (Spring Boot):** The central orchestrator. It manages business logic, security, and proxies the data stream from the AI service to the frontend.
3.  **Chatbot Service (FastAPI):** The AI engine. This is where your `chatbot_pipeline` resides, utilizing Python's extensive AI ecosystem (LangChain, OpenAI, HuggingFace).

---

## ⚡ Why SSE (Server-Sent Events)?

Traditional REST APIs require the client to wait for the *entire* response to be generated before showing anything. For LLMs, this can take 10–30 seconds, leading to a poor user experience.

We chose **SSE** over **WebSockets** for this boilerplate because:
* **Simplicity:** SSE is a lightweight protocol that runs over standard HTTP. It doesn't require the complex "handshake" or state management of WebSockets.
* **One-Way Streaming:** Chatbots primarily need to stream data *to* the user. SSE is optimized for this exact server-to-client flow.
* **Auto-Reconnection:** Browsers natively support re-establishing SSE connections if they drop.
* **Performance:** Users see "tokens" (text fragments) immediately as they are generated, making the bot feel significantly faster.

---

## 📂 Project Structure

```text
.
├── chatbot-service/    # Python FastAPI: AI logic & Streaming Pipeline
├── web-server/         # Spring Boot: API Gateway & Orchestration
└── client/             # Vite + React: UI & SSE Event Source handling
