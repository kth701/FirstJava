package chap18;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.List;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import chap18.contoller.MovieController;

public class MovieWindowView extends JFrame implements ActionListener {
	String resultMsg = null;
	
	JTextField tMovieTitle;
	JButton btnTitleInsert, btnSave, btnExit;
	List movieList;

	MovieController controller;
	
	public MovieWindowView() {
		super("영화정보 관리 화면");
		
		controller = new MovieController();// 컨트롤러 생성
		
		// 컴포넌트 객체 생성
		tMovieTitle = new JTextField(30);
		btnTitleInsert = new JButton("영화제목 추가");
		btnSave = new JButton("영화제목 파일 저장");
		
		movieList = new List();

		btnExit = new JButton("종료");
		
		// 컴포넌트 설정
		movieList.setBackground(Color.green);
		
		Panel p = new Panel();
		p.add(new JLabel("영화제목 입력"));
		p.add(tMovieTitle);
		p.add(btnTitleInsert);
		p.add(btnSave);
		
		add(BorderLayout.NORTH, p);
		add(BorderLayout.CENTER, movieList);
		add(BorderLayout.SOUTH, btnExit);
		
		setBounds(0,0, 800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 이벤트 등록 처리
		movieList.addActionListener(this);
		btnTitleInsert.addActionListener(this);
		btnSave.addActionListener(this);
		btnExit.addActionListener(this);
		
	}

	public static void main(String[] args) {
		// MVC디자인 패턴
		/*
		 * 뷰(View) : 사용자의 요청을 받아들이는 화면으로 요청을 컨트롤러로 전달
		 * 컨트롤러(Controller) : View에서 전달된 요청에 맞는 모델을 선택
		 * 모델(Model): 데이터베이스 연동이나 파일 저장, 로직등 실제 기능을 수행
		 */

		MovieWindowView movie = new MovieWindowView();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			
			String mTitle = e.getActionCommand();
			
			System.out.println("이벤트 발생:"+mTitle);
			if (e.getSource() == btnTitleInsert) {
				resultMsg = "영화제목을 추가했습니다.";
				//System.out.println("영화제목 추가하기");
				
				// 텍스트에 입력한 영화제목 읽기
				mTitle = tMovieTitle.getText().trim();
				
				// controller에 추가요청(입력한 영화제목, 현재 영화제목 List)
				controller.addTitle(mTitle, movieList);
				tMovieTitle.setText("");
				
			} else if (e.getSource() == btnSave) {
				resultMsg = "영화제목을 파일에 저장했습니다.";
				//System.out.println("영화제목을 파일에  저장");
				controller.saveTitles(movieList);
			} else {
				resultMsg = "영화제목을 삭제했습니다.";
				controller.delTitles(mTitle, movieList);
			}
			
			// 다이얼로그 박스 : 메시지
			JOptionPane.showMessageDialog(this, resultMsg, "메시지 박스", JOptionPane.INFORMATION_MESSAGE);
			
			if (e.getSource() == btnExit) {
				System.exit(1);// 정상 종료 (0): 비정상 종료
			}
			
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
		}

	}

}

/*
 * Button : ActionEvent(클릭), FocusEvent, Key....
 * Checkbox: ItemEvent(체크박스,리스트항목 선택),..
 * Frame: WindowEnvent,...
 * List: ActionEvent,ItemEvent,...
 * Label: FocusEvent,...
 * Choice : ItemEvent,...
 * Adjustable: AdjustmentEvent
 * 
 */


