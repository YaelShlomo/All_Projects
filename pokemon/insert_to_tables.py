import json
import pymysql

file_data=open("pokemon_data.json","r",encoding='utf-8')
json_data=file_data.read()
file_data.close()

con = pymysql.connect(host = 'localhost',user ='root',passwd = 'ys1234y$YSys',db = 'sql_intro')
cursor = con.cursor()
sql_data=json.loads(json_data)


for pokemon in sql_data:
    
    try: 
        cursor.execute("INSERT INTO pokemon (id, name,height,weight) VALUES (%s,%s,%s,%s)",
                            (pokemon["id"], pokemon["name"], pokemon["height"],pokemon["weight"]))
    except:
        print("error")
    for owner in pokemon["ownedBy"]:
        try:
           cursor.execute("INSERT INTO owners (name,town) VALUES (%s,%s)",
                          (owner["name"],owner["town"]))
        except(Exception):
           print("duplicate")
        try:
          cursor.execute("INSERT INTO pokemon_owners (owners_name,owners_town,pokemon_id) VALUES (%s,%s,%s)",
                  (owner["name"], owner["town"],pokemon["id"]))
        except(Exception):
            print("duplicate")

con.commit()
con.close()

