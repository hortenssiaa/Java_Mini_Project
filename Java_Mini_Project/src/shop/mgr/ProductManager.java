package shop.mgr;

import java.util.ArrayList;
import java.util.HashMap;

import shop.dao.ProductDAO;
import shop.vo.KeywordVO;
import shop.vo.ProductVO;

public class ProductManager {
	
	private ProductDAO dao = new ProductDAO();
	
	//��ǰ ���
	public ArrayList<ProductVO> listProduct() {
		ArrayList<ProductVO> list = dao.listProduct();
		return list;
	}

	public int updateProduct(ProductVO product) {
		return dao.updateProduct(product);
	}

	public int insertProduct(ProductVO product) {
		return dao.insertProduct(product);
	}

	// �������� - ���ϰ� !! 
	//��ǰ �˻�
	public ArrayList<ProductVO> searchP(int type, String word, KeywordVO keywordVO){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("word",word);

		return dao.searchP(map, keywordVO);

	}

	public ArrayList<ProductVO> showAllProductTest() {
		return dao.showAllProductTest();
	}

	public ArrayList<KeywordVO> selectAllSearchedWord(String member_id) {
		return dao.selectAllSearchedWord(member_id);
	}

}
