package ieiProject;


public class DaoFactory {
	public CustomerDao customerDao() { // MemberDao ����
		CustomerDao customerDao = new CustomerDao(); // userDao Ŭ������ ��ü ���� 
		return customerDao; // ����
	}
	/*public LoginDao loginDao(){
		LoginDao loginDao = new LoginDao();
		return loginDao;
	}*/
}
