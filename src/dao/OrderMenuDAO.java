package dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import vo.Menu;
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
}
