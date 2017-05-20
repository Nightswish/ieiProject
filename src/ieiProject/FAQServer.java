package ieiProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JFrame;

//클라이언트로부터 전송된 문자열을 받아서 다른 클라이언트에게 문자열을 보내주는 스레드 
class EchoThread extends Thread{
	Socket socket;
	Vector<Socket> vec;
	
	//받아온 소켓과 벡터 생성자로 생성 
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
				//클라이언트로부터 문자열 받기 
				str = in.readLine();
				
				//상대가 접속 끊으면 break
				if(str == null){
					vec.remove(socket);
					break;
				}
				
				//연결된 소켓들을 통해서 다른 클라이언트에게 문자열 보내주기
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
	
	//전송받은 문자열 다른 클라이언트에게 보내주는 메소드 
	public void sendMsg(String str){
		try{
			for(Socket socket:vec){
				//현재의 scoket이 데이터를 보낸 클라이언트의 경우를 제외하고 나머지 socket들에게만 데이터 보냄
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


//클라이언트로부터 전송된 문자열을 받아서 다른 클라이언트에게 문자열을 보내주는 메소드 
public class FAQServer extends JFrame{
	
	public static void main(String args[]){
		ServerSocket serversocket = null;
		Socket socket = null;
		
		// 연결된 소켓들을 배열처럼 저장할 벡터 객체 생성 
		Vector<Socket> vec = new Vector<Socket>();
		
		try{
			serversocket = new ServerSocket(3000);
			while(true){
				System.out.println("접속대기중..");
				socket = serversocket.accept();
				
				//클라이언트와 연결된 소켓을 벡터에 담기
				vec.add(socket);
				
				//스레드 구동
				new EchoThread(socket,vec).start();
			}
		}catch(IOException ie){
			System.out.println(ie.getMessage());
		}
	}
}


