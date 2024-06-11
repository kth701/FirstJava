package chap17;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JTableTest extends JFrame{
	
	JTable table;
	Object[] columnNames = {"사번","이름", "근무부서"};
	String[][] data = {
			{"220000","박길동","총무부"},
			{"220001","홍길동","관리부"},
			{"220002","이순신","회계부"},
			{"220003","임꺽정","개발부"},
			{"220004","강감찬","관리부"},
			{"220005","제임스","총무부"},
			{"220006","이길동","총무부"},
			{"220007","이갑돌","회계부"},
			{"220008","김유신","영업부"},
			{"220009","동순이","영업부"},
			{"220010","동길이","개발부"},
			{"220011","세종","영업부"},
			{"220012","흥부","회계부"},
			{"220013","놀부","총무부"}
	};
	
	
	public JTableTest() {
		table = new JTable(data, columnNames ); /// 배열을 TablModel로 이용해서 JTable생성
		setTitle("사원 정보 테이블");
		add(new JScrollPane(table)); // 스크롤 객체 테이블을 추가
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 컬럼 사이즈 조절시 양쪽 컬럼의 사이즈도 변경
		//table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
		
		// 컬럼 사이즈 조절시 마지막 컬럼의 크기만 변경
		//table.setAutoResizeMode(table.AUTO_RESIZE_LAST_COLUMN);
		
		// 컬럼 사이즈 조절시 다음 열의 크기만 변경
		//table.setAutoResizeMode(table.AUTO_RESIZE_NEXT_COLUMN);
		
		// 컬럼 사이즈를 자동으로 절하지 않고, 스브롤바를 이용
		//table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
		
		// 테이블을 포함하는 화면 크기 조절시 모든 테이블의 열의 크기를 균등하게 유지: 기본 속성
		table.setAutoResizeMode(table.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		
		pack();
		setVisible(true);
		
	}

	public static void main(String[] args) {
		// JTable : 데이터를 목록 
		
		// TableModel작성(테이블에 넣을 데이터) -> 
		// 스크롤기능있는 테이블( JTable작성 -> JScrollPanel에 JTable추가) -> 
		// Container에 추가
		
		JTableTest table = new JTableTest();
		

	}

}
