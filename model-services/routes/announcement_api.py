from flask import Blueprint, request, jsonify
from models.llm_dir import google_palm

announcement_api = Blueprint("announcement_api", __name__)

@announcement_api.route("/make/announcement", methods=["POST"])
def create_announcement():
    data = request.json["prompt"]

    if data is None:
        return jsonify({
            "error": "Missing prompt"
        })
    
    res_str = google_palm.predict(data)
    return jsonify({"response": res_str})