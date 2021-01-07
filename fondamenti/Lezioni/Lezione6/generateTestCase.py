import sys
import random
import string

with open("testcase.txt", "w") as file:
    for i in range(int(sys.argv[1])):
        file.write(random.choice(string.ascii_letters))
