use("books")

/* Exercici 1 

db.books.aggregate([
  {$group: {_id: "$title", count: {$sum: 1}}},
  {$match: {count: {$gt: 1}}}
])

[]*/
//-----------------------------------------------
/* Exercici 2 

db.books.aggregate([
  {$match: {title: "ZOMBIES WITHOUT A LEADER"}},
  {$project: {title: "$title", total: {$sum: "$sales.sale_price"}}}
])

[
  {
    "_id": "018769811-2",
    "title": "ZOMBIES WITHOUT A LEADER",
    "total": 107.88000000000001
  }
]*/
//-----------------------------------------------
/* Exercici 3 

db.books.aggregate([
  {$unwind: "$authors"},
  {$group: {_id: "$authors", count: {$sum: 1}}},
  {$sort: {count: -1, _id: 1}},
  {$limit: 1}
])

[
  {
    "_id": "Alissa Ranner",
    "count": 4
  }
]*/
//-----------------------------------------------
/* Exercici 4

var count = db.books.aggregate([
  {$unwind: "$authors"},
  {$group: {_id: "$authors", count: {$sum: 1}}},
  {$sort: {count: -1}},
  {$project: {"count": 1, "_id": 0}}
]).toArray()[0]

console.log(count)

db.books.aggregate([
  {$unwind: "$authors"},
  {$group: {_id: "$authors", count: {$sum: 1}}},
  {$match: {"count": count.count}},
  {$sort: {count: -1, _id: 1}},
])

[
  {
    "_id": "Alissa Ranner",
    "count": 4
  },
  {
    "_id": "Erny Lucey",
    "count": 4
  },
  {
    "_id": "Flossy Jacob",
    "count": 4
  },
  {
    "_id": "Jimmie Tomsett",
    "count": 4
  },
  {
    "_id": "Laurianne Laundon",
    "count": 4
  },
  {
    "_id": "Salem Domney",
    "count": 4
  },
  {
    "_id": "Valentijn Nabarro",
    "count": 4
  }
]*/
//-----------------------------------------------
/* Exercici 5 

db.books.aggregate([
  {$unwind: "$scores"},
  {$group: {_id: {ISBN: "$_id", title: "$title"}, scores: {$push: "$scores"}, median: {$avg: "$scores"}}},
  {$sort: {median: -1}},
  {$limit: 1}
])

[
  {
    "_id": {
      "ISBN": "720558475-2",
      "title": "ARMIES OF A SHADOW"
    },
    "scores": [
      10
    ],
    "median": 10
  }
]*/
//-----------------------------------------------
/* Exercici 6 

db.books.aggregate([
  {$unwind: "$scores"},
  {$group: {_id: {ISBN: "$_id", title: "$title"}, scores: {$push: "$scores"}, median: {$avg: "$scores"}}},
  {$match: {"scores.1": {$exists: true}}},
  {$sort: {median: -1}},
  {$limit: 1}
])

[
  {
    "_id": {
      "ISBN": "650753755-1",
      "title": "UNION WITHOUT FAITH"
    },
    "scores": [
      9.8,
      9.3
    ],
    "median": 9.55
  }
]*/
//-----------------------------------------------
/* Exercici 7 

db.books.aggregate([
  {$unwind: "$scores"},
  {$match: {genres: "Science fiction"}},
  {$group: {_id: {ISBN: "$_id", title: "$title"}, median: {$avg: "$scores"}}},
  {$sort: {median: -1}},
  {$limit: 1}
])

[
  {
    "_id": {
      "ISBN": "115111113-9",
      "title": "PIRATE OF THE UNDERGROUND"
    },
    "median": 8
  }
]*/
//-----------------------------------------------
/* Exercici 8 

db.books.aggregate([
  {$unwind: "$sales"},
  {$group: {_id: {year: {$year: "$sales.sale_date"}, month: {$month: "$sales.sale_date"}}, count: {$sum: 1}}},
  {$sort: {"_id.year": 1, "_id.month": 1}}
])

[
  {
    "_id": {
      "year": 2017,
      "month": 5
    },
    "count": 71
  },
  {
    "_id": {
      "year": 2017,
      "month": 6
    },
    "count": 147
  },
  {
    "_id": {
      "year": 2017,
      "month": 7
    },
    "count": 166
  },
  {
    "_id": {
      "year": 2017,
      "month": 8
    },
    "count": 149
  },
  {
    "_id": {
      "year": 2017,
      "month": 9
    },
    "count": 147
  },
  {
    "_id": {
      "year": 2017,
      "month": 10
    },
    "count": 133
  },
  {
    "_id": {
      "year": 2017,
      "month": 11
    },
    "count": 171
  },
  {
    "_id": {
      "year": 2017,
      "month": 12
    },
    "count": 138
  },
  {
    "_id": {
      "year": 2018,
      "month": 1
    },
    "count": 153
  },
  {
    "_id": {
      "year": 2018,
      "month": 2
    },
    "count": 127
  },
  {
    "_id": {
      "year": 2018,
      "month": 3
    },
    "count": 158
  },
  {
    "_id": {
      "year": 2018,
      "month": 4
    },
    "count": 143
  },
  {
    "_id": {
      "year": 2018,
      "month": 5
    },
    "count": 87
  }
]*/
//-----------------------------------------------
/* Exercici 9 

var pre_data = new Date("2017-09-01T00:00:00.0000")
var post_data = new Date("2018-05-31T23:59:59.9999")

db.books.aggregate([
  {$unwind: "$sales"},
  {$match: {"sales.sale_date": {$gte: pre_data, $lte: post_data}}},
  {$group: {_id: {year: {$year: "$sales.sale_date"}, month: {$month: "$sales.sale_date"}}, sum: {$sum: "$sales.sale_price"}}},
  {$sort: {"_id.year": 1, "_id.month": 1}}
])

[
  {
    "_id": {
      "year": 2017,
      "month": 9
    },
    "sum": 3596.42
  },
  {
    "_id": {
      "year": 2017,
      "month": 10
    },
    "sum": 3016.23
  },
  {
    "_id": {
      "year": 2017,
      "month": 11
    },
    "sum": 4033.06
  },
  {
    "_id": {
      "year": 2017,
      "month": 12
    },
    "sum": 3178.68
  },
  {
    "_id": {
      "year": 2018,
      "month": 1
    },
    "sum": 3233
  },
  {
    "_id": {
      "year": 2018,
      "month": 2
    },
    "sum": 2971.14
  },
  {
    "_id": {
      "year": 2018,
      "month": 3
    },
    "sum": 3733.36
  },
  {
    "_id": {
      "year": 2018,
      "month": 4
    },
    "sum": 3434.2
  },
  {
    "_id": {
      "year": 2018,
      "month": 5
    },
    "sum": 2058.73
  }
]*/
//-----------------------------------------------
/* Exercici 10 

db.books.aggregate([
  {$unwind: "$genres"},
  {$group: {_id: "$genres", count: {$sum: 1}}},
  {$sort: {count: -1, _id: 1}},
  {$limit: 3}
])

[
  {
    "_id": "Fantasy",
    "count": 50
  },
  {
    "_id": "Satire",
    "count": 37
  },
  {
    "_id": "Mystery",
    "count": 36
  }
]*/
//-----------------------------------------------
/* Exercici 11 

db.books.aggregate([
  {$unwind: "$genres"},
  {$group: {_id: "$genres", count: {$sum: 1}}},
  {$sort: {count: -1, _id: 1}},
  {$match: {count: {$gt: 25}}}
])

[
  {
    "_id": "Fantasy",
    "count": 50
  },
  {
    "_id": "Satire",
    "count": 37
  },
  {
    "_id": "Mystery",
    "count": 36
  },
  {
    "_id": "Poetry",
    "count": 36
  },
  {
    "_id": "Historical Fiction",
    "count": 34
  },
  {
    "_id": "Anthology",
    "count": 32
  },
  {
    "_id": "Diaries and Journals",
    "count": 31
  },
  {
    "_id": "Drama",
    "count": 29
  },
  {
    "_id": "Health",
    "count": 29
  },
  {
    "_id": "Religious",
    "count": 29
  },
  {
    "_id": "Encyclopedia",
    "count": 28
  },
  {
    "_id": "Art",
    "count": 27
  },
  {
    "_id": "Children's",
    "count": 27
  },
  {
    "_id": "Philosophy",
    "count": 27
  },
  {
    "_id": "Adventure",
    "count": 26
  }
]*/
//-----------------------------------------------
/* Exercici 12 

db.books.aggregate([
  {$group: {_id: null, perdues: {$sum: {$subtract: [{$multiply: [{$arrayElemAt: ["$sales.sale_price", 0]}, {$sum: [1, {$multiply: [{$arrayElemAt: ["$sales.sale_price", 0]}, 0.01]}]}]}, {$arrayElemAt: ["$sales.sale_price", 0]}]}}}}
])

[
  {
    "_id": null,
    "perdues": 2782.518678
  }
]*/
//-----------------------------------------------
/* Exercici 13 

db.books.aggregate([
  {$unwind: "$sales"},
  {$group: {_id: "$title", sum: {$sum: "$sales.sale_price"}}},
  {$sort: {sum: -1, title: 1}},
  {$limit: 1}
])

[
  {
    "_id": "DEAD IN THE HUNTER",
    "sum": 297.27
  }
]*/
//-----------------------------------------------
/* Exercici 14 *

var data_inici = new Date("2018-04-01T00:00:00.0000");
var data_fi = new Date("2018-05-31T23:59:59.9999");

db.books.aggregate([
  {$unwind: "$sales"},
  {$match: {"sales.sale_date": {$gte: data_inici, $lte: data_fi}}},
  {$group: {_id: {id: "$_id", title: "$title"}, count: {$sum: "$sales.sale_price"}}},
  {$match: {"count": {$gt: 50}}},
  {$sort: {count: -1}}
])

[
  {
    "_id": {
      "id": "166481901-0",
      "title": "BUFFOON HAS A SECRET LIFE"
    },
    "count": 100.44999999999999
  },
  {
    "_id": {
      "id": "571295141-X",
      "title": "WOLVES OF RAINBOWS"
    },
    "count": 95.82
  },
  {
    "_id": {
      "id": "368329062-5",
      "title": "PERFUME OF TOMORROW"
    },
    "count": 79.13
  },... 
]*/
//-----------------------------------------------
/* Exercici 15 

db.books.aggregate([
  {$unwind: "$authors"},
  {$unwind: "$sales"},
  {$group: {_id: "$authors", sum: {$sum: "$sales.sale_price"}}},
  {$sort: {sum: -1, _id: 1}},
  {$limit: 1}
])

[
  {
    "_id": "Malachi Pawden",
    "sum": 423.01
  }
]*/
//-----------------------------------------------
/* Exercici 16 

db.books.aggregate([
  {$unwind: "$sales"},
  {$match: {"scores": {$exists: false}}},
  {$group: {_id: null, count: {$sum: 1}, sum: {$sum: "$sales.sale_price"}}}
])

[
  {
    "_id": null,
    "count": 40,
    "sum": 838.68
  }
]*/
//-----------------------------------------------
/* Exercici 17 

db.books.aggregate([
  {$unwind: "$authors"},
  {$unwind: "$genres"},
  {$match: {"authors": "Miranda Belbin"}},
  {$group: {_id: "$authors", genres: {$push: "$genres"}}}
])

[
  {
    "_id": "Miranda Belbin",
    "genres": [
      "Travel",
      "Drama",
      "Historical Fiction"
    ]
  }
]*/
//-----------------------------------------------
/* Exercici 18 

var author = db.books.aggregate([
  {$unwind: "$authors"},
  {$match: {"_id": "170494833-9"}},
  {$project: {"authors": 1, "_id": 0}}
]).toArray()[0]

console.log(author)

db.books.aggregate([
  {$unwind: "$authors"},
  {$unwind: "$genres"},
  {$match: {"authors": author.authors}},
  {$group: {_id: author.authors, genres: {$push: "$genres"}}}
])

[
  {
    "_id": "Shanna Scrase",
    "genres": [
      "Historical Fiction",
      "Cookbook"
    ]
  }
]*/
//-----------------------------------------------
/* Exercici 19 

db.books.aggregate([
  {$unwind: "$authors"},
  {$group: {_id: "$authors", books: {$push: "$_id"}}},
  {$sort: {_id: 1}}
])

[
  {
    "_id": "Aaren Mougin",
    "books": [
      "893373418-X"
    ]
  },
  {
    "_id": "Abdul Limmer",
    "books": [
      "712651894-3"
    ]
  },
  {
    "_id": "Abelard Lorrimer",
    "books": [
      "515927208-9"
    ]
  },... 
]*/
//-----------------------------------------------
/* Exercici 20

db.books.aggregate([
  {$unwind: "$sales"},
  {$project: {price: 1, sales: 1, resta: {$subtract: ["$price", {$divide: [{$multiply: ["$price", "$sales.sale_discount"]}, 100]}]}}},
  {$project: {price: 1, sales: 1, resta: 1, num: {$abs: {$subtract: ["$sales.sale_price", "$resta"]}}}},
  {$match: {num: {$gte: 0.01}}}
]).toArray()

[]*/