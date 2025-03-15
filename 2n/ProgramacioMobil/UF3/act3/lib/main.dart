import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:harry_potter/provider/howarts_data.dart';
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
    return ChangeNotifierProvider (
      create: (BuildContext context) {
        return Data();
      },
      child: MaterialApp(
        title: 'Harry Potter',
        theme: ThemeData(
            colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
            fontFamily: GoogleFonts.montserrat().fontFamily,
            useMaterial3: true,
            appBarTheme: const AppBarTheme(
              foregroundColor: Colors.white,
            )
        ),
        home:  CharacterList(),
      )
    );
  }
}
