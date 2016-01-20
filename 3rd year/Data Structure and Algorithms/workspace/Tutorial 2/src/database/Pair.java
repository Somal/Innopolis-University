package database;

public class Pair<T1, T2> {
	T1 first;
	T2 second;
	
	public Pair(T1 t1, T2 t2){
		this.first=t1;
		this.second=t2;
	}
	
	public T1 first(){
		return first;		
	}
	
	public T2 second(){
		return second();
	}
	

}
