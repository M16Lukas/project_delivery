package dao;

import java.util.ArrayList;
import java.util.HashMap;

import vo.Menu;
import vo.OrderedMenu;
import vo.Store;

/*
 * 고객회원 주문 기능관련
 */
public interface OrderMenuMapper {

	// 카테고리(업종)별 매장 출력 기능
	public ArrayList<Store> printStoreByCategory(HashMap<String, Integer> map);
	
	// 검색된 매장 출력 기능
	public ArrayList<Store> searchStore(HashMap<String, Object> map);
	
	// 선택된 매장 메뉴 출력 기능
	public ArrayList<HashMap<String, Object>> storeMenuPrintAll(int store_num);
	
	// 입력한 메뉴가 선택한 매장에 있는 메뉴인지 확인 
	public Integer checkMenuIsInTheSelectedStore(Menu menu);
	
	// 장바구니에 담긴 메뉴 리스트 출력
	public Menu printShoppingBasket(int menu_num);
	
	// 장바구니에 담긴 메뉴 주문 기능
	public int orderMenu(OrderedMenu ordered);
	
	// 메뉴 추가
	public int addMenu(Menu menu);
	
	// 메뉴 수정
	public int updateMenu(Menu menu);
	
	// 메뉴 삭제
	public int deleteMenu(int menu_num);
}
