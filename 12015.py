from bisect import *

array = [0, ]
num = map(int, input())
inp = list(map(int, input().split()))

for x in inp:
    if array[-1] < x:
        array.append(x)
    else:
        value = bisect_left(array, x)
        array[value] = x

print(len(array)-1)
