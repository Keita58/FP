-- Saber si l'habitació 15 està disponible el dia de nadal (2021)
select if(bc.BookedDate is not null, 'No està disponible', 'Està disponible') 
from Rooms r 
join BookingCalendar bc on r.RoomNumber = bc.AssignedRoom 
where r.RoomNumber = 15 and bc.BookedDate = date('2023-12-24')

-- Una llista dels clients que han fet més d'una reserva a l'hotel
select c.FirstName, c.LastName 
from Customers c 
join Bookings b on c.Id = b.CustomerId 
group by c.FirstName, c.LastName 
having count(b.ReservationDateTime) > 1

-- Saber quantes habitacions dobles amb bany privat estan ocupades per les nits del 7 al 10 de desembre (2023)
select count(distinct r.RoomNumber)
from Rooms r 
join RoomTypes rt on r.RoomTypeId = rt.Id 
join BookingCalendar bc on bc.AssignedRoom = r.RoomNumber 
where rt.Name like 'Double room with private bathroom' and bc.BookedDate between '2023-12-7' and '2023-12-9'

-- Calcular el preu mitjà de tots els tipus d'habitacions per la temporada de primavera de 2024
select avg(ps.Price), rt.Name 
from RoomTypes rt 
join PriceSeasons ps on ps.RoomTypeId = rt.Id 
join Seasons s on ps.SeasonId = s.Id 
where s.Name like 'Spring 2024'
group by rt.Name 
order by rt.Id 

-- Quines habitacions estan ocupades la nit del 31 de desembre (2023)
select r.RoomNumber 
from Rooms r  
left join BookingCalendar bc on r.RoomNumber = bc.AssignedRoom 
where bc.BookedDate = '2023-12-31'

-- Quan costa la nit de reis (05/01/2024) a una Suite?
select ps.Price  
from PriceSeasons ps 
join RoomTypes rt on ps.RoomTypeId = rt.Id 
join Seasons s on s.Id = ps.SeasonId 
where rt.Name = 'Suite' and s.StartingDay <= '2024-01-05'
order by s.StartingDay desc 
limit 1

