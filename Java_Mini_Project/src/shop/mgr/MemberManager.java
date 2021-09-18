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

	//ȸ������(ȸ�� ��� ��ȸ)
	public ArrayList<MemberVO> listMember() {
		ArrayList<MemberVO> list = dao.listMember();
		return list;
	}		
	//ȸ������(ȸ�� ���� ����)

	public int updateMember(MemberVO vo) {
		return dao.updateMember(vo);
	}

	//ȸ��Ż��
	public int deleteMember(String member_id) {
		int cnt = dao.deleteMember(member_id);
		return cnt;
	}

	// ����������(���Ż�ǰ) - ���ϰ�
	public int updateUserInfo(MemberVO userInfo) {
		return dao.updateUser(userInfo);

	}

	// ����������(����ã�� ��ǰ) - ���ϰ�
	public ArrayList<HashMap<String, Object>> showPopularProduct(String member_id) {
		return dao.showPopularProduct(member_id);
	}

	// ����������(���� ����) - ���ϰ�
	public ArrayList<HashMap<String, Object>> orderdProduct(String member_id) {
		return dao.orderdProduct(member_id);
	}

	// ����������(�� �ı⺸��) - ���ϰ�
	public ArrayList<HashMap<String, Object>> myReview(String member_id) {
		return dao.myReview(member_id);
	}
}
