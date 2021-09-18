package shop.mgr;


import java.util.ArrayList;
import java.util.HashMap;

import shop.dao.MemberDAO;
import shop.vo.MemberVO;

public class MemberManager {

	private MemberDAO dao = new MemberDAO();

	public MemberVO searchId(MemberVO member) {
		return dao.searchId(member);
	}

	public boolean register(MemberVO member) {
		int cnt = dao.register(member);

		if(cnt > 0) {
			return true;
		}
		return false;
	}

	//회원관리(회원 목록 조회)
	public ArrayList<MemberVO> listMember() {
		ArrayList<MemberVO> list = dao.listMember();
		return list;
	}		
	//회원관리(회원 정보 수정)

	public int updateMember(MemberVO vo) {
		return dao.updateMember(vo);
	}

	//회원탈퇴
	public int deleteMember(String member_id) {
		int cnt = dao.deleteMember(member_id);
		return cnt;
	}

	// 마이페이지(구매상품) - 손하경
	public int updateUserInfo(MemberVO userInfo) {
		return dao.updateUser(userInfo);

	}

	// 마이페이지(자주찾는 상품) - 손하경
	public ArrayList<HashMap<String, Object>> showPopularProduct(String member_id) {
		return dao.showPopularProduct(member_id);
	}

	// 마이페이지(정보 수정) - 손하경
	public ArrayList<HashMap<String, Object>> orderdProduct(String member_id) {
		return dao.orderdProduct(member_id);
	}

	// 마이페이지(내 후기보기) - 손하경
	public ArrayList<HashMap<String, Object>> myReview(String member_id) {
		return dao.myReview(member_id);
	}
}
