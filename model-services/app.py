from flask import Flask, jsonify, abort

app = Flask(__name__)


@app.route('/', methods=["GET"])
def test_hello():
    return jsonify({"message": "Hello World"})

@app.errorhandler(404)
def not_found():
    return jsonify({"message": "Not found"})


if __name__ == "__main__":
    app.run('0.0.0.0', 8080, debug=True)