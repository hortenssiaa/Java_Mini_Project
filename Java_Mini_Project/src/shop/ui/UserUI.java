package shop.ui;

import java.util.Scanner;

import shop.vo.Login;
import shop.vo.MemberVO;

public class UserUI {
	private Scanner sc = new Scanner(System.in); 
	
	// 매개변수 생성자로 변경 - 아이디로 디비 읽어오기 위해 
	public UserUI(MemberVO loginInfo) {
		boolean run = true;
		
		while(run) {
		printMainMenu1();
		
		int menu = 0;
		
		try {
			menu = sc.nextInt();
		} catch (Exception e) {
			System.out.println("メニュ一は１~６の中の番号で入力してください。");
			sc.nextLine();
		}
		
		switch (menu) {
		case 1: //상품목록
//			PlistUI plist = new PlistUI(); // 상품목록
			ProductUI pui = new ProductUI(loginInfo);
			break;
		case 2: //마이페이지
			MypageUI mypage = new MypageUI(loginInfo); // 마이페이지 
			break;
		case 3: //트렌드
			break;
		case 4: //게시판
			break;
		case 5: //회원탈퇴
			break;
		case 6: // 프로그램종료
			break;
		default:
			System.out.println("メニュ一は１~６の中の番号で入力してください。");
			break;
			}
		}	
	}
	
	
	public void printMainMenu1(){
		System.out.println("====================");
		System.out.println("1. 商品リストを見る");
		System.out.println("2. マイペ一スに行く");
		System.out.println("3. トレンドを見る");
		System.out.println("4. 揭示板を見る");
		System.out.println("5. 会員退会する");
		System.out.println("6. プログラミングを終了する");
		System.out.println("====================");
		System.out.println("メニュ一番号を入力する > ");
	}
}
