import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class Preferences extends ChangeNotifier {

  SharedPreferences? preferences;
  bool? showSubtitles = false;

  Preferences() {
    init();
  }

  init() async {
    preferences = await SharedPreferences.getInstance();
    showSubtitles = preferences!.getBool('showSubtitles') ?? false;
    notifyListeners();
  }

  Future<void> setShowSubtitle (bool value) async {
    showSubtitles = value;
    preferences ??= await SharedPreferences.getInstance();
    notifyListeners();
    await preferences!.setBool('showSubtitles', value);
  }
}