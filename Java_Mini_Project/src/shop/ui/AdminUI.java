package shop.ui;

import java.util.ArrayList;
import java.util.Scanner;

import shop.mgr.MemberManager;
import shop.mgr.OrderManager;
import shop.mgr.ProductManager;
import shop.vo.MemberVO;
import shop.vo.OrderVO;
import shop.vo.ProductVO;

public class AdminUI {
	Scanner sc = new Scanner(System.in);
	private ProductManager mgr = new ProductManager();
	private MemberManager mgrM = new MemberManager();
	private OrderManager mgrO = new OrderManager();

	public AdminUI() {

		boolean run = true;

		while(run) {
			printMainMenu();

			int menu = 0;

			try {
				menu = sc.nextInt();
			} catch (Exception e) {
				System.out.println("メニュ一は1~5の中のナンバーで入力してください。");
				sc.nextLine();
			}

			switch (menu) {
			case 1: // 회원 정보 관리
				memberInfo();
				break;
			case 2: // 상품 정보 관리
				pInfo();
				break;
			case 3: // 주문 정보 관리
				oInfo();
				break;
			case 4: // 뒤로가기
				return;
			case 5:
				System.out.println("プログラムを終了します。");
				run = false;
				break;
			default:
				System.out.println("メニュ一は1~5の中のナンバーで入力してください。");
				break;
			}
		}	

	}

	void oInfo() { // 주문 정보 관리
		System.out.print("1. 注文情報を照会する  2. 注文情報を修正する  3. 注文情報を削除する  4. 戻る  ナンバーを入力する : ");
		int oinfo = sc.nextInt();


		switch (oinfo) {
		case 1: // 주문 정보 조회
			listOrder();
			break;
		case 2: // 주문 정보 수정
			updateOrder();
			break;
		case 3: // 주문 정보 삭제
			deleteOrder();
			break;
		case 4:
			return;
		default:
			System.out.println("もう一度入力してください。");
			break;
		}
	}


	void pInfo() { // 상품 정보 관리
		System.out.print("1. 商品情報を照会する  2. 商品情報を修正する  3. 商品を登録する  4. 商品情報を削除する  5. 戻る  ナンバーを入力する : ");
		int pinfo = sc.nextInt();


		switch (pinfo) {
		case 1: // 상품 정보 조회
			list();
			break;
		case 2: // 상품 정보 수정
			updateMember();
			break;
		case 3: // 상품 정보 등록
			insert();
			break;
		case 4: // 상품 정보 삭제
			deleteProduct();
			break;
		case 5:
			return;
		default:
			System.out.println("もう一度入力してください。");
			break;
		}
	}

	void memberInfo() { // 회원 정보 관리
		System.out.print("1. ユーザー情報を照会する  2. ユーザー情報を修正する  3. ユーザー情報を削除する  4. 戻る  ナンバーを入力する : ");
		int minfo = sc.nextInt();


		switch (minfo) {
		case 1: // 회원 정보 조회
			listMember();
			break;
		case 2: // 회원 정보 수정
			update();
			break;
		case 3: // 회원 정보 삭제
			deleteMember();
			break;
		case 4: 
			return;
		default:
			System.out.println("もう一度入力してください。");
			break;
		}
	}
	void deleteOrder() { // 주문 정보 삭제
		sc.nextLine();
		
		System.out.println("[ 注文情報を削除するページ ]");
		System.out.print("削除する注文のID : ");
		String oid = sc.nextLine();

		int cnt = mgrO.deleteOrder(oid);

		if(cnt > 0) {
			System.out.println("商品の情報削除が完了しました。");
		}else {
			System.out.println("商品の情報削除が失敗しました。");
		}
	}
	
	
	
	void deleteProduct() { // 상품 정보 삭제
		sc.nextLine();
		System.out.println("[ 商品情報を削除するページ ]");
		System.out.print("削除する商品のID : ");
		String pid = sc.nextLine();

		int cnt = mgr.deleteProduct(pid);

		if(cnt > 0) {
			System.out.println("商品情報の削除が完了しました。");
		}else {
			System.out.println("商品情報を削除に失敗しました。");
		}
	}

	void deleteMember() { // 회원 정보 삭제
		sc.nextLine();
		System.out.println("[ ユーザー情報を削除するページ ]");
		System.out.print("削除するユーザーのID : ");
		String member_id = sc.nextLine();

		int cnt = mgrM.deleteMember(member_id);

		if(cnt > 0) {
			System.out.println("ユーザー情報の削除が完了しました。");
		}else {
			System.out.println("ユーザー情報の削除に失敗しました。");
		}
	}

	public void printMainMenu(){ // 회원 메뉴 출력
		System.out.println("====================");
		System.out.println("1. ユーザーの情報を管理する");
		System.out.println("2. 商品の情報を管理する");
		System.out.println("3. 注文の情報を管理する");
		System.out.println("4. 戻す");
		System.out.println("5. プログラムを終了する");
		System.out.println("=====================");
		System.out.print("メニューナンバー > ");
	}

	// 회원관리 (회원 목록 조회)
	void listMember() {
		System.out.println(" [ 全ユーザーリスト ] ");
		ArrayList<MemberVO> list = mgrM.listMember();

		if(list.isEmpty()) {
			System.out.println("加入する会員がありません。");
		}else {
			for(MemberVO m :list) {
				System.out.println(m);
			}

		}
	}

	// 회원관리 (회원 목록 수정)
	void updateMember() {
		sc.nextLine();
		System.out.println("[ ユーザー情報を修正するページ ]");
		System.out.print("修正するユーザーのID : ");
		String updateMember_id = sc.nextLine();
		System.out.print("修正するユーザーの氏名: ");
		String updateName = sc.nextLine();
		System.out.print("修正するユーザーのパスワ一ド: ");
		String updatePassword = sc.nextLine();
		System.out.print("修正するユーザーの住所: ");
		String updateAddress = sc.nextLine();
		System.out.print("修正するユーザーの性別: ");
		String updateGender = sc.nextLine();
		System.out.print("修正するユーザーの年齢: ");
		int updateAge = sc.nextInt();
		sc.nextLine();

		MemberVO updateMember = new MemberVO();
		updateMember.setMember_id(updateMember_id);
		updateMember.setName(updateName);
		updateMember.setPassword(updatePassword);
		updateMember.setAddress(updateAddress);
		updateMember.setGender(updateGender);
		updateMember.setAge(updateAge);

		int updateStr = mgrM.updateMember(updateMember);	

		if(updateStr > 0) {
			System.out.println("ユーザー情報の修正に成功しました！");
		}else {
			System.out.println("ユーザー情報の修正に失敗しました。");

		}
	}

	void list() { //상품 목록 조회
		System.out.println(" [ 全商品のリスト ] ");

		ArrayList<ProductVO> list = mgr.listProduct();

		if(list.isEmpty()) {
			System.out.println("登録した商品がありません。");
		}else {
			for(ProductVO p: list) {
				System.out.println(" *　商品のID : " + p.getP_id());
				System.out.println(" *　商品名 : "  + p.getP_name());
				System.out.println(" *　原価 : " + p.getCost());
				System.out.println(" *　消費者の価格 : " + p.getPrice());
				System.out.println(" * 在庫量: " + p.getStock());
				System.out.println(" *　メ一カ一 : " + p.getP_company());
				System.out.println(" * 商品説明 : " + p.getP_detail());
				System.out.println();
			}
		}
	}

	void update() { //상품 목록 수정
		sc.nextLine();
		System.out.println("[ 商品登録を修正するページ ]");
		System.out.print("修正する商品名 :　");
		String updateP_name = sc.nextLine();
		System.out.print("修正する原価　: ");
		int updateCost = sc.nextInt();
		System.out.print("修正する消費者の価格　: ");
		int updatePrice = sc.nextInt();
		System.out.print("修正する在庫量　: ");
		int updateStock = sc.nextInt();
		sc.nextLine();
		System.out.print("修正するメ一カ一　: ");
		String updateP_company = sc.nextLine();
		System.out.print("修正する商品説明　: ");
		String updateP_detail = sc.nextLine();
		System.out.print("修正するユーザーナンパー : ");
		int updateP_id = sc.nextInt();

		ProductVO updateProduct = new ProductVO();
		updateProduct.setP_id(updateP_id);
		updateProduct.setP_name(updateP_name);
		updateProduct.setCost(updateCost);
		updateProduct.setPrice(updatePrice);
		updateProduct.setStock(updateStock);
		updateProduct.setP_company(updateP_company);
		updateProduct.setP_detail(updateP_detail);

		int updateCnt = mgr.updateProduct(updateProduct);

		if(updateCnt > 0) {
			System.out.println("修正に成功しました！");
		}else {
			System.out.println("修正に失敗しました。");
		}
	}

	public void insert() { //상품 등록
		sc.nextLine();
		System.out.println("[　商品の情報修正ページ　]");
		System.out.print("商品名: ");
		String p_name = sc.nextLine();
		System.out.print("原価: ");
		int cost = sc.nextInt();
		System.out.print("消費者の価格: ");
		int price = sc.nextInt();
		System.out.print("在庫量: ");
		int stock = sc.nextInt();
		sc.nextLine();
		System.out.print("メ一カ一: ");
		String p_company = sc.nextLine();
		System.out.print("商品説明: ");
		String p_detail = sc.nextLine();

		ProductVO product = new ProductVO();
		product.setP_name(p_name);
		product.setCost(cost);
		product.setPrice(price);
		product.setStock(stock);
		product.setP_company(p_company);
		product.setP_detail(p_detail);

		int cnt = mgr.insertProduct(product);

		if(cnt > 0) {
			System.out.println("商品登録に成功しました！");
		}else {
			System.out.println("商品登録に失敗しました。");
		}
	}

	//주문 정보 조회
	void listOrder() {
		System.out.println("\n[ 注文の情報リスト ]");
		ArrayList<OrderVO> list = mgrO.selectOrderAll();

		if(list.isEmpty()) {
			System.out.println("登録された注文がありません。");
		}else {
			for(OrderVO o : list) {
				System.out.println("注文のID: " + o.getOrder_id());
				System.out.println("ユーザーのID: " + o.getMember_id());
				System.out.println("商品のID: " + o.getP_id());
				System.out.println("注文の日付: " + o.getOrder_date());
				System.out.println("注文: " + o.getOrder_quan());
				System.out.println();
			}
		}
	}
	//주문 정보 수정
	void updateOrder() {

		System.out.println("[　注文の上方修正ページ　]");
		System.out.print("修正つる注文ID: ");
		int updateOrderId = sc.nextInt();
		System.out.println("=================");
		System.out.print("修正する商品ID: ");
		int updateId = sc.nextInt();
		System.out.print("修正する注文量: ");
		int updateQuan = sc.nextInt();


		OrderVO updateOrder = new OrderVO();
		updateOrder.setOrder_id(updateOrderId);
		updateOrder.setP_id(updateId);
		updateOrder.setOrder_quan(updateQuan);

		int updateCnt = mgrO.updateOrder(updateOrder);

		if(updateCnt > 0) {
			System.out.println("修正が完了しました！");
		}else {
			System.out.println("修正に失敗しました。");
		}
	}
}
