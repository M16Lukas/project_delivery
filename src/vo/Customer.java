package vo;

public class Customer {
	private int customer_num;
	private int member_num;
	private String customer_name;
	private String customer_phone;
	private int area_num;
	private String customer_address;
	
	
	/*
	 * Constructor
	 */
	public Customer() {
	}
	
	// 회원가입
	public Customer(int member_num, String customer_name, String customer_phone, int area_num, String customer_address) {
		this.member_num = member_num;
		this.customer_name = customer_name;
		this.customer_phone = customer_phone;
		this.area_num = area_num;
		this.customer_address = customer_address;
	}
	
	// 로그인한 개인 회원 상세 정보 변경
	public Customer(String customer_name, String customer_phone, int area_num, String customer_address,  int customer_num) {
		this.customer_name = customer_name;
		this.customer_phone = customer_phone;
		this.area_num = area_num;
		this.customer_address = customer_address;
		this.customer_num = customer_num;
	}

	
	/*
	 * Getter & Setter
	 */
	public int getCustomer_num() {
		return customer_num;
	}

	public void setCustomer_num(int customer_num) {
		this.customer_num = customer_num;
	}

	public int getMember_num() {
		return member_num;
	}

	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}

	public int getArea_num() {
		return area_num;
	}

	public void setArea_num(int area_num) {
		this.area_num = area_num;
	}

	public String getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}
	
	
}
