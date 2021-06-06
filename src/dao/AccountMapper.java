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
	 * ȸ�� ����
	 * */
	// ID ,PW �Է� 
	public int signUp(Member member);
	
	// ID or PW�� ���̺� �����ϴ��� Ȯ��
	public Integer checkDuplicateAccount(HashMap<String, Object> map);
	
	// ���� �з� ���̺� ���
	public ArrayList<Area> printArea();
	
	// ���� ���̺� ���
	public ArrayList<StoreCode> printStoreCode(); 
	
	// ��� ȸ�� ���� �Է�
	public int inputStore(Store store);
	
	// ���� ȸ�� ���� �Է�
	public int inputCustomer(Customer customer);
	
	
	/*
	 * �α���
	 */
	public Member login(Member member);
	
	
	/*
	 * ���� ���� ����
	 */
	// ��й�ȣ ����
	public int updatePassword(Member member);
	
	// ȸ��Ż��
	public int memberWithdrawal(int member_num);
}
