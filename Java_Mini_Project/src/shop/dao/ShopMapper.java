package shop.dao;

import java.util.ArrayList;
import java.util.HashMap;

import shop.vo.AdminVO;
import shop.vo.KeywordVO;
import shop.vo.Login;
import shop.vo.MemberVO;
import shop.vo.OrderVO;
import shop.vo.ProductVO;


public interface ShopMapper {

	//�Ϲݻ����
	public MemberVO searchId(MemberVO member); // �α���
	public int register(MemberVO member); // ȸ�� ����
	public int orderProduct(HashMap<String, Integer> map); // �����ȭ
	public int orderData(OrderVO order); // �ֹ� ����
	public ArrayList<ProductVO>selectProductTitle(HashMap<String, Object> map); //�˻�
	public int deleteMember(String member_id); // ȸ�� Ż��
	
	// My Page ����������
	public int updateUserInfo(MemberVO member);
	public ArrayList<HashMap<String, Object>> popularProduct(String member_id);
	public ArrayList<HashMap<String, Object>> orderdProduct(String member_id);
	public ArrayList<HashMap<String, Object>> showAllMyReview(String member_id);
	
	// showAllProductTest
	public ArrayList<ProductVO> showAllProductTest();
	
	
	
	// �������� - ���ϰ� !! 
	// keyword
	public int insertSearchedWord(KeywordVO keywordVO);
	public ArrayList<KeywordVO> selectAllSearchedWord(String member_id);

	
	
	
	// Admin ������
	public AdminVO searchAdmin(AdminVO admin); // ������ �α���
	public ArrayList<MemberVO> selectMemberAll(); // ȸ�� ��ü ��ȸ
	public int updateMember(MemberVO vo); // ȸ������ ����
	public int insertProduct(ProductVO product); // ��ǰ���� ���
	public int updateProduct(ProductVO product); // ��ǰ���� ����
	public ArrayList<ProductVO>selectProductAll(); // ��ǰ��� ��ü ��ȸ
	public ArrayList<OrderVO> selectOrderAll(); // �ֹ����� ��ȸ
	public int updateOrder(OrderVO vo); // �ֹ����� ����

}
