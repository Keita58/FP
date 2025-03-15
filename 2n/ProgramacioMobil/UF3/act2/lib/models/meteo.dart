// To parse this JSON data, do
//
//     final meteo = meteoFromJson(jsonString);

import 'dart:convert';

Meteo meteoFromJson(String str) => Meteo.fromJson(json.decode(str));

String meteoToJson(Meteo data) => json.encode(data.toJson());

class Meteo {
  String product;
  String init;
  List<Dataserie> dataseries;

  Meteo({
    required this.product,
    required this.init,
    required this.dataseries,
  });

  factory Meteo.fromJson(Map<String, dynamic> json) => Meteo(
    product: json["product"],
    init: json["init"],
    dataseries: List<Dataserie>.from(json["dataseries"].map((x) => Dataserie.fromJson(x))),
  );

  Map<String, dynamic> toJson() => {
    "product": product,
    "init": init,
    "dataseries": List<dynamic>.from(dataseries.map((x) => x.toJson())),
  };
}

class Dataserie {
  int timepoint;
  int cloudcover;
  int liftedIndex;
  String precType;
  int precAmount;
  int temp2M;
  String rh2M;
  Wind10M wind10M;
  String weather;

  Dataserie({
    required this.timepoint,
    required this.cloudcover,
    required this.liftedIndex,
    required this.precType,
    required this.precAmount,
    required this.temp2M,
    required this.rh2M,
    required this.wind10M,
    required this.weather,
  });

  factory Dataserie.fromJson(Map<String, dynamic> json) => Dataserie(
    timepoint: json["timepoint"],
    cloudcover: json["cloudcover"],
    liftedIndex: json["lifted_index"],
    precType: json["prec_type"],
    precAmount: json["prec_amount"],
    temp2M: json["temp2m"],
    rh2M: json["rh2m"],
    wind10M: Wind10M.fromJson(json["wind10m"]),
    weather: json["weather"],
  );

  Map<String, dynamic> toJson() => {
    "timepoint": timepoint,
    "cloudcover": cloudcover,
    "lifted_index": liftedIndex,
    "prec_type": precTypeValues.reverse[precType],
    "prec_amount": precAmount,
    "temp2m": temp2M,
    "rh2m": rh2M,
    "wind10m": wind10M.toJson(),
    "weather": weather,
  };
}

enum PrecType {
  NONE,
  RAIN,
  SNOW
}

final precTypeValues = EnumValues({
  "none": PrecType.NONE,
  "rain": PrecType.RAIN,
  "snow": PrecType.SNOW
});

class Wind10M {
  String direction;
  int speed;

  Wind10M({
    required this.direction,
    required this.speed,
  });

  factory Wind10M.fromJson(Map<String, dynamic> json) => Wind10M(
    direction: json["direction"],
    speed: json["speed"],
  );

  Map<String, dynamic> toJson() => {
    "direction": directionValues.reverse[direction],
    "speed": speed,
  };
}

enum Direction {
  E,
  N,
  NE,
  NW,
  S,
  SE,
  SW
}

final directionValues = EnumValues({
  "E": Direction.E,
  "N": Direction.N,
  "NE": Direction.NE,
  "NW": Direction.NW,
  "S": Direction.S,
  "SE": Direction.SE,
  "SW": Direction.SW
});

class EnumValues<T> {
  Map<String, T> map;
  late Map<T, String> reverseMap;

  EnumValues(this.map);

  Map<T, String> get reverse {
    reverseMap = map.map((k, v) => MapEntry(v, k));
    return reverseMap;
  }
}
