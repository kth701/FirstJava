package chap17;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserTableModelTest extends JFrame {
	JPanel searchPanel;
	JPanel bPanel;
	
	JList memberJList;
	JLabel lCondition;
	
	JTextField tCondition;
	JButton searchBtn;
	JComboBox combo;
	
	JTable memTable;
	
	JButton updateBtn, deleteBtn;
	Object[][] emp = new Object[0][4];
	
	UserTableModelTest() {
		
		initTableModelTest();
		
		setTitle("사용자 정의 테이블 실습");
		setLayout(new BorderLayout());
		
		Container c = getContentPane();
		c.add(searchPanel, "North");
		
		c.add(new JScrollPane(memTable), "Center" );
		c.add(bPanel, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(200,200);
		pack();
		
		setVisible(true);
		
		
		
	}

	private void initTableModelTest() {
		// 화면 요소 구성하기
		searchPanel = new JPanel();
		tCondition = new JTextField(10);
		lCondition = new JLabel("입력창");
		
		memberJList = new JList();
		memberJList.setBackground(Color.GREEN);
		
		memTable = new JTable();
		memTable.setModel(new UserTableModel(emp));
		
		searchBtn = new JButton("조회하기");
		// 조회버튼 클릭시 처하는 내용
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[][] data = {
						{"220000","박길동","총무부"},
						{"220001","홍길동","관리부"},
						{"220002","이순신","회계부"},
						{"220003","임꺽정","개발부"},
						{"220004","강감찬","관리부"}
				};
				
				memTable.setModel(new UserTableModel(data));
			}
		});
		
		// 그룹창( 검색어, 텍스트, 버튼)
		searchPanel.add(lCondition);
		searchPanel.add(tCondition);
		searchPanel.add(searchBtn);
		
		bPanel = new JPanel();
		updateBtn = new JButton("수정하기");
		deleteBtn = new JButton("삭제하기");
		
		bPanel.add(updateBtn);
		bPanel.add(deleteBtn);
		
		
	}

	public static void main(String[] args) {
		new UserTableModelTest();

	}

}

/*  다음주 월요일 개인 포폴 사전 발표 and 팀 프로젝토 주제 선정 */