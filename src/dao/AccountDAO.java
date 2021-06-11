package dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import vo.Area;
import vo.Customer;
import vo.Member;
import vo.Store;
import vo.StoreCode;

public class AccountDAO {
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
	public Member checkDuplicateAccount(String member_id) {
		SqlSession session = null;
		Member user = null;
		try {
			session = factory.openSession();
			AccountMapper mapper = session.getMapper(AccountMapper.class);
			user = mapper.checkDuplicateAccount(member_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		return user;
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
	
	/*
	 * �α��� 
	 */
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
	
	// �α����� ���� ȸ�� �� ���� ��������
	public Store getStoreInfo(int member_num) {
		SqlSession session = null;
		Store user = null;
		try {
			session = factory.openSession();
			AccountMapper mapper = session.getMapper(AccountMapper.class);
			user = mapper.getStoreInfo(member_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return user;
	}
		
	// �α����� ���� ȸ�� �� ���� ��������
	public Customer getCustomerInfo(int member_num) {
		SqlSession session = null;
		Customer user = null;
		try {
			session = factory.openSession();
			AccountMapper mapper = session.getMapper(AccountMapper.class);
			user = mapper.getCustomerInfo(member_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return user;
	}

	// ��й�ȣ ����
	public int updatePassword(Member member) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = factory.openSession();
			AccountMapper mapper = session.getMapper(AccountMapper.class);
			cnt = mapper.updatePassword(member);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return cnt;
	}
		
	
	// ���� ȸ�� ���� ����
	public int updateCustomerInfo(Customer customer) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = factory.openSession();
			AccountMapper mapper = session.getMapper(AccountMapper.class);
			cnt = mapper.updateCustomerInfo(customer);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return cnt;
	}
		
	// ���� ȸ�� ���� ����
	public int updateStoreInfo(Store store) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = factory.openSession();
			AccountMapper mapper = session.getMapper(AccountMapper.class);
			cnt = mapper.updateStoreInfo(store);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return cnt;
	}
	
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
