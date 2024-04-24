-- Ex 1

drop function search_film(text) 

create function search_film(titol text) returns setof film as $$
declare
	noutitol text;
begin
	if not (titol ~* '[%]') then
		select concat('%', titol, '%') into noutitol;
		raise info 'Nou títol: %', noutitol;
		return query select *
		from film
		where title like noutitol;
	else
		return query select *
		from film
		where title like titol;
	end if;
end;
$$ language plpgsql;

select * from search_film('ZA')

film_id|title             |description                                                                                                                    |release_year|language_id|original_language_id|rental_duration|rental_rate|length|replacement_cost|rating|last_update            |special_features                                   |fulltext                                                                                                                                                                                                   |
-------+------------------+-------------------------------------------------------------------------------------------------------------------------------+------------+-----------+--------------------+---------------+-----------+------+----------------+------+-----------------------+---------------------------------------------------+-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
    266|DYNAMITE TARZAN   |A Intrepid Documentary of a Forensic Psychologist And a Mad Scientist who must Face a Explorer in A U-Boat                     |        2006|          1|                    |              4|       0.99|   141|           27.99|PG-13 |2007-09-10 17:46:03.905|{"Deleted Scenes"}                                 |'boat':23 'documentari':5 'dynamit':1 'explor':18 'face':16 'forens':8 'intrepid':4 'mad':12 'must':15 'psychologist':9 'scientist':13 'tarzan':2 'u':22 'u-boat':21                                       |
    279|ELIZABETH SHANE   |A Lacklusture Display of a Womanizer And a Dog who must Face a Sumo Wrestler in Ancient Japan                                  |        2006|          1|                    |              7|       4.99|   152|           11.99|NC-17 |2007-09-10 17:46:03.905|{Trailers,"Deleted Scenes","Behind the Scenes"}    |'ancient':19 'display':5 'dog':11 'elizabeth':1 'face':14 'japan':20 'lacklustur':4 'must':13 'shane':2 'sumo':16 'woman':8 'wrestler':17                                                                  |
    684|PIZZA JUMANJI     |A Epic Saga of a Cat And a Squirrel who must Outgun a Robot in A U-Boat                                                        |        2006|          1|                    |              4|       2.99|   173|           11.99|NC-17 |2007-09-10 17:46:03.905|{Commentaries}                                     |'boat':21 'cat':8 'epic':4 'jumanji':2 'must':13 'outgun':14 'pizza':1 'robot':16 'saga':5 'squirrel':11 'u':20 'u-boat':19                                                                                |
    876|TARZAN VIDEOTAPE  |A Fast-Paced Display of a Lumberjack And a Mad Scientist who must Succumb a Sumo Wrestler in The Sahara Desert                 |        2006|          1|                    |              3|       2.99|    91|           11.99|PG-13 |2007-09-10 17:46:03.905|{Trailers}                                         |'desert':24 'display':7 'fast':5 'fast-pac':4 'lumberjack':10 'mad':13 'must':16 'pace':6 'sahara':23 'scientist':14 'succumb':17 'sumo':19 'tarzan':1 'videotap':2 'wrestler':20                          |
    980|WIZARD COLDBLOODED|A Lacklusture Display of a Robot And a Girl who must Defeat a Sumo Wrestler in A MySQL Convention                              |        2006|          1|                    |              4|       4.99|    75|           12.99|PG    |2007-09-10 17:46:03.905|{Commentaries,"Deleted Scenes","Behind the Scenes"}|'coldblood':2 'convent':21 'defeat':14 'display':5 'girl':11 'lacklustur':4 'must':13 'mysql':20 'robot':8 'sumo':16 'wizard':1 'wrestler':17                                                              |
    988|WORKER TARZAN     |A Action-Packed Yarn of a Secret Agent And a Technical Writer who must Battle a Sumo Wrestler in The First Manned Space Station|        2006|          1|                    |              7|       2.99|   139|           26.99|R     |2007-09-10 17:46:03.905|{Trailers,Commentaries,"Behind the Scenes"}        |'action':5 'action-pack':4 'agent':11 'battl':18 'first':24 'man':25 'must':17 'pack':6 'secret':10 'space':26 'station':27 'sumo':20 'tarzan':2 'technic':14 'worker':1 'wrestler':21 'writer':15 'yarn':7|

-- Ex 2 

create or replace function search_film(titol text, tipus text) returns setof film as $$
declare
	noutitol text;
begin
	if not (titol ~* '[%]') then
		select concat('%', titol, '%') into titol;
		raise info 'Nou títol: %', titol;
	end if;
	if tipus = 'title' or tipus = 'description' or tipus = 'genre' then
		case
			when tipus = 'title' then
				return query select *
				from film
				where title like titol;
			when tipus = 'description' then
				return query select *
				from film
				where description like titol;
			else
				return query select f.*
				from film f
				join film_category fc on fc.film_id = f.film_id
				join category c on fc.category_id = c.category_id
				where name like titol;
		end case;
	else
		raise exception 'El tipus especificat no és el correcte.';
	end if;
end;
$$ language plpgsql;

select * from search_film('lass', 'genre')

film_id|title                  |description                                                                                                                       |release_year|language_id|original_language_id|rental_duration|rental_rate|length|replacement_cost|rating|last_update            |special_features                                            |fulltext                                                                                                                                                                         |
-------+-----------------------+----------------------------------------------------------------------------------------------------------------------------------+------------+-----------+--------------------+---------------+-----------+------+----------------+------+-----------------------+------------------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
     14|ALICE FANTASIA         |A Emotional Drama of a A Shark And a Database Administrator who must Vanquish a Pioneer in Soviet Georgia                         |        2006|          1|                    |              6|       0.99|    94|           23.99|NC-17 |2007-09-10 17:46:03.905|{Trailers,"Deleted Scenes","Behind the Scenes"}             |'administr':13 'alic':1 'databas':12 'drama':5 'emot':4 'fantasia':2 'georgia':21 'must':15 'pioneer':18 'shark':9 'soviet':20 'vanquish':16                                     |
     37|ARIZONA BANG           |A Brilliant Panorama of a Mad Scientist And a Mad Cow who must Meet a Pioneer in A Monastery                                      |        2006|          1|                    |              3|       2.99|   121|           28.99|PG    |2007-09-10 17:46:03.905|{Trailers,"Deleted Scenes"}                                 |'arizona':1 'bang':2 'brilliant':4 'cow':13 'mad':8,12 'meet':16 'monasteri':21 'must':15 'panorama':5 'pioneer':18 'scientist':9                                                |
     60|BEAST HUNCHBACK        |A Awe-Inspiring Epistle of a Student And a Squirrel who must Defeat a Boy in Ancient China                                        |        2006|          1|                    |              3|       4.99|    89|           22.99|R     |2007-09-10 17:46:03.905|{"Deleted Scenes","Behind the Scenes"}                      |'ancient':20 'awe':5 'awe-inspir':4 'beast':1 'boy':18 'china':21 'defeat':16 'epistl':7 'hunchback':2 'inspir':6 'must':15 'squirrel':13 'student':10                           |
     91|BOUND CHEAPER          |A Thrilling Panorama of a Database Administrator And a Astronaut who must Challenge a Lumberjack in A Baloon                      |        2006|          1|                    |              5|       0.99|    98|           17.99|PG    |2007-09-10 17:46:03.905|{"Behind the Scenes"}                                       |'administr':9 'astronaut':12 'baloon':20 'bound':1 'challeng':15 'cheaper':2 'databas':8 'lumberjack':17 'must':14 'panorama':5 'thrill':4                                       |
    116|CANDIDATE PERDITION    |A Brilliant Epistle of a Composer And a Database Administrator who must Vanquish a Mad Scientist in The First Manned Space Station|        2006|          1|                    |              4|       2.99|    70|           10.99|R     |2007-09-10 17:46:03.905|{"Deleted Scenes","Behind the Scenes"}                      |'administr':12 'brilliant':4 'candid':1 'compos':8 'databas':11 'epistl':5 'first':21 'mad':17 'man':22 'must':14 'perdit':2 'scientist':18 'space':23 'station':24 'vanquish':15|
    131|CENTER DINOSAUR        |A Beautiful Character Study of a Sumo Wrestler And a Dentist who must Find a Dog in California                                    |        2006|          1|                    |              5|       4.99|   152|           12.99|PG    |2007-09-10 17:46:03.905|{"Deleted Scenes"}                                          |'beauti':4 'california':20 'center':1 'charact':5 'dentist':13 'dinosaur':2 'dog':18 'find':16 'must':15 'studi':6 'sumo':9 'wrestler':10                                        |
    166|COLOR PHILADELPHIA     |A Thoughtful Panorama of a Car And a Crocodile who must Sink a Monkey in The Sahara Desert                                        |        2006|          1|                    |              6|       2.99|   149|           19.99|G     |2007-09-10 17:46:03.905|{Commentaries,"Behind the Scenes"}                          |'car':8 'color':1 'crocodil':11 'desert':20 'monkey':16 'must':13 'panorama':5 'philadelphia':2 'sahara':19 'sink':14 'thought':4                                                |
    180|CONSPIRACY SPIRIT      |A Awe-Inspiring Story of a Student And a Frisbee who must Conquer a Crocodile in An Abandoned Mine Shaft                          |        2006|          1|                    |              4|       2.99|   184|           27.99|PG-13 |2007-09-10 17:46:03.905|{Trailers,Commentaries}                                     |'abandon':21 'awe':5 'awe-inspir':4 'conquer':16 'conspiraci':1 'crocodil':18 'frisbe':13 'inspir':6 'mine':22 'must':15 'shaft':23 'spirit':2 'stori':7 'student':10            |
    184|CORE SUIT              |A Unbelieveable Tale of a Car And a Explorer who must Confront a Boat in A Manhattan Penthouse                                    |        2006|          1|                    |              3|       2.99|    92|           24.99|PG-13 |2007-09-10 17:46:03.905|{"Deleted Scenes"}                                          |'boat':16 'car':8 'confront':14 'core':1 'explor':11 'manhattan':19 'must':13 'penthous':20 'suit':2 'tale':5 'unbeliev':4                                                       |
    190|CREEPERS KANE          |A Awe-Inspiring Reflection of a Squirrel And a Boat who must Outrace a Car in A Jet Boat                                          |        2006|          1|                    |              5|       4.99|   172|           23.99|NC-17 |2007-09-10 17:46:03.905|{Trailers,"Behind the Scenes"}                              |'awe':5 'awe-inspir':4 'boat':13,22 'car':18 'creeper':1 'inspir':6 'jet':21 'kane':2 'must':15 'outrac':16 'reflect':7 'squirrel':10                                            |
    196|CRUELTY UNFORGIVEN     |A Brilliant Tale of a Car And a Moose who must Battle a Dentist in Nigeria                                                        |        2006|          1|                    |              7|       0.99|    69|           29.99|G     |2007-09-10 17:46:03.905|{"Deleted Scenes","Behind the Scenes"}                      |'battl':14 'brilliant':4 'car':8 'cruelti':1 'dentist':16 'moos':11 'must':13 'nigeria':18 'tale':5 'unforgiven':2                                                               |
    228|DETECTIVE VISION       |A Fanciful Documentary of a Pioneer And a Woman who must Redeem a Hunter in Ancient Japan                                         |        2006|          1|                    |              4|       0.99|   143|           16.99|PG-13 |2007-09-10 17:46:03.905|{Trailers,Commentaries,"Behind the Scenes"}                 |'ancient':18 'detect':1 'documentari':5 'fanci':4 'hunter':16 'japan':19 'must':13 'pioneer':8 'redeem':14 'vision':2 'woman':11                                                 |
    249|DRACULA CRYSTAL        |A Thrilling Reflection of a Feminist And a Cat who must Find a Frisbee in An Abandoned Fun House                                  |        2006|          1|                    |              7|       0.99|   176|           26.99|G     |2007-09-10 17:46:03.905|{Commentaries}                                              |'abandon':19 'cat':11 'crystal':2 'dracula':1 'feminist':8 'find':14 'frisbe':16 'fun':20 'hous':21 'must':13 'reflect':5 'thrill':4                                             |
    266|DYNAMITE TARZAN        |A Intrepid Documentary of a Forensic Psychologist And a Mad Scientist who must Face a Explorer in A U-Boat                        |        2006|          1|                    |              4|       0.99|   141|           27.99|PG-13 |2007-09-10 17:46:03.905|{"Deleted Scenes"}                                          |'boat':23 'documentari':5 'dynamit':1 'explor':18 'face':16 'forens':8 'intrepid':4 'mad':12 'must':15 'psychologist':9 'scientist':13 'tarzan':2 'u':22 'u-boat':21             |
    297|EXTRAORDINARY CONQUERER|A Stunning Story of a Dog And a Feminist who must Face a Forensic Psychologist in Berlin                                          |        2006|          1|                    |              6|       2.99|   122|           29.99|G     |2007-09-10 17:46:03.905|{Trailers,Commentaries,"Deleted Scenes"}                    |'berlin':19 'conquer':2 'dog':8 'extraordinari':1 'face':14 'feminist':11 'forens':16 'must':13 'psychologist':17 'stori':5 'stun':4                                             |
    341|FROST HEAD             |A Amazing Reflection of a Lumberjack And a Cat who must Discover a Husband in A MySQL Convention                                  |        2006|          1|                    |              5|       0.99|    82|           13.99|PG    |2007-09-10 17:46:03.905|{Trailers,"Deleted Scenes"}                                 |'amaz':4 'cat':11 'convent':20 'discov':14 'frost':1 'head':2 'husband':16 'lumberjack':8 'must':13 'mysql':19 'reflect':5                                                       |
    346|GALAXY SWEETHEARTS     |A Emotional Reflection of a Womanizer And a Pioneer who must Face a Squirrel in Berlin                                            |        2006|          1|                    |              4|       4.99|   128|           13.99|R     |2007-09-10 17:46:03.905|{"Deleted Scenes"}                                          |'berlin':18 'emot':4 'face':14 'galaxi':1 'must':13 'pioneer':11 'reflect':5 'squirrel':16 'sweetheart':2 'woman':8                                                              |
    357|GILBERT PELICAN        |A Fateful Tale of a Man And a Feminist who must Conquer a Crocodile in A Manhattan Penthouse                                      |        2006|          1|                    |              7|       0.99|   114|           13.99|G     |2007-09-10 17:46:03.905|{Trailers,Commentaries}                                     |'conquer':14 'crocodil':16 'fate':4 'feminist':11 'gilbert':1 'man':8 'manhattan':19 'must':13 'pelican':2 'penthous':20 'tale':5                                                |
    358|GILMORE BOILED         |A Unbelieveable Documentary of a Boat And a Husband who must Succumb a Student in A U-Boat                                        |        2006|          1|                    |              5|       0.99|   163|           29.99|R     |2007-09-10 17:46:03.905|{Trailers,"Behind the Scenes"}                              |'boat':8,21 'boil':2 'documentari':5 'gilmor':1 'husband':11 'must':13 'student':16 'succumb':14 'u':20 'u-boat':19 'unbeliev':4                                                 |
    425|HOLY TADPOLE           |A Action-Packed Display of a Feminist And a Pioneer who must Pursue a Dog in A Baloon Factory                                     |        2006|          1|                    |              6|       0.99|    88|           20.99|R     |2007-09-10 17:46:03.905|{"Behind the Scenes"}                                       |'action':5 'action-pack':4 'baloon':21 'display':7 'dog':18 'factori':22 'feminist':10 'holi':1 'must':15 'pack':6 'pioneer':13 'pursu':16 'tadpol':2                            |
    432|HOPE TOOTSIE           |A Amazing Documentary of a Student And a Sumo Wrestler who must Outgun a A Shark in A Shark Tank                                  |        2006|          1|                    |              4|       2.99|   139|           22.99|NC-17 |2007-09-10 17:46:03.905|{Trailers,"Deleted Scenes","Behind the Scenes"}             |'amaz':4 'documentari':5 'hope':1 'must':14 'outgun':15 'shark':18,21 'student':8 'sumo':11 'tank':22 'tootsi':2 'wrestler':12                                                   |
    445|HYDE DOCTOR            |A Fanciful Documentary of a Boy And a Woman who must Redeem a Womanizer in A Jet Boat                                             |        2006|          1|                    |              5|       2.99|   100|           11.99|G     |2007-09-10 17:46:03.905|{Trailers,"Deleted Scenes"}                                 |'boat':20 'boy':8 'doctor':2 'documentari':5 'fanci':4 'hyde':1 'jet':19 'must':13 'redeem':14 'woman':11,16                                                                     |
    469|IRON MOON              |A Fast-Paced Documentary of a Mad Cow And a Boy who must Pursue a Dentist in A Baloon                                             |        2006|          1|                    |              7|       4.99|    46|           27.99|PG    |2007-09-10 17:46:03.905|{Commentaries,"Behind the Scenes"}                          |'baloon':22 'boy':14 'cow':11 'dentist':19 'documentari':7 'fast':5 'fast-pac':4 'iron':1 'mad':10 'moon':2 'must':16 'pace':6 'pursu':17                                        |
    471|ISLAND EXORCIST        |A Fanciful Panorama of a Technical Writer And a Boy who must Find a Dentist in An Abandoned Fun House                             |        2006|          1|                    |              7|       2.99|    84|           23.99|NC-17 |2007-09-10 17:46:03.905|{Trailers,Commentaries}                                     |'abandon':20 'boy':12 'dentist':17 'exorcist':2 'fanci':4 'find':15 'fun':21 'hous':22 'island':1 'must':14 'panorama':5 'technic':8 'writer':9                                  |
    480|JEEPERS WEDDING        |A Astounding Display of a Composer And a Dog who must Kill a Pastry Chef in Soviet Georgia                                        |        2006|          1|                    |              3|       2.99|    84|           29.99|R     |2007-09-10 17:46:03.905|{Trailers,Commentaries,"Deleted Scenes"}                    |'astound':4 'chef':17 'compos':8 'display':5 'dog':11 'georgia':20 'jeeper':1 'kill':14 'must':13 'pastri':16 'soviet':19 'wed':2                                                |
    482|JEOPARDY ENCINO        |A Boring Panorama of a Man And a Mad Cow who must Face a Explorer in Ancient India                                                |        2006|          1|                    |              3|       0.99|   102|           12.99|R     |2007-09-10 17:46:03.905|{Trailers,Commentaries}                                     |'ancient':19 'bore':4 'cow':12 'encino':2 'explor':17 'face':15 'india':20 'jeopardi':1 'mad':11 'man':8 'must':14 'panorama':5                                                  |
    484|JERK PAYCHECK          |A Touching Character Study of a Pastry Chef And a Database Administrator who must Reach a A Shark in Ancient Japan                |        2006|          1|                    |              3|       2.99|   172|           13.99|NC-17 |2007-09-10 17:46:03.905|{Trailers,Commentaries,"Deleted Scenes","Behind the Scenes"}|'administr':14 'ancient':22 'charact':5 'chef':10 'databas':13 'japan':23 'jerk':1 'must':16 'pastri':9 'paycheck':2 'reach':17 'shark':20 'studi':6 'touch':4                   |
    487|JINGLE SAGEBRUSH       |A Epic Character Study of a Feminist And a Student who must Meet a Woman in A Baloon                                              |        2006|          1|                    |              6|       4.99|   124|           29.99|PG-13 |2007-09-10 17:46:03.905|{Trailers,Commentaries}                                     |'baloon':20 'charact':5 'epic':4 'feminist':9 'jingl':1 'meet':15 'must':14 'sagebrush':2 'student':12 'studi':6 'woman':17                                                      |
    512|LEAGUE HELLFIGHTERS    |A Thoughtful Saga of a A Shark And a Monkey who must Outgun a Student in Ancient China                                            |        2006|          1|                    |              5|       4.99|   110|           25.99|PG-13 |2007-09-10 17:46:03.905|{Trailers}                                                  |'ancient':19 'china':20 'hellfight':2 'leagu':1 'monkey':12 'must':14 'outgun':15 'saga':5 'shark':9 'student':17 'thought':4                                                    |
    523|LIGHTS DEER            |A Unbelieveable Epistle of a Dog And a Woman who must Confront a Moose in The Gulf of Mexico                                      |        2006|          1|                    |              7|       0.99|   174|           21.99|R     |2007-09-10 17:46:03.905|{Commentaries}                                              |'confront':14 'deer':2 'dog':8 'epistl':5 'gulf':19 'light':1 'mexico':21 'moos':16 'must':13 'unbeliev':4 'woman':11                                                            |
    525|LOATHING LEGALLY       |A Boring Epistle of a Pioneer And a Mad Scientist who must Escape a Frisbee in The Gulf of Mexico                                 |        2006|          1|                    |              4|       0.99|   140|           29.99|R     |2007-09-10 17:46:03.905|{"Deleted Scenes"}                                          |'bore':4 'epistl':5 'escap':15 'frisbe':17 'gulf':20 'legal':2 'loath':1 'mad':11 'mexico':22 'must':14 'pioneer':8 'scientist':12                                               |
    536|LOVELY JINGLE          |A Fanciful Yarn of a Crocodile And a Forensic Psychologist who must Discover a Crocodile in The Outback                           |        2006|          1|                    |              3|       2.99|    65|           18.99|PG    |2007-09-10 17:46:03.905|{Trailers,"Behind the Scenes"}                              |'crocodil':8,17 'discov':15 'fanci':4 'forens':11 'jingl':2 'love':1 'must':14 'outback':20 'psychologist':12 'yarn':5                                                           |
    537|LOVER TRUMAN           |A Emotional Yarn of a Robot And a Boy who must Outgun a Technical Writer in A U-Boat                                              |        2006|          1|                    |              3|       2.99|    75|           29.99|G     |2007-09-10 17:46:03.905|{Trailers}                                                  |'boat':22 'boy':11 'emot':4 'lover':1 'must':13 'outgun':14 'robot':8 'technic':16 'truman':2 'u':21 'u-boat':20 'writer':17 'yarn':5                                            |
    548|MAGNIFICENT CHITTY     |A Insightful Story of a Teacher And a Hunter who must Face a Mad Cow in California                                                |        2006|          1|                    |              3|       2.99|    53|           27.99|R     |2007-09-10 17:46:03.905|{Commentaries,"Deleted Scenes"}                             |'california':19 'chitti':2 'cow':17 'face':14 'hunter':11 'insight':4 'mad':16 'magnific':1 'must':13 'stori':5 'teacher':8                                                      |
    554|MALKOVICH PET          |A Intrepid Reflection of a Waitress And a A Shark who must Kill a Squirrel in The Outback                                         |        2006|          1|                    |              6|       2.99|   159|           22.99|G     |2007-09-10 17:46:03.905|{"Behind the Scenes"}                                       |'intrepid':4 'kill':15 'malkovich':1 'must':14 'outback':20 'pet':2 'reflect':5 'shark':12 'squirrel':17 'waitress':8                                                            |
    578|MILLION ACE            |A Brilliant Documentary of a Womanizer And a Squirrel who must Find a Technical Writer in The Sahara Desert                       |        2006|          1|                    |              4|       4.99|   142|           16.99|PG-13 |2007-09-10 17:46:03.905|{"Deleted Scenes"}                                          |'ace':2 'brilliant':4 'desert':21 'documentari':5 'find':14 'million':1 'must':13 'sahara':20 'squirrel':11 'technic':16 'woman':8 'writer':17                                   |
    611|MUSKETEERS WAIT        |A Touching Yarn of a Student And a Moose who must Fight a Mad Cow in Australia                                                    |        2006|          1|                    |              7|       4.99|    73|           17.99|PG    |2007-09-10 17:46:03.905|{"Deleted Scenes","Behind the Scenes"}                      |'australia':19 'cow':17 'fight':14 'mad':16 'moos':11 'musket':1 'must':13 'student':8 'touch':4 'wait':2 'yarn':5                                                               |
    633|OCTOBER SUBMARINE      |A Taut Epistle of a Monkey And a Boy who must Confront a Husband in A Jet Boat                                                    |        2006|          1|                    |              6|       4.99|    54|           10.99|PG-13 |2007-09-10 17:46:03.905|{Trailers,Commentaries,"Behind the Scenes"}                 |'boat':20 'boy':11 'confront':14 'epistl':5 'husband':16 'jet':19 'monkey':8 'must':13 'octob':1 'submarin':2 'taut':4                                                           |
    652|PAJAMA JAWBREAKER      |A Emotional Drama of a Boy And a Technical Writer who must Redeem a Sumo Wrestler in California                                   |        2006|          1|                    |              3|       0.99|   126|           14.99|R     |2007-09-10 17:46:03.905|{Trailers,"Deleted Scenes"}                                 |'boy':8 'california':20 'drama':5 'emot':4 'jawbreak':2 'must':14 'pajama':1 'redeem':15 'sumo':17 'technic':11 'wrestler':18 'writer':12                                        |
    663|PATIENT SISTER         |A Emotional Epistle of a Squirrel And a Robot who must Confront a Lumberjack in Soviet Georgia                                    |        2006|          1|                    |              7|       0.99|    99|           29.99|NC-17 |2007-09-10 17:46:03.905|{Trailers,Commentaries}                                     |'confront':14 'emot':4 'epistl':5 'georgia':19 'lumberjack':16 'must':13 'patient':1 'robot':11 'sister':2 'soviet':18 'squirrel':8                                              |
    694|PREJUDICE OLEANDER     |A Epic Saga of a Boy And a Dentist who must Outrace a Madman in A U-Boat                                                          |        2006|          1|                    |              6|       4.99|    98|           15.99|PG-13 |2007-09-10 17:46:03.905|{Trailers,Commentaries,"Deleted Scenes"}                    |'boat':21 'boy':8 'dentist':11 'epic':4 'madman':16 'must':13 'oleand':2 'outrac':14 'prejudic':1 'saga':5 'u':20 'u-boat':19                                                    |
    725|REQUIEM TYCOON         |A Unbelieveable Character Study of a Cat And a Database Administrator who must Pursue a Teacher in A Monastery                    |        2006|          1|                    |              6|       4.99|   167|           25.99|R     |2007-09-10 17:46:03.905|{Trailers,Commentaries,"Behind the Scenes"}                 |'administr':13 'cat':9 'charact':5 'databas':12 'monasteri':21 'must':15 'pursu':16 'requiem':1 'studi':6 'teacher':18 'tycoon':2 'unbeliev':4                                   |
    731|RIGHT CRANES           |A Fateful Character Study of a Boat And a Cat who must Find a Database Administrator in A Jet Boat                                |        2006|          1|                    |              7|       4.99|   153|           29.99|PG-13 |2007-09-10 17:46:03.905|{Trailers,Commentaries,"Deleted Scenes"}                    |'administr':18 'boat':9,22 'cat':12 'charact':5 'crane':2 'databas':17 'fate':4 'find':15 'jet':21 'must':14 'right':1 'studi':6                                                 |
    744|ROOTS REMEMBER         |A Brilliant Drama of a Mad Cow And a Hunter who must Escape a Hunter in Berlin                                                    |        2006|          1|                    |              4|       0.99|    89|           23.99|PG-13 |2007-09-10 17:46:03.905|{Commentaries,"Deleted Scenes","Behind the Scenes"}         |'berlin':19 'brilliant':4 'cow':9 'drama':5 'escap':15 'hunter':12,17 'mad':8 'must':14 'rememb':2 'root':1                                                                      |
    808|SLING LUKE             |A Intrepid Character Study of a Robot And a Monkey who must Reach a Secret Agent in An Abandoned Amusement Park                   |        2006|          1|                    |              5|       0.99|    84|           10.99|R     |2007-09-10 17:46:03.905|{"Behind the Scenes"}                                       |'abandon':21 'agent':18 'amus':22 'charact':5 'intrepid':4 'luke':2 'monkey':12 'must':14 'park':23 'reach':15 'robot':9 'secret':17 'sling':1 'studi':6                         |
    815|SNATCHERS MONTEZUMA    |A Boring Epistle of a Sumo Wrestler And a Woman who must Escape a Man in The Canadian Rockies                                     |        2006|          1|                    |              4|       2.99|    74|           14.99|PG-13 |2007-09-10 17:46:03.905|{Commentaries}                                              |'bore':4 'canadian':20 'epistl':5 'escap':15 'man':17 'montezuma':2 'must':14 'rocki':21 'snatcher':1 'sumo':8 'woman':12 'wrestler':9                                           |
    828|SPIKING ELEMENT        |A Lacklusture Epistle of a Dentist And a Technical Writer who must Find a Dog in A Monastery                                      |        2006|          1|                    |              7|       2.99|    79|           12.99|G     |2007-09-10 17:46:03.905|{Trailers,"Deleted Scenes","Behind the Scenes"}             |'dentist':8 'dog':17 'element':2 'epistl':5 'find':15 'lacklustur':4 'monasteri':20 'must':14 'spike':1 'technic':11 'writer':12                                                 |
    843|STEEL SANTA            |A Fast-Paced Yarn of a Composer And a Frisbee who must Face a Moose in Nigeria                                                    |        2006|          1|                    |              4|       4.99|   143|           15.99|NC-17 |2007-09-10 17:46:03.905|{Commentaries,"Deleted Scenes"}                             |'compos':10 'face':16 'fast':5 'fast-pac':4 'frisbe':13 'moos':18 'must':15 'nigeria':20 'pace':6 'santa':2 'steel':1 'yarn':7                                                   |
    862|SUMMER SCARFACE        |A Emotional Panorama of a Lumberjack And a Hunter who must Meet a Girl in A Shark Tank                                            |        2006|          1|                    |              5|       0.99|    53|           25.99|G     |2007-09-10 17:46:03.905|{"Deleted Scenes","Behind the Scenes"}                      |'emot':4 'girl':16 'hunter':11 'lumberjack':8 'meet':14 'must':13 'panorama':5 'scarfac':2 'shark':19 'summer':1 'tank':20                                                       |
    874|TADPOLE PARK           |A Beautiful Tale of a Frisbee And a Moose who must Vanquish a Dog in An Abandoned Amusement Park                                  |        2006|          1|                    |              6|       2.99|   155|           13.99|PG    |2007-09-10 17:46:03.905|{Trailers,Commentaries}                                     |'abandon':19 'amus':20 'beauti':4 'dog':16 'frisbe':8 'moos':11 'must':13 'park':2,21 'tadpol':1 'tale':5 'vanquish':14                                                          |
    891|TIMBERLAND SKY         |A Boring Display of a Man And a Dog who must Redeem a Girl in A U-Boat                                                            |        2006|          1|                    |              3|       0.99|    69|           13.99|G     |2007-09-10 17:46:03.905|{Commentaries}                                              |'boat':21 'bore':4 'display':5 'dog':11 'girl':16 'man':8 'must':13 'redeem':14 'sky':2 'timberland':1 'u':20 'u-boat':19                                                        |
    895|TOMORROW HUSTLER       |A Thoughtful Story of a Moose And a Husband who must Face a Secret Agent in The Sahara Desert                                     |        2006|          1|                    |              3|       2.99|   142|           21.99|R     |2007-09-10 17:46:03.905|{Commentaries}                                              |'agent':17 'desert':21 'face':14 'husband':11 'hustler':2 'moos':8 'must':13 'sahara':20 'secret':16 'stori':5 'thought':4 'tomorrow':1                                          |
    899|TOWERS HURRICANE       |A Fateful Display of a Monkey And a Car who must Sink a Husband in A MySQL Convention                                             |        2006|          1|                    |              7|       0.99|   144|           14.99|NC-17 |2007-09-10 17:46:03.905|{Commentaries,"Behind the Scenes"}                          |'car':11 'convent':20 'display':5 'fate':4 'hurrican':2 'husband':16 'monkey':8 'must':13 'mysql':19 'sink':14 'tower':1                                                         |
    950|VOLUME HOUSE           |A Boring Tale of a Dog And a Woman who must Meet a Dentist in California                                                          |        2006|          1|                    |              7|       4.99|   132|           12.99|PG    |2007-09-10 17:46:03.905|{Commentaries}                                              |'bore':4 'california':18 'dentist':16 'dog':8 'hous':2 'meet':14 'must':13 'tale':5 'volum':1 'woman':11                                                                         |
    951|VOYAGE LEGALLY         |A Epic Tale of a Squirrel And a Hunter who must Conquer a Boy in An Abandoned Mine Shaft                                          |        2006|          1|                    |              6|       0.99|    78|           28.99|PG-13 |2007-09-10 17:46:03.905|{Commentaries,"Behind the Scenes"}                          |'abandon':19 'boy':16 'conquer':14 'epic':4 'hunter':11 'legal':2 'mine':20 'must':13 'shaft':21 'squirrel':8 'tale':5 'voyag':1                                                 |
    962|WASTELAND DIVINE       |A Fanciful Story of a Database Administrator And a Womanizer who must Fight a Database Administrator in Ancient China             |        2006|          1|                    |              7|       2.99|    85|           18.99|PG    |2007-09-10 17:46:03.905|{Trailers,"Deleted Scenes","Behind the Scenes"}             |'administr':9,18 'ancient':20 'china':21 'databas':8,17 'divin':2 'fanci':4 'fight':15 'must':14 'stori':5 'wasteland':1 'woman':12                                              |
    970|WESTWARD SEABISCUIT    |A Lacklusture Tale of a Butler And a Husband who must Face a Boy in Ancient China                                                 |        2006|          1|                    |              7|       0.99|    52|           11.99|NC-17 |2007-09-10 17:46:03.905|{Commentaries,"Deleted Scenes"}                             |'ancient':18 'boy':16 'butler':8 'china':19 'face':14 'husband':11 'lacklustur':4 'must':13 'seabiscuit':2 'tale':5 'westward':1                                                 |

-- Ex 3 

drop function items(text, int)

create or replace function items(titol text, codi int) returns setof inventory as $$
declare
	item inventory;
	idPeli integer;
begin
	select film_id into idPeli
	from film
	where title = titol;
	for item in select * from inventory where film_id = idPeli and store_id = codi loop 
		if(inventory_in_stock(item.inventory_id)) then 
			return next item;
		end if;
	end loop;
end;
$$ language plpgsql;

select * from items('WORKER TARZAN', 1)

inventory_id|film_id|store_id|last_update            |
------------+-------+--------+-----------------------+
        4525|    988|       1|2006-02-15 10:09:17.000|
        4526|    988|       1|2006-02-15 10:09:17.000|
        4527|    988|       1|2006-02-15 10:09:17.000|

-- Ex 4

drop function noms()

create or replace function noms() returns setof customer as $$
declare  
	item inventory;
	client text;
begin 
	for item in select * from inventory order by 1 asc loop
		if(not inventory_in_stock(item.inventory_id)) then 
			select concat(first_name, ', ', last_name, ', ', (select phone from customer_list where customer_id = id), ', ', (select title from film where film_id = item.film_id)) into client
			from customer
			where customer_id = inventory_held_by_customer(item.inventory_id);
			raise info '%', client;
			return query select *
			from customer
			where customer_id = inventory_held_by_customer(item.inventory_id);
		end if;
	end loop;		
end;
$$ language plpgsql;

select * from noms()

customer_id|store_id|first_name|last_name  |email                                |address_id|activebool|create_date|last_update            |active|
-----------+--------+----------+-----------+-------------------------------------+----------+----------+-----------+-----------------------+------+
        554|       1|DWAYNE    |OLVERA     |DWAYNE.OLVERA@sakilacustomer.org     |       560|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        366|       1|BRANDON   |HUEY       |BRANDON.HUEY@sakilacustomer.org      |       371|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        111|       1|CARMEN    |OWENS      |CARMEN.OWENS@sakilacustomer.org      |       115|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        590|       2|SETH      |HANNON     |SETH.HANNON@sakilacustomer.org       |       596|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        108|       1|TRACY     |COLE       |TRACY.COLE@sakilacustomer.org        |       112|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        236|       1|MARCIA    |DEAN       |MARCIA.DEAN@sakilacustomer.org       |       240|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        512|       1|CECIL     |VINES      |CECIL.VINES@sakilacustomer.org       |       517|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         44|       1|MARIE     |TURNER     |MARIE.TURNER@sakilacustomer.org      |        48|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        349|       2|JOE       |GILLILAND  |JOE.GILLILAND@sakilacustomer.org     |       354|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        317|       2|EDWARD    |BAUGH      |EDWARD.BAUGH@sakilacustomer.org      |       322|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        199|       2|BETH      |FRANKLIN   |BETH.FRANKLIN@sakilacustomer.org     |       203|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        213|       1|GINA      |WILLIAMSON |GINA.WILLIAMSON@sakilacustomer.org   |       217|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        422|       1|MELVIN    |ELLINGTON  |MELVIN.ELLINGTON@sakilacustomer.org  |       427|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        284|       1|SONIA     |GREGORY    |SONIA.GREGORY@sakilacustomer.org     |       289|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        107|       1|FLORENCE  |WOODS      |FLORENCE.WOODS@sakilacustomer.org    |       111|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        496|       2|TYLER     |WREN       |TYLER.WREN@sakilacustomer.org        |       501|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         60|       1|MILDRED   |BAILEY     |MILDRED.BAILEY@sakilacustomer.org    |        64|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         33|       2|ANNA      |HILL       |ANNA.HILL@sakilacustomer.org         |        37|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        100|       1|ROBIN     |HAYES      |ROBIN.HAYES@sakilacustomer.org       |       104|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         91|       2|LOIS      |BUTLER     |LOIS.BUTLER@sakilacustomer.org       |        95|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         11|       2|LISA      |ANDERSON   |LISA.ANDERSON@sakilacustomer.org     |        15|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        587|       1|SERGIO    |STANFIELD  |SERGIO.STANFIELD@sakilacustomer.org  |       593|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         15|       1|HELEN     |HARRIS     |HELEN.HARRIS@sakilacustomer.org      |        19|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         29|       2|ANGELA    |HERNANDEZ  |ANGELA.HERNANDEZ@sakilacustomer.org  |        33|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        279|       2|DIANNE    |SHELTON    |DIANNE.SHELTON@sakilacustomer.org    |       284|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        359|       2|WILLIE    |MARKHAM    |WILLIE.MARKHAM@sakilacustomer.org    |       364|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         60|       1|MILDRED   |BAILEY     |MILDRED.BAILEY@sakilacustomer.org    |        64|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        155|       1|GAIL      |KNIGHT     |GAIL.KNIGHT@sakilacustomer.org       |       159|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        324|       2|GARY      |COY        |GARY.COY@sakilacustomer.org          |       329|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        394|       2|CHRIS     |BROTHERS   |CHRIS.BROTHERS@sakilacustomer.org    |       399|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        457|       2|BILL      |GAVIN      |BILL.GAVIN@sakilacustomer.org        |       462|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        101|       1|PEGGY     |MYERS      |PEGGY.MYERS@sakilacustomer.org       |       105|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        107|       1|FLORENCE  |WOODS      |FLORENCE.WOODS@sakilacustomer.org    |       111|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         99|       2|EMILY     |DIAZ       |EMILY.DIAZ@sakilacustomer.org        |       103|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        505|       1|RAFAEL    |ABNEY      |RAFAEL.ABNEY@sakilacustomer.org      |       510|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        438|       1|BARRY     |LOVELACE   |BARRY.LOVELACE@sakilacustomer.org    |       443|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        570|       2|IVAN      |CROMWELL   |IVAN.CROMWELL@sakilacustomer.org     |       576|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        474|       2|DUSTIN    |GILLETTE   |DUSTIN.GILLETTE@sakilacustomer.org   |       479|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        208|       1|LUCY      |WHEELER    |LUCY.WHEELER@sakilacustomer.org      |       212|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        228|       2|ALLISON   |STANLEY    |ALLISON.STANLEY@sakilacustomer.org   |       232|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        424|       2|KYLE      |SPURLOCK   |KYLE.SPURLOCK@sakilacustomer.org     |       429|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        215|       2|JESSIE    |BANKS      |JESSIE.BANKS@sakilacustomer.org      |       219|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        190|       2|YOLANDA   |WEAVER     |YOLANDA.WEAVER@sakilacustomer.org    |       194|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
          9|       2|MARGARET  |MOORE      |MARGARET.MOORE@sakilacustomer.org    |        13|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        175|       1|ANNETTE   |OLSON      |ANNETTE.OLSON@sakilacustomer.org     |       179|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        211|       1|STACEY    |MONTGOMERY |STACEY.MONTGOMERY@sakilacustomer.org |       215|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        421|       1|LEE       |HAWKS      |LEE.HAWKS@sakilacustomer.org         |       426|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        585|       1|PERRY     |SWAFFORD   |PERRY.SWAFFORD@sakilacustomer.org    |       591|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        508|       2|MILTON    |HOWLAND    |MILTON.HOWLAND@sakilacustomer.org    |       513|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         23|       2|SARAH     |LEWIS      |SARAH.LEWIS@sakilacustomer.org       |        27|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         64|       2|JUDITH    |COX        |JUDITH.COX@sakilacustomer.org        |        68|true      | 2006-02-14|2006-02-15 09:57:20.000|     0|
         87|       1|WANDA     |PATTERSON  |WANDA.PATTERSON@sakilacustomer.org   |        91|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        114|       2|GRACE     |ELLIS      |GRACE.ELLIS@sakilacustomer.org       |       118|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        252|       2|MATTIE    |HOFFMAN    |MATTIE.HOFFMAN@sakilacustomer.org    |       256|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        245|       1|COURTNEY  |DAY        |COURTNEY.DAY@sakilacustomer.org      |       249|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         21|       1|MICHELLE  |CLARK      |MICHELLE.CLARK@sakilacustomer.org    |        25|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        441|       1|MARIO     |CHEATHAM   |MARIO.CHEATHAM@sakilacustomer.org    |       446|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        263|       1|HILDA     |HOPKINS    |HILDA.HOPKINS@sakilacustomer.org     |       268|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        191|       1|JEANETTE  |GREENE     |JEANETTE.GREENE@sakilacustomer.org   |       195|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        234|       1|CLAUDIA   |FULLER     |CLAUDIA.FULLER@sakilacustomer.org    |       238|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        550|       2|GUY       |BROWNLEE   |GUY.BROWNLEE@sakilacustomer.org      |       556|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        516|       2|ELMER     |NOE        |ELMER.NOE@sakilacustomer.org         |       522|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        355|       2|TERRY     |GRISSOM    |TERRY.GRISSOM@sakilacustomer.org     |       360|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        448|       1|MIGUEL    |BETANCOURT |MIGUEL.BETANCOURT@sakilacustomer.org |       453|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        405|       1|LEONARD   |SCHOFIELD  |LEONARD.SCHOFIELD@sakilacustomer.org |       410|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        264|       1|GWENDOLYN |MAY        |GWENDOLYN.MAY@sakilacustomer.org     |       269|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        178|       2|MARION    |SNYDER     |MARION.SNYDER@sakilacustomer.org     |       182|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        284|       1|SONIA     |GREGORY    |SONIA.GREGORY@sakilacustomer.org     |       289|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         83|       1|LOUISE    |JENKINS    |LOUISE.JENKINS@sakilacustomer.org    |        87|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        479|       1|ZACHARY   |HITE       |ZACHARY.HITE@sakilacustomer.org      |       484|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
          5|       1|ELIZABETH |BROWN      |ELIZABETH.BROWN@sakilacustomer.org   |         9|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        597|       1|FREDDIE   |DUGGAN     |FREDDIE.DUGGAN@sakilacustomer.org    |       603|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        457|       2|BILL      |GAVIN      |BILL.GAVIN@sakilacustomer.org        |       462|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        410|       2|CURTIS    |IRBY       |CURTIS.IRBY@sakilacustomer.org       |       415|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        330|       1|SCOTT     |SHELLEY    |SCOTT.SHELLEY@sakilacustomer.org     |       335|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        228|       2|ALLISON   |STANLEY    |ALLISON.STANLEY@sakilacustomer.org   |       232|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         14|       2|BETTY     |WHITE      |BETTY.WHITE@sakilacustomer.org       |        18|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        337|       1|JERRY     |JORDON     |JERRY.JORDON@sakilacustomer.org      |       342|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        596|       1|ENRIQUE   |FORSYTHE   |ENRIQUE.FORSYTHE@sakilacustomer.org  |       602|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        534|       1|CHRISTIAN |JUNG       |CHRISTIAN.JUNG@sakilacustomer.org    |       540|true      | 2006-02-14|2006-02-15 09:57:20.000|     0|
        361|       2|LAWRENCE  |LAWTON     |LAWRENCE.LAWTON@sakilacustomer.org   |       366|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        181|       2|ANA       |BRADLEY    |ANA.BRADLEY@sakilacustomer.org       |       185|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        411|       1|NORMAN    |CURRIER    |NORMAN.CURRIER@sakilacustomer.org    |       416|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        192|       1|LAURIE    |LAWRENCE   |LAURIE.LAWRENCE@sakilacustomer.org   |       196|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        163|       1|CATHY     |SPENCER    |CATHY.SPENCER@sakilacustomer.org     |       167|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        452|       1|TOM       |MILNER     |TOM.MILNER@sakilacustomer.org        |       457|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        335|       1|GREGORY   |MAULDIN    |GREGORY.MAULDIN@sakilacustomer.org   |       340|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        155|       1|GAIL      |KNIGHT     |GAIL.KNIGHT@sakilacustomer.org       |       159|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         56|       1|GLORIA    |COOK       |GLORIA.COOK@sakilacustomer.org       |        60|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        361|       2|LAWRENCE  |LAWTON     |LAWRENCE.LAWTON@sakilacustomer.org   |       366|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        296|       2|RAMONA    |HALE       |RAMONA.HALE@sakilacustomer.org       |       301|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        533|       1|JESSIE    |MILAM      |JESSIE.MILAM@sakilacustomer.org      |       539|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        188|       1|MELANIE   |ARMSTRONG  |MELANIE.ARMSTRONG@sakilacustomer.org |       192|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         80|       1|MARILYN   |ROSS       |MARILYN.ROSS@sakilacustomer.org      |        84|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        450|       1|JAY       |ROBB       |JAY.ROBB@sakilacustomer.org          |       455|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        527|       1|CORY      |MEEHAN     |CORY.MEEHAN@sakilacustomer.org       |       533|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        336|       1|JOSHUA    |MARK       |JOSHUA.MARK@sakilacustomer.org       |       341|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        592|       1|TERRANCE  |ROUSH      |TERRANCE.ROUSH@sakilacustomer.org    |       598|true      | 2006-02-14|2006-02-15 09:57:20.000|     0|
        557|       1|FELIX     |GAFFNEY    |FELIX.GAFFNEY@sakilacustomer.org     |       563|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         53|       1|HEATHER   |MORRIS     |HEATHER.MORRIS@sakilacustomer.org    |        57|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        229|       1|TAMARA    |NGUYEN     |TAMARA.NGUYEN@sakilacustomer.org     |       233|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        493|       1|BRENT     |HARKINS    |BRENT.HARKINS@sakilacustomer.org     |       498|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        431|       2|JOEL      |FRANCISCO  |JOEL.FRANCISCO@sakilacustomer.org    |       436|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        267|       1|MARGIE    |WADE       |MARGIE.WADE@sakilacustomer.org       |       272|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         75|       2|TAMMY     |SANDERS    |TAMMY.SANDERS@sakilacustomer.org     |        79|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        162|       2|LAUREN    |HUDSON     |LAUREN.HUDSON@sakilacustomer.org     |       166|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        516|       2|ELMER     |NOE        |ELMER.NOE@sakilacustomer.org         |       522|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        561|       2|IAN       |STILL      |IAN.STILL@sakilacustomer.org         |       567|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        193|       2|KATIE     |ELLIOTT    |KATIE.ELLIOTT@sakilacustomer.org     |       197|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        227|       1|COLLEEN   |BURTON     |COLLEEN.BURTON@sakilacustomer.org    |       231|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        476|       1|DERRICK   |BOURQUE    |DERRICK.BOURQUE@sakilacustomer.org   |       481|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         43|       2|CHRISTINE |ROBERTS    |CHRISTINE.ROBERTS@sakilacustomer.org |        47|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        560|       1|JORDAN    |ARCHULETA  |JORDAN.ARCHULETA@sakilacustomer.org  |       566|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        412|       2|ALLEN     |BUTTERFIELD|ALLEN.BUTTERFIELD@sakilacustomer.org |       417|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        200|       2|JEANNE    |LAWSON     |JEANNE.LAWSON@sakilacustomer.org     |       204|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        388|       2|CRAIG     |MORRELL    |CRAIG.MORRELL@sakilacustomer.org     |       393|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        180|       2|STACY     |CUNNINGHAM |STACY.CUNNINGHAM@sakilacustomer.org  |       184|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        135|       2|JUANITA   |MASON      |JUANITA.MASON@sakilacustomer.org     |       139|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        315|       2|KENNETH   |GOODEN     |KENNETH.GOODEN@sakilacustomer.org    |       320|true      | 2006-02-14|2006-02-15 09:57:20.000|     0|
        120|       2|SYLVIA    |ORTIZ      |SYLVIA.ORTIZ@sakilacustomer.org      |       124|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        142|       1|APRIL     |BURNS      |APRIL.BURNS@sakilacustomer.org       |       146|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        300|       1|JOHN      |FARNSWORTH |JOHN.FARNSWORTH@sakilacustomer.org   |       305|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         53|       1|HEATHER   |MORRIS     |HEATHER.MORRIS@sakilacustomer.org    |        57|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        267|       1|MARGIE    |WADE       |MARGIE.WADE@sakilacustomer.org       |       272|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        560|       1|JORDAN    |ARCHULETA  |JORDAN.ARCHULETA@sakilacustomer.org  |       566|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        175|       1|ANNETTE   |OLSON      |ANNETTE.OLSON@sakilacustomer.org     |       179|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         43|       2|CHRISTINE |ROBERTS    |CHRISTINE.ROBERTS@sakilacustomer.org |        47|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        295|       1|DAISY     |BATES      |DAISY.BATES@sakilacustomer.org       |       300|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        269|       1|CASSANDRA |WALTERS    |CASSANDRA.WALTERS@sakilacustomer.org |       274|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        327|       2|LARRY     |THRASHER   |LARRY.THRASHER@sakilacustomer.org    |       332|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        530|       2|DARRYL    |ASHCRAFT   |DARRYL.ASHCRAFT@sakilacustomer.org   |       536|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        440|       1|BERNARD   |COLBY      |BERNARD.COLBY@sakilacustomer.org     |       445|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         58|       1|JEAN      |BELL       |JEAN.BELL@sakilacustomer.org         |        62|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         42|       2|CAROLYN   |PEREZ      |CAROLYN.PEREZ@sakilacustomer.org     |        46|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        537|       2|CLINTON   |BUFORD     |CLINTON.BUFORD@sakilacustomer.org    |       543|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        417|       1|TRAVIS    |ESTEP      |TRAVIS.ESTEP@sakilacustomer.org      |       422|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        208|       1|LUCY      |WHEELER    |LUCY.WHEELER@sakilacustomer.org      |       212|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        244|       2|VIOLA     |HANSON     |VIOLA.HANSON@sakilacustomer.org      |       248|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        525|       2|ADRIAN    |CLARY      |ADRIAN.CLARY@sakilacustomer.org      |       531|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         41|       1|STEPHANIE |MITCHELL   |STEPHANIE.MITCHELL@sakilacustomer.org|        45|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         73|       2|BEVERLY   |BROOKS     |BEVERLY.BROOKS@sakilacustomer.org    |        77|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        497|       2|GILBERT   |SLEDGE     |GILBERT.SLEDGE@sakilacustomer.org    |       502|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         94|       1|NORMA     |GONZALES   |NORMA.GONZALES@sakilacustomer.org    |        98|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        152|       1|ALICIA    |MILLS      |ALICIA.MILLS@sakilacustomer.org      |       156|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         75|       2|TAMMY     |SANDERS    |TAMMY.SANDERS@sakilacustomer.org     |        79|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         15|       1|HELEN     |HARRIS     |HELEN.HARRIS@sakilacustomer.org      |        19|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        334|       2|RAYMOND   |MCWHORTER  |RAYMOND.MCWHORTER@sakilacustomer.org |       339|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        495|       2|CHARLIE   |BESS       |CHARLIE.BESS@sakilacustomer.org      |       500|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        163|       1|CATHY     |SPENCER    |CATHY.SPENCER@sakilacustomer.org     |       167|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        115|       1|WENDY     |HARRISON   |WENDY.HARRISON@sakilacustomer.org    |       119|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        186|       2|HOLLY     |FOX        |HOLLY.FOX@sakilacustomer.org         |       190|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        214|       1|KRISTIN   |JOHNSTON   |KRISTIN.JOHNSTON@sakilacustomer.org  |       218|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        448|       1|MIGUEL    |BETANCOURT |MIGUEL.BETANCOURT@sakilacustomer.org |       453|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        579|       2|DARYL     |LARUE      |DARYL.LARUE@sakilacustomer.org       |       585|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        521|       2|ROLAND    |SOUTH      |ROLAND.SOUTH@sakilacustomer.org      |       527|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         22|       1|LAURA     |RODRIGUEZ  |LAURA.RODRIGUEZ@sakilacustomer.org   |        26|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         69|       2|JUDY      |GRAY       |JUDY.GRAY@sakilacustomer.org         |        73|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        548|       1|ALLAN     |CORNISH    |ALLAN.CORNISH@sakilacustomer.org     |       554|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         28|       1|CYNTHIA   |YOUNG      |CYNTHIA.YOUNG@sakilacustomer.org     |        32|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        251|       2|VICKIE    |BREWER     |VICKIE.BREWER@sakilacustomer.org     |       255|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        216|       1|NATALIE   |MEYER      |NATALIE.MEYER@sakilacustomer.org     |       220|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        369|       2|FRED      |WHEAT      |FRED.WHEAT@sakilacustomer.org        |       374|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        269|       1|CASSANDRA |WALTERS    |CASSANDRA.WALTERS@sakilacustomer.org |       274|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        576|       2|MORRIS    |MCCARTER   |MORRIS.MCCARTER@sakilacustomer.org   |       582|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        354|       2|JUSTIN    |NGO        |JUSTIN.NGO@sakilacustomer.org        |       359|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        219|       2|WILLIE    |HOWELL     |WILLIE.HOWELL@sakilacustomer.org     |       223|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         42|       2|CAROLYN   |PEREZ      |CAROLYN.PEREZ@sakilacustomer.org     |        46|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         52|       1|JULIE     |SANCHEZ    |JULIE.SANCHEZ@sakilacustomer.org     |        56|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        354|       2|JUSTIN    |NGO        |JUSTIN.NGO@sakilacustomer.org        |       359|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
         75|       2|TAMMY     |SANDERS    |TAMMY.SANDERS@sakilacustomer.org     |        79|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        582|       2|ANDY      |VANHORN    |ANDY.VANHORN@sakilacustomer.org      |       588|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        576|       2|MORRIS    |MCCARTER   |MORRIS.MCCARTER@sakilacustomer.org   |       582|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        568|       2|ALBERTO   |HENNING    |ALBERTO.HENNING@sakilacustomer.org   |       574|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        352|       1|ALBERT    |CROUSE     |ALBERT.CROUSE@sakilacustomer.org     |       357|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        287|       2|BECKY     |MILES      |BECKY.MILES@sakilacustomer.org       |       292|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        282|       2|JENNY     |CASTRO     |JENNY.CASTRO@sakilacustomer.org      |       287|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        472|       1|GREG      |ROBINS     |GREG.ROBINS@sakilacustomer.org       |       477|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        168|       1|REGINA    |BERRY      |REGINA.BERRY@sakilacustomer.org      |       172|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        274|       1|NAOMI     |JENNINGS   |NAOMI.JENNINGS@sakilacustomer.org    |       279|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        374|       2|JEREMY    |HURTADO    |JEREMY.HURTADO@sakilacustomer.org    |       379|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        216|       1|NATALIE   |MEYER      |NATALIE.MEYER@sakilacustomer.org     |       220|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        532|       2|NEIL      |RENNER     |NEIL.RENNER@sakilacustomer.org       |       538|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|
        373|       1|LOUIS     |LEONE      |LOUIS.LEONE@sakilacustomer.org       |       378|true      | 2006-02-14|2006-02-15 09:57:20.000|     1|

-- Ex 5 

create or replace function retornar(idClient int, idItem int) returns void as $$
declare 
	idErroni int;
begin 
	if exists (select  from rental r where r.inventory_id = idItem and r.customer_id = idClient and r.return_date is null) then 
		update rental 
		set return_date = now()
		where inventory_id = idItem and customer_id = idClient;
		raise info 'S`ha retornat satisfactòriament.';
	else
		if exists(select * from rental where inventory_id = idItem and return_date is null) then 
			select customer_id into idErroni
			from rental
			where inventory_id = idItem and return_date is null;
			raise exception 'El client amb l`id % té el lloguer de l`item actualment.', idErroni;
		else
			raise exception 'L`item actualment no està en lloguer.';
		end if;
	end if;
end;
$$ language plpgsql;

select *
from rental 
where inventory_id = 871 and customer_id = 474

rental_id|rental_date            |inventory_id|customer_id|return_date|staff_id|last_update            |
---------+-----------------------+------------+-----------+-----------+--------+-----------------------+
    11909|2006-02-14 15:16:03.000|         871|        474|           |       1|2006-02-16 02:30:53.000|

select retornar(474, 871)

retornar|
--------+
        |
        
S''ha retornat satisfactòriament.

select *
from rental 
where inventory_id = 871 and customer_id = 474

rental_id|rental_date            |inventory_id|customer_id|return_date            |staff_id|last_update            |
---------+-----------------------+------------+-----------+-----------------------+--------+-----------------------+
    11909|2006-02-14 15:16:03.000|         871|        474|2024-02-28 11:57:38.593|       1|2024-02-28 11:57:38.593|