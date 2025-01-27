def ordre():
    casos = int(input())
    for x in range(casos):
        frase = input()
        res = ''.join(sorted(frase))
        print(res)

if __name__ == '__main__':
    ordre()
