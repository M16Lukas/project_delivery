package mgr;

import java.util.ArrayList;

import dao.AccountDAO;
import vo.Area;
import vo.Customer;
import vo.Member;
import vo.Store;
import vo.StoreCode;

public class AccountManager {
	AccountDAO dao = new AccountDAO();
	
	/**
	 * 계정 관련 기능 Group
	 * AccountMapper
	 */
	
	// 회원가입
	public boolean signUp(Member member) {
		return dao.signUp(member) > 0 ? true : false; 
	}

	// ID or PW가 테이블에 존재하는지 확인
	public Member checkDuplicateAccount(String member_id) {
		return dao.checkDuplicateAccount(member_id);
	}
	
	// 업종 분류 테이블 출력
	public ArrayList<Area> printArea(){
		return dao.printArea();
	}
		
	// 지역 테이블 출력
	public ArrayList<StoreCode> printStoreCode(){
		return dao.printStoreCode();
	}
	
	// 기업 상세 정보 입력
	public boolean inputStore(Store store) {
		return dao.inputStore(store) > 0 ? true : false;
	}
	
	// 개인 상세 정보 입력
	public boolean inputCustomer(Customer customer) {
		return dao.inputCustomer(customer) > 0 ? true : false; 
	}
		
	/*
	 * 로그인
	 */
	// 로그인
	public Member login(Member member) {
		return dao.login(member);
	}
	
	// 로그인한 매장 회원 상세 정보 가져오기
	public Store getStoreInfo(int member_num) {
		return dao.getStoreInfo(member_num);
	}
		
	// 로그인한 개인 회원 상세 정보 가져오기
	public Customer getCustomerInfo(int member_num) {
		return dao.getCustomerInfo(member_num);
		
	}
		
	// 비밀번호 변경
	public boolean updatePassword(Member member) {
		return dao.updatePassword(member) > 0 ? true : false;
	}
	
	// 개인 회원 정보 수정
	public boolean updateCustomerInfo(Customer customer) {
		return dao.updateCustomerInfo(customer) > 0 ? true : false;
	}
		
	// 매장 회원 정보 수정
	public boolean updateStoreInfo(Store store) {
		return dao.updateStoreInfo(store) > 0 ? true : false;
	}
		
	// 회원탈퇴
	public boolean memberWithdrawal(int member_num) {
		return dao.memberWithdrawal(member_num) > 0 ? true : false;
	}
}
