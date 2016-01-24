import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class countingSort {
	int n;
	int k;
	int[] c;
	int[] a;

	public countingSort(int n, int k) {
		this.n = n;
		this.k = k;
	}

	void increase(int i) {
		c[a[i]]++;
	}

	public long calc() {
		int n = this.n;
		int k = this.k;
		long start = System.nanoTime();
		a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = (int) (Math.random() * (k + 1));
		/*
		 * for (int i = 0; i < n; i++) System.out.print(a[i] + " ");
		 * System.out.println();
		 */
		c = new int[k + 1];
		for (int i = 0; i < k; i++)
			c[i] = 0;

		///ExecutorService application = Executors.newCachedThreadPool();
		// application.execute(increase(0));

		for (int i = 0; i < n; i++)
			increase(i);

		int l = 0;
		for (int i = 0; i < k + 1; i++)
			for (int j = 0; j < c[i]; j++) {
				a[l] = i;
				l++;
			}

		/*
		 * for (int i = 0; i < n; i++) System.out.print(a[i] + " ");
		 */
		return System.nanoTime() - start;

	}

	/*public static class increase() implements Runnable
	{
		int i;

		public increase(int i) {
			this.i = i;
		}

	}*/
}
