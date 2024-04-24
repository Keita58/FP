-- Pregunta 1
select s.Id, s.CheckIn, s.CheckOut, s.TotalPrice, s.PaymentDateTime  
from Stays s 
where s.TotalPrice >= (select sum(s.TotalPrice)/count(s.Id) + 1000
from Stays s) and s.PaymentDateTime is not null
order by s.PaymentDateTime;

-- Pregunta 2
select s.RoomNumber, count(s.Id)
from Stays s 
group by s.RoomNumber 
having count(s.RoomNumber) > (select count(s.Id)
from Stays s 
where s.RoomNumber = 10)

-- Pregunta 3
(select distinct s.RoomNumber
from Stays s)
except
(select s.RoomNumber
from Stays s 
where s.CheckIn <= '2021-09-21' and s.CheckOut >= '2021-09-24')
order by 1

-- Pregunta 4
select h.Id, h.FirstName, h.LastName, count(s.Id) as Vegades
from Stays s 
join StayHosts sh on s.Id = sh.StayId 
join Hosts h on h.Id = sh.HostId 
group by h.Id, h.FirstName, h.LastName
having count(s.Id) > (select count(s.Id)
from Stays s 
join StayHosts sh on s.Id = sh.StayId 
join Hosts h on h.Id = sh.HostId 
where h.DocNumber like '74915979B')
order by Vegades desc, h.Id;

-- Pregunta 5
(select rt.Id as Identitat, rt.Name, if(rt.Id in (select distinct rt.id
from RoomTypes rt
left join Rooms r ON rt.Id = r.RoomTypeId 
left join Stays s on s.RoomNumber = r.RoomNumber 
where s.PaymentDateTime between '2022-05-06' and '2022-05-07'
group by rt.Id, rt.Name), (select sum(s.TotalPrice)
from RoomTypes rt2
left join Rooms r2 ON rt2.Id = r2.RoomTypeId 
left join Stays s on s.RoomNumber = r2.RoomNumber 
where s.PaymentDateTime between '2022-05-06' and '2022-05-07' and rt2.Id = rt.id
group by rt2.Id, rt2.Name), 0) as Quantitat
from RoomTypes rt 
left join Rooms r ON rt.Id = r.RoomTypeId 
left join Stays s on s.RoomNumber = r.RoomNumber 
group by rt.Id, rt.Name)
order by 3 desc
