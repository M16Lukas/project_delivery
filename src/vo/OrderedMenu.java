package vo;

public class OrderedMenu {
	private int ordered_num;
	private int customer_num;
	private int store_num;
	private int menu_num;
	private int menu_count;
	private String ordred_time;
	private int total_price;
	
	
	/*
	 * Constructor
	 */
	public OrderedMenu () {}
	
	// 주문 기능
	public OrderedMenu (int customer_num, int store_num, int menu_num, int menu_count, int total_price) {
		this.customer_num = customer_num;
		this.store_num = store_num;
		this.menu_num = menu_num;
		this.menu_count = menu_count;
		this.total_price = total_price;
	}
	
	
}
