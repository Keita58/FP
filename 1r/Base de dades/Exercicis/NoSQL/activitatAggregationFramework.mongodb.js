use("sample_training")

/* Exercici 1 

db.zips.aggregate([
    {$group: {_id: "$state", totalpop: {$sum: "$pop"}}},
    {$sort: {_id: 1}}
])

[
    {
      "_id": "AK",
      "totalpop": 550043
    },
    {
      "_id": "AL",
      "totalpop": 4040587
    },
    {
      "_id": "AR",
      "totalpop": 2350725
    },
    {
      "_id": "AZ",
      "totalpop": 3665228
    },
    {
      "_id": "BCN",
      "totalpop": 244573
    },... 
]*/
//-----------------------------------------------
/* Exercici 2 

db.zips.aggregate([
    {$group: {_id: "$state", totalpop: {$sum: "$pop"}}},
    {$match: {totalpop: {$gt: 2000000}}},
    {$sort: {totalpop: -1}}
])

[
    {
      "_id": "CA",
      "totalpop": 30468715
    },
    {
      "_id": "TX",
      "totalpop": 18372609.216000002
    },
    {
      "_id": "NY",
      "totalpop": 17990455
    },
    {
      "_id": "FL",
      "totalpop": 12937926
    },
    {
      "_id": "PA",
      "totalpop": 11881643
    },... 
]*/
//-----------------------------------------------
/* Exercici 3 

Segons la documentació oficial de MongoDB, el $sum ignora tots els valors que no siguin numèrics.
*/
//-----------------------------------------------
/* Exercici 4 

db.zips.aggregate([
  {$group: {_id: "$state", totalpop: {$sum: "$pop"}}},
  {$match: {totalpop: {$gt: 2000000}}},
  {$count: "states"}
])

[
  {
    "states": 33
  }
]*/
//-----------------------------------------------
/* Exercici 5 

db.zips.aggregate([
  {$group: {_id: {city: "$city", state: "$state"}, pop: {$sum: "$pop"}}},
  {$sort: {_id: 1}}
]).toArray()

[
  ... 
  {
    "_id": {
      "city": "AUGUSTA",
      "state": "IL"
    },
    "pop": 867
  },
  {
    "_id": {
      "city": "AUGUSTA",
      "state": "KS"
    },
    "pop": 11306
  },
  {
    "_id": {
      "city": "AUGUSTA",
      "state": "KY"
    },
    "pop": 1902
  },
  {
    "_id": {
      "city": "AUGUSTA",
      "state": "ME"
    },
    "pop": 25195
  },
  {
    "_id": {
      "city": "AUGUSTA",
      "state": "MI"
    },
    "pop": 2896
  },...
]*/
//-----------------------------------------------
/* Exercici 6 

db.zips.aggregate([
  {$group: {_id: null, uniqueValues: {$addToSet: "$city"}}},
  {$unwind: "$uniqueValues" },
  {$project: { _id: 0 }},
  {$count: "id"}
])

[
  {
    "id": 16700
  }
]*/
//-----------------------------------------------
/* Exercici 7 

db.zips.aggregate([
  {$group: {_id: "$city", zips: {$sum: 1}}},
  {$project: {zips: 1}},
  {$sort: {zips: -1}}
])

[
  {
    "_id": "HOUSTON",
    "zips": 101
  },
  {
    "_id": "LOS ANGELES",
    "zips": 56
  },
  {
    "_id": "PHILADELPHIA",
    "zips": 52
  },
  {
    "_id": "DALLAS",
    "zips": 51
  },...
]*/
//-----------------------------------------------
/* Exercici 8 

db.zips.aggregate([
  {$group: {_id: "$city", zips: {$sum: 1}}},
  {$match: {zips: {$gt: 50}}},
  {$project: {zips: 1}},
  {$sort: {zips: -1}},
])

[
  {
    "_id": "HOUSTON",
    "zips": 101
  },
  {
    "_id": "LOS ANGELES",
    "zips": 56
  },
  {
    "_id": "PHILADELPHIA",
    "zips": 52
  },
  {
    "_id": "DALLAS",
    "zips": 51
  }
]*/
//-----------------------------------------------
/* Exercici 9 

db.zips.aggregate([
  {$group: {_id: "$state", zips: {$addToSet: "$zip"}, count: {$sum: 1}}},
  {$project: {_id: 1, count: 1}},
  {$sort: {_id: 1}}
])
// Aquí el count conta els zips no pel addToSet sinó perquè conta quants zips entren. El posar el addToSet no evita que es contin menys

[
  {
    "_id": "AK",
    "count": 196
  },
  {
    "_id": "AL",
    "count": 567
  },
  {
    "_id": "AR",
    "count": 578
  },
  {
    "_id": "AZ",
    "count": 270
  },... 
]*/
//-----------------------------------------------
/* Exercici 10 

db.zips.aggregate([
  {$group: {_id: {city: "$city", state: "$state"}}},
  {$group: {_id: "$_id.state", count: {$sum: 1}}},
  {$sort: {_id: 1}}
])

[
  {
    "_id": "AK",
    "count": 184
  },
  {
    "_id": "AL",
    "count": 511
  },
  {
    "_id": "AR",
    "count": 563
  },
  {
    "_id": "AZ",
    "count": 178
  },.. 
]*/
