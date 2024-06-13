package chap19.member.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chap19.common.base.AbstractBaseDAO;
import chap19.member.vo.MemberVO;

public class MemberDAOImpl extends AbstractBaseDAO implements MemberDAO{

	// 회원 정보 조회 기능 처리
	@Override
	public List<MemberVO> selectMember(MemberVO memberVO) throws Exception {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		String _memName = memberVO.getMemName();
		String sql = "";
		
		if ( _memName != null && _memName.length() != 0) {
			sql = "SELECT * FROM t_member WHERE memName = ? ORDER BY memId";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _memName);
			
		} else {
			sql = "select * from t_member";
			
			pstmt = conn.prepareStatement(sql);
		}

		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			String memId 		= rs.getString("memId");
			String memPassword 	= rs.getString("memPassword");
			String memName 		= rs.getString("memName");
			String memAddress 	= rs.getString("memAddress");
			String memPhoneNum 	= rs.getString("memPhoneNum");
			
			MemberVO vo = new MemberVO();
			vo.setMemId(memId);
			vo.setMemName(memName);
			vo.setMemPassword(memPassword);
			vo.setMemPhoneNum(memPhoneNum);
			vo.setMemAddress(memAddress);
			
			// 생성자를 이용하여 객체멤버변수에 db로부터 받은 값을 전달
//			MemberVO vo = new MemberVO(memId, memPassword, memName, memAddress, memPhoneNum);
			
//			MemberVO vo = MemberVO.builder()
//							.memId(memId)
//							.memPassword(memPassword)
//							.memName(memName)
//							.memAddress(memAddress)
//							.memPhoneNum(memPhoneNum)
//						.build();
			
			memList.add(vo);
			
		}
		rs.close();
		
		return memList;
	}

	// 회원 정보 등록 기능 처리
	@Override
	public int insertMember(MemberVO memberVO) throws SQLException {
		int result = 0;// sql문장 수행횟수
		String sql = """
				INSERT INTO t_member (
					memId, 
					memPassword ,
					memName, 
					memAddress ,
					memPhoneNum 
				) VALUES 
					(?,?,?,?,?)
				""";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberVO.getMemId());
		pstmt.setString(2, memberVO.getMemPassword());
		pstmt.setString(3, memberVO.getMemName());
		pstmt.setString(4, memberVO.getMemAddress());
		pstmt.setString(5, memberVO.getMemPhoneNum());
		
		result = pstmt.executeUpdate();

		return result;
	}

	@Override
	public int updateMember(MemberVO memberVO) throws SQLException {
		int result = 0;
		String sql = """
				UPDATE t_member 
				SET memPassword = ?,
						memName = ?,
						memAddress = ?,
						memPhoneNum = ?
				WHERE memId = ?
				""";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberVO.getMemPassword());
		pstmt.setString(2, memberVO.getMemName());
		pstmt.setString(3, memberVO.getMemAddress());
		pstmt.setString(4, memberVO.getMemPhoneNum());
		pstmt.setString(5, memberVO.getMemId());
		
		result = pstmt.executeUpdate();
		return result;
	}

	@Override
	public int deleteMember(MemberVO memberVO) throws Exception {
		int result = 0;
		String sql = """
				DELETE FROM t_member WHERE memId =?
				""";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberVO.getMemId());
		
		result = pstmt.executeUpdate();
		
		return result;
		
	}

	// 회원 ID로 정보 유무 체크
	@Override
	public MemberVO checkId(String id) throws Exception {
		int result = 0;
		
		MemberVO vo = new MemberVO();
		
		String _id = id;
		String sql = "SELECT * FROM t_member WHERE memId = ?";
			
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, _id);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			String memId 		= rs.getString("memId");
			String memPassword 	= rs.getString("memPassword");
			String memName 		= rs.getString("memName");
			String memAddress 	= rs.getString("memAddress");
			String memPhoneNum 	= rs.getString("memPhoneNum");
			
			vo.setMemId(memId);
			vo.setMemName(memName);
			vo.setMemPassword(memPassword);
			vo.setMemPhoneNum(memPhoneNum);
			vo.setMemAddress(memAddress);
			
		}
		return vo;
	}

}
