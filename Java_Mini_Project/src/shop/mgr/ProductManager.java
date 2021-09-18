package shop.mgr;

import java.util.ArrayList;
import java.util.HashMap;

import shop.dao.ProductDAO;
import shop.vo.KeywordVO;
import shop.vo.ProductVO;

public class ProductManager {

	ProductDAO dao = new ProductDAO();
	//상품 목록
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

	//상품 검색
	public ArrayList<ProductVO> searchP(int type, String word){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("word",word);

		return dao.searchP(map);

	}
	
	public ArrayList<ProductVO> searchP(int type, String word, KeywordVO keywordVO){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("word",word);

		return dao.searchP(map, keywordVO);

	}

	public ArrayList<ProductVO> showAllProductTest() {
		return dao.showAllProductTest();
	}

	// 상품 정보 삭제
	public int deleteProduct(String p_id) {
		int cnt = dao.deleteProduct(p_id);
		return cnt;
	}


	public ArrayList<KeywordVO> selectAllSearchedWord(String member_id) {
		return dao.selectAllSearchedWord(member_id);
	}

}
