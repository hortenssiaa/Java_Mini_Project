package shop.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import shop.vo.OrderVO;

public class OrderDAO {

	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	OrderVO vo = new OrderVO();
	public int orderData(OrderVO order) { // 주문 정보 
		SqlSession session = null;
		int cnt= 0;
		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);

			//주문 정보 저장
			cnt = mapper.orderData(order);

			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null){
				session.close();
			}
		}
		return cnt;

	}

	public int orderProduct(HashMap<String, Integer> map) { // 재고량 변화
		SqlSession session = null;
		int cnt = 0;
		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);

			//주문 정보 저장
			cnt = mapper.orderProduct(map);

			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null){
				session.close();
			}
		}
		return cnt;

	}

	//주문 정보 조회
	public ArrayList<OrderVO> selectOrderAll(){
		SqlSession session = null;
		ArrayList<OrderVO> list = null;

		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);
			list = mapper.selectOrderAll();

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return list;
	}

	//주문 정보 수정
	public int updateOrder(OrderVO vo) {
		SqlSession session = null;
		int cnt = 0;

		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);

			cnt = mapper.updateOrder(vo);
			session.commit();

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return cnt;
	}
	
	//트렌드(성별)
		public ArrayList<HashMap<String, Object>> selectTrendMale(){
			SqlSession session = null;
			ArrayList<HashMap<String, Object>> list = null;
			try {
				session = factory.openSession();
				ShopMapper mapper = session.getMapper(ShopMapper.class);
				
				list = mapper.selectTrendMale();
				
				}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(session != null) {
					session.close();
				}
			}
			return list;
		}
		public ArrayList<HashMap<String, Object>> selectTrendFemale(){
			SqlSession session = null;
			ArrayList<HashMap<String, Object>> list = null;
			try {
				session = factory.openSession();
				ShopMapper mapper = session.getMapper(ShopMapper.class);
				
				list = mapper.selectTrendFemale();
				
				}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(session != null) {
					session.close();
				}
			}
			return list;
		}
		// 트렌드(나이)
		public ArrayList<HashMap<String, Object>> selectTrendAge01() {
			SqlSession session = null;
			ArrayList<HashMap<String, Object>> list = null;
			try {
				session = factory.openSession();
				ShopMapper mapper = session.getMapper(ShopMapper.class);
				
				list = mapper.selectTrendAge01();
				
				}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(session != null) {
					session.close();
				}
			}
			return list;
		}
		
		public ArrayList<HashMap<String, Object>> selectTrendAge02() {
			SqlSession session = null;
			ArrayList<HashMap<String, Object>> list = null;
			try {
				session = factory.openSession();
				ShopMapper mapper = session.getMapper(ShopMapper.class);
				
				list = mapper.selectTrendAge02();
				
				}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(session != null) {
					session.close();
				}
			}
			return list;
		}
		
		public ArrayList<HashMap<String, Object>> selectTrendAge03() {
			SqlSession session = null;
			ArrayList<HashMap<String, Object>> list = null;
			try {
				session = factory.openSession();
				ShopMapper mapper = session.getMapper(ShopMapper.class);
				
				list = mapper.selectTrendAge03();
				
				}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(session != null) {
					session.close();
				}
			}
			return list;
		}
		
		// 주문 정보 삭제
		public int deleteOrder(String order_id) {
			SqlSession session = null;
			int cnt = 0;

			try {
				session = factory.openSession();
				ShopMapper mapper = session.getMapper(ShopMapper.class);

				cnt = mapper.deleteProduct(order_id);
				session.commit();

			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(session != null) {
					session.close();
				}
			}
			return cnt;
		}
}
