package ieiProject;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;

public class Test {
   public static void main(String[] ar){
   TotalTicket_sub ex = new TotalTicket_sub();
   }
}

class TotalTicket_sub extends JFrame implements ActionListener, MouseListener, KeyListener, FocusListener{
    private Container con;
    //�޴�ȭ��
    private BorderLayout bl = new BorderLayout(5, 5);
    private JButton Buyer_bt = new JButton("�����ڳ���");
    private JButton joinbt = new JButton("ȸ������");
    private JButton loginbt = new JButton("�α���");
    private JButton mypagebt = new JButton("����������");
    private JPanel MainP = new JPanel(new CardLayout());
    JPanel sp1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel sp2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    
    private JLabel lb = new JLabel("");
    private JButton logoutbt = new JButton("�α׾ƿ�");
    
    //�˻�â
    JPanel tpmain = new JPanel(new BorderLayout(3, 3));
    JPanel search = new JPanel(new BorderLayout(3, 3));
    
    private TextField searchtf = new TextField();
    private JButton btsearch = new JButton("�˻�");
    
    //�α��, ��¥��
    JPanel tklistpn = new JPanel(new GridLayout(2,1,3,3));
    JPanel tlingi = new JPanel(new BorderLayout(3, 3));
    JPanel tldate = new JPanel(new BorderLayout(3, 3));
    JLabel lbingi = new JLabel("�̴��� Best Ticket!");
    JLabel lbdate = new JLabel("��¥��");
    JList ltingi = new JList();
    JList ltdate = new JList();
    
    //Ƽ��ȭ��
    JPanel tp = new JPanel(new GridLayout(2,4,3,3));
    JScrollPane scroller = new JScrollPane(tp);
       
    JPanel mp = new JPanel(new BorderLayout(3, 3));
      
    ImageIcon home = new ImageIcon("..\\ieiProject\\image\\home2.jpg");
    ImageIcon image1 = new ImageIcon("..\\ieiProject\\image\\Ư���ù�.jpg");
    ImageIcon image2 = new ImageIcon("..\\ieiProject\\image\\�ƺ��µ�.jpg");
    ImageIcon image3 = new ImageIcon("..\\ieiProject\\image\\�г�������.jpg");
    ImageIcon image4 = new ImageIcon("..\\ieiProject\\image\\�̳�;߼�.jpg");
    ImageIcon image5 = new ImageIcon("..\\ieiProject\\image\\������.jpg");
    ImageIcon image6 = new ImageIcon("..\\ieiProject\\image\\���� �λ��̵�.jpg");
    ImageIcon image7 = new ImageIcon("..\\ieiProject\\image\\�Ǹ��������.jpg");
    ImageIcon image8 = new ImageIcon("..\\ieiProject\\image\\8����.jpg");
    ImageIcon image9 = new ImageIcon("..\\ieiProject\\image\\��ī����Ʈ.jpg");
      
    private JLabel homebt = new JLabel(home);
   
    JLabel mv1 = new JLabel("Ư���ù�  2017-04-23", image1, JLabel.CENTER);
    JLabel mv2 = new JLabel("�ƺ��µ�  2017-04-23", image2, JLabel.CENTER);
    JLabel mv3 = new JLabel("�г�������  2017-04-23", image3, JLabel.CENTER);
    JLabel mv4 = new JLabel("�̳�;߼�  2017-04-23", image4, JLabel.CENTER);
    JLabel mv5 = new JLabel("������  2017-04-23", image5, JLabel.CENTER);
    JLabel mv6 = new JLabel("���� �λ��̵�  2017-04-23", image6, JLabel.CENTER);
    JLabel mv7 = new JLabel("�Ǹ��� ����ٸ� �Դ´�  2017-04-23", image7, JLabel.CENTER);
    JLabel mv8 = new JLabel("8����  2017-04-23", image8, JLabel.CENTER);
    
    
   //ȸ������ ���̾�α�
   private Container joincon;
   private JDialog joindlg = new JDialog(this, "ȸ������", true);
   private JLabel joinidlb = new JLabel("���̵� : ", JLabel.RIGHT);
   private JLabel joinpwlb = new JLabel("��й�ȣ : ", JLabel.RIGHT);
   private JLabel joinpwoklb = new JLabel("��й�ȣ Ȯ�� : ", JLabel.RIGHT);
   private JLabel jointellb = new JLabel("��ȭ��ȣ : ", JLabel.RIGHT);
   private JLabel joinniklb = new JLabel("�г��� : ", JLabel.RIGHT);
   private JLabel joinemaillb = new JLabel("�̸��� : ", JLabel.RIGHT);
      
   private JTextField joinidtf = new JTextField(10);
   private JPasswordField joinpwtf = new JPasswordField(10);
   private JPasswordField joinpwoktf = new JPasswordField(10);
   private JTextField jointeltf = new JTextField(10);
   private JTextField joinniktf = new JTextField(10);
   private JTextField joinemailtf = new JTextField(10);
      
   private JButton joinok = new JButton("Ȯ��");
   private JButton joinx = new JButton("���");
      
   //ȸ������ �Ϸ� ���̾�α�
   private Container joinokcon;
   private JDialog joinokdlg = new JDialog(joindlg, "���ԿϷ�", true);
      
   private JLabel joinokidlb = new JLabel("���̵� : ", JLabel.RIGHT);
   private JLabel joinoktellb = new JLabel("��ȭ��ȣ : ", JLabel.RIGHT);
   private JLabel joinokniklb = new JLabel("�г��� : ", JLabel.RIGHT);
   private JLabel joinokemaillb = new JLabel("�̸��� : ", JLabel.RIGHT);
      
   private JLabel joinokidtf = new JLabel("", JLabel.LEFT);
   private JLabel joinokteltf = new JLabel("", JLabel.LEFT);
   private JLabel joinokniktf = new JLabel("", JLabel.LEFT);
   private JLabel joinokemailtf = new JLabel("", JLabel.LEFT);
      
   private JButton joinokbt  = new JButton("�Ϸ�");
      
   //ȸ������ ��� ���̾�α�
   private Container joinxcon;
   private JDialog joinxdlg = new JDialog(joindlg, "��й�ȣ ����", true);
      
   private JLabel joinxlb = new JLabel("��й�ȣ�� �Ȱ��� �Է��ϼ���");
   private JButton joinxbt = new JButton("Ȯ��");
      
   //�α��� ���̾�α�
   private Container logincon;
   private JDialog logindlg = new JDialog(this, "�α���", true);
   private JLabel loginidlb = new JLabel("���̵� : ", JLabel.RIGHT);
   private JLabel loginpwlb = new JLabel("��й�ȣ : ", JLabel.RIGHT);
      
   private JTextField loginidtf = new JTextField(10);
   private JPasswordField loginpwtf = new JPasswordField(10);
      
   private JButton loginok = new JButton("Ȯ��");
   private JButton loginx = new JButton("���");
   
   private Vector vc = new Vector();
      
   //�α��� �Ϸ� ���̾�α�
   private Container loginokcon;
   private JDialog loginokdlg = new JDialog(logindlg, "�α��� �Ϸ�", true);
      
   private JLabel loginokdlglb1 = new JLabel("");
   private JLabel loginokdlglb2 = new JLabel("�� ȯ���մϴ�.");
   private JButton loginokdlgbt = new JButton("Ȯ��");
      
   //�α��� ���� ���̾�α�
   private Container loginxcon;
   private JDialog loginxdlg = new JDialog(logindlg, "�α��� ����", true);
   private JLabel loginxdlglb = new JLabel("���̵� ��й�ȣ�� Ʋ���ϴ�!!!!"); 
   private JButton loginxdlgbt = new JButton("����ȭ��");
   
   
   // ������ ����
    private JPanel BuyerP = new JPanel(new BorderLayout(5,5));
      
    private Label  Buyerlb = new Label("�����ڳ��� Ƽ�ϸŸ�");
    private JPanel buyerjp = new JPanel(new BorderLayout(3,3));
    private JPanel tbuy = new JPanel();
    private JScrollPane jstbuy = new JScrollPane(tbuy);
    private JPanel buyer_Top_btp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JPanel buyer_Top_btp1 = new JPanel(new FlowLayout());
    private JPanel buyer_Top_btp2 = new JPanel(new FlowLayout());
    private JButton tupbt = new JButton("ǥ �ø���");
    private JButton tbuybt = new JButton("�� ��");
    
    private JPanel t1in = new JPanel(new GridLayout(1,1));
    private JPanel t1ini = new JPanel(new BorderLayout(3,3));
    private JPanel t1 = new JPanel();
    private JLabel t1lb = new JLabel(new ImageIcon("..\\ieiProject\\image\\ticket.png"));
    private JCheckBox t1check = new JCheckBox();
    private JPanel t2in = new JPanel(new GridLayout(1,1));
    private JPanel t2ini = new JPanel(new BorderLayout(3,3));
    private JPanel t2 = new JPanel();
    private JLabel t2lb = new JLabel(new ImageIcon("..\\ieiProject\\image\\ticket.png"));
    
    private JPanel t3in = new JPanel(new GridLayout(1,1));
    private JPanel t3ini = new JPanel(new BorderLayout(3,3));
    private JPanel t3 = new JPanel();
    private JLabel t3lb = new JLabel(new ImageIcon("..\\ieiProject\\image\\ticket.png"));
   

    
    //���� ��ư Ŭ�� ���� 
        private Container rsvCon;
        private JDialog rsvDlg = new JDialog(this,"���� �ϱ�",true);
        private JLabel lbName = new JLabel("���� �̸�  ");
        private JLabel lbNameDB = new JLabel("���� ���� �ܼ�Ʈ");
        private JLabel lbLoc = new JLabel("���� ���  ");
        private JLabel lbLocDB = new JLabel("kucca �溹����");
        private JLabel lbSeat = new JLabel("�¼� ��ȣ  ");
        private JLabel lbSeatDB = new JLabel("A005");
        private JLabel lbPrice = new JLabel("����  ");
        private JLabel lbPriceDB = new JLabel("50,000");
        private JLabel lbTicketNum = new JLabel("Ƽ�� ��ȣ  ");
        private JLabel lbTicketNumDB = new JLabel("201704220000001");
        private ImageIcon imgPoster = new ImageIcon("..\\ieiProject\\image\\poster.jpg"); 
        //private JLabel imgPosterLb = new JLabel(imgPoster);
        private Image originImg = imgPoster.getImage();
        private Image changedImg = originImg.getScaledInstance(150, 200, Image.SCALE_SMOOTH);
        private ImageIcon poster = new ImageIcon(changedImg);
        private JLabel imgPosterLb = new JLabel(poster);
        private JButton btnPay = new JButton("����");
        private JButton btnCancle = new JButton("���");
      
        
        
        
        //����������
        
       private GridBagLayout gridb = new GridBagLayout();
       private GridBagConstraints  constraint  =  new  GridBagConstraints();
       private JPanel mypagep= new JPanel(new FlowLayout());
       protected JTabbedPane tPane = new JTabbedPane();
       protected Panel myP = new Panel();
       protected Panel edit = new Panel(new BorderLayout());
       protected Panel pointp = new Panel(new BorderLayout());
       protected Panel show = new Panel();
        
        
        ///////////////////////////////����������â
        private Panel p4 = new Panel(new GridLayout(5,1));
        private Panel p5 = new Panel(new GridLayout(5,1));
        private Panel p4_1 = new Panel(new GridLayout(1,2));
        private Label id1= new Label("���̵� ");
        private Label id2 = new Label();
        private Panel p4_2 = new Panel(new GridLayout(1,2));
        private Label phone1= new Label("��ȭ��ȣ ");
        private Label phone2 = new Label();
        private Panel p4_3 = new Panel(new GridLayout(1,2));
        private Label nname1= new Label("�г��� ");
        private Label nname2 = new Label();
        private Panel p4_4 = new Panel(new GridLayout(1,2));
        private Label email1= new Label("�̸��� ");
        private Label email2 = new Label();
        private Panel p4_5 = new Panel(new GridLayout(1,2));
        private Label level1= new Label("��� ");
        private Label level2 = new Label();
        
        
        
        

        ////////////////////////////////����â
        private Panel p22_2 = new Panel();
        private Panel p22 = new Panel(new GridLayout(5,1));
        private Panel p2_1 = new Panel(new GridLayout(1,2));
        private Label eID= new Label("���̵� ");
        private Label eID1 = new Label();
        private Panel p2_2 = new Panel(new GridLayout(1,2));
        private Label ephone= new Label("��ȭ��ȣ ");
        private TextField tphone = new TextField(10);
        private Panel p2_3 = new Panel(new GridLayout(1,2));
        private Label ename= new Label("�г��� ");
        private TextField tname = new TextField(10);
        private Panel p2_4 = new Panel(new GridLayout(1,2));
        private Label eemail= new Label("�̸��� ");
        private TextField tmail = new TextField(10);
        private Panel p2_5 = new Panel(new GridLayout(1,2));
        private Label elevel= new Label("��� ");
        private Label elevel1 = new Label();
        private Panel p2_6_2 = new Panel(new FlowLayout());
        private Panel p2_6 = new Panel(new GridLayout(1,2));
        
        private Button check = new Button("Ȯ��");
        private Button cancel = new Button("���");
	        
	///////////////////////////////////////���� �Ϸ� ���̾�α�
	private Container updateokcon;
	private JDialog updateokdlg = new JDialog(this, "�����Ϸ�", true);
	
	private JLabel updateokidlb = new JLabel("���̵� : ", JLabel.RIGHT);
	private JLabel updateoktellb = new JLabel("��ȭ��ȣ : ", JLabel.RIGHT);
	private JLabel updateokniklb = new JLabel("�г��� : ", JLabel.RIGHT);
	private JLabel updateokemaillb = new JLabel("�̸��� : ", JLabel.RIGHT);
	
	private JLabel updateokidtf = new JLabel("", JLabel.LEFT);
	private JLabel updateokteltf = new JLabel("", JLabel.LEFT);
	private JLabel updateokniktf = new JLabel("", JLabel.LEFT);
	private JLabel updateokemailtf = new JLabel("", JLabel.LEFT);
	private JButton updateokbt = new JButton("Ȯ��");

	
        
        ////////////////////////////////////////����Ʈâ
        private Panel pointP = new Panel(new FlowLayout());
        
        private Label point = new Label("�ܿ� ����Ʈ: ");
        private Label point1 = new Label("1000(���� ��)");
        
        private Panel pointP1 = new Panel();
        private Label point2 = new Label("1000�� ����: 1100p ����");
        private Label point3 = new Label("5000�� ����: 5600p ����");
        private Label point4 = new Label("10000�� ����: 12000p ����");
        private Label point5 = new Label("50000�� ����: 65000p ����");
        
        private Panel chargeP = new Panel(new FlowLayout());
        private Button charge = new Button("����Ʈ ����");
        
     // DB ����
	 Connection conn;
	 String url ="jdbc:oracle:thin:@localhost:1521:orcl";
	 String id = "scott";
	 String pass = "tiger";
    
	// DB ȸ������
			public void joinMember(){
		 		try{
		 			Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection(url, id, pass);
					String query = "insert into member values(?,?,?,?,?,?)";
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setString(1, joinidtf.getText().trim());
					pstmt.setString(2, new String(joinpwtf.getPassword()));
					pstmt.setString(3, new String(joinpwoktf.getPassword()));
					pstmt.setString(4, jointeltf.getText().trim());
					pstmt.setString(5, joinniktf.getText().trim());
					pstmt.setString(6, joinemailtf.getText().trim());
					pstmt.executeUpdate();
					pstmt.close();
				}catch(ClassNotFoundException eee){
					
				}
		 		catch(SQLException ee){
					System.err.println("ȸ�� ���� ����!!!");
				}
			}
	
			//DB �α���
			public void loginMember(){
				try{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection(url, id, pass);
					String query="select * from member where id=? and pw=?";
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setString(1, loginidtf.getText().trim());
					pstmt.setString(2, new String(loginpwtf.getPassword()));
					
					ResultSet rs = pstmt.executeQuery();
					if(!rs.next()){
						loginxdlg.setVisible(true);
						rs.close();
						pstmt.close();
					}else{
						loginokdlglb1.setText(rs.getString("id"));
						id2.setText(rs.getString("id"));
						nname2.setText(rs.getString("nik"));
						phone2.setText(rs.getString("tel"));
						email2.setText(rs.getString("email"));
						eID1.setText(rs.getString("id"));
						tphone.setText(rs.getString("tel"));
						tmail.setText(rs.getString("email"));
						tname.setText(rs.getString("nik"));
						lb.setText(rs.getString("id") + " �� ");
						loginokdlg.setVisible(true);
						
						rs.close();
						pstmt.close();
					}
				}catch(ClassNotFoundException eee){
					
				}catch(SQLException e){
					System.err.println("�α��� ����!!!");
				}
			}
			
			// DB ����
			public void updateMember(){
				try{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection(url, id, pass);
					String query="update member set tel=?, nik=?, email=? where id=?";
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setString(1, tphone.getText().trim());
					pstmt.setString(2, tname.getText().trim());
					pstmt.setString(3, tmail.getText().trim());
					pstmt.setString(4, id2.getText().trim());
					updateokidtf.setText(eID1.getText().trim());
					updateokteltf.setText(tphone.getText().trim());
					updateokniktf.setText(tname.getText().trim());
					updateokemailtf.setText(tmail.getText().trim());
					phone2.setText(tphone.getText().trim());
					nname2.setText(tname.getText().trim());
					email2.setText(tmail.getText().trim());
					
					pstmt.executeUpdate();
					pstmt.close();
				}catch(ClassNotFoundException eee){
					
				}catch(SQLException e){
					System.err.println("���� ����!!!");
				}
				
			}
	
	
	
    public TotalTicket_sub(){
       super("����");
        this.init();
        this.start();
        this.pack();
        this.setResizable(true);
        this.setSize(1080, 820);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension di = tk.getScreenSize();
        Dimension di1 = this.getSize();
        this.setLocation((int)(di.getWidth() / 2 - di1.getWidth() / 2), 
        (int)(di.getHeight() / 2 - di1.getHeight() / 2));
        this.setVisible(true); 
    }
    
    private void start() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Buyer_bt.addActionListener(this);
        joinbt.addActionListener(this);
        loginbt.addActionListener(this);
        mypagebt.addActionListener(this);
        homebt.addMouseListener(this);
        joinok.addActionListener(this);
       joinx.addActionListener(this);
       loginok.addActionListener(this);
       loginx.addActionListener(this);
        joinokbt.addActionListener(this);
       joinxbt.addActionListener(this);
       loginokdlgbt.addActionListener(this);
       loginxdlgbt.addActionListener(this);
       logoutbt.addActionListener(this);
       mv1.addMouseListener(this);
       mv2.addMouseListener(this);
       mv3.addMouseListener(this);
       mv4.addMouseListener(this);
       mv5.addMouseListener(this);
       mv6.addMouseListener(this);
       mv7.addMouseListener(this);
       mv8.addMouseListener(this);
       btnCancle.addActionListener(this);
       check.addActionListener(this);
       cancel.addActionListener(this);
       updateokbt.addActionListener(this);

         
      }
    
    private void init() {
        //����ȭ�� ���� - ��� �޴���
        con = this.getContentPane();
        con.setLayout(bl);
        sp1.add(Buyer_bt);
        mp.add("West", sp1);
           
        sp2.add(homebt);
        sp2.add(lb);
        sp2.add(logoutbt);
        sp2.add(joinbt);
        sp2.add(loginbt);
        sp2.add(mypagebt);
        mypagebt.setVisible(false);
        lb.setVisible(false);
        logoutbt.setVisible(false);
        mp.add("East", sp2);
           
           
        //����ȭ�� ���� - �߾� Ƽ��ȭ�� ����
        tpmain.setBorder(new BevelBorder(BevelBorder.RAISED));
        
        //�˻�â(North)
        search.add("Center", searchtf);
        search.add("East", btsearch);
        
        //�α��, ��¥��(East)
        tlingi.add("North", lbingi);
        tlingi.add("Center", ltingi);
        tklistpn.add(tlingi);
        tldate.add("North", lbdate);
        tldate.add("Center", ltdate);
        tklistpn.add(tldate);
        
        //�߾��� Ƽ��ȭ�� ����(Center)
        JScrollPane jsp = new JScrollPane(tp);		//<<�̰� �۵� �ǳ���???�ȵǳ�...�ǰ���....���ּ��� �Ф�
        //tp.setBorder(new BevelBorder(BevelBorder.RAISED));
           
        mv1.setVerticalTextPosition(SwingConstants.BOTTOM);
        mv1.setHorizontalTextPosition(SwingConstants.CENTER);
        tp.add(mv1);

        mv2.setVerticalTextPosition(SwingConstants.BOTTOM);
        mv2.setHorizontalTextPosition(SwingConstants.CENTER);
        tp.add(mv2);
           
        mv3.setVerticalTextPosition(JLabel.BOTTOM);
        mv3.setHorizontalTextPosition(JLabel.CENTER);
         tp.add(mv3);
           
        mv4.setVerticalTextPosition(JLabel.BOTTOM);
        mv4.setHorizontalTextPosition(JLabel.CENTER);
        tp.add(mv4);
           
        mv5.setVerticalTextPosition(JLabel.BOTTOM);
        mv5.setHorizontalTextPosition(JLabel.CENTER);
        tp.add(mv5);
           
        mv6.setVerticalTextPosition(JLabel.BOTTOM);
        mv6.setHorizontalTextPosition(JLabel.CENTER);
        tp.add(mv6);
           
        mv7.setVerticalTextPosition(JLabel.BOTTOM);
        mv7.setHorizontalTextPosition(JLabel.CENTER);
        tp.add(mv7);
           
        mv8.setVerticalTextPosition(JLabel.BOTTOM);
        mv8.setHorizontalTextPosition(JLabel.CENTER);
        tp.add(mv8);
        
        tpmain.add("East", tklistpn);
        tpmain.add("North", search);
        tpmain.add("Center", tp);   
        con.add("North", mp);
        con.add("Center",tpmain);
        
      //ȸ������ ���̾�α� ����
         joincon = joindlg.getContentPane();
         joincon.setLayout(new BorderLayout());
         joindlg.setSize(300,220);
         joindlg.setResizable(false);
         Toolkit tk = Toolkit.getDefaultToolkit();
         Dimension di = tk.getScreenSize();
         Dimension di1 = joindlg.getSize();
         joindlg.setLocation((int)(di.getWidth() / 2 - di1.getWidth() / 2),(int)(di.getHeight() / 2 - di1.getHeight() / 2));
         
         JPanel joinp = new JPanel(new GridLayout(6,2));
         joinp.add(joinidlb); joinp.add(joinidtf);
         joinp.add(joinpwlb); joinp.add(joinpwtf);
         joinp.add(joinpwoklb); joinp.add(joinpwoktf);
         joinp.add(jointellb); joinp.add(jointeltf);
         joinp.add(joinniklb); joinp.add(joinniktf);
         joinp.add(joinemaillb); joinp.add(joinemailtf);
         
         JPanel join = new JPanel(new FlowLayout());
         join.add(joinp);
         
         JPanel joinbtp = new JPanel(new FlowLayout());
         joinbtp.add(joinok); joinbtp.add(joinx);
         
         joincon.add("Center", join);
         joincon.add("South", joinbtp);
         
         //ȸ������ �Ϸ� ���̾�α� ����
         joinokcon = joinokdlg.getContentPane();
         joinokcon.setLayout(new BorderLayout());
         joinokdlg.setSize(300,150);
         joinokdlg.setResizable(false);
         Dimension di3 = joinokdlg.getSize();
         joinokdlg.setLocation((int)(di.getWidth() / 2 - di3.getWidth() / 2),(int)(di.getHeight() / 2 - di3.getHeight() / 2));
         
         JPanel joinokp = new JPanel(new GridLayout(4,2));
         joinokp.add(joinokidlb); joinokp.add(joinokidtf);
         joinokp.add(joinoktellb); joinokp.add(joinokteltf);
         joinokp.add(joinokniklb); joinokp.add(joinokniktf);
         joinokp.add(joinokemaillb); joinokp.add(joinokemailtf);
         
         JPanel joinok = new JPanel(new FlowLayout());
         joinok.add(joinokp);
         
         JPanel joinokbtp = new JPanel(new FlowLayout());
         joinokbtp.add(joinokbt);
         
         joinokcon.add("Center", joinok);
         joinokcon.add("South", joinokbtp);
         
         //ȸ������ ���� ���̾�α� ����
         joinxcon = joinxdlg.getContentPane();
         joinxcon.setLayout(new BorderLayout());
         joinxdlg.setSize(230, 100);
         joinxdlg.setResizable(false);
         Dimension di4 = joinxdlg.getSize();
         joinxdlg.setLocation((int)(di.getWidth() / 2 - di4.getWidth() / 2),(int)(di.getHeight() / 2 - di4.getHeight() / 2));
         
         JPanel joinxlbp = new JPanel(new FlowLayout());
         joinxlbp.add(joinxlb);
         
         JPanel joinxbtp = new JPanel(new FlowLayout());
         joinxbtp.add(joinxbt);
         
         joinxcon.add("Center",joinxlbp);
         joinxcon.add("South",joinxbtp);
         
         // �α��� ���̾�α� ����
         logincon = logindlg.getContentPane();
         logincon.setLayout(new BorderLayout());
         logindlg.setSize(300,120);
         logindlg.setResizable(false);
         Dimension di2 = logindlg.getSize();
         logindlg.setLocation((int)(di.getWidth() / 2 - di2.getWidth() / 2),(int)(di.getHeight() / 2 - di2.getHeight() / 2));
         
         JPanel loginp = new JPanel(new GridLayout(2,2));
         loginp.add(loginidlb); loginp.add(loginidtf);
         loginp.add(loginpwlb); loginp.add(loginpwtf);
         
         JPanel login = new JPanel(new FlowLayout());
         login.add(loginp);
         
         JPanel loginbtp = new JPanel(new FlowLayout());
         loginbtp.add(loginok); loginbtp.add(loginx);
         
         logincon.add("Center", login);
         logincon.add("South", loginbtp);
         
         //�α��� �Ϸ� ���̾�α� ����
         loginokcon = loginokdlg.getContentPane();
         loginokcon.setLayout(new BorderLayout());
         loginokdlg.setSize(300,100);
         loginokdlg.setResizable(false);
         Dimension di6 = loginokdlg.getSize();
         loginokdlg.setLocation((int)(di.getWidth() / 2 - di6.getWidth() / 2),(int)(di.getHeight() / 2 - di6.getHeight() / 2));
         
         JPanel loginokp = new JPanel(new FlowLayout());
         loginokp.add(loginokdlglb1); loginokp.add(loginokdlglb2);
         
         JPanel loginokbtp = new JPanel(new FlowLayout());
         loginokbtp.add(loginokdlgbt);
         
         loginokcon.add("Center", loginokp);
         loginokcon.add("South", loginokbtp);      
         
         //�α��� ���� ���̾�α� ����
         loginxcon = loginxdlg.getContentPane();
         loginxcon.setLayout(new BorderLayout());
         loginxdlg.setSize(300,100);
         loginxdlg.setResizable(false);
         Dimension di5 = loginxdlg.getSize();
         loginxdlg.setLocation((int)(di.getWidth() / 2 - di5.getWidth() / 2),(int)(di.getHeight() / 2 - di5.getHeight() / 2));
         
         JPanel loginxp = new JPanel(new FlowLayout());
         loginxp.add(loginxdlglb);
         
         JPanel loginxbtp = new JPanel(new FlowLayout());
         loginxbtp.add(loginxdlgbt);
         
         loginxcon.add("Center", loginxp);
         loginxcon.add("South", loginxbtp);
         
         
         //������ ����
          tbuy.setLayout(new BoxLayout(tbuy, BoxLayout.Y_AXIS));
          t1lb.setPreferredSize(new Dimension(300, 60));
          t2lb.setPreferredSize(new Dimension(300, 60));
          t3lb.setPreferredSize(new Dimension(300, 60));
            
          t1ini.add("Center", t1lb); 
          t1in.add(t1ini);
          t1.add(t1in);
          t2ini.add("Center", t2lb); 
          t2in.add(t2ini);
          t2.add(t2in);
          t3ini.add("Center", t3lb); 
          t3in.add(t3ini);
          t3.add(t3in);
            
          tbuy.add(t1);tbuy.add(t2);tbuy.add(t3);
            

          buyer_Top_btp1.add(tupbt); buyer_Top_btp2.add(tbuybt);
          buyer_Top_btp.add(buyer_Top_btp1);buyer_Top_btp.add(buyer_Top_btp2);
            
            buyerjp.add("North", buyer_Top_btp);
            buyerjp.add("Center", jstbuy);
                  
            BuyerP.add("North", Buyerlb);
            BuyerP.add("Center", buyerjp);
            
            MainP.add(tp); MainP.add(BuyerP); 
            
            con.add("North", mp); //����
            con.add("Center",MainP);

          
          //���� �����̳� ����
            rsvCon = rsvDlg.getContentPane();
            rsvDlg.setLayout(new BorderLayout());
            rsvDlg.setSize(500, 300);;
            rsvDlg.setResizable(false);
            
            //��ġ ����
            Dimension dm1 = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension dm2 = rsvDlg.getSize();
            rsvDlg.setLocation((int)(dm1.getWidth()/2-dm2.getWidth()/2), 
                  (int)(dm1.getHeight()/2 - dm2.getHeight()/2));
            
            JPanel pl3 = new JPanel(new BorderLayout());
            
            //���� �� ���� 
            JPanel pl1 = new JPanel(new GridLayout(5, 1));
            pl1.add(lbTicketNum);
            pl1.add(lbName);
            pl1.add(lbLoc);
            pl1.add(lbSeat);
            pl1.add(lbPrice);
            pl3.add("West",pl1);
            
            //������ �� ����
            JPanel pl2 = new JPanel(new GridLayout(5,1));
            pl2.add(lbTicketNumDB);
            pl2.add(lbNameDB);
            pl2.add(lbLocDB);
            pl2.add(lbSeatDB);
            pl2.add(lbPriceDB);
            pl3.add("Center",pl2);
            
            pl3.setBorder(new TitledBorder(new SoftBevelBorder(SoftBevelBorder.RAISED), "Reservation"));
            
            rsvCon.add("Center",pl3);
            
            //��ư �߰��ϱ�
            JPanel pl4 = new JPanel(new FlowLayout());
            pl4.add(btnPay);
            pl4.add(btnCancle);
            
            rsvCon.add("South", pl4);
            rsvCon.add("West", imgPosterLb);   

            
            //����������
            tPane.setPreferredSize(new Dimension(1000, 550));
            tPane.add("����������",myP); 
               tPane.add("����", edit);
               tPane.add("����Ʈ", pointp);
               tPane.add("��������", show);
               tPane.setTabPlacement(JTabbedPane.LEFT); 
               
               p2_1.add(eID);p2_1.add(eID1);
               p2_2.add(ephone);p2_2.add(tphone);
               p2_3.add(ename);p2_3.add(tname);
               p2_4.add(eemail);p2_4.add(tmail);
               p2_5.add(elevel);p2_5.add(elevel1);
               p2_6.add(check);p2_6.add(cancel);
                
               p22.add(p2_1);p22.add(p2_2);p22.add(p2_3);p22.add(p2_4);p22.add(p2_5);
               p22_2.add(p22);
               p2_6_2.add(p2_6);
               edit.add("Center",p22_2);edit.add("South", p2_6_2);
                
               pointP1.setLayout(gridb);
               constraint.fill = GridBagConstraints.BOTH;
               
               constraint.gridx=0; constraint.gridy=0; pointP1.add(point2,constraint); 
               constraint.gridx=0; constraint.gridy=2; pointP1.add(point3,constraint);  
               constraint.gridx=0; constraint.gridy=3; pointP1.add(point4,constraint);  
               constraint.gridx=0; constraint.gridy=4; pointP1.add(point5,constraint);  
             
                
               pointP.add(point);pointP.add(point1); 
               chargeP.add(charge);
               pointp.add("North",pointP);pointp.add("South",chargeP);pointp.add("Center", pointP1);
                  
               p4_1.add(id1);p4_1.add(id2);
               p4_2.add(phone1);p4_2.add(phone2);
               p4_3.add(nname1);p4_3.add(nname2);
               p4_4.add(email1);p4_4.add(email2);
               p4_5.add(level1);p4_5.add(level2);
               p4.add(p4_1);p4.add(p4_2);p4.add(p4_3);p4.add(p4_4);p4.add(p4_5);
               p5.add("West",p4);
               
               myP.add("Center",p5);
               mypagep.add(tPane);
               MainP.add(mypagep);
            
            // ���� �Ϸ� ���̾�α�
        updateokcon = updateokdlg.getContentPane();
        updateokcon.setLayout(new BorderLayout());
		updateokdlg.setSize(300,150);
		updateokdlg.setResizable(false);
		Dimension di7 = updateokdlg.getSize();
		updateokdlg.setLocation((int)(di.getWidth() / 2 - di7.getWidth() / 2),(int)(di.getHeight() / 2 - di7.getHeight() / 2));
				
		JPanel updateokp = new JPanel(new GridLayout(4,2));
		updateokp.add(updateokidlb); updateokp.add(updateokidtf);
		updateokp.add(updateoktellb); updateokp.add(updateokteltf);
		updateokp.add(updateokniklb); updateokp.add(updateokniktf);
		updateokp.add(updateokemaillb); updateokp.add(updateokemailtf);
					
		JPanel updateok = new JPanel(new FlowLayout());
		updateok.add(updateokp);
					
		JPanel updateokbtp = new JPanel(new FlowLayout());
		updateokbtp.add(updateokbt);
				
		updateokcon.add("Center", updateok);
		updateokcon.add("South", updateokbtp);   
      }


    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == Buyer_bt){ // ������ ����
            tp.setVisible(false);
           BuyerP.setVisible(true);
         }// ������ ����
       
       else if(e.getSource()== joinbt){ // ȸ������
           // tp.setVisible(false);
            joindlg.setVisible(true);
         } //ȸ������
         
       else if(e.getSource() == joinok){ //ȸ������ ���̾�α��� �Ϸ�
            String a1 = joinidtf.getText().trim();
            String b1 = new String(joinpwtf.getPassword());
            String c1 = new String(joinpwoktf.getPassword());
            String d1 = jointeltf.getText().trim();
            String e1 = joinniktf.getText().trim();
            String f1 = joinemailtf.getText().trim();
            if(!b1.equals(c1)){
               joinxdlg.setVisible(true);
            }
            else{
            	joinMember();
               /*TotalTicket_sub1 sss = new TotalTicket_sub1(a1, b1, c1, d1, e1, f1);
               vc.addElement(sss);
               File file = new File("C:\\workspace\\data\\info.txt");
               try{
                  FileOutputStream fo = new FileOutputStream(file);
                  BufferedOutputStream bo = new BufferedOutputStream(fo);
                  ObjectOutputStream oos = new ObjectOutputStream(bo);
                  oos.writeObject(vc); // �ݵ�� ����ȭ�Ǿ� �־�� �Ѵ�.
                  oos.close();
                  bo.close();
                  fo.close();
               }catch(Exception ee){
                  System.err.println("Error = " + ee.toString());
               }*/
               joinokidtf.setText(joinidtf.getText().trim());
               joinidtf.setText("");
               joinpwtf.setText("");
               joinpwoktf.setText("");
               joinokteltf.setText(jointeltf.getText().trim());
               jointeltf.setText("");
               joinokniktf.setText(joinniktf.getText().trim());
               joinniktf.setText("");
               joinokemailtf.setText(joinemailtf.getText().trim());
               joinemailtf.setText(" ");
               joinemailtf.setText("");
               joindlg.setVisible(false);
               joinokdlg.setVisible(true);
            }
            
         } // ȸ������ ���̾�α��� �Ϸ�
       
         else if(e.getSource() == joinokbt){ // ȸ������ ���̾�α��� �Ϸ� ���̾�α��� Ȯ�ι�ư
          joinokidtf.setText("");
          joinokteltf.setText("");
             joinokniktf.setText("");
          joinokemailtf.setText(" ");
          joinokemailtf.setText("");
          joinokdlg.setVisible(false);
        }// ȸ������ ���̾�α��� �Ϸ� ���̾�α��� Ȯ�ι�ư
         
         else if(e.getSource() == joinx) { // ȸ������ ���̾�α��� ���
            joinidtf.setText("");
            joinpwtf.setText("");
            joinpwoktf.setText("");
            jointeltf.setText("");
            joinniktf.setText("");
            joinemailtf.setText(" ");
            joinemailtf.setText("");
            joindlg.setVisible(false);
         }// ȸ������ ���̾�α��� ���
         
         else if(e.getSource()== loginbt){ // �α���
            //tp.setVisible(false);
            logindlg.setVisible(true);
         } // �α���
         
         else if(e.getSource() == loginok){ //�α��� ���̾�α��� �Ϸ�
            loginMember();
        	/* File file = new File("C:\\workspace\\data\\info.txt");
            try{
               FileInputStream fi = new FileInputStream(file);
               BufferedInputStream bi = new BufferedInputStream(fi);
               ObjectInputStream ois = new ObjectInputStream(bi);
               vc.clear();
               vc = (Vector)ois.readObject();
               ois.close();
               bi.close();
               fi.close();
            }catch(Exception ee){
               ee.printStackTrace();;
            }
            for(int i=0;i<vc.size();i++){
               TotalTicket_sub1 imsi = (TotalTicket_sub1)vc.elementAt(i);
               if(imsi.getId().equals(loginidtf.getText().trim()) && imsi.getPw().equals(new String(loginpwtf.getPassword()))){
                  loginokdlglb1.setText(imsi.getId());
                  id2.setText(imsi.getId());
                  nname2.setText(imsi.getNik());
                  phone2.setText(imsi.getTel());
                  email2.setText(imsi.getEmail());
                  eID1.setText(imsi.getId());
                  tphone.setText(imsi.getTel());
                  tmail.setText(imsi.getEmail());
                  tname.setText(imsi.getNik());
                  loginokdlg.setVisible(true);
               }
               else{
                  loginxdlg.setVisible(true);
               }
            }*/
            
         } //�α��� ���̾�α��� �Ϸ�
         
         else if(e.getSource() == loginx){ //�α��� ���̾�α��� ���
            loginidtf.setText("");
            loginpwtf.setText(" ");
            loginpwtf.setText("");
            logindlg.setVisible(false);
            
         } //�α��� ���̾�α��� ���
         
         else if(e.getSource() == joinxbt){ // ȸ������ ���д��̾�α� Ȯ�ι�ư
            joinxdlg.setVisible(false);
            joinpwtf.setText("");
            joinpwoktf.setText(" ");
            joinpwoktf.setText("");
         } // ȸ������ ���д��̾�α� Ȯ�ι�ư
         
         else if(e.getSource() == loginokdlgbt){ // �α��� ���� ���̾�α� Ȯ�ι�ư
            loginidtf.setText("");
            loginpwtf.setText(" ");
            loginpwtf.setText("");
            loginokdlg.setVisible(false);
            logindlg.setVisible(false);
            
            loginbt.setVisible(false);
            joinbt.setVisible(false);
            lb.setVisible(true);
            
            
           /* File file = new File("C:\\workspace\\data\\info.txt");
            try{
               FileInputStream fi = new FileInputStream(file);
               BufferedInputStream bi = new BufferedInputStream(fi);
               ObjectInputStream ois = new ObjectInputStream(bi);
               vc.clear();
               vc = (Vector)ois.readObject();
               ois.close();
               bi.close();
               fi.close();
            }catch(Exception ee){
               ee.printStackTrace();;
            }
            for(int i=0;i<vc.size();i++){
               TotalTicket_sub1 imsi = (TotalTicket_sub1)vc.elementAt(i);
               lb.setText(imsi.getId());
            }*/
            
            logoutbt.setVisible(true);
            mypagebt.setVisible(true);
         } // �α��� ���� ���̾�α� Ȯ�ι�ư
         
         else if(e.getSource() == loginxdlgbt){ // �α��� ���� ���̾�α� Ȯ�ι�ư
            loginidtf.setText("");
            loginpwtf.setText(" ");
            loginpwtf.setText("");
            loginxdlg.setVisible(false);
         } // �α��� ���� ���̾�α� Ȯ�ι�ư
         
         else if(e.getSource() == logoutbt){ // �α׾ƿ� ��ư
            lb.setVisible(false);
            lb.setText(" ");
            lb.setText("");
            logoutbt.setVisible(false);
			mypagebt.setVisible(false);
			loginbt.setVisible(true);
			joinbt.setVisible(true);
			mypagep.setVisible(false);
         }// �α׾ƿ� ��ư
       
         else if(e.getSource() == btnCancle){ // ���� ��ҹ�ư
            rsvDlg.setVisible(false);
         } // ���� ��ҹ�ư
       
         
         else if(e.getSource()== mypagebt){ // ����������
            tp.setVisible(false);
            BuyerP.setVisible(false);
            mypagep.setVisible(true);
         } // ����������
         else if(e.getSource()==check){ // ���������� ���� Ȯ�ι�ư
				updateMember();
				updateokdlg.setVisible(true);
				
			} // ���������� ���� Ȯ�ι�ư
         else if(e.getSource()==cancel){ // ���������� ���� ��ҹ�ư
				tphone.setText(phone2.getText().trim());
				tname.setText(nname2.getText().trim());
				tmail.setText(email2.getText().trim());
				
			}// ���������� ���� ��ҹ�ư
		 
         else if(e.getSource()==updateokbt){ // ���� �Ϸ� ���̾�α� Ȯ�ι�ư
				updateokdlg.setVisible(false);
				
			}// ���� �Ϸ� ���̾�α� Ȯ�ι�ư

      
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      
    }

    @Override
    public void mousePressed(MouseEvent e) {
      
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       if(e.getSource()== homebt){ // Ȩ��ư
            tp.setVisible(true);
            BuyerP.setVisible(false);
            mypagep.setVisible(false);
         } // Ȩ��ư
       
       else if(e.getSource() == mv1){
          rsvDlg.setVisible(true);
       }
       
       else if(e.getSource() == mv2){
          rsvDlg.setVisible(true);
       }
       
       else if(e.getSource() == mv3){
          rsvDlg.setVisible(true);
       }
       
       else if(e.getSource() == mv4){
          rsvDlg.setVisible(true);
       }
       
       else if(e.getSource() == mv5){
          rsvDlg.setVisible(true);
       }
       
       else if(e.getSource() == mv6){
          rsvDlg.setVisible(true);
       }
       
       else if(e.getSource() == mv7){
          rsvDlg.setVisible(true);
       }
       
       else if(e.getSource() == mv8){
          rsvDlg.setVisible(true);
       }
      
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void mouseExited(MouseEvent e) {
      // TODO Auto-generated method stub
      
    }

   @Override
   public void focusGained(FocusEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void focusLost(FocusEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void keyTyped(KeyEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void keyPressed(KeyEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void keyReleased(KeyEvent e) {
      // TODO Auto-generated method stub
      
   }
}
class TotalTicket_sub1 implements Serializable{
   private String id;
   private String pw;
   private String pwok;
   private String tel;
   private String nik;
   private String email;
   
   public TotalTicket_sub1(String a, String b, String c, String d, String e, String f){
      id = a;
      pw = b;
      pwok = c;
      tel = d;
      nik = e;
      email = f;
   }
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

   public String getPwok() {
      return pwok;
   }

   public void setPwok(String pwok) {
      this.pwok = pwok;
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
}