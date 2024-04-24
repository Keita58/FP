Towns(**Name**, Population)

Schools(**Code**, TownName, Name)
on TownName referencia Towns(Name)

Children(**Code**, SchoolCode, TownName, FirstName, LastName, Phone)
on SchoolCode referencia Schools(Code)
i on TownName referencia Towns(Name)

Activities(**Name**)

SchoolActivities(***ActivitiesName***, ***SchoolCode***, Score, Capacity)
on ActivitiesName referencia Activities(Name)
i on SchoolCode referencia Schools(Code)