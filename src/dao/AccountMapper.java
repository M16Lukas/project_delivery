package dao;

import java.util.ArrayList;
import java.util.HashMap;

import vo.Area;
import vo.Customer;
import vo.Member;
import vo.Store;
import vo.StoreCode;

public interface AccountMapper {
	/*
	 * 회원 가입
	 * */
	// ID ,PW 입력 
	public int signUp(Member member);
	
	// ID or PW가 테이블에 존재하는지 확인
	public Integer checkDuplicateAccount(HashMap<String, Object> map);
	
	// 업종 분류 테이블 출력
	public ArrayList<Area> printArea();
	
	// 지역 테이블 출력
	public ArrayList<StoreCode> printStoreCode(); 
	
	// 기업 회원 정보 입력
	public int inputStore(Store store);
	
	// 개인 회원 정보 입력
	public int inputCustomer(Customer customer);
	
	
	/*
	 * 로그인
	 */
	public Member login(Member member);
	
	
	/*
	 * 계정 상태 변경
	 */
	// 비밀번호 변경
	public int updatePassword(Member member);
	
	// 회원탈퇴
	public int memberWithdrawal(int member_num);
}
