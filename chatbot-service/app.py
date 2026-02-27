from fastapi import FastAPI
from pydantic import BaseModel
from rag_pipeline import get_response

app = FastAPI()


class ChatRequest(BaseModel):
    session_id: str
    message: str


@app.post("/chat")
def chat(request: ChatRequest):
    answer = get_response(request.message)

    return {"session_id": request.session_id, "answer": answer}
