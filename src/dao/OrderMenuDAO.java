package dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import vo.Menu;
import vo.OrderedMenu;
import vo.Store;

public class OrderMenuDAO {
	SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	
	// ī�װ�(����)�� ���� ��� ���
	public ArrayList<Store> printStoreByCategory(HashMap<String, Integer> map){
		SqlSession session = null;
		ArrayList<Store> list = null;
		try {
			session = factory.openSession();
			OrderMenuMapper mapper = session.getMapper(OrderMenuMapper.class);
			list = mapper.printStoreByCategory(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		
		return list;
	}
	
	
	// �˻��� ���� ��� ���
	public ArrayList<Store> searchStore(HashMap<String, Object> map){
		SqlSession session = null;
		ArrayList<Store> list = null;
		try {
			session = factory.openSession();
			OrderMenuMapper mapper = session.getMapper(OrderMenuMapper.class);
			list = mapper.searchStore(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		
		return list;
	}
	
	
	// �˻��� ���� �޴� ��� ���
	public ArrayList<HashMap<String, Object>> storeMenuPrintAll(int store_num){
		SqlSession session = null;
		ArrayList<HashMap<String, Object>> list = null;
		try {
			session = factory.openSession();
			OrderMenuMapper mapper = session.getMapper(OrderMenuMapper.class);
			list = mapper.storeMenuPrintAll(store_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		return list;
	}
	
	
	// �Է��� �޴��� ������ ���忡 �ִ� �޴����� Ȯ�� 
	public Integer checkMenuIsInTheSelectedStore(Menu menu) {
		SqlSession session = null;
		Integer cnt = 0;
		try {
			session = factory.openSession();
			OrderMenuMapper mapper = session.getMapper(OrderMenuMapper.class);
			cnt = mapper.checkMenuIsInTheSelectedStore(menu);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		
		return cnt;
	}
	
	
	// ��ٱ��Ͽ� ��� �޴� ����Ʈ ���
	public Menu printShoppingBasket(int menu_num){
		SqlSession session = null;
		Menu list = null;
		try {
			session = factory.openSession();
			OrderMenuMapper mapper = session.getMapper(OrderMenuMapper.class);
			list = mapper.printShoppingBasket(menu_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		
		return list;
	}
	
	
	// ��ٱ��Ͽ� ��� �޴� �ֹ� ���
	public int orderMenu(OrderedMenu ordered) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = factory.openSession();
			OrderMenuMapper mapper = session.getMapper(OrderMenuMapper.class);
			cnt = mapper.orderMenu(ordered);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		
		return cnt;
	}
	
	
	// �޴� �߰�
	public int addMenu(Menu menu) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = factory.openSession();
			OrderMenuMapper mapper = session.getMapper(OrderMenuMapper.class);
			cnt = mapper.addMenu(menu);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		
		return cnt;
	}
	
	// �޴� ����
	public int updateMenu(Menu menu) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = factory.openSession();
			OrderMenuMapper mapper = session.getMapper(OrderMenuMapper.class);
			cnt = mapper.updateMenu(menu);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		
		return cnt;	
	}
	
	
	// �޴� ����
	public int deleteMenu(int menu_num) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = factory.openSession();
			OrderMenuMapper mapper = session.getMapper(OrderMenuMapper.class);
			cnt = mapper.deleteMenu(menu_num);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		
		return cnt;	
	}
	
	
	// �ֹ� ���� Ȯ�� ��� (����ȸ����)
	public ArrayList<HashMap<String, Object>> orderedMenuListForStore(int store_num){
		SqlSession session = null;
		ArrayList<HashMap<String, Object>> list = null;
		try {
			session = factory.openSession();
			OrderMenuMapper mapper = session.getMapper(OrderMenuMapper.class);
			list = mapper.orderedMenuListForStore(store_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		return list;
	}
		
	
	// �ֹ� ���� Ȯ�� ��� (����ȸ����)
	public ArrayList<HashMap<String, Object>> orderedMenuListForCustomer(int customer_num){
		SqlSession session = null;
		ArrayList<HashMap<String, Object>> list = null;
		try {
			session = factory.openSession();
			OrderMenuMapper mapper = session.getMapper(OrderMenuMapper.class);
			list = mapper.orderedMenuListForCustomer(customer_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		return list;
	}
}
