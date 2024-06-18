package chap20;

public class ThreadStateControl01 {
	


	public static void main(String[] args) {
		// sleep(), yield(),join()
		// sleep(): 현재 실행 중인 스레드를 주어진 시간동안 일시정지
		
		
		 
		

	}

}

class Horse extends Thread {
	private int horseNum;//경마번호
	
	// 생성자: 객체 멤버 초기화 작업(경마번호 설정)
	public Horse( int horseNum) {
		this.horseNum = horseNum;
	}
	
	@Override
	public void run() {
		long sleepTime = (long)(Math.random()*500);
		System.out.println(horseNum+"경마 "+sleepTime+"만큼 sleep...");
		
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {}
		
		for (int i=1; i<=10; i++) {
			System.out.println("horseNum "+"번 경마"+100*i+"미터 통과");
		}
	}
	
	
}
