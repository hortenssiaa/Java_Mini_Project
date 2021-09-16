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

	//일반사용자
	public MemberVO searchId(MemberVO member); // 로그인
	public int register(MemberVO member); // 회원 가입
	public int orderProduct(HashMap<String, Integer> map); // 재고량변화
	public int orderData(OrderVO order); // 주문 정보
	public ArrayList<ProductVO>selectProductTitle(HashMap<String, Object> map); //검색
	public int deleteMember(String member_id); // 회원 탈퇴
	
	// My Page 마이페이지
	public int updateUserInfo(MemberVO member);
	public ArrayList<HashMap<String, Object>> popularProduct(String member_id);
	public ArrayList<HashMap<String, Object>> orderdProduct(String member_id);
	public ArrayList<HashMap<String, Object>> showAllMyReview(String member_id);
	
	// showAllProductTest
	public ArrayList<ProductVO> showAllProductTest();
	
	
	
	// 수정했음 - 손하경 !! 
	// keyword
	public int insertSearchedWord(KeywordVO keywordVO);
	public ArrayList<KeywordVO> selectAllSearchedWord(String member_id);

	
	
	
	// Admin 관리자
	public AdminVO searchAdmin(AdminVO admin); // 관리자 로그인
	public ArrayList<MemberVO> selectMemberAll(); // 회원 전체 조회
	public int updateMember(MemberVO vo); // 회원정보 수정
	public int insertProduct(ProductVO product); // 상품정보 등록
	public int updateProduct(ProductVO product); // 상품정보 수정
	public ArrayList<ProductVO>selectProductAll(); // 상품목록 전체 조회
	public ArrayList<OrderVO> selectOrderAll(); // 주문정보 조회
	public int updateOrder(OrderVO vo); // 주문정보 수정

}
