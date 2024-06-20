package chap21;

// 사용자가 직접 작성한 라이브러리 가져오기
import my_db_libmy.mysql.MySqlDriver;
import my_db_libmy.oracle.OracleDriver;

public class MyDBLibTest {
	public static void main(String[] args) {
		
		OracleDriver driver1 = new OracleDriver();
		driver1.connect();
		
		MySqlDriver driver2 = new MySqlDriver();
		driver2.connect();

	}

}
