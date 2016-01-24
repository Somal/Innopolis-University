
public class checker {
	public static void main(String[] args){
		int[] n=new int[]{100,1000,10000,10000,100000,1000000};
		int[] k=new int[]{10000,50000,100000};
		
		for (int i=0;i<n.length;i++)
			for (int j=0;j<k.length;j++){
				countingSort cs=new countingSort(n[i],k[j]);
				System.out.println(n[i]+"\t"+k[j]+"\t"+cs.calc());
			}
	}
}
