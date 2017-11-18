import java.util.*;

public class Hashtable{

	private ArrayList<HashNode> data;
	private final int length = 50000; 
	private int size; 


	public Hashtable() {
		
		data = new ArrayList<>();
		size = 0; 

		for(int i = 0; i < length; i++)
			data.add(null);

	}

	public int size(){
		
		return size; 
	}

	public int getIndex(String key){
		
		int hashCode = key.hashCode();
		int index = Math.abs(hashCode) % length;
		return index; 
	}

	//Returns “true” if a key/value object pair (with the key matching the argument and any value).

	public boolean containsKey(String key){
		
		int index = getIndex(key);
		HashNode node = data.get(index);

		while(node != null){
			if(node.getKey().equals(key)){
				return true;
			}
			node = node.next();
		}
		return false;
	}
	//Returns the value associated with the key which is passed as an argument; 
	//returns null if no key/value pair is contained by the Hashtable instance. 

	public String get(String key){
		int index = getIndex(key);
		HashNode headNode = data.get(index);

		while(headNode != null){
			if(headNode.getKey().equals(key)){
				return headNode.getValue();
			}
			headNode = headNode.next();
		}
		return null; 
	}

	//Adds the key/value pair into the Hashtable instance.
	// If there is an existing key/value pair, the Hashtable instance replaces the stored value with the 
	//argument value. 

	public void put(String key, String value){
		
		HashNode node = null;

		int index = getIndex(key);
		node = data.get(index);

		while(node != null){
			if(node.getKey() == key){
				node.setValue(value);
			}
			node = node.next();
		}
		size++;

		HashNode head = data.get(index);
		HashNode node2 = new HashNode(key, value);
		node2.setNext(head);

		data.set( index, node2);
	}
	//Removes the key/value pair from the Hashtable instance and returns the value associated with the key to the caller. 
	//Throws an Exception instance if the key is not present in the Hashtable instance. 

	public String remove(String key){
		
		int index = getIndex(key);
		HashNode node = data.get(index);
		HashNode node2 = null; 

		while(node != null){
			if(node.getKey().equals(key)){
				String temp = node.getValue();
				if(node.next() == null){
					node = node.next;
					data.set(index, node);
				} else{
					node2.setNext(node.next());

				}
				size--;
				return temp;
			}

			node2 = node;
			node = node.next();
		}

		return null;
	}


	public class HashNode{
		
		private String key;
		private String value; 
		private HashNode next; 

		public HashNode(String key, String value){
			this.key = key;
			this.value = value;
		}
		public String getKey(){
			return key; 
		}
		public String getValue(){
			return value; 
		}
		public void setValue(String value) {
			this.value = value;
		}	
		public void setNext(HashNode node){
			next = node; 
		}
		public HashNode next() {
			return next; 
		}

	}
}