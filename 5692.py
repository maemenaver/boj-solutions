from sys import stdin

facCache = [0, 1, 2, 6, 24, 120]

while True:
    case = list(stdin.readline())
    case.pop(-1)
    if (case[0] == '0'):
        break

    result: int = 0
    length: int = len(case)

    for i in range(length):
        result += int(case[i]) * facCache[length-i]

    print(result)
