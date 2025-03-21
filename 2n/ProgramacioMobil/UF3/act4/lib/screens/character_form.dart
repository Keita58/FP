import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:harry_potter/models/character.dart';

class FormCharacter extends StatefulWidget {
  const FormCharacter({super.key});

  @override
  State<FormCharacter> createState() => _FormCharacterState();
}

class _FormCharacterState extends State<FormCharacter> {
  final controladorId = TextEditingController();
  final controladorNom = TextEditingController();
  final controladorUrl = TextEditingController();
  final controladorForca = TextEditingController();
  final controladorVelocitat = TextEditingController();
  final controladorMagia = TextEditingController();

  @override
  void dispose() {
    // Clean up the controller when the widget is disposed.
    controladorId.dispose();
    controladorNom.dispose();
    controladorUrl.dispose();
    controladorForca.dispose();
    controladorVelocitat.dispose();
    controladorMagia.dispose();
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
        title: const Text('Afegir mac'),
      ),
      body: Center(
        child: SizedBox(
          width: 350,
          child: Center(
            child: Column(
              children: [
                const Padding(
                  padding: EdgeInsets.fromLTRB(8, 30, 8, 8),
                ),
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: TextField(
                    decoration: const InputDecoration(border: OutlineInputBorder(), labelText: 'Id: '),
                    controller: controladorId,
                    keyboardType: const TextInputType.numberWithOptions(),
                    inputFormatters: <TextInputFormatter>[
                      FilteringTextInputFormatter.allow(
                        RegExp(r'(^\d*\.?\d{0})'),
                      )
                    ],
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: TextField(
                    decoration: const InputDecoration(border: OutlineInputBorder(), labelText: 'Nom mac: '),
                    controller: controladorNom,
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: TextField(
                    decoration: const InputDecoration(border: OutlineInputBorder(), labelText: 'Imatge: '),
                    controller: controladorUrl,
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: TextField(
                    decoration: const InputDecoration(border: OutlineInputBorder(), labelText: 'Força: '),
                    controller: controladorForca,
                    keyboardType: const TextInputType.numberWithOptions(),
                    inputFormatters: <TextInputFormatter>[
                      FilteringTextInputFormatter.allow(
                        RegExp(r'(^\d*\.?\d{0})'),
                      )
                    ],
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: TextField(
                    decoration: const InputDecoration(border: OutlineInputBorder(), labelText: 'Velocitat: '),
                    controller: controladorVelocitat,
                    keyboardType: const TextInputType.numberWithOptions(),
                    inputFormatters: <TextInputFormatter>[
                      FilteringTextInputFormatter.allow(
                        RegExp(r'(^\d*\.?\d{0})'),
                      )
                    ],
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: TextField(
                    decoration: const InputDecoration(border: OutlineInputBorder(), labelText: 'Màgia: '),
                    controller: controladorMagia,
                    keyboardType: const TextInputType.numberWithOptions(),
                    inputFormatters: <TextInputFormatter>[
                      FilteringTextInputFormatter.allow(
                        RegExp(r'(^\d*\.?\d{0})'),
                      )
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
          if (controladorNom.text.isNotEmpty && controladorForca.text.isNotEmpty && int.tryParse(controladorForca.text) != null) {
            int id = int.parse(controladorId.text);
            int forca = int.parse(controladorForca.text);
            int vel = int.parse(controladorVelocitat.text);
            int magia = int.parse(controladorMagia.text);
            Character aux = Character(id: id, name: controladorNom.text, url: controladorUrl.text, strength: forca, speed: vel, magic: magia);
            Navigator.pop(context, aux);
          } else {
            // Mostrar un missatge d'error a l'usuari
            String missatgeError = "";
            if(controladorNom.text.isEmpty){
              missatgeError = "El camp Activitat realitzada no pot estar buit.";
            } else if(controladorForca.text.isEmpty){
              missatgeError = "El camp Força no pot estar buit.";
            } else {
              missatgeError = "Si us plau, introdueix una quantitat vàlida.";
            }
            //Missatge d'error per si falten dades
            ScaffoldMessenger.of(context).showSnackBar(
              SnackBar(content: Text(missatgeError)),
            );
          }
        },
        child: const Icon(Icons.add),
      ),
    );
  }
}