def vocals():
    casos = int(input())
    for i in range(casos):
        vocals = [0, 0, 0, 0, 0]
        paraula = input()
        paralesSeparades = paraula.split(r'[ ,]')
        for x in paralesSeparades:
            lletres = list(x)
            for y in lletres:
                y = y.lower()
                if y == 'a':
                        vocals[0] = vocals[0] + 1
                elif y == 'e':
                        vocals[1] = vocals[1] + 1
                elif y == 'i':
                        vocals[2] = vocals[2] + 1
                elif y == 'o':
                        vocals[3]    = vocals[3] + 1
                elif y == 'u':
                        vocals[4] = vocals[4] + 1

        print("A: " + str(vocals[0]) + " E: " + str(vocals[1]) + " I: " + str(vocals[2]) + " O: " + str(vocals[3]) + " U: " + str(vocals[4]))


if __name__ == '__main__':
    vocals()
