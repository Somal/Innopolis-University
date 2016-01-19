from math import sqrt

def decision(a,b,c):
    d=b*b-4*a*c 
    if ( (a,b,c)==(0,0,0)):
        print("Infinity of answers exists.")
    elif ( (a==0) & (b!=0) ):
        print("One answer exists:")
        print(-c/b)
    elif ((a,b)==(0,0)):
        print("Answer doesn't exist .")
    elif (d<0):
        print("Answer doesn't exist in real area.")
    elif (d==0):
        print("One answer exists:")
        print(-b/(2*a))
    else:
        print("Two answers exist:")
        print((-b+sqrt(d))/(2*a))
        print((-b-sqrt(d))/(2*a))     
    
if __name__ == '__main__':
    a,b,c=map(int,raw_input().split())
    #print(a,b,c)
    decision(a,b,c)  