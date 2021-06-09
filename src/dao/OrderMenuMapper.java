package dao;

import java.util.ArrayList;
import java.util.HashMap;

import vo.Menu;
import vo.Store;

/*
 * 고객회원 주문 기능관련
 */
public interface OrderMenuMapper {

	// 카테고리(업종)별 매장 출력 기능
	public ArrayList<Store> printStoreByCategory(HashMap<String, Integer> map);
	
	
	// 검색된 매장 출력 기능
	public ArrayList<Store> searchStore(HashMap<String, Object> map);
	
	
	// 메뉴 추가
	public int addMenu(Menu menu);
}
