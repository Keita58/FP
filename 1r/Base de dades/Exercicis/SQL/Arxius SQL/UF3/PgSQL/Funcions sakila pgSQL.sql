-- Ex 1

create or replace function pelis(temps1 numeric, temps2 numeric) returns numeric as $$
declare 
	res numeric;
begin 
	select count(*) into res
	from film
	where length <= temps2 and length >= temps1;
	if(res = 0) then
		raise info 'No hi ha cap pel·lícula entre aquests dos temps.';
	end if;
	return res;
end;
$$ language plpgsql;

select pelis(80, 85)

pelis|
-----+
   56|

-- Ex 2

create or replace function actor(nom text, cognom text) returns setof film as $$
begin 
	return query select f.*
	from actor a
	join film_actor fa on a.actor_id = fa.actor_id
	join film f on f.film_id = fa.film_id
	where a.first_name = nom and a.last_name = cognom;
end;
$$ language plpgsql;

select * from actor('PENELOPE', 'GUINESS')

film_id|title                |description                                                                                                            |release_year|language_id|original_language_id|rental_duration|rental_rate|length|replacement_cost|rating|special_features                                            |last_update                  |
-------+---------------------+-----------------------------------------------------------------------------------------------------------------------+------------+-----------+--------------------+---------------+-----------+------+----------------+------+------------------------------------------------------------+-----------------------------+
      1|ACADEMY DINOSAUR     |A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies                       |        2006|          1|                    |              6|       0.99|    86|           20.99|PG    |{"Deleted Scenes","Behind the Scenes"}                      |2006-02-15 06:03:42.000 +0100|
     23|ANACONDA CONFESSIONS |A Lacklusture Display of a Dentist And a Dentist who must Fight a Girl in Australia                                    |        2006|          1|                    |              3|       0.99|    92|            9.99|R     |{Trailers,"Deleted Scenes"}                                 |2006-02-15 06:03:42.000 +0100|
     25|ANGELS LIFE          |A Thoughtful Display of a Woman And a Astronaut who must Battle a Robot in Berlin                                      |        2006|          1|                    |              3|       2.99|    74|           15.99|G     |{Trailers}                                                  |2006-02-15 06:03:42.000 +0100|
    106|BULWORTH COMMANDMENTS|A Amazing Display of a Mad Cow And a Pioneer who must Redeem a Sumo Wrestler in The Outback                            |        2006|          1|                    |              4|       2.99|    61|           14.99|G     |{Trailers}                                                  |2006-02-15 06:03:42.000 +0100|
    140|CHEAPER CLYDE        |A Emotional Character Study of a Pioneer And a Girl who must Discover a Dog in Ancient Japan                           |        2006|          1|                    |              6|       0.99|    87|           23.99|G     |{Trailers,Commentaries,"Behind the Scenes"}                 |2006-02-15 06:03:42.000 +0100|
    166|COLOR PHILADELPHIA   |A Thoughtful Panorama of a Car And a Crocodile who must Sink a Monkey in The Sahara Desert                             |        2006|          1|                    |              6|       2.99|   149|           19.99|G     |{Commentaries,"Behind the Scenes"}                          |2006-02-15 06:03:42.000 +0100|
    277|ELEPHANT TROJAN      |A Beautiful Panorama of a Lumberjack And a Forensic Psychologist who must Overcome a Frisbee in A Baloon               |        2006|          1|                    |              4|       4.99|   126|           24.99|PG-13 |{"Behind the Scenes"}                                       |2006-02-15 06:03:42.000 +0100|
    361|GLEAMING JAWBREAKER  |A Amazing Display of a Composer And a Forensic Psychologist who must Discover a Car in The Canadian Rockies            |        2006|          1|                    |              5|       2.99|    89|           25.99|NC-17 |{Trailers,Commentaries}                                     |2006-02-15 06:03:42.000 +0100|
    438|HUMAN GRAFFITI       |A Beautiful Reflection of a Womanizer And a Sumo Wrestler who must Chase a Database Administrator in The Gulf of Mexico|        2006|          1|                    |              3|       2.99|    68|           22.99|NC-17 |{Trailers,"Behind the Scenes"}                              |2006-02-15 06:03:42.000 +0100|
    499|KING EVOLUTION       |A Action-Packed Tale of a Boy And a Lumberjack who must Chase a Madman in A Baloon                                     |        2006|          1|                    |              3|       4.99|   184|           24.99|NC-17 |{Trailers,"Deleted Scenes","Behind the Scenes"}             |2006-02-15 06:03:42.000 +0100|
    506|LADY STAGE           |A Beautiful Character Study of a Woman And a Man who must Pursue a Explorer in A U-Boat                                |        2006|          1|                    |              4|       4.99|    67|           14.99|PG    |{Trailers,"Deleted Scenes","Behind the Scenes"}             |2006-02-15 06:03:42.000 +0100|
    509|LANGUAGE COWBOY      |A Epic Yarn of a Cat And a Madman who must Vanquish a Dentist in An Abandoned Amusement Park                           |        2006|          1|                    |              5|       0.99|    78|           26.99|NC-17 |{Trailers,"Deleted Scenes"}                                 |2006-02-15 06:03:42.000 +0100|
    605|MULHOLLAND BEAST     |A Awe-Inspiring Display of a Husband And a Squirrel who must Battle a Sumo Wrestler in A Jet Boat                      |        2006|          1|                    |              7|       2.99|   157|           13.99|PG    |{Trailers,"Deleted Scenes","Behind the Scenes"}             |2006-02-15 06:03:42.000 +0100|
    635|OKLAHOMA JUMANJI     |A Thoughtful Drama of a Dentist And a Womanizer who must Meet a Husband in The Sahara Desert                           |        2006|          1|                    |              7|       0.99|    58|           15.99|PG    |{"Behind the Scenes"}                                       |2006-02-15 06:03:42.000 +0100|
    749|RULES HUMAN          |A Beautiful Epistle of a Astronaut And a Student who must Confront a Monkey in An Abandoned Fun House                  |        2006|          1|                    |              6|       4.99|   153|           19.99|R     |{"Deleted Scenes","Behind the Scenes"}                      |2006-02-15 06:03:42.000 +0100|
    832|SPLASH GUMP          |A Taut Saga of a Crocodile And a Boat who must Conquer a Hunter in A Shark Tank                                        |        2006|          1|                    |              5|       0.99|   175|           16.99|PG    |{Trailers,Commentaries,"Deleted Scenes","Behind the Scenes"}|2006-02-15 06:03:42.000 +0100|
    939|VERTIGO NORTHWEST    |A Unbelieveable Display of a Mad Scientist And a Mad Scientist who must Outgun a Mad Cow in Ancient Japan              |        2006|          1|                    |              4|       2.99|    90|           17.99|R     |{Commentaries,"Behind the Scenes"}                          |2006-02-15 06:03:42.000 +0100|
    970|WESTWARD SEABISCUIT  |A Lacklusture Tale of a Butler And a Husband who must Face a Boy in Ancient China                                      |        2006|          1|                    |              7|       0.99|    52|           11.99|NC-17 |{Commentaries,"Deleted Scenes"}                             |2006-02-15 06:03:42.000 +0100|
    980|WIZARD COLDBLOODED   |A Lacklusture Display of a Robot And a Girl who must Defeat a Sumo Wrestler in A MySQL Convention                      |        2006|          1|                    |              4|       4.99|    75|           12.99|PG    |{Commentaries,"Deleted Scenes","Behind the Scenes"}         |2006-02-15 06:03:42.000 +0100|
    
-- Ex 3

create or replace function idioma(peli text, idioma text) returns void as $$
declare 
	idioma_id int;
begin 
	if exists (select * from language where name = idioma) then
		select language_id into idioma_id 
		from language 
		where name = idioma;
		if exists (select * from film where title = peli) then 
			insert into film(title, description, release_year, language_id, original_language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features)
			select title, description, release_year, idioma_id, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features from film where title = peli;
		else
			raise exception 'La pel·lícula seleccionada no existeix.';
		end if;
	else
		raise exception 'No s''ha trobat l''idioma especificat.';
	end if;
end;
$$ language plpgsql;

select * from film where title = 'TEQUILA PAST'

film_id|title       |description                                                                                    |release_year|language_id|original_language_id|rental_duration|rental_rate|length|replacement_cost|rating|special_features                  |last_update                  |
-------+------------+-----------------------------------------------------------------------------------------------+------------+-----------+--------------------+---------------+-----------+------+----------------+------+----------------------------------+-----------------------------+
    883|TEQUILA PAST|A Action-Packed Panorama of a Mad Scientist And a Robot who must Challenge a Student in Nigeria|        2006|          1|                    |              6|       4.99|    53|           17.99|PG    |{Commentaries,"Behind the Scenes"}|2006-02-15 06:03:42.000 +0100|

select idioma('TEQUILA PAST', 'Italian')

select * from film where title = 'TEQUILA PAST'


film_id|title       |description                                                                                    |release_year|language_id|original_language_id|rental_duration|rental_rate|length|replacement_cost|rating|special_features                  |last_update                  |
-------+------------+-----------------------------------------------------------------------------------------------+------------+-----------+--------------------+---------------+-----------+------+----------------+------+----------------------------------+-----------------------------+
    883|TEQUILA PAST|A Action-Packed Panorama of a Mad Scientist And a Robot who must Challenge a Student in Nigeria|        2006|          1|                    |              6|       4.99|    53|           17.99|PG    |{Commentaries,"Behind the Scenes"}|2006-02-15 06:03:42.000 +0100|
   1002|TEQUILA PAST|A Action-Packed Panorama of a Mad Scientist And a Robot who must Challenge a Student in Nigeria|        2006|          2|                   1|              6|       4.99|    53|           17.99|PG    |{Commentaries,"Behind the Scenes"}|                             |
