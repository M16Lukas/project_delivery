package mgr;

import java.util.ArrayList;
import java.util.HashMap;

import dao.OrderMenuDAO;
import vo.Menu;
import vo.OrderedMenu;
import vo.Store;

public class OrderMenuManager {

	private OrderMenuDAO dao = new OrderMenuDAO();
	
	// 카테고리(업종)별 매장 출력 기능
	//
	// @param sort : 해당 업종의 매장들 정렬 기준
	// 1 : 기본순
	// 2 : 배달빠른 순
	// 3 : 배달팁 낮은 순
	//
	// @param minPrice : 최소주문금액(조건 목록 출력 기능)
	// 0 : 설정안함
	// 1 : 5,000원 이하
	// 2 : 10,000원 이하
	// 3 : 12,000원 이하
	// 4 : 15,000원 이하
	// 5 : 20,000원 이하
	public ArrayList<Store> printStoreByCategory(int area_num, int code_num, int sort, int minPrice) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("area_num", area_num);
		map.put("code_num", code_num);
		map.put("sortType", sort);
		map.put("minOrderPrice", minPrice);
		
		return dao.printStoreByCategory(map);
	}
	
	
	// 검색된 매장 출력 기능
	public ArrayList<Store> searchStore(String name, int areaNum){
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("name", name);
		map.put("area_num", areaNum);
		
		return dao.searchStore(map);
	}
	
	
	// 선택된 매장 메뉴 출력 기능
	public ArrayList<HashMap<String, Object>> storeMenuPrintAll(int store_num){
		return dao.storeMenuPrintAll(store_num);
	}
	
	
	// 입력한 메뉴가 선택한 매장에 있는 메뉴인지 확인 
	public boolean checkMenuIsInTheSelectedStore(Menu menu) {
		return dao.checkMenuIsInTheSelectedStore(menu) != null ? true : false; 
	}
	
	
	// 장바구니에 담긴 메뉴 리스트 출력
	public Menu printShoppingBasket(int menu_num) {
		return dao.printShoppingBasket(menu_num);
	}
	
	
	// 장바구니에 담긴 메뉴 주문 기능
	public boolean orderMenu(OrderedMenu ordered) {
		return dao.orderMenu(ordered) > 0 ? true : false;
	}
	
	
	// 메뉴 추가
	public boolean addMenu(Menu menu) {
		return dao.addMenu(menu) > 0 ? true : false;
	}
	
	
	// 메뉴 수정
	public boolean updateMenu(Menu menu) {
		return dao.updateMenu(menu) > 0 ? true : false;
	}
	
	// 메뉴 삭제
	public boolean deleteMenu(int menu_num) {
		return dao.deleteMenu(menu_num) > 0 ? true : false;
	}
}
