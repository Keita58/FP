import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

import '../models/meteo.dart';


class WeatherService {

 Future<Meteo> fetchData ( ) async {
    // https://www.7timer.info/bin/api.pl?lon=113.17&lat=23.09&product=civil&output=json
    var response = await http.get(Uri.parse('https://www.7timer.info/bin/api.pl?lon=113.17&lat=23.09&product=civil&output=json'));
    debugPrint(response.body.toString());
    return  meteoFromJson(response.body);
   }
}