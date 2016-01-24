
public class LinkedNode<K, V> implements Comparable<LinkedNode<K, V>> {
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

    public LinkedNode<K, V> findMin() {
        LinkedNode<K, V> node;
        for (node = this; node.getLeft() != null; node = node.getLeft()) {
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
