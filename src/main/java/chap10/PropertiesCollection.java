package chap10;

import java.util.Enumeration;
import java.util.Properties;

public class PropertiesCollection {

	public static void main(String[] args) {
		// HashMap : key와 value => 모든 객체 타입
		// Properties : key와 value에 문자열만 사용
		// : 여러 가지 정보연결
		
		Properties p = new Properties();
		//p.setProperty("score", 100); // error => key,value 타입이 문자열사용가능
		//p.setProperty("score", new Integer(100)); // error
		p.setProperty("score", "100");
		p.setProperty("name", "홍길동");
		p.setProperty("job", "프로그래머");
		
		System.out.println(p);
		
		String name = p.getProperty("name");
		System.out.println(name);
		
		
		//System.out.println("propertyNames(): "+ p.propertyNames());
		Enumeration enu = p.propertyNames();
		while(enu.hasMoreElements()) {
			// Properties객체의 key값 추출
			String key = (String) enu.nextElement();
			
			System.out.println("key: "+key);
			System.out.println(p.getProperty(key));
		}
		
		

	}

}
