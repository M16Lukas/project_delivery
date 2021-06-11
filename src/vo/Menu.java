package vo;

public class Menu {
	/*
	 * field
	 */
	private int menu_num;
	private int store_num;
	private String menu_name;
	private String menu_intro;
	private int menu_price;
	private int menu_soldout;
	
	/*
	 * Constructor
	 */
	
	public Menu() {}
	
	// 메뉴 등록
	public Menu(int store_num, String menu_name, String menu_intro, int menu_price) {
		this.store_num = store_num;
		this.menu_name = menu_name;
		this.menu_intro = menu_intro;
		this.menu_price = menu_price;
	}
	
	// 메뉴 수정
	public Menu(int menu_num, String menu_name, String menu_intro, int menu_price, int menu_soldout) {
		this.menu_num = menu_num;
		this.menu_name = menu_name;
		this.menu_intro = menu_intro;
		this.menu_price = menu_price;
		this.menu_soldout = menu_soldout;
	}
	
	// 선택한 매장에 있는 메뉴인지 확인
	public Menu(int menu_num, int store_num) {
		this.menu_num = menu_num;
		this.store_num = store_num;
	}
	
	@Override
	public String toString() {
		return "메뉴명 : " + menu_name + "\t가격 : " + menu_price + "\n메뉴 소개 : " + menu_intro;
	}
	
	/*
	 * Getter & Setter
	 */
	public int getMenu_num() {
		return menu_num;
	}

	public void setMenu_num(int menu_num) {
		this.menu_num = menu_num;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getMenu_intro() {
		return menu_intro;
	}

	public void setMenu_intro(String menu_intro) {
		this.menu_intro = menu_intro;
	}

	public int getMenu_price() {
		return menu_price;
	}

	public void setMenu_price(int menu_price) {
		this.menu_price = menu_price;
	}

	public int getMenu_soldout() {
		return menu_soldout;
	}

	public void setMenu_soldout(int menu_soldout) {
		this.menu_soldout = menu_soldout;
	}
	
	
}
