package shop.mgr;


import shop.dao.LoginDAO;
import shop.vo.Login;
import shop.vo.MemberVO;

public class LoginManager {

	private LoginDAO dao = new LoginDAO();
	
	public MemberVO searchId(MemberVO login) {
		return dao.searchId(login);
	}
	
	public boolean register(MemberVO login) {
		int cnt = dao.register(login);
		
		if(cnt > 0) {
			return true;
		}
		return false;
	}
}
