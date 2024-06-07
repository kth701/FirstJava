package chap13;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;

public class CollectMethod07 {

	public static void main(String[] args) {
		// mapToObj():  스트림 요소 연산하는 과정에서 자료형을 변경 할 경우
		// mapToDouble(), mapToInt(), maptoLong()
		
		Map<Boolean, List<Long>> m1 = 
				IntStream.rangeClosed(1, 10)
						.mapToLong(x -> x) 	// LongStream자료형전환 (중간연산)
						.boxed()			// Stream<Long>
						.collect(Collectors.partitioningBy(i -> i % 2 == 0)); // true,false => key, data=> value
		System.out.println(m1);
		
//		System.out.println("-- stream()");
//		m1.keySet().stream()
//				.forEach( key -> {
//					if (key) {
//						System.out.println("\n-- 짝수" );
//					} else {
//						System.out.println("\n-- 홀수" );
//					}
//					// 람다식 안에서 외부 변수를 적용할 경우 : final 배열구조 = {0}
//					List<Long> numbers = m1.get(key);
//					numbers.forEach( n -> {
//						System.out.print(n+" ");
//					});
//				});
//		System.out.println("-- stream(): 2");
//		List<List<Long>> odd = m1.keySet().stream()
//							 			.filter( n -> n)
//							 			.map(n -> {
//							 				List<Long> numbers = m1.get(n);
//							 				return numbers;
//							 			})
//							 			.collect(toList());
//		System.out.println(odd);
		
		System.out.println("\n--- for");
		for ( boolean key : m1.keySet()) {
			System.out.println("\nkey: "+key);
			
			if (key) {
				System.out.println("-- 짝수" );
			} else {
				System.out.println("-- 홀수" );
			}
			
			List<Long> numbers = m1.get(key);
			numbers.forEach( n -> {
				System.out.print(n+" ");
			});
			
		}// end for
		
		System.out.println("\n-- mapToObj");
		Map<Boolean, List<Long>> m2 = 
				IntStream.rangeClosed(1, 10)
						.mapToObj(Long::new) // x -> new Long(x)
						.collect(Collectors.partitioningBy(i -> i%2 == 0));
		System.out.println(m2);
		
		// filter(Predicate함수형 인터페이스: x -> boolean) : 스트림 요소를 원하는 데이터만 제어할 경우
		
		List<Integer> list1 = Arrays.asList(1,3,5,7,9);
		List<Integer> list2 = Arrays.asList(2,4,6,8,10);
		
		List<Integer> list3 = Stream.of(list1, list2)
									.flatMap(List::stream)	// (x -> x.stream() )
									.filter( x -> x % 3 == 0) 
									.collect(Collectors.toList());
		System.out.println(list3);
		
		System.out.println("-- 중간 연산 예");
		List<String> strList = Arrays.asList("RED","bad", "BED","Web","rad");
		
		strList.stream()
				.filter(x -> 0 < x.toUpperCase().indexOf("ED") )
				.forEach(System.out::println);
		
		System.out.println("-- ED가 포함된 단어");
		
		Predicate<String> condition1 = x -> 0 < x.toUpperCase().indexOf("ED");
		strList.stream().filter(condition1).forEach(System.out::println);
		
		System.out.println("-- ED가 포함되지 않은 단어");
		Predicate<String> condition2 = Predicate.not(condition1);
		strList.stream().filter(condition2).forEach(System.out::println);
		
		// sorted() : 스트림 요소를 정렬할 경우, 정렬 기준: ascending기본
		Integer [] int1 = {1,3,5,7,9};
		Integer[] int2 = {2,4,6,8,10};
		
		Stream<Integer[]> strm1 = Stream.of(int1, int2);
		List<Integer> strm1_list = strm1
								//.flatMap( x  -> Arrays.stream(x))
								.flatMap(Arrays::stream)
								.sorted()
								.collect(Collectors.toList());
		System.out.println(strm1_list);
		
		Stream<Integer[]> strm2 = Stream.of(int1, int2);
		List<Integer> strm2_list = strm2
									.flatMap(Arrays::stream)
									.sorted(Comparator.reverseOrder()) // Descending
									.collect(Collectors.toList());
		System.out.println(strm2_list);
		
		System.out.println("-- 학생 점수 정렬");
		
		List<Score1> scores = Arrays.asList(
				new Score1("홍길동", 61, 71, 81),
				new Score1("동길이", 62, 72, 42),
				new Score1("동순이", 39, 100, 90)	
				);
		System.out.println(scores);
		
		System.out.println("-- 이름 순 정렬"); // 문자열 정렬 x.compareTo(y)
		List<Score1> sortName = scores.stream()
										//.sorted( (x,y)-> x.getName().compareTo(y.getName())) 	// 오름차순
										.sorted( (x,y)-> y.getName().compareTo(x.getName())) 	// 내림차순
										.collect(Collectors.toList());
		//System.out.println(sortName);
		sortName.stream().forEach(System.out::println);
		
		System.out.println("-- 영어 성적순으로 역정렬"); // 숫자자료형 정렬 x - y
		List<Score1> engScore = scores.stream()
										.sorted( (x,y) -> y.getEng() - x.getEng())
										.collect(Collectors.toList());
		engScore.stream().forEach(System.out::println);
		
		System.out.println("-- 영어 성적순으로 정렬");
		scores.stream()
				.sorted( (x,y) -> x.getEng() - y.getEng())
				//.collect(Collectors.toList())
				.forEach(System.out::println);
		
		
		// 클래스에서 특정 필드 기준으로 정렬 :메서드 참조
		List<ScoreBoard> boards = Arrays.asList(
				new ScoreBoard("홍길동", 61, 71, 81),
				new ScoreBoard("동길이", 62, 72, 42),
				new ScoreBoard("동순이", 39, 100, 90)					
				);
		System.out.println("-- 클래스에서 특정 필드 기준으로 정렬 :메서드 참조");
		boards.stream().forEach(System.out::println);
		System.out.println("-- sorted");
		boards.stream()
				.sorted()
				.collect(Collectors.toList())
				.forEach(System.out::println);
		
		System.out.println("-- distinct()");
		List<Integer> numberList = Arrays.asList(3,3,3,3,3,4,4,4,4,6,6,6,6);
		numberList.stream()
					.distinct()
					.sorted(Comparator.reverseOrder())
					.forEach(System.out::println);
		
		// peek(): 중간 연산 중에 중간에 처리된 요소의 값을 확인
		System.out.println("-- peek()");
		
		Stream.of("mouse","cow","hourse","monkey")
			.peek( x -> {
					System.out.println("---");
					System.out.println("처리할 요소: "+x);
					System.out.println("---");
				}
			)
			.map(String::toUpperCase)
			.peek(x -> {
				System.out.println("---");
				System.out.println("처리한 요소: "+x);
				System.out.println("---");
				}
			)
			.forEach(x -> {
				System.out.println("-- 최종 연산  결과");
				System.out.println(x);
			});
		
		// limit(), skip() : 스트림 요소를 제한
		System.out.println("-- skip()");
		Stream.of("홍길동","동순이","김길동","김갑순")
				.limit(3) // 0 ~ n-1: 처음부터 n-1개까
				.forEach(System.out::println);
	
		Map<String, Long> info = Stream.of("패","승","패","승","승","승","패")
										.skip(2) // 앞에서 2경는 연습경기
										.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		System.out.println(info);
		
		// Stream.generate(): 요소를 무한정 비순차적으로 생성할 때
		Stream.generate(Math::random)
				.map( n->(n*9)+1)
				.map(Math::round)
				.limit(10)
				.sorted()
				.forEach(System.out::println);
		
		// UUID: 유일한 랜덤 키값을 얻고자할 때 사용 - 고유한 128비트 값, 총32자구성
		Stream.generate(UUID::randomUUID)
				.limit(5)
				.forEach(System.out::println);
		

	}

}

@Data
@AllArgsConstructor
class ScoreBoard implements Comparable<ScoreBoard> {
	private String name;
	private int score1;
	private int socre2;
	private int score3;
	
	@Override
	public int compareTo(ScoreBoard o) {
		
		// 문자형 
		// 오름차순
		//return  this.getName().compareTo(o.getName());
		return  o.getName().compareTo(this.getName());
		
		// 숫자형
		//return  this.getScore1() - o.getScore1()
		//return  o.getScore1() - this.getScore1();
	}
	
	
}















