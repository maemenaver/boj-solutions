
from typing import List


class Node:
    def __init__(self, name: str):
        self.name = name
        self.child = []

    def __lt__(self, other):
        return self.name < other.name

    def insert(self, _nameList: List[str]):
        try:
            nameList = _nameList
            valid = nameList.pop(0)
            if (valid != self.name):
                return False
            if (len(nameList) == 0):
                return False

            name = nameList[0]
            nodeChild = self.exist(name)
            if (nodeChild == False):
                self.child.append(Node(name))
                self.child.sort()

            nodeChild = self.exist(name)
            if (len(nameList) > 1):
                nodeChild.insert(nameList)
        except:
            raise AssertionError

    def exist(self, name: str):
        for i in self.child:
            if (i.name == name):
                return i
        return False

    def print(self, value: str = ""):
        print(f'{value}{self.name}')
        for i in self.child:
            i.print(f'{value}--')


n = int(input())
node: List[Node] = []

for i in range(n):
    inp = list(input().split())
    nodes = inp[1:]

    isFind = False
    for currentNode in node:
        if (currentNode.name != nodes[0]):
            continue
        else:
            isFind = currentNode
            break

    if (isFind == False):
        node.append(Node(nodes[0]))
        isFind = node[-1]
        node.sort()

    isFind.insert(nodes)

for currentNode in node:
    currentNode.print()
