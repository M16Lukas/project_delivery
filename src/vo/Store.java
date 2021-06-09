package vo;

public class Store {
	/*
	 * field
	 */
	private int store_num;
	private int member_num;
	private int area_num;
	private int code_num;
	private String store_name;
	private String store_phone;
	private int minOrderPrice;
	private int deliveryTime;
	private int deliveryTip;
	
	/*
	 * Construcor
	 */
	public Store() {
		
	}
	
	// ȸ������ ������
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
	
	// ���� ȸ�� ���� ����
	public Store(int area_num, int code_num, String store_name, String store_phone, int minOrderPrice, int deliveryTime, int deliveryTip, int store_num) {
		this.area_num = area_num;
		this.code_num = code_num;
		this.store_name = store_name;
		this.store_phone = store_phone;
		this.minOrderPrice = minOrderPrice;
		this.deliveryTime = deliveryTime;
		this.deliveryTip = deliveryTip;
		this.store_num = store_num;
	}
	
	// ī�װ�(����)�� ���� ��� ��� ������
	public Store(int area_num, int code_num) {
		this.area_num = area_num;
		this.code_num = code_num;
	}

	
	/*
	 * Method
	 */
	@Override
	public String toString() {
		return "�����Ϲ�ȣ : " + store_num + "\t\t����� : " + store_name + "\t\t��ȭ��ȣ : " + store_phone 
				+ "\n�ּ��ֹ��ݾ� : " + minOrderPrice + "\t\t��޽ð� : " + deliveryTime + "\t\t����� : " + deliveryTip;
	}
	
	
	/*
	 * Getter & Setter
	 */
	public int getStore_num() {
		return store_num;
	}

	public void setStore_num(int store_num) {
		this.store_num = store_num;
	}

	public int getMember_num() {
		return member_num;
	}

	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}

	public int getArea_num() {
		return area_num;
	}

	public void setArea_num(int area_num) {
		this.area_num = area_num;
	}

	public int getCode_num() {
		return code_num;
	}

	public void setCode_num(int code_num) {
		this.code_num = code_num;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getStore_phone() {
		return store_phone;
	}

	public void setStore_phone(String store_phone) {
		this.store_phone = store_phone;
	}

	public int getMinOrderPrice() {
		return minOrderPrice;
	}

	public void setMinOrderPrice(int minOrderPrice) {
		this.minOrderPrice = minOrderPrice;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public int getDeliveryTip() {
		return deliveryTip;
	}

	public void setDeliveryTip(int deliveryTip) {
		this.deliveryTip = deliveryTip;
	}
	
	
}
