package shop.ui;

import java.util.Scanner;

import shop.mgr.LoginManager;
import shop.vo.Login;
import shop.vo.MemberVO;

public class LoginUI {
	private LoginManager mgr = new LoginManager();
	
	private Scanner sc = new Scanner(System.in); 

	public LoginUI() {

		boolean run = true;

		while(run) {
			printMainMenu();

			int menu = 0;

			try {
				menu = sc.nextInt();
			} catch (Exception e) {
				System.out.println("メニュ一は1~4の中の番号で入力してください。");
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
				System.out.println("メニュ一は1~4の中の番号で入力してください。");
				break;
			}
		}
	}

	public void printMainMenu(){
		System.out.println("====================");
		System.out.println("1. 一般ユ一ザ一ログイン");
		System.out.println("2. 管理者アカウントログイン");
		System.out.println("3. 登録する");
		System.out.println("4. 終了");
		System.out.println("====================");
		System.out.println("メニュ一番号を入力する > ");
	}

	void login() { //exception
		sc.nextLine();

		String member_id, password;
		System.out.println("ID : ");
		member_id = sc.nextLine();
		System.out.println("パスワ一ド : ");
		password = sc.nextLine();

		MemberVO login = new MemberVO();
		login.setMember_id(member_id);
		login.setPassword(password);
		try {
			MemberVO login2 = mgr.searchId(login);

			if(member_id.equals(login2.getMember_id())) {
				if(password.equals(login2.getPassword())) {
					System.out.println("ログインに成功しました。");
					UserUI userUi = new UserUI(login2);
				} 
			} 
		} catch (NullPointerException e) {
			System.out.println("IDまたはパスワ一ドが間違っています。");
		}
	}

	void insert() { //exception 
		sc.nextLine();

		boolean flag = true;
		boolean flag1 = true;
		boolean flag2 = true;
		String member_id = null;
		String password = null;
		String name, address, gender;
		int age;
		
		while(flag) {
			System.out.println("ID : ");
			member_id = sc.nextLine();
			if(member_id.length() < 4 || member_id.length() > 10) {
				System.out.println("4~10字以内のIDのみ可能です。");
			}else {
				flag = false;
			}
		}
		while(flag1) {
			System.out.println("パスワ一ド : ");
			password = sc.nextLine();
			if(password.length() < 4 || password.length() > 12) {
				System.out.println("4~12字以内のパスワ一ドのみ使用可能です。");
			}else {
				flag1 = false;
			}
		}
		System.out.println("氏名 : ");
		name = sc.nextLine();
		System.out.println("住所 : ");
		address = sc.nextLine();
		System.out.println("年齢 :");
		age = sc.nextInt();
		sc.nextLine();
		System.out.println("性別 : ");
		gender = sc.nextLine();
		while(flag2) {
			if(!gender.equals("m") && !gender.equals("f")) {
				System.out.println("m/fの中で一つを入力してください。");
			} else {
				flag2 = false;
			}
		}
		MemberVO login = new MemberVO();
					
			login.setMember_id(member_id);
			login.setPassword(password);
			login.setName(name);
			login.setAddress(address);
			login.setAge(age);
			login.setGender(gender);
			
			boolean check = mgr.register(login);
			
//			if(member_id.equals(login1.getMember_id())) {
//				System.out.println("회원가입 실패! 같은 아이디가 있습니다!");
//			}
			
			if(check) { 
				System.out.println("会員登録成功！");
			} else {
				System.out.println("会員登録成功失敗！同じIDがあります。");
			}
	}
	
	
}
