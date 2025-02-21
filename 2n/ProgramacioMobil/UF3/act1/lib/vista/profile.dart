import 'package:fitness_app/vista/main.dart';
import 'package:flutter/material.dart';

class Profile extends StatelessWidget{
  const Profile({super.key});


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
        title: Text('My profile'),
      ),
      body: Center(
        child: Column(
          children: <Widget>[
            Container(
              alignment: Alignment.center,
              child: Hero(
                  tag: 'foto',
                  child: Padding(
                    padding: const EdgeInsets.fromLTRB(0, 8, 0, 0),
                    child: CircleAvatar(
                      backgroundImage: NetworkImage(
                          "https://randomuser.me/api/portraits/women/44.jpg"),
                      maxRadius: 150,
                    ),
                  )),
            ),
            Text(
              'Antònia Font',
              style: Theme.of(context).textTheme.headlineMedium!.copyWith(
                color: Colors.black54,
              ),
            ),
            Text('since 20 April 2022',
                style: TextStyle(color: Colors.black54)),
            Padding(
              padding: const EdgeInsets.fromLTRB(0, 30, 0, 30),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: Card(
                      color: Color(0xFFCA7DF9),
                      child: Column(
                        children: [
                          Padding(
                            padding: const EdgeInsets.all(8.0),
                            child: Column(children: [
                              Icon(Icons.access_time),
                              Text('Time',
                                  style: Theme.of(context)
                                      .textTheme
                                      .titleMedium),
                              Text('2h 45\'',
                                  style: Theme.of(context)
                                      .textTheme
                                      .headlineSmall)
                            ]),
                          )
                        ],
                      ),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: Card(
                      color: Color(0xFFCA7DF9),
                      child: Column(
                        children: [
                          Padding(
                            padding: const EdgeInsets.all(8.0),
                            child: Column(children: [
                              Icon(Icons.access_time),
                              Text('Km',
                                  style: Theme.of(context)
                                      .textTheme
                                      .titleMedium),
                              Text(LlistaCartes.kmTotalsFunct().toString(),
                                  style: Theme.of(context)
                                      .textTheme
                                      .headlineSmall)
                            ]),
                          )
                        ],
                      ),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: Card(
                      color: Color(0xFFCA7DF9),
                      child: Column(
                        children: [
                          Padding(
                            padding: const EdgeInsets.all(8.0),
                            child: Column(children: [
                              Icon(Icons.access_time),
                              Text('Activities',
                                  style: Theme.of(context)
                                      .textTheme
                                      .titleMedium),
                              Text(LlistaCartes.cartes.length.toString(),
                                  style: Theme.of(context)
                                      .textTheme
                                      .headlineSmall)
                            ]),
                          )
                        ],
                      ),
                    ),
                  ),
                ],
              ),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text('Height'),
                SizedBox(
                  width: 250,
                  child: Slider(
                    value: 30,
                    max: 100,
                    activeColor: Color(0xFFCA7DF9),
                    onChanged: (double value) {},
                  ),
                ),
                Text('150 cm'),
              ],
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text('Weight'),
                SizedBox(
                  width: 250,
                  child: Slider(
                    value: 20,
                    max: 100,
                    activeColor: Color(0xFFCA7DF9),
                    onChanged: (double value) {},
                  ),
                ),
                Text('50 kg'),
              ],
            ),
          ]
        )
      )
    );
  }
}