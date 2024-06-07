package chap15;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class SerializableClass {

	public static void main(String[] args) {
		
		String path =  "C:"+File.separator+"javaStudy"+File.separator+"download"+
				File.separator;
		System.out.println(path);
		
		// 객체 직렬화 기능 : 객체의 상태를 전송하거나 파일로 저장하는 경우
		Employee e1 = new Employee("홍길동", "서울", "123456-1234567", "010-1234-1234");
		Employee e2 = Employee.builder()
				.name("동순이").address("부산").jumin("12347-2345678").phone("010-2222-2222")
				.build();
		
		List<Employee> eList = new ArrayList<Employee>();
		eList.add(e1); eList.add(e2);
		
		try ( FileOutputStream fos = new FileOutputStream(new File(path+"serial.ser"));
				ObjectOutputStream oos = new ObjectOutputStream(fos);){
			
			oos.writeObject(eList);
			System.out.println("직렬화 객체를 파일로 저장했습니다.");
			
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		// 직렬화 객체 역 직렬화
		try ( FileInputStream fis 	= new FileInputStream(new File(path+"serial.ser"));
			  ObjectInputStream ois = new ObjectInputStream(fis);) {
			
			List<Employee> list = (ArrayList<Employee>) ois.readObject();
			
			System.out.println("-- 사원 정보 --");
			list.stream()
				.forEach( e -> {
					System.out.printf("이름: %s, 주소: %s, 주민번호: %s, 전화번호: %s\n",
									   e.getName(), e.getAddress(),e.getJumin(),e.getPhone());
				});
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Employee implements Serializable{
	
	// serialVersionUID : 역직렬화 시 컴파일러는 serialVersionUID가 동일한지 판별한 후, 
	// 동일하면 역직렬화를 수행
	
	//private static final long seriUID = 10L;
	//private static final long serialVersionUID = -8309779839588169825L;
	
	private String name;
	private String address;
	// transient: 직렬화 제외
	transient private String jumin;
	private String phone;
}
