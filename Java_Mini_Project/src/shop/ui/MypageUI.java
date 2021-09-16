package shop.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import shop.mgr.MemberManager;
import shop.vo.Login;
import shop.vo.MemberVO;
import shop.vo.ProductVO;

public class MypageUI { // with MemberDAO, MemberMgr, MemberVO
	
	private static final int UPDATE_USER_INTO = 1;
	private static final int POPULAR_PRODUCT = 2;
	private static final int ORDERED_PAGE = 3;
	private static final int SHOW_MY_REVIEW = 4;
	private static final int EXIT = 0;
	private MemberManager memberMGR = new MemberManager();
	private Scanner sc = new Scanner(System.in);
	private int page = 0;
	
	public MypageUI(MemberVO loginInfo) {
		
		boolean run = true;
		boolean sub_run = true;
		
		System.out.println("\t[" + loginInfo.getMember_id() + "さまのマイペ一ジ ]");
		showUserInfo(loginInfo);
		
		while (run) {
			showMemu();

			int menu = 0;
			
			try {
				menu = sc.nextInt();
			} catch (Exception e) {
				System.out.println("\nメニュ一は０~３の中の番号で入力してください。");
				sc.nextLine();
			}
			
			switch (menu) {
				case UPDATE_USER_INTO: //회원정보수정
					updateUserInfo(loginInfo.getMember_id());
					break;
					
				case POPULAR_PRODUCT: //자주찾는상품
					popularProduct(loginInfo.getMember_id());
					break;
					
				case ORDERED_PAGE: //구매상품
					orderedProduct(loginInfo.getMember_id());
					break;
					
				case SHOW_MY_REVIEW: // 내가 쓴 후기
					ArrayList<ArrayList<HashMap<String, Object>>> duList = splitArrayList(loginInfo.getMember_id());
					myReview(duList, page);
					
					while(sub_run) {
						System.out.println("\n\t\t\t1. 前を見る\t2. 次を見る\t0.戻る");
						System.out.print("メニュ一選択 : ");
						
						int subMenu = sc.nextInt();
						switch (subMenu) {
							case 1:
								if (page == 0) {
									System.out.println("\n----------------------------------* 最初のペ一ジです。 *----------------------------------------\n\n");
								} else {
									myReview(duList, --page);
								}
								break;
								
							case 2:
								if (page >= (duList.size() - 1)) {
									System.out.println("\n----------------------------------* 最後のペ一ジです。 *----------------------------------------\n\n");
								} else {
									myReview(duList, ++page);
								}
								break;
								
							case 0:
								sub_run = false;
								break;
								
							default:
								System.out.println("\nメニュ一は０~２の中の番号で入力してください。");
								break;
						}
						
					}
					
					break;
					
				case EXIT:
					return;
					
				default:
					System.out.println("メニュ一は０~３の中の番号で入力してください。");
					break;
				
			}	
			
		}
	}

	private ArrayList<ArrayList<HashMap<String, Object>>> splitArrayList(String member_id) { 	// ArrayList 속의 ArrayList
		
		ArrayList<HashMap<String, Object>> receivedList = memberMGR.myReview(member_id);
		ArrayList<ArrayList<HashMap<String, Object>>> dualList = null;
		
		if (!receivedList.isEmpty()) {
			dualList = new ArrayList<ArrayList<HashMap<String, Object>>>();
			
			int size = receivedList.size(); //       35  10
			int splitnum = 5;				// 몇개씩 보여줄건지
			
			if (size < splitnum) {
				dualList.add(receivedList);
				return dualList;
			}
			
			int quotient = size / splitnum;     // 몫    3  2
			int remainder = size % splitnum;  // 나머지 5  0
			int start = 0;
			int end = splitnum;
			
			for(int i=0; i<quotient; i++) {	// 0~2 (0,1,2)
				ArrayList<HashMap<String, Object>> newList = new ArrayList<HashMap<String, Object>>();
				HashMap<String, Object> map = null;
				for(int j = start; j < end; j++) { 
					map = receivedList.get(j);
					newList.add(map);
				}
				dualList.add(i, newList);
				start += splitnum;
				end += splitnum;
				
				if ( (remainder == 0) & (i+1 == quotient) ) { // 나머지가 0일때 
					return dualList;
				} else if ((i+1) == quotient) {  		// 나머지가 있을 때  
					end -= (splitnum - remainder);
					for(int k = start; k < end; k++) {
						map = receivedList.get(k);
						newList.add(map);
					}
					dualList.add(++i, newList);
				}
			}
		} else {
			System.out.println("error on split arraylist");
		}
		
		return dualList;
	}
	
	private void myReview(ArrayList<ArrayList<HashMap<String, Object>>> dualList, int page) {
		
		if (dualList == null) {
			System.out.println("作成したレビュ一がありません。");
		} else {
			System.out.println("\n\t\t\t\t  [ 私のレビュ一のリスト ]");
			for(int j=0; j<dualList.get(page).size(); j++) {
					System.out.println("\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━[ "+ (j+1) +" ]━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
					System.out.println("┃ * 題目\t: " + dualList.get(page).get(j).get("TITLE"));
					System.out.println("┃ * 内容\t: " + dualList.get(page).get(j).get("CONTENT"));
					System.out.println("┃  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -");
					System.out.println("┃ * 商品名\t: " + dualList.get(page).get(j).get("P_NAME"));
					System.out.println("┃ * メ一カ一\t: " + dualList.get(page).get(j).get("P_COMPANY"));
					System.out.println("┃ * 作成日\t: " + dualList.get(page).get(j).get("INDATE"));
					System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			}
			System.out.println("\n------------------------------------ペ一ジ [" + (page+1) + " / " + dualList.size() + "]------------------------------------------\n");
		}
		 
	}

	private void orderedProduct(String member_id) {
		
		ArrayList<HashMap<String, Object>> productList = null;
		
		System.out.println("\n\t\t[" + member_id + "さまの購買リスト ]");
		productList = memberMGR.orderdProduct(member_id);

		if (productList.isEmpty()) {
			System.out.println("\n--------お買い上げになった商品がございません。--------");
		} else {
			System.out.println("\n------------------最近の買い物5-------------------");

			HashMap<String, Object> productMap = new HashMap<String, Object>();
			
			for(int i = 0; i < 5; i++) {
				productMap = productList.get(i);
				System.out.println("\n----------------------[ "+ (i+1) +" ]----------------------");
				System.out.println(" * 商品名\t: " + productMap.get("P_NAME"));
				System.out.println(" * メ一カ一\t: " + productMap.get("P_COMPANY"));
				System.out.println(" * 価格\t: " + productMap.get("PRICE"));
				System.out.println(" * 購買数量\t: " + productMap.get("ORDER_QUAN"));
				System.out.println(" * 最近の購買日\t: " + productMap.get("ORDER_DATE"));
				System.out.println("-------------------------------------------------");
			}
		}
	}

	private void popularProduct(String member_id) {

		ArrayList<HashMap<String, Object>> productList = null;
		
		System.out.println("\n\t\t[" + member_id + "さまのよく訪れる商品リスト ]");
		productList = memberMGR.showPopularProduct(member_id);

		if (productList.isEmpty()) {
			System.out.println("\n--------お気に入りの商品リストがありません。--------");
		} else {
			System.out.println("\n----------------お気に入りの商品 TOP 5-----------------");

			HashMap<String, Object> productMap = new HashMap<String, Object>();
			
			for(int i = 0; i < 5; i++) {
				productMap = productList.get(i);
				System.out.println("\n----------------------[ "+ (i+1) +" ]----------------------");
				System.out.println(" * 商品名\t: " + productMap.get("P_NAME"));
				System.out.println(" * メ一カ一\t: " + productMap.get("P_COMPANY"));
				System.out.println(" * 価格\t: " + productMap.get("PRICE"));
				System.out.println(" * 購買数量\t: " + productMap.get("ORDER_QUAN"));
				System.out.println(" * 最近の購買日\t: " + productMap.get("ORDER_DATE"));
				System.out.println("--------------------------------------------------");
			}
		}
		
	}

	private void updateUserInfo(String member_id) {
		sc.nextLine();
		
		MemberVO updatMemberInfo = new MemberVO();
		String password, name, address, gender;
		int age = 0;

		System.out.println("\n[" + member_id + "さまの情報修正ページ ]");
		System.out.print("変更するパスワード: ");
		password = sc.nextLine(); updatMemberInfo.setPassword(password);
		
		System.out.print("変更する名前: ");
		name = sc.nextLine(); updatMemberInfo.setName(name);
		
		System.out.print("変更する住所: ");
		address = sc.nextLine(); updatMemberInfo.setAddress(address);
		
		System.out.print("変更する性別: ");
		gender = sc.nextLine(); updatMemberInfo.setGender(gender);
		
		System.out.print("変更する年齢: ");
		age = sc.nextInt(); updatMemberInfo.setAge(age);
		
		updatMemberInfo.setMember_id(member_id);
		int check = memberMGR.updateUserInfo(updatMemberInfo);
		if (check > 0) {
			System.out.println("-------修正が完了しました。-------");
		} else {
			System.out.println("-------修正をもう一度試みてください。-------");
		}
	}

	private void showMemu() {
		System.out.println("\n==============================");
		System.out.println("\t1. 会員情報修正");
		System.out.println("\t2. よく訪れる商品");
		System.out.println("\t3. 購買商品");
		System.out.println("\t4. 作成したレビュ一");
		System.out.println("\t0. 戻る");
		System.out.println("==============================");
		System.out.print("\tメニュ一 > ");
	}

	private void showUserInfo(MemberVO loginInfo) {
		String gender = loginInfo.getGender().equals("f") ? "女性" : "男性";
		System.out.println("\n-------------------------------");
		System.out.println(" *　氏名 : " + loginInfo.getName());
		System.out.println(" * ID : " + loginInfo.getMember_id());
		System.out.println(" * 住所 : " + loginInfo.getAddress());
		System.out.println(" * 性別 : " + gender);
		System.out.println(" * 年齢 : " + loginInfo.getAge());
		System.out.println("-------------------------------");
	}
	

}
