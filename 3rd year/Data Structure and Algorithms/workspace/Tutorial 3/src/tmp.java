import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by Somal on 03.11.2015.
 */
public class tmp {
    public static void main(String[] args) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream("data.in");
        PrintWriter pw = new PrintWriter(fos);
        for (int i=0;i<60000;i++)
            pw.write("1 1 ");

//        pw.write("0 0");

//        for (int i=0;i<60000;i++)
//            pw.write(5+" "+5+" ");
        pw.write("2 2 3 3 4 4 5 5 ");
        pw.close();

    }


}
