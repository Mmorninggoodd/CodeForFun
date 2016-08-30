/*

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

*/

public class LRUCache {
    class Node {
        private int key;
        private int value;
        Node prev;
        Node next;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }
    Map<Integer, Node> map;
    int capacity;
    Node head;
    Node tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private void add(Node node) {
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;
    }
    public int get(int key) {
        Node node = map.get(key);
        if(node == null) return -1;
        remove(node);
        add(node);
        return node.value;
    }
    
    public void set(int key, int value) {
        if(map.containsKey(key)) {
            if(get(key) != value) map.get(key).value = value;
        }
        else {
            if(map.size() == capacity) {
                map.remove(tail.prev.key);  // remove from map
                remove(tail.prev);
            }
            Node node = new Node(key, value);
            add(node);
            map.put(key, node);
        }
    }
}