package ieiProject;

public class Member {
	private String id;
	private String pw;
	private String tel;
	private String nik;
	private String email;
	private String grade;
	private int point;
	
	public Member(String id, String pw,String tel, String nik,String email){
		this.id = id;
		this.pw = pw;
		this.tel = tel;
		this.nik = nik;
		this.email = email;
	}
	public Member(){}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getNik() {
		return nik;
	}
	public void setNik(String nik) {
		this.nik = nik;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
	
}
