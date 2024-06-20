package chap22;


import mysql_goods.MySqlGoods;
import oracle_goods.OracleGoods;

public class ModuleTest {

	public static void main(String[] args) {
		OracleGoods g = new OracleGoods();
		g.listGoods();

		System.out.println("----");
		
		MySqlGoods m = new MySqlGoods();
		m.listGoods();

	}

}

/*  모듈 프로젝트
 * module-info.java 파일 생성하여 아래 내용 추가
 * 
 * module oracle_module {
	exports oracle_goods;
	}
 * 
 * 모듈 가져와서 사용하기
 * module-info.java 파일 생성하여 아래 내용 추가
 * 
 * module MyModuleTest2(프로젝트와 동일한 모듈명) {
		requires oracle_module;
	}
	
  이클리스 환경에서는 modulepath를 등록해야함.
  프로젝트 -> Build Path -> Configure .. -> Projects탭 -> Module Path -> Add버튼 등록
 */
