
'''
 @author: Sokolov Maxim,  N.Novgorod
'''
# -*- coding: utf-8 -*-
from random import randint, seed

def cubic(k):
    return [0,1,2,decision[k]][randint(0,3)]
    
def DambaldorsHat(k1,k2):
    win=("01","12","20")
    res1=res2=0
    while (abs(res1-res2)<2):
        hand1=cubic(k1)
        hand2=cubic(k2)
        if (win.__contains__(str(hand1)+str(hand2))):
            res1+=1
        if (win.__contains__(str(hand2)+str(hand1))):
            res2+=1

    return k1 if (res1>res2) else k2

if __name__=="__main__":
    k=int(raw_input())
    n=2**k
    var={0:u"камень",1:u"ножницы",2:u"бумага"}  

    seed()
    decision=[randint(0,2) for i in xrange(n)]
    
    for i in xrange(n):
        print ( u"игрок"+str(i+1)+": "+var[decision[i]] )
    print
    
    part=range(n)
    for i in xrange(k):
        print ((u"Раунд"+str(i+1)+":") if (i+1<k) else u"Финал:")
        for j in xrange(len(part)/2):
            k1=part[j]
            k2=part[j+1]
            res=DambaldorsHat(k1,k2)
            print(u"игрок"+str(k1+1)+" vs "+u"игрок"+str(k2+1)+u" - победил игрок"+str(res+1)+"!")
            del part[(j+1) if (res==k1) else j ]
        print
        
        
    
    
