
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

public class MyFramework {

	/**
	 * TODO Implement your own binary search algorithms
	 * 
	 * @param data
	 *            - list of data to search. Assume data is sorted
	 * @param map
	 *            - method that converts object from the list to Comparable
	 *            object. Most likely this is a method that takes value from the
	 *            row. use map.apply(value) to start
	 * @param value
	 *            - value we are searching for
	 * @return index of the element in array if present, else -1
	 */
	@SuppressWarnings({ "rawtypes" })
	public static <T> int binarySearch(List<T> data, Function<Object, Comparable> map, Comparable value) {
		if (data.isEmpty()) {//if object was not founded
			return -1;
		}
		int midIndex = data.size() / 2; 

		Comparable mid = map.apply(data.get(midIndex));
		int result = mid.compareTo(value);
		if (result == 0)
			return midIndex;
		if (result > 0)
			return binarySearch(data.subList(0, midIndex), map, value);//left part of list
		else {
			int idx = binarySearch(data.subList(midIndex + 1, data.size()), map, value);//right part of list
			return idx == -1 ? idx : midIndex + 1 + idx;
		}

	}

	public static <T> int iterativeBinarySearch(List<T> data, Function<Object, Comparable> map, Comparable value) {
		int l = 0;//left index of part where we are finding element
		int r = data.size();//right index of part where we are finding element
		while (l < r) {
			int midIndex = (l + r) / 2;

			Comparable mid = map.apply(data.get(midIndex));
			int result = mid.compareTo(value);
			if (result == 0)
				return midIndex;
			if (result > 0)
				r = midIndex - 1;//left part
			else
				l = midIndex + 1;//rigth part
		}
		return -1;

	}

	/**
	 * TODO Implement your own sort that is VERY fast
	 * 
	 * @param data
	 *            data to sort
	 * @param map
	 *            - method that converts object from the list to Comparable
	 *            object. Most likely this is a method that takes value from the
	 *            row. use map.apply(value) to start
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <T> void sort(List<T> data, Function<Object, Comparable> map) {
		// bucketSort(data, map);
		insertionSort(data, map);
	}

	/**
	 * TODO entry point for assignment #2 task
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Locale.setDefault(new Locale("en", "US", "WIN"));
		File fin = new File("sort.in");
		PrintWriter out = null;
		Scanner sc;
		try {
			sc = new Scanner(fin);
			out = new PrintWriter(new FileWriter("sort.out"));
			Function<Object, Comparable> map = new Function<Object, Comparable>() {
				@Override
				public Comparable apply(Object t) {
					return (Comparable) t;
				}
			};

			int index = 0;
			String type = sc.next();
			switch (type) {// not used generic because scanner cannot read
							// generic types
			case "String":
				ArrayList<String> stringList = new ArrayList<String>();
				while (sc.hasNext())
					stringList.add(sc.next());
				String stringFound = stringList.get(stringList.size() - 1);
				stringList.remove(stringList.size() - 1);

				sort(stringList, map);
				index = binarySearch(stringList, map, stringFound);
				break;
			case "Float":
				ArrayList<Float> floatList = new ArrayList<Float>();
				while (sc.hasNext())
					floatList.add(sc.nextFloat());

				Float floatFound = floatList.get(floatList.size() - 1);
				floatList.remove(floatList.size() - 1);

				sort(floatList, map);
				index = binarySearch(floatList, map, floatFound);
				break;

			case "Integer":
				ArrayList<Integer> integerList = new ArrayList<Integer>();
				while (sc.hasNext())
					integerList.add(sc.nextInt());
				Integer integerFound = integerList.get(integerList.size() - 1);
				integerList.remove(integerList.size() - 1);

				sort(integerList, map);
				index = iterativeBinarySearch(integerList, map, integerFound);
				break;
			}

			out.write(Integer.toString(index));
			out.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static <T> void bucketSort(List<T> data, Function<Object, Comparable> map) {
		int sizeBucket = 100;
		int max = 10000000;
		int count = data.size();

		Object[] ps = new Object[(int) ((max / sizeBucket) + 1)];

		ArrayList<T>[] bucket = new ArrayList[(max / sizeBucket) + 1];
		for (int i = 0; i < (max / sizeBucket) + 1; i++)//initialize buckets
			bucket[i] = new ArrayList<T>();

		Iterator<T> it = data.iterator();
		while (it.hasNext()) {//every elememt send to bucket
			T current = it.next();
			int cur = (Integer) current;
			boolean flag = bucket[cur / sizeBucket].add(current);
		}

		// parallelling in comments
		ExecutorService application = Executors.newCachedThreadPool();
		for (int i = 0; i < (max / sizeBucket) + 1; i++) {//sorting
			//ps[i] = new MyFramework.PartSorting<T>(bucket[i], map);
			//((PartSorting<T>) ps[i]).run();
			// application.execute((Runnable) ps[i]);
			insertionSort(data,map);
		}
		// application.shutdown();

		int k = 0;
		for (int i = 0; i < (max / sizeBucket) + 1; i++)//recovering
			if (!bucket[i].isEmpty())
				for (int j = 0; j < bucket[i].size(); j++) {
					data.set(k, bucket[i].get(j));
					k++;
				}

	}

	public class PartSorting<T> implements Runnable {
		ArrayList<T> al;
		Function<Object, Comparable> map;

		@Override
		public void run() {
			insertionSort(al,map);
		}

		public PartSorting(ArrayList<T> al, Function<Object, Comparable> map) {
			this.al = al;
			this.map = map;
		}
	}
	
	public static <T> void insertionSort(List<T> data, Function<Object, Comparable> map) {
		Comparable<T> fieldval = null;
		T cur;
		for (int i = 0; i < data.size(); i++) {
			cur = data.get(i);
			fieldval = map.apply(cur);//current comparable element
			int j = i;
			while (j > 0 && fieldval.compareTo(data.get(j - 1)) < 0) {//finding needed place for current element
				data.set(j, data.get(j - 1));
				j--;
			}
			data.set(j, cur);
		}
	}

	public static <T> void countingSort(List<T> data) {
		/*MyMap<<Integer,T>, Integer> map = new MyMap<Pair<Integer,T>, Integer>();

		for (T elem : data) {
			int hash=elem.hashCode();
			Integer previous = map.get(hash);
			if (previous == null)
				map.put(hash, 1);
			else
				map.put(hash, previous + 1);
		}

		int k = 0;
		for (Integer key : map.keySet())
			for (int i = 0; i < map.get(key); i++) {
				data.set(k, key);
				k++;
			}*/

	}


}
