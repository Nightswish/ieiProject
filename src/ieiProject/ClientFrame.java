package ieiProject;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Id extends JFrame implements ActionListener{
	WriteThread wt;
	ClientFrame cf;
	static String id;
	
	public Id(){}
	
	public Id(WriteThread wt, ClientFrame cf, String id){
		this.wt = wt;
		this.cf = cf;
		this.id = id;
	}
	
	public void actionPerformed(ActionEvent e){
		wt.sendMsg();
		cf.setVisible(true);
		this.dispose();
	}
	
	static public String getId(){
		return id;
	}
}


public class ClientFrame extends JFrame implements ActionListener{
	
	Container FAQcon;
	JTextArea taFAQ = new JTextArea();
	JScrollPane jpFAQ = new JScrollPane(taFAQ);
	JTextField tfFAQ = new JTextField(15);
	JDialog FAQdlg = new JDialog(this,"1:1 문의하기", false);
	JButton btnTransfer = new JButton("전송");
	JButton btnExit = new JButton("종료");

	String userid;
	
	Socket socket;
	WriteThread wt;
	
	public ClientFrame(Socket socket, String id){
		super("1:1 문의하기");
		this.socket = socket;
		this.userid = id;
		wt = new WriteThread(this,userid);
	
		FAQcon = FAQdlg.getContentPane();
		FAQdlg.setSize(400,300);
		taFAQ.setEditable(false);
		
		JPanel pl = new JPanel();
		pl.add(tfFAQ);
		pl.add(btnTransfer);
		pl.add(btnExit);
		
		FAQcon.add("Center",jpFAQ);
		FAQcon.add("South",pl);
		
		btnTransfer.addActionListener(this);
		btnExit.addActionListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		FAQdlg.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == btnTransfer){	//전송버튼 눌렀을 때 
			//메시지 입력 없이 전송 버튼만 눌렀을 경우 
			if(tfFAQ.getText().equals(""))
				return;
			
			taFAQ.append("[" + userid + "] " + tfFAQ.getText()+"\n");
			wt.sendMsg();
			tfFAQ.setText("");
		}else{
			this.dispose();
		}
	}
	
}
