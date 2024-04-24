use("sample_mflix");

/* Exercici 1 

db.movies.find({
    languages: {$exists: false}
}, 
{
    _id: 1, 
    title: 1, 
    year: 1
});

[
    {
      "_id": {
        "$oid": "573a1390f29313caabcd5a93"
      },
      "title": "Civilization",
      "year": 1916
    },
    {
      "_id": {
        "$oid": "573a1391f29313caabcd6ea2"
      },
      "title": "The Saphead",
      "year": 1920
    },
    {
      "_id": {
        "$oid": "573a1391f29313caabcd6f98"
      },
      "title": "The Ace of Hearts",
      "year": 1921
    },... 
]*/
//------------------------------------------------------
/* Exercici 2 

db.movies.find({
    $and: [{
        languages: {
            $ne: [
                "English"
            ]
        }
    }, {
        languages: {
            $ne: [
                "French"
            ]
        }
    }, {
        languages: {
            $exists: true
        }
    }
]}, 
{
    _id: 1, 
    title: 1, 
    year: 1, 
    languages: 1
}).toArray()

[
    {
      "_id": {
        "$oid": "573a1391f29313caabcd8c6b"
      },
      "title": "Storm Over Asia",
      "languages": [
        "Russian"
      ],
      "year": 1928
    },
    {
      "_id": {
        "$oid": "573a1391f29313caabcd8de7"
      },
      "title": "Asphalt",
      "languages": [
        "German"
      ],
      "year": 1929
    },... 
]*/
//--------------------------------------------------------
/* Exercici 3 

db.movies.find({languages: {$nin: ["French", "English", "German", "Spanish"]}}, {_id: 1, title: 1, year: 1, languages: 1}).sort({title: 1})

[
  {
    "_id": {
      "$oid": "573a13e7f29313caabdc74b6"
    },
    "title": "'Til Madness Do Us Part",
    "languages": [
      "Chinese"
    ],
    "year": 2013
  },
  {
    "_id": {
      "$oid": "573a13d6f29313caabd9f7a8"
    },
    "title": "009 Re: Cyborg",
    "languages": [
      "Japanese"
    ],
    "year": 2012
  }, ... 
]*/
//-----------------------------------------------------------
/* Exercici 4

db.movies.find({languages: ["French"], genres: {$nin: ["Short", "Drama"]}, runtime: {$gte: 90, $lte: 120}})

[
  {
    "_id": {
      "$oid": "573a1392f29313caabcd97ee"
    },
    "countries": [
      "Germany",
      "USA"
    ],
    "genres": [
      "Comedy",
      "Crime",
      "Musical"
    ],
    "runtime": 104,
    "cast": [
      "Albert Prèjean",
      "Florelle",
      "Gaston Modot",
      "Margo Lion"
    ],
    "poster": "https://m.media-amazon.com/images/M/MV5BYTAzMTIwOGMtZmQzNy00NmY0LTk5NTUtYWY3OWY1NjIwZjZmXkEyXkFqcGdeQXVyMzg1ODEwNQ@@._V1_SY1000_SX677_AL_.jpg",
    "title": "L'opèra de quat'sous",
    "lastupdated": "2015-07-23 00:01:26.507000000",
    "languages": [
      "French"
    ],
    "released": {
      "$date": {
        "$numberLong": "-1138147200000"
      }
    },
    "directors": [
      "Georg Wilhelm Pabst"
    ],
    "writers": [
      "Bèla Balèzs (adaptation)",
      "Bertolt Brecht (play)",
      "Lèo Lania (adaptation)",
      "Andrè Mauprey (dialogue)",
      "Ninon Steinhoff (adaptation)",
      "Solange Tèrac (adaptation)",
      "Ladislaus Vajda (adaptation)"
    ],
    "awards": {
      "wins": 1,
      "nominations": 0,
      "text": "1 win."
    },
    "year": 1931,
    "imdb": {
      "rating": 7,
      "votes": 126,
      "id": 22235
    },
    "type": "movie",
    "tomatoes": {
      "viewer": {
        "rating": 3.2,
        "numReviews": 117,
        "meter": 48
      },
      "production": "Warner Bros.",
      "lastUpdated": {
        "$date": "2015-09-14T17:40:04Z"
      }
    },
    "num_mflix_comments": 0
  }, ... 
]*/
//--------------------------------------------------------
/* Exercici 5

db.movies.find({directors: {$exists:false}})

[
  {
    "_id": {
      "$oid": "573a1395f29313caabce2f03"
    },
    "plot": "The extended Forsyte family live a more than pleasant upper middle class life in Victorian and later Edwardian England. The two central characters are Soames Forsyte and his cousin Jolyon ...",
    "genres": [
      "Drama"
    ],
    "runtime": 50,
    "cast": [
      "Eric Porter",
      "Margaret Tyzack",
      "Nyree Dawn Porter",
      "June Barry"
    ],
    "num_mflix_comments": 0,
    "poster": "https://m.media-amazon.com/images/M/MV5BMTQzNjgzODI2MF5BMl5BanBnXkFtZTcwNTg0MTAzMQ@@._V1_SY1000_SX677_AL_.jpg",
    "title": "The Forsyte Saga",
    "fullplot": "The extended Forsyte family live a more than pleasant upper middle class life in Victorian and later Edwardian England. The two central characters are Soames Forsyte and his cousin Jolyon Forsyte. Soames is a solicitor, all proper and straight-laced. His love for the beautiful Irene is his only weakness as is his beautiful daughter Fleur. Young Jolyon is the opposite, a free-thinking artist who abandons his wife to live with his children's nanny. Their lives and their children's lives will intersect over 30 years bringing happiness to some and tragedy to others.",
    "languages": [
      "English"
    ],
    "released": {
      "$date": {
        "$numberLong": "-7603200000"
      }
    },
    "awards": {
      "wins": 4,
      "nominations": 2,
      "text": "Won 1 Primetime Emmy. Another 3 wins & 2 nominations."
    },
    "lastupdated": "2015-09-05 00:46:25.133000000",
    "year": 1967,
    "imdb": {
      "rating": 8.3,
      "votes": 547,
      "id": 61253
    },
    "countries": [
      "UK"
    ],
    "type": "series",
    "tomatoes": {
      "viewer": {
        "rating": 2.6,
        "numReviews": 128,
        "meter": 32
      },
      "dvd": {
        "$date": "2009-11-17T00:00:00Z"
      },
      "production": "Romar Entertainment",
      "lastUpdated": {
        "$date": "2015-08-21T18:17:47Z"
      }
    }
  }, ... 
]*/
//-------------------------------------------------
/* Exercici 6 

db.movies.find({$or: [{year: {$gte: 1970, $lte: 1979}}, {year: {$gte: 1990, $lte: 1999}}]}, {title: 1, year: 1, directors: 1}).toArray()

[
  {
    "_id": {
      "$oid": "573a1396f29313caabce39b2"
    },
    "title": "Le Boucher",
    "directors": [
      "Claude Chabrol"
    ],
    "year": 1970
  },
  {
    "_id": {
      "$oid": "573a1396f29313caabce39f5"
    },
    "title": "Colossus: The Forbin Project",
    "directors": [
      "Joseph Sargent"
    ],
    "year": 1970
  },... 
]*/
//--------------------------------------------------
/* Exercici 7 

db.movies.find({runtime: 90}, {title: 1, year: 1, directors: 1, runtime: 1})

[
  {
    "_id": {
      "$oid": "573a1391f29313caabcd8de7"
    },
    "runtime": 90,
    "title": "Asphalt",
    "directors": [
      "Joe May"
    ],
    "year": 1929
  },
  {
    "_id": {
      "$oid": "573a1391f29313caabcd8e8f"
    },
    "runtime": 90,
    "title": "Disraeli",
    "directors": [
      "Alfred E. Green"
    ],
    "year": 1929
  },... 
]*/
//----------------------------------------------------
/* Exercici 8 

db.movies.find({runtime: {$ne: 90}, runtime: {$exists: true}}, {title: 1, year: 1, directors: 1, runtime: 1}).sort({runtime: 1})

[
  {
    "_id": {
      "$oid": "573a139cf29313caabcf560f"
    },
    "runtime": 1,
    "title": "The Kiss",
    "directors": [
      "William Heise"
    ],
    "year": 1896
  },
  {
    "_id": {
      "$oid": "573a13a0f29313caabd041db"
    },
    "runtime": 1,
    "title": "The Kiss",
    "directors": [
      "William Heise"
    ],
    "year": 1896
  },... 
]*/