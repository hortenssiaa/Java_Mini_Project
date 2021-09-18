package shop.vo;

public class MemberVO { 
	
	private String member_id;
	private String password;
	private String name;
	private String address;
	private String indate;	
	private String gender;
	private int age;
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "아이디 : "+member_id +"  패스워드 : "+ password +"  이름 : "+ name +"  주소 : "+ address +
				"  성별 : "+ gender+"  나이 : "+age+"  가입일 : "+indate; 
	} 
	
	
	
}
