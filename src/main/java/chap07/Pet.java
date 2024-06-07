package chap07;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Pet { // 추상 클래스
	// 공통된 기능 영역
	private boolean wing;
	private int legCount;
	
	// 생성자 : 초기값 설정
//	public Pet(boolean wing, int legCount) {
//		this.wing = wing; this.legCount = legCount;
//	}
	
	// 날 수 있다 없다 판별하는 함수 정의
	public String isWing() {
		String str;
		if (this.wing) {
			str = "날 수 있다.";
		} else {
			str = "날 수 없다";
		}
		return str;
	}
	// 다리 개수 파악하는 함수 정의
	public int getLegCount() {
		return legCount;
	}
	
	
	// 개별 기능 영역
	public abstract void run(String name);  // 추상 메서드

}
