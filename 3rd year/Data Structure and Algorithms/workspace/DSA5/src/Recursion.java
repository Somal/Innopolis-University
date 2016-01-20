
public class Recursion {
	public static void main(String[] args) {
		System.out.println(squareRoot(0));

	}

	public static double squareRoot(int deep) {
		if (deep == 7)
			return 1;

		double value = 1 / (2 + squareRoot(deep + 1));
		if (deep == 0)
			value+=1;
		Thread.dumpStack(); //great thing!!!
		return value;
		
	}

}
