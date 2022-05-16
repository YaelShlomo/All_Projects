import requests
from flask import request
from queries import heaviest_pokemon, find_by_type, find_owners, find_roster, finds_most_owned, update_types, \
    add_pokemon, get_pokemons_by_type, delete_pokemon

def test_get_pokemons_by_type():
    url = "http://localhost:5000/getPokemonByType/normal"
    name = requests.get(url=url)
    assert name == "eevee"
    url = "http://localhost:5000/updatePokemonType/eevee"
    ans=requests.get(url=url)
    assert ans.status_code() == 200

def test_add_pokemon():
    url = "http://localhost:5000/addNewPokemon"
    pokemon={
        "id":193,
        "name":"yanma",
        "height":12,
        "weight":380
    }
    res = requests.post(url=url, json=pokemon)
    assert res.status_code()==200
    url = "http://localhost:5000/getPokemonByType/bug"
    res = requests.get(url=url)
    assert "yanma" in res
    url = "http://localhost:5000/getPokemonByType/flying"
    res = requests.get(url=url)
    assert "yanma" in res

def test_update_pokemon_types():
    url = "http://localhost:5000/updatePokemonType/venusaur"
    res = requests.get(url=url)
    assert res.status_code()==200
    url = "http://localhost:5000/getPokemonByType/poison"
    res = requests.get(url=url)
    assert "venusaur" in res
    url = "http://localhost:5000/getPokemonByType/grass"
    res = requests.get(url=url)
    assert "venusaur" in res

def test_get_pokemons_by_owner():
    url = "http://localhost:5000/allPokemons/Drasna"
    res = requests.get(url=url)
    assert res.status_code()==200
    assert res["pokemons"] == ["wartortle", "caterpie", "beedrill", "arbok", "clefairy",
                               "wigglytuff", "persian", "growlithe", "machamp", "golem", "dodrio", "hypno", "cubone", "eevee", "kabutops"]


def test_get_owners_of_a_pokemon():
    url = "http://localhost:5000/getTrainers/charmander"
    res = requests.get(url=url)
    assert res.status_code()==200
    assert res["owners"]==["Giovanni", "Jasmine", "Whitney"]

def test_evolve():
    url = "http://localhost:5000/evolve_pokemon"
    json={
        "pokemon_name":"pinsir",
        "owner_name":"Whitney ",
        "owner_town":"Zedon"
    }
    res = requests.put(url=url, json=json)
    assert res==""
    json = {
        "pokemon_name": "spearow",
        "owner_name": "Archie",
        "owner_town": "Little Italy"
    }
    res = requests.put(url=url, json=json)
    assert res == ""
    json = {
        "pokemon_name": "oddish",
        "owner_name": "Whitney",
        "owner_town": "Zedon"
    }
    res = requests.put(url=url, json=json)
    assert res == ""
    json = {
        "pokemon_name": "oddish",
        "owner_name": "Whitney",
        "owner_town": "Zedon"
    }
    res = requests.put(url=url, json=json)
    assert res == ""
    url = "http://localhost:5000/allPokemons/Whitney"
    res = requests.get(url=url)
    assert "gloom" in res

