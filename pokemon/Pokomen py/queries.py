import pymysql
import requests
from connection import con, cursor

def heaviest_pokemon():
    query="select * from pokemon where weight = (select MAX(weight) from pokemon)"
    try:
        cursor.execute(query)
    except:
        return "error"
    result = cursor.fetchall()
    print(result)

#heaviest_pokemon()

def find_by_type(type):
    query="select name from pokemon where type='{}'".format(type)
    try:
        cursor.execute(query)
    except:
        return "error"
    result = cursor.fetchall()
    print(result)

#print(find_by_type("grass"))

def find_owners(pokemon_name):
    query = "select owners_name from pokemon_owners where pokemon_id = (select id from pokemon where name='{}')".format(pokemon_name)
    try:
        cursor.execute(query)
        result = cursor.fetchall()
        return result
    except:
        return "error"

#print(find_owners("kakuna"))

def find_roster(trainer_name):
    query = "select pokemon.name from  pokemon, pokemon_owners where pokemon.id = pokemon_owners.pokemon_id and pokemon_owners.owners_name='{}'".format(trainer_name)
    try:
        cursor.execute(query)
    except:
        return "error"
    result = cursor.fetchall()

    return result
#print(find_roster("Loga"))

def finds_most_owned():
    query = "select count(*) from pokemon, pokemon_owners group by pokemon.id"
    try:
        cursor.execute(query)
    except:
        return "error"
    result = cursor.fetchall()
    print(result)

#finds_most_owned()

def update_types(typesLst, name):
    try:
        query = "select id from pokemon where name = '{}'".format(name)
        cursor.execute(query)
        result = cursor.fetchall()
        for type in typesLst:
            try:
                cursor.execute("INSERT INTO types (pokemon_id, pokemon_type) VALUES (%s,%s)", (result[0], type))
            except:
                return "error"
    except:
        return "error"
    con.commit()
    return "updated"

def add_pokemon(pokemon):
    try:
        cursor.execute("INSERT INTO pokemon(id, name, height, weight) VALUES (%s,%s,%s, %s)",(pokemon["id"], pokemon["name"],pokemon["height"],pokemon["weight"]))
        #for type in pokemon["type"]:
            #cursor.execute("INSERT INTO types(pokemon_id, pokemon_type) VALUES (%s,%s)",(pokemon["id"],'{}'.format(type)))
        con.commit()
        return "pokemon created"
    except:
        return "error pokemon create"

def get_pokemons_by_type(type):
    try:
        cursor.execute("select name from pokemon where id in (select pokemon_id from types where pokemon_type = '{}')".format(type))
        result = cursor.fetchall()
        return result
    except:
        return "Failed to find pokemons"

def delete_pokemon(id):
    try:
        cursor.execute("DELETE FROM pokemon_owners WHERE pokemon_id = '{}'".format(id))
    except:
        return "Failed to delete pokemon's from pokemon_owners"
    try:
        cursor.execute("DELETE FROM types WHERE pokemon_id = '{}'".format(id))
    except:
        return "Failed to delete pokemon's type"
    try:
        cursor.execute("DELETE FROM pokemon WHERE id = '{}'".format(id))
    except:
        return "Failed to delete pokemon"
    con.commit()
    return "Pokemon deleted"

def get_evolve(name):
    link = "https://pokeapi.co/api/v2/pokemon/" + name
    f = requests.get(link, verify=False).json()
    species = f["species"]
    link = species["url"]
    f = requests.get(link, verify=False).json()
    evolution = f["evolution_chain"]
    link = evolution["url"]
    f = requests.get(link, verify=False).json()
    name = f["chain"]["evolves_to"][0]['species']["name"]
    return name

def find_pokemonId_by_name(name):
    cursor.execute("select id from pokemon where name='{}'".format(name))
    result = cursor.fetchall()
    return result

def evolves(name, next_name, trainer):
    pokemon_id = find_pokemonId_by_name(name)
    next_id = find_pokemonId_by_name(next_name)
    if next_id==None:
        link = "https://pokeapi.co/api/v2/pokemon/" + next_name
        info = requests.get(link, verify=False).json()
        try:
            cursor.execute("INSERT INTO pokemon(id, name, height, weight) VALUES (%s,%s,%s, %s)",(info["id"], next_name,info["height"],info["height"]))
            next_id = info["id"]
        except:
            return "failed to add envolved"
    cursor.execute("DELETE FROM pokemon_owners WHERE pokemon_id = '{}'".format(id))











