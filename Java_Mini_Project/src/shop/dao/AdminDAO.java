package shop.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import shop.vo.AdminVO;

public class AdminDAO {
	
private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory(); // ���̹�Ƽ�� ��ü
	
	//�� ����
	public AdminVO searchAdmin(AdminVO admin) {
		SqlSession session = null;
		AdminVO log = null;
		
		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);
			log = mapper.searchAdmin(admin);
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
}
