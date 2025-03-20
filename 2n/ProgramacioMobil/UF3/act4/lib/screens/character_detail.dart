

import 'package:flutter/material.dart';
import 'package:harry_potter/providers/hogwarts_data.dart';
import 'package:provider/provider.dart';

import '../widgets/rating.dart';

class CharacterDetail extends StatefulWidget {
  const CharacterDetail({super.key, required this.id});

  //final Character character;
  final int id;

  @override
  State<CharacterDetail> createState() => _CharacterDetailState();
}

class _CharacterDetailState extends State<CharacterDetail> {

  int lastRating = 0;

  @override
  Widget build(BuildContext context) {

    var imageWidth = MediaQuery.of(context).size.width * 0.7;
    var imageHeight = MediaQuery.of(context).size.height * 0.7;

    return Consumer<HogwartsData>(
      builder: (context, data, child) {
        // Agafem el Character a partir del Id
        var character = data.getCharacterFromId(widget.id);

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
                  tag: character.name,  // Ponemos un objetoque se igual en una pantalla i otra
                  child: Image.network(character.url,
                                 height: imageHeight,
                  ),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: [
                    Rating(value: character.stars,),  // SENSE FUNCIó, NO CLICALBE
                    Text ("${character.reviews}  reviews"),
                  ],
                ),
                Text(character.name,
                  style: const TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
                ),
                Rating(value: lastRating.toDouble(), // Tenim que passar-lo a Dobule ja que el parametre es double.
                    onStartClicked: (value ) {
                          print(value);
                          lastRating=value;
                          // widget.character.addRating(value);
                          data.addRating(character, value);
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
                        Text("${character.strength}")
                      ],
                    ),
                    Column(
                      children: [
                        const Icon(Icons.auto_fix_normal),
                        const Text("Màgia"),
                        Text("${character.magic}")
                      ],
                    ),
                    Column(
                      children: [
                        const Icon(Icons.speed),
                        const Text("Velocitat"),
                        Text(character.speed.toString()) // Tambés es pot passar a String

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


