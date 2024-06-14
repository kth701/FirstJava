package chap19.member.window;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import chap19.common.RentTableModel;
import chap19.member.controller.MemberController;
import chap19.member.vo.MemberVO;

public class SearchMemDialog extends JDialog {
	
	JPanel panelSearch, panelBtn;
	JLabel lMemName;
	JTextField tf;
	
	JButton btnSearch;
	JButton btnReg;
	JButton btnModify;
	JButton btnDelete;
	
	// 테이블 UI view
	JTable memTable;
	// 테이블UI 모델객체
	RentTableModel rentTableModel;
	
	String[] columnNames = { "아이디","비밀번호","이름","주소", "전화번호"};
	
	Object[][] memItems = new String[0][5];// 테이블에 표시될 회원 정보 저장(2차원 배열)
	int rowIdx = 0, colIdx = 0; // 태이블 수정시 선택한 행과열 인덱스 저장
	
	// 회원 관리 컨트롤러는 입력,수정,조회,삭제 작업요청시 작업처리하는 객체
	MemberController memberController;
	
	// 생성자
	public SearchMemDialog(MemberController memberController, String str) {
		this.memberController = memberController;
		
		setTitle(str);// 다이얼 창의 제목
		init();// UI객체생성하는 메서드 호출
	}
	
	// UI 객체 생성하는 메서드 
	public void init() {
		memTable = new JTable();
		
		ListSelectionModel rowSel = memTable.getSelectionModel();
		rowSel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// ListSelection 이벤트 처리
		//rowSel.addListSelectionListener(이벤트 핸들러 );
		
		panelSearch = new JPanel();
		panelBtn = new JPanel();
		
		lMemName = new JLabel("이름");
		tf = new JTextField("회원 이름을 입력하세요.");
		
		// 검색에 관련 UI Panel
		panelSearch.add(lMemName);
		panelSearch.add(tf);
		panelSearch.add(btnSearch);
		
		btnReg = new JButton("회원등록하기");
		btnModify = new JButton("수정하기");
		btnDelete = new JButton("삭제하기");
		
		// 각 버튼에 대한 이벤트 핸들러
		
		// 버튼에 관련 UI Panel
		panelBtn.add(btnDelete);
		panelBtn.add(btnModify);
		panelBtn.add(btnDelete);
		
		// Table설정
		// 테이블 데이터 모델 생성
		rentTableModel = new RentTableModel();
		// 테이블 UI view에 테이블이블 데이터 모델을 설정
		memTable.setModel(rentTableModel); 
		
		// 각 panel을 대화창(JDialog)에 배치
		add(panelSearch, BorderLayout.NORTH);
		add(new JScrollPane(memTable), BorderLayout.CENTER);		
		add(panelBtn, BorderLayout.SOUTH);
		
		
		setLocation(300,100);	// 다이얼로그 출력 위치
		setSize(800,600); 		// 창의 크기
		setModal(true); 		// 항상 부모창 위에 표시
		setVisible(true);		// 대화창을 화면에 표시
	
	}
	
	private void loadTableData(List<MemberVO> memList) {
		
		// 넘겨받은 회원정보 List에 회원정보가 있으면 처리
		if (memList!= null && memList.size() != 0) {
			
			// 회원정보 -> 테이블 데이터로 전환
			memItems = new String[memList.size()][5];// List의 개수 만큼 테이블 행을 설정
			
			for (int i=0; i<memList.size(); i++) {
				MemberVO memVO = memList.get(i);
				
				memItems[i][0] = memVO.getMemId();
				memItems[i][1] = memVO.getMemPassword();
				memItems[i][2] = memVO.getMemName();
				memItems[i][3] = memVO.getMemAddress();
				memItems[i][4] = memVO.getMemPhoneNum();
			}
			
			// 테이블 데이터 모델 설정
			rentTableModel = new RentTableModel(memItems, columnNames);
			// 테이블 UI view에 테이블 데이터 모델 설정
			memTable.setModel(rentTableModel);
		} else {
			//  전달 받은 데이터가 없을 경우 처리
			//System.out.println("조회한 정보가 없습니다.");
			messge("조회한 정보가 없습니다.");
			
			memItems = new Object[10][0];
			rentTableModel = new RentTableModel(memItems, columnNames);
			memTable.setModel(rentTableModel);
			
		}
		
		
	}// end loadTableData()
	
	
	// 메시지 전달한 메서드
	private void messge(String msg) {
		JOptionPane.showMessageDialog(this, msg, "메시지 박스", JOptionPane.INFORMATION_MESSAGE);
	}

}


