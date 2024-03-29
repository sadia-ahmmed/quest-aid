from flask import Flask, jsonify
from routes.announcement_api import announcement_api
from routes.chatbot_api import chatbot_api
from pyspark.sql import SparkSession
from flask_cors import CORS, cross_origin

app = Flask(__name__)
cors = CORS(app)
app.config['CORS_HEADERS'] = 'Content-Type'
# spark = SparkSession.builder.appName("Recommender").getOrCreate()

app.register_blueprint(announcement_api)
app.register_blueprint(chatbot_api)


@app.errorhandler(404)
def not_found():
    return jsonify({"message": "Not found"})


if __name__ == "__main__":
    app.run('0.0.0.0', 8080, debug=True)
