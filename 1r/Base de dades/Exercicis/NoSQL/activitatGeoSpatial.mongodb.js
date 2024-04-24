use("geo")

/* Índex */

//db.restaurants.createIndex({ location: "2dsphere" });
//db.neighborhoods.createIndex({ geometry: "2dsphere" });

/* Exercici 1 

db.neighborhoods.aggregate([
  {$sort: {_id: 1}},
  {$limit: 1}
])

[
  {
    "_id": {
      "$oid": "55cb9c666c522cafdb053a1a"
    },
    "geometry": {
      "coordinates": [
        [
          [
            -73.94193078816193,
            40.70072523469547
          ],
          [
            -73.9443878859649,
            40.70042452378256
          ], ... 
        ]
      ],
      "type": "Polygon"
    },
    "name": "Bedford"
  }
]

db.restaurants.aggregate([
  {$sort: {_id: 1}},
  {$limit: 1}
])

[
  {
    "_id": {
      "$oid": "55cba2476c522cafdb053add"
    },
    "location": {
      "coordinates": [
        -73.856077,
        40.848447
      ],
      "type": "Point"
    },
    "name": "Morris Park Bake Shop"
  }
]

Les coordenades que hi ha en la base de dades estan "invertides" respecte les que utilitza el maps, invertides en el sentit de que la segona coordenada és la seva primera i la nostra segona és la seva primera.
Això és degut a que les nostres coordenades estan ordenades en "longitud - latitud" mentre que en el Maps estan posades "Nord - Oest".
*/

/* Exercici 2 

db.restaurants.find({
  location: {$near: {$geometry: {type: "Point", coordinates: [-73.96 , 40.78]}}}
}).limit(1)

[
  {
    "_id": {
      "$oid": "55cba2476c522cafdb053bff"
    },
    "location": {
      "coordinates": [
        -73.9598324,
        40.7798381
      ],
      "type": "Point"
    },
    "name": "The New Amity Restaurant"
  }
]*/

/* Exercici 3 

db.restaurants.find({
  location: {$nearSphere: {$geometry: {type: "Point", coordinates: [-74, 40.7]}, $maxDistance: 500}}
})

[
  {
    "_id": {
      "$oid": "55cba2476c522cafdb05688a"
    },
    "location": {
      "coordinates": [
        -73.9970183,
        40.7017076
      ],
      "type": "Point"
    },
    "name": "Brooklyn Bridge Garden Bar"
  },
  {
    "_id": {
      "$oid": "55cba2476c522cafdb0590fc"
    },
    "location": {
      "coordinates": [
        -73.9970183,
        40.7017076
      ],
      "type": "Point"
    },
    "name": "Lizzmonade"
  },
  {
    "_id": {
      "$oid": "55cba2476c522cafdb058253"
    },
    "location": {
      "coordinates": [
        -73.99973,
        40.696913
      ],
      "type": "Point"
    },
    "name": "Ample Hills Creamery"
  }
]*/

/* Exercici 4 

db.restaurants.find({
  location: {$near: {$geometry: {type: "Point", coordinates: [-73.88, 40.7]}, $minDistance: 194}}
}).limit(1)

[
  {
    "_id": {
      "$oid": "55cba2476c522cafdb053c97"
    },
    "location": {
      "coordinates": [
        -73.8803351,
        40.7017284
      ],
      "type": "Point"
    },
    "name": "Mcdonald'S"
  }
]*/

/* Exercici 5 

db.neighborhoods.find({
  geometry: {$geoIntersects: {$geometry: {type: "Point", coordinates: [-74, 40.6]}}}
}, {_id: 0, geometry: 0})

[
  {
    "name": "Bath Beach"
  }
]*/

/* Exercici 6 

var barrierasmus = db.neighborhoods.findOne({name:"Erasmus"})

db.restaurants.find({
  location: {$geoWithin: {$geometry: {type: "Polygon", coordinates: barrierasmus.geometry.coordinates}}}
}).sort({name: -1}).limit(1)

[
  {
    "_id": {
      "$oid": "55cba2486c522cafdb059720"
    },
    "location": {
      "coordinates": [
        -73.955874,
        40.6410423
      ],
      "type": "Point"
    },
    "name": "Yummy Chinese Restaurant"
  }
]*/

/* Exercici 7 

var barri = db.neighborhoods.find({
  geometry: {$geoIntersects: {$geometry: {type: "Point", coordinates: [-73.93, 40.82]}}}
}).toArray()[0]

db.restaurants.find({
  location: {$geoWithin: {$geometry: {type: "Polygon", coordinates: barri.geometry.coordinates}}}
}).count()

125*/

/* Exercici 8 

La diferència entre $near i $nearSphere es que la primera fa una busca en direcció "recta", sense tenir en compte la corbatura de la terra mentre que la segona fa les busques tenint-ho en compte i fent els càlculs amb geometria esfèrica.

*/

/* Exercici 9

La diferència entre $geoWithin i $geoIntersect és que en la primera es quan estàs fent una busca per tot allò que hi hagi a dins de l'àrea que especifiques, mentre que en la segona estàs fent la busca per tot allò que entri en contacte amb l'area, no necessàriament estant només a dins de la mateixa.

*/

