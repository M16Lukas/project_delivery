package dao;

import java.util.ArrayList;
import java.util.HashMap;

import vo.Menu;
import vo.OrderedMenu;
import vo.Store;

/*
 * ��ȸ�� �ֹ� ��ɰ���
 */
public interface OrderMenuMapper {

	// ī�װ�(����)�� ���� ��� ���
	public ArrayList<Store> printStoreByCategory(HashMap<String, Integer> map);
	
	// �˻��� ���� ��� ���
	public ArrayList<Store> searchStore(HashMap<String, Object> map);
	
	// ���õ� ���� �޴� ��� ���
	public ArrayList<HashMap<String, Object>> storeMenuPrintAll(int store_num);
	
	// �Է��� �޴��� ������ ���忡 �ִ� �޴����� Ȯ�� 
	public Integer checkMenuIsInTheSelectedStore(Menu menu);
	
	// ��ٱ��Ͽ� ��� �޴� ����Ʈ ���
	public Menu printShoppingBasket(int menu_num);
	
	// ��ٱ��Ͽ� ��� �޴� �ֹ� ���
	public int orderMenu(OrderedMenu ordered);
	
	// �޴� �߰�
	public int addMenu(Menu menu);
	
	// �޴� ����
	public int updateMenu(Menu menu);
	
	// �޴� ����
	public int deleteMenu(int menu_num);
}
