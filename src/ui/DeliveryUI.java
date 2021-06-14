package ui;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
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
	/*
	 * field
	 */
	// final field
	private final int STORE = 1;				// member_sort : 매장 회원 번호
	private final int CUSTOMER = 2;				// member_sort : 개인 회원 번호
	private final int MAX_STORE_CODE_NUM = 15;	// 업종 테이블 마지막 번호
	private final int MAX_AREA_NUM = 17;		// 지역 테이블 마지막 번호
	
	// Object
	private	Scanner scanner = new Scanner(System.in);
	private AccountManager accuntMgr = new AccountManager();
	private OrderMenuManager orderMgr = new OrderMenuManager();
	
	private Member loginUser = null;		// 로그인한 계정 정보 저장용 객체 
	private Customer custUserInfo = null; 	// 로그인한 매장 회원 상세 정보
	private Store storeUserInfo = null; 	// 로그인한 개인 회원 상세 정보
	
	private ArrayList<HashMap<String, Integer>> shoppingBasketLists = new ArrayList<>();	// 장바구니용 ArrayList
	// 장바구니에는 하나의 매장의 음식들만 담길 수 있다
	// 만약 장바구니를 초기화하지 않고 다른 매장의 메뉴를 담을 경우 값을 비교하여 장바구니를 초기화할지 물어본다
	private int shoppingBasketNumber = -1;
	
	// field
	private boolean isRun = true;
	
	
	/*
	 * Constructor
	 */
	public DeliveryUI(){
		while (isRun) {
			int key = BeginPage();
			switch (key) {
			case 0:	// 종료
				ProgramExit();
				break;
			case 1: // 로그인
				loginPage();
				break;
			case 2:	// 회원가입
				signUpPage();
				break;
			default:
				System.out.println("[SYSTEM] 잘못된 입력입니다 (0 ~ 2 숫자를 입력해주세요)");
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
	 * 계정 관련 페이지
	 * Account
	 * 
	 *********************************************************************************************************/
	/*
	 * 시작 페이지
	 */
	private int BeginPage(){
		int menu = -1;
		System.out.println("===== [ 배달 프로그램 ] =====\n");
		System.out.println("1. 로그인\n");
		System.out.println("2. 회원가입\n");
		System.out.println("0. 종료\n");
		System.out.println("========================\n");
		System.out.print("> ");
		try {
			menu = scanner.nextInt();
		} catch (InputMismatchException e) {
			scanner.nextLine();
		}
		return menu;
	}
	
	
	/*
	 * 회원가입 페이지
	 */
	private void signUpPage(){
		int sort = -1;
		boolean run = true;
		while(run) {
			System.out.println("----- [ 회원가입 ] -----");
			System.out.println("1. 기업회원");
			System.out.println("2. 개인회원");
			System.out.println("---------------------");
			System.out.print("> ");
			try {
				sort = scanner.nextInt();
			} catch (InputMismatchException e) {
				scanner.nextLine();
			}
			
			if (sort == 1 || sort == 2)
				run = false;
			else
				System.out.println("[SYSTEM] 잘못된 입력입니다 (기업회원  : 1, 개인회원 : 2 - 숫자를 입력해주세요)");
		}
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
	 * 로그인 페이지 
	 */
	private void loginPage() {
		System.out.println("----- [ 로그인 ] -----");
		
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
	 * 기업 회원 메인 페이지
	 */
	private void storeMainPage() {
		storeUserInfo = accuntMgr.getStoreInfo(loginUser.getMember_num());
		
		int menu = -1;
		boolean run = true;
		while(run) {
			System.out.println("============ [ SCIT 배달 ] ============");
			System.out.println("1. 주문 이력 확인");
			System.out.println("2. 메뉴 관리");
			System.out.println("3. my정보");
			System.out.println("======================================");
			System.out.print("> ");
			try {
				menu = scanner.nextInt();
			} catch (InputMismatchException e) {
				scanner.nextLine();
			}
			
			switch (menu) {
			case 1:	// 주문 이력 확인
				orderedMenuListPage(STORE, storeUserInfo.getStore_num());
				break;
			case 2:	// 메뉴 관리
				controlMenuPage();
				break;
			case 3:	// my정보
				run = userInfoPage();
				break;
			default:
				System.out.println("[SYSTEM] 잘못된 입력입니다 - 숫자(1 ~ 3)를 입력해주세요");
				break;
			}
		}
	}
	
	
	/*
	 * 개인 회원 메인 페이지
	 */
	private void customerMainPage() {
		custUserInfo = accuntMgr.getCustomerInfo(loginUser.getMember_num());
		int menu = -1;
		boolean run = true;
		while(run) {
			System.out.println("============ [ SCIT 배달 ] ============");
			System.out.println("1. 매장 검색");
			System.out.println("2. 카테고리(업종)");
			System.out.println("3. 주문내역");
			System.out.println("4. my정보");
			System.out.println("======================================");
			System.out.print("> ");
			try {
				menu = scanner.nextInt();
			} catch (InputMismatchException e) {
				scanner.nextLine();
			}
			
			switch (menu) {
			case 1:	// 매장 검색
				searchStorePage();
				break;
			case 2:	// 카테고리(업종)
				storeCategoryPage();
				break;
			case 3:	// 주문내역
				orderedMenuListPage(CUSTOMER, custUserInfo.getCustomer_num());
				break;
			case 4:	// my정보
				run = userInfoPage();
				break;
			default:
				System.out.println("[SYSTEM] 잘못된 입력입니다 - 숫자(1 ~ 4)를 입력해주세요");
				break;
			}
		}
	}


	/*
	 * 로그인 계정 관리 페이지
	 */
	private boolean userInfoPage() {
		int menu = -1;
		boolean run = true;
		boolean returnB = false;
		while(run) {
			System.out.println("+++++ [ MY정보 ] +++++");
			System.out.println("안녕하세요 ! " + loginUser.getMember_id() + " 님");
			System.out.println("--------------------");
			System.out.println("1. 계정 정보 수정");
			System.out.println("2. 로그아웃");
			System.out.println("3. 회원탈퇴");
			System.out.println("0. 뒤로가기");
			System.out.println("--------------------");
			System.out.print("> ");
			try {
				menu = scanner.nextInt();
			} catch (InputMismatchException e) {
				scanner.nextLine();
			}
			switch (menu) {
			case 1:	// 계정 정보 수정
				updateInfoPage();
				break;
			case 2:	// 로그아웃
				logOut();
				run = false;
				returnB = false;
				break;
			case 3:	// 회원탈퇴
				run = withdrawalPage();
				break;
			case 0:	// 뒤로가기
				run = false;
				returnB = true;
				break;
			default:
				System.out.println("[SYSTEM] 잘못된 입력입니다 - 숫자(0 ~ 3)를 입력해주세요");
				break;
			}
		}
		
		return returnB;
	}
	
	
	/*
	 * 계정정보 수정
	 */
	private void updateInfoPage() {
		scanner.nextLine();
		int menu = -1;
		boolean run = true;
		while(run) {
			System.out.println("----- [ 계정정보 수정 ] -----");
			System.out.println("1. 비밀번호 변경");
			System.out.println("2. 상세정보 변경");
			System.out.println("0. 뒤로가기");
			System.out.println("------------------------");
			System.out.print("> ");
			try {
				menu = scanner.nextInt();
			} catch (InputMismatchException e) {
				scanner.nextLine();
			}
			switch (menu) {
			case 1:
				//비밀번호 변경
				updatePwdPage();
				break;
			case 2:
				//상세정보 변경
				int memSort = loginUser.getMember_sort(); 
				if(memSort == STORE) { // 매장 회원
					updateStoreInfoPage();
				} else if (memSort == CUSTOMER) { // 개인 회원
					updateCustomerInfoPage();
				}
				break;
			case 0:
				// 뒤로가기
				run = false;
				break;
			default:
				System.out.println("[SYSTEM] 잘못된 입력입니다 - 숫자(0 ~ 2)를 입력해주세요");
				break;
			}
		}
	}
	
	
	/*
	 * 매장 회원 정보 수정 페이지
	 */
	private void updateStoreInfoPage(){
		scanner.nextLine();
		System.out.println("----- [ 상세 정보 수정 ] -----");
		
		System.out.print("매장명 : ");
		String name = scanner.nextLine();
		name = name.equals("") ? null : name;
		
		System.out.print("매장 전화번호 : ");
		String phone = scanner.nextLine();
		phone = phone.equals("") ? null : phone;
		
		// 업종 변경
		int code = inputStoreCodeForUpdate();
		
		// 지역 번호 수정
		int area = inputAreaForUpdate();
		
		System.out.print("주문 최소 금액 : ");
		String minOrderPriceStr = scanner.nextLine();
		int minOrderPrice = minOrderPriceStr.equals("") ? -1 : Integer.parseInt(minOrderPriceStr);
		
		System.out.print("배달 시간 : ");
		String deliveryTimeStr = scanner.nextLine();
		int deliveryTime = deliveryTimeStr.equals("") ? -1 : Integer.parseInt(deliveryTimeStr);
		
		System.out.print("배달팁 : ");
		String deliveryTipStr = scanner.nextLine();
		int deliveryTip = deliveryTipStr.equals("") ? -1 : Integer.parseInt(deliveryTipStr);
		
		
		
		if (accuntMgr.updateStoreInfo(new Store(area, code, name, phone, minOrderPrice, deliveryTime, deliveryTip, storeUserInfo.getStore_num()))) {
			System.out.println("[SYSTEM] 성공적으로 수정하였습니다");
		} else {
			System.out.println("[SYSTEM] 오류 발생");
		} 
		
	}
	
	/*
	 * 개인 회원 정보 수정 페이지
	 */
	private void updateCustomerInfoPage() {
		scanner.nextLine();
		System.out.println("----- [ 상세 정보 수정 ] -----");
		System.out.print("이름 : ");
		String name = scanner.nextLine();
		name = name.equals("") ? null : name;
		
		System.out.print("전화번호 : ");
		String phone = scanner.nextLine();
		phone = phone.equals("") ? null : phone;
		
		// 지역 수정
		int area = inputAreaForUpdate();
		
		System.out.print("주소 : ");
		String address = scanner.nextLine();
		address = address.equals("") ? null : address;
		
	
		if(accuntMgr.updateCustomerInfo(new Customer(name, phone, area, address, custUserInfo.getCustomer_num()))) {
			System.out.println("[SYSTEM] 성공적으로 수정하였습니다");
		} else {
			System.out.println("[SYSTEM] 오류 발생");
		} 
	}
	
	
	/*
	 * 회원탈퇴 페이지
	 */
	private boolean withdrawalPage() {
		scanner.nextLine();
		System.out.println("***************** 회원탈퇴 ******************");
		
		boolean run = true;
		while (run) {
			System.out.println("회원탈퇴를 원하시는 경우 비밀번호를 입력해 주세요 (뒤로가기 : 0)");
			System.out.print("> ");
			String pw = scanner.nextLine();
			
			// 뒤로가기
			if (pw.equals("0")) {
				break;
			}
			
			// 로그인한 계정의 번호를 전달하여 비밀번호와 num return
			Member tmp = accuntMgr.checkDuplicateAccount(loginUser.getMember_id());
			
			// return된 pw 와 입력된 password가 일치한지 확인
			if (tmp.getMember_password().equals(pw) ) {
				System.out.println("-------------- [주의] -------------");
				System.out.println("| 회원탈퇴 시 저장된 정보들이 전부 삭제됩니다     |");
				System.out.println("| 탈퇴를 원하시면 Y를 입력해주세요  [Y / N] |");
				System.out.println("----------------------------------");
				System.out.print("> ");
				String yesOrNo = scanner.nextLine();
				
				if (yesOrNo.toUpperCase().equals("Y") || yesOrNo.toUpperCase().equals("YES")) {
					withdrawal();	// 회원탈퇴 기능
					return false;	// 무한루프 종료
				} else {
					System.out.println("[SYSTEM] 탈퇴를 취소합니다");
					run = false;
				}
			} else {
				System.out.println("[SYSTEM] 비밀번호가 일치하지 않습니다");
			}
		}
		
		return true;
	} 

	
	
	/********************************************************************************************************
	 *
	 * 매장 검색, 주문 이력 확인, 주문 기능 페이지
	 * OrderMenu
	 * 
	 *********************************************************************************************************/
	/*
	 * 매장 검색 기능
	 */
	private void searchStorePage() {
		scanner.nextLine();
		System.out.println("~~~~~~~~~~ [매장 검색 ] ~~~~~~~~~~");
		System.out.print("검색 : ");
		String name = scanner.nextLine(); 
		
		// 검색된 매장 리스트 출력 & 매장 번호 입력
		ArrayList<Store> lists = orderMgr.searchStore(name, custUserInfo.getArea_num());
		printSearchedStoreList(lists);
	}
	

	/*
	 * 카테고리(업종) 페이지
	 */
	private void storeCategoryPage() {
		int codeNum = 0, sort = 0, minPrice = 0;
		
		boolean run = true;
		while(run) {
			System.out.println("~~~~~~~~~~ [ 카테고리 ] ~~~~~~~~~~");		
			// 업종 코드 출력
			System.out.println("[SYSTEM] 전체 매장 검색은 0 을 입력해주세요");
			codeNum = inputStoreCode();

			
			// 매장 정렬 기준 선택
			sort = printStoreSortMethod();

			// 추가 조건(최소주문금액) 추가
			minPrice = minOrderPriceList();
			
			run = false;
		}
		
		// 카테고리 조건 & 정렬방식이 적용된 select 결과
		ArrayList<Store> lists = orderMgr.printStoreByCategory(custUserInfo.getArea_num(), codeNum, sort, minPrice);
		
		// 검색된 매장 리스트 출력 & 매장 번호 입력
		printSearchedStoreList(lists);
	}
	
	
	/*
	 * 매장 정렬 방법 출력
	 */
	private int printStoreSortMethod() {
		int sort = -1;
		
		System.out.println("####### [ 정렬 방법 ] #######");
		System.out.println("1. 기본순");
		System.out.println("2. 배달빠른 순");
		System.out.println("3. 배달팁 낮은 순");
		System.out.println("-------------------------");
		boolean run = true;
		while (run) {
			System.out.print("> ");
			try {
				sort = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("[SYSTEM] 잘못된 입력입니다");
				scanner.nextLine();
				continue;
			}
			
			if (sort < 1 || sort > 3) {
				System.out.println("[SYSTEM] 잘못된 입력입니다");
			} else {
				run = false;
			}
		}
		return sort;
	}
	
	
	/*
	 * 최소 주문 금액 출력
	 */
	private int minOrderPriceList() {
		int sort = -1;
		System.out.println("####### [ 최소주문금액 ] #######");
		System.out.println("0. 설정안함");
		System.out.println("1. 5,000원 이하");
		System.out.println("2. 10,000원 이하");
		System.out.println("3. 12,000원 이하");
		System.out.println("4. 15,000원 이하");
		System.out.println("5. 20,000원 이하");
		System.out.println("----------------------------");
		boolean run = true;
		while (run) {
			System.out.print("> ");
			try {
				sort = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("[SYSTEM] 잘못된 입력입니다");
				scanner.nextLine();
				continue;
			}
			
			if (sort < 0 || sort > 5) {
				System.out.println("[SYSTEM] 잘못된 입력입니다");
			} else {
				run = false;
			}
		}
		return sort;
	}
	
	
	/**
	 * 검색된 매장 리스트 출력 및 매장 번호 입력
	 */
	private void printSearchedStoreList(ArrayList<Store> lists) {
		boolean run = true;
		int store_num = -1;
		while (run) {
			// 검색된 매장 리스트 출력
			boolean isEmpty = searchStore(lists);
			
			boolean run2 = true;
			while (run2) {
				if (!isEmpty) {
					System.out.println("[SYSTEM] 매장 번호를 입력해주세요");
				}
				System.out.println("[SYSTEM] 뒤로가기 시 0 을 입력해주세요");
				System.out.print("> ");
				
				try {
					store_num = scanner.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("[SYSTEM] 잘못된 입력입니다");
					scanner.nextLine();
					continue;
				}
				
				run2 = false;
			}
			
			// 뒤로가기
    		if (store_num == 0) {
    			run = false;
    			break;
    		}
    		
    		// 입력한 매장 번호가 검색된 매장 리스트에 있는지 확인
    		boolean in = false;
    		for(Store store : lists) {
    			// 입력한 매장 번호가 검색된 매장 리스트에 있는 경우
    			if (store.getStore_num() == store_num) {
					in = true;
					break;
				}
    		}
			
			if (store_num != 0 && !isEmpty && in) {	// 입력값이 0이 아니고, 검색된 매장 리스트에 값이 있고, 입력한 매장 번호가 검색된 매장 리스트에 있는 경우
				storeMenuPage(store_num);			// 선택한 매장의 메뉴 출력 페이지
			} else {
				System.out.println("[SYSTEM] 매장번호를 확인해주세요");
			}
		}
	}
	
	
	/*
	 * 선택한 매장의 메뉴 출력 페이지
	 */
	private void storeMenuPage(int store_num) {
	    ArrayList<HashMap<String, Object>> mList = orderMgr.storeMenuPrintAll(store_num);
	    if (mList.isEmpty()) {
	      System.out.println("[SYSTEM] 메뉴를 추가중입니다!");
	    } else {
	    	// 매장 정보 출력
	    	System.out.println("############ [ " + mList.get(STORE).get("STORE_NAME") +" ] ############");
			System.out.println("전화번호 : " + mList.get(STORE).get("STORE_PHONE") 
								+ "\n최소주문금액 : " + mList.get(STORE).get("MINORDERPRICE")
								+ "\n배달시간 : " + mList.get(STORE).get("DELIVERYTIME")
								+ "\n배달팁 : " + mList.get(STORE).get("DELIVERYTIP"));
			System.out.println("============ [ 메뉴 목록 ] ============");
			
	    	// 매장 메뉴 리스트 출력
			printMenuList(mList);
			
			// 번호 입력
			boolean run = true;
	    	while (run) {
	    		if (!shoppingBasketLists.isEmpty()) {
	    			System.out.println("[SYSTEM] 뒤로가기/그만담기 : 0");
				} else {
					System.out.println("[SYSTEM] 뒤로가기 : 0");
				}
	    		System.out.print("주문할 메뉴 번호를 입력해주세요 > ");
	    		int menu_num = -1;
	    		
	    		try {
	    			menu_num = scanner.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("[SYSTEM] 잘못된 입력입니다");
					scanner.nextLine();
					continue;
				}
	    		
	    		
	    		// 뒤로가기
	    		if (menu_num == 0) {
	    			run = false;
	    			break;
	    		}
	    		
	    		// 선택한 매장에 있는 메뉴인지 확인
	    		if (!orderMgr.checkMenuIsInTheSelectedStore(new Menu(menu_num, store_num))) {
	    			System.out.println("[SYSTEM] 해당 매장에 없는 메뉴입니다");
	    			continue;
	    		}
	    		
	    		// 장바구니에는 하나의 매장의 음식들만 담길 수 있다
	    		// 만약 장바구니를 초기화하지 않고 다른 매장의 메뉴를 담을 경우 값을 비교하여 장바구니를 초기화할지 물어본다
	    		
	    		// 장바구니가 비어있는 경우 : 해당 매장의 번호를 저장한다
	    		scanner.nextLine();
	    		if (shoppingBasketLists.isEmpty()) {											// 장바구니가 비어있는경우
	    			shoppingBasketNumber = store_num;
		    		insertShoppingBasket(menu_num);												// 장바구니에 선택한 매장 메뉴 담기
				} else {																		// 장바구니가 비어있지 않은 경우
					if (shoppingBasketNumber == store_num) {									// 동일한 매장의 메뉴를 담을 경우
						insertShoppingBasket(menu_num);											// 장바구니에 선택한 매장 메뉴 담기
					} else {																	// 새로운 매장의 메뉴를 담을 경우
						System.out.println("[SYSTEM] 장바구니에는 같은 가게의 메뉴만 담을 수 있습니다");
			    		System.out.println("[SYSTEM] 선택하신 메뉴를 장바구니에 담을 경우 이전에 담은 메뉴가 삭제됩니다");
			    		System.out.print("담기(Y) / 취소(N) > ");
			    		String yn = scanner.nextLine();
			    		if (yn.toUpperCase().equals("Y") || yn.toUpperCase().equals("YES")) {	// 새로운 매장 메뉴 등록
			    			shoppingBasketNumber = store_num;
			    			shoppingBasketLists.clear();
			    			insertShoppingBasket(menu_num);
			    		} else {																// 취소(뒤로가기)
			    			run = false;
			    			return;
			    		}
					}
				}
	    	}
	    	

	    	// 장바구니가 비어있으면
	    	if (shoppingBasketLists.isEmpty()) {
				return;
			}
	    	
	    	scanner.nextLine();
    		System.out.print("장바구니 목록 확인 (Y / N) > ");
    		String yn = scanner.nextLine();
    		if (yn.toUpperCase().equals("Y") || yn.toUpperCase().equals("YES")) {
    			confirmShoppingBasketPage(mList.get(STORE));	// 해당 매장의 정보를 전달
    		}
	    }
	}

	
	/*
	 * 장바구니에 메뉴 담기
	 */
	private void insertShoppingBasket(int menu_num) {    				
		// 주문 갯수 입력
		int cnt = 0;
		boolean run = true;
		while (run) {
			System.out.print("수량 > ");
			try {
				cnt = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("[SYSTEM] 잘못된 입력입니다");
				scanner.nextLine();
			}
			
			if (cnt < 1) {
				System.out.println("[SYSTEM] 최소 주문 수량은 1개 이상입니다");
			} else {
				run = false;
			}
		}
  	
    	// 장바구니에 저장
    	boolean inBaskets = false; 
    	
    	// 주문하려는 메뉴가 이미 장바구니에 존재하는지 확인
    	for(HashMap<String, Integer> list : shoppingBasketLists) {
			if (list.get("menu_num") == menu_num) {							// 동일한 메뉴를 중복하여 주문하는 경우
				list.replace("menu_count", list.get("menu_count") + cnt);	// 갯수만 추가
				inBaskets = true;
				break;
			}
		}
    	
    	// 장바구니에 없는 경우
    	if (!inBaskets) {
    		HashMap<String, Integer> map = new HashMap<>();
    		map.put("menu_num", menu_num);
        	map.put("menu_count", cnt);
        	shoppingBasketLists.add(map);
		}
    }
	
	
	/*
	 * 장바구니 확인 페이지
	 */
	private void confirmShoppingBasketPage(HashMap<String, Object> store) {
		System.out.println("============ [ 장바구니 ] ============");
		
		int total_price = 0;	// 총 금액
		
		// 저장된 음식 리스트 출력
		for (int i = 0; i < shoppingBasketLists.size() ; i++) {
			int menu_num = shoppingBasketLists.get(i).get("menu_num");		// 메뉴번호
			int menu_count = shoppingBasketLists.get(i).get("menu_count");	// 메뉴 갯수
		
			Menu menu = orderMgr.printShoppingBasket(menu_num);
			
			int menu_total_price = (int) (menu.getMenu_price() * menu_count);	// 메뉴 별 갯수를 곱한 가격 
			
			System.out.println("이름 : " + menu.getMenu_name());
			System.out.println("갯수 : " + menu_count);
			System.out.println("가격 : " + menu_total_price + " 원");
			System.out.println("--------------------------------");
			
			shoppingBasketLists.get(i).put("total_price", menu_total_price);	// hashmap에 메뉴 별 갯수를 곱한 가격  저장
			
			total_price += menu_total_price;
		}
		
		System.out.println("총 주문 금액 : " + total_price  + " 원");
		System.out.println("--------------------------------");
		
		// 장바구니 관리
		System.out.println("0. 뒤로가기");
		System.out.println("1. 장바구니 전체 삭제");
		System.out.println("2. " + total_price + " 원 주문하기");
		System.out.println("--------------------------------");
		int key = -1;
		boolean run = true;
		while (run) {
			System.out.print("> ");
			try {
				key = scanner.nextInt();
			} catch (InputMismatchException e) {
				scanner.nextLine();
			}
			
			switch (key) {
			case 0:	// 뒤로가기
				run = false;
				break;
			case 1:	// 장바구니 전체 삭제
				shoppingBasketLists.clear();
				run = false;
				break;
			case 2:	// 주문하기
				int minOrderPrice = Integer.parseInt(String.valueOf(store.get("MINORDERPRICE")));
				if (total_price < minOrderPrice) {
					System.out.println("[SYSTEM] 배달 최소주문금액을 채워주세요");
					System.out.println("배달 최소 주문 금액 : " + minOrderPrice + " 원");
					run = false;
				} else {
					orderShoppingBasketPage(total_price, store);	// 최종 결제 페이지
					run = false;
				}
				break;
			default:
				System.out.println("[SYSTEM] 잘못된 입력입니다");
				break;
			}
		}
	}
	
	
	/*
	 * 주문(결제) 페이지
	 */
	private void orderShoppingBasketPage(int total_price, HashMap<String, Object> store) {
		scanner.nextLine();
		System.out.println("============ [ 배달정보 ] ============");
		System.out.println("배달 주소 	: " + custUserInfo.getCustomer_address());
		System.out.println("번       호 	: " + custUserInfo.getCustomer_phone());
		System.out.println("------------ [ 결제금액 ] -------------");
		System.out.println("주문금액 : " + total_price + " 원");
		System.out.println("배 달 팁  : " + store.get("DELIVERYTIP") + " 원");
		System.out.println("-----------------------------------");
		int orderTotalPrice = (total_price + Integer.parseInt(String.valueOf(store.get("DELIVERYTIP"))));
		System.out.println("총 결제금액 : " + orderTotalPrice + " 원");
		System.out.println("-----------------------------------");
		System.out.println(orderTotalPrice + " 원 주문하기 (Y / N)");
		System.out.println("-----------------------------------");
		System.out.print("> ");
		String yn = scanner.nextLine();
		
		// 최종 주문 
		if (yn.toUpperCase().equals("Y") || yn.toUpperCase().equals("YES")) {
			for(HashMap<String, Integer> list : shoppingBasketLists) {
				OrderedMenu ordered = new OrderedMenu(custUserInfo.getCustomer_num()
													, Integer.parseInt(String.valueOf(store.get("STORE_NUM")))
													, list.get("menu_num")
													, list.get("menu_count")
													, list.get("total_price")); 
				orderMgr.orderMenu(ordered);
			}
			shoppingBasketLists.clear();	// 장바구니 초기화
		} else {
			System.out.print("지금의 주문으로 다시 주문하시겠습니까 (Y / N) > ");
			yn = scanner.nextLine();
			if (yn.toUpperCase().equals("N") || yn.toUpperCase().equals("NO")) {
				shoppingBasketLists.clear();	// 장바구니 초기화
			}
		}
	}
	
	
	/*
	 *	메뉴 관리 페이지
	 */
	private void controlMenuPage() {
		boolean run = true;
		while (run) {			
			System.out.println("~~~~~~~ [ MY 매장 메뉴 관리 ] ~~~~~~~");
			// 메뉴 리스트 출력
			printMenuList(orderMgr.storeMenuPrintAll(storeUserInfo.getStore_num()));
			
			System.out.println("1. 메뉴 추가");
			System.out.println("2. 메뉴 수정");
			System.out.println("3. 메뉴 삭제");
			System.out.println("0. 뒤로가기");
			System.out.println("--------------------------------");
			int menu = -1;
			System.out.print("> ");
			try {
				menu = scanner.nextInt();
			} catch (InputMismatchException e) {
				scanner.nextLine();
			}
			
			switch (menu) {
			case 1:	// 메뉴 추가
				addMenuPage();
				break;
			case 2:	// 메뉴 수정
				updateMenuPage();
				break;
			case 3: // 메뉴 삭제
				deleteMenuPage();
				break;
			case 0:	// 뒤로가기
				run = false;
				break;
			default:
				System.out.println("[SYSTEM] 잘못된 입력입나다");
				break;
			}
		}
	}
	
	
	/*
	 * 메뉴 추가 페이지
	 */
	private void addMenuPage() {
		scanner.nextLine();
		System.out.println("~~~~~ [ 메뉴 추가 페이지 ] ~~~~~");
		
		System.out.print("[필수] 메뉴 이름 : ");
		String name = scanner.nextLine();
		
		System.out.print("[선택] 메뉴 소개 : ");
		String intro = scanner.nextLine();
		intro = intro.equals("") ? null : intro;
		
		int price = 0;
		boolean run = true;
		while (run) {
			System.out.print("[필수] 메뉴 가격 : ");
			try {
				price = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("[SYSTEM] 잘못된 입력입니다");
				scanner.nextLine();
				continue;
			}
			run = false;
		}

		
		if (orderMgr.addMenu(new Menu(storeUserInfo.getStore_num(), name, intro, price))) {
			System.out.println("[SYSTEM] 새로운 메뉴를 추가했습니다.");
		} else {
			System.out.println("[SYSTEM] 에러 발생");
		}
	}
	
	/*
	 * 메뉴 수정 페이지
	 */
	private void updateMenuPage() {
		// 수정할 메뉴 번호 전달 후 수정 작업
		int menu = -1;
		boolean run = true;
		while (run) {
			System.out.print("메뉴 번호 입력 (뒤로가기 : 0) > ");
			try {
				menu = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("[SYSTEM] 잘못된 입력입니다");
			}
			
			// 뒤로가기
			if (menu == 0) {
				return;
			}
			
			if (orderMgr.checkMenuIsInTheSelectedStore(new Menu(menu, storeUserInfo.getStore_num()))) {
				run = false;
			} else {
				System.out.println("[SYSTEM] 매장에 없는 메뉴 번호입니다");
			}
		}
		
		updateMenu(menu);
	}
	
	
	/*
	 * 매장 수정 기능
	 */
	private void updateMenu(int num) {
		scanner.nextLine();
		System.out.println("~~~~~ [ 메뉴 수정 페이지 ] ~~~~~");
		
		System.out.print("메뉴 이름 : ");
		String name = scanner.nextLine();
		name = name.equals("") ? null : name;
		
		System.out.print("메뉴 소개 : ");
		String intro = scanner.nextLine();
		intro = intro.equals("") ? null : intro;
		
		System.out.print("메뉴 가격 : ");
		String priceStr = scanner.nextLine();
		int price = priceStr.equals("") ? -1 : Integer.parseInt(priceStr);
		
		System.out.print("품절 여부(Y / N) : ");
		String soldoutStr = scanner.nextLine();
		int soldout = 0;
		
		if (soldoutStr.equals("")) {
			soldout = -1;	// 설정 유지
		} else if (soldoutStr.toUpperCase().equals("Y") || soldoutStr.toUpperCase().equals("YES")) {
			soldout = 1; 	// 품질
		} else {
			soldout = 0; 	// 판매중
		}
		
		
		if (orderMgr.updateMenu(new Menu(num, name, intro, price, soldout))) {
			System.out.println("[SYSTEM] 메뉴 정보 변경 완료");
		} else {
			System.out.println("[SYSTEM] 에러 발생");
		}
	}
	
	
	/*
	 * 메뉴 삭제 페이지
	 */
	private void deleteMenuPage() {
		int menu_num = -1;
		boolean run = true;
		while (run) {
			System.out.print("메뉴 번호 입력 (뒤로가기 : 0) > ");
			try {
				menu_num = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("[SYSTEM] 잘못된 입력입니다");
			}
			
			// 뒤로가기
			if (menu_num == 0) {
				return;
			}
			
			if (orderMgr.checkMenuIsInTheSelectedStore(new Menu(menu_num, storeUserInfo.getStore_num()))) {
				run = false;
			} else {
				System.out.println("[SYSTEM] 매장에 없는 메뉴 번호입니다");
			}
		}
		
		System.out.print("메뉴를 삭제하겠습니까? (Y / N) > ");
		String yesNo = scanner.nextLine();
		if (yesNo.toUpperCase().equals("Y") || yesNo.toUpperCase().equals("YES")) {
			if (orderMgr.deleteMenu(menu_num)) {
				System.out.println("[SYSTEM] 메뉴를 삭제했습니다");
			} else {
				System.out.println("[SYSTEM] 오류 발생");
			}
		}
	}
	
	
	/**
	 * 
	 * 주문 내역 확인 페이지
	 * 
	 * @param member_sort - STORE : 매장, CUSTOMER : 개인
	 * @param num		  - 매장 or 고객의 고유 번호 (store_num, customer_num)
	 */
	private void orderedMenuListPage(int member_sort, int num) {
		switch (member_sort) {
		case STORE:	// 매장회원의 주문 내역 확인
			orderedMenuListPageForStore(num);
			break;
		case CUSTOMER:	// 고객 회원의 주문 내역 확인
			orderedMenuListPageForCustomer(num);
			break;
		default:
			break;
		}
	}

	
	/**
	 * 주문 내역 확인 페이지 (매장회원 용)
	 * 
	 * @param num : store_num
	 */
	private void orderedMenuListPageForStore(int num) {
		ArrayList<HashMap<String, Object>> lists = orderMgr.orderedMenuListForStore(num);
		System.out.println("---------- [ 주문내역 ] ----------");
		// 주문 내역 출력
		printOrderedMenuListForStore(lists);
		boolean run = true;
		while (run) {
			System.out.println("[SYSTEM] 뒤로가기 : 0");
			System.out.print("> ");
			int input = -1;
			try {
				input = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("[SYSTEM] 잘못된 입력입니다");
				scanner.nextLine();
				continue;
			}
			
			if (input == 0) {
				run = false;
				break;
			}
		}
	}
	
	
	/*
	 * 주문 내역 확인 페이지 (고객회원 용)
	 * 
	 * @param num : customer_num
	 */
	private void orderedMenuListPageForCustomer(int num) {
		ArrayList<HashMap<String, Object>> lists = orderMgr.orderedMenuListForCustomer(num);
		System.out.println("---------- [ 주문내역 ] ----------");
		// 주문 내역 출력
		printOrderedMenuListForCustomer(lists);
		boolean run = true;
		while (run) {
			System.out.println("[SYSTEM] 뒤로가기  : 0");
			System.out.print("> ");
			int input = -1;
			try {
				input = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("[SYSTEM] 잘못된 입력입니다");
				scanner.nextLine();
				continue;
			}
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
	 * 계정 관리 Method
	 * Account
	 * 
	 *********************************************************************************************************/
	
	/*
	 * 비밀번호 변경
	 */
	private void updatePwdPage() {
		scanner.nextLine();
		System.out.println("----- [ 비밀번호 변경 ] -----");
		System.out.println("새로 변경할 비밀번호를 입력해 주세요");
		System.out.print("> ");
		String newPw = scanner.nextLine();
		System.out.println("비밀번호 확인");
		System.out.print("> ");
		String newPw2 = scanner.nextLine();

		if (newPw.equals(newPw2)) {
			loginUser.setMember_password(newPw);
			if (accuntMgr.updatePassword(loginUser)) {
				System.out.println("[SYSTEM] 비밀번호가 변경되었습니다");
			}
		} else {
			System.out.println("[SYSTEM] 비밀번호가 일치하지 않습니다");
		}
	}
	
	
	/*
	 * 로그아웃
	 */
	private boolean logOut() {
		loginUser = null;
		return false;
	}
	
	
	/*
	 * 회원 탈퇴
	 */
	private void withdrawal() {
		if( accuntMgr.memberWithdrawal(loginUser.getMember_num()) ) {
			System.out.println("[SYSTEM] 그동안 저희 프로그램을 사용해 주셔서 감사합니다.");
			loginUser = null;
		} else {
			System.out.println("[SYSTEM] 오류가 발생했습니다");
		}
		
	} 
	
	
	/*
	 * 프로그램 종료
	 */
	private void ProgramExit() {
		System.out.println("[SYSTEM] 프로그램을 종료합니다.");
		isRun = false;
		System.exit(0);
	}
	
	
	/*
	 * 지역 테이블 출력
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
	}
	
	/*
	 * 지역 테이블 입력 (회원가입용)
	 */
	private int inputArea() {
		printArea();
		int area = -1;
		boolean run = true;
		while (run) {
			System.out.print("지역번호 > ");
			try {
				area = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("[SYSTEM] 잘못된 입력입니다");
				scanner.nextLine();
				continue;
			}
			
			if (area < 1 || area > MAX_AREA_NUM) {
				System.out.println("[SYSTEM] 잘못된 입력입니다");
			} else {
				run = false;
			}
		}
		return area;
	}
	
	/*
	 * 지역 테이블 입력 (회원정보변경용)
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
	 * 업종 구분 코드 출력
	 */
	private void printStoreCode() {
		System.out.println("-------------------------------------");
		for(StoreCode code : accuntMgr.printStoreCode()) {
			System.out.println("| " + code);
		}
		System.out.println("-------------------------------------");
	}
	
	
	/*
	 * 업종 구분 코드 입력 (회원가입용)
	 */
	private int inputStoreCode() {
		printStoreCode();
		int code = -1;
		boolean run = true;
		while (run) {
			System.out.print("업종 코드 > ");
			try {
				code = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("[SYSTEM] 잘못된 입력입니다");
				scanner.nextLine();
				continue;
			}
			
			if (code < 0 || code > MAX_STORE_CODE_NUM) {
				System.out.println("[SYSTEM] 잘못된 입력입니다");
			} else {
				run = false;
			}
		}
		return code;
	}
	
	
	/*
	 * 업종 구분 코드 입력 (회원정보변경용)
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
	 * ID, PW 입력 (로그인용)
	 */
	private Member inputIdPw() {
		scanner.nextLine();
		boolean chk = true;
		Member user = null;		// 로그인 정보 return
		Member chkId = null;	// 계정 존재 확인용
		
		String id = null, pw = null;
		
		while(chk) {
			boolean run = true;
			while (run) {
				System.out.print("ID : ");
				id = scanner.nextLine();
				if (id.length() > 20) {
					System.out.println("[SYSTEM] id는 최대 20 자 입니다");
				} else {
					run = false;
				}
			}
			
			run = true;
			while (run) {
				System.out.print("PW : ");
				pw = scanner.nextLine();
				if (pw.length() > 20) {
					System.out.println("[SYSTEM] 패스워드는 최대 20 자 입니다");
				} else {
					run = false;
				}
			}
			
			// id가 table에 존재하는지 확인
			chkId = accuntMgr.checkDuplicateAccount(id);
			if (chkId == null) {
				System.out.println("[SYSTEM] 존재하지 않는 아이디 입니다");
				continue;
			}
			
			// 입력한 id 의 pw 와 입력한 pw 값이 일치하는지 확인
			if (pw.equals(chkId.getMember_password())) {
				user = accuntMgr.login(new Member(id, pw));
				chk = false;
			} else {
				System.out.println("[SYSTEM] 비밀번호를 잘못 입력하셨습니다");
				continue;
			}
		}
		return user;
	}
	
	
	/*
	 * ID, PW 입력 (회원가입용)
	 */
	private Member inputIdPw(int sort) {
		scanner.nextLine();
		String signUpID = null;	// 가입할 ID
		String signUpPW = null;	// 가입할 PW
		
		// ID 중복 체크
		boolean chk = true;
		while (chk) {
			boolean run = true;
			while (run) {
				System.out.print("ID : ");
				signUpID = scanner.nextLine();
				if (signUpID.length() > 20) {
					System.out.println("[SYSTEM] id는 최대 20 자 입니다");
				} else {
					run = false;
				}
			}
	
			if (accuntMgr.checkDuplicateAccount(signUpID) == null) {
				chk = false;
			} else {
				System.out.println("[SYSTEM] 이미 존재하는 ID 입니다");
			}
		}
		
		// 비밀번호 일치 확인
		chk = true;
		while (chk) {
			boolean run = true;
			while (run) {
				System.out.print("PW : ");
				signUpPW = scanner.nextLine();
				if (signUpPW.length() > 20) {
					System.out.println("[SYSTEM] PW는 최대 20 자 입니다");
				} else {
					run = false;
				}
			}
			
			System.out.print("PW 재입력 : ");
			String signUpPWChk = scanner.nextLine();
			
			if (signUpPW.equals(signUpPWChk)) {
				chk = false;
			} else {
				System.out.println("[SYSTEM] 비밀번호가 일치하지 않습니다");
			}
		}
		
		return new Member(signUpID, signUpPW, sort);	
	}
	
	
	/*
	 * 기업 회원 정보 입력
	 */
	private void inputStoreInfo() {	
		// ID & PW 입력
		Member member = inputIdPw(STORE);
		
		String storeName = "", storePhone = "";
		int minOrderPrice = 0, deliveryTime = 0, deliveryTip = 0;
		
		boolean chk = true;
		while(chk) {
			// 지역 입력
			int areaNum = inputArea();
			
			// 업종 종류 출력 & 입력
			int codeNum = inputStoreCode();
			scanner.nextLine();
			
			// 상세 정보 입력
			// 매장 이름
			boolean run = true;
			while (run) {
				System.out.print("매장명 : ");
				storeName = scanner.nextLine();
				if (storeName.equals("")) {
					System.out.println("[SYSTEM] 매장명을 입력해주세요");
				} else if (storeName.length() > 25) {
					System.out.println("[SYSTEM] 최대길이는 25자 입니다 (한글 기준)");
				} else {
					run = false;
				}
			}
			
			// 매장 전화번호
			run = true;
			while (run) {
				System.out.print("매장 전화번호 : ");
				storePhone = scanner.nextLine();
				if (storePhone.equals("")) {
					System.out.println("[SYSTEM] 매장 전화번호를 입력해주세요");
				} else if (storePhone.length() > 15) {
					System.out.println("[SYSTEM] 최대길이는 15자 입니다");
				} else {
					run = false;
				}
			}	
			
			// 최소주문금액
			run = true;
			while (run) {
				System.out.print("최소주문금액 : ");
				try {
					minOrderPrice = scanner.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("[SYSTEM] 잘못된 입력입니다");
					scanner.nextLine();
					continue;
				}
				
				if (String.valueOf(minOrderPrice).length() > 20) {
					System.out.println("[SYSTEM] 최대길이는 20자 입니다");
				} else {
					run = false;
				}
			}	
			
			// 배달시간
			run = true;
			while (run) {
				System.out.print("배달시간 : ");
				try {
					deliveryTime = scanner.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("[SYSTEM] 잘못된 입력입니다");
					scanner.nextLine();
					continue;
				}
				
				if (String.valueOf(deliveryTime).length() > 20) {
					System.out.println("[SYSTEM] 최대길이는 20자 입니다");
				} else {
					run = false;
				}
			}
			
			// 배달팁
			run = true;
			while (run) {
				System.out.print("배달팁 : ");
				try {
					deliveryTip = scanner.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("[SYSTEM] 잘못된 입력입니다");
					scanner.nextLine();
					continue;
				}
				
				if (String.valueOf(deliveryTip).length() > 20) {
					System.out.println("[SYSTEM] 최대길이는 20자 입니다");
				} else {
					run = false;
				}
			}
			
			if (accuntMgr.signUp(member)) { 
				int memberNum = accuntMgr.checkDuplicateAccount(member.getMember_id()).getMember_num();			
				Store store = new Store(memberNum, areaNum, codeNum, storeName, storePhone, minOrderPrice, deliveryTime, deliveryTip);
				
				if (accuntMgr.inputStore(store)) {
					System.out.println("[SYSTEM] 회원 가입 완료");
					chk = false;
				} else {
					System.out.println("[SYSTEM] 오류 발생");
				}
			} else {
				System.out.println("[SYSTEM] 오류 발생");
			}
		}
	}
	
	
	/**
	 * 개인 회원 정보 입력
	 */
	private void inputCustomerInfo() {
		// ID & PW 입력
		Member member = inputIdPw(CUSTOMER);
		
		String name = "", phone = "", address = "";
		
		boolean chk = true;
		while(chk) {
			// 상세 정보 입력
			boolean run = true;
			while (run) {
				System.out.print("이름 : ");
				name = scanner.nextLine();
				if (name.equals("")) {
					System.out.println("[SYSTEM] 이름을 입력해주세요");
				} else if (name.length() > 25) {
					System.out.println("[SYSTEM] 최대길이는 25자 입니다 (한글 기준)");
				} else {
					run = false;
				}
			}
			
			run = true;
			while (run) {
				System.out.print("전화번호 : ");
				phone = scanner.nextLine();
				if (phone.equals("")) {
					System.out.println("[SYSTEM] 전화번호를 입력해주세요");
					continue;
				} else if (phone.length() > 15) {
					System.out.println("[SYSTEM] 최대길이는 15자 입니다 (한글 기준)");
				} else {
					run = false;
				}
			}
			
			// 지역 입력
			int areaNum = inputArea();
			scanner.nextLine();
			
			run = true;
			while (run) {
				System.out.print("상세주소 : ");
				address = scanner.nextLine();
				if (address.equals("")) {
					System.out.println("[SYSTEM] 상세주소를 입력해주세요");
					continue;
				} else if (address.length() > 25) {
					System.out.println("[SYSTEM] 최대길이는 25자 입니다 (한글 기준)");
				} else {
					run = false;
				}
			}
			
			if (accuntMgr.signUp(member)) {
				int memberNum = accuntMgr.checkDuplicateAccount(member.getMember_id()).getMember_num();
				Customer customer = new Customer(memberNum, name, phone, areaNum, address);
				
				if (accuntMgr.inputCustomer(customer)) {
					System.out.println("[SYSTEM] 회원 가입 완료");
					chk = false;
				} else {
					System.out.println("[SYSTEM] 오류 발생");
				}
			} else {
				System.out.println("[SYSTEM] 오류 발생");
			}
		}
	}
	
	
	/********************************************************************************************************
	 *
	 * 매장 검색, 주문 이력 확인, 주문 기능
	 * OrderMenu
	 * 
	 *********************************************************************************************************/
	
	/*
	 * 매장 검색 기능 
	 */
	private boolean searchStore(ArrayList<Store> lists) {		
		if (lists.isEmpty()) {
			System.out.println("[SYSTEM] 텅~");
			return true;
		} else {
			// 매장 리스트 출력
			System.out.println("------------------------------------------------------------------------------");
			for(Store list : lists) {
				System.out.println(list);
				System.out.println("------------------------------------------------------------------------------");
			}
			return false;
		}
	}
	
	
	/*
	 * 매장의 메뉴 리스트 출력
	 */
	private void printMenuList(ArrayList<HashMap<String, Object>> storeMenuList) {
    	for (HashMap<String, Object> map : storeMenuList) {
    		if(Integer.parseInt(String.valueOf(map.get("MENU_SOLDOUT"))) == 1) {
    			continue;
    		}
    		System.out.println("매뉴번호 : " + map.get("MENU_NUM") + "\t매뉴이름 : " + map.get("MENU_NAME"));
    		System.out.println("매뉴소개 : " + ((String) map.get("MENU_INTRO") == null ? "" : map.get("MENU_INTRO")));
    		System.out.println("매뉴가격 : " + map.get("MENU_PRICE"));
    		System.out.println("--------------------------------");
    	}
	}
	
	
	/*
	 * 주문 내역 출력 (개인 회원 용)
	 */
	private void printOrderedMenuListForCustomer(ArrayList<HashMap<String, Object>> lists) {
		if (lists.isEmpty()) {
			System.out.println("[SYSTEM] 주문 내역이 없습니다");
		} else {
			// 주문 내역 비교용 hashmap
			HashMap<String, Object> tmp = lists.get(0);
			// 출력할 메뉴
			String menuList = "\n" + String.valueOf(tmp.get("MENU_NAME")) + " " + String.valueOf(tmp.get("MENU_COUNT")) + " 개\t" + String.valueOf(tmp.get("TOTAL_PRICE")) + " 원";
			// 총 주문금액
			int totalPrice = Integer.parseInt(String.valueOf(tmp.get("TOTAL_PRICE")));
			// 팁
			int tip = Integer.parseInt(String.valueOf(tmp.get("DELIVERYTIP")));
			
			for (int i = 1; i < lists.size(); i++) {
				// 동일한 시간대 주문(= 같은 매장) 일 경우
				if (tmp.get("ORDERED_TIME").equals(lists.get(i).get("ORDERED_TIME"))) {
					// 출력할 메뉴 정보 추가
					menuList += "\n" + String.valueOf(lists.get(i).get("MENU_NAME")) + " " + String.valueOf(lists.get(i).get("MENU_COUNT")) + " 개\t" + String.valueOf(lists.get(i).get("TOTAL_PRICE")) + " 원";
					// 총 주문금액 추가
					totalPrice += Integer.parseInt(String.valueOf(lists.get(i).get("TOTAL_PRICE")));
				} else {
					// 주문 내역 출력
					printOrderedForCustomer(tmp, menuList, totalPrice, tip);
					
					// 새로운 주문 내역 저장을 위한 초기화
					tmp = lists.get(i);
					// 출력할 메뉴
					menuList = "\n" + String.valueOf(tmp.get("MENU_NAME")) + " " + String.valueOf(tmp.get("MENU_COUNT")) + " 개\t" + String.valueOf(tmp.get("TOTAL_PRICE")) + " 원";
					// 총 주문금액
					totalPrice = (Integer.parseInt(String.valueOf(tmp.get("MENU_COUNT"))) * Integer.parseInt(String.valueOf(tmp.get("TOTAL_PRICE"))));
					// 팁
					tip = Integer.parseInt(String.valueOf(tmp.get("DELIVERYTIP")));
				}
				
				// 마지막 주문 내역일 경우 
				if (i == lists.size() - 1) {
					printOrderedForCustomer(tmp, menuList, totalPrice, tip);
				}
			}
			
		}
	}
	
	
	/*
	 * 주문 내역 출력 (매장 회원 용)
	 */
	private void printOrderedMenuListForStore(ArrayList<HashMap<String, Object>> lists) {
		if (lists.isEmpty()) {
			System.out.println("[SYSTEM] 주문 내역이 없습니다");
		} else {
			// 주문 내역 비교용 hashmap
			HashMap<String, Object> tmp = lists.get(0);
			// 출력할 메뉴
			String menuList = "\n" + String.valueOf(tmp.get("MENU_NAME")) + " " + String.valueOf(tmp.get("MENU_COUNT")) + " 개\t" + String.valueOf(tmp.get("TOTAL_PRICE")) + " 원";
			// 총 주문금액
			int totalPrice = Integer.parseInt(String.valueOf(tmp.get("TOTAL_PRICE")));
			// 팁
			int tip = storeUserInfo.getDeliveryTip();
						
			for (int i = 1; i < lists.size(); i++) {
				// 동일한 시간에 고객이 주문한 경우
				if (tmp.get("ORDERED_TIME").equals(lists.get(i).get("ORDERED_TIME")) && tmp.get("CUSTOMER_NUM").equals(lists.get(i).get("CUSTOMER_NUM"))) {
					// 출력할 메뉴 정보 추가
					menuList += "\n" + String.valueOf(lists.get(i).get("MENU_NAME")) + " " + String.valueOf(lists.get(i).get("MENU_COUNT")) + " 개\t" + String.valueOf(lists.get(i).get("TOTAL_PRICE")) + " 원";
					// 총 주문금액 추가
					totalPrice += Integer.parseInt(String.valueOf(lists.get(i).get("TOTAL_PRICE")));
				} else {
					// 주문 내역 출력
					printOrderedForStore(tmp, menuList, totalPrice, tip);
							
					// 새로운 주문 내역 저장을 위한 초기화
					tmp = lists.get(i);
					// 출력할 메뉴
					menuList = "\n" + String.valueOf(tmp.get("MENU_NAME")) + " " + String.valueOf(tmp.get("MENU_COUNT")) + " 개\t" + String.valueOf(tmp.get("TOTAL_PRICE")) + " 원";
					// 총 주문금액
					totalPrice = (Integer.parseInt(String.valueOf(tmp.get("MENU_COUNT"))) * Integer.parseInt(String.valueOf(tmp.get("TOTAL_PRICE"))));
				}
							
				// 마지막 주문 내역일 경우 
				if (i == lists.size() - 1) {
					printOrderedForStore(tmp, menuList, totalPrice, tip);
				}
			}
		}
	}
	
	
	/*
	 * 주문 내역 출력 양식
	 */
	private void PrintOrderedMenu(String menuList, int totalPrice, int tip) {
		System.out.println("-------------------------------");
		System.out.println(menuList);
		System.out.println("-------------------------------");
		System.out.println("총주문금액 : " + totalPrice + " 원");
		System.out.println("배   달   팁 : " + tip + " 원");
		System.out.println("-------------------------------");
		System.out.println("총결제금액 : " + (totalPrice + tip) + " 원");
		System.out.println("===============================");
	}
	
	/**
	 * 주문 내역 출력문 (개인 회원 용)
	 */
	private void printOrderedForCustomer(HashMap<String, Object> tmp, String menuList, int totalPrice, int tip) {
		System.out.println("주문일시 : " + tmp.get("ORDERED_TIME"));
		System.out.println("매      장 : " + tmp.get("STORE_NAME"));
		PrintOrderedMenu(menuList, totalPrice, tip);
	}
	
	/**
	 * 주문 내역 출력문 (매장 회원 용)
	 */
	private void printOrderedForStore(HashMap<String, Object> tmp, String menuList, int totalPrice, int tip) {
		System.out.println("주문 일시  	 : " + tmp.get("ORDERED_TIME"));
		System.out.println("고객명   	 : " + tmp.get("CUSTOMER_NAME"));
		System.out.println("고객 전화번호 : " + tmp.get("CUSTOMER_PHONE"));
		System.out.println("고객 주소 	 : " + tmp.get("CUSTOMER_ADDRESS"));
		PrintOrderedMenu(menuList, totalPrice, tip);
	}
}

