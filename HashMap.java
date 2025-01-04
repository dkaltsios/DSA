public class HashMap<K, V> implements HashMapInterface<K, V> {
    private final int CAPACITY = 16;
    private int size = 0;
    private final Node<K, V> table[];

    @SuppressWarnings("unchecked")
    public HashMap() {
        this.table = (Node<K, V>[]) new Node[CAPACITY];
    }   


    @Override
    public void put(K key, V value) throws NullPointerException {
        // Error handling
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }

        // Get a random index and check if a node already exists
        int index = Math.abs(key.hashCode() % CAPACITY);
        Node<K, V> node = table[index];

        // If the node is null, create a new node
        if (node == null) {
            table[index] = new Node<>(key, value);
            // Increase size
            size++;
        } else {
            // Else if a next node exists and the key is the same, update the value
            while (node.getNext() != null) {
                if (node.getKey().equals(key)) {
                    node.setValue(value);
                    return; 
                }
                node = node.getNext();
            }
            
            // Check the last node as well and if there isn't a node with the same key, create a new node
            if (node.getKey().equals(key)) {
                node.setValue(value);
                return;
            }
            node.setNext(new Node<>(key, value));
            // Increase size
            size++;
        }
    }


    @Override
    public V get(K key) throws NullPointerException {
        // Error handling
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }

        // Get a random index and check if a node already exists
        int index = Math.abs(key.hashCode() % CAPACITY);
        Node<K, V> node = table[index];
        
        // If the key exists, return the value
        while (node != null) {
            if (node.getKey().equals(key)) {
                return node.getValue();
            }
            node = node.getNext();
        }

        // If the key doesn't exist, return null
        return null;
    }


    @Override
    public Node<K, V> remove(K key) throws NullPointerException {
        // Error handling
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }

        // Get a random index and check if a node already exists
        int index = Math.abs(key.hashCode() % CAPACITY);
        Node<K, V> node = table[index];

        // If the node doesn't exist, return
        if (node == null) {
            return null;
        }

        // If it's the first node (the head) isolate it and return the removed node
        if (node.getKey() == key) {
            table[index] = node.getNext();
            node.setNext(null);
            // Decrease size
            size--;
            return node;
        }

        // Tail
        Node<K, V> prev = node;
        node = node.getNext();

        // Iterate through the tail
        while (node != null) {
            if (node.getKey().equals(key)) {
                prev.setNext(node.getNext());
                node.setNext(null);
                // Decrease size
                size--;
                return node;
            } else {
                // Iterate through the next node
                prev = node;
                node = node.getNext();
            }
        }

        // If the key isn't found return null
        return null;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    public boolean containsKey(K key) throws NullPointerException {
        // Error handling
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }

        // Get a random index and check if a node already exists
        int index = Math.abs(key.hashCode() % CAPACITY);
        Node<K, V> node = table[index];

        // If the key exists, return true
        while (node != null) {
            if (node.getKey().equals(key)) {
                return true;
            }
            node = node.getNext();
        }

        // If the key doesn't exist, return false
        return false;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CAPACITY; i++) {
            if (table[i] != null) {
                sb.append(i).append(" ").append(table[i].toString()).append("\n");
            } else {
                sb.append(i).append(" ").append("null").append("\n");
            }
        }
        return sb.toString();
    }
}
