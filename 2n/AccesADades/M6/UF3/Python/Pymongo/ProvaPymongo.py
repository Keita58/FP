from pymongo import MongoClient  # pip install pymongo
from pprint import pprint

uri = "mongodb+srv://keita58:contrasenya@basededades.9tcii4i.mongodb.net/"  # Adreça per connectar-se a l'Atlas
client = MongoClient(uri)

print(client.list_database_names())  # Nom de les bases de dades
collecio = client["anime"]["anime"]  # El primer és el nom de la BD i el segon de la col·lecció

pprint(collecio.find_one())  # Imprimeix una entrada de la col·lecció
pprint(collecio.find_one({"Nom": "Angel Beats!"}))  # Retorna l'entrada a la col·lecció amb la busca especificada

entrades = collecio.find({"Nom": {"$regex": 'a'}}, {"_id": 0, "Nom": 1, "Year": 1})
for i in entrades:
    pprint(i)