inp = input().split()
n = list(inp[0])
b = int(inp[1])

result = 0
for i in range(len(n)):
    if ord(n[i]) > 64:
        result += (ord(n[i]) - 55) * (b ** (len(n)-i-1))
    else:
        result += int(n[i]) * (b ** (len(n)-i-1))

print(result)
