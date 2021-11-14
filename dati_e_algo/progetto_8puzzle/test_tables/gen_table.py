import random
import sys

def count_inversions(a):
  res = 0
  counts = [0]*(len(a)+1)
  rank = { v : i+1 for i, v in enumerate(sorted(a)) }
  for x in reversed(a):
    i = rank[x] - 1
    while i:
      res += counts[i]
      i -= i & -i
    i = rank[x]
    while i <= len(a):
      counts[i] += 1
      i += i & -i
  return res


try:
    lato = int(sys.argv[1])
except:
    lato = 3
    
board = [i for i in range(lato*lato)]
while True:
    random.shuffle(board)
    board = [int(i) for i in "0 1 3 4 2 5 7 8 6".split()]
    if lato%2 != 0 and count_inversions(board) % 2 == 0:
        break
    elif lato%2 == 0 and (count_inversions(board)+board.index(0)//lato) % 2 == 0:
        break
    
    
print(lato)
for i in board[:-1]:
    print(i, end=" ")
    
print(board[-1])
