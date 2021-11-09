const beforeTime = new Date()

const fs = require('fs');

// let input = fs.readFileSync('/dev/stdin').toString().split('\n');
let input = '3 3\n1 2 1\n2 3 2\n1 3 3'.toString().split('\n')
// const [v, e] = input[1].split(' ')

const node = {};
let key = 0;

for (let i = 1; i < input.length; i++) {
    const [a, b, c] = input[i].split(' ').map(v => +v)

    if (!node[a]) node[a] = {}
    node[a][b] = c

    if (!node[b]) node[b] = {}
    node[b][a] = c

    if (!key) key = a
}

let mst = Infinity;

let collected = [key]
let banned = []
let mstCurrent = 0;
while (collected.length < Object.keys(node).length) {
    let mstKey = Infinity
    let mstValue = Infinity
    let counted = false;

    for (let k of collected) {
        if (banned.some(v => v == k)) continue
        for (let v in node[k]) {
            let numV = +v
            if (collected.some(selected => numV == selected)) {
                continue;
            }

            if (mstValue > node[k][v]) {
                mstKey = numV
                mstValue = node[k][v]
                counted = true;
            }
        }
        if (!counted) {
            banned.push(k)
        }
    }

    if (mstKey != Infinity) {
        collected.push(mstKey)
        mstCurrent += +mstValue
    }
}

mst = Math.min(mst, mstCurrent)

console.log(mst)

console.log(new Date().getTime() - beforeTime.getTime())