import 'package:fitness_app/Classes/cartes.dart';
import 'package:fitness_app/afegir.dart';
import 'package:fitness_app/profile.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:percent_indicator/circular_percent_indicator.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    String hexColor = "#F896D8"; // Rosa
    return MaterialApp(
      title: 'Fitness App',
      theme: ThemeData(
          // This is the theme of your application.
          //
          // TRY THIS: Try running your application with "flutter run". You'll see
          // the application has a purple toolbar. Then, without quitting the app,
          // try changing the seedColor in the colorScheme below to Colors.green
          // and then invoke "hot reload" (save your changes or press the "hot
          // reload" button in a Flutter-supported IDE, or press "r" if you used
          // the command line to start the app).
          //
          // Notice that the counter didn't reset back to zero; the application
          // state is not lost during the reload. To reset the state, use hot
          // restart instead.
          //
          // This works for code too, not just values: Most code changes can be
          // tested with just a hot reload.
          //colorScheme: ColorScheme.fromSeed(seedColor: Color(0x00f896d8)),
          colorScheme: Theme.of(context).colorScheme.copyWith(
              primary: Color(
                  int.parse(hexColor.substring(1, 7), radix: 16) + 0xFF000000),
              secondary: Colors.deepPurpleAccent,
              tertiary: Colors.blue),
          useMaterial3: true,
          fontFamily: GoogleFonts.montserrat().fontFamily,
          textTheme: Theme.of(context).textTheme.copyWith(
              headlineMedium: GoogleFonts.montserrat(
                  textStyle: Theme.of(context).textTheme.headlineMedium),
              bodyMedium: GoogleFonts.montserrat(
                  textStyle: Theme.of(context).textTheme.bodyMedium),
              titleSmall: GoogleFonts.montserrat(
                  textStyle: Theme.of(context).textTheme.titleSmall),
              titleLarge: GoogleFonts.montserrat(
                  textStyle: Theme.of(context).textTheme.titleLarge),
              headlineSmall: GoogleFonts.montserrat(
                  textStyle: Theme.of(context).textTheme.headlineSmall),
              titleMedium: GoogleFonts.montserrat(
                  textStyle: Theme.of(context).textTheme.titleMedium))),
      home: const MyHomePage(title: 'Fitness App'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  List<Cartes> cartes = [
    Cartes(
      id: 1,
      titol: "Running",
      data: 'Ayer, 18:20',
      km: 7300,
    ),
    Cartes(
      id: 2,
      titol: "Running",
      data: '15 Oct 2022, 13:45',
      km: 6500,
    ),
    Cartes(
      id: 3,
      titol: "Running",
      data: '10 Oct 2022, 19:02',
      km: 7300,
    ),
    Cartes(
      id: 4,
      titol: "Running",
      data: '10 Oct 2022, 19:02',
      km: 7300,
    ),
    Cartes(
      id: 5,
      titol: "Running",
      data: '10 Oct 2022, 19:02',
      km: 7300,
    ),
    Cartes(
      id: 6,
      titol: "Running",
      data: '10 Oct 2022, 19:02',
      km: 7300,
    ),
  ];

  @override
  Widget build(BuildContext context) {
    // This method is rerun every time setState is called, for instance as done
    // by the _incrementCounter method above.
    //
    // The Flutter framework has been optimized to make rerunning build methods
    // fast, so that you can just rebuild anything that needs updating rather
    // than having to individually change instances of widgets.
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
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        title: Text(
          widget.title,
        ),
        leading: IconButton(
          icon: Icon(Icons.menu),
          tooltip: 'Menu',
          onPressed: () {},
        ),
        actions: <Widget>[
          GestureDetector(
            onTap: () {
              Navigator.push(
                  context, MaterialPageRoute(builder: (context) => Profile()));
            },
            child: Padding(
              padding: const EdgeInsets.fromLTRB(0, 0, 10, 0),
              child: Hero(
                  tag: 'foto',
                  child: CircleAvatar(
                    backgroundImage: NetworkImage(
                        "https://randomuser.me/api/portraits/women/44.jpg"),
                  )),
            ),
          ),
        ],
      ),
      body: Column(
        children: <Widget>[
          Padding(
            padding: const EdgeInsets.fromLTRB(20, 8, 20, 8),
            child: Container(
              alignment: Alignment.centerLeft,
              child: Text(
                'Hola Diana,',
                style: Theme.of(context).textTheme.headlineMedium,
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.fromLTRB(20, 8, 20, 8),
            child: Text(
              'Come 5 veces al dia y permanece hidratada durante el dia',
              style: Theme.of(context).textTheme.bodyMedium,
            ),
          ),
          Padding(
            padding: const EdgeInsets.fromLTRB(20, 8, 20, 20),
            child: Container(
              alignment: Alignment.centerLeft,
              child: GestureDetector(
                onTap: () {
                  Navigator.push(context,
                      MaterialPageRoute(builder: (context) => Profile()));
                },
                child: Text(
                  'Más detalles',
                  style: Theme.of(context).textTheme.titleSmall!.copyWith(
                        color: Theme.of(context).colorScheme.tertiary,
                      ),
                ),
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.fromLTRB(20, 8, 20, 8),
            child: Container(
              alignment: Alignment.centerLeft,
              child: Text(
                'Últimas actividades',
                style: Theme.of(context).textTheme.titleSmall,
              ),
            ),
          ),
          Expanded(
              child: SingleChildScrollView(
                  child: Column(
            children: [
              for (int i = 0; i < cartes.length; i++)
                Padding(
                  padding: const EdgeInsets.fromLTRB(15, 0, 15, 0),
                  child: Card(
                    child: ListTile(
                      leading: Icon(Icons.run_circle_outlined),
                      title: Text(cartes[i].titol,
                          style: Theme.of(context).textTheme.titleLarge),
                      subtitle: Text(cartes[i].data),
                      trailing: Text(
                        "${cartes[i].km}km",
                        style: Theme.of(context).textTheme.headlineSmall,
                      ),
                    ),
                  ),
                ),
            ],
          ))),
          Padding(
            padding: const EdgeInsets.fromLTRB(0, 15, 0, 0),
            child: CircularPercentIndicator(
              radius: 60.0,
              lineWidth: 10.0,
              percent: 0.7,
              circularStrokeCap: CircularStrokeCap.round,
              progressColor: Theme.of(context).colorScheme.secondary,
              center: Text(
                '70.0%',
                style: Theme.of(context).textTheme.headlineMedium,
              ),
              footer: Text(
                'Objetivo mensual',
              ),
            ),
          )
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.push(
              context, MaterialPageRoute(builder: (context) => Afegir()));
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
