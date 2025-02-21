
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'dart:developer';

import '../model/cartes.dart';

class Afegir extends StatefulWidget {
  const Afegir({super.key});

  @override
  State<Afegir> createState() => _AfegirState();
}

class _AfegirState extends State<Afegir> {
  final controladorTitol = TextEditingController();
  final controladorQuantitat = TextEditingController();

  @override
  void dispose() {
    // Clean up the controller when the widget is disposed.
    controladorTitol.dispose();
    controladorQuantitat.dispose();
    super.dispose();
  }

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
      body: Center(
        child: SizedBox(
          width: 350,
          child: Center(
            child: Column(
              children: [
                Padding(
                  padding: const EdgeInsets.fromLTRB(8, 30, 8, 8),
                  child: Text(
                    "Afegeix la nova activitat",
                    style: Theme.of(context).textTheme.titleMedium,
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: TextField(
                    decoration: InputDecoration(border: OutlineInputBorder(), labelText: 'Activitat realitzada'),
                    controller: controladorTitol,
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: TextField(
                    decoration: InputDecoration(border: OutlineInputBorder(), labelText: 'Quantitat'),
                    controller: controladorQuantitat,
                    keyboardType: TextInputType.numberWithOptions(),
                    inputFormatters: <TextInputFormatter>[
                      FilteringTextInputFormatter.digitsOnly
                    ],
                  ),
                )
              ],
            ),
          )
        )
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          List<String> mesos = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
          DateTime data = DateTime.now();
          String dataText = "${data.day} ${mesos[data.month]} ${data.year}, ${data.hour}:${data.minute}";
          Cartes aux = Cartes(titol: controladorTitol.text, data: dataText, km: int.parse(controladorQuantitat.text));
          log("prova: $aux");
          Navigator.pop(
              context, aux);
        },
        backgroundColor: Theme.of(context).colorScheme.primary,
        child: Icon(Icons.add),
      ),
      bottomNavigationBar: BottomNavigationBar(
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(Icons.home),
            label: 'Inicio',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.search),
            label: 'Buscar',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.person),
            label: 'Perfil',
          ),
        ],
      ),
    );
  }
}