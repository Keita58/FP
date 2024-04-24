-- DML -> Data Management Language
-- Select / Insert / Update / Delete <- Importants!! (Per buscar, actualitzar, etc. la informació de la base de dades)

select *
from LastNames
where LastNameId <= 30 and Freq1 > 50000
order by Freq1 desc;
#limit 3 -- Només t'imprimeix el número de línies que hagis posat al límit

select * 
from LastNames
where Name='Massana' or Name='Manzanares'
order by LastNameId;

select count(*) as Cognoms_Semblants
from LastNames
where Name like 'San%lo';

select count(Name)
from LastNames ln2 
where Name not like '%e%';

select DISTINCT Freq1
from LastNames 
order by LastNameId desc;

select sum(Freq1) Persones_contades
from LastNames;

select Name
from LastNames
where (Name='ARANDA' or Name='MASOLIVER') or (Freq1 <= 300 and Freq1 >= 200) or (Freq2 < 400 and Freq2 > 300)
order by Name asc;

select Name
from LastNames
where (Freq1 > 10000 or Freq1 < 50) and (Freq1 > Freq2) and (Name != 'MONEGRO' and Name != 'HARDY')
order by Name asc;