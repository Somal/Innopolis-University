import java.util.ArrayList;

public class LinkedBinarySearchTree<K, V> implements Visitor {
    private LinkedNode<K, V> root;
    private int size;
    private int maxInt = 9;
    private int count = 10;

    public LinkedBinarySearchTree() {
        root = null;
        size = 0;
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

    @Override
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

    private void add(LinkedNode<K, V> node, LinkedNode<K, V> childNode) {
        if (node == null)
            return;

        if (node.compareTo(childNode) > 0) {
            if (node.getLeft() != null)
                add(node.getLeft(), childNode);
            else
                node.setLeft(childNode);
        }
        if (node.compareTo(childNode) < 0) {
            if (node.getRight() != null)
                add(node.getRight(), childNode);
            else
                node.setRight(childNode);
        }
        if (node.compareTo(childNode) == 0) {
            System.out.println("same data " + childNode.getValue());
        }
    }

    public LinkedNode<K, V> add(K key, V value) {
        if (isEmpty()) return addRoot(key, value);
        else {
            LinkedNode<K, V> newNode = new LinkedNode<>(key, value);
            add(root, newNode);
            return newNode;
        }
    }

    public LinkedNode delete(K key, V value) {
        LinkedNode<K, V> node = find(key, value);
        LinkedNode<K,V> parent;

        switch (node.numOfChildren()) {
            case 0:
                parent=node.getParent();
                node.removeFromParent();
                node.delete();
                return parent;
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
                return parent;
            case 2:
                LinkedNode<K, V> prec = getPrecestor(node);
                parent=node.getParent();
                prec.removeFromParent();
                node.setKey(prec.getKey());
                node.setValue(prec.getValue());
                return parent;
            default:
                return null;
        }


    }

    public LinkedNode<K, V> getPrecestor(LinkedNode<K, V> node) {
        return getprecestor(node.getRight());
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

    private LinkedNode<K, V> getprecestor(LinkedNode<K, V> node) {
        LinkedNode<K, V> prec = node;
        while (prec.getLeft() != null)
            prec = prec.getLeft();

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

    public class Pair<A, B> {
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


}
