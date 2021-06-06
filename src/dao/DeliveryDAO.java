package dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import vo.Area;
import vo.Customer;
import vo.Member;
import vo.Store;
import vo.StoreCode;

public class DeliveryDAO {
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	
	/**
	 * ���� ���� ��� Group
	 * AccountMapper
	 */
	// ȸ������
	public int signUp(Member member) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = factory.openSession();
			AccountMapper mapper = session.getMapper(AccountMapper.class);
			cnt = mapper.signUp(member);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		
		return cnt;
	}

	
	// ID or PW�� ���̺� �����ϴ��� Ȯ��
	public Integer checkDuplicateAccount(HashMap<String, Object> map) {
		SqlSession session = null;
		Integer check = 0;
		try {
			session = factory.openSession();
			AccountMapper mapper = session.getMapper(AccountMapper.class);
			check = mapper.checkDuplicateAccount(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}		
		return check;
	}
	
	// ���� �з� ���̺� ���
	public ArrayList<Area> printArea(){
		SqlSession session = null;
		ArrayList<Area> list = null;
		try {
			session = factory.openSession();
			AccountMapper mapper = session.getMapper(AccountMapper.class);
			list = mapper.printArea();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		
		return list;
	}
		
	
	// ���� ���̺� ���
	public ArrayList<StoreCode> printStoreCode(){
		SqlSession session = null;
		ArrayList<StoreCode> list = null;
		try {
			session = factory.openSession();
			AccountMapper mapper = session.getMapper(AccountMapper.class);
			list = mapper.printStoreCode();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		
		return list;
	}
	
	
	// ��� �� ���� �Է�
	public int inputStore(Store store) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = factory.openSession();
			AccountMapper mapper = session.getMapper(AccountMapper.class);
			cnt = mapper.inputStore(store);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		
		return cnt;
	}
	
	
	// ���� �� ���� �Է�
	public int inputCustomer(Customer customer) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = factory.openSession();
			AccountMapper mapper = session.getMapper(AccountMapper.class);
			cnt = mapper.inputCustomer(customer);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return cnt;
	}
	
	
	// �α���
	public Member login(Member member) {
		SqlSession session = null;
		Member user = null;
		try {
			session = factory.openSession();
			AccountMapper mapper = session.getMapper(AccountMapper.class);
			user = mapper.login(member);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return user;
	}

//	// ��й�ȣ ����
//	public int updatePassword(Member member) {
//			
//	}
		
	// ȸ��Ż��
	public int memberWithdrawal(int member_num) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = factory.openSession();
			AccountMapper mapper = session.getMapper(AccountMapper.class);
			cnt = mapper.memberWithdrawal(member_num);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return cnt;
	}
}
