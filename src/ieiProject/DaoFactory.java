package ieiProject;


public class DaoFactory {
	public MemberDao memberDao() { // MemberDao ����
		MemberDao memberDao = new MemberDao(); // userDao Ŭ������ ��ü ���� 
		return memberDao; // ����
	}
	/*public LoginDao loginDao(){
		LoginDao loginDao = new LoginDao();
		return loginDao;
	}*/
}
