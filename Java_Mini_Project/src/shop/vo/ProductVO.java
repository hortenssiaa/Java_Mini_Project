package shop.vo;

public class ProductVO {

		private int p_id;
		private String p_name;
		private int	cost;
		private int price;
		private int stock;
		private String p_company;
		private String p_detail;
		
		public int getP_id() {
			return p_id;
		}
		public void setP_id(int p_id) {
			this.p_id = p_id;
		}
		public String getP_name() {
			return p_name;
		}
		public void setP_name(String p_name) {
			this.p_name = p_name;
		}
		public int getCost() {
			return cost;
		}
		public void setCost(int cost) {
			this.cost = cost;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
			
		}
		public int getStock() {
			return stock;
		}
		public void setStock(int stock) {
			this.stock = stock;
		}
		public String getP_company() {
			return p_company;
		}
		public void setP_company(String p_company) {
			this.p_company = p_company;
		}
		public String getP_detail() {
			return p_detail;
		}
		public void setP_detail(String p_detail) {
			this.p_detail = p_detail;
		}
		
		@Override
		public String toString() {
			return p_id +"\t상품명 : "+ p_name +"\t가격 : "+ price +"\t제조사 : "+ p_company +"\t재고량 : "+ stock; 
		} 
		
}
