import java.util.*;
import java.util.EmptyStackException;
public class Hashtable{
	private static class ListNode{
		String key;
		String value;
		ListNode next ; 

	}
	private ListNode[]table;
	private int c;

	public Hashtable(){
		table = new ListNode[100];
	}
	public boolean containsKey(String key){
		int bucket = hash(key);
		ListNode list = table[bucket];
		while(list != null ){
			if(list.key.equals(key))
				return true;
			list = list.next;
		}
		return false;
	}

	public String remove(String key){
		int bucket = hash (key);
		if(containsKey(key)== false){
			throw new  EmptyStackException();
		}
		if(table[bucket]== null)
			throw new  EmptyStackException();
		if(table[bucket].key.equals(key)){
			table[bucket]= table[bucket].next;
			c--;
			return key;
		}
		ListNode prev = table[bucket];
		ListNode current = prev.next;
		while(current!= null && !current.key.equals(key)){
			current = current.next;
			prev = current;
		}
		if(current!= null){
			prev.next =current.next;
			c--;
		}
		return null;
	}


	public void put ( String key, String value ){
		int bucket = hash(key);
		ListNode list = table[bucket];
		while(list!= null ){
			if(list.key.equals(key))
				break;
			list = list.next;
		}
		if(list != null){
			list.value = value;
		}else{
			ListNode node = new ListNode();
			node.key = key;
			node.value = value;
			node.next = table[bucket];
			table[bucket] = node;
			c++;
		}
	}

	public String get(String key){
		int bucket = hash(key);
		ListNode list =table[bucket];
		while( list != null ){
			if(list.key.equals(key))
				return list.value;
			list = list.next;
		}
		return null;
	}

	private int hash (String key){
		return (Math.abs(key.hashCode()))% table.length;
	}

}