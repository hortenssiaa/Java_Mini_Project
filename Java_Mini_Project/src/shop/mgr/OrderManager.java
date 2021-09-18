package shop.mgr;

import java.util.ArrayList;
import java.util.HashMap;

import shop.dao.OrderDAO;
import shop.vo.OrderVO;

public class OrderManager { 
	OrderDAO dao = new OrderDAO();

	public boolean orderData(OrderVO order) {
		int cnt = dao.orderData(order);

		if(cnt > 0) {
			return true;
		}
		return false;
	}

	public int orderProduct(int pid, int pnum) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("order_quan", pnum);
		map.put("p_id", pid);
		return dao.orderProduct(map);
	}

	//�ֹ� ���� ��ȸ
	public ArrayList<OrderVO> selectOrderAll(){
		ArrayList<OrderVO> list = dao.selectOrderAll();
		return list;
	}

	//�ֹ� ���� ����
	public int updateOrder(OrderVO vo) {
		int cnt = dao.updateOrder(vo);
		return cnt;
	}

	//Ʈ����(����)
	public ArrayList<HashMap<String, Object>> selectTrendMale(){
		return dao.selectTrendMale();
	}

	public ArrayList<HashMap<String, Object>> selectTrendFemale(){
		return dao.selectTrendFemale();
	}
	//Ʈ����(����)
	public ArrayList<HashMap<String, Object>> selectTrendAge01(){
		return dao.selectTrendAge01();
	}

	public ArrayList<HashMap<String, Object>> selectTrendAge02(){
		return dao.selectTrendAge02();
	}

	public ArrayList<HashMap<String, Object>> selectTrendAge03(){
		return dao.selectTrendAge03();
	}

	// �ֹ� ���� ����
	public int deleteOrder(String order_id) {
		int cnt = dao.deleteOrder(order_id);
		return cnt;
	}
}
