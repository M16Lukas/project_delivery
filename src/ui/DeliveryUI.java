package ui;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import mgr.AccountManager;
import mgr.OrderMenuManager;
import vo.Area;
import vo.Customer;
import vo.Member;
import vo.Menu;
import vo.OrderedMenu;
import vo.Store;
import vo.StoreCode;

public class DeliveryUI{
	// final field
	private final int STORE = 1;				// member_sort : ���� ȸ�� ��ȣ
	private final int CUSTOMER = 2;				// member_sort : ���� ȸ�� ��ȣ
	private final int MAX_STORE_CODE_NUM = 15;	// ���� ���̺� ������ ��ȣ
	
	// Object
	private	Scanner scanner = new Scanner(System.in);
	private AccountManager accuntMgr = new AccountManager();
	private OrderMenuManager orderMgr = new OrderMenuManager();
	
	private Member loginUser = null;		// �α����� ���� ���� ����� ��ü 
	private Customer custUserInfo = null; 	// �α����� ���� ȸ�� �� ����
	private Store storeUserInfo = null; 	// �α����� ���� ȸ�� �� ����
	
	private ArrayList<HashMap<String, Integer>> shoppingBasketLists = new ArrayList<>();	// ��ٱ��Ͽ� ArrayList
	
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
	
	/***************************************************************************************************************************
	 * 
	 * Page Group
	 * 
	 **************************************************************************************************************************/
	
	/********************************************************************************************************
	 *
	 * ���� ���� ������
	 * Account
	 * 
	 *********************************************************************************************************/
	/*
	 * ���� ������
	 */
	private int BeginPage(){
		System.out.println("===== [ ��� ���α׷� ] =====\n");
		System.out.println("1. �α���\n");
		System.out.println("2. ȸ������\n");
		System.out.println("0. ����\n");
		System.out.println("========================\n");
		System.out.print("> ");
		return scanner.nextInt();
	}
	
	
	/*
	 * ȸ������ ������
	 */
	private void signUpPage(){
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
		}
	}
	
	
	/*
	 * �α��� ������ 
	 */
	private void loginPage() {
		System.out.println("----- [ �α��� ] -----");
		
		loginUser = inputIdPw();
		switch (loginUser.getMember_sort()) {
		case STORE:
			storeMainPage();
			break;
		case CUSTOMER:
			customerMainPage();
			break;
		}
	}
	
	
	/*
	 * ��� ȸ�� ���� ������
	 */
	private void storeMainPage() {
		storeUserInfo = accuntMgr.getStoreInfo(loginUser.getMember_num());
		boolean run = true;
		while(run) {
			System.out.println("============ [ SCIT ��� ] ============");
			System.out.println("1. �ֹ� �̷� Ȯ��");
			System.out.println("2. �޴� ����");
			System.out.println("3. my����");
			System.out.println("======================================");
			System.out.print("> ");
			int menu = scanner.nextInt();
			switch (menu) {
			case 1:	// �ֹ� �̷� Ȯ��
				orderedMenuListPage(STORE, storeUserInfo.getStore_num());
				break;
			case 2:	// �޴� ����
				controlMenuPage();
				break;
			case 3:	// my����
				run = userInfoPage();
				break;
			default:
				run = false;
				break;
			}
		}
	}
	
	
	/*
	 * ���� ȸ�� ���� ������
	 */
	private void customerMainPage() {
		custUserInfo = accuntMgr.getCustomerInfo(loginUser.getMember_num());
		boolean run = true;
		while(run) {
			System.out.println("============ [ SCIT ��� ] ============");
			System.out.println("1. ���� �˻�");
			System.out.println("2. ī�װ�(����)");
			System.out.println("3. �ֹ�����");
			System.out.println("4. my����");
			System.out.println("======================================");
			System.out.print("> ");
			int menu = scanner.nextInt();
			switch (menu) {
			case 1:	// ���� �˻�
				searchStorePage();
				break;
			case 2:	// ī�װ�(����)
				storeCategoryPage();
				break;
			case 3:	// �ֹ�����
				orderedMenuListPage(CUSTOMER, custUserInfo.getCustomer_num());
				break;
			case 4:	// my����
				run = userInfoPage();
				break;
			default:
				break;
			}
		}
	}


	/*
	 * �α��� ���� ���� ������
	 */
	private boolean userInfoPage() {
		boolean run = true;
		boolean returnB = false;
		while(run) {
			System.out.println("+++++ [ MY���� ] +++++");
			System.out.println("�ȳ��ϼ��� ! " + loginUser.getMember_id() + " ��");
			System.out.println("--------------------");
			System.out.println("1. ���� ���� ����");
			System.out.println("2. �α׾ƿ�");
			System.out.println("3. ȸ��Ż��");
			System.out.println("0. �ڷΰ���");
			System.out.println("--------------------");
			System.out.print("> ");
			int menu = scanner.nextInt();
			switch (menu) {
			case 1:	// ���� ���� ����
				updateInfoPage();
				break;
			case 2:	// �α׾ƿ�
				logOut();
				run = false;
				returnB = false;
				break;
			case 3:	// ȸ��Ż��
				run = withdrawalPage();
				break;
			case 0:	// �ڷΰ���
				run = false;
				returnB = true;
				break;
			default:
				break;
			}
		}
		
		return returnB;
	}
	
	
	/*
	 * �������� ����
	 */
	private void updateInfoPage() {
		scanner.nextLine();
		boolean run = true;
		while(run) {
			System.out.println("----- [ �������� ���� ] -----");
			System.out.println("1. ��й�ȣ ����");
			System.out.println("2. ������ ����");
			System.out.println("0. �ڷΰ���");
			System.out.println("------------------------");
			System.out.print("> ");
			int menu = scanner.nextInt();
			switch (menu) {
			case 1:
				//��й�ȣ ����
				updatePwdPage();
				break;
			case 2:
				//������ ����
				int memSort = loginUser.getMember_sort(); 
				if(memSort == STORE) { // ���� ȸ��
					updateStoreInfoPage();
				} else if (memSort == CUSTOMER) { // ���� ȸ��
					updateCustomerInfoPage();
				}
				break;
			case 0:
				// �ڷΰ���
				run = false;
				break;
			default:
				break;
			}
		}
	}
	
	
	/*
	 * ���� ȸ�� ���� ���� ������
	 */
	private void updateStoreInfoPage(){
		scanner.nextLine();
		System.out.println("----- [ �� ���� ���� ] -----");
		
		System.out.print("����� : ");
		String name = scanner.nextLine();
		name = name.equals("") ? null : name;
		
		System.out.print("���� ��ȭ��ȣ : ");
		String phone = scanner.nextLine();
		phone = phone.equals("") ? null : phone;
		
		// ���� ����
		int code = inputStoreCodeForUpdate();
		
		// ���� ��ȣ ����
		int area = inputAreaForUpdate();
		
		System.out.print("�ֹ� �ּ� �ݾ� : ");
		String minOrderPriceStr = scanner.nextLine();
		int minOrderPrice = minOrderPriceStr.equals("") ? -1 : Integer.parseInt(minOrderPriceStr);
		
		System.out.print("��� �ð� : ");
		String deliveryTimeStr = scanner.nextLine();
		int deliveryTime = deliveryTimeStr.equals("") ? -1 : Integer.parseInt(deliveryTimeStr);
		
		System.out.print("����� : ");
		String deliveryTipStr = scanner.nextLine();
		int deliveryTip = deliveryTipStr.equals("") ? -1 : Integer.parseInt(deliveryTipStr);
		
		
		
		if (accuntMgr.updateStoreInfo(new Store(area, code, name, phone, minOrderPrice, deliveryTime, deliveryTip, storeUserInfo.getStore_num()))) {
			System.out.println("[SYSTEM] ���������� �����Ͽ����ϴ�");
		} else {
			System.out.println("[SYSTEM] ���� �߻�");
		} 
		
	}
	
	/*
	 * ���� ȸ�� ���� ���� ������
	 */
	private void updateCustomerInfoPage() {
		scanner.nextLine();
		System.out.println("----- [ �� ���� ���� ] -----");
		System.out.print("�̸� : ");
		String name = scanner.nextLine();
		name = name.equals("") ? null : name;
		
		System.out.print("��ȭ��ȣ : ");
		String phone = scanner.nextLine();
		phone = phone.equals("") ? null : phone;
		
		// ���� ����
		int area = inputAreaForUpdate();
		
		System.out.print("�ּ� : ");
		String address = scanner.nextLine();
		address = address.equals("") ? null : address;
		
	
		if(accuntMgr.updateCustomerInfo(new Customer(name, phone, area, address, custUserInfo.getCustomer_num()))) {
			System.out.println("[SYSTEM] ���������� �����Ͽ����ϴ�");
		} else {
			System.out.println("[SYSTEM] ���� �߻�");
		} 
	}
	
	
	/*
	 * ȸ��Ż�� ������
	 */
	private boolean withdrawalPage() {
		scanner.nextLine();
		System.out.println("***************** ȸ��Ż�� ******************");
		
		boolean run = true;
		while (run) {
			System.out.println("ȸ��Ż�� ���Ͻô� ��� ��й�ȣ�� �Է��� �ּ��� (�ڷΰ��� : 0)");
			System.out.print("> ");
			String pw = scanner.nextLine();
			
			// �ڷΰ���
			if (pw.equals("0")) {
				break;
			}
			
			// �α����� ������ ��ȣ�� �����Ͽ� ��й�ȣ�� num return
			Member tmp = accuntMgr.checkDuplicateAccount(loginUser.getMember_id());
			
			// return�� pw �� �Էµ� password�� ��ġ���� Ȯ��
			if (tmp.getMember_password().equals(pw) ) {
				System.out.println("-------------- [����] -------------");
				System.out.println("| ȸ��Ż�� �� ����� �������� ���� �����˴ϴ�     |");
				System.out.println("| Ż�� ���Ͻø� Y�� �Է����ּ���  [Y / N] |");
				System.out.println("----------------------------------");
				System.out.print("> ");
				String yesOrNo = scanner.nextLine();
				
				if (yesOrNo.toUpperCase().equals("Y") || yesOrNo.toUpperCase().equals("YES")) {
					withdrawal();	// ȸ��Ż�� ���
					return false;	// ���ѷ��� ����
				} else {
					System.out.println("[SYSTEM] Ż�� ����մϴ�");
					run = false;
				}
			} else {
				System.out.println("[SYSTEM] ��й�ȣ�� ��ġ���� �ʽ��ϴ�");
			}
		}
		
		return true;
	} 

	
	
	/********************************************************************************************************
	 *
	 * ���� �˻�, �ֹ� �̷� Ȯ��, �ֹ� ��� ������
	 * OrderMenu
	 * 
	 *********************************************************************************************************/
	/*
	 * ���� �˻� ���
	 */
	private void searchStorePage() {
		scanner.nextLine();
		System.out.println("~~~~~~~~~~ [���� �˻� ] ~~~~~~~~~~");
		System.out.print("�˻� : ");
		String name = scanner.nextLine(); 
		
		// �˻��� ���� ����Ʈ ��� & ���� ��ȣ �Է�
		ArrayList<Store> lists = orderMgr.searchStore(name, custUserInfo.getArea_num());
		printSearchedStoreList(lists);
	}
	

	/*
	 * ī�װ�(����) ������
	 */
	private void storeCategoryPage() {
		int codeNum = 0, sort = 0, minPrice = 0;
		
		boolean run = true;
		while(run) {
			System.out.println("~~~~~~~~~~ [ ī�װ� ] ~~~~~~~~~~");		
			// ���� �ڵ� ���
			System.out.println(" ��ü ���� �˻��� 0 �� �Է� ");
			codeNum = inputStoreCode();
			if (codeNum < 0 || codeNum > MAX_STORE_CODE_NUM) {
				System.out.println("[SYSTEM] �߸��� �Է��Դϴ�");
				continue;
			}
			
			// ���� ���� ���� ����
			sort = printStoreSortMethod();
			if (sort < 0 || sort > 3) {
				System.out.println("[SYSTEM] �߸��� �Է��Դϴ�");
				continue;
			}
			
			// �߰� ����(�ּ��ֹ��ݾ�) �߰�
			minPrice = minOrderPriceList();
			run = false;
		}
		
		// ī�װ� ���� & ���Ĺ���� ����� select ���
		ArrayList<Store> lists = orderMgr.printStoreByCategory(custUserInfo.getArea_num(), codeNum, sort, minPrice);
		
		// �˻��� ���� ����Ʈ ��� & ���� ��ȣ �Է�
		printSearchedStoreList(lists);
	}
	
	
	/*
	 * ���� ���� ��� ���
	 */
	private int printStoreSortMethod() {
		System.out.println("####### [ ���� ��� ] #######");
		System.out.println("1. �⺻��");
		System.out.println("2. ��޺��� ��");
		System.out.println("3. ����� ���� ��");
		System.out.println("-------------------------");
		System.out.print("> ");
		return scanner.nextInt();
	}
	
	
	/*
	 * �ּ� �ֹ� �ݾ� ���
	 */
	private int minOrderPriceList() {
		System.out.println("####### [ �ּ��ֹ��ݾ� ] #######");
		System.out.println("0. ��������");
		System.out.println("1. 5,000�� ����");
		System.out.println("2. 10,000�� ����");
		System.out.println("3. 12,000�� ����");
		System.out.println("4. 15,000�� ����");
		System.out.println("5. 20,000�� ����");
		System.out.println("----------------------------");
		System.out.print("> ");
		return scanner.nextInt();
	}
	
	
	/**
	 * �˻��� ���� ����Ʈ ��� �� ���� ��ȣ �Է�
	 */
	private void printSearchedStoreList(ArrayList<Store> lists) {
		boolean run = true;
		while (run) {
			// �˻��� ���� ����Ʈ ���
			boolean isEmpty = searchStore(lists);
			
			if (!isEmpty) {
				System.out.println("[SYSTEM] ���� ��ȣ�� �Է����ּ���");
			}
			System.out.println("[SYSTEM] �ڷΰ��� �� 0 �� �Է����ּ���");
			System.out.print("> ");
			int store_num = scanner.nextInt();
			
			// �ڷΰ���
    		if (store_num == 0) {
    			run = false;
    			break;
    		}
    		
    		// �Է��� ���� ��ȣ�� �˻��� ���� ����Ʈ�� �ִ��� Ȯ��
    		boolean in = false;
    		for(Store store : lists) {
    			// �Է��� ���� ��ȣ�� �˻��� ���� ����Ʈ�� �ִ� ���
    			if (store.getStore_num() == store_num) {
					in = true;
					break;
				}
    		}
			
			if (store_num != 0 && !isEmpty && in) {	// �Է°��� 0�� �ƴϰ�, �˻��� ���� ����Ʈ�� ���� �ְ�, �Է��� ���� ��ȣ�� �˻��� ���� ����Ʈ�� �ִ� ���
				storeMenuPage(store_num);			// ������ ������ �޴� ��� ������
			} else {
				System.out.println("[SYSTEM] �����ȣ�� Ȯ�����ּ���");
			}
		}
	}
	
	
	/*
	 * ������ ������ �޴� ��� ������
	 */
	private void storeMenuPage(int store_num) {
	    ArrayList<HashMap<String, Object>> mList = orderMgr.storeMenuPrintAll(store_num);
	    
	    if (mList.isEmpty()) {
	      System.out.println("[SYSTEM] �޴��� �߰����Դϴ�!");
	    } else {
	    	// ���� ���� ���
	    	System.out.println("############ [ " + mList.get(STORE).get("STORE_NAME") +" ] ############");
			System.out.println("��ȭ��ȣ : " + mList.get(STORE).get("STORE_PHONE") 
								+ "\n�ּ��ֹ��ݾ� : " + mList.get(STORE).get("MINORDERPRICE")
								+ "\n��޽ð� : " + mList.get(STORE).get("DELIVERYTIME")
								+ "\n����� : " + mList.get(STORE).get("DELIVERYTIP"));
			System.out.println("============ [ �޴� ��� ] ============");
			
	    	// ���� �޴� ����Ʈ ���
			printMenuList(mList);
			
			// ��ȣ �Է�
			boolean run = true;
	    	while (run) {
	    		System.out.println("[SYSTEM] �ڷΰ���/�׸���� : 0");
	    		System.out.print("�ֹ��� �޴� ��ȣ�� �Է����ּ��� > ");
	    		int menu_num = scanner.nextInt();
	    		
	    		// �ڷΰ���
	    		if (menu_num == 0) {
	    			run = false;
	    			break;
	    		}
	    		
	    		// ������ ���忡 �ִ� �޴����� Ȯ��
	    		if (!orderMgr.checkMenuIsInTheSelectedStore(new Menu(menu_num, store_num))) {
	    			System.out.println("[SYSTEM] �ش� ���忡 ���� �޴��Դϴ�");
	    			continue;
	    		}
	    		
	    		// ��ٱ��Ͽ� ������ ���� �޴� ���
	    		insertShoppingBasket(menu_num);
	    	}
	    	

	    	// ��ٱ��ϰ� ���������
	    	if (shoppingBasketLists.isEmpty()) {
				return;
			}
	    	
    		scanner.nextLine();
    		System.out.print("��ٱ��� ��� Ȯ�� (Y / N) > ");
    		String yn = scanner.nextLine();
    		if (yn.toUpperCase().equals("Y") || yn.toUpperCase().equals("YES")) {
    			confirmShoppingBasketPage(mList.get(STORE));	// �ش� ������ ������ ����
    		}
	    }
	}

	
	/*
	 * ��ٱ��Ͽ� �޴� ���
	 */
	private void insertShoppingBasket(int menu_num) {    				
		// �ֹ� ���� �Է�
    	System.out.print("���� > ");
    	int cnt = scanner.nextInt();
    		
    	// ��ٱ��Ͽ� ����
    	boolean inBaskets = false; 
    	
    	// �ֹ��Ϸ��� �޴��� �̹� ��ٱ��Ͽ� �����ϴ��� Ȯ��
    	for(HashMap<String, Integer> list : shoppingBasketLists) {
			if (list.get("menu_num") == menu_num) {							// ������ �޴��� �ߺ��Ͽ� �ֹ��ϴ� ���
				list.replace("menu_count", list.get("menu_count") + cnt);	// ������ �߰�
				inBaskets = true;
				break;
			}
		}
    	
    	// ��ٱ��Ͽ� ���� ���
    	if (!inBaskets) {
    		HashMap<String, Integer> map = new HashMap<>();
    		map.put("menu_num", menu_num);
        	map.put("menu_count", cnt);
        	shoppingBasketLists.add(map);
		}
    }
	
	
	/*
	 * ��ٱ��� Ȯ�� ������
	 */
	private void confirmShoppingBasketPage(HashMap<String, Object> store) {
		System.out.println("============ [ ��ٱ��� ] ============");
		
		int total_price = 0;	// �� �ݾ�
		
		// ����� ���� ����Ʈ ���
		for (int i = 0; i < shoppingBasketLists.size() ; i++) {
			int menu_num = shoppingBasketLists.get(i).get("menu_num");		// �޴���ȣ
			int menu_count = shoppingBasketLists.get(i).get("menu_count");	// �޴� ����
		
			Menu menu = orderMgr.printShoppingBasket(menu_num);
			
			int menu_total_price = (int) (menu.getMenu_price() * menu_count);	// �޴� �� ������ ���� ���� 
			
			System.out.println("�̸� : " + menu.getMenu_name());
			System.out.println("���� : " + menu_count);
			System.out.println("���� : " + menu_total_price);
			System.out.println("--------------------------------");
			
			shoppingBasketLists.get(i).put("total_price", menu_total_price);	// hashmap�� �޴� �� ������ ���� ����  ����
			
			total_price += menu_total_price;
		}
		
		System.out.println("�� �ֹ� �ݾ� : " + total_price);
		System.out.println("--------------------------------");
		
		// ��ٱ��� ����
		System.out.println("1. ��ٱ��� ��ü ����");
		System.out.println("2. " + total_price + " �� �ֹ��ϱ�");
		System.out.println("--------------------------------");
		System.out.print("> ");
		int key = scanner.nextInt();
		
		switch (key) {
		case 1:	// ��ٱ��� ��ü ����
			shoppingBasketLists.clear();
			break;
		case 2:	// �ֹ��ϱ�
			int minOrderPrice = Integer.parseInt(String.valueOf(store.get("MINORDERPRICE")));
			if (total_price < minOrderPrice) {
				System.out.println("[SYSTEM] ��� �ּ��ֹ��ݾ��� ä���ּ���");
				System.out.println("��� �ּ� �ֹ� �ݾ� : " + minOrderPrice + " ��");
			} else {
				orderShoppingBasketPage(total_price, store);	// ���� ���� ������
			}
			break;
		default:
			break;
		}
	}
	
	
	/*
	 * �ֹ�(����) ������
	 */
	private void orderShoppingBasketPage(int total_price, HashMap<String, Object> store) {
		scanner.nextLine();
		System.out.println("============ [ ������� ] ============");
		System.out.println("��� �ּ� 	: " + custUserInfo.getCustomer_address());
		System.out.println("��       ȣ 	: " + custUserInfo.getCustomer_phone());
		System.out.println("------------ [ �����ݾ� ] -------------");
		System.out.println("�ֹ��ݾ� : " + total_price);
		System.out.println("�� �� ��  : " + store.get("DELIVERYTIP"));
		System.out.println("-----------------------------------");
		int orderTotalPrice = (total_price + Integer.parseInt(String.valueOf(store.get("DELIVERYTIP"))));
		System.out.println("�� �����ݾ� : " + orderTotalPrice);
		System.out.println("-----------------------------------");
		System.out.println(orderTotalPrice + " �� �ֹ��ϱ� (Y / N)");
		System.out.print("> ");
		String yn = scanner.nextLine();
		
		// ���� �ֹ� 
		if (yn.toUpperCase().equals("Y") || yn.toUpperCase().equals("YES")) {
			for(HashMap<String, Integer> list : shoppingBasketLists) {
				OrderedMenu ordered = new OrderedMenu(custUserInfo.getCustomer_num()
													, Integer.parseInt(String.valueOf(store.get("STORE_NUM")))
													, list.get("menu_num")
													, list.get("menu_count")
													, list.get("total_price")); 
				orderMgr.orderMenu(ordered);
			}
		} else {
			System.out.print("������ �ֹ����� �ٽ� �ֹ��Ͻðڽ��ϱ� (Y / N) > ");
			yn = scanner.nextLine();
			if (yn.toUpperCase().equals("N") || yn.toUpperCase().equals("NO")) {
				shoppingBasketLists.clear();	// ��ٱ��� �ʱ�ȭ
			}
		}
	}
	
	
	/*
	 *	�޴� ���� ������
	 */
	private void controlMenuPage() {
		boolean run = true;
		while (run) {			
			System.out.println("~~~~~~~ [ MY ���� �޴� ���� ] ~~~~~~~");
			// �޴� ����Ʈ ���
			printMenuList(orderMgr.storeMenuPrintAll(storeUserInfo.getStore_num()));
			
			System.out.println("1. �޴� �߰�");
			System.out.println("2. �޴� ����");
			System.out.println("3. �޴� ����");
			System.out.println("0. �ڷΰ���");
			System.out.println("--------------------------------");
			System.out.print("> ");
			int menu = scanner.nextInt();
			switch (menu) {
			case 1:	// �޴� �߰�
				addMenuPage();
				break;
			case 2:	// �޴� ����
				updateMenuPage();
				break;
			case 3: // �޴� ����
				deleteMenuPage();
				break;
			case 0:	// �ڷΰ���
				run = false;
				break;
			default:
				break;
			}
		}
	}
	
	
	/*
	 * �޴� �߰� ������
	 */
	private void addMenuPage() {
		scanner.nextLine();
		System.out.println("~~~~~ [ �޴� �߰� ������ ] ~~~~~");
		
		System.out.print("[�ʼ�] �޴� �̸� : ");
		String name = scanner.nextLine();
		
		System.out.print("[����] �޴� �Ұ� : ");
		String intro = scanner.nextLine();
		intro = intro.equals("") ? null : intro;
		
		System.out.print("[�ʼ�] �޴� ���� : ");
		int price = scanner.nextInt();
		
		if (orderMgr.addMenu(new Menu(storeUserInfo.getStore_num(), name, intro, price))) {
			System.out.println("[SYSTEM] ���ο� �޴��� �߰��߽��ϴ�.");
		} else {
			System.out.println("[SYSTEM] ���� �߻�");
		}
	}
	
	/*
	 * �޴� ���� ������
	 */
	private void updateMenuPage() {
		// ������ �޴� ��ȣ ���� �� ���� �۾�
		System.out.print("�޴� ��ȣ �Է� > ");
		updateMenu(scanner.nextInt());
	}
	
	
	/*
	 * ���� ���� ���
	 */
	private void updateMenu(int num) {
		scanner.nextLine();
		System.out.println("~~~~~ [ �޴� ���� ������ ] ~~~~~");
		
		System.out.print("�޴� �̸� : ");
		String name = scanner.nextLine();
		name = name.equals("") ? null : name;
		
		System.out.print("�޴� �Ұ� : ");
		String intro = scanner.nextLine();
		intro = intro.equals("") ? null : intro;
		
		System.out.print("�޴� ���� : ");
		String priceStr = scanner.nextLine();
		int price = priceStr.equals("") ? -1 : Integer.parseInt(priceStr);
		
		System.out.print("ǰ�� ����(Y / N) : ");
		String soldoutStr = scanner.nextLine();
		int soldout = 0;
		
		if (soldoutStr.equals("")) {
			soldout = -1;	// ���� ����
		} else if (soldoutStr.toUpperCase().equals("Y") || soldoutStr.toUpperCase().equals("YES")) {
			soldout = 1; 	// ǰ��
		} else {
			soldout = 0; 	// �Ǹ���
		}
		
		
		if (orderMgr.updateMenu(new Menu(num, name, intro, price, soldout))) {
			System.out.println("[SYSTEM] �޴� ���� ���� �Ϸ�");
		} else {
			System.out.println("[SYSTEM] ���� �߻�");
		}
	}
	
	
	/*
	 * �޴� ���� ������
	 */
	private void deleteMenuPage() {
		System.out.print("�޴� ��ȣ �Է� > ");
		int menu_num = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("�޴��� �����ϰڽ��ϱ�? (Y / N) > ");
		String yesNo = scanner.nextLine();
		if (yesNo.toUpperCase().equals("Y") || yesNo.toUpperCase().equals("YES")) {
			if (orderMgr.deleteMenu(menu_num)) {
				System.out.println("[SYSTEM] �޴��� �����߽��ϴ�");
			} else {
				System.out.println("[SYSTEM] ���� �߻�");
			}
		}
	}
	
	
	/**
	 * 
	 * �ֹ� ���� Ȯ�� ������
	 * 
	 * @param member_sort - STORE : ����, CUSTOMER : ����
	 * @param num		  - ���� or ���� ���� ��ȣ (store_num, customer_num)
	 */
	private void orderedMenuListPage(int member_sort, int num) {
		switch (member_sort) {
		case STORE:	// ����ȸ���� �ֹ� ���� Ȯ��
			orderedMenuListPageForStore(num);
			break;
		case CUSTOMER:	// �� ȸ���� �ֹ� ���� Ȯ��
			orderedMenuListPageForCustomer(num);
			break;
		default:
			break;
		}
	}

	
	/**
	 * �ֹ� ���� Ȯ�� ������ (����ȸ�� ��)
	 * 
	 * @param num : store_num
	 */
	private void orderedMenuListPageForStore(int num) {
		ArrayList<HashMap<String, Object>> lists = orderMgr.orderedMenuListForStore(num);
		System.out.println("---------- [ �ֹ����� ] ----------");
		// �ֹ� ���� ���
		printOrderedMenuListForStore(lists);
		boolean run = true;
		while (run) {
			System.out.println("[SYSTEM] �ڷΰ��� : 0");
			System.out.print("> ");
			int input = scanner.nextInt();
			
			if (input == 0) {
				run = false;
				break;
			}
		}
	}
	
	
	/*
	 * �ֹ� ���� Ȯ�� ������ (��ȸ�� ��)
	 * 
	 * @param num : customer_num
	 */
	private void orderedMenuListPageForCustomer(int num) {
		ArrayList<HashMap<String, Object>> lists = orderMgr.orderedMenuListForCustomer(num);
		System.out.println("---------- [ �ֹ����� ] ----------");
		// �ֹ� ���� ���
		printOrderedMenuListForCustomer(lists);
		boolean run = true;
		while (run) {
			System.out.println("[SYSTEM] �ڷΰ���  : 0");
			System.out.print("> ");
			int input = scanner.nextInt();
			
			if (input == 0) {
				run = false;
				break;
			}
		}
	}


	/***************************************************************************************************************************
	 * 
	 * Method Group
	 * 
	 **************************************************************************************************************************/

	/*********************************************************************************************************
	 * 
	 * ���� ���� Method
	 * Account
	 * 
	 *********************************************************************************************************/
	
	/*
	 * ��й�ȣ ����
	 */
	private void updatePwdPage() {
		scanner.nextLine();
		System.out.println("----- [ ��й�ȣ ���� ] -----");
		System.out.println("���� ������ ��й�ȣ�� �Է��� �ּ���");
		System.out.print("> ");
		String newPw = scanner.nextLine();
		System.out.println("��й�ȣ Ȯ��");
		System.out.print("> ");
		String newPw2 = scanner.nextLine();

		if (newPw.equals(newPw2)) {
			loginUser.setMember_password(newPw);
			if (accuntMgr.updatePassword(loginUser)) {
				System.out.println("[SYSTEM] ��й�ȣ�� ����Ǿ����ϴ�");
			}
		} else {
			System.out.println("[SYSTEM] ��й�ȣ�� ��ġ���� �ʽ��ϴ�");
		}
	}
	
	
	/*
	 * �α׾ƿ�
	 */
	private boolean logOut() {
		loginUser = null;
		return false;
	}
	
	
	/*
	 * ȸ�� Ż��
	 */
	private void withdrawal() {
		if( accuntMgr.memberWithdrawal(loginUser.getMember_num()) ) {
			System.out.println("[SYSTEM] �׵��� ���� ���α׷��� ����� �ּż� �����մϴ�.");
			loginUser = null;
		} else {
			System.out.println("[SYSTEM] ������ �߻��߽��ϴ�");
		}
		
	} 
	
	
	/*
	 * ���α׷� ����
	 */
	private void ProgramExit() {
		System.out.println("[SYSTEM] ���α׷��� �����մϴ�.");
		isRun = false;
		System.exit(0);
	}
	
	
	/*
	 * ���� ���̺� ���
	 */
	private void printArea() {
		int cnt = 0;
		System.out.println("-------------------------------------------------------------------------------------------------");
		for(Area area : accuntMgr.printArea()) {
			System.out.print(area + "\t|   ");
			cnt++;
			if (cnt == 3) {
				cnt = 0;
				System.out.println();
			}
		}
		System.out.println("\n-------------------------------------------------------------------------------------------------");
		System.out.print("���� ��ȣ > ");
	}
	
	/*
	 * ���� ���̺� �Է� (ȸ�����Կ�)
	 */
	private int inputArea() {
		printArea();
		return scanner.nextInt();
	}
	
	/*
	 * ���� ���̺� �Է� (ȸ�����������)
	 */
	private int inputAreaForUpdate() {
		printArea();
		String areaNum = scanner.nextLine();
		
		if (areaNum.equals("")) {
			return 0;
		} else {
			return Integer.parseInt(areaNum);
		}
	}

	
	/*
	 * ���� ���� �ڵ� ���
	 */
	private void printStoreCode() {
		System.out.println("-------------------------------------");
		for(StoreCode code : accuntMgr.printStoreCode()) {
			System.out.println("| " + code);
		}
		System.out.println("-------------------------------------");
		System.out.print("���� �ڵ� > ");
	}
	
	
	/*
	 * ���� ���� �ڵ� �Է� (ȸ�����Կ�)
	 */
	private int inputStoreCode() {
		printStoreCode();
		return scanner.nextInt();
	}
	
	
	/*
	 * ���� ���� �ڵ� �Է� (ȸ�����������)
	 */
	private int inputStoreCodeForUpdate() {
		printStoreCode();
		String codeNum = scanner.nextLine();
		
		if (codeNum.equals("")) {
			return 0;
		} else {
			return Integer.parseInt(codeNum);
		}
	}
	
	
	/*
	 * ID, PW �Է� (�α��ο�)
	 */
	private Member inputIdPw() {
		scanner.nextLine();
		boolean chk = true;
		Member user = null;		// �α��� ���� return
		Member chkId = null;	// ���� ���� Ȯ�ο�
		
		while(chk) {
			System.out.print("ID : ");
			String id = scanner.nextLine();
			
			System.out.print("PW : ");
			String pw = scanner.nextLine();	
			
			// id�� table�� �����ϴ��� Ȯ��
			chkId = accuntMgr.checkDuplicateAccount(id);
			if (chkId == null) {
				System.out.println("[SYSTEM] �������� �ʴ� ���̵� �Դϴ�");
				continue;
			}
			
			// �Է��� id �� pw �� �Է��� pw ���� ��ġ�ϴ��� Ȯ��
			if (pw.equals(chkId.getMember_password())) {
				user = accuntMgr.login(new Member(id, pw));
				chk = false;
			} else {
				System.out.println("[SYSTEM] ��й�ȣ�� �߸� �Է��ϼ̽��ϴ�");
				continue;
			}
		}
		return user;
	}
	
	
	/*
	 * ID, PW �Է� (ȸ�����Կ�)
	 */
	private Member inputIdPw(int sort) {
		scanner.nextLine();
		String signUpID = null;	// ������ ID
		String signUpPW = null;	// ������ PW
		
		// ID �ߺ� üũ
		boolean chk = true;
		while (chk) {
			System.out.print("ID : ");
			signUpID = scanner.nextLine();
	
			if (accuntMgr.checkDuplicateAccount(signUpID) == null) {
				chk = false;
			} else {
				System.out.println("[SYSTEM] �̹� �����ϴ� ID �Դϴ�");
			}
		}
		
		// ��й�ȣ ��ġ Ȯ��
		chk = true;
		while (chk) {
			System.out.print("PW : ");
			signUpPW = scanner.nextLine();
			
			System.out.print("PW ���Է� : ");
			String signUpPWChk = scanner.nextLine();
			
			if (signUpPW.equals(signUpPWChk)) {
				chk = false;
			} else {
				System.out.println("[SYSTEM] ��й�ȣ�� ��ġ���� �ʽ��ϴ�");
			}
		}
		
		return new Member(signUpID, signUpPW, sort);	
	}
	
	
	/*
	 * ��� ȸ�� ���� �Է�
	 */
	private void inputStoreInfo() {	
		boolean chk = true;
		while(chk) {
			// ID & PW �Է�
			Member member = inputIdPw(STORE);
			
			// ���� �Է�
			int areaNum = inputArea();
			
			// ���� ���� ��� & �Է�
			int codeNum = inputStoreCode();
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
			
			if (accuntMgr.signUp(member)) { 
				int memberNum = accuntMgr.checkDuplicateAccount(member.getMember_id()).getMember_num();			
				Store store = new Store(memberNum, areaNum, codeNum, storeName, storePhone, minOrderPrice, deliveryTime, deliveryTip);
				
				if (accuntMgr.inputStore(store)) {
					System.out.println("[SYSTEM] ȸ�� ���� �Ϸ�");
					chk = false;
				} else {
					System.out.println("[SYSTEM] ���� �߻�");
				}
			} else {
				System.out.println("[SYSTEM] ���� �߻�");
			}
		}
	}
	
	
	/**
	 * ���� ȸ�� ���� �Է�
	 */
	private void inputCustomerInfo() {
		boolean chk = true;
		while(chk) {
			// ID & PW �Է�
			Member member = inputIdPw(CUSTOMER);
			
			// �� ���� �Է�
			System.out.print("�̸� : ");
			String name = scanner.nextLine();
			
			System.out.print("��ȭ��ȣ : ");
			String phone = scanner.nextLine();
			
			// ���� �Է�
			int areaNum = inputArea();
			scanner.nextLine();
			
			System.out.print("���ּ� : ");
			String address = scanner.nextLine();
			
			if (accuntMgr.signUp(member)) {
				int memberNum = accuntMgr.checkDuplicateAccount(member.getMember_id()).getMember_num();
				Customer customer = new Customer(memberNum, name, phone, areaNum, address);
				
				if (accuntMgr.inputCustomer(customer)) {
					System.out.println("[SYSTEM] ȸ�� ���� �Ϸ�");
					chk = false;
				} else {
					System.out.println("[SYSTEM] ���� �߻�");
				}
			} else {
				System.out.println("[SYSTEM] ���� �߻�");
			}
		}
	}
	
	
	/********************************************************************************************************
	 *
	 * ���� �˻�, �ֹ� �̷� Ȯ��, �ֹ� ���
	 * OrderMenu
	 * 
	 *********************************************************************************************************/
	
	/*
	 * ���� �˻� ��� 
	 */
	private boolean searchStore(ArrayList<Store> lists) {		
		if (lists.isEmpty()) {
			System.out.println("[SYSTEM] ��~");
			return true;
		} else {
			// ���� ����Ʈ ���
			System.out.println("------------------------------------------------------------------------------");
			for(Store list : lists) {
				System.out.println(list);
				System.out.println("------------------------------------------------------------------------------");
			}
			return false;
		}
	}
	
	
	/*
	 * ������ �޴� ����Ʈ ���
	 */
	private void printMenuList(ArrayList<HashMap<String, Object>> storeMenuList) {
    	for (HashMap<String, Object> map : storeMenuList) {
    		if(Integer.parseInt(String.valueOf(map.get("MENU_SOLDOUT"))) == 1) {
    			continue;
    		}
    		System.out.println("�Ŵ���ȣ : " + map.get("MENU_NUM") + "\t�Ŵ��̸� : " + map.get("MENU_NAME"));
    		System.out.println("�Ŵ��Ұ� : " + ((String) map.get("MENU_INTRO") == null ? "" : map.get("MENU_INTRO")));
    		System.out.println("�Ŵ����� : " + map.get("MENU_PRICE"));
    		System.out.println("--------------------------------");
    	}
	}
	
	
	/*
	 * �ֹ� ���� ��� (���� ȸ�� ��)
	 */
	private void printOrderedMenuListForCustomer(ArrayList<HashMap<String, Object>> lists) {
		if (lists.isEmpty()) {
			System.out.println("[SYSTEM] �ֹ� ������ �����ϴ�");
		} else {
			// �ֹ� ���� �񱳿� hashmap
			HashMap<String, Object> tmp = lists.get(0);
			// ����� �޴�
			String menuList = "\n" + String.valueOf(tmp.get("MENU_NAME")) + " " + String.valueOf(tmp.get("MENU_COUNT")) + " ��\t" + String.valueOf(tmp.get("TOTAL_PRICE")) + " ��";
			// �� �ֹ��ݾ�
			int totalPrice = Integer.parseInt(String.valueOf(tmp.get("TOTAL_PRICE")));
			// ��
			int tip = Integer.parseInt(String.valueOf(tmp.get("DELIVERYTIP")));
			
			for (int i = 1; i < lists.size(); i++) {
				// ������ �ð��� �ֹ�(= ���� ����) �� ���
				if (tmp.get("ORDERED_TIME").equals(lists.get(i).get("ORDERED_TIME"))) {
					// ����� �޴� ���� �߰�
					menuList += "\n" + String.valueOf(lists.get(i).get("MENU_NAME")) + " " + String.valueOf(lists.get(i).get("MENU_COUNT")) + " ��\t" + String.valueOf(lists.get(i).get("TOTAL_PRICE")) + " ��";
					// �� �ֹ��ݾ� �߰�
					totalPrice += Integer.parseInt(String.valueOf(lists.get(i).get("TOTAL_PRICE")));
				} else {
					// �ֹ� ���� ���
					printOrderedForCustomer(tmp, menuList, totalPrice, tip);
					
					// ���ο� �ֹ� ���� ������ ���� �ʱ�ȭ
					tmp = lists.get(i);
					// ����� �޴�
					menuList = "\n" + String.valueOf(tmp.get("MENU_NAME")) + " " + String.valueOf(tmp.get("MENU_COUNT")) + " ��\t" + String.valueOf(tmp.get("TOTAL_PRICE")) + " ��";
					// �� �ֹ��ݾ�
					totalPrice = (Integer.parseInt(String.valueOf(tmp.get("MENU_COUNT"))) * Integer.parseInt(String.valueOf(tmp.get("TOTAL_PRICE"))));
					// ��
					tip = Integer.parseInt(String.valueOf(tmp.get("DELIVERYTIP")));
				}
				
				// ������ �ֹ� ������ ��� 
				if (i == lists.size() - 1) {
					printOrderedForCustomer(tmp, menuList, totalPrice, tip);
				}
			}
			
		}
	}
	
	
	/*
	 * �ֹ� ���� ��� (���� ȸ�� ��)
	 */
	private void printOrderedMenuListForStore(ArrayList<HashMap<String, Object>> lists) {
		if (lists.isEmpty()) {
			System.out.println("[SYSTEM] �ֹ� ������ �����ϴ�");
		} else {
			// �ֹ� ���� �񱳿� hashmap
			HashMap<String, Object> tmp = lists.get(0);
			// ����� �޴�
			String menuList = "\n" + String.valueOf(tmp.get("MENU_NAME")) + " " + String.valueOf(tmp.get("MENU_COUNT")) + " ��\t" + String.valueOf(tmp.get("TOTAL_PRICE")) + " ��";
			// �� �ֹ��ݾ�
			int totalPrice = Integer.parseInt(String.valueOf(tmp.get("TOTAL_PRICE")));
			// ��
			int tip = storeUserInfo.getDeliveryTip();
						
			for (int i = 1; i < lists.size(); i++) {
				// ������ �ð��� ���� �ֹ��� ���
				if (tmp.get("ORDERED_TIME").equals(lists.get(i).get("ORDERED_TIME")) && tmp.get("CUSTOMER_NUM").equals(lists.get(i).get("CUSTOMER_NUM"))) {
					// ����� �޴� ���� �߰�
					menuList += "\n" + String.valueOf(lists.get(i).get("MENU_NAME")) + " " + String.valueOf(lists.get(i).get("MENU_COUNT")) + " ��\t" + String.valueOf(lists.get(i).get("TOTAL_PRICE")) + " ��";
					// �� �ֹ��ݾ� �߰�
					totalPrice += Integer.parseInt(String.valueOf(lists.get(i).get("TOTAL_PRICE")));
				} else {
					// �ֹ� ���� ���
					printOrderedForStore(tmp, menuList, totalPrice, tip);
							
					// ���ο� �ֹ� ���� ������ ���� �ʱ�ȭ
					tmp = lists.get(i);
					// ����� �޴�
					menuList = "\n" + String.valueOf(tmp.get("MENU_NAME")) + " " + String.valueOf(tmp.get("MENU_COUNT")) + " ��\t" + String.valueOf(tmp.get("TOTAL_PRICE")) + " ��";
					// �� �ֹ��ݾ�
					totalPrice = (Integer.parseInt(String.valueOf(tmp.get("MENU_COUNT"))) * Integer.parseInt(String.valueOf(tmp.get("TOTAL_PRICE"))));
				}
							
				// ������ �ֹ� ������ ��� 
				if (i == lists.size() - 1) {
					printOrderedForStore(tmp, menuList, totalPrice, tip);
				}
			}
		}
	}
	
	
	/*
	 * �ֹ� ���� ��� ���
	 */
	private void PrintOrderedMenu(String menuList, int totalPrice, int tip) {
		System.out.println(menuList);
		System.out.println("-------------------------------");
		System.out.println("���ֹ��ݾ� : " + totalPrice + " ��");
		System.out.println("��   ��   �� : " + tip + " ��");
		System.out.println("-------------------------------");
		System.out.println("�Ѱ����ݾ� : " + (totalPrice + tip) + " ��");
		System.out.println("===============================");
	}
	
	/**
	 * �ֹ� ���� ��¹� (���� ȸ�� ��)
	 */
	private void printOrderedForCustomer(HashMap<String, Object> tmp, String menuList, int totalPrice, int tip) {
		System.out.println("�ֹ��Ͻ� : " + tmp.get("ORDERED_TIME"));
		System.out.println("��      �� : " + tmp.get("STORE_NAME"));
		PrintOrderedMenu(menuList, totalPrice, tip);
	}
	
	/**
	 * �ֹ� ���� ��¹� (���� ȸ�� ��)
	 */
	private void printOrderedForStore(HashMap<String, Object> tmp, String menuList, int totalPrice, int tip) {
		System.out.println("�ֹ� �Ͻ�  	 : " + tmp.get("ORDERED_TIME"));
		System.out.println("����   	 : " + tmp.get("CUSTOMER_NAME"));
		System.out.println("�� ��ȭ��ȣ : " + tmp.get("CUSTOMER_PHONE"));
		System.out.println("�� �ּ� 	 : " + tmp.get("CUSTOMER_ADDRESS"));
		PrintOrderedMenu(menuList, totalPrice, tip);
	}
}

