package chap10;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorCollection {

	public static void main(String[] args) {
		// Iterator : 자료를 처음부터 끝까지 차례대로 처리한는 인터페이스
		
		List<String> list = new ArrayList<String>();
		list.add("dog");list.add("cat");list.add("lion");
		
		System.out.println("1. forEach");
		list.forEach( x -> System.out.println(x));
		
		// Iterator 기능
//		for (int i=0; i<list.size();i++) {
//			System.out.println( list.get(i)   );
//		}
		
		System.out.println("2. 확장 for");
		for( String s : list)
			System.out.println(s);
		
		System.out.println("3. Iterator");
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		System.out.println("5. Iteraotr활용 : 람다");
		Iterator<String> it2 = list.iterator();
		it2.forEachRemaining( x -> System.out.println(x));
		
		System.out.println("--");
		list.forEach( x -> System.out.println(x));
		
		
		
		

	}

}
