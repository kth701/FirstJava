package chap05;

public class NestedClass03 {

	public static void main(String[] args) {
		// 3. 익명 내부 클래스
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("익명 내부 클래스 실행");
				
			}
		}; // end Runnable()
		
		r.run();

		Greeting greeting = new Greeting() {
			
			@Override
			public String sayHello(String name) {
				return name + " 안녕";
			}
		};
		
		System.out.println( greeting.sayHello("동길이"));

	}

}

interface Greeting{
	public String sayHello(String name); // 본체가 없음
}