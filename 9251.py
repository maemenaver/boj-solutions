# 가장 긴 공통 부분 수열(LCS) 구하기

a = input()
b = input()
na, nb = len(a), len(b)

v = [[0] * (na+1) for _ in range(nb+1)]

for i in range(1, nb+1):
    for j in range(1, na+1):
        if a[j-1] == b[i-1]:
            v[i][j] = v[i-1][j-1]+1
        else:
            v[i][j] = max(v[i-1][j], v[i][j-1])

print(v[nb][na])
