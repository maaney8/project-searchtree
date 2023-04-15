/**
 * Implementation of SimpleMap using a custom binary search tree
 */
public class CustomBinaryTreeSimpleMap<K extends Comparable<K>, V> implements SimpleMap<K, V> {

	static class Node<K extends Comparable<K>, V>{
		K key;
		V value;
		Node <K, V> left;
		Node <K, V> right;
		
		Node (K key, V value){
			this.key = key;
			this.value = value;
			this.left = this.right = null;
		}
	}
	
	// The root of the tree
	private Node<K, V> root;
	
	// Returns the node with the given key or null if not found
	private Node<K, V> find (Node<K, V> root, K key){
		if (root == null) {
			return null;
		}
		int cmp = key.compareTo(root.key);
		if (cmp == 0) {
			return root;
		}else if (cmp < 0) {
			return find(root.left, key);
		} else {
			return find(root.right, key);
		}
	}
	
	// Inserts the given key/value pair into the tree rooted at root
	private Node<K, V> insert(Node<K, V> root, K key, V value){
		if(root == null) {
			return new Node<K, V> (key, value);
		}
		int cmp = key.compareTo(root.key);
		if (cmp == 0) {
			root.value = value;
		}else if (cmp < 0) {
			root.left = insert(root.left, key, value);
		} else {
			root.right = insert(root.right, key, value);
		}
		return root;
	}
	
	// Construct an empty tree
	CustomBinaryTreeSimpleMap(){
		root = null;
	}
	
	
    @Override
    public void clear() {
        // TODO
    	root = null;
    }
    
    @Override
    public V put(K key, V value) {
        // TODO
    	Node<K, V> node = find(root, key);		//Find the node with the given key
    	if (node == null) {
    		root = insert(root, key, value);	// if node is not found insert the key/value pair into the tree
    	return null;
    	}else {
    		V old = node.value;		// Node is found, update the value
    		node.value = value;		// Update the value
    		return old;
    	}
    }

    @Override
    public V get(K key) {
        // TODO
    	Node<K, V> node = find(root, key);
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        // TODO
        return find(root, key) != null;
    }

    @Override
    public boolean isEmpty() {
        // TODO
        return root == null;
    }
    
}
