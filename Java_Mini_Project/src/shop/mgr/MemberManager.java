package shop.mgr;

import java.util.ArrayList;
import java.util.HashMap;

import shop.dao.MemberDAO;
import shop.vo.MemberVO;
import shop.vo.ProductVO;

public class MemberManager {
	
	private MemberDAO memberDAO = new MemberDAO();

	// 마이페이지(구매상품) - 손하경
	public int updateUserInfo(MemberVO userInfo) {
		return memberDAO.updateUser(userInfo);
		
	}
		
	// 마이페이지(자주찾는 상품) - 손하경
	public ArrayList<HashMap<String, Object>> showPopularProduct(String member_id) {
		return memberDAO.showPopularProduct(member_id);
	}
		
	// 마이페이지(정보 수정) - 손하경
	public ArrayList<HashMap<String, Object>> orderdProduct(String member_id) {
		return memberDAO.orderdProduct(member_id);
	}
	
	// 마이페이지(내 후기보기) - 손하경
	public ArrayList<HashMap<String, Object>> myReview(String member_id) {
		return memberDAO.myReview(member_id);
	}

}
