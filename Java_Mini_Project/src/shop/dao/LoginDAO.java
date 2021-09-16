package shop.dao;


import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import shop.vo.Login;
import shop.vo.MemberVO;
import shop.dao.ShopMapper;

public class LoginDAO {
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory(); // 마이바티스 객체
	
	
	//글 저장
	public MemberVO searchId(MemberVO login) {
		SqlSession session = null;
		MemberVO log = null;
		
		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);
			log = mapper.searchId(login);
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
	
	public int register(MemberVO login) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);
			cnt = mapper.register(login);
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
	
}
