package database;

import java.util.ArrayList;
import java.util.function.Function;

public class PartSorting<T> implements Runnable{
	ArrayList<T> al;
	Function<Object, Comparable> map;
	
	public <T> void partSort(ArrayList<T> al, Function<Object, Comparable> map) {
		for (int i = 0; i < al.size() - 1; i++)
			for (int j = i; j >= 0; j--)
				if ((int) al.get(j) > (int) al.get(j + 1)) {
					T tmp = al.get(j);
					al.set(j, al.get(j + 1));
					al.set(j + 1, tmp);
				}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		partSort(al,map);			
	}
	
	public  PartSorting(ArrayList<T> al, Function<Object, Comparable> map){
		this.al=al;
		this.map=map;
	}
}