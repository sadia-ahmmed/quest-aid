from flask import Blueprint, request, jsonify
from models.llm_dir import google_palm_chat

chatbot_api = Blueprint("chatbot_api", __name__)

@chatbot_api.route("/chat", methods=["POST"])
def send_chat():
    data = request.json["prompt"]

    if data is None:
        return jsonify({
            "error": "Missing prompt"
        })
    
    res_str = google_palm_chat.predict(data)
    return jsonify({"response": res_str})