package shop.ui;

import java.util.Scanner;

public class ShopUI {
	
	private Scanner sc = new Scanner(System.in); 
	
	public ShopUI() {
		
		boolean run = true;

		while(run) {
			printMainMenu();

			int menu = 0;

			try {
				menu = sc.nextInt();
			} catch (Exception e) {
				System.out.println("メニュ一は１~３の中の番号で入力してください。");
				sc.nextLine();
				continue;
			}

			switch (menu) {
			case 1:
				login();
				break;
			case 2:
				insert();
				break;
			case 3:
				run = false;
				System.out.println("プログラムを終了します。");
				break;
			default:
				System.out.println("メニュ一は１~３の中の番号で入力してください。");
				break;
			}
		}
	}
	
	public void printMainMenu(){
		System.out.println("====================");
		System.out.println("1. ログイン");
		System.out.println("2. 会員登録");
		System.out.println("3. 終了する");
		System.out.println("====================");
		System.out.println("メニュ一番号を入力する > ");
	}

	void login() { //exception
	}

	void insert() { //exception 
	}

}
