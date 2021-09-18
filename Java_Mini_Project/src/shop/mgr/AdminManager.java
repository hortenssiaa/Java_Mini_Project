package shop.mgr;

import shop.dao.AdminDAO;
import shop.vo.AdminVO;

public class AdminManager { 
	private AdminDAO dao = new AdminDAO();
	
	public AdminVO searchAdmin(AdminVO admin) { //Á¶È¸
		return dao.searchAdmin(admin);
	}
	

}
