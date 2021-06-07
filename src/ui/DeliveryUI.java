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
	
	private Member loginUser = null;	// 로그인한 계정 정보 저장용 객체
	
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
	
	/*******************
	 * 
	 * Page Group
	 * 
	 ******************/
	// 시작 페이지
	public int BeginPage(){
		System.out.println("===== [ 배달 프로그램TEST ] =====\n");
		System.out.println("1. 로그인\n");
		System.out.println("2. 회원가입\n");
		System.out.println("0. 종료\n");
		System.out.println("========================\n");
		System.out.print("> ");
		return scanner.nextInt();
	}
	
	
	// 로그인 페이지
	public void loginPage() {
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
	
	
	// 회원가입 페이지
	public void signUpPage(){
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
	
	
	// 기업 회원 메인 페이지
	public void storeMainPage() {
		System.out.println("~~~~~ [ SCIT 배달 ] ~~~~~");
		System.out.println("1. 주문 이력 확인");
		System.out.println("2. 메뉴 관리");
		System.out.println("3. 매장정보관리");
		System.out.print("> ");
		int menu = scanner.nextInt();
		switch (menu) {
		case 1:	// 주문 이력 확인
			break;
		case 2:	// 메뉴 관리
			break;
		case 3:	// 매장정보관리
			break;
		default:
			break;
		}
	}
	
	
	// 개인 회원 메인 페이지
	public void customerMainPage() {
		System.out.println("~~~~~ [ SCIT 배달 ] ~~~~~");
		System.out.println("1. 매장 검색");
		System.out.println("2. 카테고리");
		System.out.println("3. 주문내역");
		System.out.println("4. my정보");
		System.out.print("> ");
		int menu = scanner.nextInt();
		switch (menu) {
		case 1:	// 매장 검색
			break;
		case 2:	// 카테고리
			break;
		case 3:	// 주문내역
			break;
		case 4:	// my정보
			userInfoPage();
			break;
		default:
			break;
		}
	}
	
	
	/*
	 * 고객 정보 페이지
	 */
	public void userInfoPage() {
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
			break;
		case 2:	// 로그아웃
			logOut();
			break;
		case 3:	// 회원탈퇴
			withdrawalPage();
			break;
		case 0:	// 뒤로가기
			customerMainPage();
			break;
		default:
			break;
		}
	}
	
	
	/*
	 * 회원탈퇴 페이지
	 */
	public void withdrawalPage() {
		scanner.nextLine();
		System.out.println("************ 회원탈퇴 *************");
		System.out.println("회원탈퇴를 원하시는 경우 비밀번호를 입력해 주세요");
		System.out.print("> ");
		String pw = scanner.nextLine();
		if (loginUser.getMember_num() == mgr.checkDuplicateAccount(2, pw)) {
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
				userInfoPage();
			}
		} else {
			System.out.println("[SYSTEM] 비밀번호가 일치하지 않습니다");
			userInfoPage();
		}
	} 
	
	
	/*******************
	 * 
	 * Method Group
	 * 
	 ******************/
	
	/*
	 * 로그아웃
	 */
	public void logOut() {
		loginUser = null;
		loginPage();
	}
	
	
	/*
	 * 회원 탈퇴
	 */
	public void withdrawal() {
		if( mgr.memberWithdrawal(loginUser.getMember_num()) ) {
			System.out.println("[SYSTEM] 그동안 저희 프로그램을 사용해 주셔서 감사합니다.");
			loginUser = null;
		} else {
			System.out.println("[SYSTEM] 오류가 발생했습니다");
		}
	} 
	
	
	/*
	 * 프로그램 종료
	 */
	public void ProgramExit() {
		System.out.println("[SYSTEM] 프로그램을 종료합니다.");
		isRun = false;
		System.exit(0);
	}
	
	
	/*
	 * 지역 테이블 출력
	 */
	public int printArea() {
		for(Area area : mgr.printArea()) {
			System.out.println(area);
		}
		System.out.print("> ");
		return scanner.nextInt();
	}

	
	/*
	 * 업종 구분 코드 출력
	 */
	public int printStoreCode() {
		for(StoreCode code : mgr.printStoreCode()) {
			System.out.println(code);
		}
		System.out.print("> ");
		return scanner.nextInt();
	}
	
	
	/*
	 * ID, PW 입력 (로그인용)
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
				System.out.println("[SYSTEM] 존재하지 않은 아이디 입니다");
				continue;
			}
			
			int chkPw = mgr.checkDuplicateAccount(2, pw); 
			if (chkPw != chkId) {
				System.out.println("[SYSTEM] 비밀번호를 잘못 입력하셨습니다");
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
	 * ID, PW 입력 (회원가입용)
	 */
	public Member inputIdPw(int sort) {
		scanner.nextLine();
		String signUpID = null;
		
		// ID 중복 체크
		boolean chk = true;
		while (chk) {
			System.out.print("ID : ");
			signUpID = scanner.nextLine();
	
			if (mgr.checkDuplicateAccount(1, signUpID) == 0) {
				chk = false;
			} else {
				System.out.println("[SYSTEM] 이미 존재하는 ID 입니다");
			}
		}
		
		System.out.print("PW : ");
		String signUpPW = scanner.nextLine();
		return new Member(signUpID, signUpPW, sort);	
	}
	
	
	/*
	 * 기업 회원 정보 입력
	 */
	public void inputStoreInfo() {		
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
		
		if (mgr.signUp(member)) {
			int memberNum = mgr.checkDuplicateAccount(1, member.getMember_id());
			Store store = new Store(memberNum, areaNum, codeNum, storeName, storePhone, minOrderPrice, deliveryTime, deliveryTip);
			
			if (mgr.inputStore(store)) {
				System.out.println("[SYSTEM] 회원 가입 완료");
				BeginPage();	// 회원 가입 완료 후 로그인 페이지로 돌아가기
			} else {
				System.out.println("[SYSTEM] 오류 발생");
			}
		} else {
			System.out.println("[SYSTEM] 오류 발생");
		}
	}
	
	
	/**
	 * 개인 회원 정보 입력
	 */
	public void inputCustomerInfo() {
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
		
		if (mgr.signUp(member)) {
			int memberNum = mgr.checkDuplicateAccount(1, member.getMember_id());
			Customer customer = new Customer(memberNum, name, phone, areaNum, address);
			if (mgr.inputCustomer(customer)) {
				System.out.println("[SYSTEM] 회원 가입 완료");
				BeginPage();	// 회원 가입 완료 후 로그인 페이지로 돌아가기
			} else {
				System.out.println("[SYSTEM] 오류 발생");
			}
		} else {
			System.out.println("[SYSTEM] 오류 발생");
		}
	}
	
}
