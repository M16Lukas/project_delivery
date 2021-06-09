package mgr;

import java.util.ArrayList;
import java.util.HashMap;

import dao.OrderMenuDAO;
import vo.Menu;
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
	
	
	// 메뉴 추가
	public boolean addMenu(Menu menu) {
		return dao.addMenu(menu) > 0 ? true : false;
	}
}
