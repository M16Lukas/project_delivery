package vo;

public class Store {
	private int store_num;
	private int member_num;
	private int area_num;
	private int code_num;
	private String store_name;
	private String store_phone;
	private int minOrderPrice;
	private int deliveryTime;
	private int deliveryTip;
	
	public Store(int member_num, int area_num, int code_num, String store_name, String store_phone, int minOrderPrice, int deliveryTime, int deliveryTip) {
		this.member_num = member_num;
		this.area_num = area_num;
		this.code_num = code_num;
		this.store_name = store_name;
		this.store_phone = store_phone;
		this.minOrderPrice = minOrderPrice;
		this.deliveryTime = deliveryTime;
		this.deliveryTip = deliveryTip;
	}
}
