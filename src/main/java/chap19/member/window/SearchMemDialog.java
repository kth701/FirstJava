package chap19.member.window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import chap19.member.controller.MemberController;

public class SearchMemDialog extends JDialog {
	
	JPanel panelSearch, panelBtn;
	JTextField lMemeName;
	
	JButton btnSearch;
	JButton btnReg;
	JButton btnModify;
	JButton btnDelete;
	
	JTable memTable;
	// 테이블모델
	String[] columnNames = { "아이디","비밀번호","이름","주소", "전화번호"};
	
	Object[][] memItems = new String[0][5];// 테이블에 표시될 회원 정보 저장(2차원 배열)
	int rowIdx = 0, colIdx = 0; // 태이블 수정시 선택한 행과열 인덱스 저장
	
	// 회원 관리 컨트롤러는 입력,수정,조회,삭제 작업요청시 작업처리하는 객체
	MemberController memberController;
	
	// 생성자
	public SearchMemDialog(MemberController memberController, String str) {
		this.memberController = memberController;
		
		setTitle(str);
		init();
	}
	
	// UI 객체 생성하는 메서드 
	public void init() {
		
	}

}
