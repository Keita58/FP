import 'package:flutter/material.dart';
import 'package:harry_potter/providers/hogwarts_data.dart';
import 'package:harry_potter/providers/preferences.dart';
import 'package:harry_potter/screens/character_detail.dart';
import 'package:provider/provider.dart';
import '../models/character.dart';

class CharacterList extends StatefulWidget {
  const CharacterList({super.key});

  @override
  State<CharacterList> createState() => _CharacterListState();
}

class _CharacterListState extends State<CharacterList> {
  @override
  Widget build(BuildContext context) {
    return Consumer<Preferences>(
      builder: (context, info, child) {
        return Scaffold(
          appBar: AppBar(
            backgroundColor: Colors.purple,
            title: const Text("Welcome to Hogwarts", style: TextStyle(color:Colors.white),),
            actions: [Switch(value: info.showSubtitles!, onChanged: (value) {
                info.setShowSubtitle(value);
            })],
          ),
          body: Consumer<HogwartsData>(
            builder: (BuildContext context, HogwartsData data, child ) {
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
                                  builder: (context) =>  CharacterDetail(id: character.id,)
                              )
                          );
                        },
                        leading: Hero(tag: character.name, // Posem el mateix "codi" als dos costats
                            child: Image.network(character.url)),
                        title: Text(character.name),
                        subtitle: info.showSubtitles == true ? Text("${character.reviews.toString()} reviews ") : null,
                      ),
                    )
                ],
              );
            }
          ),
          floatingActionButton: FloatingActionButton(
            onPressed: () async {
              final mac = await Navigator.push(
                  context, MaterialPageRoute(builder: (context) => Form()));
              HogwartsData..add(carta);
              setState(() {});
            },
            child: const Icon(Icons.add),
          ),
        );
      });
  }
}
