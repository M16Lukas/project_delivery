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
	
	// 카테고리(업종)별 매장 출력 기능
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
	
	
	// 검색된 매장 출력 기능
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
	
	
	// 검색된 매장 메뉴 출력 기능
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
	
	
	// 입력한 메뉴가 선택한 매장에 있는 메뉴인지 확인 
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
	
	
	// 장바구니에 담긴 메뉴 리스트 출력
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
	
	
	// 장바구니에 담긴 메뉴 주문 기능
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
	
	
	// 메뉴 추가
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
	
	// 메뉴 수정
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
	
	
	// 메뉴 삭제
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
	
	
	// 주문 내역 확인 기능 (매장회원용)
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
		
	
	// 주문 내역 확인 기능 (개인회원용)
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
