package ieiProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JFrame;

//Ŭ���̾�Ʈ�κ��� ���۵� ���ڿ��� �޾Ƽ� �ٸ� Ŭ���̾�Ʈ���� ���ڿ��� �����ִ� ������ 
class EchoThread extends Thread{
	Socket socket;
	Vector<Socket> vec;
	
	//�޾ƿ� ���ϰ� ���� �����ڷ� ���� 
	public EchoThread(Socket socket, Vector<Socket> vec){
		this.socket = socket;
		this.vec = vec;
	}
	
	public void run(){
		BufferedReader in = null;
		try{
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String str = null;
			
			while(true){
				//Ŭ���̾�Ʈ�κ��� ���ڿ� �ޱ� 
				str = in.readLine();
				
				//��밡 ���� ������ break
				if(str == null){
					vec.remove(socket);
					break;
				}
				
				//����� ���ϵ��� ���ؼ� �ٸ� Ŭ���̾�Ʈ���� ���ڿ� �����ֱ�
				sendMsg(str);
			}
			
		}catch(IOException ie){
			System.out.println(ie.getMessage());
		}finally{
			try{
				if(in != null)
					in.close();
				if(socket != null)
					socket.close();
			}catch(IOException ie){
				System.out.println(ie.getMessage());
			}
		}
	}
	
	//���۹��� ���ڿ� �ٸ� Ŭ���̾�Ʈ���� �����ִ� �޼ҵ� 
	public void sendMsg(String str){
		try{
			for(Socket socket:vec){
				//������ scoket�� �����͸� ���� Ŭ���̾�Ʈ�� ��츦 �����ϰ� ������ socket�鿡�Ը� ������ ����
				if(socket != this.socket){
					PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
					pw.println(str);
					pw.flush();
				}
			}
		}catch(IOException iee){
			System.out.println(iee.getMessage());
		}
	}
}


//Ŭ���̾�Ʈ�κ��� ���۵� ���ڿ��� �޾Ƽ� �ٸ� Ŭ���̾�Ʈ���� ���ڿ��� �����ִ� �޼ҵ� 
public class FAQServer extends JFrame{
	
	public static void main(String args[]){
		ServerSocket serversocket = null;
		Socket socket = null;
		
		// ����� ���ϵ��� �迭ó�� ������ ���� ��ü ���� 
		Vector<Socket> vec = new Vector<Socket>();
		
		try{
			serversocket = new ServerSocket(3000);
			while(true){
				System.out.println("���Ӵ����..");
				socket = serversocket.accept();
				
				//Ŭ���̾�Ʈ�� ����� ������ ���Ϳ� ���
				vec.add(socket);
				
				//������ ����
				new EchoThread(socket,vec).start();
			}
		}catch(IOException ie){
			System.out.println(ie.getMessage());
		}
	}
}


