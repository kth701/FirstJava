package chap13;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class CollectMethod05 {

	public static void main(String[] args) {
		// 중간 연산( Intermediate operations)
		
		// map() : 스트림의 요소 하나나에 연한 결과를 그대로 반환 : x -> x
		List<Integer> list = IntStream.rangeClosed(1, 10)
									.map( x  -> x*2)
									.boxed()
									.collect(toList());
		System.out.println(list);
		
		List<Score1> scores = Arrays.asList(
				new Score1("홍길동", 61, 71, 81),
				new Score1("동길이", 62, 72, 42),
				new Score1("동순이", 39, 100, 90)	
				);

		scores.stream()
			.map( x -> {
				if (x.getKor()< 40 || x.getEng() < 40 || x.getEng() <40)
					x.setMsg("과락입니다.");
				else if ( (x.getKor()+x.getMat()+x.getEng())/3 < 60)
					x.setMsg("불합격");
				else 
					x.setMsg("합격입니다.");
				return x;
			})
			.forEach(System.out::println);
		
		System.out.println("---");
		double korAvg = scores.stream()
							.mapToInt( x -> x.getKor())
							.average()
							.getAsDouble();
		System.out.println("국어 평균: "+korAvg);
		
		
		// Optional 클래스 : 결과값이 null인 경우 디폴트 값을 설정
		// isPresent(): 값 유무, orElse(T), orElse(double): 값이 없는 경우 디폴트값 지정
		// ifPresent(Consumer), ifPresent(DobuleConsumer): 값이 있는 경우 처리
		
		
		OptionalDouble optional = scores.stream()
										.mapToInt(x -> x.getKor())
										.average();
		
		if (optional.isPresent())// 값이 존재 하면
			System.out.println(optional.getAsDouble());
		else 
			System.out.println("점수를 처리할 수 없습니다.");
		
		
		System.out.println("---");
		
		int[] numbers = {55,66,77,88,99};
		System.out.println(Arrays.toString(numbers));
		
		long count = Arrays.stream(numbers).count();
		System.out.println("count="+count);
		
		count = Arrays.stream(numbers)
						.filter(x -> x >= 70)
						.count();
		System.out.println("70이상:"+count);
		
		int sum = Arrays.stream(numbers)
						.filter(x -> x>=70)
						.sum();
		System.out.println("70이상:"+sum);
		
		int allSum = Arrays.stream(numbers)
							.map(x -> -x)
							.sum();
		System.out.println("총합: "+allSum);
		
		double avg = Arrays.stream(numbers)
							//.filter(x -> x>=80)
							.average()
							.getAsDouble();
		System.out.println("전체 평균: "+avg);
		
		int max = Arrays.stream(numbers)
						.filter(x -> x>=80)
						.max()
						.getAsInt();
		System.out.println("max: "+max);
		
		int first = Arrays.stream(numbers)
						 .filter(x -> x>60)
						 .findFirst()
						 .getAsInt();
		System.out.println("first : "+first);
		
		int korTot = scores.stream()
							.map(Score1::getKor) //.map(x -> x.getKor())
							.reduce( (a,b)-> a+b)
							.get();
		System.out.println("국어 총합: "+korTot);
							
							

		
		
		
	}

}
