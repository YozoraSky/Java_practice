package cc.openhome;

public class LinkList {
	public class ListNode{
		private int val;
		private ListNode next;
		ListNode(int x){
			this.val = x;
		}
		
		public int getVal() {
			return val;
		}
		
		public void setVal(int val) {
			this.val = val;
		}
		
		public ListNode getNext() {
			return next;
		}
		
		public void setNext(ListNode node) {
			this.next = node;
		}
	}
	
	private ListNode first;
	
	public void add(int elem) {
		ListNode node = new ListNode(elem);
		if(first==null) {
			first = node;
		}
		else
			append(node);
	}
	
	private void append(ListNode node) {
		ListNode last = first;
		while(last.next!=null) {
			last = last.next;
		}
		last.next = node;
	}
	
	public int getSize() {
		int count = 0;
		ListNode last = first;
		while(last!=null) {
			last = last.next;
			count++;
		}
		return count;
	}
	
	public ListNode get(int index) {
		int size = getSize();
		if(index >= size) {
			throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", index, size));
		}
		return findElem(index);
	}
	
	private ListNode findElem(int index) {
		int count = 0;
		ListNode last = first;
		while(count < index) {
			last = last.next;
			count++;
		}
		return last;
	}
	
	public void remove(int index) {
		findElem(index-1).next = findElem(index + 1);
	}
	
	public String toString() {
		ListNode last = first;
		String string = "";
		while(last!=null) {
			string = string + last.val + "->";
			last = last.next;
		}
		string += "null";
		return string;
	}
}
