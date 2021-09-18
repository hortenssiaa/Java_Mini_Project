package shop.ui;

import java.util.ArrayList;
import java.util.Scanner;

import shop.mgr.OrderManager;
import shop.mgr.ProductManager;
import shop.vo.KeywordVO;
import shop.vo.MemberVO;
import shop.vo.OrderVO;
import shop.vo.ProductVO;

public class ProductUI {
	Scanner sc = new Scanner(System.in);
	private ProductManager mgr = new ProductManager();
	private OrderManager mgrO = new OrderManager();
	private ArrayList<ArrayList<ProductVO>> dualList = splitArrayList();
	private int page = 0;

	public ProductUI(MemberVO login) {
		boolean run = true;
		showAllProductTest(dualList, page);

		while(run) {

			System.out.print("\n1. 説明を見る  2. 買う  3. 検索する  4. 以前のペ一ジ  5. 次のペ一ジ  6. 戻る   メニュ一入力する : ");

			int menu = 0;

			try {
				menu = sc.nextInt();
			} catch (Exception e) {
				System.out.println("メニュ一は１~５の中のナンバーで入力してください。");
				sc.nextLine();
			}

			switch (menu) {
			case 1: // 설명보기
				pDetail();
				break;
			case 2: // 구매하기
				pOrder(login);
				break;
			case 3: // 검색
				search(login);
				break;
			case 4: // 전의 상품 리스트
				if (page == 0) {
					System.out.println("------最初のペ一ジです。------");
				} else {
					showAllProductTest(dualList, --page);
				}
				break;
			case 5: // 다음 상품 리스트
				if (page >= dualList.size() - 1) {
					System.out.println("------最後のペ一ジです。------");
				} else {
					showAllProductTest(dualList, ++page);
				}
				break;
			case 6: // 뒤로가기
				return;
			default:
				System.out.println("メニュ一は１~５の中のナンバーで入力してください。");
				break;
			}
		}	
	}

	private ArrayList<ArrayList<ProductVO>> splitArrayList() { 	// ArrayList 속의 ArrayList

		ArrayList<ProductVO> receivedList = mgr.showAllProductTest();
		ArrayList<ArrayList<ProductVO>> dualList = null;

		if (!receivedList.isEmpty()) {
			dualList = new ArrayList<ArrayList<ProductVO>>();

			int size = receivedList.size(); //       35  10
			int splitnum = 5;				// 몇개씩 보여줄건지

			if (size < splitnum) {
				dualList.add(receivedList);
				return dualList;
			}
			int quotient = size / splitnum;     // 몫    3  2
			int remainder = size % splitnum;  // 나머지 	5  0
			int start = 0;
			int end = splitnum;

			for(int i=0; i<quotient; i++) {	// 0~2 (0,1)
				ArrayList<ProductVO> newList = new ArrayList<ProductVO>();
				ProductVO productVO = null;
				for(int j = start; j < end; j++) { 
					productVO = receivedList.get(j);
					newList.add(productVO);
				}
				dualList.add(i, newList);
				start += splitnum;
				end += splitnum;

				if ( (remainder == 0) & (i+1 == quotient) ) { // 나머지가 0일때 
					return dualList;
				} else if ((i+1) == quotient) {  		// 나머지가 있을 때  
					end -= (splitnum - remainder);
					for(int k = start; k < end; k++) {
						productVO = receivedList.get(k);
						newList.add(productVO);
					}
					dualList.add(++i, newList);
				}
			}
		} else {
			System.out.println("error on split arraylist");
		}

		return dualList;
	}

	private void showAllProductTest(ArrayList<ArrayList<ProductVO>> dualList, int page) {

		if (dualList == null) {
			System.out.println("商品リストを読んで来られませんでした。");
		} else {
			System.out.println("\n\t\t\t\t  [ 商品リスト ]");
			for(int j=0; j<dualList.get(page).size(); j++) {
				System.out.println("\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━[ "+ (j+1) +" ]━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("┃ * 商品コ一ド\t: " + dualList.get(page).get(j).getP_id());
				System.out.println("┃ * 商品名\t: " + dualList.get(page).get(j).getP_name());
				System.out.println("┃ * メ一カ一\t: " + dualList.get(page).get(j).getP_company());
				System.out.println("┃ * 価格\t: " + dualList.get(page).get(j).getPrice());
//				System.out.println("┃ * 商品説明\t: " + dualList.get(page).get(j).getP_detail());
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			}
			System.out.println("\n-----------------------------------ペ一ジ [" + (page+1) + " / " + dualList.size() + "]-----------------------------------------\n\n");
		}
	}

	public void pOrder(MemberVO log) {
		OrderVO order = new OrderVO();

		System.out.print("購入したい商品のナンパーを入力してください : ");
		int pid = sc.nextInt();
		sc.nextLine();
		System.out.print("数量を入力してください : ");
		int pnum = sc.nextInt();
		sc.nextLine();

		order.setMember_id(log.getMember_id()); // 아이디(입력x)
		order.setP_id(pid); // 상품번호 
		order.setOrder_quan(pnum); // 수량

		boolean o_cnt = mgrO.orderData(order);
		mgrO.orderProduct(pid, pnum);
		if(o_cnt) {
			System.out.println("購買が完了しました！ありがとうございます：）");
		} else {
			System.out.println("すみません。。\n購買が完了しませんでした。もう一度購入してください。");
		}

	}


	public void pDetail() { // 상품설명

		ArrayList<ProductVO> pList = mgr.listProduct();
		int pNum = 0;

		System.out.print("読みたい商品の説明を選んでください : ");

		try {
			pNum = sc.nextInt();
		} catch (Exception e) {
			System.out.println("ナンバーをもう一度入力してください。");
			sc.nextLine();
		}

		switch (pNum) {
		case 1: System.out.println(pList.get(pNum).getP_detail()); break;
		case 2: System.out.println(pList.get(pNum).getP_detail()); break;
		case 3: System.out.println(pList.get(pNum).getP_detail()); break;
		case 4: System.out.println(pList.get(pNum).getP_detail()); break;
		case 5: System.out.println(pList.get(pNum).getP_detail()); break;
		case 6: System.out.println(pList.get(pNum).getP_detail()); break;
		case 7: System.out.println(pList.get(pNum).getP_detail()); break;
		case 8: System.out.println(pList.get(pNum).getP_detail()); break;
		case 9: System.out.println(pList.get(pNum).getP_detail()); break;
		case 10: System.out.println(pList.get(pNum).getP_detail()); break;
		default: System.out.println("번호를 다시 입력해주세요."); break;
		}
	}

	public void pDisplay(int start, int end) { //상품 목록 출력

		ArrayList<ProductVO> list = mgr.listProduct();

		System.out.println("[ 商品のリスト ]");

		if(list.isEmpty()) {
			System.out.println("登録された商品がありません。");
		}else {

			System.out.println(list.subList(start, end));

		}
	}

	// 최근 검색어
	private void searchedWords(String member_id) {

		ArrayList<KeywordVO> keywordList = mgr.selectAllSearchedWord(member_id);

		if(keywordList.isEmpty()) {
			System.out.println("\n--------------------最近の検索語がありません。--------------------\n");
		} else {
			System.out.println("\n\t\t\t\t  [ 最近の検索語リスト ]");
			if (keywordList.size() < 5) {
				System.out.println("\t\t");
				for(int i=0; i<keywordList.size(); i++) {
					System.out.print("      ["+(i+1)+"] "+keywordList.get(i).getKeyword());
				}
				System.out.println("\n");
			} else {
				System.out.println("\t\t");
				for(int i=0; i<5; i++) {
					System.out.print("    ["+(i+1)+"] "+keywordList.get(i).getKeyword());
				}
				System.out.println("\n");
			}

		}

	}
	
	public void search(MemberVO loginInfo) { // 상품 검색
		System.out.println("\n\t\t\t[ 検索のページ　] ");
		System.out.println("\n 1. 商品名\t 2. メ一カ一\t 3. 最近の検索語\t 0.戻す");
		System.out.print(" * ナンバーを選ぶ : ");
		int type = sc.nextInt();
		
		switch (type) {
		case 1: // 상품명
			sc.nextLine();
			System.out.print(" * 検索する商品名 : ");
			break;
		case 2: // 제조사
			sc.nextLine();
			System.out.print(" * 検索するメ一カ一 : ");
			break;
		case 3: // 최근 검색어 
			searchedWords(loginInfo.getMember_id());
			break;
		case 0: // 뒤로가기
			return;
		default:
			System.out.println("-----------メニュ一は０~３の中のナンバーで入力してください。-----------");
			break;
		}
		
		if (type == 1 || type == 2) {
			String word = sc.nextLine();
			KeywordVO keywordVO = new KeywordVO();
			keywordVO.setKeyword(word);
			keywordVO.setMember_id(loginInfo.getMember_id());
			
			ArrayList<ProductVO> list = mgr.searchP(type, word, keywordVO);
			
			if(list.isEmpty()) {
				System.out.println("-----------------検索の結果がありません。-----------------");
			} else {
				System.out.println();
				for(ProductVO p: list) {
					System.out.println("---------------------------------------------------------------------------------------------");
					System.out.println(" * 商品ID : " + p.getP_id());
					System.out.println(" * 商品名 : "  + p.getP_name());
					System.out.println(" * 原価 : " + p.getCost());
					System.out.println(" * 消費者の価格 : " + p.getPrice());
					System.out.println(" * 在庫量: " + p.getStock());
					System.out.println(" * メ一カ一 : " + p.getP_company());
					System.out.println(" * 商品説明 : " + p.getP_detail());
					System.out.println("---------------------------------------------------------------------------------------------\n");
				}
			}
		}
	}


}

