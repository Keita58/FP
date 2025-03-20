import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:harry_potter/providers/hogwarts_data.dart';
import 'package:harry_potter/providers/preferences.dart';
import 'package:harry_potter/screens/character_list.dart';
import 'package:provider/provider.dart';


void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (BuildContext context) {return HogwartsData();}),
        ChangeNotifierProvider(create: (BuildContext context) {return Preferences();})
        ],
        child: MaterialApp(
          title: 'Flutter Demo ',
          theme: ThemeData(
              colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
              fontFamily: GoogleFonts.montserrat().fontFamily,
              useMaterial3: true,
              appBarTheme: const AppBarTheme(
                foregroundColor: Colors.white,
              )
          ),
          home: CharacterList(),
        ),
      );
  }
}
