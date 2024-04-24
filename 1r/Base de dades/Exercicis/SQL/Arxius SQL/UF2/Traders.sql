-- Mostra quantes estades (Stays) hi ha en total.
select count(*)
from Stays;

-- Mostra el nom de tots els productes (Products) el nom dels quals conté una 'z' (en majúscula o minúscula).
select Name 
from Products 
where Name like '%z%';

-- Mostra l'identificador i el nom de totes les naus (Ships) de càrrega (Cargo ship). Ordena els resultats per l'identificador.
select Id, Name 
from Ships
where `Type` like 'Cargo Ship'
order by id;

-- Mostra el nom i cognom de tots els mercaders (Traders) el cognom dels quals és alfabèticament més gran que "L". Ordena els resultats alfabèticament pel cognom.
select Firstname, Lastname 
from Traders 
where Lastname > 'L%'
order by Lastname asc;

-- Mostra el sobrenom, nom i cognom de tots els mercaders (Traders) que no hagin mort abans de retirar-se (no tenen data de defunció a la BD), i que el seu sobrenom, nom o cognom comenci per la lletra 'C'. Ordena els resultats alfabèticament pel sobrenom.
select Nickname, Firstname, Lastname 
from Traders
where DeceaseDate is null and (Nickname like 'C%' or Firstname like 'C%' or Lastname like 'C%') 
order by Nickname asc;

-- Mostra l'identificador, el nom i el tipus de totes les naus (Ships) que tenen una capacitat inferior o igual a 100, i que tenen un espai en el tercer caràcter del seu nom. Ordena els resultats per l'identificador.
select Id, Name, `Type` 
from Ships 
where Capacity <= 100 and Name like '__ %'
order by id;

-- Mostra totes les línies de transaccions (taula TradeLines) que impliquen algun dels productes amb identificadors 1, 5, 9, 14 o 18, i 100 unitats o més. Ordena els resultats pel preu unitari, de major a menor.
select *
from TradeLines
where ProductId in ('1', '5', '9', '14', '18') and Quantity >= 100
order by UnitPrice desc;

-- Volem trobar l'identificador de les naus que han estat comprades per un dels mercaders almenys una vegada (taula ShipOwners). Ordena els resultats per aquest identificador, i assegura't que no apareixen identificadors repetits.
select distinct ShipId 
from ShipOwners
where AcquisitionCause like 'Bought'
order by ShipId;

-- Mostra totes les línies de ShipOwners que compleixen que el cost d'adquisició de la nau és més d'un 10% superior al benefici obtingut al despendre-se'n. No incloguis les files per les quals el benefici obtingut és 0. Ordena els resultats per la data d'adquisició.
select *
from ShipOwners
where ((AcquisitionPrice/LostBenefit)-1)*100 > 10 and LostBenefit != 0
order by AcquisitionDate;

#--------------------------------------------------------------------------------------------------------------
-- Mostra els sobrenoms, ordenats alfabèticament, de tots els mercaders que han obtingut més de cent milions de crèdits en la venta dels seus productes.
select t.Nickname 
from Traders t 
join Stays s on s.TraderId = t.Id 
join Trades t2 on s.Id = t2.StayId 
where t2.`Type` like 'Sell'
group by t.Id, t.Nickname 
having sum(t2.Total) > 100000000
order by t.Nickname asc

-- Mostra el codi, el nom, i la quantitat de planetes que tenen, d'aquells sistemes solars que tenen més d'un planeta. Ordena la llista de més a menys planetes, i en cas d'empat pel codi del sistema.
select ss.Code, ss.Name, count(p.Id)
from Planets p
join SolarSystems ss on ss.Id = p.SystemId 
group by ss.Code, ss.Name 
having count(p.Id) > 1
order by 3 desc, ss.Code 

-- Per a cada sistema solar, mostra una llista separada per comes, i ordenada alfabèticament, de tots els planetes (del sistema que siguin) el nom dels quals comença per la mateixa lletra que el nom del sistema solar. Cal mostrar el codi del sistema, el nom del sistema, i la llista especificada. Ordena els resultats pel codi del sistema solar.
select ss.Code, ss.Name, GROUP_CONCAT(p.Name order by p.Name asc) 
from SolarSystems ss, Planets p  
where SUBSTRING(ss.Name,1,1) like SUBSTRING(p.Name,1,1) 
group by ss.Code, ss.Name
order by ss.Code

-- Mostra l'identificador i nom dels productes que no han estat disponibles enlloc, és a dir, pels quals no existeix cap canvi de preu.
select p.Id, p.Name 
from Products p 
left join PriceChanges pc on pc.ProductId = p.Id 
where pc.ProductId is null

-- Quant valia el producte Divine Gravity Mine abans del 15 de juny de 2215 al planeta Doiclite?
select pc.NewPrice 
from Products p 
join PriceChanges pc on pc.ProductId = p.Id 
join Planets p2 on p2.Id = pc.PlanetId 
where p.Name like 'Divine Gravity Mine' and p2.Name like 'Doiclite' and pc.`Date` < '2215-06-15' and pc.`Date` >= (
	select max(pc.`Date`)
	from Products p 
	join PriceChanges pc on pc.ProductId = p.Id 
	join Planets p2 on p2.Id = pc.PlanetId 
	where pc.`Date` < '2215-06-15' and p.Name like 'Divine Gravity Mine' and p2.Name like 'Doiclite');

-- Volem obtenir una llista de les distàncies entre tots els sistemes solars. De cara parella de sistemes en volem el nom i codi de cadascun i la distància que els separa. La llista estarà ordenada per proximitat. No volem la distància entre un sistema i ell mateix (que sempre és 0), ni que apareguin diverses vegades la distància entre la mateixa parella de sistemes. Per evitar duplicitats i ambigüitats, per cada parella de sistemes, mostrarem primer el que tingui el nom alfabèticament més petit.
select distinct ss.Name as planeta1Nom, ss.Code as planeta1Codi, ss2.Name as planeta2Nom, ss2.Code as planeta2Codi, ssd.Distance 
from SolarSystems ss
join SolarSystems ss2
join SolarSystemsDistances ssd on ssd.SystemId1 = ss.Id and ssd.SystemId2 = ss2.Id
where ss.Code not like ss2.Code
order by ssd.Distance 

-- Volem cercar qui era el propietari de cada nau l'1 de gener de 2300. Mostra l'identificador i nom de les naus, i el sobrenom dels mercaders propietaris. Ordena els resultats per l'identificador de la nau, i el sobrenom dels mercaders.
select s.Id, s.Name, t.Nickname 
from Traders t 
join ShipOwners so on t.Id = so.TraderId 
join Ships s on s.Id = so.ShipId 
where so.AcquisitionDate <= '2300-01-01' and so.LostDate > '2300-01-01'
order by s.Id, t.Nickname 

-- Mostra l'identificador i el sobrenom dels mercaders, i el benefici que han obtingut amb la compravenda de naus, dels mercaders que han obtingut algun benefici, és a dir, que el balanç entre guanys i pèrdues per aquest concepte els surt positiu. Ordena els resultats de més a menys benefici.
select t.Id, t.Nickname, sum(so.LostBenefit)-sum(so.AcquisitionPrice)
from Traders t 
join ShipOwners so on t.Id = so.TraderId 
group by t.Id, t.Nickname 
having (sum(so.LostBenefit)-sum(so.AcquisitionPrice)) > 0
order by 3 desc

-- Mostra una llista amb tots els planetes i la quantitat de visites de mercaders que ha rebut cadascun d'ells. Dels planetes mostra'n l'identificador i el nom, i ordena els resultats per l'identificador dels planetes.
select p.Id, p.Name, count(s.Id)
from Planets p 
left join Stays s on p.Id = s.PlanetId 
left join Traders t on s.TraderId = t.Id 
group by p.Id, p.Name 
order by p.Id 

-- Mostra una llista dels mercaders (identificador i sobrenom) i els anys que, en total, han passat en algun planeta. Ordena els resultats de menys a més temps.
select t.Id, t.Nickname, sum(DATEDIFF(s.EndDate, s.StartDate)/365) 
from Traders t 
join Stays s on t.Id = s.TraderId 
join Planets p on s.PlanetId = p.Id 
group by t.Id, t.Nickname 
order by 3 asc

#----------------------------------------------------------------------------------------------------------------
-- Mostra totes les dades personals del nostre trader (o traders) que més dies hagi estat de viatge (stays).
select t.Id, t.Nickname, t.Firstname, t.Lastname 
from Traders t 
join Stays s on t.Id = s.TraderId 
group by t.Id, t.Nickname, t.Firstname, t.Lastname 
having sum(DATEDIFF(s.EndDate, s.StartDate)) = (
	select sum(DATEDIFF(s2.EndDate, s2.StartDate))
	from Stays s2
	group by s2.TraderId
	order by 1 DESC
	limit 1)
	
-- Mostra les 11 parelles de sistemes solars que estiguin més a prop l'un de l'altre ordenades de menor a mayor distància. S'ha de mostrar tot en una única columna en una frase que inclogui el nom dels dos sistemes i la distancia que els separa. Per exemple "De Grif a Akaers hi ha 1,34 anys llum" (podrien ser més d'11)
select CONCAT('De ', ss.Name, ' a ', ss2.Name, ' hi ha ', ssd.Distance, ' anys llum') 
from SolarSystems ss 
join SolarSystems ss2 
join SolarSystemsDistances ssd on ss.Id = ssd.SystemId1 and ss2.Id = ssd.SystemId2 
where ssd.Distance in (select * from (
	select ssd2.Distance
	from SolarSystemsDistances ssd2
	order by 1 ASC
	limit 11) as distancies)
order by ssd.Distance ASC, ss.Name

-- Quin ha estat el planeta (o planetes) que més vegades ha visitat el trader "Roys Vedstra" ? Cal mostrar el nom del planeta i el nombre de visites que hi va fer el Roys.
select p.Name, count(s.Id)
from Traders t 
join Stays s on t.Id = s.TraderId 
join Planets p on s.PlanetId = p.Id 
where t.Firstname like 'Roys' and t.Lastname like 'Vedstra'
group by p.Name
having count(s.Id) = (
	select count(s2.Id)
	from Traders t2 
	join Stays s2 on t2.Id = s2.TraderId 
	join Planets p2 on s2.PlanetId = p2.Id 
	where t2.Firstname like 'Roys' and t2.Lastname like 'Vedstra'
	group by s2.PlanetId
	order by 1 desc
	limit 1)

-- Calcular quants diners va gastar cada trader en comprar naus entre els anys 2.260 i 2.275 (inclosos) . Cal mostrar el nickname de cada trader i la suma total gastada (sense decimals) per cadascun en aquest període ordenats alfabèticament pel Nickname.
select t.Nickname, round(sum(so.AcquisitionPrice))
from Traders t 
left join ShipOwners so on so.TraderId = t.Id 
where year(so.AcquisitionDate) between 2260 and 2275 and so.AcquisitionCause like 'Bought'
group by t.Nickname 
order by t.Nickname 

-- A quin preu diari li va sortir la possessió de cadascuna de les naus que va tenir la nostra trader "Eriska Hordell"? Cal mostrar l'id i nom de cada nau amb el seu preu diari amb dues xifres decimals ordenats de major a menor preu.
select so.ShipId, s.Name, TRUNCATE(((so.AcquisitionPrice-so.LostBenefit)/(DATEDIFF(so.LostDate, so.AcquisitionDate))), 2) as PreuDiari
from Traders t 
join ShipOwners so on so.TraderId = t.Id 
join Ships s on s.Id = so.ShipId 
where t.Firstname like 'Eriska' and t.Lastname like 'Hordell'
order by PreuDiari desc

-- Quin és el sistema (o sistemes) solars que tenen més planetes? Cal mostrar id, codi i nom del sistema.
select ss.Id, ss.Code, ss.Name 
from SolarSystems ss 
join Planets p on p.SystemId = ss.Id 
group by ss.Id, ss.Name, ss.Code 
having count(p.Id) = (
	select count(p2.Id)
	from SolarSystems ss2
	join Planets p2 on p2.SystemId = ss2.Id
	group by ss2.Id
	order by 1 DESC
	limit 1)
	
-- Quina és l'última nau que ha robat (stolen) la "Sabrya Mapother"? Cal mostrar totes les dades de la nau (o naus). Podria haver robat més d'una nau el mateix dia.
select s.Id, s.Name, s.`Type`, s.Capacity 
from ShipOwners so 
join Ships s on so.ShipId = s.Id 
join Traders t on so.TraderId = t.Id 
where t.Firstname like 'Sabrya' and t.Lastname like 'Mapother' and so.AcquisitionCause like 'Stolen' and so.AcquisitionDate = (
	select so2.AcquisitionDate
	from ShipOwners so2
	join Traders t2 on so2.TraderId = t2.Id
	where t2.Firstname like 'Sabrya' and t2.Lastname like 'Mapother' and so2.AcquisitionCause like 'Stolen'
	order by 1 DESC
	limit 1)

-- Quantes naus s'han robat (stolen) cada any ? Cal mostrar exclusivament l'any amb 4 xifres i el nombre de naus robades ordenat per l'any.
select year(so.AcquisitionDate), count(so.ShipId)
from ShipOwners so 
where so.AcquisitionCause like 'Stolen' and char_length(convert(year(so.AcquisitionDate), CHAR)) = 4
group by year(so.AcquisitionDate) 
order by 1

-- Mostrar totes les dades del productes que mai han canviat de preu ordenades pel nom del producte.
select p.Id, p.Name 
from Products p 
where p.Id not in (
	select distinct p2.Id
	from Products p2
	join PriceChanges pc on pc.ProductId = p2.Id)
order by p.Name 

-- Quina és la durada mitjana (en dies) de les estades als planetes de cadascun dels nostres traders? Cal mostrar el nickname del trader i la seva mitjana en dies sense xifres decimals ordenades de major a menor permanència.
select t.Nickname, round(sum(DATEDIFF(s.EndDate, s.StartDate))/count(s.Id))
from Traders t 
join Stays s on s.TraderId = t.Id 
group by t.Nickname 
order by 2 desc

-- Mostrar totes les dades personals del primer trader que va morir. En podrien haver mort més d'un el mateix dia.
select t.Id, t.Nickname, t.Firstname, t.Lastname, t.DeceaseDate 
from Traders t 
where t.DeceaseDate = (
	SELECT min(t2.deceaseDate)
	from Traders t2)

#--------------------------------------------------------------------------------------------------------------
-- Quantes naus (ships) tenim que no hagin estat mai propietat de cap comerciant (trader)?
select count(*) 
from Ships s 
where s.Id not in (
	select distinct s2.Id
	from Ships s2
	join ShipOwners so on so.ShipId = s2.Id
	order by s2.Id asc)

-- Quin o quins traders no han visitat mai el planeta "Ofradus" ? Cal mostrar el cognom i el nom d'aquests traders ordenats alfabèticament pel cognom.
(select t.Lastname, t.Firstname
from Traders t)
except
(select distinct t.Lastname, t.Firstname
from Traders t 
join Stays s on s.TraderId = t.Id 
where s.PlanetId in (
	select p2.Id
	from Planets p2
	where p2.Name like 'Ofradus'))
order by 1

-- Quins sistemes solars tenen més de 2 planetes? Cal mostrar l’id, codi i nom dels sistemes.
select ss.Id, ss.Code, ss.Name 
from SolarSystems ss 
join Planets p on p.SystemId = ss.Id 
group by ss.Id, ss.Code, ss.Name
having count(p.Id) > 2

-- Quin o quins sistemes solars tenen menys planetes? Cal mostrar l’id, codi i nom dels sistemes ordentas pel nom.
select ss.Id, ss.Code, ss.Name
from SolarSystems ss 
join Planets p on p.SystemId = ss.Id 
group by ss.Id, ss.Code, ss.Name 
having count(p.Id) = (
	select count(p2.Id)
	from SolarSystems ss2
	join Planets p2 on p2.SystemId = ss2.Id 
	group by ss2.Id, ss2.Code, ss2.Name
	order by 1 asc 
	limit 1)
order by ss.Name 

-- Quines naus es van adquirir (per qualsevol causa) el mateix dia que el Billis Droogan va comprar la seva última nau? (cal mostrar totes les dades de las naus)
select s.Id, s.Name, s.`Type`, s.Capacity 
from Traders t 
join ShipOwners so on so.TraderId = t.Id 
join Ships s on so.ShipId = s.Id 
where so.AcquisitionDate = (
	select so2.AcquisitionDate
	from Traders t2
	join ShipOwners so2 on t2.Id = so2.TraderId
	where t2.FirstName like 'Billis' and t2.LastName like 'Droogan' and so2.AcquisitionCause like 'Bought'
	order by 1 desc
	limit 1)

-- La policia estelar està fent el rànking dels més lladres dels nostres traders. Volem saber totes les dades dels traders que han robat les dues quantitats més altes de naus ordenades per la clau primària.
select t.Id, t.Nickname, t.Firstname, t.Lastname, t.DeceaseDate 
from Traders t 
join ShipOwners so on so.TraderId = t.Id 
where so.AcquisitionCause like 'Stolen'
group by t.Id, t.Nickname, t.Lastname, t.Firstname, t.DeceaseDate 
having count(so.Id) in (select * from(
	select count(so2.Id)
	from ShipOwners so2
	join Traders t2 on so2.TraderId = t2.Id
	where so2.AcquisitionCause like 'Stolen'
	group by t2.Id
	order by 1 desc
	limit 2) t)
order by t.Id

-- Quins son els planetes que mai han estat visitat per cap dels nostres traders? Cal mostrar id i nom dels planetes.
select p.Id, p.Name 
from Planets p  
where p.Id not in (
	select distinct p2.Id 
	from Stays s2
	join Traders t on s2.TraderId = t.Id
	join Planets p2 on p2.Id = s2.PlanetId
	order by 1)

-- Quants dies ha passat de viatge el trader (o traders) que més temps han estat viatjant?
select sum(DATEDIFF(s.EndDate, s.StartDate))
from Stays s 
group by s.TraderId
order by 1 DESC 
limit 1

-- Quin o quins són els productes que mès vegades han canviat de preu? Cal mostrar el nom d'aquests productes i quantes vegades han canviat de preu
select p.Name, count(pc.Id)
from Products p 
join PriceChanges pc on pc.ProductId = p.Id 
group by p.Name
having count(pc.Id) = (
	select count(pc2.Id)
	from PriceChanges pc2
	group by pc2.ProductId
	order by 1 desc
	limit 1)

-- Quant es van gastar en total en naus els nostres traders l'any 2.283?
select sum(so.AcquisitionPrice)
from ShipOwners so 
where year(so.AcquisitionDate) = '2283'

-- La hisenda estelar està buscant els traders que més naus han heretat per cobrar-los els impostos de transmission patrimonials. Volem saber id, nom i cognom i quantes naus han heretat els traders que hagin heretat les dues xifres més altes de naus.
select t.Id, t.Firstname, t.Lastname, count(so.Id)
from Traders t 
join ShipOwners so on so.TraderId = t.Id 
where so.AcquisitionCause like 'Inherited'
group by t.Id, t.Lastname, t.Firstname
having count(so.Id) in (select * from(
	select distinct count(so2.Id)
	from ShipOwners so2
	join Traders t2 on so2.TraderId = t2.Id
	where so2.AcquisitionCause like 'Inherited'
	group by t2.Id
	order by 1 desc
	limit 2) t)

-- Quantes naus s'han perdut destruïdes (destroyed) cada any? Cal mostrar l'any amb 4 dígits i el nombre de naus.
select year(so.LostDate), count(so.ShipId)
from ShipOwners so 
where so.LostCause like 'Destroyed'
group by year(so.LostDate) 

-- Mostrar l'id, el nom de les nostres naus (ships) i quants propietaris diferents han tingut d'aquelles que hagin tingut propietari durant més de 50.000 dies. Cal mostrar l'id i el nom de la nau, el nombre de propietaris i el nombre de dies ordenats de major a menor nombre de dies.
select s.Id, s.Name, count(so.TraderId), sum(DATEDIFF(so.LostDate, so.AcquisitionDate))
from ShipOwners so 
join Ships s on so.ShipId = s.Id 
group by s.Id, s.Name 
having sum(DATEDIFF(so.LostDate, so.AcquisitionDate)) > 50000
order by 4 desc

-- Quina és la nau (o naus) més petites que s'han robat? Cal mostrar totes les dades de les naus.
select s.Id, s.Name, s.`Type`, s.Capacity 
from Ships s 
join ShipOwners so on so.ShipId = s.Id 
where so.AcquisitionCause like 'Stolen' and s.Capacity = (
	select min(s2.Capacity)
	from Ships s2)
	
-- Quin és el nostre trader (o traders) que més dies ha passat de viatge? Cal mostrar el seu nickname i nombre de dies total d’estada.
select t.Nickname, sum(DATEDIFF(s.EndDate, s.StartDate))
from Stays s 
join Traders t on s.TraderId = t.Id 
group by t.Nickname
having sum(DATEDIFF(s.EndDate, s.StartDate)) = (
	select sum(DATEDIFF(s2.EndDate, s2.StartDate))
	from Stays s2
	join Traders t2 on t2.Id = s2.TraderId
	group by t2.Id
	order by 1 DESC
	limit 1)

-- Quin és el planeta en què més dies han passat els nostres traders (entre tots). Cal mostrar ´'id i el nom del planeta i el nom del seu sistema solar. Podrien ser-ne més d'un.
select p.Id, p.Name, ss.Name as Sistema
from Planets p 
join Stays s on p.Id = s.PlanetId 
join SolarSystems ss on p.SystemId = ss.Id 
group by p.Id, p.Name, ss.Id,ss.Name 
having sum(DATEDIFF(s.EndDate, s.StartDate)) = (
	select sum(DATEDIFF(s2.EndDate, s2.StartDate))
	from Planets p2
	join Stays s2 on p2.Id = s2.PlanetId
	group by p2.Id
	order by 1 DESC
	limit 1)

#------------------------------------------------------------------------------------------------
-- Troba el codi i el nom de tots els sistemes solars el nom dels quals té una longitud d'exactament 6 lletres. Ordena els resultats alfabèticament pel codi.
select ss.Code, ss.Name 
from SolarSystems ss 
where CHAR_LENGTH(ss.Name) = 6
order by ss.Code asc

-- Troba el nom dels dos sistemes solars i la distància a la qual estan, dels dos sistemes que es troben més lluny un de l'altre que qualsevol altra parella de sistemes.
select ss.Name, ss2.Name, ssd.Distance 
from SolarSystemsDistances ssd 
join SolarSystems ss on ss.Id = ssd.SystemId1 
join SolarSystems ss2 on ss2.Id = ssd.SystemId2 
where ssd.Distance = (select max(ssd2.Distance) from SolarSystemsDistances ssd2)

-- Mostra una llista amb el codi i nom de tots els sistemes solars i la quantitat de planetes que cada sistema solar té a la base de dades. Volem obtenir la llista ordenada per aquesta quantitat, de més a menys. En cas d'empat, ordena'ls pel codi.
select ss.Code, ss.Name, count(p.Id)
from SolarSystems ss 
join Planets p on ss.Id = p.SystemId 
group by ss.Code, ss.Name
order by 3 desc, ss.Code

-- Cerca la proporció de mercaders que han mort abans de retirar-se respecte al total de mercaders. El resultat ha de ser un nombre entre 0 i 1 (0 voldria dir que cap mercader ha mort, 1 que han mort tots, i 0.5 que han mort la meitat).
select count(t.Id)/(select count(t2.Id) from Traders t2)
from Traders t
where t.DeceaseDate is not NULL 


-- Volem obtenir una llista amb tots els tipus de naus que hi ha, i la capacitat mitjana de cadascun dels tipus. Només s'han de mostrar els tipus pels quals la capacitat mitjana sigui superior a 10, i s'han de mostrar els resultats ordenats per la capacitat, de més a menys.
select s.`Type`, avg(s.Capacity)
from Ships s 
group by s.`Type` 
having avg(s.Capacity) > 10
order by 2 desc

-- Volem saber el mercader que ha tingut més naus (al llarg del temps, no en un moment donat). Mostra el sobrenom del mercader i la quantitat de naus que ha tingut. Si n'hi ha més d'un empatats en aquest màxim, els volem tots, ordenats alfabèticament pel sobrenom.
select t.Nickname, count(so.ShipId)
from Traders t 
join ShipOwners so on so.TraderId = t.Id 
group by t.Nickname 
having count(so.ShipId) = (
	select count(so2.ShipId)
	from Traders t2
	join ShipOwners so2 on so2.TraderId = t2.Id
	group by t2.Id
	order by 1 DESC
	limit 1)
order by t.Nickname 

-- Volem saber el preu més baix que ha assolit cadascun dels productes. Mostra una llista amb el nom de tots els productes, el seu preu mínim, la data en què s'ha donat aquest preu mínim, i el nom del planeta on s'ha donat. Ordena la llista alfabèticament pel nom del producte.
select p.Name as Producte, pc.NewPrice, pc.`Date`, p2.Name
from PriceChanges pc 
join Products p on p.Id = pc.ProductId 
join Planets p2 on p2.Id = pc.PlanetId 
where pc.NewPrice = (select min(pc2.NewPrice) from PriceChanges pc2 where pc2.ProductId = pc.ProductId)
order by p.Name 

-- Mostra el sobrenom del mercader, el nom del planeta, i la quantitat de dies de l'estada més llarga que s'ha fet. Pots suposar que no hi ha empats en la durada de l'estada més llarga.
select t.Nickname, p.Name, DATEDIFF(s.EndDate, s.StartDate)  
from Traders t 
join Stays s on s.TraderId = t.Id 
join Planets p on p.Id = s.PlanetId 
order by 3 desc 
limit 1

-- Mostra, en dues files, el total de diners gastats en compres de productes, i el total de diners obtinguts gràcies a les ventes de productes.
(select sum(t.Total)
from Trades t  
where t.`Type` like 'Buy')
UNION 
(select sum(t.Total)
from Trades t  
where t.`Type` like 'Sell')

-- Mostra una llista amb tots els productes i la quantitat d'unitats que s'ha venut de cadascun d'ells. Tingues en compte que si d'un producte no se n'ha venut cap unitat, volem que surti un 0. De cada producte volem el seu identificador, el seu nom, i la quantitat total d'unitat venudes. Ordena els resultats per l'identificador del producte.
select p.Id, p.Name, IFNULL(sum(tl.Quantity), 0)
from Products p 
left join TradeLines tl on tl.ProductId = p.Id 
group by p.Id, p.Name 
order by p.Id 