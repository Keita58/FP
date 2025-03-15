import 'package:flutter/material.dart';

import '../models/meteo.dart';

class ForecastWidget extends StatelessWidget {
  const ForecastWidget({
    super.key,
    required this.meteo,
  });

  final Meteo meteo;

  @override
  Widget build(BuildContext context) {
    return ListView(
      children: meteo.dataseries.map((e) {
// Per cada instant de temps mostrar un row
        return Row(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: [
            Text(
              '${e.timepoint}',
              style: TextStyle(fontSize: 24),
            ),
            if (e.weather.toString() == "clearday")
              const Icon(Icons.sunny)
            else if (e.weather.toString() == "clearnight")
              const Icon(Icons.dark_mode)
            else if (e.weather.toString() == "cloudyday" || e.weather.toString() == "cloudynight")
              const Icon(Icons.cloud)
            else if (e.weather.toString() == "lightrainday" || e.weather.toString() == "lightrainnight" || e.weather.toString() == "rainday" || e.weather.toString() == "rainnight")
              const Icon(Icons.thunderstorm)
            else
              const Icon(Icons.emoji_emotions),
            Text(
              '${e.temp2M}',
              style: TextStyle(fontSize: 24),
            ),
          ],
        );
      }).toList(),
    );
  }
}
