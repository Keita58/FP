import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';
import 'package:http/http.dart' as http;

import '../models/meteo.dart';

class WeatherService {
  Future<Meteo> fetchData() async {
    bool serviceEnabled;
    LocationPermission permission;
    serviceEnabled = await Geolocator.isLocationServiceEnabled();
    if (!serviceEnabled) {
      return Future.error('Location services are disabled');
    }

    permission = await Geolocator.checkPermission();
    if (permission == LocationPermission.denied) {
      permission = await Geolocator.requestPermission();
      if (permission == LocationPermission.denied) {
        return Future.error('Location permissions are denied');
      }
    }

    var localitzacio = await Geolocator.getCurrentPosition();
    var response = await http.get(Uri.parse(
        'https://www.7timer.info/bin/api.pl?lon=${localitzacio.longitude}&lat=${localitzacio.latitude}&product=civil&output=json'));
    debugPrint("${localitzacio.longitude} ${localitzacio.latitude}");
    debugPrint(response.body.toString());
    return meteoFromJson(response.body);
  }
}
