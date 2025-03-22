from pymongo import MongoClient
import pymongo
from datetime import datetime
from pprint import pprint

import mainmongoengine

uri = "mongodb+srv://keita58:contrasenya@basededades.9tcii4i.mongodb.net/"

client = MongoClient(uri)
db = client['Magic']
collection = db['Gathering']


def search(id, nom):
    carta = collection.find_one({'idCarta': int(id), nom: {"$exists": True}})
    if nom == "power" or nom == "toughness":
        if carta is not None:
            return carta[nom]
        else:
            print("No té " + nom)
    else:
        if carta is not None:
            return int(id), carta[nom]
        else:
            return print("No té " + nom)


def eliminar(nomJugador):
    collection = db['Partida']
    jugador = collection.find_one({'nomJugador': nomJugador})
    if jugador is not None:
        collection.delete_one({'nomJugador': nomJugador})
        jugadors = list(collection.find({}))
        print("Queden", len(jugadors), "documents.")
        for jugador in jugadors:
            pprint(jugador['nomJugador'])
    else:
        print("El jugador no existeix!")


if __name__ == '__main__':
    while True:
        print("Opcions: buscar <<id carta>> <<camp a buscar>> / migracio / add <<nom jugador>> / simulacio / atac "
              "<<nom jugador>> / eliminar <<nom jugador>> / fi")
        lineaentrada = input("Tria el valor que vols buscar: ").split()
        print()
        if lineaentrada[0].lower() == "fi":
            break
        elif lineaentrada[0].lower() == "buscar":
            id = lineaentrada[1]
            valorCarta = lineaentrada[2]
            resultat = search(id, valorCarta)
            if resultat is not None:
                print("El valor és: " + str(resultat))
        elif lineaentrada[0].lower() == "migracio":
            mainmongoengine.migracio()
        elif lineaentrada[0].lower() == "add":
            nomJugador = lineaentrada[1]
            mainmongoengine.afegir(nomJugador)
        elif lineaentrada[0].lower() == "simulacio":
            mainmongoengine.simulacio()
        elif lineaentrada[0].lower() == "atac":
            nomJugador = lineaentrada[1]
            mainmongoengine.atac(nomJugador)
        elif lineaentrada[0].lower() == "eliminar":
            nomJugador = lineaentrada[1]
            eliminar(nomJugador)
        print()
