-- Pregunta 1
select r.RoomNumber, r.RoomTypeId, h.FirstName, h.LastName 
from Rooms r 
join Stays s on r.RoomNumber = s.RoomNumber 
join StayHosts sh on s.Id = sh.StayId 
join Hosts h on h.Id = sh.HostId 
where s.CheckIn <= '2022-12-25' and s.CheckOut > '2022-12-25'
order by r.RoomNumber, h.Id 

-- Pregunta 2
(select c.Id, c.FirstName, c.LastName 
from Customers c
where c.Nationality like 'French')
EXCEPT
(select c.Id, c.FirstName, c.LastName 
from Customers c   
join Bookings b on b.CustomerId = c.Id  
where c.Nationality like 'French')
order by LastName, FirstName
#-------------------------------------------------

select c.Id, c.FirstName, c.LastName 
from Customers c
left join Bookings b on c.Id = b.CustomerId 
where b.CustomerId is null and c.Nationality like 'French'
order by c.LastName, c.FirstName

-- Pregunta 3
select r.RoomNumber, rt.Name, sum(s.TotalPrice)
from Rooms r 
join RoomTypes rt on r.RoomTypeId = rt.Id 
join Stays s on s.RoomNumber = r.RoomNumber  
where s.PaymentDateTime between '2023-01-01' and '2023-12-31'
group by r.RoomNumber, rt.Name
having sum(s.TotalPrice) > 25000
order by 3 desc

-- Pregunta 4
select rt.Name, sum(ps.Price)/count(s.Id)
from RoomTypes rt 
join PriceSeasons ps on rt.Id = ps.RoomTypeId 
join Seasons s on ps.SeasonId = s.Id 
where s.Name like '% 2022'
group by rt.Name 
order by 2 DESC 
limit 3

-- Pregunta 5
select r.RoomNumber, c.FirstName, c.LastName 
from Rooms r 
join BookingCalendar bc on bc.AssignedRoom = r.RoomNumber 
join Bookings b on bc.BookingId = b.Id 
join Customers c on b.CustomerId = c.Id 
where bc.BookedDate = '2023-12-31'
order by r.RoomNumber asc