package Test;

import static java.lang.System.out;

import cc.openhome.LinkList;

public class Test_RemoveDuplicatesFromSortedList {
	public static void main(String[] args) {
		LinkList linkList = new LinkList();
//		LinkList.ListNode node = new LinkList().new ListNode(3);
		linkList.add(1);
		linkList.add(1);
		linkList.add(1);
		linkList.add(2);
		linkList.add(2);
		linkList.add(2);
		linkList.add(2);
		linkList.add(3);
		int count = 0;
		int i=1;
		int originSize = linkList.getSize();
		while(i<originSize) {
			if(linkList.get(count).getVal() == linkList.get(count+1).getVal()) {
				if(count+2>=linkList.getSize())
					linkList.get(count).setNext(null);
				else
					linkList.get(count).setNext(linkList.get(count+2));
				i++;
			}
			else {
				count++;
				i++;
			}
		}
		out.println(linkList.toString());
	}
}

