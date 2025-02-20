
import 'package:flutter/material.dart';

class Afegir extends StatelessWidget {
  const Afegir({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        elevation: 5.0,
        shadowColor: Colors.black,
        centerTitle: true,
        foregroundColor: Colors.white,
        // TRY THIS: Try changing the color here to a specific color (to
        // Colors.amber, perhaps?) and trigger a hot reload to see the AppBar
        // change color while the other colors stay the same.
        backgroundColor: Theme.of(context).colorScheme.primary,
        title: Text('Afegir activitat'),
      ),
      body: Column(
        children: [

        ],
      ),
    );
  }
}