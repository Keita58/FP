import 'package:flutter/material.dart';
import 'package:weather_app/services/weather_service.dart';

import '../models/meteo.dart';
import '../widgets/forecast_widget.dart';

class Home extends StatelessWidget {
  const Home({super.key});

  @override
  Widget build(BuildContext context) {

    Future<Meteo> meteo = WeatherService().fetchData();
    return Scaffold(
      appBar: AppBar(title: Text('Weatherapp')),
      body: FutureBuilder(future: meteo, builder: (context , AsyncSnapshot<Meteo> snapshot) {
         if (snapshot.hasData) {
           var meteo = snapshot.data!;
           return ForecastWidget(meteo: meteo);
         }
         else if (snapshot.hasError) {
           return const Text('Error loading data');
         }
         else {
           return const Center(child: CircularProgressIndicator());
         }
        }),
      floatingActionButton: FloatingActionButton(onPressed: () {
          WeatherService().fetchData();
        },
        child: const Icon(Icons.sunny)
      ),
    );
  }
}

