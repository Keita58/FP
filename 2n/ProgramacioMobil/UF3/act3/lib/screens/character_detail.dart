

import 'package:flutter/material.dart';
import 'package:harry_potter/provider/howarts_data.dart';
import 'package:provider/provider.dart';

import '../models/character.dart';
import '../widgets/rating.dart';

class CharacterDetail extends StatefulWidget {
  const CharacterDetail({super.key, required this.idCharacter});

  final int idCharacter;


  @override
  State<CharacterDetail> createState() => _CharacterDetailState();
}

class _CharacterDetailState extends State<CharacterDetail> {

  int lastRating = 0;

  @override
  Widget build(BuildContext context) {

    var imageWidth = MediaQuery.of(context).size.width * 0.7;
    var imageHeight = MediaQuery.of(context).size.height * 0.7;

    return Consumer<Data>(builder: (BuildContext context, Data data, child) {
      return Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.purple.shade200,
          title: const Text("Harry Potter App"),
          centerTitle: true,
        ),
        body: SingleChildScrollView(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              //  Image.network("https://static.wikia.nocookie.net/warnerbros/images/a/ae/Harrypotter.jpg/revision/latest?cb=20120402154317&path-prefix=es"),

              Hero(
                tag: data.getCharacterFromId(widget.idCharacter).name,  // Ponemos un objetoque se igual en una pantalla i otra
                child: Image.network(data.getCharacterFromId(widget.idCharacter).url,
                  height: imageHeight,
                ),
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  Rating(value: data.getCharacterFromId(widget.idCharacter).stars,),  // SENSE FUNCIó, NO CLICALBE
                  Text ("${data.getCharacterFromId(widget.idCharacter).reviews}  reviews"),
                ],
              ),
              Text(data.getCharacterFromId(widget.idCharacter).name,
                style: const TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
              ),
              Rating(value: lastRating.toDouble(), // Tenim que passar-lo a Dobule ja que el parametre es double.
                onStartClicked: (value ) {
                  print(value);
                  lastRating=value;
                  data.addRating(data.getCharacterFromId(widget.idCharacter), value);
                  setState(() {});
                },
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround ,
                children: [
                  Column(
                    children: [
                      const Icon(Icons.fitness_center),
                      const Text("Força"),
                      Text("${data.getCharacterFromId(widget.idCharacter).strength}")
                    ],
                  ),
                  Column(
                    children: [
                      const Icon(Icons.auto_fix_normal),
                      const Text("Màgia"),
                      Text("${data.getCharacterFromId(widget.idCharacter).magic}")
                    ],
                  ),
                  Column(
                    children: [
                      const Icon(Icons.speed),
                      const Text("Velocitat"),
                      Text(data.getCharacterFromId(widget.idCharacter).speed.toString()) // Tambés es pot passar a String
                    ],
                  ),
                ],
              ),
            ],
          ),
        ),
      );
    }
    );
  }
}


