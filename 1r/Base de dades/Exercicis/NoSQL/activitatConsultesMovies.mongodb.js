use("sample_mflix")

/* Exercici 1

db.movies.find({}, {_id: 1, title: 2, year: 3, genres: 4}).sort({year: 1})

[
  {
    "_id": {
      "$oid": "573a139cf29313caabcf560f"
    },
    "genres": [
      "Short",
      "Romance"
    ],
    "title": "The Kiss",
    "year": 1896
  },
  {
    "_id": {
      "$oid": "573a13a0f29313caabd041db"
    },
    "genres": [
      "Short",
      "Romance"
    ],
    "title": "The Kiss",
    "year": 1896
  }, ...
]*/
//-------------------------------------------------------------
/* Exercici 2 

db.movies.find({year: {$lte: 1989, $gte: 1980}}, {_id: 1, title: 1, year: 1, genres: 1}).sort({year: 1, title: 1})

[
    {
      "_id": {
        "$oid": "573a1397f29313caabce7b59"
      },
      "genres": [
        "Drama",
        "History",
        "Romance"
      ],
      "title": "A Tale of Two Cities",
      "year": 1980
    },
    {
      "_id": {
        "$oid": "573a1397f29313caabce7671"
      },
      "genres": [
        "Drama",
        "Mystery",
        "Thriller"
      ],
      "title": "Aakrosh",
      "year": 1980
    }, ...
]*/
//-----------------------------------------------------------
/* Exercici 3

db.movies.find({genres:{$exists:true}},  {_id: 1, title: 1, year: 1, genres: 1}).sort({year: -1})

[
  {
    "_id": {
      "$oid": "573a13eaf29313caabdcfbc1"
    },
    "genres": [
      "Documentary"
    ],
    "title": "The Roosevelts: An Intimate History",
    "year": "2014è"
  },
  {
    "_id": {
      "$oid": "573a13f1f29313caabddc613"
    },
    "genres": [
      "Action",
      "Adventure",
      "Sci-Fi"
    ],
    "title": "Halo: Nightfall",
    "year": "2014è"
  }, ... 
]*/
//-------------------------------------------------------------
/* Exercici 4 

db.movies.find({genres: "Short", year: 2000}).sort({runtime: 1})

[
  {
    "_id": {
      "$oid": "573a13a3f29313caabd0f030"
    },
    "plot": "A group of snooty birds roosting on a telephone wire get their just desserts when a goofy bird drops in.",
    "genres": [
      "Animation",
      "Short",
      "Comedy"
    ],
    "runtime": 3,
    "cast": [
      "Ralph Eggleston"
    ],
    "num_mflix_comments": 0,
    "poster": "https://m.media-amazon.com/images/M/MV5BMTQzMDEyODYwNF5BMl5BanBnXkFtZTgwOTcxMDgwMjE@._V1_SY1000_SX677_AL_.jpg",
    "title": "For the Birds",
    "fullplot": "This animated short starts with a group of small birds perched together on a telephone wire. When a much larger and awkward-looking bird arrives, the smaller birds reject him with taunts and insults. Still, he persists in trying to win their friendship, until at last the smaller birds decide to shove him from their perch. Only too late do they discover that there may be a problem with this plan.",
    "countries": [
      "USA"
    ],
    "released": {
      "$date": "2001-11-02T00:00:00Z"
    },
    "directors": [
      "Ralph Eggleston"
    ],
    "rated": "G",
    "awards": {
      "wins": 7,
      "nominations": 1,
      "text": "Won 1 Oscar. Another 6 wins & 1 nomination."
    },
    "lastupdated": "2015-09-15 03:57:58.743000000",
    "year": 2000,
    "imdb": {
      "rating": 8.2,
      "votes": 18236,
      "id": 248808
    },
    "type": "movie",
    "tomatoes": {
      "viewer": {
        "rating": 4.1,
        "numReviews": 1759,
        "meter": 89
      },
      "production": "Pixar Animation Studios",
      "lastUpdated": {
        "$date": "2015-09-12T18:50:43Z"
      }
    }
  }, ... 
]*/
//------------------------------------------------------
/* Exercici 5 

db.movies.find({genres: "Short", year: {$lte: 2010, $gte: 2000}})

[
  ..., 
  {
    "_id": {
      "$oid": "573a13a3f29313caabd0ce20"
    },
    "plot": "Europe; the plague years. A wigmaker, locked in his shop, observes the events and writes about them in his journal. Mostly, we see shrouded bodies, and a young girl who lives in the tavern ...",
    "genres": [
      "Short",
      "Animation",
      "Drama"
    ],
    "runtime": 15,
    "cast": [
      "Kenneth Branagh",
      "Alice Fairhall",
      "Roger Law",
      "Daren C. Evans"
    ],
    "title": "The Periwig-Maker",
    "fullplot": "Europe; the plague years. A wigmaker, locked in his shop, observes the events and writes about them in his journal. Mostly, we see shrouded bodies, and a young girl who lives in the tavern across the way that gets progressively sicker. When she dies, the wigmaker goes to the mass grave where she's buried and cuts off her luxurious red hair; he makes himself a wig from it, and soon dies.",
    "languages": [
      "English"
    ],
    "released": {
      "$date": "2000-10-18T00:00:00Z"
    },
    "directors": [
      "Steffen Schèffler"
    ],
    "writers": [
      "Annette Schèffler",
      "Steffen Schèffler",
      "Daniel Defoe (book)"
    ],
    "awards": {
      "wins": 18,
      "nominations": 1,
      "text": "Nominated for 1 Oscar. Another 17 wins & 1 nomination."
    },
    "lastupdated": "2015-04-09 01:09:45.433000000",
    "year": 2000,
    "imdb": {
      "rating": 7,
      "votes": 429,
      "id": 239797
    },
    "countries": [
      "Germany"
    ],
    "type": "movie",
    "tomatoes": {
      "viewer": {
        "rating": 0,
        "numReviews": 0
      },
      "lastUpdated": {
        "$date": "2015-06-29T19:15:13Z"
      }
    },
    "num_mflix_comments": 0
  }, ...
]*/
//---------------------------------------------------------------------
/* Exercici 6 

db.movies.find({$or: [{year: 2000}, {year: 2010}]})

[
  ...,
  {
    "_id": {
      "$oid": "573a139af29313caabcf0e8d"
    },
    "fullplot": "Having been hopelessly repressed and facing eventual certain death at the chicken farm where they are held, Rocky the rooster and Ginger the chicken decide to rebel against the evil Mr. and Ms. Tweedy, the farm's owners. Rocky and Ginger lead their fellow chickens in a great escape from the murderous farmers and their farm of doom.",
    "imdb": {
      "rating": 7,
      "votes": 128693,
      "id": 120630
    },
    "year": 2000,
    "plot": "When a bird \"flies\" into a chicken farm, the fellow chickens see him as an opportunity to escape their evil owners.",
    "genres": [
      "Animation",
      "Family",
      "Comedy"
    ],
    "rated": "G",
    "metacritic": 88,
    "title": "Chicken Run",
    "lastupdated": "2015-08-26 00:45:33.447000000",
    "languages": [
      "English"
    ],
    "writers": [
      "Peter Lord (original story)",
      "Nick Park (original story)",
      "Karey Kirkpatrick (screenplay)",
      "Mark Burton (additional dialogue)",
      "John O'Farrell (additional dialogue)"
    ],
    "type": "movie",
    "tomatoes": {
      "website": "http://www.reel.com/reel.asp?node=chickenrun",
      "viewer": {
        "rating": 3,
        "numReviews": 546668,
        "meter": 63
      },
      "dvd": {
        "$date": "2000-11-21T00:00:00Z"
      },
      "critic": {
        "rating": 8.1,
        "numReviews": 170,
        "meter": 97
      },
      "boxOffice": "$106.6M",
      "consensus": "Chicken Run has all the charm of Nick Park's Wallace & Gromit, and something for everybody. The voice acting is fabulous, the slapstick is brilliant, and the action sequences are spectacular.",
      "rotten": 5,
      "production": "Dreamworks Pictures",
      "lastUpdated": {
        "$date": "2015-09-12T17:39:05Z"
      },
      "fresh": 165
    },
    "poster": "https://m.media-amazon.com/images/M/MV5BY2UyYjFkNzAtYzIyMC00MGI1LTlkNDktNzUyOGQ5NTI2ZGFjXkEyXkFqcGdeQXVyNTUyMzE4Mzg@._V1_SY1000_SX677_AL_.jpg",
    "num_mflix_comments": 0,
    "released": {
      "$date": "2000-06-23T00:00:00Z"
    },
    "awards": {
      "wins": 24,
      "nominations": 25,
      "text": "Nominated for 1 Golden Globe. Another 23 wins & 25 nominations."
    },
    "countries": [
      "UK",
      "USA",
      "France"
    ],
    "cast": [
      "Phil Daniels",
      "Lynn Ferguson",
      "Mel Gibson",
      "Tony Haygarth"
    ],
    "directors": [
      "Peter Lord",
      "Nick Park"
    ],
    "runtime": 84
  },... 
]*/
//------------------------------------------------------------------------
/* Exercici 7 

db.movies.find({$or: [{year: 2000}, {year: 2010}], genres: "Short"})

[
  ...,
  {
    "_id": {
      "$oid": "573a13a3f29313caabd0e2d6"
    },
    "plot": "Michael Jordan Is Praised as the Greatest Basketball Player of All Time in This Documentary!",
    "genres": [
      "Documentary",
      "Short",
      "Sport"
    ],
    "runtime": 46,
    "metacritic": 57,
    "cast": [
      "Michael Jordan",
      "Phil Jackson",
      "Doug Collins",
      "Bob Greene"
    ],
    "poster": "https://m.media-amazon.com/images/M/MV5BMTIyMDg2NTAyM15BMl5BanBnXkFtZTcwMzg3MDU1MQ@@._V1_SY1000_SX677_AL_.jpg",
    "title": "Michael Jordan to the Max",
    "fullplot": "Michael Jordan Is Praised as the Greatest Basketball Player of All Time in This Documentary!",
    "languages": [
      "English"
    ],
    "released": {
      "$date": "2000-05-05T00:00:00Z"
    },
    "directors": [
      "Don Kempf",
      "James D. Stern"
    ],
    "writers": [
      "Jonathan Hock"
    ],
    "awards": {
      "wins": 0,
      "nominations": 1,
      "text": "1 nomination."
    },
    "lastupdated": "2015-08-23 00:30:38.937000000",
    "year": 2000,
    "imdb": {
      "rating": 7.5,
      "votes": 1640,
      "id": 245280
    },
    "countries": [
      "USA"
    ],
    "type": "movie",
    "tomatoes": {
      "viewer": {
        "rating": 3.8,
        "numReviews": 3104,
        "meter": 82
      },
      "dvd": {
        "$date": "2001-02-13T00:00:00Z"
      },
      "critic": {
        "rating": 6.2,
        "numReviews": 27,
        "meter": 67
      },
      "lastUpdated": {
        "$date": "2015-09-15T19:18:35Z"
      },
      "rotten": 9,
      "production": "Giant Screen Sport",
      "fresh": 18
    },
    "num_mflix_comments": 0
  },...
]*/
//---------------------------------------------------------
/* Exercici 8

db.movies.find({$or: [{genres: "Drama"}, {genres: "Horror"}, {genres: "Music"}], runtime: 120, languages: ["French"]}, {title: 1, year: 1, directors: 1, _id: 0}).sort({year: -1})

[
  {
    "title": "Cafè de Flore",
    "directors": [
      "Jean-Marc Vallèe"
    ],
    "year": 2011
  },
  {
    "title": "Mozart's Sister",
    "directors": [
      "Renè Fèret"
    ],
    "year": 2010
  },
  {
    "title": "Sister Smile",
    "directors": [
      "Stijn Coninx"
    ],
    "year": 2009
  }, ... 
]*/
//-------------------------------------------------------
/* Exercici 9 

db.movies.find({year: {$lt: 1930}})

[
  {
    "_id": {
      "$oid": "573a1390f29313caabcd42e8"
    },
    "plot": "A group of bandits stage a brazen train hold-up, only to find a determined posse hot on their heels.",
    "genres": [
      "Short",
      "Western"
    ],
    "runtime": 11,
    "cast": [
      "A.C. Abadie",
      "Gilbert M. 'Broncho Billy' Anderson",
      "George Barnes",
      "Justus D. Barnes"
    ],
    "poster": "https://m.media-amazon.com/images/M/MV5BMTU3NjE5NzYtYTYyNS00MDVmLWIwYjgtMmYwYWIxZDYyNzU2XkEyXkFqcGdeQXVyNzQzNzQxNzI@._V1_SY1000_SX677_AL_.jpg",
    "title": "The Great Train Robbery",
    "fullplot": "Among the earliest existing films in American cinema - notable as the first film that presented a narrative story to tell - it depicts a group of cowboy outlaws who hold up a train and rob the passengers. They are then pursued by a Sheriff's posse. Several scenes have color included - all hand tinted.",
    "languages": [
      "English"
    ],
    "released": {
      "$date": {
        "$numberLong": "-2085523200000"
      }
    },
    "directors": [
      "Edwin S. Porter"
    ],
    "rated": "TV-G",
    "awards": {
      "wins": 1,
      "nominations": 0,
      "text": "1 win."
    },
    "lastupdated": "2015-08-13 00:27:59.177000000",
    "year": 1903,
    "imdb": {
      "rating": 7.4,
      "votes": 9847,
      "id": 439
    },
    "countries": [
      "USA"
    ],
    "type": "movie",
    "tomatoes": {
      "viewer": {
        "rating": 3.7,
        "numReviews": 2559,
        "meter": 75
      },
      "fresh": 6,
      "critic": {
        "rating": 7.6,
        "numReviews": 6,
        "meter": 100
      },
      "rotten": 0,
      "lastUpdated": {
        "$date": "2015-08-08T19:16:10Z"
      }
    },
    "num_mflix_comments": 0
  }, ... 
]*/
//----------------------------------------------------
/* Exercici 10 

db.movies.find({year: {$lt: 1930}, languages: ["English"]}, {_id: 1, title: 1, year: 1, languages: 1}).toArray()

[
  {
    "_id": {
      "$oid": "573a1390f29313caabcd42e8"
    },
    "title": "The Great Train Robbery",
    "languages": [
      "English"
    ],
    "year": 1903
  },
  {
    "_id": {
      "$oid": "573a1390f29313caabcd446f"
    },
    "title": "A Corner in Wheat",
    "languages": [
      "English"
    ],
    "year": 1909
  }, ... 
]*/
//----------------------------------------------------
/* Exercici 11 

db.movies.find({year: {$lt: 1930}, $and: [{languages: {$not: /English/}}, {languages: {$exists: true}}]}, {_id: 1, title: 1, year: 1, languages: 1}).toArray()

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
  }
]*/