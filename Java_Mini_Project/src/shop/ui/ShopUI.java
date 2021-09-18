package shop.ui;

import java.util.Scanner;
import shop.mgr.AdminManager;
import shop.mgr.MemberManager;
import shop.vo.AdminVO;
import shop.vo.MemberVO;

public class ShopUI {
	private MemberManager mgr = new MemberManager();
	private AdminManager admgr = new AdminManager(); 

	private Scanner sc = new Scanner(System.in); 

	public ShopUI(){

		boolean run = true;

		while(run) {
			printMainMenu();

			int menu = 0;

			try {
				menu = sc.nextInt();
			} catch (Exception e) {
				System.out.println("メニュ一は１~４の中のナンバーで入力してください。");
				sc.nextLine();
				continue;
			}

			switch (menu) {
			case 1:
				login();
				break;
			case 2:
				adminLogin();
				break;
			case 3:
				insert();
				break;
			case 4:
				run = false;
				System.out.println("プログラムを終了します。");
				break;
			default:
				System.out.println("メニュ一は１~４の中のナンバーで入力してください。");
				break;
			}
		}
	}

	public void printMainMenu(){
		System.out.println("====================");
		System.out.println("日本の輸入品オンラインショップへようこそ！");
		System.out.println("====================");
		System.out.println("1. 一般ユ一ザ一ログイン");
		System.out.println("2. 管理者アカウントログイン");
		System.out.println("3. 登録する");
		System.out.println("4. 終了");
		System.out.println("====================");
		System.out.print("メニュ一ナンバーを入力する > ");
	}

	void login() { //일반 사용자 로그인
		sc.nextLine();

		String member_id,password;
		System.out.print("ID : ");
		member_id = sc.nextLine();
		System.out.print("パスワ一ド : ");
		password = sc.nextLine();

		MemberVO member = new MemberVO();
		member.setMember_id(member_id);
		member.setPassword(password);
		try {
			MemberVO login2 = mgr.searchId(member);

			if(member_id.equals(login2.getMember_id())) {
				if(password.equals(login2.getPassword())) {
					System.out.println("ログインに成功しました。");
					new UserUI(login2);
				} else {
					System.out.println("ログインに失敗しました。");
				}
			} 
		} catch (NullPointerException e) {
			System.out.println("IDまたはパスワ一ドが間違っています。");
		}
	}

	void adminLogin() { //관리자 계정 로그인
		sc.nextLine();

		String admin_id, admin_pwd;
		System.out.print("ID : ");
		admin_id = sc.nextLine();
		System.out.print("パスワ一ド: ");
		admin_pwd = sc.nextLine();

		AdminVO admin = new AdminVO();
		admin.setAdmin_id(admin_id);
		admin.setAdmin_pwd(admin_pwd);

		try {
			AdminVO admin2 = admgr.searchAdmin(admin);

			if(admin_id.equals(admin2.getAdmin_id())) {
				if(admin_pwd.equals(admin2.getAdmin_pwd())) {
					System.out.println("ログインに成功しました。管理者ページへ接続します。");
					new AdminUI();
				} else {
					System.out.println("ログインに失敗しました。");
				}
			}
		} catch (NullPointerException e) {
			System.out.println("IDまたはパスワ一ドが間違っています。");
		}

	}

	void insert() { // 회원가입
		sc.nextLine();

		boolean flag = true;
		boolean flag1 = true;
		boolean flag2 = true;
		String member_id = null;
		String password = null;
		String gender = null;
		String name, address;
		int age = 0;

		while(flag) {
			System.out.print("ID : ");
			member_id = sc.nextLine();
			if(member_id.length() < 4 || member_id.length() > 10) {
				System.out.println("4~10字以内のIDのみ可能です。");
			}else {
				flag = false;
			}
		}
		while(flag1) {
			System.out.print("パスワ一ド : ");
			password = sc.nextLine();
			if(password.length() < 4 || password.length() > 12) {
				System.out.println("4~12字以内のパスワ一ドのみ使用可能です。");
			}else {
				flag1 = false;
			}
		}
		System.out.print("氏名 : ");
		name = sc.nextLine();
		System.out.print("住所 : ");
		address = sc.nextLine();
		System.out.print("年齢 :");
		age = sc.nextInt();
		sc.nextLine();
		
		while(flag2) {
			System.out.print("性別（m/f） : ");
			gender = sc.nextLine();
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

		if(check) { 
			System.out.println("会員登録成功！");
		} else {
			System.out.println("会員登録成功失敗！同じIDがあります。");
		}
	}

}
