from sys import stdin

nk = {
    0: {
        # -1: 0,
        0: 1,
        1: 0
    },
    1: {
        # -1: 1,
        0: 0,
        1: 1
    }
}


def fibonacci(n: int):
    if (not n in nk):
        if (not n-2 in nk):
            fibonacci(n-2)
        if (not n-1 in nk):
            fibonacci(n-1)
        nk[n] = {
            # -1: nk[n-2][-1] + nk[n-1][-1],
            0: nk[n-2][0] + nk[n-1][0],
            1: nk[n-2][1] + nk[n-1][1]
        }


n = int(stdin.readline())
k = list()
big = 0
for _ in range(n):
    i = int(stdin.readline())
    k.append(i)
    if (big < i):
        big = i

fibonacci(big)
for i in k:
    print(f'{nk[i][0]} {nk[i][1]}')