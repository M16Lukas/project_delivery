package ui;


import java.util.ArrayList;
import java.util.Scanner;

import mgr.AccountManager;
import mgr.OrderMenuManager;
import vo.Area;
import vo.Customer;
import vo.Member;
import vo.Menu;
import vo.Store;
import vo.StoreCode;

public class DeliveryUI{
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
	
	// field
	private boolean isRun = true;
	
	// Constructor
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
		System.out.println("===== [ 배달 프로그램 ] =====\n");
		System.out.println("1. 로그인\n");
		System.out.println("2. 회원가입\n");
		System.out.println("0. 종료\n");
		System.out.println("========================\n");
		System.out.print("> ");
		return scanner.nextInt();
	}
	
	
	/*
	 * 회원가입 페이지
	 */
	private void signUpPage(){
		scanner.nextLine();
		System.out.println("----- [ 회원가입 ] -----");
		System.out.println("1. 기업회원");
		System.out.println("2. 개인회원");
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
		default:
			break;
		}
	}
	
	
	/*
	 * 기업 회원 메인 페이지
	 */
	private void storeMainPage() {
		storeUserInfo = accuntMgr.getStoreInfo(loginUser.getMember_num());
		boolean run = true;
		while(run) {
			System.out.println("============ [ SCIT 배달 ] ============");
			System.out.println("1. 주문 이력 확인");
			System.out.println("2. 메뉴 관리");
			System.out.println("3. my정보");
			System.out.print("> ");
			int menu = scanner.nextInt();
			switch (menu) {
			case 1:	// 주문 이력 확인
				break;
			case 2:	// 메뉴 관리
				controlMenuPage();
				break;
			case 3:	// my정보
				userInfoPage();
				break;
			default:
				run = false;
				break;
			}
		}
	}
	
	
	/*
	 * 개인 회원 메인 페이지
	 */
	private void customerMainPage() {
		custUserInfo = accuntMgr.getCustomerInfo(loginUser.getMember_num());
		boolean run = true;
		while(run) {
			System.out.println("============ [ SCIT 배달 ] ============");
			System.out.println("1. 매장 검색");
			System.out.println("2. 카테고리(업종)");
			System.out.println("3. 주문내역");
			System.out.println("4. my정보");
			System.out.print("> ");
			int menu = scanner.nextInt();
			switch (menu) {
			case 1:	// 매장 검색
				searchStorePage();
				break;
			case 2:	// 카테고리(업종)
				storeCategoryPage();
				break;
			case 3:	// 주문내역
				break;
			case 4:	// my정보
				run = userInfoPage();
				break;
			default:
				break;
			}
		}
	}
	
	
	/*
	 * 로그인 계정 관리 페이지
	 */
	private boolean userInfoPage() {
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
			System.out.print("> ");
			int menu = scanner.nextInt();
			switch (menu) {
			case 1:	// 계정 정보 수정
				updateInfoPage();
				break;
			case 2:	// 로그아웃
				logOut();
				run = false;
				break;
			case 3:	// 회원탈퇴
				withdrawalPage();
				run = false;
				break;
			case 0:	// 뒤로가기
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
	 * 계정정보 수정
	 */
	private void updateInfoPage() {
		scanner.nextLine();
		boolean run = true;
		while(run) {
			System.out.println("----- [ 계정정보 수정 ] -----");
			System.out.println("1. 비밀번호 변경");
			System.out.println("2. 상세정보 변경");
			System.out.println("0. 뒤로가기");
			System.out.print("> ");
			int menu = scanner.nextInt();
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
				break;
			}
		}
	}
	
	
	/*
	 * 개인 회원 정보 수정 페이지
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
		int code = printStoreCodeForUpdate();
		
		// 지역 번호 수정
		int area = printAreaForUpdate();
		
		System.out.print("주문 최소 금액 : ");
		String minOrderPriceStr = scanner.nextLine();
		int minOrderPrice = minOrderPriceStr.equals("") ? 0 : Integer.parseInt(minOrderPriceStr);
		
		System.out.print("배달 시간 : ");
		String deliveryTimeStr = scanner.nextLine();
		int deliveryTime = deliveryTimeStr.equals("") ? 0 : Integer.parseInt(deliveryTimeStr);
		
		System.out.print("배달팁 : ");
		String deliveryTipStr = scanner.nextLine();
		int deliveryTip = deliveryTipStr.equals("") ? 0 : Integer.parseInt(deliveryTipStr);
		
		
		
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
		int area = printAreaForUpdate();
		
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
	private void withdrawalPage() {
		scanner.nextLine();
		System.out.println("************ 회원탈퇴 *************");
		System.out.println("회원탈퇴를 원하시는 경우 비밀번호를 입력해 주세요");
		System.out.print("> ");
		String pw = scanner.nextLine();
		
		if (loginUser.getMember_password().equals(pw)) {
			System.out.println("-------------- [주의] -------------");
			System.out.println("| 회원탈퇴 시 저장된 정보들이 전부 삭제됩니다     |");
			System.out.println("| 탈퇴를 원하시면 Y를 입력해주세요  [Y / N] |");
			System.out.println("----------------------------------");
			System.out.print("> ");
			String yesOrNo = scanner.nextLine();
			
			if (yesOrNo.toUpperCase().equals("Y") || yesOrNo.toUpperCase().equals("YES")) {
				withdrawal();// 회원탈퇴 기능
			} else {
				System.out.println("[SYSTEM] 탈퇴를 취소합니다");
			}
		} else {
			System.out.println("[SYSTEM] 비밀번호가 일치하지 않습니다");
		}
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
		searchStore(scanner.nextLine());
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
			System.out.println(" 전체 매장 검색은 0 을 입력 ");
			codeNum = printStoreCode();
			if (codeNum < 0 || codeNum > MAX_STORE_CODE_NUM) {
				System.out.println("[SYSTEM] 잘못된 입력입니다");
				continue;
			}
			
			// 매장 정렬 기준 선택
			sort = printStoreSortMethod();
			if (sort < 0 || sort > 3) {
				System.out.println("[SYSTEM] 잘못된 입력입니다");
				continue;
			}
			
			// 추가 조건(최소주문금액) 추가
			minPrice = minOrderPriceList();
			run = false;
		}
		
		// 카테고리 조건 & 정렬방식이 적용된 select 결과
		ArrayList<Store> lists = orderMgr.printStoreByCategory(custUserInfo.getArea_num(), codeNum, sort, minPrice);
		
		
		if (lists.isEmpty()) {
			System.out.println("[SYSTEM] 매장이 텅~");
		} else {
			for(Store list : lists) {
				System.out.println(list);
			}
			System.out.println("[SYSTEM] 뒤로가기 시 0 을 입력해주세요");
			System.out.print("> ");
			int back = scanner.nextInt();
			
		}
	}
	
	
	/*
	 * 매장 정렬 방법 출력
	 */
	private int printStoreSortMethod() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("#### [ 정렬 방법 ] ####");
		System.out.println("1. 기본순");
		System.out.println("2. 배달빠른 순");
		System.out.println("3. 배달팁 낮은 순");
		System.out.print("> ");
		return scanner.nextInt();
	}
	
	
	/*
	 * 최소 주문 금액 출력
	 */
	private int minOrderPriceList() {
		System.out.println("#### [ 최소주문금액 ] ####");
		System.out.println("0) 설정안함");
		System.out.println("1) 5,000원 이하");
		System.out.println("2) 10,000원 이하");
		System.out.println("3) 12,000원 이하");
		System.out.println("4) 15,000원 이하");
		System.out.println("5) 20,000원 이하");
		System.out.println("> ");
		return scanner.nextInt();
	}
	
	
	/*
	 *	메뉴 관리 페이지
	 */
	private void controlMenuPage() {
		boolean run = true;
		while (run) {
			System.out.println("~~~~~ [ MY 매장 메뉴 관리 ] ~~~~~");
			System.out.println("1. 메뉴 추가");
			System.out.println("2. 메뉴 수정");
			System.out.println("0. 뒤로가기");
			System.out.print("> ");
			int menu = scanner.nextInt();
			switch (menu) {
			case 1:	// 메뉴 추가
				addMenuPage();
				break;
			case 2:	// 메뉴 수정
				break;
			case 0:	// 뒤로가기
				run = false;
				break;
			default:
				break;
			}
		}
	}
	
	
	/*
	 * 메뉴 추가 페이지
	 */
	private void addMenuPage() {
		scanner.nextLine();
		System.out.println("~~~~~ [ 메뉴 추가 ] ~~~~~");
		
		System.out.print("[필수] 메뉴 이름 : ");
		String name = scanner.nextLine();
		
		System.out.print("[선택] 메뉴 소개 : ");
		String intro = scanner.nextLine();
		intro = intro.equals("") ? null : intro;
		
		System.out.print("[필수] 메뉴 가격 : ");
		int price = scanner.nextInt();
		
		if (orderMgr.addMenu(new Menu(storeUserInfo.getStore_num(), name, intro, price))) {
			System.out.println("[SYSTEM] 새로운 메뉴를 추가했습니다.");
		} else {
			System.out.println("[SYSTEM] 에러 발생");
		}
	}
	
	/*
	 * 메뉴 수정
	 */
	
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
	 * 지역 테이블 출력 (회원가입용)
	 */
	private int printArea() {
		for(Area area : accuntMgr.printArea()) {
			System.out.println(area);
		}
		System.out.print("> ");
		return scanner.nextInt();
	}
	
	/*
	 * 지역 테이블 출력 (회원정보변경용)
	 */
	private int printAreaForUpdate() {
		for(Area area : accuntMgr.printArea()) {
			System.out.println(area);
		}
		System.out.print("> ");
		String areaNum = scanner.nextLine();
		
		if (areaNum.equals("")) {
			return 0;
		} else {
			return Integer.parseInt(areaNum);
		}
	}

	
	/*
	 * 업종 구분 코드 출력 (회원가입용)
	 */
	private int printStoreCode() {
		for(StoreCode code : accuntMgr.printStoreCode()) {
			System.out.println(code);
		}
		System.out.print("> ");
		return scanner.nextInt();
	}
	
	/*
	 * 업종 구분 코드 출력 (회원정보변경용)
	 */
	private int printStoreCodeForUpdate() {
		for(StoreCode code : accuntMgr.printStoreCode()) {
			System.out.println(code);
		}
		System.out.print("> ");
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
		
		while(chk) {
			System.out.print("ID : ");
			String id = scanner.nextLine();
			
			System.out.print("PW : ");
			String pw = scanner.nextLine();	
			
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
			System.out.print("ID : ");
			signUpID = scanner.nextLine();
	
			if (accuntMgr.checkDuplicateAccount(signUpID) == null) {
				chk = false;
			} else {
				System.out.println("[SYSTEM] 이미 존재하는 ID 입니다");
			}
		}
		
		// 비밀번호 일치 확인
		chk = true;
		while (chk) {
			System.out.print("PW : ");
			signUpPW = scanner.nextLine();
			
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
		boolean chk = true;
		while(chk) {
			// ID & PW 입력
			Member member = inputIdPw(STORE);
			
			// 지역 입력
			int areaNum = printArea();
			
			// 업종 종류 출력 & 입력
			int codeNum = printStoreCode();
			scanner.nextLine();
			
			// 상세 정보 입력
			// 매장 이름
			System.out.print("매장명 : ");
			String storeName = scanner.nextLine();
			
			// 매장 전화번호
			System.out.print("매장 전화번호 : ");
			String storePhone = scanner.nextLine();
			
			// 최소주문금액
			System.out.print("최소주문금액 : ");
			int minOrderPrice = scanner.nextInt();
			
			// 배달시간
			System.out.print("배달시간 : ");
			int deliveryTime = scanner.nextInt();
			
			// 배달팁
			System.out.print("배달팁 : ");
			int deliveryTip = scanner.nextInt();
			
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
		boolean chk = true;
		while(chk) {
			// ID & PW 입력
			Member member = inputIdPw(CUSTOMER);
			
			// 상세 정보 입력
			System.out.print("이름 : ");
			String name = scanner.nextLine();
			
			System.out.print("전화번호 : ");
			String phone = scanner.nextLine();
			
			// 지역 입력
			int areaNum = printArea();
			scanner.nextLine();
			
			System.out.print("상세주소 : ");
			String address = scanner.nextLine();
			
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
	private void searchStore(String name) {
		ArrayList<Store> lists = orderMgr.searchStore(name, custUserInfo.getArea_num());
		
		if (lists.isEmpty()) {
			System.out.println("[SYSTEM] 매장이 텅~");
		} else {
			for(Store list : lists) {
				System.out.println(list);
			}
		}
	}
}
