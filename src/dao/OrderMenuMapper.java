package dao;

import java.util.ArrayList;
import java.util.HashMap;

import vo.Menu;
import vo.Store;

/*
 * ��ȸ�� �ֹ� ��ɰ���
 */
public interface OrderMenuMapper {

	// ī�װ�(����)�� ���� ��� ���
	public ArrayList<Store> printStoreByCategory(HashMap<String, Integer> map);
	
	
	// �˻��� ���� ��� ���
	public ArrayList<Store> searchStore(HashMap<String, Object> map);
	
	
	// �޴� �߰�
	public int addMenu(Menu menu);
}
