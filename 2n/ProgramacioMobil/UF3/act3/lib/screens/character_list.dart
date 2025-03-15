import 'package:flutter/material.dart';
import 'package:harry_potter/provider/howarts_data.dart';
import 'package:harry_potter/screens/character_detail.dart';
import 'package:provider/provider.dart';
import '../models/character.dart';

class CharacterList extends StatelessWidget {
  CharacterList({super.key});

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.purple,
        title: const Text("Welcome to Hogwarts", style: TextStyle(color:Colors.white),),
      ),
      body: Consumer<Data>(
        builder: (BuildContext context, Data data, child) {
          return ListView(
            children: [
              for (Character character in data.characters)
                Padding(
                  padding: const EdgeInsets.all(3.0),
                  child: ListTile(
                    onTap: () {
                      Navigator.push(
                          context,
                          MaterialPageRoute(
                              builder: (context) =>  CharacterDetail(idCharacter: character.id,)
                          )
                      );
                    },
                    leading: Hero(tag: character.name, // Posem el mateix "codi" als dos costats
                        child: Image.network(character.url)),
                    title: Column(
                      children: [
                        Text(character.name),
                        Text(character.reviews.toString())
                      ],
                    ),
                  ),
                )
            ],
          );
        }
      )
    );
  }
}
