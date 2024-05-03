use("sample_mflix");

/* Exercici 1

db.movies.find({title: /o$/, $or: [{languages: "Catalan"}, {"awards.wins": {$gt: 90}}]}, {title: 1, languages: 1, "awards.wins": 1, _id: 0})

[
  {
    "title": "Atolladero",
    "languages": [
    "Spanish",
    "Catalan"
    ],
    "awards": {
    "wins": 1
    }
  },
  {
    "title": "Juno",
    "languages": [
    "English"
    ],
    "awards": {
    "wins": 102
    }
  },
  {
    "title": "Argo",
    "languages": [
    "English",
    "Persian"
    ],
    "awards": {
    "wins": 98
    }
  },
  {
    "title": "Map of the Sounds of Tokyo",
    "languages": [
    "Japanese",
    "English",
    "Catalan"
    ],
    "awards": {
    "wins": 1
    }
  },
  {
    "title": "Argo",
    "languages": [
    "English",
    "Persian"
    ],
    "awards": {
    "wins": 98
    }
  }
]*/

/* Exercici 2 

db.movies.find({cast: "Emma Watson", year:{$gte:2010, $lt:2020}}, {title: 1, cast: 1, year: 1, _id: 0}).sort({title: 1})

[
  {
    "cast": [
      "Bill Nighy",
      "Emma Watson",
      "Richard Griffiths",
      "Harry Melling"
    ],
    "title": "Harry Potter and the Deathly Hallows: Part 1",
    "year": 2010
  },
  {
    "cast": [
      "Katie Chang",
      "Israel Broussard",
      "Emma Watson",
      "Claire Julien"
    ],
    "title": "The Bling Ring",
    "year": 2013
  }
]*/

/* Exercici 3 

db.movies.find({"directors.8": {$exists: true}}, {titles: 1, directors: 1}).sort({"directors.length": 1})

[
  {
    "_id": {
      "$oid": "573a1393f29313caabcdbfb0"
    },
    "directors": [
      "Norman Ferguson",
      "James Algar",
      "Samuel Armstrong",
      "Ford Beebe Jr.",
      "Jim Handley",
      "T. Hee",
      "Wilfred Jackson",
      "Hamilton Luske",
      "Bill Roberts",
      "Paul Satterfield",
      "Ben Sharpsteen"
    ]
  },
  {
    "_id": {
      "$oid": "573a1397f29313caabce6b75"
    },
    "directors": [
      "Alf Brustellin",
      "Rainer Werner Fassbinder",
      "Alexander Kluge",
      "Maximiliane Mainka",
      "Beate Mainka-Jellinghaus",
      "Peter Schubert",
      "Bernhard Sinkel",
      "Hans Peter Cloos",
      "Edgar Reitz",
      "Katja Rupè",
      "Volker Schlèndorff"
    ]
  },... 
]*/

/* Exercici 4 

db.movies.findOne()

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
}

db.movies.updateMany({}, {$mul: {runtime: 0.01666666}})

{
  "acknowledged": true,
  "insertedId": null,
  "matchedCount": 21349,
  "modifiedCount": 21349,
  "upsertedCount": 0
}

db.movies.find({title: "The Great Train Robbery"})

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
    "runtime": 0.18333326,
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
  },
  {
    "_id": {
      "$oid": "573a1397f29313caabce7257"
    },
    "plot": "In Victorian England, a master criminal makes elaborate plans to steal a shipment of gold from a moving train.",
    "genres": [
      "Adventure",
      "Crime",
      "Drama"
    ],
    "runtime": 1.8333325999999999,
    "rated": "PG",
    "cast": [
      "Sean Connery",
      "Donald Sutherland",
      "Lesley-Anne Down",
      "Alan Webb"
    ],
    "poster": "https://m.media-amazon.com/images/M/MV5BOWE4M2UwNWEtODFjOS00M2JiLTlhOGQtNTljZjI5ZTZlM2MzXkEyXkFqcGdeQXVyNjUwNzk3NDc@._V1_SY1000_SX677_AL_.jpg",
    "title": "The Great Train Robbery",
    "fullplot": "Sutherland and Connery wish to rob a moving train's safe in Victorian England. They need wax impressions of keys, coffins, dead cats, and a great deal of planning in order to pull it off.",
    "languages": [
      "English"
    ],
    "released": {
      "$date": "1979-02-02T00:00:00Z"
    },
    "directors": [
      "Michael Crichton"
    ],
    "writers": [
      "Michael Crichton (screenplay)",
      "Michael Crichton (novel)"
    ],
    "awards": {
      "wins": 1,
      "nominations": 1,
      "text": "1 win & 1 nomination."
    },
    "lastupdated": "2015-08-21 00:24:14.033000000",
    "year": 1978,
    "imdb": {
      "rating": 7,
      "votes": 10895,
      "id": 79240
    },
    "countries": [
      "UK"
    ],
    "type": "movie",
    "tomatoes": {
      "viewer": {
        "rating": 3.4,
        "numReviews": 7862,
        "meter": 69
      },
      "dvd": {
        "$date": "2002-08-09T00:00:00Z"
      },
      "critic": {
        "rating": 6.5,
        "numReviews": 23,
        "meter": 78
      },
      "lastUpdated": {
        "$date": "2015-08-24T18:04:42Z"
      },
      "rotten": 5,
      "production": "MGM",
      "fresh": 18
    },
    "num_mflix_comments": 0
  }
]*/

use("sample_training")

/* Exercici 5 

db.grades.find({class_id: 16, "scores.type": "homework", "scores.score": {$gte: 80, $lt: 86}})

[
  {
    "_id": {
      "$oid": "56d5f7eb604eb380b0d8db06"
    },
    "student_id": 56,
    "scores": [
      {
        "type": "exam",
        "score": 45.34521245353286
      },
      {
        "type": "quiz",
        "score": 96.07093755151331
      },
      {
        "type": "homework",
        "score": 99.7526962446806
      },
      {
        "type": "homework",
        "score": 8.055275231572756
      }
    ],
    "class_id": 16
  },
  {
    "_id": {
      "$oid": "56d5f7eb604eb380b0d8dc72"
    },
    "student_id": 93,
    "scores": [
      {
        "type": "exam",
        "score": 52.82384818675659
      },
      {
        "type": "quiz",
        "score": 12.416143597802499
      },
      {
        "type": "homework",
        "score": 98.14834761000435
      },
      {
        "type": "homework",
        "score": 64.64295601601002
      }
    ],
    "class_id": 16
  },... 
]*/

/* Exercici 6 

db.grades.aggregate([
  {$match: {class_id: 7}},
  {$unwind: "$scores"},
  {$group: {_id: {student_id: "$student_id", class_id: "$class_id", type: "$scores.type"}, scores: {$avg: "$scores.score"}}},
  {$sort: {"_id.student_id": 1, "_id.type": 1}}
])

[
  {
    "_id": {
      "student_id": 0,
      "class_id": 7,
      "type": "exam"
    },
    "scores": 11.182574562228819
  },
  {
    "_id": {
      "student_id": 0,
      "class_id": 7,
      "type": "homework"
    },
    "scores": 53.56120570291038
  },
  {
    "_id": {
      "student_id": 0,
      "class_id": 7,
      "type": "quiz"
    },
    "scores": 8.819662605640733
  },
  {
    "_id": {
      "student_id": 28,
      "class_id": 7,
      "type": "exam"
    },
    "scores": 66.80752036354325
  },... 
]*/

/* Exercici 7 

db.grades.aggregate([
  {$group: {_id: "$class_id", count: {$sum: 1}}},
  {$sort: {count: -1}},
  {$limit: 3}
])

[
  {
    "_id": 108,
    "count": 240
  },
  {
    "_id": 299,
    "count": 238
  },
  {
    "_id": 243,
    "count": 235
  }
]*/

/* Exercici 8 

db.grades.aggregate([
  {$unwind: "$scores"},
  {$match: {"scores.type": "exam"}},
  {$group: {_id: {class_id:"$class_id", type: "$scores.type"}, students_id: {$push: "$student_id"}, notes: {$push: "$scores.score"}}},
  {$match: {students_id: 48}},
  {$project: {students_id: 0, "_id.type": 0}}
])

[
  {
    "_id": {
      "class_id": 299
    },
    "notes": [
      31.779958039781654,
      42.186743527649064,
      69.4494919297887,
      92.9739605514939,
      19.94859052695146,
      85.76720379955118,
      80.44622261883462,
      16.60098998437345,
      41.48989128440025,
      57.64893594534435,
      91.04248033582314,
      47.760988142245075,
      78.15182424999428,
      22.842632804832665,
      46.49665646817962,
      62.39956808378656,
      13.96666090274099,
      75.46147050640354,
      65.1444885698009,
      72.46756182355817,
      89.58956202278901,
      58.07720608666204,
      19.598516800889833,
      30.423128225617933,
      67.5830449774851,
      74.98417000176703,
      1.5933343942953315,
      25.51267025198427,
      39.15928453238823,
      46.346202051776686,
      92.33592444308746,
      96.05556124860314,
      63.12839498584469,
      29.070477545480177,
      72.71752336042113,
      87.56203133521903,
      82.93714263505578,
      53.932177274278914, ... 
    ]
  }
}*/


/* Exercici 9 

//db.trips.createIndex({"end station location": "2dsphere"})

db.trips.find({
  "end station location":{
    $nearSphere:{
      $geometry:{type: "Point", coordinates: [-74.00176, 40.75066]},
      $maxDistance: 500
    }
  }
}, {"end station name": 1, bikeid: 1}).sort({"end station location.coordinates": 1})

[
  {
    "_id": {
      "$oid": "572bb8222b288919b68acd02"
    },
    "end station name": "11 Ave & W 27 St",
    "bikeid": 23116
  },
  {
    "_id": {
      "$oid": "572bb8232b288919b68b14c3"
    },
    "end station name": "11 Ave & W 27 St",
    "bikeid": 17476
  },
  {
    "_id": {
      "$oid": "572bb8232b288919b68b19b0"
    },
    "end station name": "11 Ave & W 27 St",
    "bikeid": 20613
  },... 
]*/