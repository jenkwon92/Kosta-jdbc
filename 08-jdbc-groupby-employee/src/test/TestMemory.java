package test;

import java.util.ArrayList;
import java.util.HashMap;

public class TestMemory {
	public static void main(String[] args) {
		ArrayList<HashMap<String,String>> list1 = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map1 = new HashMap<String, String>();
		map1.put("name", "짜장면");
		map1.put("price","5000원");
		list1.add(map1	);
		//System.out.println(list1.get(0).get("name"));
		//System.out.println(list1);
		
		HashMap<String,String> map2 = new HashMap<String, String>();
		map2.put("name", "짬뽕");
		map2.put("price","7000원");
		list1.add(map2);
		
		System.out.println(list1.get(0).get("name"));
		//System.out.println(list1);

		for(int i=0;i<list1.size(); i++) {
			HashMap<String, String > m = list1.get(i);
			System.out.println(m.get("name")+" " +m.get("price"));
		}
	}
}
