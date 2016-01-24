/**
 * Created by Somal on 20.10.2015.
 */

public class AVL<K extends Comparable<K>, V> extends LinkedBinarySearchTree<K, V> {

    public AVL() {
        super();
//        insert(null,null);
    }

    public void insert(K key, V value) {
//        System.out.println("before inserting");
//        show();
        LinkedNode<K, V> node = new LinkedNode<K, V>(key, value);
        if (getRoot() == null) setRoot(node);
        else setRoot(insertNode(getRoot(), node));

//        System.out.println("after inserting");
//        show();
//        System.out.println();
    }

    public void delete(K key) {
        setRoot(remove(getRoot(), key));
    }

    LinkedNode<K, V> remove(LinkedNode<K, V> p, K k) {
        if (k.compareTo(p.getKey()) < 0) p.setLeft(remove(p.getLeft(), k));
        else if (k.compareTo(p.getKey()) > 0) p.setRight(remove(p.getRight(), k));
        else { // if (k == p->key)
            LinkedNode<K, V> q = p.getLeft();
            LinkedNode<K, V> r = p.getRight();
            if (r == null) return q;
            LinkedNode<K, V> min = r.findMin();
            min.setRight(removeMin(r));
            min.setLeft(q);
            return balance(min);
        }
//        return balance();
        return balance(p);
    }

    LinkedNode<K, V> removeMin(LinkedNode<K, V> p) {
        if (p.getLeft() == null) return p.getRight();
        p.setLeft(removeMin(p.getLeft()));
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

    public LinkedNode balance(LinkedNode<K, V> p) {
//        System.out.println("balancing " + p.getKey());
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
