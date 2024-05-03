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

app.get('/songs', async(req, res) => {
    let client2;
    try {
        client2 = await client.connect(url);
        const db = client2.db(dbName);
        const songs = await db.collection("songs").find().toArray();
        res.json(songs);
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

app.get('/song', async(req, res) => {
    let client2;
    let reproduccions = Number(req.query.reproduccions); //El nom en la busca ha d'estar EXACTAMENT igual a aquest, en aquest cas "reproduccions", tot en minúscula
    let recaptacio = Number(req.query.recaptacio);
    try {
        client2 = await client.connect(url);
        const db = client2.db(dbName);
        const songs = await db.collection("songs").find({'Reproduccions': {$gte: reproduccions}, 'Recaptacio': {$gte: recaptacio}}).toArray();
        res.json(songs);
    }
    catch (err) {
        console.log("Error amb l'operació CRUD de l'API", err);
    }
    finally {
        if(client2) {
            client2.close();
        }
    }
});

app.post('/songs/insert', async(req, res) => {
    let client2;
    let nom = req.body.nom;
    let reproduccions = Number(req.body.reproduccions);
    let recaptacio = Number(req.body.recaptacio);
    try {
        client2 = await client.connect(url);
        const db = client2.db(dbName);
        let id = (await db.collection('songs').find().toArray()).length + 1;
        const song = await db.collection('songs').insertOne({'id': id, 'Nom': nom, 'Reproduccions': reproduccions, 'Recaptacio': recaptacio});
        res.json("Afegida!");
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

//-------------------------------------------------------------(Exercicis amb usuaris_app)

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
        let id = (await db.collection('usuaris_app').find().toArray()).length + 1;
        const user = await db.collection('usuaris_app').insertOne({'id': id, 'nom': nom, 'password': password});
        res.json("Afegida!");
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

app.get('/users/update', async(req, res) => {
    let client2;
    let id = req.query.id;
    let nom = req.query.nom;
    let password = req.query.password;
    try {
        client2 = await client.connect(url);
        const db = client2.db(dbName);
        if(id !== undefined) {
            if (nom === undefined && password !== undefined) {
                let user = await db.collection('usuaris_app').updateOne({'id': id}, {$set: {'password': password}});
                console.log("Password canviada");
            } else if (nom !== undefined && password === undefined) {
                let user = await db.collection('usuaris_app').updateOne({'id': id}, {$set: {'nom': nom}});
                console.log("Correu canviat");
            } else {
                let user = await db.collection('usuaris_app').updateOne({'id': id}, {$set: {'nom': nom, 'password': password}});
                console.log("Correu i password canviats");
            }
            res.json("Actualitzada!");
        }
        else
            res.json("Et falta l'id!");
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
