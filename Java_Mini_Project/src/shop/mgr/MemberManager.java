package shop.mgr;

import java.util.ArrayList;
import java.util.HashMap;

import shop.dao.MemberDAO;
import shop.vo.MemberVO;
import shop.vo.ProductVO;

public class MemberManager {
	
	private MemberDAO memberDAO = new MemberDAO();

	// ����������(���Ż�ǰ) - ���ϰ�
	public int updateUserInfo(MemberVO userInfo) {
		return memberDAO.updateUser(userInfo);
		
	}
		
	// ����������(����ã�� ��ǰ) - ���ϰ�
	public ArrayList<HashMap<String, Object>> showPopularProduct(String member_id) {
		return memberDAO.showPopularProduct(member_id);
	}
		
	// ����������(���� ����) - ���ϰ�
	public ArrayList<HashMap<String, Object>> orderdProduct(String member_id) {
		return memberDAO.orderdProduct(member_id);
	}
	
	// ����������(�� �ı⺸��) - ���ϰ�
	public ArrayList<HashMap<String, Object>> myReview(String member_id) {
		return memberDAO.myReview(member_id);
	}

}
