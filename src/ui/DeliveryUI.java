package ui;


import java.util.Scanner;

import mgr.DeliveryManager;
import vo.Area;
import vo.Customer;
import vo.Member;
import vo.Store;
import vo.StoreCode;

public class DeliveryUI{
	// final field
	private final int STORE = 1;
	private final int CUSTOMER = 2;
	
	// Object
	private	Scanner scanner = new Scanner(System.in);
	private DeliveryManager mgr = new DeliveryManager();
	
	private Member loginUser = null;	// �α����� ���� ���� ����� ��ü
	
	// field
	private boolean isRun = true;
	
	// Constructor
	public DeliveryUI(){
		while (isRun) {
			int key = BeginPage();
			switch (key) {
			case 0:	// ����
				ProgramExit();
				break;
			case 1: // �α���
				loginPage();
				break;
			case 2:	// ȸ������
				signUpPage();
				break;
			default:
				break;
			}
		}
	}
	
	/*******************
	 * 
	 * Page Group
	 * 
	 ******************/
	// ���� ������
	public int BeginPage(){
		System.out.println("===== [ ��� ���α׷�TEST ] =====\n");
		System.out.println("1. �α���\n");
		System.out.println("2. ȸ������\n");
		System.out.println("0. ����\n");
		System.out.println("========================\n");
		System.out.print("> ");
		return scanner.nextInt();
	}
	
	
	// �α��� ������
	public void loginPage() {
		System.out.println("----- [ �α��� ] -----");
		
		loginUser = inputIdPw();
		switch (loginUser.getMember_sort()) {
		case STORE:
			storeMainPage();
			break;
		case CUSTOMER:
			customerMainPage();
			break;
		default:
			break;
		}
	}
	
	
	// ȸ������ ������
	public void signUpPage(){
		scanner.nextLine();
		System.out.println("----- [ ȸ������ ] -----");
		System.out.println("1. ���ȸ��");
		System.out.println("2. ����ȸ��");
		System.out.println("---------------------");
		System.out.print("> ");
		int sort = scanner.nextInt();
		switch (sort) {
		case STORE:
			inputStoreInfo();
			break;
		case CUSTOMER:
			inputCustomerInfo();
			break;
		default:
			break;
		}
	}
	
	
	// ��� ȸ�� ���� ������
	public void storeMainPage() {
		System.out.println("~~~~~ [ SCIT ��� ] ~~~~~");
		System.out.println("1. �ֹ� �̷� Ȯ��");
		System.out.println("2. �޴� ����");
		System.out.println("3. ������������");
		System.out.print("> ");
		int menu = scanner.nextInt();
		switch (menu) {
		case 1:	// �ֹ� �̷� Ȯ��
			break;
		case 2:	// �޴� ����
			break;
		case 3:	// ������������
			break;
		default:
			break;
		}
	}
	
	
	// ���� ȸ�� ���� ������
	public void customerMainPage() {
		System.out.println("~~~~~ [ SCIT ��� ] ~~~~~");
		System.out.println("1. ���� �˻�");
		System.out.println("2. ī�װ�");
		System.out.println("3. �ֹ�����");
		System.out.println("4. my����");
		System.out.print("> ");
		int menu = scanner.nextInt();
		switch (menu) {
		case 1:	// ���� �˻�
			break;
		case 2:	// ī�װ�
			break;
		case 3:	// �ֹ�����
			break;
		case 4:	// my����
			userInfoPage();
			break;
		default:
			break;
		}
	}
	
	
	/*
	 * �� ���� ������
	 */
	public void userInfoPage() {
		System.out.println("+++++ [ MY���� ] +++++");
		System.out.println("�ȳ��ϼ��� ! " + loginUser.getMember_id() + " ��");
		System.out.println("--------------------");
		System.out.println("1. ���� ���� ����");
		System.out.println("2. �α׾ƿ�");
		System.out.println("3. ȸ��Ż��");
		System.out.println("0. �ڷΰ���");
		System.out.print("> ");
		int menu = scanner.nextInt();
		switch (menu) {
		case 1:	// ���� ���� ����
			break;
		case 2:	// �α׾ƿ�
			logOut();
			break;
		case 3:	// ȸ��Ż��
			withdrawalPage();
			break;
		case 0:	// �ڷΰ���
			customerMainPage();
			break;
		default:
			break;
		}
	}
	
	
	/*
	 * ȸ��Ż�� ������
	 */
	public void withdrawalPage() {
		scanner.nextLine();
		System.out.println("************ ȸ��Ż�� *************");
		System.out.println("ȸ��Ż�� ���Ͻô� ��� ��й�ȣ�� �Է��� �ּ���");
		System.out.print("> ");
		String pw = scanner.nextLine();
		if (loginUser.getMember_num() == mgr.checkDuplicateAccount(2, pw)) {
			System.out.println("-------------- [����] -------------");
			System.out.println("| ȸ��Ż�� �� ����� �������� ���� �����˴ϴ�     |");
			System.out.println("| Ż�� ���Ͻø� Y�� �Է����ּ���  [Y / N] |");
			System.out.println("----------------------------------");
			System.out.print("> ");
			String yesOrNo = scanner.nextLine();
			if (yesOrNo.toUpperCase().equals("Y") || yesOrNo.toUpperCase().equals("YES")) {
				withdrawal();// ȸ��Ż�� ���
			} else {
				System.out.println("[SYSTEM] Ż�� ����մϴ�");
				userInfoPage();
			}
		} else {
			System.out.println("[SYSTEM] ��й�ȣ�� ��ġ���� �ʽ��ϴ�");
			userInfoPage();
		}
	} 
	
	
	/*******************
	 * 
	 * Method Group
	 * 
	 ******************/
	
	/*
	 * �α׾ƿ�
	 */
	public void logOut() {
		loginUser = null;
		loginPage();
	}
	
	
	/*
	 * ȸ�� Ż��
	 */
	public void withdrawal() {
		if( mgr.memberWithdrawal(loginUser.getMember_num()) ) {
			System.out.println("[SYSTEM] �׵��� ���� ���α׷��� ����� �ּż� �����մϴ�.");
			loginUser = null;
		} else {
			System.out.println("[SYSTEM] ������ �߻��߽��ϴ�");
		}
	} 
	
	
	/*
	 * ���α׷� ����
	 */
	public void ProgramExit() {
		System.out.println("[SYSTEM] ���α׷��� �����մϴ�.");
		isRun = false;
		System.exit(0);
	}
	
	
	/*
	 * ���� ���̺� ���
	 */
	public int printArea() {
		for(Area area : mgr.printArea()) {
			System.out.println(area);
		}
		System.out.print("> ");
		return scanner.nextInt();
	}

	
	/*
	 * ���� ���� �ڵ� ���
	 */
	public int printStoreCode() {
		for(StoreCode code : mgr.printStoreCode()) {
			System.out.println(code);
		}
		System.out.print("> ");
		return scanner.nextInt();
	}
	
	
	/*
	 * ID, PW �Է� (�α��ο�)
	 */
	public Member inputIdPw() {
		scanner.nextLine();
		boolean chk = true;
		Member user = null;
		
		while(chk) {
			System.out.print("ID : ");
			String id = scanner.nextLine();
			
			System.out.print("PW : ");
			String pw = scanner.nextLine();	

			int chkId = mgr.checkDuplicateAccount(1, id);
			if (chkId == 0) {
				System.out.println("[SYSTEM] �������� ���� ���̵� �Դϴ�");
				continue;
			}
			
			int chkPw = mgr.checkDuplicateAccount(2, pw); 
			if (chkPw != chkId) {
				System.out.println("[SYSTEM] ��й�ȣ�� �߸� �Է��ϼ̽��ϴ�");
				continue;
			} else {
				Member tmp = new Member();
				tmp.setMember_id(id);
				tmp.setMember_password(pw);
				
				user = mgr.login(tmp);
				if (user != null) {
					chk = false;
				}
			}
		}
		
		return user;
	}
	
	
	/*
	 * ID, PW �Է� (ȸ�����Կ�)
	 */
	public Member inputIdPw(int sort) {
		scanner.nextLine();
		String signUpID = null;
		
		// ID �ߺ� üũ
		boolean chk = true;
		while (chk) {
			System.out.print("ID : ");
			signUpID = scanner.nextLine();
	
			if (mgr.checkDuplicateAccount(1, signUpID) == 0) {
				chk = false;
			} else {
				System.out.println("[SYSTEM] �̹� �����ϴ� ID �Դϴ�");
			}
		}
		
		System.out.print("PW : ");
		String signUpPW = scanner.nextLine();
		return new Member(signUpID, signUpPW, sort);	
	}
	
	
	/*
	 * ��� ȸ�� ���� �Է�
	 */
	public void inputStoreInfo() {		
		// ID & PW �Է�
		Member member = inputIdPw(STORE);
		
		// ���� �Է�
		int areaNum = printArea();
		
		// ���� ���� ��� & �Է�
		int codeNum = printStoreCode();
		scanner.nextLine();
		
		// �� ���� �Է�
		// ���� �̸�
		System.out.print("����� : ");
		String storeName = scanner.nextLine();
		
		// ���� ��ȭ��ȣ
		System.out.print("���� ��ȭ��ȣ : ");
		String storePhone = scanner.nextLine();
		
		// �ּ��ֹ��ݾ�
		System.out.print("�ּ��ֹ��ݾ� : ");
		int minOrderPrice = scanner.nextInt();
		
		// ��޽ð�
		System.out.print("��޽ð� : ");
		int deliveryTime = scanner.nextInt();
		
		// �����
		System.out.print("����� : ");
		int deliveryTip = scanner.nextInt();
		
		if (mgr.signUp(member)) {
			int memberNum = mgr.checkDuplicateAccount(1, member.getMember_id());
			Store store = new Store(memberNum, areaNum, codeNum, storeName, storePhone, minOrderPrice, deliveryTime, deliveryTip);
			
			if (mgr.inputStore(store)) {
				System.out.println("[SYSTEM] ȸ�� ���� �Ϸ�");
				BeginPage();	// ȸ�� ���� �Ϸ� �� �α��� �������� ���ư���
			} else {
				System.out.println("[SYSTEM] ���� �߻�");
			}
		} else {
			System.out.println("[SYSTEM] ���� �߻�");
		}
	}
	
	
	/**
	 * ���� ȸ�� ���� �Է�
	 */
	public void inputCustomerInfo() {
		// ID & PW �Է�
		Member member = inputIdPw(CUSTOMER);
		
		// �� ���� �Է�
		System.out.print("�̸� : ");
		String name = scanner.nextLine();
		
		System.out.print("��ȭ��ȣ : ");
		String phone = scanner.nextLine();
		
		// ���� �Է�
		int areaNum = printArea();
		scanner.nextLine();
		
		System.out.print("���ּ� : ");
		String address = scanner.nextLine();
		
		if (mgr.signUp(member)) {
			int memberNum = mgr.checkDuplicateAccount(1, member.getMember_id());
			Customer customer = new Customer(memberNum, name, phone, areaNum, address);
			if (mgr.inputCustomer(customer)) {
				System.out.println("[SYSTEM] ȸ�� ���� �Ϸ�");
				BeginPage();	// ȸ�� ���� �Ϸ� �� �α��� �������� ���ư���
			} else {
				System.out.println("[SYSTEM] ���� �߻�");
			}
		} else {
			System.out.println("[SYSTEM] ���� �߻�");
		}
	}
	
}
