const express = require('express');
const cors = require("cors");
const bodyParser = require("body-parser");
const client = require("mongodb").MongoClient;

const app = express();
app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
  extended: true
}))

//Creem el port
app.listen(3000, function () {
  console.log("Funciona! :D (Port 3000)");
})
module.exports = app;

const url = "mongodb+srv://keita58:hFqS7KeKEkz9u20r@basededades.9tcii4i.mongodb.net/";
const dbName = "albums";
app.get('/', function(req, res) {
  return res.send({error: false, message: 'holiis'});
});

app.get('/users', async(req, res) => {
  let client2;
  try{
    client2 = await client.connect(url);
    const db = client2.db(dbName);
    const users = await db.collection("usuaris_app").find().toArray();
    res.json(users);
  }
  catch(err){
    console.log("Error amn l'operació CRUD de l'API", err);
  }
  finally {
    if(client2) {
      client2.close();
    }
  }
});

app.get('/user', async(req, res) => {
  let client2;
  let nom = String(req.query.nom);
  let password = String(req.query.password);
  try {
    client2 = await client.connect(url);
    const db = client2.db(dbName);
    const users = await db.collection("usuaris_app").find({'nom': nom, 'password': password}).toArray();
    res.json(users.length);
  }
  catch (err) {
    console.log("Error amn l'operació CRUD de l'API", err);
  }
  finally {
    if(client2) {
      client2.close();
    }
  }
});

app.post('/users/insert', async(req, res) => {
  let client2;
  let nom = req.body.nom;
  let password = String(req.body.password);
  try {
    client2 = await client.connect(url);
    const db = client2.db(dbName);
    console.log(await db.collection('usuaris_app').find({'nom': nom}));
    if(await db.collection('usuaris_app').find({'nom': nom})) {
      res.json("Correu existent en la BD");
    }
    else {
      let id = (await db.collection('usuaris_app').find().toArray()).length + 1;
      const user = await db.collection('usuaris_app').insertOne({'id': id, 'nom': nom, 'password': password});
      res.json("Afegida!");
    }
  }
  catch (err) {
    console.log("Error amn l'operació CRUD de l'API", err);
  }
  finally {
    if(client2) {
      client2.close();
    }
  }
});
