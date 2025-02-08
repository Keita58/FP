def array():
    casos = int(input())
    for x in range(casos):
        nombres = int(input())
        nombresLlista = input()
        nombresLlista = nombresLlista.split(' ')
        buscar = input()
        print(nombresLlista.count(buscar))

if __name__ == "__main__":
    array()