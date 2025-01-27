import math
def hora():
    hora = int(input())
    hores = math.floor((hora/3600)%24)
    minuts = (hora%3600) // 60
    segons = hora % 60
    print(str(hores) + ":" + str(minuts) + ":" + str(segons))

if __name__ == "__main__":
    hora()
