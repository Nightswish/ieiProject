package ieiProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDao {
	ConnectionMaker connectionMaker = new ConnectionLink();
	public MemberDao(){
		connectionMaker = new ConnectionLink();
	}
	public void add(Member member) throws ClassNotFoundException, SQLException{
		Connection c = connectionMaker.makeConnection();
		
		PreparedStatement pstmt = c.prepareStatement("insert into customer(ID,PW,TEL,NIK,EMAIL) values(?,?,?,?,?)");
		
		pstmt.setString(1, member.getId());
		pstmt.setString(2, member.getPw());
		pstmt.setString(3, member.getTel());
		pstmt.setString(4, member.getNik());
		pstmt.setString(5, member.getEmail());
		
		pstmt.executeUpdate();
		pstmt.close();
		c.close();
			
	}
	public Member join(String id, String pw) throws ClassNotFoundException, SQLException{
		Connection c = connectionMaker.makeConnection();
		
		PreparedStatement pstmt = c.prepareStatement("select * from customer where id=?");
		pstmt.setString(1, id);
		
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		
		Member member1 = new Member();
		
		member1.setId(rs.getString("ID"));
		member1.setPw(rs.getString("PW"));
		member1.setTel(rs.getString("TEL"));
		member1.setNik(rs.getString("NIK"));
		member1.setEmail(rs.getString("EMAIL"));
		member1.setGrade(rs.getString("GRADE"));
		member1.setPoint(rs.getInt("POINT"));
		
		if(pw == member1.getPw()){
			id = member1.getId();
			String tel = member1.getTel();
			String nik = member1.getNik();
			String email = member1.getEmail();
			String grade = member1.getGrade();
			int point = member1.getPoint();}
		
		rs.close();
		pstmt.close();
		c.close();
	
		return member1;
	}

}
