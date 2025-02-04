nombres = input("Escriu diferents nombres (1-20): ").split()
llista = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

'''
for i in nombres:
    if llista[i] != "":
        print(llista[i])
        llista.update(llista[i].values() + 1)
    else:
        llista[i] = 1

vegades = 0
nombre = -10
for i in nombres:
    print(i, end=", ")
    if llista.__getitem__(llista, i) > vegades:
        vegades = llista.__getitem__(llista, i)
        nombre = i
print()
print(nombre)
'''

for i in nombres:
    llista[int(i)] += 1

max = 0
num = 0
'''
for i in llista:
    if llista[i] > 0:
        print(i)
        if llista[i] > max:
            max = llista[i]
            num = i
'''
for i in range(0, len(llista)):
    if llista[i] > 0:
        print(i)
        if llista[i] > max:
            max = llista[i]
            num = i

print(num)