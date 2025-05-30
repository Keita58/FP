import 'package:flutter/material.dart';
import '../models/character.dart';
import 'package:firebase_database/firebase_database.dart';

class HogwartsData extends ChangeNotifier{

  DatabaseReference ref = FirebaseDatabase.instance.ref("");

  final List<Character> characters = [];

  HogwartsData() {
    //creaInfo();
    llegeixMac();
  }

  Future<void> creaInfo() async {
    ref = FirebaseDatabase.instance.ref("mac/Harry Potter");
    await ref.set({
      "id": 1,
      "name": "Harry Potter",
      "url": "https://static.wikia.nocookie.net/esharrypotter/images/8/8d/PromoHP7_Harry_Potter.jpg/revision/latest/scale-to-width-down/1200?cb=20160903184919",
      "strength": 6,
      "speed": 8,
      "magic": 9,
      "stars": 0,
      "totalStars": 0,
      "reviews": 0
    });

    ref = FirebaseDatabase.instance.ref("mac/Hermione Granger");
    await ref.set({
      "id": 2,
      "name": "Hermione Granger",
      "url": "https://static.wikia.nocookie.net/warnerbros/images/3/3e/Hermione.jpg/revision/latest/scale-to-width-down/399?cb=20120729103114&path-prefix=es",
      "strength": 8,
      "speed": 10,
      "magic": 10,
      "stars": 0,
      "totalStars": 0,
      "reviews": 0
    });

    ref = FirebaseDatabase.instance.ref("mac/Ron Weasly");
    await ref.set({
      "id": 3,
      "name": "Ron Weasly",
      "url": "https://static.wikia.nocookie.net/esharrypotter/images/6/69/P7_promo_Ron_Weasley.jpg/revision/latest?cb=20150523213430",
      "strength": 4,
      "speed": 6,
      "magic": 7,
      "stars": 0,
      "totalStars": 0,
      "reviews": 0
    });
    notifyListeners();
  }

  void addRating (Character character, int value){
    character.addRating(value);
    notifyListeners();
  }

  Character getCharacterFromId (int id) {
    return characters.firstWhere( (character) { return character.id==id; });
  }

  Future<void> llegeixMac() async {
    characters.clear();
    ref = FirebaseDatabase.instance.ref("");
    final val = await ref.child('mac/').get();
    for(var c in val.children) {
      final Map<dynamic, dynamic> cData = c.value as Map<dynamic, dynamic>;
      characters.add(Character(id: cData['id'], name: cData['name'], url: cData['url'], strength: cData['strength'], speed: cData['speed'], magic: cData['magic']));
    }
    notifyListeners();
  }

  void addMac(Character mac) {
    creaMac(mac);
    characters.add(
      Character(id: mac.id, name: mac.name, url: mac.url, strength: mac.strength, speed: mac.speed, magic: mac.magic)
    );
    notifyListeners();
  }

  Future<void> creaMac(Character mac) async {
    ref = FirebaseDatabase.instance.ref("mac/${mac.name}");
    await ref.set({
      "id": mac.id,
      "name": mac.name,
      "url": mac.url,
      "strength": mac.strength,
      "speed": mac.speed,
      "magic": mac.magic,
      "stars": 0,
      "totalStars": 0,
      "reviews": 0
    });
  }
}