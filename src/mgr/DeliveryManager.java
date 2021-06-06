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
	 * ���� ���� ��� Group
	 * AccountMapper
	 */
	
	// ȸ������
	public boolean signUp(Member member) {
		return dao.signUp(member) > 0 ? true : false; 
	}

	
	// ID or PW�� ���̺� �����ϴ��� Ȯ��
	// type : 1 -> ID, 2 -> PW
	// true : ID or PW �� �ߺ�, false : �ߺ����� ����
	public Integer checkDuplicateAccount(int type, String word) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("type", type);
		map.put("word", word);
		Integer num = dao.checkDuplicateAccount(map);
		return num != null ? num : 0;
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
		
	// �α���
	public Member login(Member member) {
		return dao.login(member);
	}
//		
//	// ��й�ȣ ����
//	public boolean updatePassword(Member member) {
//			
//	}
		
	// ȸ��Ż��
	public boolean memberWithdrawal(int member_num) {
		return dao.memberWithdrawal(member_num) > 0 ? true : false;
	}
}
