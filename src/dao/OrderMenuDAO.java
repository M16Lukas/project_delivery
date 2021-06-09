package dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import vo.Menu;
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
}
