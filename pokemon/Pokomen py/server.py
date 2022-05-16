from flask import Flask, Response, request
import json
import requests
from queries import heaviest_pokemon, find_by_type, find_owners, find_roster, finds_most_owned, update_types, add_pokemon,get_pokemons_by_type, delete_pokemon
import urllib

app = Flask(__name__)

@app.route('/', methods=['GET'])
def index():
    return "hi"

@app.route('/allPokemons/<owner_name>', methods=['GET'])
def get_all_pokemons(owner_name):
    res = find_roster(owner_name)
    return Response(json.dumps(res))

@app.route('/getInfo/<name>', methods=['GET'])
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

@app.route('/updatePokemonType/<name>', methods=['GET'])
def update_type(name):
    link = "https://pokeapi.co/api/v2/pokemon/" + name
    f = requests.get(link, verify=False).json()
    types =f["types"]
    typesLst=[]
    for obj in types:
        typesLst.append(obj['type']['name'])
    res=update_types(typesLst, name)
    return res

@app.route('/getTrainers/<name>', methods=['GET'])
def get_trainers(name):
    resulst=find_owners(name)
    return json.dumps(resulst)

@app.route('/addNewPokemon', methods=['POST'])
def add_new_pokemon():
    pokemon = request.get_json()
    try:
        res=add_pokemon(pokemon)
    except:
        return "error"
    #return Response(json.dumps({"Pokemon created": pokemon}), 201)
    return res

@app.route('/getPokemonByType/<type>', methods=['GET'])
def getPokemonByType(type):
    try:
        res = get_pokemons_by_type(type)
        return json.dumps(res)
    except:
        print("error")

@app.route('/deletePokemon/<id>', methods=['DELETE'])
def deletePokemon(id):
    try:
        ans=delete_pokemon(id)
    except:
        return ans
    return ans


if __name__ == '__main__':
    app.run(port=5000)