package vo;

public class Member {
	/*
	 * field
	 */
	private int member_num;
	private String member_id;
	private	String member_password;
	private int member_sort;
	private String member_signup;
	private int member_withdrawal;
	

	/*
	 * Constructor
	 */
	// 회원 가입
	public Member(String member_id, String member_password, int member_sort) {
		this.member_id = member_id;
		this.member_password = member_password;
		this.member_sort = member_sort;
	}

	public Member() {
		
	}
	
	/*
	 * Getter & Setter
	 */
	public int getMember_num() {
		return member_num;
	}

	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_password() {
		return member_password;
	}

	public void setMember_password(String member_password) {
		this.member_password = member_password;
	}

	public int getMember_sort() {
		return member_sort;
	}

	public void setMember_sort(int member_sort) {
		this.member_sort = member_sort;
	}

	public String getMember_signup() {
		return member_signup;
	}

	public void setMember_signup(String member_signup) {
		this.member_signup = member_signup;
	}

	public int getMember_withdrawal() {
		return member_withdrawal;
	}

	public void setMember_withdrawal(int member_withdrawal) {
		this.member_withdrawal = member_withdrawal;
	}
}

