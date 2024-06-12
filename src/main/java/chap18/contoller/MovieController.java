package chap18.contoller;



//import javax.swing.JList;
import java.awt.List;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import chap18.model.MovieModel;

public class MovieController {
	
	
	MovieModel movieModel;
	
	public MovieController() {
		movieModel = new MovieModel();
	}
	
	// 추가기능 요청
	public void addTitle(String title, List movieList) {
		// 요청에 해당되는 처리기능 수행
		movieModel.addTitle(title, movieList);
	}
	// 저장 기능 요청
	public void saveTitles(List movieList) {
		// 요청에 해당되는 처리기능 수행
		movieModel.saveTitles(movieList);
	}
	 
	// 삭제 기능 요청
	public void delTitles(String title, List movieList) {
		// 요청에 해당되는 처리기능 수행
		movieModel.delTitles(title, movieList);
	}
	
}
