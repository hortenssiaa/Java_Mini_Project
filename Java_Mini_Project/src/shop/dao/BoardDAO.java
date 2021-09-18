package shop.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import shop.vo.BoardVO;

public class BoardDAO { 
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	
	// ������
	public int insertBoard(BoardVO board) {
		SqlSession session = null;
		int cnt  = 0;
		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);
			cnt = mapper.insertBoard(board);
			
			session.commit();
		}catch (Exception e) {
//			e.printStackTrace();
			System.out.println("��ǰ ��ȣ�� ã�� �� �����ϴ�.");
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return cnt;
	}
	// �� ���
	public ArrayList<BoardVO> listBoard(){
		SqlSession session = null;
		ArrayList<BoardVO> list = null;
		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);
			list = mapper.selectBoardAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return list;
	}
	// 1���� �� �а� ��ȸ�� ����
	public BoardVO readBoard(int num) {
		SqlSession session = null;
		BoardVO board = null;
		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);
			// �ش� �� ��ȸ�� ����
			mapper.updateHits(num);
			session.commit();
			board = mapper.selectBoardByNum(num);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session != null) {
					session.close();
				}
			} return board;
		}
		// 1���� �� ����
	public int deleteBoard(BoardVO board) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);
			
			cnt = mapper.deleteBoard(board);
			session.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return cnt;
	}
		// �� �˻�
	public ArrayList<BoardVO> searchBoardVO(HashMap<String, Object> map){
		SqlSession session = null;
		ArrayList<BoardVO> list = null;
		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);
			list = mapper.selectBoardByTitle(map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return list;
	}	
}
