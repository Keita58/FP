import random

from mongoengine import *

uri = "mongodb+srv://keita58:contrasenya@basededades.9tcii4i.mongodb.net/Magic?retryWrites=true&w=majority"
client = connect(host=uri, db="Magic")

# Enums per les cartes
Color = ("G", "R", "B", "W", "U")
Layout = ("normal", "scheme", "modal_dfc")
Types = ("Artifact", "Creature", "Enchantment", "Instant", "Land", "Planeswalker", "Scheme", "Sorcery")


# Fi enums

class LeadershipSkills(EmbeddedDocument):
    brawl = BooleanField(required=True)
    commander = BooleanField(required=True)
    oathbreaker = BooleanField(required=True)


class Ruling(EmbeddedDocument):
    date = StringField(required=True, max_length=100)
    text = StringField(required=True, max_length=100)


class Legalities(EmbeddedDocument):
    alchemy = StringField(max_length=100)
    brawl = StringField(max_length=100)
    commander = StringField(max_length=100)
    duel = StringField(max_length=100)
    explorer = StringField(max_length=100)
    gladiator = StringField(max_length=100)
    historic = StringField(max_length=100)
    legacy = StringField(max_length=100)
    modern = StringField(max_length=100)
    oathbreaker = StringField(max_length=100)
    pauper = StringField(max_length=100)
    paupercommander = StringField(max_length=100)
    pioneer = StringField(max_length=100)
    timeless = StringField(max_length=100)
    vintage = StringField(max_length=100)
    penny = StringField(max_length=100)
    future = StringField(max_length=100)
    standard = StringField(max_length=100)
    standardbrawl = StringField(max_length=100)


class PurchaseUrls(EmbeddedDocument):
    cardKingdom = StringField(max_length=100)
    cardKingdomFoil = StringField(max_length=100)
    cardmarket = StringField(max_length=100)
    tcgplayer = StringField(max_length=100)


class Identifiers(EmbeddedDocument):
    scryfallOracleId = StringField(required=True)


class ForeignData(EmbeddedDocument):
    language = StringField(required=True, max_length=50)
    name = StringField(required=True, max_length=100)
    text = StringField(required=True, max_length=100)
    type = StringField(required=True, max_length=100)
    flavorText = StringField(max_length=100)


class Gathering(Document):
    _id = ObjectIdField(required=True)
    idCarta = IntField(required=True)
    colorIdentity = ListField(StringField(choises=Color), required=True)
    colors = ListField(StringField(choices=Color), required=True)
    convertedManaCost = IntField(required=True)
    edhrecRank = IntField()
    edhrecSaltiness = FloatField()
    firstPrinting = StringField(max_length=100, required=True)
    foreignData = ListField(EmbeddedDocumentField(ForeignData))
    identifiers = EmbeddedDocumentField(Identifiers, required=True)
    keywords = ListField(StringField(max_length=100))
    isFunny = BooleanField()
    layout = StringField(choices=Layout, required=True)
    leadershipSkills = EmbeddedDocumentField(LeadershipSkills)
    legalities = EmbeddedDocumentField(Legalities)
    manaCost = StringField(max_length=100)
    manaValue = IntField(required=True)
    name = StringField(max_length=100, required=True)
    power = StringField(max_length=2)
    printings = ListField(StringField(max_length=100), required=True)
    purchaseUrls = EmbeddedDocumentField(PurchaseUrls)
    rulings = ListField(EmbeddedDocumentField(Ruling))
    subtypes = ListField(StringField(max_length=100))
    supertypes = ListField(StringField(max_length=100))
    text = StringField(max_length=100, required=True)
    toughness = StringField(max_length=2)
    type = StringField(choises=Types, required=True)
    types = ListField(StringField(choises=Types), required=True)
    faceName = StringField(max_length=50)
    side = StringField(max_length=50)
    faceConvertedManaCost = IntField()
    faceManaValue = IntField()
    loyalty = StringField(max_length=1)
    meta = {'collection': 'Gathering'}  # Necessari perquè totes les collection son en minúscules i ho necessitem en majúscules (only if the collection starts with uppercase)


class Carta(EmbeddedDocument):
    idCarta = IntField(required=True)
    nomCarta = StringField(required=True, max_length=100)
    atac = IntField(required=True, max_value=20, min_value=0)
    defensa = IntField(required=True, max_value=20, min_value=0)
    isFunny = BooleanField()


class Partida(Document):
    nomJugador = StringField(required=True, max_length=50)
    vida = IntField(required=True, min_value=0, max_value=20, default=20)
    cartes = ListField(EmbeddedDocumentField(Carta))
    meta = {'collection': 'Partida'}


def migracio():
    jugador1 = Partida(nomJugador="Pepe", vida=20, cartes=[])
    jugador2 = Partida(nomJugador="Nagisa_Shiota", vida=20, cartes=[])
    jugador1.save()
    jugador2.save()

    criatures = Gathering.objects(types="Creature")

    for i in range(10):
        carta = Carta(idCarta=criatures[i].idCarta, nomCarta=criatures[i].name, atac=criatures[i].power,
                      defensa=criatures[i].toughness, isFunny=criatures[i].isFunny)
        if i > 4:
            jugador2.cartes.append(carta)
            jugador2.save()
        else:
            jugador1.cartes.append(carta)
            jugador1.save()

    print("Jugador: " + jugador1.nomJugador + ", vida: " + str(jugador1.vida) + ", cartes: ")
    for carta in jugador1.cartes:
        print("Nom carta: " + carta.nomCarta + ", atac: " + str(carta.atac) + ", defensa: " + str(
            carta.defensa) + ", és divertida? " + str(carta.isFunny))
    print()
    print("Jugador: " + jugador2.nomJugador + ", vida: " + str(jugador2.vida) + ", cartes: ")
    for carta in jugador2.cartes:
        print("Nom carta: " + carta.nomCarta + ", atac: " + str(carta.atac) + ", defensa: " + str(
            carta.defensa) + ", és divertida? " + str(carta.isFunny))


def afegir(nomJugador):
    criatures = list(Gathering.objects(types="Creature"))
    random.shuffle(criatures)
    print(nomJugador)
    try:
        jugador = Partida.objects(nomJugador=nomJugador)
        carta = Carta(idCarta=criatures[0].idCarta, nomCarta=criatures[0].name, atac=criatures[0].power,
                      defensa=criatures[0].toughness, isFunny=criatures[0].isFunny)
        for j in jugador:
            j.cartes.append(carta)
            j.save()
            print("Jugador modificat: ")
            print("Jugador: " + j.nomJugador + ", vida: " + str(j.vida) + ", cartes: ")
            for carta in j.cartes:
                print("Nom carta: " + carta.nomCarta + ", atac: " + str(carta.atac) + ", defensa: " + str(
                    carta.defensa) + ", és divertida? " + str(carta.isFunny))
            print()
    except:
        print("El jugador no existeix!")


def simulacio():
    jugadors = list(Partida.objects())
    criatures = list(Gathering.objects(types="Creature"))
    if len(jugadors) >= 2:
        random.shuffle(jugadors)
        llistaJugadors = jugadors[:2]
        for jugador in llistaJugadors:
            if jugador.vida < 20:
                jugador.vida = 20
                jugador.save()
            if len(jugador.cartes) < 5:
                random.shuffle(criatures)
                for i in range(5 - len(jugador.cartes)):
                    carta = Carta(idCarta=criatures[i].idCarta, nomCarta=criatures[i].name, atac=criatures[i].power,
                                  defensa=criatures[i].toughness, isFunny=criatures[i].isFunny)
                    jugador.cartes.append(carta)
                    jugador.save()

        # Inici del bucle de joc
        torn = 0
        while True:
            if torn % 2 == 0:
                #Torn jugador 1
                print("Torn del jugador " + llistaJugadors[0].nomJugador + ", té " + str(
                    llistaJugadors[0].vida) + " punts de vida")
                cartaEscollida = random.choice(llistaJugadors[0].cartes)
                print("El jugador " + llistaJugadors[
                    0].nomJugador + " ha triat la carta " + cartaEscollida.nomCarta + ", atac: " + str(
                    cartaEscollida.atac))
                cartaEscollida2 = random.choice(llistaJugadors[1].cartes)
                print("El jugador " + llistaJugadors[
                    1].nomJugador + " ha triat la carta " + cartaEscollida2.nomCarta + ", defensa: " + str(
                    cartaEscollida2.defensa))

                # Posem >= per a que no hi hagi bucle infinit en els casos on l'atac és 0 o 1
                if cartaEscollida.atac >= cartaEscollida2.defensa:
                    llistaJugadors[1].vida -= cartaEscollida.atac
                    print("El jugador " + llistaJugadors[0].nomJugador + " ataca el jugador " + llistaJugadors[
                        1].nomJugador + " i aquest es queda amb " + str(llistaJugadors[1].vida) + " punts de vida")
                    if llistaJugadors[1].vida <= 0:
                        llistaJugadors[1].vida = 0
                        llistaJugadors[1].save()
                        print("El jugador " + llistaJugadors[1].nomJugador + " s'ha quedat sense vida: " + str(
                            llistaJugadors[1].vida))
                        print()
                        print(
                            "El jugador " + llistaJugadors[0].nomJugador + " HA GUANYAT I LI QUEDEN AQUESTES CARTES: ")
                        for carta in llistaJugadors[0].cartes:
                            print("Nom carta: " + carta.nomCarta + ", atac: " + str(carta.atac) + ", defensa: " + str(
                                carta.defensa) + ", és divertida? " + str(carta.isFunny))
                        break
                    else:
                        llistaJugadors[1].save()
                        llistaJugadors[0].cartes.remove(cartaEscollida)
                        print("El jugador " + llistaJugadors[
                            0].nomJugador + " ha descartat la carta " + cartaEscollida.nomCarta)
                        llistaJugadors[0].save()
                        random.shuffle(criatures)
                        carta = Carta(idCarta=criatures[0].idCarta, nomCarta=criatures[0].name, atac=criatures[0].power,
                                      defensa=criatures[0].toughness, isFunny=criatures[0].isFunny)
                        llistaJugadors[0].cartes.append(carta)
                        llistaJugadors[0].save()
                        print("El jugador " + llistaJugadors[0].nomJugador + " ha agafat la carta " + carta.nomCarta)
                else:
                    print("No ha pogut atacar!")
                print()
            elif torn % 2 == 1:
                #Torn jugador 2
                print("Torn del jugador " + llistaJugadors[1].nomJugador + ", té " + str(
                    llistaJugadors[1].vida) + " punts de vida")
                cartaEscollida = random.choice(llistaJugadors[0].cartes)
                print("El jugador " + llistaJugadors[
                    0].nomJugador + " ha triat la carta " + cartaEscollida.nomCarta + ", defensa: " + str(
                    cartaEscollida.defensa))
                cartaEscollida2 = random.choice(llistaJugadors[1].cartes)
                print("El jugador " + llistaJugadors[
                    1].nomJugador + " ha triat la carta " + cartaEscollida2.nomCarta + ", atac: " + str(
                    cartaEscollida2.atac))

                # Posem >= per a que no hi hagi bucle infinit en els casos on l'atac és 0 o 1
                if cartaEscollida2.atac >= cartaEscollida.defensa:
                    llistaJugadors[0].vida -= cartaEscollida2.atac
                    print("El jugador " + llistaJugadors[1].nomJugador + " ataca el jugador " + llistaJugadors[
                        0].nomJugador + " i aquest es queda amb " + str(llistaJugadors[0].vida) + " punts de vida")
                    if llistaJugadors[0].vida <= 0:
                        llistaJugadors[0].vida = 0
                        llistaJugadors[0].save()
                        print("El jugador " + llistaJugadors[0].nomJugador + " s'ha quedat sense vida: " + str(
                            llistaJugadors[0].vida))
                        print()
                        print(
                            "El jugador " + llistaJugadors[1].nomJugador + " HA GUANYAT I LI QUEDEN AQUESTES CARTES: ")
                        for carta in llistaJugadors[1].cartes:
                            print("Nom carta: " + carta.nomCarta + ", atac: " + str(carta.atac) + ", defensa: " + str(
                                carta.defensa) + ", és divertida? " + str(carta.isFunny))
                        break
                    else:
                        llistaJugadors[0].save()
                        llistaJugadors[1].cartes.remove(cartaEscollida2)
                        print("El jugador " + llistaJugadors[
                            1].nomJugador + " ha descartat la carta " + cartaEscollida2.nomCarta)
                        llistaJugadors[1].save()
                        random.shuffle(criatures)
                        carta = Carta(idCarta=criatures[0].idCarta, nomCarta=criatures[0].name, atac=criatures[0].power,
                                      defensa=criatures[0].toughness, isFunny=criatures[0].isFunny)
                        llistaJugadors[1].cartes.append(carta)
                        llistaJugadors[1].save()
                        print("El jugador " + llistaJugadors[1].nomJugador + " ha agafat la carta " + carta.nomCarta)
                else:
                    print("No ha pogut atacar!")
                print()
            torn += 1
    else:
        print("No hi ha jugadors suficients per a començar la partida. :(")


def atac(nomJugador):
    jugador = list(Partida.objects(nomJugador=nomJugador))
    if len(jugador) > 0 :
        for j in jugador:
            for carta in j.cartes:
                if carta.isFunny is not None and carta.isFunny is True:
                    print("Pujant stats de la carta: " + carta.nomCarta)
                    carta.atac += 2
                    j.save()
                    if carta.atac > 20:
                        print("La carta " + carta.nomCarta + ", ja està al valor màxim")
                        carta.atac = 20
                        j.save()

            print("Jugador " + j.nomJugador)
            for carta in j.cartes:
                print("Nom carta: " + carta.nomCarta + ", atac: " + str(carta.atac) + ", defensa: " + str(
                    carta.defensa) + ", és divertida? " + str(carta.isFunny))
            print()
    else:
        print("No hi ha cap jugador amb aquest nom :(")
