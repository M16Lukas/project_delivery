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
	 * ���� ���� ��� Group
	 * AccountMapper
	 */
	
	// ȸ������
	public boolean signUp(Member member) {
		return dao.signUp(member) > 0 ? true : false; 
	}

	// ID or PW�� ���̺� �����ϴ��� Ȯ��
	public Member checkDuplicateAccount(String member_id) {
		return dao.checkDuplicateAccount(member_id);
	}
	
	// ���� �з� ���̺� ���
	public ArrayList<Area> printArea(){
		return dao.printArea();
	}
		
	// ���� ���̺� ���
	public ArrayList<StoreCode> printStoreCode(){
		return dao.printStoreCode();
	}
	
	// ��� �� ���� �Է�
	public boolean inputStore(Store store) {
		return dao.inputStore(store) > 0 ? true : false;
	}
	
	// ���� �� ���� �Է�
	public boolean inputCustomer(Customer customer) {
		return dao.inputCustomer(customer) > 0 ? true : false; 
	}
		
	/*
	 * �α���
	 */
	// �α���
	public Member login(Member member) {
		return dao.login(member);
	}
	
	// �α����� ���� ȸ�� �� ���� ��������
	public Store getStoreInfo(int member_num) {
		return dao.getStoreInfo(member_num);
	}
		
	// �α����� ���� ȸ�� �� ���� ��������
	public Customer getCustomerInfo(int member_num) {
		return dao.getCustomerInfo(member_num);
		
	}
		
	// ��й�ȣ ����
	public boolean updatePassword(Member member) {
		return dao.updatePassword(member) > 0 ? true : false;
	}
	
	// ���� ȸ�� ���� ����
	public boolean updateCustomerInfo(Customer customer) {
		return dao.updateCustomerInfo(customer) > 0 ? true : false;
	}
		
	// ���� ȸ�� ���� ����
	public boolean updateStoreInfo(Store store) {
		return dao.updateStoreInfo(store) > 0 ? true : false;
	}
		
	// ȸ��Ż��
	public boolean memberWithdrawal(int member_num) {
		return dao.memberWithdrawal(member_num) > 0 ? true : false;
	}
}
