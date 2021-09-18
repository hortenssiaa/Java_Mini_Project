package shop.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import shop.vo.BoardVO;

public class BoardDAO { 
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	
	// 글저장
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
			System.out.println("상품 번호를 찾을 수 없습니다.");
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return cnt;
	}
	// 글 목록
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
	// 1개의 글 읽고 조회수 증가
	public BoardVO readBoard(int num) {
		SqlSession session = null;
		BoardVO board = null;
		try {
			session = factory.openSession();
			ShopMapper mapper = session.getMapper(ShopMapper.class);
			// 해당 글 조회수 증가
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
		// 1개의 글 삭제
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
		// 글 검색
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
