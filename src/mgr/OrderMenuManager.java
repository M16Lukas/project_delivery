package mgr;

import java.util.ArrayList;
import java.util.HashMap;

import dao.OrderMenuDAO;
import vo.Menu;
import vo.OrderedMenu;
import vo.Store;

public class OrderMenuManager {

	private OrderMenuDAO dao = new OrderMenuDAO();
	
	// ī�װ�(����)�� ���� ��� ���
	//
	// @param sort : �ش� ������ ����� ���� ����
	// 1 : �⺻��
	// 2 : ��޺��� ��
	// 3 : ����� ���� ��
	//
	// @param minPrice : �ּ��ֹ��ݾ�(���� ��� ��� ���)
	// 0 : ��������
	// 1 : 5,000�� ����
	// 2 : 10,000�� ����
	// 3 : 12,000�� ����
	// 4 : 15,000�� ����
	// 5 : 20,000�� ����
	public ArrayList<Store> printStoreByCategory(int area_num, int code_num, int sort, int minPrice) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("area_num", area_num);
		map.put("code_num", code_num);
		map.put("sortType", sort);
		map.put("minOrderPrice", minPrice);
		
		return dao.printStoreByCategory(map);
	}
	
	
	// �˻��� ���� ��� ���
	public ArrayList<Store> searchStore(String name, int areaNum){
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("name", name);
		map.put("area_num", areaNum);
		
		return dao.searchStore(map);
	}
	
	
	// ���õ� ���� �޴� ��� ���
	public ArrayList<HashMap<String, Object>> storeMenuPrintAll(int store_num){
		return dao.storeMenuPrintAll(store_num);
	}
	
	
	// �Է��� �޴��� ������ ���忡 �ִ� �޴����� Ȯ�� 
	public boolean checkMenuIsInTheSelectedStore(Menu menu) {
		return dao.checkMenuIsInTheSelectedStore(menu) != null ? true : false; 
	}
	
	
	// ��ٱ��Ͽ� ��� �޴� ����Ʈ ���
	public Menu printShoppingBasket(int menu_num) {
		return dao.printShoppingBasket(menu_num);
	}
	
	
	// ��ٱ��Ͽ� ��� �޴� �ֹ� ���
	public boolean orderMenu(OrderedMenu ordered) {
		return dao.orderMenu(ordered) > 0 ? true : false;
	}
	
	
	// �޴� �߰�
	public boolean addMenu(Menu menu) {
		return dao.addMenu(menu) > 0 ? true : false;
	}
	
	
	// �޴� ����
	public boolean updateMenu(Menu menu) {
		return dao.updateMenu(menu) > 0 ? true : false;
	}
	
	// �޴� ����
	public boolean deleteMenu(int menu_num) {
		return dao.deleteMenu(menu_num) > 0 ? true : false;
	}
}
