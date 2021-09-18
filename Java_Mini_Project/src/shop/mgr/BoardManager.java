package shop.mgr;

import java.util.ArrayList;
import java.util.HashMap;

import shop.dao.BoardDAO;
import shop.vo.BoardVO;

public class BoardManager { 
	private BoardDAO dao = new BoardDAO();
	
	// �� ����
	public boolean insertBoard(BoardVO board) {
		int cnt = dao.insertBoard(board);
		if(cnt > 0) {
			return true;
		} 
		return false;
	}
	// �� ���
	public ArrayList<BoardVO> listBoard(){
		ArrayList<BoardVO> list = dao.listBoard();
		return list;
	}
	// 1���� �� �а� ��ȸ�� ����
	public BoardVO readBoard(int num) {
		return dao.readBoard(num);
	}
	// 1���� �� ����
	public boolean deleteBoard(BoardVO board) {
		return dao.deleteBoard(board) > 0 ? true : false;
	}
	// �� �˻�
	public ArrayList<BoardVO> searchBoardVO(int type, String word){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("word", word);
		return dao.searchBoardVO(map);
			
	}
	
}
