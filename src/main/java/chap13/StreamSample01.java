package chap13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;

public class StreamSample01 {

	public static void main(String[] args) {
		// 스트림의 장범
		// 람다식 사용 가능, 대용량의 데이터를 병렬 처리, 데이터를 최종처리전에 여러가지 작업 처리 가능
		
		// 스트림 : 대용량의 데이터를 빠르게 처리하기 위해 도입.
		var list = Arrays.asList(1,2,3,2,1,5);
		System.out.println(list.toString()); // 배열의 내용을 표시 -> Arrays.toString(배열이름)
		
		List<Integer> list2 = new ArrayList<Integer>( new HashSet<Integer>(list) );
		// 확장 for
		System.out.println("-- 중복 제거 : HashSet()");
		for (int n : list2) {
			System.out.println(n);
		}
		// stream
		System.out.println("-- stream");
		list.stream().distinct().forEach( System.out::println);
		
		
		// 배열을 스트림의 형태로 전환하는 객체
		String[] strArr = {"홍길동","홍길순","동길이","동순이"};
		int[] intArr = {3,6,9};
		double[] floatArr = {3.1, 6.1, 7.2};
		
		
		// 스트림 객체 생성
		Stream<String> strStm = Arrays.stream (strArr);
		IntStream intStm = Arrays.stream(intArr);
		DoubleStream doubleStm = Arrays.stream(floatArr);
		
		
		// 스트림 출력1
		strStm.forEach(System.out::println);
		intStm.forEach(System.out::println);
		doubleStm.forEach(System.out::println);
		// 스트림 출력2
		Arrays.stream(strArr).forEach( x -> System.out.println(x));
		
		// 사용자가 정이한 class자료형 스트림처리하기
		DataObj[] data = { 
				new DataObj(1, "홍길동"),
				new DataObj(2, "동길이"),
				new DataObj(3, "김갑돌")
				};
		
		Stream<DataObj> stm = Arrays.stream(data);
		stm.forEach( x -> {
			System.out.printf("번호: %d, 이름:%s\n", x.getNo(), x.getName());
		});
		

	}

}

// 사용자가 정의한 자료형
@Data@AllArgsConstructor
class DataObj{
	private int no;
	private String name;
}
