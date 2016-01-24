package submitting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Somal on 29.10.2015.
 */
public class AVLTree {

    public static void main(String[] args) throws FileNotFoundException {
        AVL<Integer, Integer> tree = new AVL<>();//used key-value tree
        Scanner sc = new Scanner(new File("avl.in"));
        int i;

        FileOutputStream fos = new FileOutputStream("avl.out");
        PrintWriter pw = new PrintWriter(fos);
        for (int j = 0; j < 10000; j++) {
            long start = System.nanoTime();
            tree.insert(j, j);
            pw.write(j + " " + (System.nanoTime() - start) + " " + tree.getHeight() + "\n");
        }

//        //inserting
//        String[] ar = sc.nextLine().split(" ");
//        for (String s : ar)
//            if (s.length() > 0) {//not empty
//                i = (int) Double.parseDouble(s);
//                tree.insert(i, i);
//            }

//        //deleting
//        ar = sc.nextLine().split(" ");
//        for (String s : ar)
//            if (s.length() > 0) {
//                i = (int) Double.parseDouble(s);
//                tree.delete(i);
//            }



//        //finding
//        ar = sc.nextLine().split(" ");
//        for (String s : ar)
//            if (s.length() > 0) {
//                int value = (int) Double.parseDouble(s);
//                BST<Integer, Integer>.LinkedNode<Integer, Integer> q = tree.find(value, value);
//                pw.write((q == null ? "null" : (q.getRight() == null ? "null" : q.getRight().getKey().toString())) + " ");
//            }

        pw.close();//closing
    }

    /** Class of AVL key-value tree
     * @param <K> type of key
     * @param <V> type of value
     */
    static class AVL<K extends Comparable<K>, V> extends BST<K, V> {

        public AVL() {
            super();
        }

        /** Insert into tree node
         * @param key key of node
         * @param value value of node
         */
        public void insert(K key, V value) {
            LinkedNode<K, V> node = new LinkedNode<K, V>(key, value);
            if (getRoot() == null) setRoot(node); //adding root
            else setRoot(insertNode(getRoot(), node));
        }

        public void delete(K key) {
            setRoot(remove(getRoot(), key));
        }

        LinkedNode<K, V> remove(LinkedNode<K, V> p, K k) {
            if (k.compareTo(p.getKey()) < 0) p.setLeft(remove(p.getLeft(), k));
            else if (k.compareTo(p.getKey()) > 0) p.setRight(remove(p.getRight(), k));
            else { // if (k == p->key)
                LinkedNode<K, V> l = p.getLeft();
                LinkedNode<K, V> r = p.getRight();
                if (l == null) return r;
                LinkedNode<K, V> max = l.findMax();
                max.setLeft(removeMax(l));
                max.setRight(r);
                return balance(max);
            }
//        return balance();
            return balance(p);
        }

        LinkedNode<K, V> removeMax(LinkedNode<K, V> p) {
            if (p.getRight() == null) return p.getLeft();
            p.setRight(removeMax(p.getRight()));
            return balance(p);
        }

        private LinkedNode insertNode(LinkedNode<K, V> parent, LinkedNode<K, V> childNode) {
            if (parent == null) return childNode;
            if (parent.compareTo(childNode) < 0)
                parent.setRight(insertNode(parent.getRight(), childNode));
            if (parent.compareTo(childNode) > 0)
                parent.setLeft(insertNode(parent.getLeft(), childNode));

//        System.out.println("before balancing");
//        show();
            return balance(parent);
        }


        private LinkedNode balance(LinkedNode<K, V> p) {
            fixHeight(p);
            if (bfact(p) == 2) {
                if (bfact(p.getRight()) < 0)
                    p.setRight(rightRotation(p.getRight()));
                return leftRotation(p);
            } else if (bfact(p) == -2) {
                if (bfact(p.getLeft()) > 0)
                    p.setLeft(leftRotation(p.getLeft()));
                return rightRotation(p);
            }
            return p;
        }

        private int bfact(LinkedNode<K, V> node) {
            if (node == null) return 0;
            return this.height(node.getRight()) - height(node.getLeft());
        }

        private LinkedNode<K, V> rightRotation(LinkedNode<K, V> p) {
//        LinkedNode<K, V> parent = p.getParent();
//        p.removeFromParent();

            LinkedNode<K, V> q = p.getLeft();
            p.setLeft(q.getRight());
            q.setRight(p);
            fixHeight(p);
            fixHeight(q);

//        if (p.compareTo(parent) < 0) parent.setLeft(q);
//        else parent.setRight(q);

            return q;
        }

        private LinkedNode<K, V> leftRotation(LinkedNode<K, V> q) {
//        LinkedNode<K, V> parent = q.getParent();
//        q.removeFromParent();

            LinkedNode<K, V> p = q.getRight();
            q.setRight(p.getLeft());
            p.setLeft(q);
            fixHeight(q);
            fixHeight(p);

//        if (parent != null)
//            if (q.compareTo(parent) < 0) parent.setLeft(p);
//            else parent.setRight(p);

            return p;
        }

        private void fixHeight(LinkedNode<K, V> node) {
            if (node != null) node.height = height(node);
        }
    }

    static class BST<K, V> {
        private LinkedNode<K, V> root;
        private int size;
        private int maxInt = 9;
        private int count = 10;


        public BST() {
            this.root = null;
            this.size = 0;
        }

        public void show() {
            System.out.println("Visulization of tree");
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

        public void setRoot(LinkedNode<K, V> node) {
            root = node;
        }

        public <K, V> void visit(LinkedNode<K, V> node) {
            System.out.print(" " + node.getValue() + " ");
            System.out.println();
        }

        public void postorderTraversal() {
            privatePreorderTraversal(root);
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
            this.root = createNode(key, value, null, null, null);
            size = 1;
            return root;
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
                System.out.println("same data " + childNode.getValue());
            }
        }

        public void insert(K key, V value) {
            if (isEmpty()) addRoot(key, value);
            else {
                LinkedNode<K, V> newNode = new LinkedNode<>(key, value);
                insert(root, newNode);
                //return newNode;
            }
        }

        private boolean delete(K key, V value) {
            LinkedNode<K, V> node = find(key, value);
            if (node == null) return false;
            LinkedNode<K, V> parent;

            switch (node.numOfChildren()) {
                case 0:
                    parent = node.getParent();
                    node.removeFromParent();
                    node.delete();
                    break;
                case 1:
                    parent = node.getParent();
                    LinkedNode<K, V> child;
                    if (node.getLeft() != null) {
                        child = node.getLeft();
                        child.setParent(parent);
                        parent.setLeft(child);
                    } else {
                        child = node.getRight();
                        child.setParent(parent);
                        parent.setRight(child);
                    }
                    node.delete();
                    break;
                case 2:
                    LinkedNode<K, V> prec = getPredecessor(node);
                    parent = node.getParent();
                    prec.removeFromParent();
                    node.setKey(prec.getKey());
                    node.setValue(prec.getValue());
                    break;

            }
            return true;
        }

        public LinkedNode<K, V> getPredecessor(LinkedNode<K, V> node) {
            return getpredecessor(node.getLeft());
        }

        public LinkedNode<K, V> find(K key, V value) {
            return findData(key, value, root);
        }

        public int getHeight() {
            return height(root);
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
                    System.out.print(pair.getFirst().getValue());
                }

            }

            System.out.println();
        }

        private void privatePreorderTraversal(LinkedNode<K, V> node) {
            if (node == null) {
                //System.out.println("yes");
                return;
            }
            privatePreorderTraversal(node.getLeft());
            privatePreorderTraversal(node.getRight());
            visit(node);
        }

        protected int height(LinkedNode<K, V> node) {
            return (node == null) ? 0 : Math.max(height(node.getLeft()), height(node.getRight())) + 1;
        }

        private LinkedNode<K, V> getpredecessor(LinkedNode<K, V> node) {
            LinkedNode<K, V> prec = node;
            while (prec.getRight() != null)
                prec = prec.getRight();

            return prec;
        }

        private LinkedNode<K, V> addLeft(LinkedNode<K, V> n, K key, V value) {
            if (n.getLeft() == null) throw new IllegalArgumentException("Node has left child");
            LinkedNode<K, V> child = createNode(key, value, null, null, null);
            n.setLeft(child);
            size++;
            return child;
        }

        private LinkedNode<K, V> addRight(LinkedNode<K, V> n, K key, V value) {
            if (n.getRight() == null) throw new IllegalArgumentException("Node has right child");
            LinkedNode<K, V> child = createNode(key, value, null, null, null);
            n.setRight(child);
            size++;
            return child;
        }

        private LinkedNode<K, V> findData(K key, V value, LinkedNode<K, V> parent) {
            LinkedNode<K, V> tmp = new LinkedNode<>(key, value);
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

        private LinkedNode<K, V> createNode(K key, V value, LinkedNode<K, V> parent, LinkedNode<K, V> childLeft, LinkedNode<K, V> childRight) {
            return new LinkedNode<K, V>(key, value, parent, childLeft, childRight);
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

            public LinkedNode(K key, V value) {
                this.value = value;
                this.key = key;
                this.parent = null;
                this.child1 = null;
                this.child2 = null;
            }

            public LinkedNode(K key, V value, LinkedNode<K, V> parent, LinkedNode<K, V> child1, LinkedNode<K, V> child2) {
                this.value = value;
                this.key = key;
                this.parent = parent;
                this.child1 = child1;
                this.child2 = child2;
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
        }


    }


}
