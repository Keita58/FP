use('sample_training');

/* Exercici 1

db.zips.find({zip: "01222"});

[
    {
    "_id": {
        "$oid": "5c8eccc1caa187d17ca719c7"
    },
    "city": "ASHLEY FALLS",
    "zip": "01222",
    "loc": {
        "y": 42.059552,
        "x": 73.320195
    },
    "pop": 561,
    "state": "MA"
    }
]
*/
//------------------------------------------------
/* Exercici 2

db.zips.find().skip(2420).limit(1);

[
    {
    "_id": {
        "$oid": "5c8eccc1caa187d17ca6f68c"
    },
    "city": "SOLEDAD",
    "zip": "93960",
    "loc": {
        "y": 36.41964,
        "x": 121.324286
    },
    "pop": 9046,
    "state": "CA"
    }
]
*/
//-----------------------------------------------
/* Exercici 3 

db.zips.find().sort({_id: -1}).limit(5);

[
    {
    "_id": {
        "$oid": "5c8eccc1caa187d17ca76050"
    },
    "city": "AFTON",
    "zip": "83110",
    "loc": {
        "y": 42.712829,
        "x": 110.941976
    },
    "pop": 3201,
    "state": "WY"
    },
    {
    "_id": {
        "$oid": "5c8eccc1caa187d17ca7604f"
    },
    "city": "LYMAN",
    "zip": "82937",
    "loc": {
        "y": 41.329136,
        "x": 110.292629
    },
    "pop": 2327,
    "state": "WY"
    },
    {
    "_id": {
        "$oid": "5c8eccc1caa187d17ca7604e"
    },
    "city": "LONETREE",
    "zip": "82936",
    "loc": {
        "y": 41.049144,
        "x": 110.172862
    },
    "pop": 24,
    "state": "WY"
    },
    {
    "_id": {
        "$oid": "5c8eccc1caa187d17ca7604d"
    },
    "city": "THAYNE",
    "zip": "83127",
    "loc": {
        "y": 42.933026,
        "x": 111.011354
    },
    "pop": 505,
    "state": "WY"
    },
    {
    "_id": {
        "$oid": "5c8eccc1caa187d17ca7604c"
    },
    "city": "LA BARGE",
    "zip": "83123",
    "loc": {
        "y": 42.24734,
        "x": 110.210865
    },
    "pop": 606,
    "state": "WY"
    }
]
*/
//----------------------------------------------
/* Exercici 4 

db.zips.find({city: "NEW YORK"}, {zip: 1, _id: 0})

[
    {
    "zip": "10001"
    },
    {
    "zip": "10003"
    },
    {
    "zip": "10005"
    },
    {
    "zip": "10006"
    },
    {
    "zip": "10009"
    },
    {
    "zip": "10010"
    },
    {
    "zip": "10002"
    },
    {
    "zip": "10012"
    },
    {
    "zip": "10011"
    },
    {
    "zip": "10007"
    },
    {
    "zip": "10013"
    },
    {
    "zip": "10014"
    },
    {
    "zip": "10017"
    },
    {
    "zip": "10018"
    },
    {
    "zip": "10019"
    },
    {
    "zip": "10020"
    },
    {
    "zip": "10021"
    },
    {
    "zip": "10016"
    },
    {
    "zip": "10024"
    },
    {
    "zip": "10022"
    }
]
*/
//---------------------------------------
/* Exercici 5 

db.zips.find({$and: [{city: "NEW YORK"}, {pop: {$gte: 50000}}]}, {zip: 1, _id: 0});

[
    {
        "zip": "10003"
    },
    {
        "zip": "10009"
    },
    {
        "zip": "10002"
    },
    {
        "zip": "10021"
    },
    {
        "zip": "10016"
    },
    {
        "zip": "10024"
    },
    {
        "zip": "10027"
    },
    {
        "zip": "10025"
    },
    {
        "zip": "10023"
    },
    {
        "zip": "10031"
    },
    {
        "zip": "10032"
    },
    {
        "zip": "10033"
    },
    {
        "zip": "10029"
    },
    {
        "zip": "10128"
    }
]
*/
//-------------------------------------------
/* Exercici 6 

db.zips.find({$or: [{city: "NEW YORK"}, {pop: 90000}]}, {zip: 1, _id: 0}).toArray();

[
    {
      "zip": "10003"
    },
    {
      "zip": "10001"
    },
    {
      "zip": "10005"
    },
    {
      "zip": "10006"
    },
    {
      "zip": "10017"
    },
    {
      "zip": "10002"
    },
    {
      "zip": "10007"
    },
    {
      "zip": "10014"
    },
    {
      "zip": "10019"
    },
    {
      "zip": "10022"
    },
    {
      "zip": "10025"
    },
    {
      "zip": "10037"
    },
    {
      "zip": "10038"
    },
    {
      "zip": "10040"
    },
    {
      "zip": "10020"
    },
    {
      "zip": "10016"
    },
    {
      "zip": "10023"
    },
    {
      "zip": "10026"
    },
    {
      "zip": "10033"
    }, ...
] */
//----------------------------------------------
/* Exercici 7 

db.zips.find({city: /^Z/}).sort({city: 1}).toArray();

[
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca712db"
    },
    "city": "ZACHARIAH",
    "zip": "41396",
    "loc": {
      "y": 37.674917,
      "x": 83.677244
    },
    "pop": 36,
    "state": "KY"
  },
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca7156a"
    },
    "city": "ZACHARY",
    "zip": "70791",
    "loc": {
      "y": 30.656129,
      "x": 91.135841
    },
    "pop": 18647,
    "state": "LA"
  },
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca73a17"
    },
    "city": "ZAHL",
    "zip": "58856",
    "loc": {
      "y": 48.578747,
      "x": 103.659926
    },
    "pop": 94,
    "state": "ND"
  },
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca724c9"
    },
    "city": "ZALMA",
    "zip": "63787",
    "loc": {
      "y": 37.13648,
      "x": 90.075711
    },
    "pop": 898,
    "state": "MO"
  },
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca6f88a"
    },
    "city": "ZAMORA",
    "zip": "95698",
    "loc": {
      "y": 38.799896,
      "x": 121.90654
    },
    "pop": 317,
    "state": "CA"
  },
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca73aa2"
    },
    "city": "ZANESFIELD",
    "zip": "43360",
    "loc": {
      "y": 40.302396,
      "x": 83.664832
    },
    "pop": 1171,
    "state": "OH"
  }, ...
] */
//------------------------------------------------
/* Exercici 8 

db.zips.find({state: {$in: ['OR', 'NY']}}).sort({city: 1}).toArray();

[
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca7320c"
    },
    "city": "12462",
    "zip": "12462",
    "loc": {
      "y": 42.026764,
      "x": 74.444263
    },
    "pop": 53,
    "state": "NY"
  },
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca731e3"
    },
    "city": "ACCORD",
    "zip": "12404",
    "loc": {
      "y": 41.808308,
      "x": 74.235336
    },
    "pop": 2695,
    "state": "NY"
  },
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca731e9"
    },
    "city": "ACRA",
    "zip": "12405",
    "loc": {
      "y": 42.330367,
      "x": 74.085723
    },
    "pop": 525,
    "state": "NY"
  },
  {
    "_id": {
      "$oid": "5c8eccc1caa187d17ca74193"
    },
    "city": "ADAMS",
    "zip": "97810",
    "loc": {
      "y": 45.749678,
      "x": 118.617582
    },
    "pop": 609,
    "state": "OR"
  }, ... 
] */
//----------------------------------------------------
/* Exercici 9 

db.zips.find().sort({zip: -1}).limit(1) // -> {"_id": {"$oid": "5c8eccc1caa187d17ca6f01a"}, "city": "KETCHIKAN", "zip": "99950", "loc": {"y": 55.942471, "x": 133.18479}, "pop": 422, "state": "AK"}

db.zips.insertOne({"city": "SABADELL", "zip": "99951", "loc": {"y": 41.54329, "x": 2.10942}, "pop": 211734, "state": "BCN"})

{
	"acknowledged" : true,
	"insertedId" : ObjectId("65f5755bd7814755b205c8ab")
}

:)*/
//----------------------------------------------------
/* Exercici 10 

db.zips.insertMany([
  {"city": "BARBERA DEL VALLES", "zip": "99952", "loc": {"y": 41.5159, "x": 2.12457}, "pop": 32839, "state": "BCN"},
  {"city": "CERDANYOLA DEL VALLES", "zip": "99953", "loc": {"y": 41.49109, "x": 2.14079}, "pop": 57740, "state": "BCN"}
])

{
  "acknowledged": true,
  "insertedIds": {
    "0": {
      "$oid": "65f57725795d81c0f921c9e6"
    },
    "1": {
      "$oid": "65f57725795d81c0f921c9e7"
    }
  }
}*/