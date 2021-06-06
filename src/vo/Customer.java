package vo;

public class Customer {
	private int customer_num;
	private int member_num;
	private String customer_name;
	private String customer_phone;
	private int area_num;
	private String customer_address;
	
	public Customer(int member_num, String customer_name, String customer_phone, int area_num, String customer_address) {
		this.member_num = member_num;
		this.customer_name = customer_name;
		this.customer_phone = customer_phone;
		this.area_num = area_num;
		this.customer_address = customer_address;
	}
}
