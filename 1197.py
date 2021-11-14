from typing import Dict
from sys import stdin
import copy

Infinity = 2147483648
Minimum = -2147483649

# w = [
#     [],
#     [None, 0,           1,          3,  Infinity,   Infinity],
#     [None, 1,           0,          3,  6,          Infinity],
#     [None, 3,           3,          0,  4,          2],
#     [None, Infinity,    6,          4,  0,          5],
#     [None, Infinity,    Infinity,   2,  5,          0]
# ]

nearest: Dict[int, int] = {}
distance: Dict[int, int] = {}


def prim(n: int, W: Dict[int, Dict[int, int]]):
    vnear: int = Infinity
    min: int
    result: int = 0

    for i in range(2, n+1):
        nearest[i] = 1
        if i in W[1]:
            distance[i] = W[1][i]
        else:
            if i in W:
                distance[i] = Infinity

    for _ in range(n):
        min = Infinity

        for i in distance.keys():
            if (Minimum < distance[i] and distance[i] < min):
                min = distance[i]
                vnear = i

        # print(Infinity)
        if min == Infinity:
            break
        result += min
        distance.pop(vnear)

        tmpW = copy.deepcopy(W[vnear])
        for i in tmpW:
            if not i in distance:
                W[vnear].pop(i)
                continue
            if (W[i][vnear] < distance[i]):
                distance[i] = W[i][vnear]
                nearest[i] = vnear

    return result


v, e = map(int, stdin.readline().split())
w: Dict[int, Dict[int, int]] = {}
for _ in range(e):
    a, b, c = map(int, stdin.readline().split())
    if not a in w:
        w[a] = {}
    if not b in w:
        w[b] = {}
    w[a][b] = c
    w[b][a] = c

print(prim(v, w))
