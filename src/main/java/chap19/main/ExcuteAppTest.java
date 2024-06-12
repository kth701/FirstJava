package chap19.main;

import java.util.ArrayList;
import java.util.List;

import chap19.member.controller.MemberController;
import chap19.member.controller.MemberControllerImpl;
import chap19.member.vo.MemberVO;

public class ExcuteAppTest {

	public static void main(String[] args) {
		MemberControllerImpl controller = new MemberControllerImpl();
		
		List<MemberVO> listMembers = new ArrayList<MemberVO>();
		listMembers =  controller.listMember(null);
		
		System.out.println("-- 회원 정보 조회 --");
		listMembers.stream().forEach( m -> {
			System.out.println(m);
		});
		

	}

}
