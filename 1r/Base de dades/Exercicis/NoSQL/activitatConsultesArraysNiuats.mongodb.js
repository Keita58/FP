use("results")

/* Exercici 1 

db.results.find({results: {$gte: 90}})

[
    {
        "_id": {
        "$oid": "65fdca924ab5e7e7a7bc42c6"
        },
        "results": [
        47,
        41,
        30,
        99
        ]
    },
    {
        "_id": {
        "$oid": "65fdca924ab5e7e7a7bc42ca"
        },
        "results": [
        96,
        2,
        23,
        68
        ]
    },... 
]*/
//---------------------------------------------------------
/* Exercici 2 

db.results.find({$or: [{results: 77}, {results: 80}]})

[
    {
      "_id": {
        "$oid": "65fdca924ab5e7e7a7bc42dd"
      },
      "results": [
        80
      ]
    },
    {
      "_id": {
        "$oid": "65fdca924ab5e7e7a7bc42fc"
      },
      "results": [
        70,
        80,
        43,
        89,
        18
      ]
    },... 
]*/
//---------------------------------------------------------
/* Exercici 3 

db.results.find({$and: [{results: 77}, {results: 72}, {results: 80}]})

[
    {
        "_id": {
        "$oid": "65fdca924ab5e7e7a7bc4d8d"
        },
        "results": [
        21,
        80,
        72,
        31,
        77
        ]
    },
    {
        "_id": {
        "$oid": "65fdca964ab5e7e7a7bcf9e4"
        },
        "results": [
        72,
        61,
        80,
        77
        ]
    }
]*/
//---------------------------------------------------------
/* Exercici 4 

db.results.find({results: {$elemMatch: {$gt: 77, $lt: 80}}})

[
    {
      "_id": {
        "$oid": "65fdca924ab5e7e7a7bc42f6"
      },
      "results": [
        49,
        56,
        78,
        59
      ]
    },
    {
      "_id": {
        "$oid": "65fdca924ab5e7e7a7bc430d"
      },
      "results": [
        79,
        65,
        35,
        18
      ]
    },... 
]*/
//---------------------------------------------------------
/* Exercici 5 

db.results.find({results: {$size: 2}})

[
    {
      "_id": {
        "$oid": "65fdca924ab5e7e7a7bc42c7"
      },
      "results": [
        61,
        59
      ]
    },
    {
      "_id": {
        "$oid": "65fdca924ab5e7e7a7bc42c9"
      },
      "results": [
        32,
        89
      ]
    },
    {
      "_id": {
        "$oid": "65fdca924ab5e7e7a7bc42cb"
      },
      "results": [
        10,
        59
      ]
    },... 
]*/
//---------------------------------------------------------
/* Exercici 6 

db.results.find({results: [87, 94, 91]})

[]*/
//---------------------------------------------------------
use("sample_weatherdata")

/* Exercici 7 

db.data.find({"atmosphericPressureChange.quantity24Hours.quality": {$exists: true}}, {_id: 1, "airTemperature.quality": 1, "atmosphericPressureChange.quantity24Hours.quality": 1})

[
    {
      "_id": {
        "$oid": "5553a998e4b02cf7151190bf"
      },
      "airTemperature": {
        "quality": "1"
      },
      "atmosphericPressureChange": {
        "quantity24Hours": {
          "quality": "9"
        }
      }
    },
    {
      "_id": {
        "$oid": "5553a998e4b02cf7151190c2"
      },
      "airTemperature": {
        "quality": "1"
      },
      "atmosphericPressureChange": {
        "quantity24Hours": {
          "quality": "9"
        }
      }
    },... 
]*/
//---------------------------------------------------------
/* Exercici 8 

db.data.find({"atmosphericPressureChange": {$exists: true}, "airTemperature": {$exists: true}}, {_id: 1, "airTemperature.quality": 1, "atmosphericPressureChange.quantity24Hours.quality": 1})

[
    {
      "_id": {
        "$oid": "5553a998e4b02cf7151190bf"
      },
      "airTemperature": {
        "quality": "1"
      },
      "atmosphericPressureChange": {
        "quantity24Hours": {
          "quality": "9"
        }
      }
    },
    {
      "_id": {
        "$oid": "5553a998e4b02cf7151190c2"
      },
      "airTemperature": {
        "quality": "1"
      },
      "atmosphericPressureChange": {
        "quantity24Hours": {
          "quality": "9"
        }
      }
    },... 
]*/
//---------------------------------------------------------
/* Exercici 9 

db.data.find({"airTemperature.value": {$gt: 35, $lt: 40}}, {_id: 1, "airTemperature": 1, "atmosphericPressureChange": 1})

[
    {
      "_id": {
        "$oid": "5553a998e4b02cf715119a22"
      },
      "airTemperature": {
        "value": 35.2,
        "quality": "1"
      },
      "atmosphericPressureChange": {
        "tendency": {
          "code": "6",
          "quality": "1"
        },
        "quantity3Hours": {
          "value": 0.6,
          "quality": "1"
        },
        "quantity24Hours": {
          "value": 99.9,
          "quality": "9"
        }
      }
    },... 
]*/
//---------------------------------------------------------
/* Exercici 10 

db.data.find({"atmosphericPressureChange.quantity24Hours.quality": "9"}, {_id: 1, airTemperature: 1, atmosphericPressureChange: 1})

[
    {
      "_id": {
        "$oid": "5553a998e4b02cf7151190bf"
      },
      "airTemperature": {
        "value": -5.1,
        "quality": "1"
      },
      "atmosphericPressureChange": {
        "tendency": {
          "code": "8",
          "quality": "1"
        },
        "quantity3Hours": {
          "value": 0.5,
          "quality": "1"
        },
        "quantity24Hours": {
          "value": 99.9,
          "quality": "9"
        }
      }
    },.. 
]*/