from sys import stdin

n, m = list(map(int, stdin.readline().split()))
nList = list(map(int, stdin.readline().split()))
nList.insert(0, 0)
sum = [0]

for k in range(1, n+1):
    sum.append(sum[k-1] + nList[k])

for i in range(m):
    i, j = list(map(int, stdin.readline().split()))
    result: int = 0

    start = sum[j] - sum[i-1]
    print(start)
