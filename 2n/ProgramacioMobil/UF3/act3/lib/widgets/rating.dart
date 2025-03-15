import 'package:flutter/material.dart';

import '../models/character.dart';

class Rating extends StatelessWidget {
  const Rating({
    super.key,
    required this.value, this.onStartClicked,
  });

  final double value;
  final Function(int)? onStartClicked;

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
        children: [
          for (int i=1; i<=5; i++)
            InkWell( // GestureDetector(..) és un altra opció, són iguals tret d'un sombrellat
              onTap: () {
                if (onStartClicked != null) onStartClicked!(i);
              },
              child:
                (value >= i)
                    ?  Icon(Icons.star , color:  (onStartClicked == null)? Colors.black : Colors.purple )
                    :  Icon(Icons.star_border_sharp , color:  (onStartClicked == null)? Colors.black : Colors.purple ),
            ),
        ]
    );
  }
}