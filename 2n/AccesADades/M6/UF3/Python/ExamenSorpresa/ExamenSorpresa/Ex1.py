string = input("Escriu: ").split()
llarg = 0
paraula = ""

for i in string:
    if len(i) > llarg:
        llarg = len(i)
        paraula = i

print(paraula)