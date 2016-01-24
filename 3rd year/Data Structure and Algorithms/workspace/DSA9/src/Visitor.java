/**
 * Created by Somal on 19.10.2015.
 */
public interface Visitor {
    <K,V> void visit(LinkedNode<K,V> node);
}
