class Character {
  final int id;
  final String name;
  final String url;
  double get stars {
    return totalStars / reviews;
  }
  int totalStars;
  int reviews;
  // int stars;
  int strength;
  int magic;
  int speed;

  Character({
    required this.id,
    required this.name,
    required this.url,
    this.totalStars = 0,
    this.reviews = 0,
    this.strength = 0,
    this.magic = 0,
    this.speed = 0,
  });

  void addRating(int value) {
    reviews++;
    totalStars += value;
  }
}
