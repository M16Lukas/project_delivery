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
	public Member checkDuplicateAccount(String member_id);
	
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
	// �α���
	public Member login(Member member);
	
	// �α����� ���� ȸ�� �� ���� ��������
	public Store getStoreInfo(int member_num);
	
	// �α����� ���� ȸ�� �� ���� ��������
	public Customer getCustomerInfo(int member_num);
	
	/*
	 * ���� ���� ����
	 */
	// ���� ȸ�� ���� ����
	public int updateCustomerInfo(Customer customer);

	// ���� ȸ�� ���� ����
	public int updateStoreInfo(Store store);
	
	// ��й�ȣ ����
	public int updatePassword(Member member);
	
	// ȸ��Ż��
	public int memberWithdrawal(int member_num);
}
