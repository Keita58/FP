
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
        children:
         meteo.dataseries.map( (e) {
           // PEr cada instant de temps mostrar un row

           return  Row (
             mainAxisAlignment: MainAxisAlignment.spaceEvenly,
             children: [
               Text('${e.timePoint}', style: TextStyle(fontSize: 24),),
               Icon(Icons.sunny),
               Text('${e.temp2M}', style: TextStyle(fontSize: 24),),

             ],

           );




         } ).toList(),




    );
  }
}