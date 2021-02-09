from flask import Flask,jsonify,request
from classes import *
import json

app = Flask(__name__)

all_values = []

@app.route("/posts", methods=['GET','POST'])
def index():
   global all_values
   if (request.method == 'POST'):
      some_json = request.get_json()
      print(some_json)
      return jsonify(some_json),201
   else:
      pass

   return jsonify(all_values)

if __name__ == '__main__':
   all_values = initialize(all_values)
   app.run(debug = True)