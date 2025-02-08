def func():
    text = input()
    while text != "FIN":
        exc1 = text.count('¡')
        exc2 = text.count('!')
        if(exc1 == exc2):
            print("SI")
        else:
            print("NO")
        text = input()

if __name__ == "__main__":
    func()