package shop.dao;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import shop.vo.MemberVO;

public class MemberDAO {
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory(); // 마이바티스 객체

	//글 저장
	public MemberVO searchId(MemberVO member) {
		SqlSession session = null;
		MemberVO log = null;

		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);
			log = mapper.searchId(member);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return log;
	}

	public int register(MemberVO member) {
		SqlSession session = null;
		int cnt = 0;

		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);
			cnt = mapper.register(member);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return cnt;
	}

	//회원관리(회원 목록 조회)
	public ArrayList<MemberVO> listMember() {
		SqlSession session = null;
		ArrayList<MemberVO> list = null;
		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);
			list = mapper.selectMemberAll();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return list;
	}

	public int updateMember(MemberVO vo) {
		SqlSession session = null;
		int cnt = 0;

		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);

			cnt = mapper.updateMember(vo);

			session.commit();

		}catch(Exception e) {

		}finally {
			if(session != null) {
				session.close();
			}
		}

		return cnt;
	}

	//회원탈퇴
	public int deleteMember(String member_id) {
		SqlSession session = null;
		int cnt = 0;

		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);

			cnt = mapper.deleteMember(member_id);
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
	
	// 마이페이지(정보 수정) - 손하경
		public int updateUser(MemberVO member) {
			
			SqlSession session = null;
			int count = 0;
			
			try {
				session = factory.openSession();
				ShopMapper mapper = session.getMapper(ShopMapper.class);
				count = mapper.updateUserInfo(member);
				session.commit();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (session != null) {
					session.close();
				}
			}		
			
			return count;
		}

		// 마이페이지(자주찾는 상품) - 손하경
		public ArrayList<HashMap<String, Object>> showPopularProduct(String member_id) {

			ArrayList<HashMap<String, Object>> productList = null;
			SqlSession session = null;
			
			try {
				session = factory.openSession();
				ShopMapper mapper = session.getMapper(ShopMapper.class);
				
				productList = mapper.popularProduct(member_id);
						
						
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return productList;
		}
		

		// 마이페이지(구매상품) - 손하경
		public ArrayList<HashMap<String, Object>> orderdProduct(String member_id) {
			
			ArrayList<HashMap<String, Object>> productList = null;
			SqlSession session = null;
			
			try {
				session = factory.openSession();
				ShopMapper mapper = session.getMapper(ShopMapper.class);
				
				productList = mapper.orderdProduct(member_id);
						
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return productList;
		}
		
		
		// 마이페이지(내 후기보기) - 손하경
		public ArrayList<HashMap<String, Object>> myReview(String member_id) {
			
			ArrayList<HashMap<String, Object>> reviewList = null;
			SqlSession session = null;
			
			try {
				session = factory.openSession();
				ShopMapper mapper = session.getMapper(ShopMapper.class);
				
				reviewList = mapper.showAllMyReview(member_id);
						
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return reviewList;
		}
}
