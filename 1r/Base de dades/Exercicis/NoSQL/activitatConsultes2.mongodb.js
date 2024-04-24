use("sample_training")

/* Exercici 1 

db.zips.find({pop: {$gt: 60000}}).count()

166*/
//-----------------------------------------------
/* Exercici 2 

db.zips.find({state: "OH"}, {zip: 1, city: 1, pop: 1}).limit(10).sort({pop: -1})

[
    {
      "_id": {
        "$oid": "5c8eccc1caa187d17ca73b7a"
      },
      "city": "ELYRIA",
      "zip": "44035",
      "pop": 66674
    },
    {
      "_id": {
        "$oid": "5c8eccc1caa187d17ca73b79"
      },
      "city": "MENTOR",
      "zip": "44060",
      "pop": 60109
    },
    {
      "_id": {
        "$oid": "5c8eccc1caa187d17ca73b94"
      },
      "city": "EDGEWATER",
      "zip": "44107",
      "pop": 59702
    },... 
]*/
//-----------------------------------------------
/* Exercici 3 

db.zips.find({zip: "99953"})

[
    {
      "_id": {
        "$oid": "65f57725795d81c0f921c9e7"
      },
      "city": "CERDANYOLA DEL VALLES",
      "zip": "99953",
      "loc": {
        "y": 41.49109,
        "x": 2.14079
      },
      "pop": 57740,
      "state": "BCN"
    }
  ]

db.zips.updateOne({zip: "99953"}, {$inc: {pop: 10000}})
db.zips.find({zip: "99953"})

[
    {
      "_id": {
        "$oid": "65f57725795d81c0f921c9e7"
      },
      "city": "CERDANYOLA DEL VALLES",
      "zip": "99953",
      "loc": {
        "y": 41.49109,
        "x": 2.14079
      },
      "pop": 67740,
      "state": "BCN"
    }
  ]*/
//-----------------------------------------------
/* Exercici 4 

db.zips.deleteOne({zip: "99953"})

{
  "acknowledged": true,
  "deletedCount": 1
}*/
//-----------------------------------------------
/* Exercici 5 

db.zips.updateMany({state: "TX"}, {$mul: {pop: 1.04}})

[
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca74ed3"
    },
    "city": "76350",
    "zip": "76350",
    "loc": {
      "y": 33.431472,
      "x": 98.371578
    },
    "pop": 82.20160000000001,
    "state": "TX"
  },...
]*/
//-----------------------------------------------
/* Exercici 6 

db.zips.distinct("state")

[
  "AK",
  "AL",
  "AR",
  "AZ",
  "BCN",
  "CA",
  "CO",
  "CT",
  "DC",
  "DE",
  "FL",
  "GA",
  "HI",
  "IA",
  "ID",
  "IL",
  "IN",
  "KS",
  "KY",
  "LA",
  "MA",
  "MD",
  "ME",
  "MI",
  "MN",
  "MO",
  "MS",
  "MT",
  "NC",
  "ND",
  "NE",
  "NH",
  "NJ",
  "NM",
  "NV",
  "NY",
  "OH",
  "OK",
  "OR",
  "PA",
  "RI",
  "SC",
  "SD",
  "TN",
  "TX",
  "UT",
  "VA",
  "VT",
  "WA",
  "WI",
  "WV",
  "WY"
]*/
//-----------------------------------------------
/* Exercici 7 

db.zips.distinct("city", {state: "NY"})

[
  "12462",
  "ACCORD",
  "ACRA",
  "ADAMS BASIN",
  "ADAMS CENTER",
  "ADDISON",
  "ADIRONDACK",
  "AFTON",
  "AKRON",
  "ALABAMA",...
] -> 1371*/
//-----------------------------------------------
/* Exercici 8 

db.zips.find({"loc.x": {$gt: 74}}, {zip: 1, pop: 1, _id: 0})

[
  {
    "zip": "35014",
    "pop": 3062
  },
  {
    "zip": "35020",
    "pop": 40549
  },
  {
    "zip": "35004",
    "pop": 6055
  },
  {
    "zip": "35019",
    "pop": 1781
  },... 
]*/
//-----------------------------------------------
/* Exercici 9 

db.zips.find({"loc.y": {$lte: 42}}, {zip: 1, pop: 1, _id: 0}).sort({"loc.y": -1}).limit(3)

[
  {
    "zip": "02895",
    "pop": 53733
  },
  {
    "zip": "14721",
    "pop": 33
  },
  {
    "zip": "16426",
    "pop": 4099
  }
]*/
//-----------------------------------------------
/* Exercici 10 

db.zips.find({state: "CA"}, {zip: 1, _id: 0}).sort({"loc.x": -1})

[
  {
    "zip": "95536"
  },
  {
    "zip": "95558"
  },
  {
    "zip": "95551"
  },... 
]*/
//-----------------------------------------------
/* Exercici 11 

db.zips.find({state: "CA"}, {_id: 1, city: 1, zip: 1, pop: 1, state: 1}).sort({pop: -1}).limit(8)

[
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca6f39d"
    },
    "city": "BELL GARDENS",
    "zip": "90201",
    "pop": 99568,
    "state": "CA"
  },
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca6f366"
    },
    "city": "LOS ANGELES",
    "zip": "90011",
    "pop": 96074,
    "state": "CA"
  },
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca6f3db"
    },
    "city": "NORWALK",
    "zip": "90650",
    "pop": 94188,
    "state": "CA"
  },
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca6f423"
    },
    "city": "ARLETA",
    "zip": "91331",
    "pop": 88114,
    "state": "CA"
  },
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca6f3b7"
    },
    "city": "SOUTH GATE",
    "zip": "90280",
    "pop": 87026,
    "state": "CA"
  },
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca6f388"
    },
    "city": "LOS ANGELES",
    "zip": "90044",
    "pop": 83958,
    "state": "CA"
  },
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca6f510"
    },
    "city": "FONTANA",
    "zip": "92335",
    "pop": 81255,
    "state": "CA"
  },
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca6f3ab"
    },
    "city": "HOLLY PARK",
    "zip": "90250",
    "pop": 78511,
    "state": "CA"
  }
] 

var busca = db.zips.find({state: "CA"}).sort({pop: -1}).limit(8)

busca.forEach(patata => {
  db.zips.updateOne({"_id": patata._id}, {$mul: {pop: 2}})
});

//El resultat de fer aquest forEach es un "undefined", però si fem la busca anterior una altra vegada
//es pot veure que ha realitzat la multiplicació

db.zips.find({state: "CA"}).sort({pop: -1}).limit(8)

[
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca6f39d"
    },
    "city": "BELL GARDENS",
    "zip": "90201",
    "loc": {
      "y": 33.969177,
      "x": 118.17205
    },
    "pop": 199136,
    "state": "CA"
  },
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca6f366"
    },
    "city": "LOS ANGELES",
    "zip": "90011",
    "loc": {
      "y": 34.007856,
      "x": 118.258189
    },
    "pop": 192148,
    "state": "CA"
  },
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca6f3db"
    },
    "city": "NORWALK",
    "zip": "90650",
    "loc": {
      "y": 33.90564,
      "x": 118.081767
    },
    "pop": 188376,
    "state": "CA"
  },
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca6f423"
    },
    "city": "ARLETA",
    "zip": "91331",
    "loc": {
      "y": 34.258081,
      "x": 118.420692
    },
    "pop": 176228,
    "state": "CA"
  },
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca6f3b7"
    },
    "city": "SOUTH GATE",
    "zip": "90280",
    "loc": {
      "y": 33.94617,
      "x": 118.201349
    },
    "pop": 174052,
    "state": "CA"
  },
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca6f388"
    },
    "city": "LOS ANGELES",
    "zip": "90044",
    "loc": {
      "y": 33.955089,
      "x": 118.290119
    },
    "pop": 167916,
    "state": "CA"
  },
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca6f510"
    },
    "city": "FONTANA",
    "zip": "92335",
    "loc": {
      "y": 34.079351,
      "x": 117.455114
    },
    "pop": 162510,
    "state": "CA"
  },
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca6f3ab"
    },
    "city": "HOLLY PARK",
    "zip": "90250",
    "loc": {
      "y": 33.913214,
      "x": 118.346978
    },
    "pop": 157022,
    "state": "CA"
  }
]*/
