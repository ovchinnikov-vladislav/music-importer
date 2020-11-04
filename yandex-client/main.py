from flask import Flask, Response
from flask import request
from yandex_music.client import Client
import json

app = Flask(__name__)

@app.route('/user-likes-tracks', methods=['POST'])
def get_music_list():
    content = request.json
    username = content['username']
    password = content['password']

    client = Client.from_credentials(username, password)

    user_music_l = client.users_likes_tracks().tracks

    l = list()
    for u in user_music_l:
        l.append(u.track.to_dict())

    return Response(json.dumps(l, ensure_ascii=False), mimetype='application/json')


if __name__ == '__main__':
    app.run(host= '0.0.0.0',debug=True)