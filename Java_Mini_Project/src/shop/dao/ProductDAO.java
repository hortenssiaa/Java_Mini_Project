package shop.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import shop.vo.KeywordVO;
import shop.vo.ProductVO;

public class ProductDAO {
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	
	public int insertProduct(ProductVO product) { //등록
		SqlSession session = null;
		int cnt= 0;
		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);
			
			cnt = mapper.insertProduct(product);
			
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
	
	public int updateProduct(ProductVO product) { //수정
		SqlSession session = null;
		int cnt = 0;
		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);
			cnt = mapper.updateProduct(product);
			
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(session !=null) {
				session.close();
			}
	}
	return cnt;
	
	
	}
	public ArrayList<ProductVO> listProduct() { //조회
		SqlSession session = null;
		ArrayList<ProductVO> list = null;
		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);
			list = mapper.selectProductAll();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return list;
	}
	
	
	// 수정했음 - 손하경 !! 
	public ArrayList<ProductVO> searchP(HashMap<String,Object> map, KeywordVO keywordVO){
		SqlSession session = null;
		ArrayList<ProductVO> list = null;
		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);
			
			// 검색어를 keyword table 에 insert 
			mapper.insertSearchedWord(keywordVO);
			session.commit();
			
			list = mapper.selectProductTitle(map);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return list;
	}
	
	// 수정했음 - 손하경 - finally 추가 
	// showAllProductTest
	public ArrayList<ProductVO> showAllProductTest() {
		
		SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
		
		ArrayList<ProductVO> productList = null;
		SqlSession session = null;
		
		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);
			
			productList = mapper.showAllProductTest();
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		return productList;
	}
	
	
	
	
	// 수정했음 - 손하경 
	// 최근 키워드 갖고오기
	public ArrayList<KeywordVO> selectAllSearchedWord(String member_id) {
		
		SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
		
		ArrayList<KeywordVO> keywoardList = null;
		SqlSession session = null;
		
		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);
			
			keywoardList = mapper.selectAllSearchedWord(member_id);
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		return keywoardList;
	}
	
}
