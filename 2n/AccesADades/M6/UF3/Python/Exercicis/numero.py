def numero():
    num1 = int(input())
    num2 = int(input())
    if str(num1)[:1] > str(num2)[:1]:
        print("A")
    else:
        print("B")

if __name__ == "__main__":
    numero()