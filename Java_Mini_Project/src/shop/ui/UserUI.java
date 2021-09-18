package shop.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import shop.mgr.BoardManager;
import shop.mgr.MemberManager;
import shop.mgr.OrderManager;
import shop.vo.BoardVO;
import shop.vo.MemberVO;

public class UserUI {
	private Scanner sc = new Scanner(System.in); 
	private MemberManager mgrM = new MemberManager();
	private OrderManager mgrO = new OrderManager();
	private BoardManager b_mgr = new BoardManager();

	public UserUI(MemberVO login) {
		boolean run = true;

		while(run) {
			printMainMenu();

			int menu = 0;

			try {
				menu = sc.nextInt();
			} catch (Exception e) {
				System.out.println("メニュ一は１~７の中のナンバーで入力してください。");
				sc.nextLine();
			}

			switch (menu) {
			case 1: //상품목록
				new ProductUI(login);//상품목록
				break;
			case 2: //마이페이지
				new MypageUI(login); // 마이페이지
				break;
			case 3: //트렌드
				trend();
				break;
			case 4: //게시판
				boolean run1 = true;
				while(run1) {
					printBoardMainMenu();

					int menu1 = 0;
					try {
						menu1 = sc.nextInt();
					} catch (Exception e) {
						System.out.println("メニュ一は１~５の中のナンバーで入力してください。");
						sc.nextLine();
						continue;
					}

					switch (menu1) {
					case 1:
						// 글 저장
						boardInsert(login);
						break;
					case 2:
						// 글 목록
						boardList();
						break;
					case 3:
						// 글 읽기
						boardRead();
						break;
					case 4:
						// 글 삭제
						boardDelete(login);
						break;
					case 5:
						// 글 검색
						boardSearch();
						break;
					case 0:
						run1 = false;
						break;
					default:
						System.out.println("間違ったメニューナンバーです。");
						break;
					}
				}
				break;
			case 5: //회원탈퇴
				deleteMember(login);
				break;
			case 6: //뒤로가기
				return;
			case 7: // 프로그램종료
				System.out.println("プログラムを終了します。");
				run = false;
				break;
			default:
				System.out.println("メニュ一は１~７の中のナンバーで入力してください。");
				break;
			}
		}	
	}


	public void printMainMenu(){
		System.out.println("====================");
		System.out.println("1. 商品リストを見る\"");
		System.out.println("2. マイペ一スに行く");
		System.out.println("3. トレンドのデータを見る");
		System.out.println("4. 揭示板を見る");
		System.out.println("5. 会員退会する");
		System.out.println("6. 戻す");
		System.out.println("7. プログラミングを終了する");
		System.out.println("====================");
		System.out.print("メニューナンバー　> ");
	}

	public void trend() {
		boolean run = true;

		while (run) {
			System.out.println("===============");
			System.out.println("[　トレンド　]");
			System.out.println("1.性別トレンド");
			System.out.println("2.年齢別トレンド");
			System.out.println("3.戻す");
			System.out.print("メニューナンバー　> ");

			int menu = 0;

			try {
				menu = sc.nextInt();
			} catch (Exception e) {
				System.out.println("メニュ一は１~２の中のナンバーで入力してください。");
				sc.nextLine();
			}

			switch(menu) {
			case 1: //성별에 따른 트렌드(5개씩) - 남.녀 선택하면 주문량으로 뜨게 하기
				System.out.println("================");
				System.out.println("[　性別トレンド　]");
				System.out.println("1.男性ユーザーのトレンド");
				System.out.println("2.女性ユーザーのトレンド");
				System.out.println("3.戻す");
				System.out.print("メニューナンバー　> ");
				int menu1 = 0;

				try {
					menu1 = sc.nextInt();
				}catch(Exception e){
					System.out.println("メニュ一は１~２の中のナンバーで入力してください。");
					sc.nextLine();
				}

				switch(menu1){
				case 1: //남성 회원의 트렌드
					System.out.println("[　男性ユーザーの商品トレンド・リスト　]");
					ArrayList<HashMap<String, Object>> listMale = null;
					listMale = mgrO.selectTrendMale();
					if(listMale.isEmpty()) {
						System.out.println("商品トレンドのリストがありません。");
					}else {
						HashMap<String, Object> orderList = new HashMap<String, Object>();
						for(int i = 0; i < 5; i++) {
							orderList = listMale.get(i);
							System.out.println("商品ID: " + orderList.get("P_ID"));
							System.out.println("商品名: " + orderList.get("P_NAME"));
							System.out.println("販売量: " + orderList.get("ORDER_QUAN"));
							System.out.println();
						}
					}					
					break;
				case 2: //여성 회원의 트렌드
					System.out.println("[　女性ユーザーの商品トレンド・リスト　]");
					ArrayList<HashMap<String, Object>> listFemale = null;
					listFemale = mgrO.selectTrendFemale();
					if(listFemale.isEmpty()) {
						System.out.println("商品トレンドのデータがありません。");
					}else {
						HashMap<String, Object> orderList = new HashMap<String, Object>();
						for(int i = 0; i < 5; i++) {
							orderList = listFemale.get(i);
							System.out.println("商品ID: " + orderList.get("P_ID"));
							System.out.println("商品名: " + orderList.get("P_NAME"));
							System.out.println("販売量: " + orderList.get("ORDER_QUAN"));
							System.out.println();
						}
					}					
					break;
				case 3:
					return;
				default:
					System.out.println("メニュ一は１~２の中のナンバーで入力してください。");
					break;

				}

				break;
			case 2: //나이에 따른 트렌드(5개씩)
				System.out.println("==============");
				System.out.println("[　年齢別トレンド　]");
				System.out.println("1.10代~20代");
				System.out.println("2.30代~40代");
				System.out.println("3.50代~60代");
				System.out.println("4.戻す");
				System.out.println();
				System.out.print("メニューナンバー　> ");
				int menu2 = 0;

				try {
					menu2 = sc.nextInt();
				}catch(Exception e) {
					System.out.println("メニュ一は１~５の中のナンバーで入力してください。");
				}

				switch(menu2) {
				case 1:
					System.out.println("[　10代~20代のトレンド　]");
					ArrayList<HashMap<String, Object>> list01 = null;
					list01 = mgrO.selectTrendAge01();
					if(list01.isEmpty()) {
						System.out.println("商品トレンドのデータがありません。");
					}else {
						HashMap<String, Object> orderList = new HashMap<String, Object>();
						for(int i = 0; i < 5; i++) {
							orderList = list01.get(i);
							System.out.println("商品ID: " + orderList.get("P_ID"));
							System.out.println("商品名: " + orderList.get("P_NAME"));
							System.out.println("販売量: " + orderList.get("ORDER_QUAN"));
							System.out.println();
						}
					}
					break;
				case 2:
					System.out.println("[　30代~40代のトレンド　]");
					ArrayList<HashMap<String, Object>> list02 = null;
					list02 = mgrO.selectTrendAge02();
					if(list02.isEmpty()) {
						System.out.println("商品トレンドのデータがありません。");
					}else {
						HashMap<String, Object> orderList = new HashMap<String, Object>();
						for(int i = 0; i < 5; i++) {
							orderList = list02.get(i);
							System.out.println("商品ID: " + orderList.get("P_ID"));
							System.out.println("商品名: " + orderList.get("P_NAME"));
							System.out.println("販売量: " + orderList.get("ORDER_QUAN"));
							System.out.println();
						}
					}
					break;
				case 3:
					System.out.println("[　50代~60代のトレンド　]");
					ArrayList<HashMap<String, Object>> list03 = null;
					list03 = mgrO.selectTrendAge03();
					if(list03.isEmpty()) {
						System.out.println("商品トレンドのデータがありません。");
					}else {
						HashMap<String, Object> orderList = new HashMap<String, Object>();
						for(int i = 0; i < 5; i++) {
							orderList = list03.get(i);
							System.out.println("商品ID: " + orderList.get("P_ID"));
							System.out.println("商品名: " + orderList.get("P_NAME"));
							System.out.println("販売量: " + orderList.get("ORDER_QUAN"));
							System.out.println();
						}
					}
					break;
				case 4: //뒤로가기
					return;
				default:
					System.out.println("メニュ一は１~５の中のナンバーで入力してください。");
					break;

				}

			case 3: //뒤로가기
				return;
			default:
				System.out.println("メニュ一は１~３の中のナンバーで入力してください。");
				break;

			}
		}
	}

	//회원탈퇴
	void deleteMember(MemberVO log) {
		sc.nextLine();
		System.out.println("[　会員退会ページ　]");

		System.out.print("削除する会員のパスワード : ");
		String password = sc.nextLine();

		if(password.equals(log.getPassword())) {
			int cnt = mgrM.deleteMember(log.getMember_id());

			if(cnt > 0) {
				System.out.println("会員退会が完了しました。ありがとうございました。");
			}else {
				System.out.println("会員退会に失敗しました。");
			}
		}
	}

	void ordersProduct(String member_id) { //구매상품

		ArrayList<HashMap<String, Object>> productList = null;

		System.out.println("\n\t      [" + member_id + "さまの購買リスト ]");
		productList = mgrM.orderdProduct(member_id);

		if (productList.isEmpty()) {
			System.out.println("\n--------購入した商品がありません。--------");
		} else {

			HashMap<String, Object> productMap = new HashMap<String, Object>();

			for(int i = 0; i < productList.size(); i++) {
				productMap = productList.get(i);
				System.out.println("\n----------------------[ "+ (i+1) +" ]----------------------");
				System.out.println(" * 商品ナンバー : " + productMap.get("P_ID"));
				System.out.println(" * 商品名 : " + productMap.get("P_NAME"));
//				System.out.println(" * 제조사 : " + productMap.get("P_COMPANY"));
//				System.out.println(" * 가격 : " + productMap.get("PRICE"));
//				System.out.println(" * 구매수량 : " + productMap.get("ORDER_QUAN"));
//				System.out.println(" * 최근 구매일 : " + productMap.get("ORDER_DATE"));
				System.out.println("-------------------------------------------------");
			}
		}
	}

	// 글쓰기
	void boardInsert(MemberVO member){

		sc.nextLine();

		System.out.println("[　レビュ一作成ページ　]");
		ordersProduct(member.getMember_id());
		System.out.print("商品のナンパー: ");
		int p_id = sc.nextInt();

		sc.nextLine();
		String title,content;
		System.out.println(" [ レビュ一を作成する ] ");
		System.out.print("題目 : ");
		title = sc.nextLine();
		System.out.print("内容 : ");
		content = sc.nextLine();
		BoardVO board = new BoardVO();
		board.setTitle(title);
		board.setContent(content);
		board.setMember_id(member.getMember_id());
		board.setP_id(p_id);

		boolean check = b_mgr.insertBoard(board);
		if(check) {
			System.out.println("レビュ一を作成しました！");
		}else {
			System.out.println("レビュ一作成に失敗しました。");
		}
	};
	// 전체 글 목록
	void boardList(){
		System.out.println(" [ 全レビュ一リスト ] ");
		ArrayList<BoardVO> list = b_mgr.listBoard();

		if(list.isEmpty()) {
			System.out.println("登録されたレビュ一がありません。");
		}else {
			for(BoardVO b: list) {
				System.out.println("文ナンバー : " + b.getBoardnum());
				System.out.println("文題目 : " + b.getTitle());
				System.out.println("Hits : " + b.getHits());
				System.out.println("作成者 : " + b.getMember_id());
				System.out.println("作成日 : " + b.getCredate());
				System.out.println();
			}
		}
	}

	// 글 읽기
	void boardRead(){
		System.out.println(" [ レビュ一読みページ ] ");
		System.out.print(" レビュ一のナンバー: ");
		int num = sc.nextInt();
		BoardVO board = b_mgr.readBoard(num);
		if(board == null) {
			System.out.println("該当レビューがありません。");
		}else {
			System.out.println("レビューのナンバー : " + board.getBoardnum());
			System.out.println("レビュー題目 : " + board.getTitle());
			System.out.println("レビューの内容 : " + board.getContent());
			System.out.println("Hits : " + board.getHits());
			System.out.println("作成日 : " + board.getCredate());
			System.out.println();
		}
	}

	// 글 삭제
	void boardDelete(MemberVO member){
		BoardVO boardVO = new BoardVO();
		System.out.println(" [ レビュー削除ページ ] ");
		System.out.print("レビューのナンバー: ");
		int num = sc.nextInt();
		boardVO.setBoardnum(num);
		boardVO.setMember_id(member.getMember_id());
		boolean check = b_mgr.deleteBoard(boardVO);
		if(check) {
			System.out.println("レビューが削除されました。");
		}else {
			System.out.println("レビューの削除に失敗しました。");
		}
	}

	// 글 검색
	void boardSearch(){
		sc.nextLine();
		System.out.println(" [ 検索ページ ] ");
		System.out.print("神作する文を選ぶ　(1:作成者ID 2:題目 3:本文) : ");
		int type = sc.nextInt();
		sc.nextLine();
		System.out.print("検索する内容: ");
		String word = sc.nextLine();

		ArrayList<BoardVO> list = b_mgr.searchBoardVO(type, word);

		if(list.isEmpty()) {
			System.out.println("検索の結果がありません。");
		}else {
			for(BoardVO b: list) {
				System.out.println("レビューのナンバー : " + b.getBoardnum());
				System.out.println("レビューの題目 : " + b.getTitle());
				System.out.println("レビューの内容 : " + b.getContent());
				System.out.println("Hits : " + b.getHits());
				System.out.println("作成日 : " + b.getCredate());
				System.out.println();
			}
		}
	}
	public void printBoardMainMenu() {
		System.out.println("====================");
		System.out.println("1.レビューを作成する");
		System.out.println("2.全レビューのリスト");
		System.out.println("3.レビューを読む");
		System.out.println("4.レビューを削除する");
		System.out.println("5.レビューを検索する");
		System.out.println("0.戻す");
		System.out.println("====================");
		System.out.print("メニューナンバー　> ");
	}
}
