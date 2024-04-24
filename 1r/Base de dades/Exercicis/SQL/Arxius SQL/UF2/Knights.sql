-- in

select Name, Color
from Dragons
where Color in ('Blue', 'Black', 'Green')
order by Name asc;

-- between

select Name
from Dragons
where KnownVictims >= 300 and KnownVictims <= 500
order by Name asc;

select Name
from Dragons
where KnownVictims between 300 and 500
order by Name asc;

#Amb el between els extrems estan inclosos (<= i >=)

#Un es el contrari de l'altre, quan es vol buscar la informació contrària sempre s'ha d'invertir tot el que tinguis

select Name
from Dragons
where KnownVictims < 300 or KnownVictims > 500
order by Name asc;

select Name
from Dragons
where KnownVictims not between 300 and 500
order by Name asc;

-- dates

select Nickname
from Knights
where BirthDate < '1200/01/01'
order by Nickname asc;

select Nickname
from Knights
where year(BirthDate) < 1200
order by Nickname asc;

select Nickname
from Knights
where BirthDate between '1190/01/01' and '1190/12/31'
order by Nickname asc;

