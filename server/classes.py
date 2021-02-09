import json


all_values = []

def initialize(all_values):
   ##all_values.append({"id":3,"userId":5,"title":"lord","body":"test"})
   all_values.append({"name":"Martin","score":6})
   all_values.append({"name":"Franta","score":5})
   all_values.append({"name":"Pavel","score":4})
   all_values.append({"name":"Jana","score":3})
   return all_values



def add_new(all_values,value):
   print(value)
   val = value['score']

   print(val)

   for x in range(len(all_values)):
      if val > all_values[x]['score']:
         all_values.insert(x,value)
         break

   while len(all_values) > 10:
      all_values.pop()

   return all_values
