-- mostra el nom complet i dataNaixement dels tres usuaris més joves ordenats de més petit a més gran
select nomUsuari, cognom1Usuari, cognom2Usuari, dataNaixement 
from usuaris
order by dataNaixement DESC 
limit 3;

-- mostra sense repeticions el nom dels usuaris el nom dels quals començi per 'A', ordena el resultat alfabèticament
select distinct nomUsuari 
from usuaris
where nomUsuari like 'A%'
order by nomUsuari;

-- mostra el nom, cognom1, cognom2, rol dels usuaris que no tinguin segon cognom i que tinguin rol 'A'
select nomUsuari, cognom1Usuari, cognom2Usuari, rol
from usuaris
where cognom2Usuari is null and rol = 'A'

-- mostra el nom, cognom1, cognom2 dels usuaris que el seu nom, cognom1, cognom2 comenci per 'A'
select nomUsuari, cognom1Usuari, cognom2Usuari 
from usuaris 
where nomUsuari like 'A%' and cognom1Usuari like 'A%' and cognom2Usuari like 'A%'

-- mostra el nom, cognom1, cognom2 dels usuaris que el seu nom, cognom1, cognom2 acabi per 'A'
select nomUsuari, cognom1Usuari, cognom2Usuari 
from usuaris 
where nomUsuari like '%a' and cognom1Usuari like '%a' and cognom2Usuari like '%a'

-- mostra el nom, cognom1, cognom2 dels usuaris que el seu nom, cognom1, cognom2 contingui, almenys, dues 'A'
select nomUsuari, cognom1Usuari, cognom2Usuari 
from usuaris 
where nomUsuari like '%a%a%' and cognom1Usuari like '%a%a%' and cognom2Usuari like '%a%a%'

-- mostra el nom, cognom1, cognom2 dels usuaris que el seu nom, cognom1, cognom2 tingui, exactament, 4 lletres
select nomUsuari, cognom1Usuari, cognom2Usuari 
from usuaris 
where nomUsuari like '____' and cognom1Usuari like '____' and cognom2Usuari like '____'

-- Selecciona els alumnes nascut al març de 2003 
select *
from usuaris
where dataNaixement between '2003-03-01' and '2003-03-31'

#----------------------------------------------------------------------------------------------------------------
-- Mostra el nom, cognom1 i cognom2 dels usuaris amb el rol 'A' i que estiguin actius (valor 1) Ordena els resultats per cognom1, cognom2 i nom i mostra únicament els 10 primers
select u.nomUsuari, u.cognom1Usuari, u.cognom2Usuari 
from usuaris u 
where rol like 'A' and actiu = 1
order by 2, 3, 1
limit 10

-- Mostra, en una única columna el cognom1, cognom2, nomUsuari dels usuaris nascuts abans de l'any 2000 ordenats per dataNaixement i idUsuari. Exemple: Folch Gonzalez, Carles (és a dir, deixar entre els cognoms un espai i posar una ',' després del segon cognom) Si un usuari no te algun dels cognoms, ha de deixar aquest cognom en blanc Exemple: Contreras , Cristhian
select CONCAT(IFNULL(u.cognom1Usuari,''), ' ', IFNULL(u.cognom2Usuari,''), ', ', u.nomUsuari)
from usuaris u 
where year(u.dataNaixement) < '2000'
order by u.dataNaixement, u.idUsuari 

-- Motrar l'idUsuari, data de naixement, rol i sexe d'aquells usuaris que no tinguin data de naixement o sigui a partir de l'any 2000 i, que a més, siguin dones (sexe 'F') Ordena per idUsuari
select u.idUsuari, u.dataNaixement, u.rol, u.sexe 
from usuaris u 
where (u.dataNaixement is null or year(u.dataNaixement) > 1999) and u.sexe like 'F'
order by u.idUsuari 

-- Saber, dels cicles de grau superior (nivell), els mòduls que tenen més de 100 hores. S'ha de mostrar el nom del cicle, el nom del mòdul i les hores. Ordenar el resultat per hores i per nom del mòdul
select c.nom, m.nom as modul, m.hores 
from moduls m 
join cicles c on m.cicle = c.id 
where m.hores > 100
order by m.hores, m.nom

-- Saber, dels cicles formatius que tinguin mòdulos, quants mòduls té i el número total d'hores dels mòduls que conté. Mostreu, ordernats per nom de cicle, número de mòduls i total d'hores dels cicles dels que hi ha definida la informació demanada.
select c.nom, count(m.id), sum(m.hores) 
from cicles c 
join moduls m on m.cicle = c.id 
group by c.nom
order by c.nom, 2, 3 

-- Saber, per cada cicle formatiu, el seu nom, quants mòduls té i el número total d'hores dels mòduls que conté. Si un cicle formatiu no te definits els mòduls, indicar que te 0 mòduls i les seves hores sumen 0. Mostreu els resultats ordenats per nom del cicle
select c.nom, ifnull(count(m.id), 0), ifnull(sum(m.hores), 0) 
from cicles c 
left join moduls m on m.cicle = c.id 
group by c.nom
order by c.nom

-- Saber, per cada cicle formatiu, nom del cicle, quants mòduls té, quantes unitats formatives té i el número d'hores que representen aquests unitats formatives. Mostreu únicament els cicles formatius dels que hi ha definida la informació demanada, ordenats per nom de cicle
select c.nom AS NomCicle, COUNT(DISTINCT m.id) AS NumeroModuls, COUNT(DISTINCT u.id) AS UnitatsFormatives, SUM(u.hores) AS HoresUnitatsFormatives 
FROM cicles c 
JOIN moduls m ON c.id = m.Cicle 
JOIN unitats_formatives u ON m.id = u.idmodul 
GROUP BY c.nom 
ORDER BY c.nom;

-- Saber, per cada cicle formatiu, quants mòduls té, quantes unitats formatives té i el número d'hores que representen aquests unitats formatives. Si un cicle formatiu no te definits els mòduls, indicar que te 0 mòduls i les seves hores sumen 0. Mostreu els resultats ordenats per nom del cicle
select c.nom, count(distinct m.id), count(uf.id), ifnull(sum(uf.hores), 0)
from cicles c 
left join moduls m on m.cicle = c.id 
left join unitats_formatives uf on uf.idmodul = m.id
group by c.nom
order by c.nom 

-- Saber el nom del cicle, el nom del mòdul i número d'unitats formatives dels mòduls que tenen més de 3 unitats formatives. Ordeneu el resultat per nom del cicle, i dins de cada cicle, pel nom del mòdul.
select c.nom as cicles, m.nom, count(uf.id)
from cicles c 
join moduls m on m.cicle = c.id 
join unitats_formatives uf on uf.idmodul = m.id 
group by c.nom, m.nom
having count(uf.id) > 3
order by c.nom, m.nom

-- Mostrar el nom dels mòduls que compleixen que el número d'hores assignades no es correspon amb el número d'hores que sumen les seves unitats formatives. Mostreu també el número d'hores del mòdul i el número d'hores de les seves unitats formatives
select m.nom, m.hores, sum(uf.hores)
from moduls m 
join unitats_formatives uf on uf.idmodul = m.id 
group by m.nom, m.hores
having sum(uf.hores) < m.hores or sum(uf.hores) > m.hores

#-------------------------------------------------------------------------------------------------------------------------------------------------------
-- Mostra l'idUsuari dels 10 últims usuaris (diferents) que han accedit (taula registre). Ordena els resultats per l'idUsuari.
select distinct u.idUsuari 
from registre r 
right join usuaris u on r.idUsuari = u.idUsuari 
order by r.Moment desc
limit 10

-- Mostra, per cada usuari que s'hagi logejat, el seu idUsuari, el moment de l'últim accés, el moment del primer accés i el número d'accessos que ha fet
select idUsuari, max(r.Moment), min(r.Moment), count(r.id)
from registre r 
group by r.idUsuari 

-- Mostrar l'idUsuari, el nom, cognoms dels usuaris actius amb rol 'A' que hagin accedit més de 10 vegades
select r.idUsuari, u.nomUsuari, u.cognom1Usuari, u.cognom2Usuari 
from usuaris u 
join registre r on u.idUsuari = r.idUsuari 
where u.rol like 'A' and u.actiu = 1
group by r.idUsuari, u.nomUsuari, u.cognom1Usuari, u.cognom2Usuari 
having count(r.Id) > 10

-- Mostrar el nom i cognoms dels usuaris que no han accedit mai. Ordena els resultats per cognom1, cognom2, nom
(select u.nomUsuari, u.cognom1Usuari, u.cognom2Usuari 
from usuaris u)
except
(select distinct u.nomUsuari, u.cognom1Usuari, u.cognom2Usuari 
from usuaris u
join registre r on r.idUsuari = u.idUsuari)
order by 2, 3, 1

-- Saber el nom i cognoms dels usuaris que més vegades han accedit, sense fer terrorisme. Ordena el resultat per cognom1, cognom2, nom
select u.nomUsuari, u.cognom1Usuari, u.cognom2Usuari 
from usuaris u 
join registre r on u.idUsuari = r.idUsuari 
group by u.nomUsuari, u.cognom1Usuari, u.cognom2Usuari, r.idUsuari 
having count(r.Id) = (select count(r2.id) from registre r2 group by r2.idUsuari order by 1 desc limit 1)
order by 2, 3, 1

-- Mostrar el nom i cognoms dels 10 últims usuaris que han accedit al sistema ordenant el resultat per cognom1, cognom2, nom
select u.nomUsuari, u.cognom1Usuari, u.cognom2Usuari 
from registre r 
join usuaris u on u.idUsuari = r.idUsuari 
order by r.Moment DESC
limit 10


-- Mostrar els noms dels usuaris que es repeteixen almenys 5 vegades a la taula usuaris
select u.nomUsuari
from usuaris u 
group by u.nomUsuari 
having count(u.nomUsuari) >= 5

-- Saber, per cada rol, quants usuaris actius hi ha
select u.rol, count(u.idUsuari)
from usuaris u 
where u.actiu = 1
group by u.rol

-- Saber els noms dels usuaris que es repeteixen i que tenen assignat un rol diferent. Ordeneu el resultat per nom
select u.nomUsuari
from usuaris u 
group by u.nomUsuari
having count(u.nomUsuari) > 1 and count(distinct u.rol) > 1
order by u.nomUsuari 

-- Mostrar el nom, cognoms dels usuaris on tant el seu nom, cognom1, cognom2 tenen dues 'A' almenys.
select u.nomUsuari, u.cognom1Usuari, u.cognom2Usuari 
from usuaris u 
where u.nomUsuari like '%a%a%' and u.cognom1Usuari like '%a%a%' and u.cognom2Usuari like '%a%a%'

#------------------------------------------------------------------------------------------------------------------------------------------------------
-- Mostra, sense repetició, el primer cognom dels usuaris. Ordena alfabèticament els resultats
select distinct u.cognom1Usuari 
from usuaris u 
order by 1

-- Mostra la freqüència d'aparició dels cognom1 dels usuaris. Ordena per freqûència d'aparició descendentment, i, cas d'empat, alfabèticament.
select u.cognom1Usuari, count(*)
from usuaris u
group by u.cognom1Usuari
order by 2 desc, 1

-- Mostra la freqüència d'aparició dels cognom1 dels usuaris que estiguin actius (1) i la freqüència d'aparició dels quals sigui superior a 2. Ordena per freqûència d'aparició descendentment, i, cas d'empat, alfabèticament.
select u.cognom1Usuari, count(*)
from usuaris u
where u.actiu = 1
group by u.cognom1Usuari
having count(*) > 2
order by 2 desc, 1

-- Mostra, en una única columna, el cognom dels usuaris, tant si apareixen com a primer cognom com a segon cognom. Els cognoms no han de ser NULL. Ordena alfabèticament els resultats
(select u.cognom1Usuari 
from usuaris u
where u.cognom1Usuari is not null) 
union
(select u2.cognom2Usuari
from usuaris u2
where u2.cognom2Usuari is not null)
order by 1

-- Saber, per cada any de naixement, quants usuaris hi ha. Considereu solament aquells usuaris que tenen posada la data de naixement.
select year(u.dataNaixement), count(u.idUsuari)
from usuaris u 
where u.dataNaixement is not null
group by year(u.dataNaixement) 

-- Mostra l'idUsuari, el dia del mes que va néixer, el més que va néixer, l'any que va néixer (columnes separeades) dels usuaris que tenen definida la data de naixement. Ordena els resultats per any, mes, dia
select u.idUsuari, day(u.dataNaixement), month(u.dataNaixement), year(u.dataNaixement)
from usuaris u 
where u.dataNaixement is not null
order by 4, 3, 2

-- Mostra els dies i mesos on hi ha almenys 3 usuaris que fan el seu aniversari aquell dia. Mostra també quants usuaris fan els anys aquell dia. Agafa únicament aquells usuaris que tenen definida la data de naixement. Ordena els resultats segons el número d'usuaris que comparteixen aniversari descendentment, i en cas d'empat, per mes i dia.
select day(u.dataNaixement), month(u.dataNaixement), count(u.idUsuari)
from usuaris u 
where u.dataNaixement is not null
group by day(u.dataNaixement), month(u.dataNaixement) 
having count(u.idUsuari) > 2
order by 3 desc, 2, 1

-- Per cada grup, mostra el nom del grup i la data de naixement de l'alumne (rol 'A') més jove i la data de naixement de l'alumne gran.
select g.nom, min(u.dataNaixement), max(u.dataNaixement)
from usuaris u 
join grups g on u.idGrup = g.id 
where u.rol like 'A'
group by g.nom

-- Per cada grup, mostra el nom del grup i la data de naixement de l'alumne (rol 'A') més jove i la data de naixement de l'alumne gran. Mostra també la diferència en dies entre aquestes dues dates. Fes que solament surtin les dades quan la diferència en dies sigui superior a 10000 dies
select g.nom, min(u.dataNaixement), max(u.dataNaixement), DATEDIFF(max(u.dataNaixement), min(u.dataNaixement)) 
from usuaris u 
join grups g on u.idGrup = g.id 
where u.rol like 'A'
group by g.nom
having DATEDIFF(max(u.dataNaixement), min(u.dataNaixement)) > 10000

-- Mostra el nom i cognoms dels usuaris que tenen el mateixos cognoms (cognom1 igual que cognom2). Ordena els resultats per cognoms
select u.nomUsuari, u.cognom1Usuari, u.cognom2Usuari 
from usuaris u 
where u.cognom1Usuari like u.cognom2Usuari 
order by u.cognom1Usuari, u.cognom2Usuari 

-- Mostra sense repetició, els cognoms que surten com a primer cognom i com a segon cognom. Ordena els resultats alfabèticament
(select distinct u.cognom1Usuari
from usuaris u 
where u.cognom1Usuari is not null)
union
(select distinct u.cognom2Usuari
from usuaris u 
where u.cognom2Usuari is not null)
order by 1


-- Volem saber si hi ha algun cognom1 que surti també com a cognom2 i com a nom. Potser no n'hi ha cap. Ordena alfabèticament els resultats
select u.cognom1Usuari 
from usuaris u 
where u.cognom1Usuari like u.cognom2Usuari and u.cognom1Usuari like u.nomUsuari 
order by 1