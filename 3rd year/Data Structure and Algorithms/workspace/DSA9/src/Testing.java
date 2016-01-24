import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Somal on 19.10.2015.
 */
public class Testing {
    public static void main(String[] args) throws IOException {
//        AVLAutoTesting();
        AVLTesting();
//        bstTesting();
    }

    public static void bstTesting() {
        LinkedBinarySearchTree<Integer, String> tree = new LinkedBinarySearchTree<>();
//        LinkedBinarySearchTree<Integer,String> bst2= new LinkedBinarySearchTree<>();

        tree.add(1, "January");
        tree.add(2, "February");
        tree.add(3, "March");
        tree.add(4, "April");
        tree.add(5, "May");
        tree.add(6, "June");
        tree.add(7, "July");
        tree.add(8, "August");
        tree.add(9, "September");
        tree.add(10, "October");
        tree.add(11, "November");
        tree.add(12, "December");
        System.out.println("height of first tree is " + tree.getHeight());
        tree.show();

//        fill(bst2, array, 0, count - 1);
//        System.out.println("height of second tree is " + bst2.getHeight());
//        bst2.show();
    }

    public static void AVLTesting() {
//        AVL<Integer, String> avl = new AVL<>();
//        avl.insert(3, "March");
//        avl.insert(5, "May");
//        avl.insert(11, "November");
//        avl.insert(8, "August");
//        avl.insert(4, "April");
//        avl.insert(1, "January");
//        avl.insert(12, "December");
//        avl.insert(7, "July");
//        avl.insert(2, "February");
//        avl.insert(6, "June");
//        avl.insert(10, "October");
//        avl.insert(9, "September");
//
//        avl.show();
//        avl.delete(4);
//        avl.delete(8);
//        avl.delete(12);
//        System.out.println(avl.find(10, "October").getValue().equals("October"));
        AVL<Integer, Integer> avl = new AVL<>();
        for (int i = 1; i < 5; i++) {
            avl.insert(i, i);
        }
        avl.delete(1);

        System.out.println("height of first tree is " + avl.getHeight());
        avl.show();

    }

    public static void AVLAutoTesting() throws IOException {
        AVL<Integer, Integer> tree = new AVL<Integer, Integer>();
        long start;
        PrintWriter pw = new PrintWriter(new FileWriter("insertResult.txt"));
        for (int i = 0; i <= 1000000; i++) {
            start = System.nanoTime();
            tree.insert(i, i);
            pw.write(Math.log(System.nanoTime() - start) / Math.log(2) + " " + tree.getHeight() + " ");
//            pw.write((System.nanoTime() - start) + " " + tree.getHeight()+" ");
//            System.out.println(Math.log(System.nanoTime() - start)/Math.log(2) + " " + tree.getHeight());
        }
        pw.close();
        pw = new PrintWriter(new FileWriter("deleteResult.txt"));
        for (int i = 1000000; i >= 0; i--) {
            start = System.nanoTime();
            tree.delete(i);
            pw.write(Math.log(System.nanoTime() - start) / Math.log(2) + " " + tree.getHeight() + " ");
//            pw.write((System.nanoTime() - start) + " " + tree.getHeight()+" ");
//            System.out.println(Math.log(System.nanoTime() - start)/Math.log(2) + " " + tree.getHeight());
        }
        pw.close();


    }
}

