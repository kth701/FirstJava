package chap05;

public class NestedClass04 {

	public static void main(String[] args) {
		// 정적 중첩 클래스

		Student2.Score score = new Student2.Score("홍길동");
		score.eng = 23;
		score.mat = 21;
		
		score.showInfo();
		
	}

}
