package cc.openhome;

import java.util.ArrayList;

public class GenericGetMin {
	public static void main(String[] args) {
		GenericGetMin t = new GenericGetMin();
		ArrayList<Double> list = new ArrayList<Double>();
		list.add(12.1);
		list.add(11.4);
		list.add(17.3);
		System.out.println(t.min2(list));
	}
	public <T extends Comparable<T>> T min1(ArrayList<T> list){
		T min = list.get(0);
		for(int i=0;i<list.size();i++) {
			if(min.compareTo(list.get(i))>0)
				min = list.get(i);
		}
		return min;
	}
	
	public <T extends Number & Comparable<T>> T min2(ArrayList<T> list){
		T min = list.get(0);
		for(int i=0;i<list.size();i++) {
			if(min.compareTo(list.get(i))>0)
				min = list.get(i);
		}
		return min;
	}
}
