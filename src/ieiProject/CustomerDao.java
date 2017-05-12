package ieiProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {
	ConnectionMaker connectionMaker = new ConnectionLink();
	public CustomerDao(){
		connectionMaker = new ConnectionLink();
	}
	public void add(Customer customer) throws ClassNotFoundException, SQLException{
		ConnectionLink c = connectionMaker.makeConnection();
		
		PreparedStatement pstmt = c.prepareStatement("insert into customer(ID,PW,TEL,NIK,EMAIL) values(?,?,?,?,?)");
		
		pstmt.setString(1, customer.getId());
		pstmt.setString(2, customer.getPw());
		pstmt.setString(3, customer.getTel());
		pstmt.setString(4, customer.getNik());
		pstmt.setString(5, customer.getEmail());
		
		pstmt.executeUpdate();
		pstmt.close();
		c.close();
			
	}
	public Customer join(String id, String pw) throws ClassNotFoundException, SQLException{
		ConnectionLink c = connectionMaker.makeConnection();
		
		PreparedStatement pstmt = c.prepareStatement("select * from customer where id=?");
		pstmt.setString(1, id);
		
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		
		Customer customer1 = new Customer();
		
		customer1.setId(rs.getString("ID"));
		customer1.setPw(rs.getString("PW"));
		customer1.setTel(rs.getString("TEL"));
		customer1.setNik(rs.getString("NIK"));
		customer1.setEmail(rs.getString("EMAIL"));
		customer1.setGrade(rs.getString("GRADE"));
		customer1.setPoint(rs.getInt("POINT"));
		
		if(pw == customer1.getPw()){
			id = customer1.getId();
			String tel = customer1.getTel();
			String nik = customer1.getNik();
			String email = customer1.getEmail();
			String grade = customer1.getGrade();
			int point = customer1.getPoint();}
		
		rs.close();
		pstmt.close();
		c.close();
	
		return customer1;
	}
	public Customer update(String ide, String pw, String phone, String name, String mail)throws ClassNotFoundException, SQLException{
		ConnectionLink c = connectionMaker.makeConnection();
		
		PreparedStatement pstmt = c.prepareStatement("update customer set tel=?, nik=?, email=? where id=?");
		pstmt.setString(1, phone);
		pstmt.setString(2, name);
		pstmt.setString(3, mail);
		pstmt.setString(4, ide);
		
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		
		Customer update = new Customer();
		update.setId(rs.getString("ID"));
		update.setPw(rs.getString("PW"));
		update.setTel(rs.getString("TEL"));
		update.setNik(rs.getString("NIK"));
		update.setEmail(rs.getString("EMAIL"));
		update.setGrade(rs.getString("GRADE"));
		update.setPoint(rs.getInt("POINT"));
		
		if(ide == update.getId()){
			String tel = update.getTel();
			String nik = update.getNik();
			String email = update.getEmail();
		}
		rs.close();
		pstmt.close();
		c.close();
		
		return update;
	}
	public void point(Customer customer)throws ClassNotFoundException, SQLException { // 고객을 추가하는 메소드, 매개변수는  Customer클래스의 객체
		ConnectionLink c = connectionMaker.makeConnection();
		
		PreparedStatement pstmt = c.prepareStatement("update customer set POINT=? where ID=?");
		
		pstmt.setString(2, customer.getId());
		
		pstmt.executeUpdate();
		pstmt.close();
		c.close();
	}

}
