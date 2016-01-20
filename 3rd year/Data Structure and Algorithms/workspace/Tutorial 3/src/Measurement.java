import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

enum Color {Red, Black};

/**
 * Created by Somal on 03.11.2015.
 */


public class Measurement {

    public static void main(String[] args) throws FileNotFoundException {
        BST<Integer, Integer> tree = new BST<>();
        Scanner sc = new Scanner(new File("data.in"));
        FileOutputStream fos = new FileOutputStream("data.out");
        PrintWriter pw = new PrintWriter(fos);

        int i = 0;
        int d = 0;
        int x = 0;
        int y = 0;
        while (sc.hasNextInt()) {
            if (i % 60000 == 0 && i > 0)
                pw.write(sqrtt(tree.findMin()) + " " + sqrtt(tree.findMax()) + " ");

            x = sc.nextInt();
            y = sc.nextInt();
            d = x * x + y * y;
            tree.insert(d, d);
            i++;
        }
        pw.write(sqrtt(tree.findMin()) + " " + sqrtt(tree.findMax()) + " ");
//        tree.show();


        BST<Integer, Integer>.LinkedNode<Integer, Integer> found = tree.find(d, d);
        Object[] nodes = new Object[]{found.getPredecessor(), found.getSuccessor(), found.getParent()};
        BST<Integer, Integer>.LinkedNode<Integer, Integer> node = null;
        BST<Integer, Integer>.LinkedNode<Integer, Integer> tmp;
        int dist = Integer.MAX_VALUE;
        for (Object o : nodes) {
            tmp = (BST<Integer, Integer>.LinkedNode<Integer, Integer>) o;
            if (tmp != null)
                if (Math.abs(found.getValue() - tmp.getValue()) < dist) {
                    dist = Math.abs(found.getValue() - tmp.getValue());
                    node = tmp;
                }
        }

        pw.write(String.valueOf(sqrtt(node.getValue()))+" ");
        pw.close();
    }

    public static int sqrtt(int a){
        return (int) Math.round(Math.sqrt(a));
    }


    static class RBT<K extends Comparable<K>, V> extends BST<K, V> {

        public RBT() {
            super();
        }

        public void insert(K key, V value) {
//            System.out.println("before insertion");
//            show();
            if (getRoot() == null) setRoot(new LinkedNode<>(key, value, Color.Black)); //adding root
            else insertNode(getRoot(), new LinkedNode<>(key, value, Color.Red));
//
//            System.out.println("after insertion");
//            show();
//            System.out.println();
        }

        private void insertNode(LinkedNode<K, V> parent, LinkedNode<K, V> childNode) {
            if (parent.compareTo(childNode) < 0) {
                if (parent.getRight() != null) {
                    insertNode(parent.getRight(), childNode);
                    return;
                } else {
                    parent.setRight(childNode);
                    nodeProcessing(childNode);
                    return;
                }
            } else if (parent.compareTo(childNode) > 0) {
                if (parent.getLeft() != null) {
                    insertNode(parent.getLeft(), childNode);
                    return;
                } else {
                    parent.setLeft(childNode);
                    nodeProcessing(childNode);
                    return;
                }
            }

//        System.out.println("before balancing");
//        show();
        }

        private void turning(LinkedNode<K, V> node) {
//            System.out.println("turning ");
            LinkedNode<K, V>[] parents = null;
            LinkedNode<K, V>[] childrens = null;
            LinkedNode<K, V> parent = node.getParent();
            LinkedNode<K, V> grandparent = node.getGrandparent();
            LinkedNode<K, V> grandgrandparent = node.getGrandparent().getParent();

//            System.out.println(node.getKey().toString() + " " + parent.getKey() + " " + grandparent.getKey());
            if (node.isLeftChild() && parent.isLeftChild()) {
                parents = new LinkedNode[]{node, parent, grandparent};
                childrens = new LinkedNode[]{node.getLeft(), node.getRight(), parent.getRight(), grandparent.getRight()};
            }

            if (!node.isLeftChild() && parent.isLeftChild()) {
                parents = new LinkedNode[]{parent, node, grandparent};
                childrens = new LinkedNode[]{
                        parent.getLeft(), node.getLeft(), node.getRight(), grandparent.getRight()};
            }
            if (node.isLeftChild() && !parent.isLeftChild()) {
                parents = new LinkedNode[]{grandparent, node, parent};
                childrens = new LinkedNode[]{grandparent.getLeft(), node.getLeft(), node.getRight(), parent.getRight()};
            }
            if (!node.isLeftChild() && !parent.isLeftChild()) {
                parents = new LinkedNode[]{grandparent, parent, node};
                childrens = new LinkedNode[]{grandparent.getLeft(), parent.getLeft(), node.getLeft(), node.getRight()};
            }

//            for (LinkedNode<K, V> p : parents)
//                System.out.print(p + " ");
//            System.out.println();
//
//            for (LinkedNode<K, V> c : childrens)
//                System.out.print(c + " ");
//            System.out.println();

            node.removeFromParent();
            parent.removeFromParent();
            grandparent.removeFromParent();

            parents[0].setLeft(childrens[0]);
            parents[0].setRight(childrens[1]);
            parents[2].setLeft(childrens[2]);
            parents[2].setRight(childrens[3]);

            parents[0].setColor(Color.Red);
            parents[2].setColor(Color.Red);

            parents[1].setColor(Color.Black);
            parents[1].setLeft(parents[0]);
            parents[1].setRight(parents[2]);

            if (grandgrandparent == null)
                setRoot(parents[1]);
            else {
                if (grandgrandparent.compareTo(parents[1]) < 0) grandgrandparent.setRight(parents[1]);
                if (grandgrandparent.compareTo(parents[1]) > 0) grandgrandparent.setLeft(parents[1]);
            }

//                        System.out.println("New");
//                        System.out.println(parents[1].getParent().getRight());
//                        System.out.println(parents[0].toString() + parents[0].getParent() + parents[2]);
        }

        private void nodeProcessing(LinkedNode<K, V> node) {//stupid node processing method

            if (node.getParent() != null)
                if (node.getGrandparent() != null) {
                    if (node.getParent().isRed()) {
                        if (node.getUncle() != null) {
                            if (node.getUncle().isRed()) {
//                                System.out.println("recoloring: " + node);
                                node.getGrandparent().setColor(Color.Red);
                                node.getParent().setColor(Color.Black);
                                node.getUncle().setColor(Color.Black);
                                getRoot().setColor(Color.Black);
                                nodeProcessing(node.getGrandparent());
                            } else turning(node);
                        } else turning(node);
                    }
                }
//            System.out.println("after processing:");
//            show();
        }


       }

    static class BST<K, V> {
        private LinkedNode<K, V> root;
        private int size;
        private int maxInt = 9;
        private int count = 3;


        public BST() {
            this.root = null;
            this.size = 0;
        }

        public K findMin() {
            LinkedNode<K, V> node;
            for (node = getRoot(); node.getLeft() != null; node = node.getLeft()) {
            }
            return node.getKey();
        }

        public K findMax() {
            LinkedNode<K, V> node;
            for (node = getRoot(); node.getRight() != null; node = node.getRight()) {
            }
            return node.getKey();
        }


        public void show() {
//            System.out.println("Visulization of tree");
            int height = getHeight();
            int weight = 1 << height;
//        System.out.println(weight);
            ArrayList<Pair<LinkedNode<K, V>, Pair<Integer, Integer>>> answer = new ArrayList<Pair<LinkedNode<K, V>, Pair<Integer, Integer>>>(weight);
            ArrayList<Pair<LinkedNode<K, V>, Pair<Integer, Integer>>> oldAnswer = new ArrayList<Pair<LinkedNode<K, V>, Pair<Integer, Integer>>>(weight);
            for (int i = 0; i < weight; i++) {
                answer.add(null);
                oldAnswer.add(null);
            }

            answer.set(weight / 2, new Pair<LinkedNode<K, V>, Pair<Integer, Integer>>(root, new Pair<Integer, Integer>(0, weight - 1)));
//        showNodes(answer);
//        System.out.println(this.root.getRight().getData());
            for (int i = 0; i <= height - 1; i++) {
                showNodes(answer);//show one line


                oldAnswer = new ArrayList<Pair<LinkedNode<K, V>, Pair<Integer, Integer>>>(weight);//initializing new arraylist
                for (int k = 0; k < weight; k++)
                    oldAnswer.add(null);


                for (int j = 0; j < weight; j++)
                    if (answer.get(j) != null) {
//                    System.out.println("not null "+i);
                        Pair<LinkedNode<K, V>, Pair<Integer, Integer>> pair = answer.get(j);
                        LinkedNode<K, V> childLeft = pair.getFirst().getLeft();
                        LinkedNode<K, V> childRight = pair.getFirst().getRight();
                        int l = pair.getSecond().getFirst();
                        int r = pair.getSecond().getSecond();
                        if (childLeft != null) {
                            oldAnswer.set((l + j - 1) / 2, new Pair<LinkedNode<K, V>, Pair<Integer, Integer>>(childLeft, new Pair<Integer, Integer>(l, j - 1)));
//                        System.out.println("data " +childLeft.getData()+" l "+l+" r "+(j-1));
                        }
                        if (childRight != null) {
                            oldAnswer.set((j + 1 + r) / 2, new Pair<LinkedNode<K, V>, Pair<Integer, Integer>>(childRight, new Pair<Integer, Integer>(j + 1, r)));
//                        System.out.println("data " + childRight.getData() + " l " + (j + 1) + " r " + r + " index " + (j + 1 + r) / 2);
                        }
                    }
                answer = oldAnswer;
            }


        }

        private void showNodes(ArrayList<Pair<LinkedNode<K, V>, Pair<Integer, Integer>>> input) {
//        System.out.println(input.size());
            for (int i = 0; i < input.size(); i++) {
                Pair<LinkedNode<K, V>, Pair<Integer, Integer>> pair = input.get(i);
                if (pair == null)
                    for (int j = 0; j < count; j++) {
                        System.out.print(" ");
                    }
                else {
                    System.out.print(pair.getFirst().getValue().toString() + ((pair.getFirst().isRed()) ? 'r' : 'b'));
                }

            }

            System.out.println();
        }

        public void setRoot(LinkedNode<K, V> node) {
            root = node;
        }

        public int size() {
            return size;
        }

        public LinkedNode<K, V> getRoot() {
            return root;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private LinkedNode addRoot(K key, V value) {
            if (!isEmpty()) throw new IllegalStateException("Tree is not empty!");
            this.root = createNode(key, value, null, null, null, Color.Black);
            size = 1;
            return root;
        }

        public void insert(K key, V value) {
            if (isEmpty()) addRoot(key, value);
            else {
                LinkedNode<K, V> newNode = new LinkedNode<K, V>(key, value, Color.Red);
                insert(root, newNode);
                //return newNode;
            }
        }

        private void insert(LinkedNode<K, V> node, LinkedNode<K, V> childNode) {
            if (node == null)
                return;

            if (node.compareTo(childNode) > 0) {
                if (node.getLeft() != null)
                    insert(node.getLeft(), childNode);
                else
                    node.setLeft(childNode);
            }
            if (node.compareTo(childNode) < 0) {
                if (node.getRight() != null)
                    insert(node.getRight(), childNode);
                else
                    node.setRight(childNode);
            }
            if (node.compareTo(childNode) == 0) {
//                System.out.println("same data " + childNode.getValue());
            }
        }

        public LinkedNode<K, V> find(K key, V value) {
            return findData(key, value, root);
        }

        private LinkedNode<K, V> findData(K key, V value, LinkedNode<K, V> parent) {
            LinkedNode<K, V> tmp = new LinkedNode<>(key, value, Color.Red);
            if (parent.compareTo(tmp) == 0) return parent;


//        LinkedNode<K, V> answer = null;
            if (parent.getLeft() != null)
                if (parent.compareTo(tmp) > 0)
                    return findData(key, value, parent.getLeft());
            if (parent.getRight() != null)
                if (parent.compareTo(tmp) < 0)
                    return findData(key, value, parent.getRight());
            return null;
        }

        public int getHeight() {
            return height(root);
        }

        protected int height(LinkedNode<K, V> node) {
            return (node == null) ? 0 : Math.max(height(node.getLeft()), height(node.getRight())) + 1;
        }

        private LinkedNode<K, V> createNode(K key, V value, LinkedNode<K, V> parent, LinkedNode<K, V> childLeft, LinkedNode<K, V> childRight, Color color) {
            return new LinkedNode<K, V>(key, value, parent, childLeft, childRight, color);
        }


        class Pair<A, B> {
            private A first;
            private B second;

            public Pair(A first, B second) {
                super();
                this.first = first;
                this.second = second;
            }

            public A getFirst() {
                return first;
            }

            public void setFirst(A first) {
                this.first = first;
            }

            public B getSecond() {
                return second;
            }

            public void setSecond(B second) {
                this.second = second;
            }
        }

        class LinkedNode<K, V> implements Comparable<LinkedNode<K, V>> {
            private K key = null;
            private V value = null;
            private LinkedNode<K, V> parent = null;
            private LinkedNode<K, V> child1 = null;
            private LinkedNode<K, V> child2 = null;
            int height = 0;
            private Color color;

            public String toString() {
                return ((LinkedNode<K, V>) this == null) ? "null" : getValue().toString();
            }

            public LinkedNode(K key, V value, Color color) {
                this.value = value;
                this.key = key;
                this.parent = null;
                this.child1 = null;
                this.child2 = null;
                this.color = color;
            }

            public LinkedNode(K key, V value, LinkedNode<K, V> parent, LinkedNode<K, V> child1, LinkedNode<K, V> child2, Color color) {
                this.value = value;
                this.key = key;
                this.parent = parent;
                this.child1 = child1;
                this.child2 = child2;
                this.color = color;
            }


            public LinkedNode<K, V> getPredecessor() {
                LinkedNode<K, V> prec = getLeft();
                if (prec != null)
                    while (prec.getRight() != null)
                        prec = prec.getRight();

                return prec;
            }

            public LinkedNode<K, V> getSuccessor() {
                LinkedNode<K, V> prec = getRight();
                if (prec != null)
                    while (prec.getLeft() != null)
                        prec = prec.getLeft();

                return prec;
            }

            public K getKey() {
                return key;
            }

            public V getValue() {
                return value;
            }

            public void setKey(K key) {
                this.key = key;
            }

            public void setValue(V value) {
                this.value = value;
            }

            public LinkedNode<K, V> getParent() {
                return parent;
            }

            public LinkedNode<K, V> getGrandparent() {
                return getParent().getParent();
            }

            public LinkedNode<K, V> getUncle() {
//                System.out.println("uncle need to: "+this);
                LinkedNode<K, V> parent = getParent();
                LinkedNode<K, V> grandparent = getGrandparent();
                if (grandparent.getLeft() == null && grandparent.getRight() == parent) return null;
                if (grandparent.getRight() == null && grandparent.getLeft() == parent) return null;
                return (getParent().compareTo(this.getGrandparent().getLeft()) == 0) ? this.getGrandparent().getRight() : this.getGrandparent().getLeft();
            }

            public void setParent(LinkedNode<K, V> parent) {
                this.parent = parent;
            }

            public LinkedNode<K, V> getLeft() {
                return child1;
            }

            public LinkedNode<K, V> getRight() {
                return child2;
            }

            public void setLeft(LinkedNode<K, V> childNode) {
//        for (LinkedNode<K,V> n=this;n!=null;n=n.getParent())
//            if (n.compareTo(childNode)==0) throw new IllegalArgumentException();

                if (this.getLeft() != null) {
                    getLeft().setParent(childNode);
                }

                if (childNode != null)
                    childNode.setParent(this);
                this.child1 = childNode;
            }

            public void setRight(LinkedNode<K, V> childNode) {
//        for (LinkedNode<K,V> n=this;n!=null;n=n.getParent())
//            if (n==childNode) throw new IllegalArgumentException();

                if (this.getRight() != null) {
                    getRight().setParent(childNode);
                }

                if (childNode != null)
                    childNode.setParent(this);
                this.child2 = childNode;
            }

            public void removeFromParent() {
                if (getParent() != null) {
                    if (getParent().getLeft() == this) {
                        getParent().setLeft(null);
                    } else if (getParent().getRight() == this) {
                        getParent().setRight(null);
                    }
                    parent = null;
                }
            }

            public int numOfChildren() {
                int count = 0;
                if (getLeft() != null)
                    count++;
                if (getRight() != null)
                    count++;
                return count;
            }

            public LinkedNode<K, V> findMax() {
                LinkedNode<K, V> node;
                for (node = this; node.getRight() != null; node = node.getRight()) {
                }
                return node;
            }

            @Override
            public int compareTo(LinkedNode<K, V> another) {
                Comparable node1 = (Comparable) key;
                Comparable node2 = (Comparable) another.getKey();
                return node1.compareTo(node2);
            }

            public void delete() {
                parent = null;
                child1 = null;
                child2 = null;
                key = null;
                value = null;
            }

            public boolean isRed() {
                return this.color == Color.Red;
            }

            public void setColor(Color color) {
                this.color = color;
            }

            public boolean isLeftChild() {
                return (compareTo(getParent()) < 0);
            }

        }


    }


}
