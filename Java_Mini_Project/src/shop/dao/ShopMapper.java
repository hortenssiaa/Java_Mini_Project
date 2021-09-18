package shop.dao;

import java.util.ArrayList;
import java.util.HashMap;

import shop.vo.AdminVO;
import shop.vo.BoardVO;
import shop.vo.KeywordVO;
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
	public ArrayList<HashMap<String, Object>> selectTrendMale(); // 트렌드
	public ArrayList<HashMap<String, Object>> selectTrendFemale();
	public ArrayList<HashMap<String, Object>> selectTrendAge01();
	public ArrayList<HashMap<String, Object>> selectTrendAge02();
	public ArrayList<HashMap<String, Object>> selectTrendAge03();
	// My Page 마이페이지
	public int updateUserInfo(MemberVO member);
	public ArrayList<HashMap<String, Object>> popularProduct(String member_id);
	public ArrayList<HashMap<String, Object>> orderdProduct(String member_id);
	public ArrayList<HashMap<String, Object>> showAllMyReview(String member_id);
	// showAllProductTest
	public ArrayList<ProductVO> showAllProductTest();
	// Board 게시판
	public int insertBoard(BoardVO board);// 글저장
	public ArrayList<BoardVO> selectBoardAll();// 글목록
	public BoardVO selectBoardByNum(int boardnum);// 글 읽기
	public void updateHits(int boardnum);// 글의 조회수 증가
	public int checkUserID(BoardVO board);
	public int deleteBoard(BoardVO board);// 글 삭제
	public ArrayList<BoardVO> selectBoardByTitle(HashMap<String, Object> map); // 글 검색
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
	public int deleteProduct(String p_id); // 상품정보 삭제
	public int deleteOrder(int order_id); // 주문정보 삭제

}
