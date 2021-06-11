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
	 * 계정 관련 기능 Group
	 * AccountMapper
	 */
	// 회원가입
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

	
	// ID or PW가 테이블에 존재하는지 확인
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
	
	// 업종 분류 테이블 출력
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
		
	
	// 지역 테이블 출력
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
	
	
	// 기업 상세 정보 입력
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
	
	
	// 개인 상세 정보 입력
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
	 * 로그인 
	 */
	// 로그인
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
	
	// 로그인한 매장 회원 상세 정보 가져오기
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
		
	// 로그인한 개인 회원 상세 정보 가져오기
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

	// 비밀번호 변경
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
		
	
	// 개인 회원 정보 수정
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
		
	// 매장 회원 정보 수정
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
	
	// 회원탈퇴
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
