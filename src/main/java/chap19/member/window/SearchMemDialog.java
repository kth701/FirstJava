package chap19.member.window;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
		
		// 테이블의 행 선택 객체 // 선택방법 : 단일 선택(SINGLE_SELECTION)
		ListSelectionModel rowSel = memTable.getSelectionModel();
		rowSel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// 테이블의 열 선택 객체
		ListSelectionModel colSel = memTable.getColumnModel().getSelectionModel();
		colSel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		
		panelSearch = new JPanel();
		panelBtn = new JPanel();
		
		lMemName = new JLabel("이름");
		tf = new JTextField(15);
		btnSearch = new JButton("조회하기");
	
		
		// 검색에 관련 UI Panel
		panelSearch.add(lMemName);
		panelSearch.add(tf);
		panelSearch.add(btnSearch);
		
		btnReg 		= new JButton("회원등록하기");
		btnModify 	= new JButton("수정하기");
		btnDelete 	= new JButton("삭제하기");
		
		
		// 각 버튼에 대한 이벤트 핸들러
		btnReg.addActionListener(new MemberBtnHandler());
		btnModify.addActionListener(new MemberBtnHandler());
		btnDelete.addActionListener(new MemberBtnHandler());
		btnSearch.addActionListener(new MemberBtnHandler());
		
		
		// 버튼에 관련 UI Panel
		panelBtn.add(btnDelete);
		panelBtn.add(btnModify);
		panelBtn.add(btnDelete);
		
		//------------------------ //
		// Table설정
		// -----------------------//
		
		// 테이블 데이터 모델 생성
		rentTableModel = new RentTableModel(memItems, columnNames);
		// 테이블 UI view에 테이블이블 데이터 모델을 설정
		memTable.setModel(rentTableModel); 
		
		// 테이블 이벤트 핸들러
		rowSel.addListSelectionListener(new ListRowSelectionHandler());// 행 클릭시
		colSel.addListSelectionListener(new ListColSelectionHandler());// 열 클릭시

		
		// 각 panel을 대화창(JDialog)에 배치
		add(panelSearch, BorderLayout.NORTH);
		add(new JScrollPane(memTable), BorderLayout.CENTER);		
		add(panelBtn, BorderLayout.SOUTH);
		
		
		setLocation(300,100);	// 다이얼로그 출력 위치
		setSize(800,600); 		// 창의 크기
		setModal(true); 		// 항상 부모창 위에 표시
		setVisible(true);		// 대화창을 화면에 표시
	
	}
	

	// 회원 정보를 받아 테이블 데이터 모델에 전달하여 테이블 View에 표시하는 메서드
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
			
			memItems = new Object[10][5];
			
			rentTableModel = new RentTableModel(memItems, columnNames);
			memTable.setModel(rentTableModel);
			
		}
		
		
	}// end loadTableData()
	
	
	// 메시지 전달한 메서드
	private void messge(String msg) {
		JOptionPane.showMessageDialog(this, msg, "메시지 박스", JOptionPane.INFORMATION_MESSAGE);
	}

	
	// 회원관리 이벤트 처리하는 클래스(내부 클래스 이벤트 핸들러)
	class MemberBtnHandler implements ActionListener{
		String memId = null;
		String memPassword = null;
		String memName = null;
		String memAddress = null;
		String memPhoneNum = null;
		
		List<MemberVO> memList = null;
		

		@Override
		public void actionPerformed(ActionEvent e) {
			// 조회버튼 클릭할 경우
			if (e.getSource() == btnSearch) {
				
				String name = tf.getText().trim();
				
				memList = new ArrayList<MemberVO>();
				MemberVO memVO = new MemberVO();
				
				if (name != null && name.length() != 0) {
					
					memVO.setMemName(name); // 이름 기준으로 조회
					// 조회요청
					memList =  memberController.listMember(memVO);
					
					
					if (memList != null && memList.size() != 0) {// 검색결과 값이 있을 경우
						loadTableData(memList);
					} else if (memList == null || memList.size() == 0){// 없을 경우
						loadTableData(null);
					}
					
					//System.out.println("-- 이름 검색");
					//memList.stream().forEach(System.out::println);
					
				} else  {
					// 전체 조회
					memList =  memberController.listMember(memVO);
					loadTableData(memList);
					
					//System.out.println("-- 전체 조회");
					//memList.stream().forEach(System.out::println);
				}
				
				tf.setText("");
				
				return;
				
				
			} else if (e.getSource() == btnReg) {
				// 회원등록 대화창 생성하여 실행
				new RegMemDialog(memberController, "회원 등록창");
				return;
				
			}else if (e.getSource() == btnModify) {
				memId 		= (String) memItems[rowIdx][0];
				memPassword = (String) memItems[rowIdx][1];
				memName 	= (String) memItems[rowIdx][2];
				memAddress 	= (String) memItems[rowIdx][3];
				memPhoneNum = (String) memItems[rowIdx][4];
				
				MemberVO memVO = new MemberVO(memId, memPassword, memName, memAddress, memPhoneNum);
				System.out.println("수정할 회원 선택 :"+memVO);
				
				memberController.modMember(memVO);
				return ;

			}else  if (e.getSource() == btnDelete) {
				memId 		= (String) memItems[rowIdx][0];
				memPassword = (String) memItems[rowIdx][1];
				memName 	= (String) memItems[rowIdx][2];
				memAddress 	= (String) memItems[rowIdx][3];
				memPhoneNum = (String) memItems[rowIdx][4];
				
				MemberVO memVO = new MemberVO(memId, memPassword, memName, memAddress, memPhoneNum);
				System.out.println("삭제할 회원 선택 :"+memVO);
				
				memberController.removeMember(memVO);
				return;
			}
		}// end actionPerformed()
		
	} // end  class
	
	
	// 테이블의 행 클릭시 이벤트 처리
	class ListRowSelectionHandler implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				rowIdx = lsm.getMinSelectionIndex();
				System.out.println(rowIdx+" 번째 행이 선택됨...");
			}

		}
	}
	class ListColSelectionHandler implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// 선택한 항목의 행 추출
			ListSelectionModel lsm = (ListSelectionModel)e.getSource();
			colIdx = lsm.getMinSelectionIndex();
			
			if (!e.getValueIsAdjusting()) {
				System.out.println(rowIdx+" 번째 행, "+colIdx+ "열 선택됨..");
			}

		}
		
	}
}


