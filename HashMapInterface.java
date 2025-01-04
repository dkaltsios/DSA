public interface HashMapInterface<K, V> {
    public void put(K key, V value);
    public V get(K key) throws NullPointerException;
    public Node<K, V> remove(K key) throws NullPointerException;
    public int size();
    public boolean isEmpty();
}
