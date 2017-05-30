package ieiProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
// Ű����� ���۹��ڿ� �Է¹޾� ������ �����ϴ� ������
class WriteThread{
	Socket socket;
	ClientFrame cf;
	String str;
	String id;
	public WriteThread(ClientFrame cf, String id) {
		this.cf  = cf;
		this.socket= cf.socket;
		this.id = id;
	}
	public void sendMsg() {
		//Ű����κ��� �о���� ���� ��Ʈ����ü ����
		BufferedReader br=
		new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=null;
		try{
			//������ ���ڿ� �����ϱ� ���� ��Ʈ����ü ����
			pw=new PrintWriter(socket.getOutputStream(),true);
			//ù��° �����ʹ� id �̴�. ���濡�� id�� �Բ� �� IP�� �����Ѵ�.
			str= "["+id+"] "+cf.tfFAQ.getText();
/*			if(cf.isFirst==true){
				InetAddress iaddr=socket.getLocalAddress();				
				String ip = iaddr.getHostAddress();				
				getId();
				System.out.println("ip:"+ip+"id:"+id);
				str = "["+id+"] �� �α��� ("+ip+")"; 
			}else{
				str= "["+id+"] "+cf.tfFAQ.getText();
			}*/
			//�Է¹��� ���ڿ� ������ ������
			pw.println(str);
		
		}catch(IOException ie){
			System.out.println(ie.getMessage());
		}finally{
			try{
				if(br!=null) br.close();
				//if(pw!=null) pw.close();
				//if(socket!=null) socket.close();
			}catch(IOException ie){
				System.out.println(ie.getMessage());
			}
		}
	}	
	public void getId(){
		id = Id.getId();
	}
}
//������ ������ ���ڿ��� ���۹޴� ������
class ReadThread extends Thread{
	Socket socket;
	ClientFrame cf;
	public ReadThread(Socket socket, ClientFrame cf) {
		this.cf = cf;
		this.socket=socket;
	}
	public void run() {
		BufferedReader br=null;
		try{
			//�����κ��� ���۵� ���ڿ� �о���� ���� ��Ʈ����ü ����
			br=new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			while(true){
				//�������κ��� ���ڿ� �о��
				String str=br.readLine();
				if(str==null){
					System.out.println("������ ������");
					break;
				}
				//���۹��� ���ڿ� ȭ�鿡 ���
				//System.out.println("[server] " + str);
				cf.taFAQ.append(str+"\n");
			}
		}catch(IOException ie){
			System.out.println(ie.getMessage());
		}finally{
			try{
				if(br!=null) br.close();
				if(socket!=null) socket.close();
			}catch(IOException ie){}
		}
	}
}

public class MultiChatClient {
	static String userid;
	
	public MultiChatClient(String id){
		this.userid = id;
		
		Socket socket=null;
		ClientFrame cf;
		try{
			socket=new Socket("127.0.0.1",3000);   //  localhost or 127.0.0.1 -> ���� ��ǻ��
			System.out.println("���Ἲ��!");
			cf = new ClientFrame(socket,userid);
			new ReadThread(socket, cf).start();
		}catch(IOException ie){
			System.out.println(ie.getMessage());
		}
	}
	
	
	/*//���� ���� ���̵� ���� 	
	public static void main(String[] args) {
		
		Socket socket=null;
		ClientFrame cf;
		try{
			socket=new Socket("192.168.1.101",3000);
			System.out.println("���Ἲ��!");
			cf = new ClientFrame(socket,userid);
			new ReadThread(socket, cf).start();
		}catch(IOException ie){
			System.out.println(ie.getMessage());
		}
	}*/
}

