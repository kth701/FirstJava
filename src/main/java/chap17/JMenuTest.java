package chap17;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JMenuTest {
	


	JFrame frame;
	
	JMenuBar menuBar;
	JMenu carMenu, memberMenu, rentMenu, helpMenu;

	JMenuItem carMenu11, carMenu12, carMenu13, carMenu14;
	JMenuItem memMenu21,memMenu22,memMenu23,memMenu24;
	JMenuItem helpMenu41;
	
	JPanel jPanel;
	JLabel lCarName;
	
	JTextField tf;
	JButton searchBtn;
	
	public JMenuTest() {
		frame = new JFrame("렌터카 예약 시스템");
		
		menuBar = new JMenuBar();
		
		carMenu 	= new JMenu("차량 관리");
		memberMenu 	= new JMenu("회원 관리");
		rentMenu 	= new JMenu("예약 관리");
		helpMenu 	= new JMenu("도움말");
	}
	
	public void createFrame() {
		frame.setJMenuBar(menuBar);// 프레임에 메뉴바 설정
		
		menuBar.add(carMenu); // 메뉴바에 메뉴 설정
		carMenu.add(carMenu11 = new JMenuItem("차량등록")); // 메뉴에 메뉴항목 설정
		carMenu.add(carMenu12 = new JMenuItem("차량조회"));
		carMenu.addSeparator();// 메뉴 구분선
		
		carMenu.add(carMenu13 = new JMenuItem("차량수정"));
		carMenu.add(carMenu14 = new JMenuItem("차량삭제"));
		
		
		menuBar.add(memberMenu);
		memberMenu.add(memMenu21 = new JMenuItem("회원등록"));
		memberMenu.add(memMenu22 = new JMenuItem("회원조회"));
		memberMenu.addSeparator();
		
		memberMenu.add(memMenu23 = new JMenuItem("회원수정"));
		memberMenu.add(memMenu24 = new JMenuItem("회원삭제"));
		
		menuBar.add(helpMenu);
		helpMenu.add(helpMenu41 = new JMenuItem("버전"));
		
		jPanel = new JPanel();
		
		lCarName = new JLabel("차량명");
		tf = new JTextField(15);
		searchBtn = new JButton("차량 조회하기");
		
		// 프레임 자체에 있는 메뉴바영역은 별도로 추가하지 않고 메뉴및 메뉴항목 설정
		
		jPanel.add(lCarName);
		jPanel.add(tf);
		jPanel.add(searchBtn);
		
		Container con = frame.getContentPane();
		con.add(jPanel, BorderLayout.NORTH);
		
		frame.setLocation(200,100);
		frame.setSize(800, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		// JMenuBar -> JMenu -> JMenuItem

		JMenuTest menu = new JMenuTest();
		menu.createFrame();
		

	}

}
