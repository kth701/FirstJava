package chap03;

public class Test_Q01 {

	public static void main(String[] args) {
		// 제어문 활용
		
		// 1부터 차례로 자연수를 더할 때 그 합이 1000이상이 되게 하는
		// 최초의 자연수와 그 합을 출력해 보세요.
		
		// 1-10까지의 합=> 반복횟수 => for, 반복회수를 알 수 없을 경우 => while, do-while
		
		// 숫자 발생, 숫자 합계
		int sum = 0;
		int cnt = 1;
		
		while(true) {
			sum += cnt;//+1,+2,+3,.......
			
			if (sum >= 1000) break;
			cnt++; // 2, 3, 4,....
			
		}
		System.out.println("최초로 1000이상이 되게 하는 자연수는 "+cnt);
		System.out.println("1에서 "+cnt+" 까지의 합은 : "+ sum);

	}

}
