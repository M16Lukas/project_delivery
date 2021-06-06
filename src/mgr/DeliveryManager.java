package mgr;

import java.util.ArrayList;
import java.util.HashMap;

import dao.DeliveryDAO;
import vo.Area;
import vo.Customer;
import vo.Member;
import vo.Store;
import vo.StoreCode;

public class DeliveryManager {
	DeliveryDAO dao = new DeliveryDAO();
	
	/**
	 * 계정 관련 기능 Group
	 * AccountMapper
	 */
	
	// 회원가입
	public boolean signUp(Member member) {
		return dao.signUp(member) > 0 ? true : false; 
	}

	
	// ID or PW가 테이블에 존재하는지 확인
	// type : 1 -> ID, 2 -> PW
	// true : ID or PW 가 중복, false : 중복되지 않음
	public Integer checkDuplicateAccount(int type, String word) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("type", type);
		map.put("word", word);
		Integer num = dao.checkDuplicateAccount(map);
		return num != null ? num : 0;
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
		
	// 로그인
	public Member login(Member member) {
		return dao.login(member);
	}
//		
//	// 비밀번호 변경
//	public boolean updatePassword(Member member) {
//			
//	}
		
	// 회원탈퇴
	public boolean memberWithdrawal(int member_num) {
		return dao.memberWithdrawal(member_num) > 0 ? true : false;
	}
}
