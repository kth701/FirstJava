package chap21;

public class Test {
	public static void main(String[] args) {
		String name = "홍길동";
		String name2 = "홍길동";
		String name3 = new String("홍길동");
		
		System.out.println(name.hashCode()+", "+name2.hashCode());
		System.out.println(name3.hashCode());
	}

}
