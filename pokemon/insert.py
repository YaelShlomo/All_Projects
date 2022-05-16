import requests
import json
import pymysql

def insert():
    con = pymysql.connect(host='localhost', user='root', passwd='ys1234y$YSys', db='sql_intro')
    cursor = con.cursor()
    f = open('pokemon_data.json', "r")
    data = json.load(f)
    f.close()
    #print(data)

    #for pokemon in data:
        # print(pokemon)
        #cursor.execute("INSERT INTO pokemon (id, name, type, height, weight) VALUES (%s,%s,%s,%s,%s)",
                       #(pokemon["id"], pokemon["name"], pokemon["type"], pokemon["height"], pokemon["weight"] ))
        # for owner in pokemon["ownedBy"]:
        #     cursor.execute("INSERT INTO owners (name, town) VALUES (%s,%s)",
        #                    (owner["name"], owner["town"]))
    #cursor.execute("INSERT INTO pokemon(id, name, type, height, weight)  VALUES (1,"v","d",4,4))
   
insert()