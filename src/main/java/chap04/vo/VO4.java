package chap04.vo;

public class VO4 {
	
	// private :클래스 내에서만 접근 가능
	private int hakbun;
	private String name;
	
	private int kor;
	private int eng;
	private int mat;
	

	// 멤버변수는 메서드로 통행 자료 전달
	// 값을 설정: 입력
	public void setHakbun(int hakbun) {
		this.hakbun = hakbun;
	}
	// 값을 추출: 출력
	public int getHakbun() {
		return this.hakbun;
	}
	
	public void setName(String name) {this.name = name;}
	public String getName() {return this.name;}
	
	public void setKor(int kor) {this.kor = kor;}
	public int getKor() { return this.kor;}
	
	public void setMat(int mat) { this.mat = mat;}
	public int getMat() { return this.mat;}
	public void setEng(int eng) { this.eng = eng;}
	public int getEng() { return this.eng;}
	
	
}













