import 'package:flutter/material.dart';
import '../models/character.dart';

class HogwartsData extends ChangeNotifier{

  final List<Character> characters = [
    Character(
      id: 1,
      name: "Harry Potter",
      url:
      "https://static.wikia.nocookie.net/esharrypotter/images/8/8d/PromoHP7_Harry_Potter.jpg/revision/latest/scale-to-width-down/1200?cb=20160903184919",
      strength: 6,
      speed: 8,
      magic: 9,
      // stars: 5,
      // reviews:30,
    ),
    Character(
      id:2,
      name: "Hermione Granger",
      url:
      "https://static.wikia.nocookie.net/warnerbros/images/3/3e/Hermione.jpg/revision/latest/scale-to-width-down/399?cb=20120729103114&path-prefix=es",
      strength: 8,
      speed: 10,
      magic: 10,
      // stars: 3,
      // reviews:50,
    ),
    Character(
      id:3,
      name: "Ron Weasly",
      url:
      "https://static.wikia.nocookie.net/esharrypotter/images/6/69/P7_promo_Ron_Weasley.jpg/revision/latest?cb=20150523213430",
      strength: 4,
      speed: 6,
      magic: 7,
      // stars: 5,
      // reviews:30,
    ),
  ];

  void addRating (Character character, int value){
    character.addRating(value);
    notifyListeners();
  }

  Character getCharacterFromId (int id) {
    return characters.firstWhere( (character ) {return character.id==id; });
  }

  void addMac(Character mac) {
    characters.add(mac);
    notifyListeners();
  }
}