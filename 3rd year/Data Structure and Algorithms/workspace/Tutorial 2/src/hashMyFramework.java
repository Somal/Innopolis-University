
/**
* @author Sokolov Maxim
* @email m.sokolov@innopolis.ru
* @date 29 September, 2015
* In accordance with the academic honor, I (________) certify that
* the answers here are my own work without copying of others and
* solutions from Internet or other sources."
*/

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

public class hashMyFramework {

	/**
	 * TODO implement your own HashMap class!
	 * 
	 * @param <K>
	 *            key type
	 * @param <V>
	 *            value type
	 */
	public static class MyMap<K, V> implements Map<K, V> {
		int size = 0; //size of non-null elements
		ArrayList<Pair<K, V>> al = new ArrayList<Pair<K, V>>(); //list with entries

		@Override
		public void clear() {
			al = new ArrayList<Pair<K, V>>();
			size = 0;
		}

		@Override
		public boolean containsKey(Object arg0) {
			int hash = hashCode(arg0);
			Pair<K, V> pair = al.get(hash);
			if (pair == null)
				return false;
			return pair.first.equals(arg0);
		}

		@Override
		public boolean containsValue(Object arg0) {
			// TODO Auto-generated method stub
			for (int i = 0; i < size(); i++) {
				Pair<K, V> pair = al.get(i);
				if (pair.second.equals(arg0)) {
					// break;
					return true;
				}
			}
			return false;
		}

		
		@Override
		public Set<java.util.Map.Entry<K, V>> entrySet() {
			// TODO Auto-generated method stub
			Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K, V>>();

			Iterator<Pair<K, V>> it = al.iterator();
			while (it.hasNext()) {
				set.add(it.next());
			}
			return set;
		}

		@Override
		public V get(Object arg0) {
			int hash = hashCode(arg0);//hashing
			if (hash >= al.size()) 
				return null;
			Pair<K, V> pair = al.get(hash);//getting element
			if (pair == null)
				return null;
			return al.get(hash).second;
		}

		@Override
		public boolean isEmpty() {
			return size == 0; //simple code
		}

		@Override
		public Set<K> keySet() {
			// TODO Auto-generated method stub
			Set<K> set = new HashSet<K>();
			for (int i = 0; i < size(); i++) {
				Pair<K, V> pair = al.get(i);
				if (pair != null)
					set.add(pair.first);
			}

			return set;
		}

		@Override
		public V put(K arg0, V arg1) {
			int hash = hashCode(arg0);
			resize(hash + 1); //increase size if it need
			V hashth = get(arg0); // previous element 
			al.set(hash, new Pair<K, V>(arg0, arg1));//put new element
			if (hashth == null) {
				size++;
				return null;
			} else 
				return hashth;
		}

		@Override
		public void putAll(Map<? extends K, ? extends V> arg0) {
			size += arg0.size();
			Collection<? extends V> values = arg0.values();
			Set<? extends K> keys = arg0.keySet();

			Iterator<? extends V> valuesIterator = values.iterator();
			Iterator<? extends K> keysIterator = keys.iterator();
			while (valuesIterator.hasNext()) {
				put(keysIterator.next(), valuesIterator.next());
			}

		}

		@Override
		public V remove(Object arg0) {
			size--;
			int hash = hashCode(arg0);
			V ans = get(arg0);
			al.set(hash, null);
			return ans;
		}

		@Override
		public int size() {
			return size;
		}

		@Override
		public Collection<V> values() {
			Collection<V> col = new ArrayList<V>();

			for (int i = 0; i < size(); i++) {
				Pair<K, V> pair = al.get(i);
				if (pair != null)
					col.add(pair.second);
			}

			return col;
		}

		public void resize(int newSize) {
			while (al.size() <= newSize)
				al.add(null);
		}

		public int hashCode(Object o) {
			if (o == null)
				return 0;
			else
				return (o.hashCode() % 100000) + 1;
		}

		public class Pair<T1, T2> implements Map.Entry<T1, T2> {
			T1 first;
			T2 second;

			public Pair(T1 t1, T2 t2) {
				this.first = t1;
				this.second = t2;
			}

			@Override
			public T1 getKey() {
				return first;
			}

			@Override
			public T2 getValue() {
				return second;
			}

			@Override
			public T2 setValue(T2 value) {
				this.second = value;
				return null;
			}
		}

		public void show() {
			Iterator<Pair<K, V>> it = al.iterator();
			while (it.hasNext()) {
				Pair<K, V> pair = it.next();
				if (pair != null)
					System.out.println(pair.first + " " + pair.second);
			}
		}
	}

	/**
	 * TODO Your own hash function with uniform distribution for input strings
	 * 
	 * @param string
	 *            any string
	 * @return hash for the string
	 */
	public static int hash(String string) {//polynomial hashing with simple key which>255 
		int answer = 0;
		int key = 257;// 51
		int ppow = 1;
		int procent = Integer.MAX_VALUE;//max value
		for (int i = 0; i < string.length(); i++) {
			int code = (int) string.charAt(i);
			answer = (answer + code * ppow) % procent;
			ppow = (ppow * key) % procent;
		}
		return Math.abs(answer);//hash must be more 0
	}

	/**
	 * TODO Your own hash function with uniform distribution for floats
	 * 
	 * @param flt
	 *            floating point number
	 * @return hash code
	 */
	public static int hash(Float flt) {
		return hash(Double.toString(flt));//simple hashing using string. Good if string hashing is good
	}

	public static int hash(Integer flt) {//simple hashing
		// TODO: implement
		return Math.abs(flt);
	}

	/**
	 * TODO entry point for assignment #2 task
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Locale.setDefault(new Locale("en", "US", "WIN"));
		File fin = new File("hash.in");
		PrintWriter out = null;
		Scanner sc;
		try {
			sc = new Scanner(fin);
			out = new PrintWriter(new FileWriter("hash.out"));
			MyMap<String, String> map = new MyMap<String, String>();
			String[] array = sc.nextLine().split(" ");
			for (int i = 0; i < array.length / 2; i++) {
				String a = array[2 * i];
				String b = array[2 * i + 1];
				// System.out.println(a + " " + b);
				if (b.equals("-"))
					map.remove(a);
				else
					map.put(a, b);
			}

			array = sc.nextLine().split(" ");
			for (String string : array) {
				out.write(map.get(string) + " ");
			}

			out.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
