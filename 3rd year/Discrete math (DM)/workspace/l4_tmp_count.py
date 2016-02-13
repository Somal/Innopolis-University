from math import sqrt

def simple(n):
    flag=True
    for i in xrange(2,int(sqrt(n))+1):
        if n%i==0:
            flag=False
            break
    return flag

for i in xrange(50):
    m=i*i-i+41
    print(i,m,simple(m))

