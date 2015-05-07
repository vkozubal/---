import os;
import string;
import random;
import requests;
import json

def text_generator(size=240, chars=string.ascii_uppercase + string.digits):
  return ''.join(random.choice(chars) for _ in range(size))

def id_generator(size=10, chars=string.ascii_uppercase + string.digits):
  return ''.join(random.choice(chars) for _ in range(size))

for x in range(0,512):
  url = "http://localhost:8811/api/post/save"
  data = {"text": text_generator(),"userId": id_generator()}
  headers = {'Content-type': 'application/json'}
  credentials=('user', 'password')
  r = requests.post(url, data=json.dumps(data), headers=headers, auth=credentials)
  print(r.json())
  print("\n")