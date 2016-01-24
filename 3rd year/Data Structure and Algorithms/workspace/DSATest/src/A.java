import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A {
	static PrintWriter fos = null;

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(new File("numbers.in"));
			fos = new PrintWriter(new FileWriter("numbers.out"));
			String s;
			int i = 0;
			while (sc.hasNext()) {
				s = sc.next();
				int tmp = (int) s.charAt(s.length() - 1) - (int) '0';
				if (isDigit(s)) {
					if (tmp >= 0 && tmp <= 9)
						if (tmp % 2 == 0)
							print("even ");
						else
							print("odd ");
				} else
					print("nan ");

			}
			print("\n");
			fos.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static boolean isDigit(String s) {
		/*
		 * for (int i=0;i<s.length();i++){ int tmp = (int) s.charAt(i) - (int)
		 * '0'; if ((tmp>0 && tmp<9) || s.charAt(i)=='-'){
		 * 
		 * } } return flag;
		 */
		Pattern p = Pattern.compile("[0-9[-]]");
		boolean flag = true;
		for (int i = 0; i < s.length(); i++) {
			Matcher m = p.matcher(Character.toString(s.charAt(i)));
			if (!m.matches())
				flag = false;
		}
		return flag;

	}

	public static void print(String s) {
		fos.write(s);
		System.out.print(s + " ");
	}
}
