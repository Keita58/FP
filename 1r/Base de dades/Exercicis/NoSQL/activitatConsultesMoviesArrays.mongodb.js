use("sample_mflix")

/* Exercici 2

db.movies.find({cast: {$size: 3}}, {_id: 1, title: 1, year: 1, directors: 1, cast: 1})

[
    {
      "_id": {
        "$oid": "573a1390f29313caabcd50e5"
      },
      "cast": [
        "Winsor McCay",
        "George McManus",
        "Roy L. McCardell"
      ],
      "title": "Gertie the Dinosaur",
      "directors": [
        "Winsor McCay"
      ],
      "year": 1914
    },
    {
      "_id": {
        "$oid": "573a1391f29313caabcd71f5"
      },
      "cast": [
        "Harold Lloyd",
        "Mildred Davis",
        "Anna Mae Bilson"
      ],
      "title": "Now or Never",
      "directors": [
        "Fred C. Newmeyer",
        "Hal Roach"
      ],
      "year": 1921
    },... 
]*/
//---------------------------------------------------
/* Exercici 3

db.movies.find({$or: [{cast: {$size: 1}}, {cast: {$size: 2}}]}, {_id: 1, title: 1, year: 1, directors: 1, cast: 1})

[
    {
      "_id": {
        "$oid": "573a1390f29313caabcd4803"
      },
      "cast": [
        "Winsor McCay"
      ],
      "title": "Winsor McCay, the Famous Cartoonist of the N.Y. Herald and His Moving Comics",
      "directors": [
        "Winsor McCay",
        "J. Stuart Blackton"
      ],
      "year": 1911
    },
    {
      "_id": {
        "$oid": "573a1391f29313caabcd6e2a"
      },
      "cast": [
        "Buster Keaton",
        "Sybil Seely"
      ],
      "title": "One Week",
      "directors": [
        "Edward F. Cline",
        "Buster Keaton"
      ],
      "year": 1920
    },... 
]*/
//------------------------------------------------------
/* Exercici 4

db.movies.find({cast: "Billy Burke"}, {_id: 1, title: 1, year: 1, directors: 1, cast: 1})

[
    {
      "_id": {
        "$oid": "573a139af29313caabcf0edb"
      },
      "cast": [
        "Jay Mohr",
        "Billy Burke",
        "Christina Applegate",
        "Pamela Gidley"
      ],
      "title": "Jane Austen's Mafia!",
      "directors": [
        "Jim Abrahams"
      ],
      "year": 1998
    },
    {
      "_id": {
        "$oid": "573a139ef29313caabcfd1c9"
      },
      "cast": [
        "Jill Hennessy",
        "Billy Burke",
        "Kevin Zegers",
        "Paul Gleeson"
      ],
      "title": "Komodo",
      "directors": [
        "Michael Lantieri"
      ],
      "year": 1999
    },..
]*/
//-----------------------------------------------------
/* Exercici 5

db.movies.find({languages: ["Spanish"], cast: "Silvia Munt"}, {_id: 1, title: 1, year: 1, directors: 1, languages: 1,cast: 1}).sort({year: 1})

[
    {
      "_id": {
        "$oid": "573a1398f29313caabce8f6e"
      },
      "cast": [
        "Silvia Munt",
        "Josè Luis Lèpez Vèzquez",
        "Mary Carrillo",
        "Walter Vidarte"
      ],
      "title": "Akelarre",
      "languages": [
        "Spanish"
      ],
      "directors": [
        "Pedro Olea"
      ],
      "year": 1984
    },... 
]*/
//-----------------------------------------------------
/* Exercici 6 

db.movies.find({languages: ["Spanish"], $and: [{cast: "Silvia Munt"}, {cast: "Javier Bardem"}]}, {_id: 1, title: 1, year: 1, directors: 1, languages: 1,cast: 1})

[
    {
      "_id": {
        "$oid": "573a139af29313caabcf05c0"
      },
      "cast": [
        "Javier Bardem",
        "Federico Luppi",
        "Silvia Munt",
        "Daniel Guzmèn"
      ],
      "title": "èxtasis",
      "languages": [
        "Spanish"
      ],
      "directors": [
        "Mariano Barroso"
      ],
      "year": 1996
    }
]*/
//--------------------------------------------------------
/* Exercici 7

db.movies.find({languages: ["Spanish"], cast: {$size: 2}})

[
    {
      "_id": {
        "$oid": "573a1394f29313caabce095f"
      },
      "plot": "\"Araya\" is an old natural salt mine located in a peninsula in northeastern Venezuela which was still, by 1959, being exploited manually five hundred years after its discovery by the Spanish...",
      "genres": [
        "Documentary"
      ],
      "runtime": 90,
      "metacritic": 73,
      "title": "Araya",
      "num_mflix_comments": 0,
      "countries": [
        "Venezuela",
        "France"
      ],
      "fullplot": "\"Araya\" is an old natural salt mine located in a peninsula in northeastern Venezuela which was still, by 1959, being exploited manually five hundred years after its discovery by the Spanish. Margot Benacerraf captures in images, the life of the \"salineros\" and their archaic methods of work before their definite disappearance with the arrival of the industrial exploitation.",
      "languages": [
        "Spanish"
      ],
      "cast": [
        "Josè Ignacio Cabrujas",
        "Laurent Terzieff"
      ],
      "directors": [
        "Margot Benacerraf"
      ],
      "writers": [
        "Margot Benacerraf",
        "Pierre Seghers"
      ],
      "awards": {
        "wins": 0,
        "nominations": 1,
        "text": "1 nomination."
      },
      "lastupdated": "2015-07-11 00:22:55.230000000",
      "year": 1959,
      "imdb": {
        "rating": 7.8,
        "votes": 285,
        "id": 51372
      },
      "type": "movie",
      "tomatoes": {
        "viewer": {
          "rating": 4.2,
          "numReviews": 76,
          "meter": 96
        },
        "dvd": {
          "$date": "2011-05-17T00:00:00Z"
        },
        "critic": {
          "rating": 7.7,
          "numReviews": 24,
          "meter": 88
        },
        "lastUpdated": {
          "$date": "2015-07-24T19:32:20Z"
        },
        "rotten": 3,
        "production": "Milestone Films",
        "fresh": 21
      }
    },... 
]*/
//-------------------------------------------------
/* Exercici 8 

db.movies.find({year: {$type: "string"}})

[
    {
      "_id": {
        "$oid": "573a1397f29313caabce7c93"
      },
      "plot": "An Earth man and his alien friend escape Earth's destruction and go on a truly strange adventure as space hitchhikers.",
      "genres": [
        "Comedy",
        "Sci-Fi",
        "Adventure"
      ],
      "runtime": 152,
      "cast": [
        "Peter Jones",
        "Simon Jones",
        "David Dixon",
        "Sandra Dickinson"
      ],
      "num_mflix_comments": 0,
      "poster": "https://m.media-amazon.com/images/M/MV5BMTI2OTMwNDU1NF5BMl5BanBnXkFtZTcwOTIyNzAzMQ@@._V1_SY1000_SX677_AL_.jpg",
      "title": "The Hitch Hikers Guide to the Galaxy",
      "fullplot": "When the Earth is destroyed a Vogon Demolition Fleet to make way for a new hyperspace bypass, Arthur Dent joins his friend Ford Prefect (who turns out to be a researcher for an electronic reference guide called the Hitchhiker's Guide to the Galaxy) for a galactic voyage on which they meet Zaphod Beeblebrox, a two-headed ex-President of the Galaxy, and his human companion, Trillian. Their journey takes them from the remains of Earth to Milliways, the Restaurant at the End of the Universe. Based on a radio play by Douglas Adams.",
      "languages": [
        "English"
      ],
      "released": {
        "$date": "1982-10-30T00:00:00Z"
      },
      "awards": {
        "wins": 5,
        "nominations": 0,
        "text": "5 wins."
      },
      "lastupdated": "2015-08-27 00:23:21.167000000",
      "year": "1981è",
      "imdb": {
        "rating": 8,
        "votes": 7828,
        "id": 81874
      },
      "countries": [
        "UK"
      ],
      "type": "series",
      "tomatoes": {
        "viewer": {
          "rating": 3.4,
          "numReviews": 102
        },
        "lastUpdated": {
          "$date": "2015-07-31T18:38:34Z"
        }
      }
    },... 
]*/
//----------------------------------------------
/* Exercici 9

db.movies.find({cast: ["Aleksei Ananishnov", "Gudrun Geyer"]})

[
    {
      "_id": {
        "$oid": "573a139af29313caabcf0b52"
      },
      "plot": "A slow and poignant story of love and patience told via a dying mother nursed by her devoted son. The simple narrative is a thread woven among the deeply spiritual images of the countryside...",
      "genres": [
        "Drama"
      ],
      "runtime": 73,
      "cast": [
        "Aleksei Ananishnov",
        "Gudrun Geyer"
      ],
      "num_mflix_comments": 1,
      "poster": "https://m.media-amazon.com/images/M/MV5BMTQwNTk4Nzk1MV5BMl5BanBnXkFtZTcwOTc0MDAyMQ@@._V1_SY1000_SX677_AL_.jpg",
      "title": "Mother and Son",
      "fullplot": "A slow and poignant story of love and patience told via a dying mother nursed by her devoted son. The simple narrative is a thread woven among the deeply spiritual images of the countryside and cottage.",
      "languages": [
        "Russian"
      ],
      "released": {
        "$date": "1998-02-06T00:00:00Z"
      },
      "directors": [
        "Aleksandr Sokurov"
      ],
      "writers": [
        "Yuriy Arabov"
      ],
      "awards": {
        "wins": 4,
        "nominations": 4,
        "text": "4 wins & 4 nominations."
      },
      "lastupdated": "2015-09-02 00:00:23.397000000",
      "year": 1997,
      "imdb": {
        "rating": 7.6,
        "votes": 2638,
        "id": 119711
      },
      "countries": [
        "Russia",
        "Germany"
      ],
      "type": "movie",
      "tomatoes": {
        "viewer": {
          "rating": 2.8,
          "numReviews": 186699,
          "meter": 48
        },
        "dvd": {
          "$date": "1998-12-01T00:00:00Z"
        },
        "critic": {
          "rating": 5.4,
          "numReviews": 30,
          "meter": 43
        },
        "lastUpdated": {
          "$date": "2015-09-10T18:39:36Z"
        },
        "rotten": 17,
        "production": "Dreamworks",
        "fresh": 13
      }
    },
    {
      "_id": {
        "$oid": "573a139af29313caabcf1dc2"
      },
      "plot": "A slow and poignant story of love and patience told via a dying mother nursed by her devoted son. The simple narrative is a thread woven among the deeply spiritual images of the countryside...",
      "genres": [
        "Drama"
      ],
      "runtime": 73,
      "cast": [
        "Aleksei Ananishnov",
        "Gudrun Geyer"
      ],
      "poster": "https://m.media-amazon.com/images/M/MV5BMTQwNTk4Nzk1MV5BMl5BanBnXkFtZTcwOTc0MDAyMQ@@._V1_SY1000_SX677_AL_.jpg",
      "title": "Mother and Son",
      "fullplot": "A slow and poignant story of love and patience told via a dying mother nursed by her devoted son. The simple narrative is a thread woven among the deeply spiritual images of the countryside and cottage.",
      "languages": [
        "Russian"
      ],
      "released": {
        "$date": "1998-02-06T00:00:00Z"
      },
      "directors": [
        "Aleksandr Sokurov"
      ],
      "writers": [
        "Yuriy Arabov"
      ],
      "awards": {
        "wins": 4,
        "nominations": 4,
        "text": "4 wins & 4 nominations."
      },
      "lastupdated": "2015-08-29 08:01:44.993000000",
      "year": 1997,
      "imdb": {
        "rating": 7.6,
        "votes": 2634,
        "id": 119711
      },
      "countries": [
        "Russia",
        "Germany"
      ],
      "type": "movie",
      "num_mflix_comments": 0
    }
]*/
//--------------------------------------------------
/* Exercici 10 

db.movies.find({"cast.2": "Silvia Munt"})

[
    {
      "_id": {
        "$oid": "573a139af29313caabcf05c0"
      },
      "plot": "Three friends, two young men and a young woman, are bored by the normal world of their parents and want to flee in order to start living somewhere else. Thus they make a pervert plan: rob ...",
      "genres": [
        "Drama"
      ],
      "runtime": 93,
      "cast": [
        "Javier Bardem",
        "Federico Luppi",
        "Silvia Munt",
        "Daniel Guzmèn"
      ],
      "num_mflix_comments": 0,
      "poster": "https://m.media-amazon.com/images/M/MV5BMTU2MTM3MjEwOF5BMl5BanBnXkFtZTcwNDg0MzMzMQ@@._V1_SY1000_SX677_AL_.jpg",
      "title": "èxtasis",
      "fullplot": "Three friends, two young men and a young woman, are bored by the normal world of their parents and want to flee in order to start living somewhere else. Thus they make a pervert plan: rob their own families. After that they flee without any destination and without having really planned where to go to.",
      "languages": [
        "Spanish"
      ],
      "released": {
        "$date": "1996-02-16T00:00:00Z"
      },
      "directors": [
        "Mariano Barroso"
      ],
      "writers": [
        "Mariano Barroso",
        "Joaquèn Oristrell"
      ],
      "awards": {
        "wins": 1,
        "nominations": 4,
        "text": "1 win & 4 nominations."
      },
      "lastupdated": "2015-07-30 00:31:23.927000000",
      "year": 1996,
      "imdb": {
        "rating": 6.9,
        "votes": 559,
        "id": 118239
      },
      "countries": [
        "Spain"
      ],
      "type": "movie",
      "tomatoes": {
        "viewer": {
          "rating": 3.5,
          "numReviews": 127,
          "meter": 71
        },
        "dvd": {
          "$date": "2008-07-29T00:00:00Z"
        },
        "lastUpdated": {
          "$date": "2015-03-09T18:10:10Z"
        }
      }
    },
    {
      "_id": {
        "$oid": "573a139af29313caabcf0ca7"
      },
      "plot": "Javi and his friend Carlos snoop around an old house on the way home from school. According to his brother Juan this is a haunted house and one can hear the voices of the dead. Later he is ...",
      "genres": [
        "Drama"
      ],
      "runtime": 105,
      "cast": [
        "Carmelo Gèmez",
        "Charo Lèpez",
        "Silvia Munt",
        "Vicky Peèa"
      ],
      "num_mflix_comments": 0,
      "poster": "https://m.media-amazon.com/images/M/MV5BZDEwZGNhYTctOWZlYy00Y2IwLWIwYmUtYzEzM2NjZjYwMjU4XkEyXkFqcGdeQXVyMTA0MjU0Ng@@._V1_SY1000_SX677_AL_.jpg",
      "title": "Secrets of the Heart",
      "fullplot": "Javi and his friend Carlos snoop around an old house on the way home from school. According to his brother Juan this is a haunted house and one can hear the voices of the dead. Later he is intrigued with a room which is always closed (the room where his father was found dead). He is so interested in these mysteries that he starts to investigate all the secrets of these dead people and their stories.",
      "languages": [
        "Spanish"
      ],
      "released": {
        "$date": "1997-03-19T00:00:00Z"
      },
      "directors": [
        "Montxo Armendèriz"
      ],
      "writers": [
        "Montxo Armendèriz"
      ],
      "awards": {
        "wins": 19,
        "nominations": 8,
        "text": "Nominated for 1 Oscar. Another 18 wins & 8 nominations."
      },
      "lastupdated": "2015-07-21 06:11:25.200000000",
      "year": 1997,
      "imdb": {
        "rating": 7.3,
        "votes": 1293,
        "id": 120090
      },
      "countries": [
        "France",
        "Portugal",
        "Spain"
      ],
      "type": "movie",
      "tomatoes": {
        "viewer": {
          "rating": 3.6,
          "numReviews": 688,
          "meter": 84
        },
        "dvd": {
          "$date": "2000-11-14T00:00:00Z"
        },
        "lastUpdated": {
          "$date": "2015-06-12T18:42:10Z"
        }
      }
    }
]*/
//--------------------------------------------
/* Exercici 11

db.movies.find({cast: {$size: 4}, "cast.0": "Viggo Mortensen", "cast.3": "Sheila Moore"}).sort({year: -1})

[
    {
      "_id": {
        "$oid": "573a1399f29313caabcec28c"
      },
      "plot": "A young boy tries to cope with rural life circa 1950s and his fantasies become a way to interpret events. After his father tells him stories of vampires, he becomes convinced that the widow...",
      "genres": [
        "Drama",
        "Horror",
        "Thriller"
      ],
      "runtime": 96,
      "rated": "R",
      "cast": [
        "Viggo Mortensen",
        "Lindsay Duncan",
        "Jeremy Cooper",
        "Sheila Moore"
      ],
      "poster": "https://m.media-amazon.com/images/M/MV5BOWZjN2YyNmMtZWE4NS00N2E5LTljMGQtODJmNDU5YmRhZTcwXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_SY1000_SX677_AL_.jpg",
      "title": "The Reflecting Skin",
      "fullplot": "A young boy tries to cope with rural life circa 1950s and his fantasies become a way to interpret events. After his father tells him stories of vampires, he becomes convinced that the widow up the road is a vampire, and tries to find ways of discouraging his brother from seeing her. He must deal with an abusive mother, a father with a charge of molestation, a band of youths creating havoc, and an unforgiving environment in general.",
      "languages": [
        "English"
      ],
      "released": {
        "$date": "1990-11-09T00:00:00Z"
      },
      "directors": [
        "Philip Ridley"
      ],
      "writers": [
        "Philip Ridley"
      ],
      "awards": {
        "wins": 6,
        "nominations": 3,
        "text": "6 wins & 3 nominations."
      },
      "lastupdated": "2015-09-02 00:37:40.913000000",
      "year": 1990,
      "imdb": {
        "rating": 7.1,
        "votes": 4285,
        "id": 100469
      },
      "countries": [
        "UK",
        "Canada"
      ],
      "type": "movie",
      "tomatoes": {
        "viewer": {
          "rating": 3.9,
          "numReviews": 2519,
          "meter": 79
        },
        "dvd": {
          "$date": "1992-02-26T00:00:00Z"
        },
        "critic": {
          "rating": 6,
          "numReviews": 9,
          "meter": 78
        },
        "lastUpdated": {
          "$date": "2015-08-07T19:11:16Z"
        },
        "rotten": 2,
        "production": "Miramax Films",
        "fresh": 7
      },
      "num_mflix_comments": 0
    }
]*/