package ieiProject;

import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import javafx.scene.control.TableView;

//�ҽ����� 170511      

public class TotalTicket {
	public static void main(String[] ar) {
		TotalTicket_sub123 ex = new TotalTicket_sub123();
	}
}

class TotalTicket_sub123 extends JFrame implements ActionListener, MouseListener, KeyListener, FocusListener {
	
	ImageIcon error_image = new ImageIcon("..\\ieiProject\\image\\error.jpg");
	private String saveshowname = null;
	private Container con;
	// �޴�ȭ��
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
	private JButton adminbt = new JButton("������ ���");
	
	//������ ���
	private Container admincon;
	private JDialog admindlg = new JDialog(this, "������ ���", true);
	protected JTabbedPane adminPane = new JTabbedPane();
	protected Panel customerP = new Panel(new BorderLayout());
	protected Panel showP = new Panel(new BorderLayout());
	
	//������ ��� ������ 
	int updatecustomerRow;
	int updateshowRow;
	DefaultTableModel customerdatamodel;
	JTable customertableView;
	JScrollPane customerscrollList;
	
	String customercolName[] = { "ID", "PW", "TEL", "NIK", "EMAIL", "GRADE", "POINT" };
	Vector<Vector<String>> customerdata;
	Vector<String> customercolNames;
	
	JPanel cp = new JPanel(new FlowLayout());
	JLabel clb1 = new JLabel("ID", JLabel.CENTER);
	JLabel clb2 = new JLabel("PW", JLabel.CENTER);
	JLabel clb3 = new JLabel("TEL", JLabel.CENTER);
	JLabel clb4 = new JLabel("NIK", JLabel.CENTER);
	JLabel clb5 = new JLabel("EMAIL", JLabel.CENTER);
	JLabel clb6 = new JLabel("GRADE", JLabel.CENTER);
	JLabel clb7 = new JLabel("POINT", JLabel.CENTER);
	JTextField ctf1 = new JTextField(10);
	JTextField ctf2 = new JTextField(10);
	JTextField ctf3 = new JTextField(10);
	JTextField ctf4 = new JTextField(10);
	JTextField ctf5 = new JTextField(10);
	JTextField ctf6 = new JTextField(10);
	JTextField ctf7 = new JTextField(10);
	JPanel cp1 = new JPanel(new GridLayout(2,1));
	JPanel cp2 = new JPanel(new GridLayout(2,1));
	JPanel cp3 = new JPanel(new GridLayout(2,1));
	JPanel cp4 = new JPanel(new GridLayout(2,1));
	JPanel cp5 = new JPanel(new GridLayout(2,1));
	JPanel cp6 = new JPanel(new GridLayout(2,1));
	JPanel cp7 = new JPanel(new GridLayout(2,1));
	JPanel cus = new JPanel(new FlowLayout());
	JButton customerinbt = new JButton("�߰�");
	JButton customerupbt = new JButton("����");
	JButton customerdebt = new JButton("����");
	
	//������ ��� ���� ����
	DefaultTableModel showdatamodel;
	JTable showtableView;
	JScrollPane showscrollList;
	
	String showcolName[] = { "SID", "SNAME", "SLOC", "SPRICE", "SIMG", "DTDATE",};
	Vector<Vector<String>> showdata;
	Vector<String> showcolNames;
	
	JPanel ssp = new JPanel(new FlowLayout());
	JLabel sslb1 = new JLabel("SID", JLabel.CENTER);
	JLabel sslb2 = new JLabel("SNAME", JLabel.CENTER);
	JLabel sslb3 = new JLabel("SLOC", JLabel.CENTER);
	JLabel sslb4 = new JLabel("SPRICE", JLabel.CENTER);
	JLabel sslb5 = new JLabel("SIMG", JLabel.CENTER);
	JLabel sslb6 = new JLabel("DTDATE", JLabel.CENTER);
	JTextField sstf1 = new JTextField(10);
	JTextField sstf2 = new JTextField(10);
	JTextField sstf3 = new JTextField(10);
	JTextField sstf4 = new JTextField(10);
	JTextField sstf5 = new JTextField(10);
	JTextField sstf6 = new JTextField(10);
	
	JPanel ssp1 = new JPanel(new GridLayout(2,1));
	JPanel ssp2 = new JPanel(new GridLayout(2,1));
	JPanel ssp3 = new JPanel(new GridLayout(2,1));
	JPanel ssp4 = new JPanel(new GridLayout(2,1));
	JPanel ssp5 = new JPanel(new GridLayout(2,1));
	JPanel ssp6 = new JPanel(new GridLayout(2,1));
	
	JPanel sho = new JPanel(new FlowLayout());
	JButton shoinbt = new JButton("�߰�");
	JButton shoupbt = new JButton("����");
	JButton shodebt = new JButton("����");


	// �˻�â
	Choice ch1 = new Choice();
	JPanel tpmain = new JPanel(new BorderLayout(3, 3));
	JPanel search = new JPanel(new FlowLayout(FlowLayout.LEFT));

	private TextField searchtf = new TextField("���� �Ǵ� ��¥ �Է�", 80);
	private JButton searchbt = new JButton("�˻�");

	// �˻� ���
	JPanel srchresult = new JPanel(new GridLayout(2, 4, 3, 3));
	
	// �α��, ��¥��
	JPanel tklistpn = new JPanel(new GridLayout(2, 1, 5, 5));
	JPanel tlingi = new JPanel(new BorderLayout(3, 3));
	JPanel tldate = new JPanel(new BorderLayout(3, 3));
	JLabel lbingi = new JLabel("�̴��� Best Ticket!");
	JLabel lbdate = new JLabel("��¥��");
	List ltingi = new List();
	List ltdate = new List();

	// Ƽ��ȭ��
	JPanel tp = new JPanel(new GridLayout(2, 3, 3, 3));
	JScrollPane scroller = new JScrollPane(tp);

	JPanel mp = new JPanel(new BorderLayout(3, 3));

	ImageIcon home = new ImageIcon("..\\ieiProject\\image\\home2.jpg");
	private int num = 7;
	private int num1 = 6;
	ImageIcon[] image = new ImageIcon[num];
	ImageIcon[] image1 = new ImageIcon[num1];

	private JLabel homebt = new JLabel(home);
	// ���� ��
	private JLabel[] mv = new JLabel[num];
	private JLabel[] mv1 = new JLabel[num1];
	// copy ��
	private JLabel[] mvc = new JLabel[num];
	private JLabel[] mvc1 = new JLabel[num1];

	String[] mvst = new String[num];
	String[] mvst1 = new String[num1];
	// ȸ������ ���̾�α�
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

	// ȸ������ �Ϸ� ���̾�α�
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

	private JButton joinokbt = new JButton("�Ϸ�");

	// ȸ������ ��� ���̾�α�
	private Container joinxcon;
	private JDialog joinxdlg = new JDialog(joindlg, "��й�ȣ ����", true);

	private JLabel joinxlb = new JLabel("��й�ȣ�� �Ȱ��� �Է��ϼ���");
	private JButton joinxbt = new JButton("Ȯ��");

	// �α��� ���̾�α�
	private Container logincon;
	private JDialog logindlg = new JDialog(this, "�α���", true);
	private JLabel loginidlb = new JLabel("���̵� : ", JLabel.RIGHT);
	private JLabel loginpwlb = new JLabel("��й�ȣ : ", JLabel.RIGHT);

	private JTextField loginidtf = new JTextField(10);
	private JPasswordField loginpwtf = new JPasswordField(10);

	private JButton loginok = new JButton("Ȯ��");
	private JButton loginx = new JButton("���");

	private Vector vc = new Vector();

	// �α��� �Ϸ� ���̾�α�
	private Container loginokcon;
	private JDialog loginokdlg = new JDialog(logindlg, "�α��� �Ϸ�", true);

	private JLabel loginokdlglb1 = new JLabel("");
	private JLabel loginokdlglb2 = new JLabel("�� ȯ���մϴ�.");
	private JButton loginokdlgbt = new JButton("Ȯ��");

	// �α��� ���� ���̾�α�
	private Container loginxcon;
	private JDialog loginxdlg = new JDialog(logindlg, "�α��� ����", true);
	private JLabel loginxdlglb = new JLabel("���̵� ��й�ȣ�� Ʋ���ϴ�!!!!");
	private JButton loginxdlgbt = new JButton("����ȭ��");

	// ������ ����
	private JPanel BuyerP = new JPanel(new BorderLayout(5, 5));

	private Label Buyerlb = new Label("�����ڳ��� Ƽ�ϸŸ�");
	private JPanel buyerjp = new JPanel(new BorderLayout(3, 3));
	private JPanel tbuy = new JPanel();
	private JScrollPane jstbuy = new JScrollPane(tbuy);
	private JPanel buyer_Top_btp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JPanel buyer_Top_btp1 = new JPanel(new FlowLayout());
	private JPanel buyer_Top_btp2 = new JPanel(new FlowLayout());
	private JButton tupbt = new JButton("ǥ �ø���");
	private JButton tbuybt = new JButton("�� ��");

	private JPanel t1in = new JPanel(new BorderLayout(3, 3));
	private JPanel t1show = new JPanel(new BorderLayout(3, 3));
	private JPanel t1infop = new JPanel(new GridLayout(2, 1));
	private Checkbox buycb = new Checkbox();
	private Label t1name = new Label("Ư���ù�", Label.CENTER);
	private Label t1date = new Label("2017-04-23", Label.CENTER);
	private JPanel t1 = new JPanel();
	private JPanel t3in = new JPanel(new GridLayout(1, 1));
	private JPanel t3ini = new JPanel(new BorderLayout(3, 3));
	private JPanel t3 = new JPanel();
	private JLabel t3lb = new JLabel(new ImageIcon("..\\ieiProject\\image\\ticket.png"));

	// ���� ���̵�
	String selectedSid;

	// ���� ��ư Ŭ�� ����
	private Container rsvCon;
	private JDialog rsvDlg = new JDialog(this, "���� �ϱ�", true);
	private JLabel lbName = new JLabel("���� �̸�  ");
	private JLabel lbNameDB = new JLabel();
	private JLabel lbLoc = new JLabel("���� ���  ");
	private JLabel lbLocDB = new JLabel();
	private JLabel lbPrice = new JLabel("����  ");
	private int showPrice = 0;
	private JLabel lbPriceDB = new JLabel();
	private JLabel lbTicketNum = new JLabel("Ƽ�� ��ȣ  ");
	private JLabel lbTicketNumDB = new JLabel("201704220000001");
	private ImageIcon imgPoster = new ImageIcon("..\\ieiProject\\image\\poster.jpg");
	private JLabel imgPosterLb = new JLabel(imgPoster);
	private JButton btnSeatSelect = new JButton("�¼� ����");
	private JButton btnCancle = new JButton("���");
	private JLabel lbSelectPerson = new JLabel("�ο��� ����");

	String cntPerson[] = { "1", "2", "3", "4", "5" };
	private JComboBox cbCount = new JComboBox(cntPerson);

	// ��¥ �޺� �ڽ� ����
	private JLabel lbDay = new JLabel("���� ��¥ ");
	String cbSelectedDate;
	private JComboBox cbDay = new JComboBox();

	// �¼� ���� ��ư Ŭ�� ����
	private Container sltSeatCon;
	private JDialog sltSeatDlg = new JDialog(this, "�¼� ����", true);
	private JLabel lbNowSltSeat = new JLabel("���� �¼� ���� ��");
	String strPersonCnt;
	private JLabel lbAllSeat = new JLabel();
	private JLabel lbSlash = new JLabel("/");
	private JLabel lbSltSeat = new JLabel("0");
	private JButton btnFinSeat = new JButton("���� �Ϸ�");
	private JButton btnReselect = new JButton("�ٽ� ����");
	
	//�¼� ������ �� ��α��ν� �α����϶�� �ϴ� ���̾�α�
	private Container loginplzCon;
	private JDialog loginplzdlg = new JDialog(this, "�α��� �ʿ�", true);
	private JLabel loginplzlb = new JLabel("�α����� �ʿ��� ����Դϴ�.", error_image, JLabel.CENTER);
	private JButton loginplzok = new JButton("Ȯ��");
	
	// �¼� ��ư
	private JButton btnNotSelected[][] = new JButton[6][16];

	// ����â
	private Container payCon;
	private JDialog payDlg = new JDialog(this, "���� �ϱ�", true);
	private JLabel lbPay = new JLabel("���� �ݾ�");
	private JLabel lbToPay = new JLabel("0");
	private JButton btnPayDlgPay = new JButton("����");
	private JButton btnPayDlgCancle = new JButton("���");

	// ����������
	private GridBagLayout gridb = new GridBagLayout();
	private GridBagConstraints constraint = new GridBagConstraints();
	private JPanel mypagep = new JPanel(new FlowLayout());
	protected JTabbedPane tPane = new JTabbedPane();
	protected Panel myP = new Panel();
	protected Panel edit = new Panel(new BorderLayout());
	protected Panel pointp = new Panel(new BorderLayout());
	protected Panel show = new Panel(new BorderLayout());

	/////////////////////////////// ����������â
	private Panel p4 = new Panel(new GridLayout(5, 1));
	private Panel p5 = new Panel(new GridLayout(5, 1));
	private Panel p4_1 = new Panel(new GridLayout(1, 2));
	private Label id1 = new Label("���̵� ");
	private Label id2 = new Label();
	private Panel p4_2 = new Panel(new GridLayout(1, 2));
	private Label phone1 = new Label("��ȭ��ȣ ");
	private Label phone2 = new Label();
	private Panel p4_3 = new Panel(new GridLayout(1, 2));
	private Label nname1 = new Label("�г��� ");
	private Label nname2 = new Label();
	private Panel p4_4 = new Panel(new GridLayout(1, 2));
	private Label email1 = new Label("�̸��� ");
	private Label email2 = new Label();
	private Panel p4_5 = new Panel(new GridLayout(1, 2));
	private Label level1 = new Label("��� ");
	private Label level2 = new Label();

	//////////////////////////////// ����â
	private Panel p22_2 = new Panel();
	private Panel p22 = new Panel(new GridLayout(5, 1));
	private Panel p2_1 = new Panel(new GridLayout(1, 2));
	private Label eID = new Label("���̵� ");
	private Label eID1 = new Label();
	private Panel p2_2 = new Panel(new GridLayout(1, 2));
	private Label ephone = new Label("��ȭ��ȣ ");
	private TextField tphone = new TextField(10);
	private Panel p2_3 = new Panel(new GridLayout(1, 2));
	private Label ename = new Label("�г��� ");
	private TextField tname = new TextField(10);
	private Panel p2_4 = new Panel(new GridLayout(1, 2));
	private Label eemail = new Label("�̸��� ");
	private TextField tmail = new TextField(10);
	private Panel p2_5 = new Panel(new GridLayout(1, 2));
	private Label elevel = new Label("��� ");
	private Label elevel1 = new Label();
	private Panel p2_6_2 = new Panel(new FlowLayout());
	private Panel p2_6 = new Panel(new GridLayout(1, 2));

	private Button check = new Button("Ȯ��");
	private Button cancel = new Button("���");

	/////////////////////////////////////// ���� �Ϸ� ���̾�α�
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

	//////////////////////////////////////// ����Ʈâ
	private Panel pointP = new Panel(new FlowLayout());

	private Label point = new Label("�ܿ� ����Ʈ: ");
	private Label point1 = new Label("0");

	private Panel pointP1 = new Panel();
	private Label point2 = new Label("1000�� ����: 1100p ����");
	private Label point3 = new Label("5000�� ����: 5600p ����");
	private Label point4 = new Label("10000�� ����: 12000p ����");
	private Label point5 = new Label("50000�� ����: 53000p ����");

	private Panel chargeP = new Panel(new FlowLayout());
	private Button charge = new Button("����Ʈ ����");

	/////////////////////////////////////////////// ����Ʈ ����â
	private Container chargecon;
	private JDialog chargedlg = new JDialog(this, "����Ʈ ����", true);

	private Panel chargep = new Panel(new FlowLayout());
	private Label chargelb = new Label("����: ");
	private TextField chargetf = new TextField(10);
	private Button chargebt = new Button("����");
	////////////////////////////////////// ��������â

	// private BoxLayout box = new BoxLayout();

	// private JScrollPane showfpsc = new JScrollPane(show);

	private JPanel showtkp = new JPanel();
	private JPanel t1in1 = new JPanel(new GridLayout(1, 1));//
	private JPanel t1ini1 = new JPanel(new BorderLayout(3, 3));//
	private JPanel t11 = new JPanel();
	private Checkbox cancelcb = new Checkbox();
	private JLabel t1lb1 = new JLabel(new ImageIcon("..\\ieiProject\\image\\desert.jpg"));
	private JButton tbuybt1 = new JButton("�󼼺���1");

	private JPanel t2in1 = new JPanel(new GridLayout(1, 1));//
	private JPanel t2ini1 = new JPanel(new BorderLayout(3, 3));//
	private JPanel t21 = new JPanel();
	private Checkbox cancelcb2 = new Checkbox();
	private JLabel t2lb1 = new JLabel(new ImageIcon("..\\ieiProject\\image\\desert.jpg"));
	private JButton tbuybt21 = new JButton("�󼼺���2");

	private JPanel t3in1 = new JPanel(new GridLayout(1, 1));//
	private JPanel t3ini1 = new JPanel(new BorderLayout(3, 3));//
	private JPanel t31 = new JPanel();
	private Checkbox cancelcb3 = new Checkbox();
	private JLabel t3lb1 = new JLabel(new ImageIcon("..\\ieiProject\\image\\desert.jpg"));
	private JButton tbuybt31 = new JButton("�󼼺���3");

	private Button canceltk = new Button("�������");
	/////////////////// ������� ���̾Ʒα�
	private JDialog canceldlg = new JDialog(this, "�������", true);
	private Panel realcancelp = new Panel(new BorderLayout());
	private Label realcancel = new Label("������ ����Ͻðڽ��ϱ�?");
	private Panel canceldlgp = new Panel(new FlowLayout());
	private Button cancelokbt = new Button("Ȯ��");
	private Button cancelnobt = new Button("���");

	// DB ����
	Connection conn;
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String id = "scott";
	String pass = "tiger";

	// DB ȸ������
	public void joinMember() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "insert into customer(ID,PW,TEL,NIK,EMAIL,GRADE) values(?,?,?,?,?,?)";
			// member�� point�߰��ؼ� ������ �ٲ���ϴ�(2017.5.10)
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, joinidtf.getText().trim());
			pstmt.setString(2, new String(joinpwtf.getPassword()));
			pstmt.setString(3, jointeltf.getText().trim());
			pstmt.setString(4, joinniktf.getText().trim());
			pstmt.setString(5, joinemailtf.getText().trim());
			pstmt.setString(6, "����"); // ����, ����, ����, ����, ���� ������ ������ ���� ���� ������ �⺻��
			pstmt.executeUpdate();
			pstmt.close();
		} catch (ClassNotFoundException eee) {

		} catch (SQLException ee) {
			System.err.println("ȸ�� ���� ����!!!");
		}
	}

	// DB �α���
	public void loginMember() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "select * from customer where id=? and pw=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginidtf.getText().trim());
			pstmt.setString(2, new String(loginpwtf.getPassword()));

			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				loginxdlg.setVisible(true);
				rs.close();
				pstmt.close();
			}
			
			else if (rs.getString("ID").equals("asd") && rs.getString("PW").equals("1")){
				loginokdlglb1.setText("������");
				id2.setText(rs.getString("id"));
				nname2.setText(rs.getString("nik"));
				phone2.setText(rs.getString("tel"));
				email2.setText(rs.getString("email"));
				eID1.setText(rs.getString("id"));
				tphone.setText(rs.getString("tel"));
				tmail.setText(rs.getString("email"));
				tname.setText(rs.getString("nik"));
				point1.setText(rs.getString("POINT"));// ����Ʈ �߰�(2017.5.10)
				lb.setText("������" + " �� ");
				loginokdlg.setVisible(true);
				adminbt.setVisible(true);
				mypagebt.setVisible(false);
				viewcustomer();
				viewshow();

				rs.close();
				pstmt.close();
			}
			
			else {
				loginokdlglb1.setText(rs.getString("id"));
				id2.setText(rs.getString("id"));
				nname2.setText(rs.getString("nik"));
				phone2.setText(rs.getString("tel"));
				email2.setText(rs.getString("email"));
				eID1.setText(rs.getString("id"));
				tphone.setText(rs.getString("tel"));
				tmail.setText(rs.getString("email"));
				tname.setText(rs.getString("nik"));
				point1.setText(rs.getString("POINT"));// ����Ʈ �߰�(2017.5.10)
				lb.setText(rs.getString("id") + " �� ");
				loginokdlg.setVisible(true);

				rs.close();
				pstmt.close();
			}
		} catch (ClassNotFoundException eee) {

		} catch (SQLException e) {
			System.err.println("�α��� ����!!!");
		}
	}
	
	// �α׾ƿ� (DB���� ����)
	public void logoutMember(){
		try{
			conn.close();
		}catch(SQLException e){
			System.out.println("�α׾ƿ� ����");
		}
	}

	// DB ����
	public void updateMember() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "update customer set tel=?, nik=?, email=? where id=?";
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
		} catch (ClassNotFoundException eee) {

		} catch (SQLException e) {
			System.err.println("���� ����!!!");
		}

	}

	//////////////////////////////// ����Ʈ ����DB(2017.5.10)
	public void chargepoint() {
		if (Integer.parseInt(chargetf.getText().trim()) == 1000) {
			point1.setText(String.valueOf(Integer.parseInt(point1.getText().trim()) + 1030));
			chargetf.setText("");
		}

		else if (Integer.parseInt(chargetf.getText().trim()) == 5000) {
			point1.setText(String.valueOf(Integer.parseInt(point1.getText().trim()) + 5200));
			chargetf.setText("");
		}

		else if (Integer.parseInt(chargetf.getText().trim()) == 10000) {
			point1.setText(String.valueOf(Integer.parseInt(point1.getText().trim()) + 10500));
			chargetf.setText("");
		}

		else if (Integer.parseInt(chargetf.getText().trim()) == 50000) {
			point1.setText(String.valueOf(Integer.parseInt(point1.getText().trim()) + 53000));
			chargetf.setText("");

		}
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "update customer set POINT=? where ID=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, point1.getText().trim());
			pstmt.setString(2, id2.getText().trim());
			pstmt.executeUpdate();
			pstmt.close();

			System.out.println("��������11111");

		} catch (ClassNotFoundException eee) {
			System.err.println("���� ����!!!11111");
		} catch (SQLException e) {
			System.err.println("���� ����!!!2222222");
		}

	}

	// ���� ���� DB�� ���� �ҷ�����
	public void detshow() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);

			// ���� ���� �ҷ�����
			String query = "select * from show where sname=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, saveshowname);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// if (rs.getString("SNAME").equals("Ư���ù�")) {
				lbNameDB.setText(rs.getString("SNAME"));
				lbLocDB.setText(rs.getString("SLOC"));
				lbPriceDB.setText(rs.getString("SPRICE"));
				showPrice = Integer.parseInt(lbPriceDB.getText());

				// ������ �̹��� �缳��
				imgPosterLb.setIcon(new ImageIcon(rs.getString("SIMG")));
			}

			rs.close();
			pstmt.close();

			// �޺��ڽ� (��¥ �ҷ�����)
			String dayQuery = "select * from detshow where sid like (select sid from show where sname=?)";
			PreparedStatement cbpstmt = conn.prepareStatement(dayQuery);
			cbpstmt.setString(1, saveshowname);
			ResultSet cbrs = cbpstmt.executeQuery();

			// ��¥ ���� ��ȯ�Ͽ� �����ϱ�
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� kk:mm");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd kk:mm");

			cbDay.removeAllItems();

			// DB�κ��� ��¥ �޺� �ڽ� �ҷ�����
			while (cbrs.next()) {
				selectedSid = cbrs.getString("SID");
				cbDay.addItem(sdf.format(cbrs.getTimestamp("DTDATE")));
			}
			cbrs.close();
			cbpstmt.close();
		} catch (ClassNotFoundException eee) {
			System.out.println("���� ���� �ҷ����� ����");
		} catch (SQLException e) {
			System.err.println("���� ���� �ҷ����� ����");
		}
	}
	
	// ��¥�� ����
	public void dateshow(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);

			String query = "select sname from show, dateview where show.sid = dateview.sid";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ltdate.add(rs.getString("SNAME"));

			}
			rs.close();
			pstmt.close();
			
		}catch (ClassNotFoundException eee) {
			System.out.println("��¥�� ���� �ҷ����� ����");
		} catch (SQLException e) {
			System.err.println("��¥�� ���� �ҷ����� ����2");
		}
	}
	
	// best ����
	public void bestshow(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);

			String query = "select sname from show, bestview where show.sid = bestview.sid";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ltingi.add(rs.getString("SNAME"));

			}
			rs.close();
			pstmt.close();
				
		}catch (ClassNotFoundException eee) {
			System.out.println("��¥�� ���� �ҷ����� ����");
		} catch (SQLException e) {
			System.err.println("��¥�� ���� �ҷ����� ����2");
		}
	}
		
	// �� ����
	public void viewcustomer(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);

			String query = "select * from customer";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Vector<String> vector = new Vector<String>();
				vector.add(rs.getString("ID"));
				vector.add(rs.getString("PW"));
				vector.add(rs.getString("TEL"));
				vector.add(rs.getString("NIK"));
				vector.add(rs.getString("EMAIL"));
				vector.add(rs.getString("GRADE"));
				vector.add(rs.getString("POINT"));
				customerdata.addElement(vector);
			}
			rs.close();
			pstmt.close();
				
		}catch (ClassNotFoundException eee) {
			System.out.println("�� �ҷ����� ����");
		} catch (SQLException e) {
			System.err.println("�� �ҷ����� ����2");
		}
	}
	
	// �� �߰�
	public void addcustomer(){
		Vector<String> vector = new Vector<String>();
		vector.add(ctf1.getText());
		vector.add(ctf2.getText());
		vector.add(ctf3.getText());
		vector.add(ctf4.getText());
		vector.add(ctf5.getText());
		vector.add(ctf6.getText());
		vector.add(ctf7.getText());
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "insert into customer(ID,PW,TEL,NIK,EMAIL,POINT) values(?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, ctf1.getText());
			pstmt.setString(2, ctf2.getText());
			pstmt.setString(3, ctf3.getText());
			pstmt.setString(4, ctf4.getText());
			pstmt.setString(5, ctf5.getText());
			pstmt.setString(6, "0");
			pstmt.executeUpdate();
			pstmt.close();
		} catch (ClassNotFoundException eee) {

		} catch (SQLException ee) {
			System.err.println("�� �߰� ����!!!");
		}
		ctf1.setText(null);
		ctf2.setText(null);
		ctf3.setText(null);
		ctf4.setText(null);
		ctf5.setText(null);
		ctf6.setText(null);
		ctf7.setText(null);
		ctf1.requestFocus();
		customerdata.addElement(vector);
		customerdatamodel.fireTableDataChanged();
	}
	
	// �� ����
	public void updatecustomer(){
		updatecustomerRow = customertableView.getSelectedRow();
		customertableView.setValueAt(ctf1.getText(), updatecustomerRow, 0);
		customertableView.setValueAt(ctf2.getText(), updatecustomerRow, 1);
		customertableView.setValueAt(ctf3.getText(), updatecustomerRow, 2);
		customertableView.setValueAt(ctf4.getText(), updatecustomerRow, 3);
		customertableView.setValueAt(ctf5.getText(), updatecustomerRow, 4);
		customertableView.setValueAt(ctf6.getText(), updatecustomerRow, 5);
		customertableView.setValueAt(ctf7.getText(), updatecustomerRow, 6);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "update customer set pw=?, tel=?, nik=?, email=?, grade=?, point=? where id=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, ctf2.getText());
			pstmt.setString(2, ctf3.getText());
			pstmt.setString(3, ctf4.getText());
			pstmt.setString(4, ctf5.getText());
			pstmt.setString(5, ctf6.getText());
			pstmt.setInt(6, Integer.parseInt(ctf7.getText()));
			pstmt.setString(7, ctf1.getText());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (ClassNotFoundException eee) {

		} catch (SQLException ee) {
			System.err.println(ee.toString());
		}
		ctf1.setText(null);
		ctf2.setText(null);
		ctf3.setText(null);
		ctf4.setText(null);
		ctf5.setText(null);
		ctf6.setText(null);
		ctf7.setText(null);
		ctf1.requestFocus();
	}
	
	// �� ����
	public void deletecustomer(){
		JTable tb = customertableView;
		int deleteRow = tb.getSelectedRow(); // ���� �������� �ʾ������ -1�� �����Ѵ�.
		if (deleteRow == -1) {
			return;
		}
		DefaultTableModel model = (DefaultTableModel) tb.getModel();
		model.removeRow(deleteRow);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "delete from customer where id=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, ctf1.getText());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (ClassNotFoundException eee) {

		} catch (SQLException ee) {
			System.err.println(ee.toString());
		}
		ctf1.setText(null);
		ctf2.setText(null);
		ctf3.setText(null);
		ctf4.setText(null);
		ctf5.setText(null);
		ctf6.setText(null);
		ctf7.setText(null);
		ctf1.requestFocus();
		
	}
	
	// ���� ����
	public void viewshow(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);

			String query = "select show.sid, sname, sloc, sprice, simg, dtdate from show, detshow where show.sid=detshow.sid";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd kk:mm");
			
			while (rs.next()) {
				
				Vector<String> vector = new Vector<String>();
				vector.add(rs.getString("SID"));
				vector.add(rs.getString("SNAME"));
				vector.add(rs.getString("SLOC"));
				vector.add(rs.getString("SPRICE"));
				vector.add(rs.getString("SIMG"));
				vector.add(sdf.format(rs.getTimestamp("DTDATE")));
				showdata.addElement(vector);
			}
			rs.close();
			pstmt.close();
				
		}catch (ClassNotFoundException eee) {
			System.out.println("���� �ҷ����� ����");
		} catch (SQLException e) {
			System.err.println("���� �ҷ����� ����2");
		}
	}
	
	// ����  �߰�
		public void addshow() throws ParseException{
			Vector<String> vector = new Vector<String>();
			vector.add(sstf1.getText());
			vector.add(sstf2.getText());
			vector.add(sstf3.getText());
			vector.add(sstf4.getText());
			vector.add(sstf5.getText());
			vector.add(sstf6.getText());
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, id, pass);
				String query = "insert into show values(?,?,?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, Integer.parseInt(sstf1.getText()));
				pstmt.setString(2, sstf2.getText());
				pstmt.setString(3, sstf3.getText());
				pstmt.setInt(4, Integer.parseInt(sstf4.getText()));
				pstmt.setString(5, sstf5.getText());
				pstmt.executeUpdate();
				pstmt.close();
			} catch (ClassNotFoundException eee) {

			} catch (SQLException ee) {
				System.err.println("���� �߰� ����!!!");
			}
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, id, pass);
				String query = "insert into detshow(sid, dtdate) values(?,?)";
				PreparedStatement pstmt = conn.prepareStatement(query);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd kk:mm");
				String time = sstf6.getText().trim();
				Date parsedDate = sdf.parse(time);
				Timestamp writeDate = new Timestamp(parsedDate.getTime());
				
				pstmt.setInt(1, Integer.parseInt(sstf1.getText()));
				pstmt.setTimestamp(2, writeDate);
				pstmt.executeUpdate();
				pstmt.close();
			} catch (ClassNotFoundException eee) {

			} catch (SQLException ee) {
				System.err.println("���� �߰� ����!!!");
			}
			sstf1.setText(null);
			sstf2.setText(null);
			sstf3.setText(null);
			sstf4.setText(null);
			sstf5.setText(null);
			sstf6.setText(null);
			sstf1.requestFocus();
			showdata.addElement(vector);
			showdatamodel.fireTableDataChanged();
		}
		
		// ���� ����
		public void updateshow() throws ParseException{
			updateshowRow = showtableView.getSelectedRow();
			showtableView.setValueAt(sstf1.getText(), updateshowRow, 0);
			showtableView.setValueAt(sstf2.getText(), updateshowRow, 1);
			showtableView.setValueAt(sstf3.getText(), updateshowRow, 2);
			showtableView.setValueAt(sstf4.getText(), updateshowRow, 3);
			showtableView.setValueAt(sstf5.getText(), updateshowRow, 4);
			showtableView.setValueAt(sstf6.getText(), updateshowRow, 5);
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, id, pass);
				String query = "update show set sname=?, sloc=?, sprice=?, simg=? where sid=?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, sstf2.getText());
				pstmt.setString(2, sstf3.getText());
				pstmt.setInt(3, Integer.parseInt(sstf4.getText()));
				pstmt.setString(4, sstf5.getText());
				pstmt.setInt(5, Integer.parseInt(sstf1.getText()));
				pstmt.executeUpdate();
				pstmt.close();
			} catch (ClassNotFoundException eee) {

			} catch (SQLException ee) {
				System.err.println(ee.toString());
			}
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, id, pass);
				String query = "update detshow set dtdate=? where sid=?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd kk:mm");
				String time = sstf6.getText().trim();
				Date parsedDate = sdf.parse(time);
				Timestamp writeDate = new Timestamp(parsedDate.getTime());
				
				pstmt.setTimestamp(1, writeDate);
				pstmt.setInt(2, Integer.parseInt(sstf1.getText()));
				pstmt.executeUpdate();
				pstmt.close();
			} catch (ClassNotFoundException eee) {

			} catch (SQLException ee) {
				System.err.println("���� ���� ����!!!");
			}
			sstf1.setText(null);
			sstf2.setText(null);
			sstf3.setText(null);
			sstf4.setText(null);
			sstf5.setText(null);
			sstf6.setText(null);
			sstf1.requestFocus();
		}
		
		// ���� ����
		public void deleteshow() throws ParseException{
			JTable tb = showtableView;
			int deleteRow = tb.getSelectedRow(); // ���� �������� �ʾ������ -1�� �����Ѵ�.
			if (deleteRow == -1) {
				return;
			}
			DefaultTableModel model = (DefaultTableModel) tb.getModel();
			model.removeRow(deleteRow);
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, id, pass);
				String query = "delete from detshow where dtdate=?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd kk:mm");
				String time = sstf6.getText().trim();
				Date parsedDate = sdf.parse(time);
				Timestamp writeDate = new Timestamp(parsedDate.getTime());
				
				pstmt.setTimestamp(1, writeDate);
				pstmt.executeUpdate();
				pstmt.close();
			} catch (ClassNotFoundException eee) {

			} catch (SQLException ee) {
				System.err.println(ee.toString());
			}
			sstf1.setText(null);
			sstf2.setText(null);
			sstf3.setText(null);
			sstf4.setText(null);
			sstf5.setText(null);
			sstf6.setText(null);
			sstf1.requestFocus();
			
		}
		

	public TotalTicket_sub123() {
		super("����");
		this.init();
		this.start();
		this.pack();
		this.setResizable(true);
		this.setSize(1080, 820);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension di = tk.getScreenSize();
		Dimension di1 = this.getSize();
		this.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
				(int) (di.getHeight() / 2 - di1.getHeight() / 2));
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
		loginplzok.addActionListener(this);
		loginx.addActionListener(this);
		joinokbt.addActionListener(this);
		joinxbt.addActionListener(this);
		loginokdlgbt.addActionListener(this);
		loginxdlgbt.addActionListener(this);
		logoutbt.addActionListener(this);
		for (int i = 0; i < num; i++) {
			mv[i].addMouseListener(this);
			mvc[i].addMouseListener(this);
		} 
		
		btnCancle.addActionListener(this);
		btnSeatSelect.addActionListener(this);
		btnFinSeat.addActionListener(this);
		btnPayDlgCancle.addActionListener(this);
		cbCount.addActionListener(this);
		cbDay.addActionListener(this);
		btnReselect.addActionListener(this);
		searchtf.addFocusListener(this);
		searchtf.addMouseListener(this);
		searchbt.addActionListener(this);
		ltdate.addActionListener(this);
		ltingi.addActionListener(this);
		adminbt.addActionListener(this);
		customerinbt.addActionListener(this);
		customerupbt.addActionListener(this);
		customerdebt.addActionListener(this);
		shoinbt.addActionListener(this);
		shoupbt.addActionListener(this);
		shodebt.addActionListener(this);
		customertableView.addMouseListener(this);
		showtableView.addMouseListener(this);


		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 16; j++) {
				btnNotSelected[i][j].addActionListener(this);
			}
		}

		// �¼� ���̾�α� ������� �� �¼� �ʱ�ȭ��Ű��
		sltSeatDlg.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				seatClear();
			}
		});

		check.addActionListener(this);
		cancel.addActionListener(this);
		updateokbt.addActionListener(this);

		// �������� ��ҹ�ư
		canceltk.addActionListener(this);
		cancelokbt.addActionListener(this);
		cancelnobt.addActionListener(this);

		// ����Ʈ(2017.5.10)
		charge.addActionListener(this);
		chargebt.addActionListener(this);
	}

	// TODO : �¼� ���õ� �¼��� ��Ȱ��ȭ �ϱ�
		public void showSeat(String seat, String time) throws ParseException {

			// String������ time�� Timestamp �������� ��ȯ
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd kk:mm");
			Date parsedDate = sdf.parse(time);
			Timestamp writeDate = new Timestamp(parsedDate.getTime());

			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 16; j++) {
					btnNotSelected[i][j].setEnabled(true);
					switch (i) {
					case 0:
						btnNotSelected[i][j].setText("A" + (j + 1));
						break;
					case 1:
						btnNotSelected[i][j].setText("B" + (j + 1));
						break;
					case 2:
						btnNotSelected[i][j].setText("C" + (j + 1));
						break;
					case 3:
						btnNotSelected[i][j].setText("D" + (j + 1));
						break;
					case 4:
						btnNotSelected[i][j].setText("E" + (j + 1));
						break;
					case 5:
						btnNotSelected[i][j].setText("F" + (j + 1));
						break;
					}
				}
			}

			try {
				// �ð� ��� ���� Ȯ�� �ڵ�
				// System.out.println("�ٲ� �ð� ��� " + sdf.format(parsedDate));
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, id, pass);
				String query = "select seatid from seat where SID = ? and DTDATE = ? ";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, seat);
				pstmt.setTimestamp(2, writeDate);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					for (int i = 0; i < 6; i++) {
						for (int j = 0; j < 16; j++) {
							if (btnNotSelected[i][j].getText().equals(rs.getString("SEATID"))) {
								btnNotSelected[i][j].setEnabled(false);
								btnNotSelected[i][j].setText("X");
							}
						}
					}
				}
				rs.close();
				pstmt.close();

			} catch (ClassNotFoundException eee) {
				System.out.println("Class ����");
			} catch (SQLException ee) {
				System.err.println("SQL ����");
			} 

		}
	
	// �¼� �ʱ�ȭ�ϱ�
	public void seatClear() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 16; j++) {
				btnNotSelected[i][j].setBackground(null);
			}
		}
		cnt = 0;
		lbSltSeat.setText(Integer.toString(cnt));
	}

	private void init() {
		// ����ȭ�� ���� - ��� �޴���
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
		sp2.add(adminbt);
		mypagebt.setVisible(false);
		lb.setVisible(false);
		logoutbt.setVisible(false);
		adminbt.setVisible(false);
		mp.add("East", sp2);

		for (int i = 0; i < num; i++) {
			mv[i] = new JLabel();
			mvc[i] = new JLabel();
			mvst[i] = null;
			image[i] = null;
		}

		// DB���� �̹��� �������°� ����
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "select * from show";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				image[i] = new ImageIcon(rs.getString("SIMG"));
				mv[i] = new JLabel(rs.getString("SNAME"), image[i], JLabel.CENTER);
				mvc[i] = new JLabel(rs.getString("SNAME"), image[i], JLabel.CENTER);

				i++;
			}

			rs.close();
			pstmt.close();
		} catch (ClassNotFoundException eee) {
			System.err.println("�α��� ����?!!!");
		} catch (SQLException e) {
			System.err.println("�α��� ����?!!!");
		}

		// ����ȭ�� ���� - �߾� Ƽ��ȭ�� ����
		tpmain.setBorder(new BevelBorder(BevelBorder.RAISED));

		// �˻�â(North)
		ch1.add("----------");
		ch1.add("Ƽ���̸�");
		ch1.add("������¥");

		search.add("West", ch1);
		search.add("Center", searchtf);
		search.add("East", searchbt);

		// �˻� ���â
		
		tpmain.add("Center", srchresult);
		
				
		// �α��, ��¥��(East)
		tlingi.add("North", lbingi);
		tlingi.add("Center", ltingi);
		tklistpn.add(tlingi);
		tldate.add("North", lbdate);
		tldate.add("Center", ltdate);
		tklistpn.add(tldate);

		// �߾��� Ƽ��ȭ�� ����(Center)
		// tp.setBorder(new BevelBorder(BevelBorder.RAISED));

		for (int i = 0; i < num; i++) {
			mv[i].setVerticalTextPosition(SwingConstants.BOTTOM);
			mv[i].setHorizontalTextPosition(SwingConstants.CENTER);
			tp.add(mv[i]);
		} 
		
		tpmain.add("East", tklistpn);
		tpmain.add("North", search);
		tpmain.add("Center", tp);
		con.add("North", mp);
		con.add("Center", tpmain);

		

		// ȸ������ ���̾�α� ����
		joincon = joindlg.getContentPane();
		joincon.setLayout(new BorderLayout());
		joindlg.setSize(300, 220);
		joindlg.setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension di = tk.getScreenSize();
		Dimension di1 = joindlg.getSize();
		joindlg.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
				(int) (di.getHeight() / 2 - di1.getHeight() / 2));

		JPanel joinp = new JPanel(new GridLayout(6, 2));
		joinp.add(joinidlb);
		joinp.add(joinidtf);
		joinp.add(joinpwlb);
		joinp.add(joinpwtf);
		joinp.add(joinpwoklb);
		joinp.add(joinpwoktf);
		joinp.add(jointellb);
		joinp.add(jointeltf);
		joinp.add(joinniklb);
		joinp.add(joinniktf);
		joinp.add(joinemaillb);
		joinp.add(joinemailtf);

		JPanel join = new JPanel(new FlowLayout());
		join.add(joinp);

		JPanel joinbtp = new JPanel(new FlowLayout());
		joinbtp.add(joinok);
		joinbtp.add(joinx);

		joincon.add("Center", join);
		joincon.add("South", joinbtp);

		// ȸ������ �Ϸ� ���̾�α� ����
		joinokcon = joinokdlg.getContentPane();
		joinokcon.setLayout(new BorderLayout());
		joinokdlg.setSize(300, 150);
		joinokdlg.setResizable(false);
		Dimension di3 = joinokdlg.getSize();
		joinokdlg.setLocation((int) (di.getWidth() / 2 - di3.getWidth() / 2),
				(int) (di.getHeight() / 2 - di3.getHeight() / 2));

		JPanel joinokp = new JPanel(new GridLayout(4, 2));
		joinokp.add(joinokidlb);
		joinokp.add(joinokidtf);
		joinokp.add(joinoktellb);
		joinokp.add(joinokteltf);
		joinokp.add(joinokniklb);
		joinokp.add(joinokniktf);
		joinokp.add(joinokemaillb);
		joinokp.add(joinokemailtf);

		JPanel joinok = new JPanel(new FlowLayout());
		joinok.add(joinokp);

		JPanel joinokbtp = new JPanel(new FlowLayout());
		joinokbtp.add(joinokbt);

		joinokcon.add("Center", joinok);
		joinokcon.add("South", joinokbtp);

		// ȸ������ ���� ���̾�α� ����
		joinxcon = joinxdlg.getContentPane();
		joinxcon.setLayout(new BorderLayout());
		joinxdlg.setSize(230, 100);
		joinxdlg.setResizable(false);
		Dimension di4 = joinxdlg.getSize();
		joinxdlg.setLocation((int) (di.getWidth() / 2 - di4.getWidth() / 2),
				(int) (di.getHeight() / 2 - di4.getHeight() / 2));

		JPanel joinxlbp = new JPanel(new FlowLayout());
		joinxlbp.add(joinxlb);

		JPanel joinxbtp = new JPanel(new FlowLayout());
		joinxbtp.add(joinxbt);

		joinxcon.add("Center", joinxlbp);
		joinxcon.add("South", joinxbtp);

		// �α��� ���̾�α� ����
		logincon = logindlg.getContentPane();
		logincon.setLayout(new BorderLayout());
		logindlg.setSize(300, 120);
		logindlg.setResizable(false);
		Dimension di2 = logindlg.getSize();
		logindlg.setLocation((int) (di.getWidth() / 2 - di2.getWidth() / 2),
				(int) (di.getHeight() / 2 - di2.getHeight() / 2));

		JPanel loginp = new JPanel(new GridLayout(2, 2));
		loginp.add(loginidlb);
		loginp.add(loginidtf);
		loginp.add(loginpwlb);
		loginp.add(loginpwtf);

		JPanel login = new JPanel(new FlowLayout());
		login.add(loginp);

		JPanel loginbtp = new JPanel(new FlowLayout());
		loginbtp.add(loginok);
		loginbtp.add(loginx);

		logincon.add("Center", login);
		logincon.add("South", loginbtp);

		// �α��� �Ϸ� ���̾�α� ����
		loginokcon = loginokdlg.getContentPane();
		loginokcon.setLayout(new BorderLayout());
		loginokdlg.setSize(300, 100);
		loginokdlg.setResizable(false);
		Dimension di6 = loginokdlg.getSize();
		loginokdlg.setLocation((int) (di.getWidth() / 2 - di6.getWidth() / 2),
				(int) (di.getHeight() / 2 - di6.getHeight() / 2));

		JPanel loginokp = new JPanel(new FlowLayout());
		loginokp.add(loginokdlglb1);
		loginokp.add(loginokdlglb2);

		JPanel loginokbtp = new JPanel(new FlowLayout());
		loginokbtp.add(loginokdlgbt);

		loginokcon.add("Center", loginokp);
		loginokcon.add("South", loginokbtp);

		// �α��� ���� ���̾�α� ����
		loginxcon = loginxdlg.getContentPane();
		loginxcon.setLayout(new BorderLayout());
		loginxdlg.setSize(300, 100);
		loginxdlg.setResizable(false);
		Dimension di5 = loginxdlg.getSize();
		loginxdlg.setLocation((int) (di.getWidth() / 2 - di5.getWidth() / 2),
				(int) (di.getHeight() / 2 - di5.getHeight() / 2));

		JPanel loginxp = new JPanel(new FlowLayout());
		loginxp.add(loginxdlglb);

		JPanel loginxbtp = new JPanel(new FlowLayout());
		loginxbtp.add(loginxdlgbt);

		loginxcon.add("Center", loginxp);
		loginxcon.add("South", loginxbtp);

		////////////////////// ������ ����
		tbuy.setLayout(new BoxLayout(tbuy, BoxLayout.Y_AXIS));

		t3lb.setPreferredSize(new Dimension(300, 60));

		JPanel t1in = new JPanel(new BorderLayout(3, 3));
		JPanel t1show = new JPanel(new BorderLayout(3, 3));
		JPanel t1infop = new JPanel(new GridLayout(2, 1));
		Checkbox buycb = new Checkbox();
		Label t1name = new Label("Ư���ù�", Label.CENTER);
		Label t1date = new Label("2017-04-23", Label.CENTER);
		JPanel t1 = new JPanel();
		JLabel t1lb = new JLabel(image[0]);
		JPanel t2in = new JPanel(new BorderLayout(3, 3));
		JPanel t2show = new JPanel(new BorderLayout(3, 3));
		JPanel t2infop = new JPanel(new GridLayout(2, 1));
		Checkbox buycb1 = new Checkbox();
		Label t2name = new Label("�ƺ��µ�", Label.CENTER);
		Label t2date = new Label("2017-04-12", Label.CENTER);
		JPanel t2 = new JPanel();
		JLabel t2lb = new JLabel(image[1]);

		t1infop.add(t1name);
		t1infop.add(t1date);
		t1show.add("Center", t1lb);
		t1show.add("East", t1infop);
		t1in.add("Center", t1show);
		t1in.add("West", buycb);
		t1.add(t1in);
		t2infop.add(t2name);
		t2infop.add(t2date);
		t2show.add("Center", t2lb);
		t1show.add("East", t2infop);
		t2in.add("Center", t2show);
		t2in.add("West", buycb1);
		t2.add(t2in);
		t3ini.add("Center", t3lb);
		t3in.add(t3ini);
		t3.add(t3in);

		tbuy.add(t1);
		tbuy.add(t2);
		tbuy.add(t3);

		buyer_Top_btp1.add(tupbt);
		buyer_Top_btp2.add(tbuybt);
		buyer_Top_btp.add(buyer_Top_btp1);
		buyer_Top_btp.add(buyer_Top_btp2);

		buyerjp.add("North", buyer_Top_btp);
		buyerjp.add("Center", jstbuy);

		BuyerP.add("North", Buyerlb);
		BuyerP.add("Center", buyerjp);

		MainP.add(tpmain);
		MainP.add(BuyerP);

		con.add("North", mp); // ����
		con.add("Center", MainP);

		// ������ ��� ���̾�α� ����
		admincon = admindlg.getContentPane();
		admindlg.setLayout(new FlowLayout());
		admindlg.setSize(1200, 600);
		Dimension di8 = admindlg.getSize();
		admindlg.setLocation((int) (di.getWidth() / 2 - di8.getWidth() / 2),
				(int) (di.getHeight() / 2 - di8.getHeight() / 2));
		adminPane.setPreferredSize(new Dimension(1000, 550));
		adminPane.add("�� ����", customerP);
		adminPane.add("���� ����", showP);
		adminPane.setTabPlacement(JTabbedPane.LEFT);
		
		// ������ ��� �� ���� ����
		
		
		customerdata = new Vector<Vector<String>>();
		customercolNames = new Vector<String>();
		
		for (int i = 0; i < customercolName.length; i++) {
			customercolNames.add(customercolName[i]);
		}
		customerdatamodel = new DefaultTableModel(customerdata, customercolNames);
		customertableView = new JTable(customerdatamodel);
		customerscrollList = new JScrollPane(customertableView);
		
		customerP.add("North", customerscrollList);
		
		cp1.add(clb1); cp1.add(ctf1);
		cp2.add(clb2); cp2.add(ctf2);
		cp3.add(clb3); cp3.add(ctf3);
		cp4.add(clb4); cp4.add(ctf4);
		cp5.add(clb5); cp5.add(ctf5);
		cp6.add(clb6); cp6.add(ctf6);
		cp7.add(clb7); cp7.add(ctf7);
		
		cp.add(cp1); cp.add(cp2); cp.add(cp3); cp.add(cp4); cp.add(cp5); cp.add(cp6); cp.add(cp7);
		
		customerP.add("Center", cp);
		
		cus.add(customerinbt);
		cus.add(customerupbt);
		cus.add(customerdebt);
		
		customerP.add("South", cus);
		
		
		// ������ ��� ���� ���� ����
		
		
		showdata = new Vector<Vector<String>>();
		showcolNames = new Vector<String>();
		
		for (int i = 0; i < showcolName.length; i++) {
			showcolNames.add(showcolName[i]);
		}
		
		showdatamodel = new DefaultTableModel(showdata, showcolNames);
		showtableView = new JTable(showdatamodel);
		showscrollList = new JScrollPane(showtableView);
		
		showP.add("North", showscrollList);
		
		ssp1.add(sslb1); ssp1.add(sstf1);
		ssp2.add(sslb2); ssp2.add(sstf2);
		ssp3.add(sslb3); ssp3.add(sstf3);
		ssp4.add(sslb4); ssp4.add(sstf4);
		ssp5.add(sslb5); ssp5.add(sstf5);
		ssp6.add(sslb6); ssp6.add(sstf6);
		
		ssp.add(ssp1); ssp.add(ssp2); ssp.add(ssp3); ssp.add(ssp4); ssp.add(ssp5); ssp.add(ssp6);
		
		showP.add("Center", ssp);
		
		
		sho.add(shoinbt);
		sho.add(shoupbt);
		sho.add(shodebt);
		
		showP.add("South", sho);
		
		admincon.add(adminPane);
		
		// ���� �����̳� ����
		rsvCon = rsvDlg.getContentPane();
		rsvDlg.setLayout(new BorderLayout());
		rsvDlg.setSize(600, 400);
		rsvDlg.setResizable(false);
		
		
		// ��α��ν� ���ٸ��̴� �����̳� ����
		loginplzCon = loginplzdlg.getContentPane();
		loginplzdlg.setLayout(new BorderLayout());
		JPanel loginplzbtpn = new JPanel(new FlowLayout());
		JPanel loginplzlbpn = new JPanel(new FlowLayout());
		loginplzdlg.setLocation((int) (di.getWidth() / 2 - di5.getWidth() / 2),
				(int) (di.getHeight() / 2 - di5.getHeight() / 2));
		loginplzdlg.setSize(270, 150);
		loginplzlbpn.add(loginplzlb);
		loginplzbtpn.add(loginplzok);
		loginplzCon.add("Center", loginplzlbpn);
		loginplzCon.add("South", loginplzbtpn);
		
		
		// �¼� ���� �����̳� ����
		sltSeatCon = sltSeatDlg.getContentPane();
		sltSeatDlg.setLayout(new BorderLayout(50, 50));
		sltSeatDlg.setSize(1000, 450);

		// ���� �����̳� ����
		payCon = payDlg.getContentPane();
		payDlg.setLayout(new BorderLayout(40, 40));
		payDlg.setSize(350, 150);

		// ��ġ ����
		Dimension dm1 = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dm2 = rsvDlg.getSize();
		Dimension dm3 = sltSeatDlg.getSize();
		Dimension dm4 = payDlg.getSize();

		rsvDlg.setLocation((int) (dm1.getWidth() / 2 - dm2.getWidth() / 2),
				(int) (dm1.getHeight() / 2 - dm2.getHeight() / 2));

		sltSeatDlg.setLocation((int) (dm1.getWidth() / 2 - dm3.getWidth() / 2),
				(int) (dm1.getHeight() / 2 - dm3.getHeight() / 2));

		payDlg.setLocation((int) (dm1.getWidth() / 2 - dm4.getWidth() / 2),
				(int) (dm1.getHeight() / 2 - dm4.getHeight() / 2));

		JPanel pl3 = new JPanel(new BorderLayout());

		/* ���� ���̾�α� */
		// ���� �� ����
		JPanel pl1 = new JPanel(new GridLayout(5, 1));
		pl1.add(lbTicketNum);
		pl1.add(lbName);
		pl1.add(lbLoc);
		pl1.add(lbPrice);
		pl3.add("West", pl1);

		// ������ �� ����
		JPanel pl2 = new JPanel(new GridLayout(5, 1));
		pl2.add(lbTicketNumDB);
		pl2.add(lbNameDB);
		pl2.add(lbLocDB);
		pl2.add(lbPriceDB);
		pl3.add("Center", pl2);
		pl3.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

		JPanel pl5 = new JPanel(new BorderLayout());

		// ���� ���� (������ ����, ���� ����)
		pl5.add("Center", pl3);
		pl5.add("West", imgPosterLb);
		// pl5.setBorder(new TitledBorder(new
		// SoftBevelBorder(SoftBevelBorder.RAISED), "���� ����"));

		// ������ �޷�, �������� �ο���, �¼�����, ��ҹ�ư �߰�
		JPanel pl7 = new JPanel(new GridLayout(1, 2));

		// �ο���, �¼� ����, ��� ��ư
		JPanel pl6 = new JPanel(new GridLayout(2, 1));

		// �ο��� ����
		JPanel pl8 = new JPanel(new FlowLayout());
		pl8.add(lbSelectPerson);
		pl8.add(cbCount);

		// �¼� ����, ��� ��ư
		JPanel pl4 = new JPanel(new FlowLayout());
		pl4.add(btnSeatSelect);
		pl4.add(btnCancle);

		// �޺��ڽ��� �¼� ����, ��� ��ư �߰�
		pl6.add(pl8);
		pl6.add(pl4);

		// ��¥ ���� �޺��ڽ�
		JPanel p13 = new JPanel(new FlowLayout());
		p13.add(lbDay);
		p13.add(cbDay);

		pl7.add(p13);
		pl7.add(pl6);

		/* �¼� ���� ���̾�α� */
		// ������ �Ʒ��� (���� �¼��� �¼� ����)
		JPanel p10 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

		lbSltSeat.setFont(new Font("Sherif", Font.PLAIN, 16));
		lbSlash.setFont(new Font("Sherif", Font.PLAIN, 16));
		lbAllSeat.setFont(new Font("Sherif", Font.PLAIN, 16));
		btnFinSeat.setFont(new Font("Sherif", Font.PLAIN, 16));
		btnReselect.setFont(new Font("Sherif", Font.PLAIN, 16));

		p10.add(lbSltSeat);
		p10.add(lbSlash);
		p10.add(lbAllSeat);

		JPanel p11 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		p11.add(p10);
		p11.add(btnReselect);
		p11.add(btnFinSeat);

		// �¼� �׸��� ����� imgSelected, imgNotSelected, imgNotAble
		JLabel lbScreen = new JLabel("�� ��", JLabel.CENTER);
		lbScreen.setFont(new Font("Sherif", Font.PLAIN, 30));

		JPanel plAllSection = new JPanel(new GridLayout(1, 4, 7, 7));
		JPanel plSection1 = new JPanel(new GridLayout(6, 4));
		JPanel plSection2 = new JPanel(new GridLayout(6, 4));
		JPanel plSection3 = new JPanel(new GridLayout(6, 4));
		JPanel plSection4 = new JPanel(new GridLayout(6, 4));

		// �¼� ���� �׸���
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 16; j++) {
				switch (i) {
				case 0:
					btnNotSelected[i][j] = new JButton("A" + (j + 1));
					break;
				case 1:
					btnNotSelected[i][j] = new JButton("B" + (j + 1));
					break;
				case 2:
					btnNotSelected[i][j] = new JButton("C" + (j + 1));
					break;
				case 3:
					btnNotSelected[i][j] = new JButton("D" + (j + 1));
					break;
				case 4:
					btnNotSelected[i][j] = new JButton("E" + (j + 1));
					break;
				case 5:
					btnNotSelected[i][j] = new JButton("F" + (j + 1));
					break;
				}
				btnNotSelected[i][j].setSize(50, 50);

				if (j < 4)
					plSection1.add(btnNotSelected[i][j]);
				else if (j >= 4 && j < 8)
					plSection2.add(btnNotSelected[i][j]);
				else if (j >= 8 && j < 12)
					plSection3.add(btnNotSelected[i][j]);
				else if (j >= 12 && j < 16)
					plSection4.add(btnNotSelected[i][j]);
			}
		}

		plAllSection.add(plSection1);
		plAllSection.add(plSection2);
		plAllSection.add(plSection3);
		plAllSection.add(plSection4);
				
		/* ���� ���̾�α� ���� */
		JPanel pl12 = new JPanel(new FlowLayout());
		pl12.add(lbPay);
		pl12.add(lbToPay);

		JPanel pl13 = new JPanel(new FlowLayout());
		pl13.add(btnPayDlgPay);
		pl13.add(btnPayDlgCancle);

		rsvCon.add("Center", pl5);
		rsvCon.add("South", pl7);
		lbScreen.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

		sltSeatCon.add("North", lbScreen);
		sltSeatCon.add("Center", plAllSection);
		sltSeatCon.add("South", p11);

		payCon.add("North", pl12);
		payCon.add("Center", pl13);

		// ����������
		tPane.setPreferredSize(new Dimension(1000, 550));
		tPane.add("����������", myP);
		tPane.add("����", edit);
		tPane.add("����Ʈ", pointp);
		tPane.add("��������", show);
		tPane.setTabPlacement(JTabbedPane.LEFT);

		p2_1.add(eID);
		p2_1.add(eID1);
		p2_2.add(ephone);
		p2_2.add(tphone);
		p2_3.add(ename);
		p2_3.add(tname);
		p2_4.add(eemail);
		p2_4.add(tmail);
		p2_5.add(elevel);
		p2_5.add(elevel1);
		p2_6.add(check);
		p2_6.add(cancel);

		p22.add(p2_1);
		p22.add(p2_2);
		p22.add(p2_3);
		p22.add(p2_4);
		p22.add(p2_5);
		p22_2.add(p22);
		p2_6_2.add(p2_6);
		edit.add("Center", p22_2);
		edit.add("South", p2_6_2);

		pointP1.setLayout(gridb);
		constraint.fill = GridBagConstraints.BOTH;

		constraint.gridx = 0;
		constraint.gridy = 0;
		pointP1.add(point2, constraint);
		constraint.gridx = 0;
		constraint.gridy = 2;
		pointP1.add(point3, constraint);
		constraint.gridx = 0;
		constraint.gridy = 3;
		pointP1.add(point4, constraint);
		constraint.gridx = 0;
		constraint.gridy = 4;
		pointP1.add(point5, constraint);

		pointP.add(point);
		pointP.add(point1);
		chargeP.add(charge);
		pointp.add("North", pointP);
		pointp.add("South", chargeP);
		pointp.add("Center", pointP1);

		////////// ����Ʈ����(2017.5.10)
		chargecon = chargedlg.getContentPane();
		chargecon.setLayout(new BorderLayout());
		chargedlg.setSize(300, 220);
		chargedlg.setResizable(false);
		tk = Toolkit.getDefaultToolkit();
		di = tk.getScreenSize();
		di1 = chargedlg.getSize();
		chargedlg.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
				(int) (di.getHeight() / 2 - di1.getHeight() / 2));

		chargep.add(chargelb);
		chargep.add(chargetf);
		chargep.add(chargebt);

		chargecon.add("Center", chargep);
		/////////////////////////////////////////
		p4_1.add(id1);
		p4_1.add(id2);
		p4_2.add(phone1);
		p4_2.add(phone2);
		p4_3.add(nname1);
		p4_3.add(nname2);
		p4_4.add(email1);
		p4_4.add(email2);
		p4_5.add(level1);
		p4_5.add(level2);
		p4.add(p4_1);
		p4.add(p4_2);
		p4.add(p4_3);
		p4.add(p4_4);
		p4.add(p4_5);
		p5.add("West", p4);

		///////////////////////////////// ��������

		showtkp.setLayout(new BoxLayout(showtkp, BoxLayout.Y_AXIS));
		t1lb1.setPreferredSize(new Dimension(300, 60));
		t1ini1.add("West", cancelcb);
		t1ini1.add("Center", t1lb1);
		t1ini1.add("East", tbuybt1);
		t1in1.add(t1ini1);
		t11.add(t1in1);

		t2lb1.setPreferredSize(new Dimension(300, 60));
		t2ini1.add("West", cancelcb2);
		t2ini1.add("Center", t2lb1);
		t2ini1.add("East", tbuybt21);
		t2in1.add(t2ini1);
		t21.add(t2in1);

		t3lb1.setPreferredSize(new Dimension(300, 60));
		t3ini1.add("West", cancelcb3);
		t3ini1.add("Center", t3lb1);
		t3ini1.add("East", tbuybt31);
		t3in1.add(t3ini1);
		t31.add(t3in1);

		showtkp.add(t11);
		showtkp.add(t21);
		showtkp.add(t31);
		show.add("North", canceltk);
		show.add("Center", showtkp);
		//////////////////////////////////// ������Ҵ��̾�α�

		canceldlgp.add(cancelokbt);
		canceldlgp.add(cancelnobt);

		realcancelp.add("North", realcancel);
		realcancelp.add("Center", canceldlgp);
		canceldlg.add(realcancelp);

		///////////////////////////////////////////////////

		myP.add("Center", p5);
		mypagep.add(tPane);
		MainP.add(mypagep);

		// ���� �Ϸ� ���̾�α�
		updateokcon = updateokdlg.getContentPane();
		updateokcon.setLayout(new BorderLayout());
		updateokdlg.setSize(300, 150);
		updateokdlg.setResizable(false);
		Dimension di7 = updateokdlg.getSize();
		updateokdlg.setLocation((int) (di.getWidth() / 2 - di7.getWidth() / 2),
				(int) (di.getHeight() / 2 - di7.getHeight() / 2));

		JPanel updateokp = new JPanel(new GridLayout(4, 2));
		updateokp.add(updateokidlb);
		updateokp.add(updateokidtf);
		updateokp.add(updateoktellb);
		updateokp.add(updateokteltf);
		updateokp.add(updateokniklb);
		updateokp.add(updateokniktf);
		updateokp.add(updateokemaillb);
		updateokp.add(updateokemailtf);

		JPanel updateok = new JPanel(new FlowLayout());
		updateok.add(updateokp);

		JPanel updateokbtp = new JPanel(new FlowLayout());
		updateokbtp.add(updateokbt);

		updateokcon.add("Center", updateok);
		updateokcon.add("South", updateokbtp);

		tpmain.setVisible(true);
		bestshow();
		dateshow();
	}
	int cnt = 0;
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		// �޺� �ڽ� ���� �ο���
		strPersonCnt = cbCount.getSelectedItem().toString();
		lbAllSeat.setText(strPersonCnt);

		if (e.getSource() == Buyer_bt) { // ������ ����
			tpmain.setVisible(false);
			srchresult.setVisible(false);
			BuyerP.setVisible(true);
		} // ������ ����

		if (e.getSource() == tbuybt) {
			if (buycb.getState() == true) {
				System.out.println("???");
				t1.setVisible(false);
			}
		}
		
		// �˻���ư
		else if (e.getSource() == searchbt) {
			srchresult.setVisible(true);
			srchresult.removeAll();
			String rslt = searchtf.getText().trim();
			for (int i = 0; i < num; i++) {
				mvst[i] = mv[i].getText().trim();
			} 
			try {
				for (int i = 0; i < num; i++) {
					if (mvst[i].matches(".*" + rslt + ".*")) {
						mvc[i].setVerticalTextPosition(SwingConstants.BOTTOM);
						mvc[i].setHorizontalTextPosition(SwingConstants.CENTER);
						srchresult.add(mvc[i]);
					}
				}
					} catch (PatternSyntaxException ee) {
				System.err.println(ee);
			}
			tp.setVisible(false);
			tpmain.add("Center",srchresult);
			searchtf.setText("���� �Ǵ� ��¥ �˻�");
			srchresult.setVisible(true);
		}

		else if (e.getSource() == joinbt) { // ȸ������
			// tp.setVisible(false);
			joindlg.setVisible(true);
		} // ȸ������

		else if (e.getSource() == joinok) { // ȸ������ ���̾�α��� �Ϸ�
			String a1 = joinidtf.getText().trim();
			String b1 = new String(joinpwtf.getPassword());
			String c1 = new String(joinpwoktf.getPassword());
			String d1 = jointeltf.getText().trim();
			String e1 = joinniktf.getText().trim();
			String f1 = joinemailtf.getText().trim();
			if (!b1.equals(c1)) {
				joinxdlg.setVisible(true);
			} else {
				joinMember();
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

		else if (e.getSource() == joinokbt) { // ȸ������ ���̾�α��� �Ϸ� ���̾�α��� Ȯ�ι�ư
			joinokidtf.setText("");
			joinokteltf.setText("");
			joinokniktf.setText("");
			joinokemailtf.setText(" ");
			joinokemailtf.setText("");
			joinokdlg.setVisible(false);
		} // ȸ������ ���̾�α��� �Ϸ� ���̾�α��� Ȯ�ι�ư

		else if (e.getSource() == joinx) { // ȸ������ ���̾�α��� ���
			joinidtf.setText("");
			joinpwtf.setText("");
			joinpwoktf.setText("");
			jointeltf.setText("");
			joinniktf.setText("");
			joinemailtf.setText(" ");
			joinemailtf.setText("");
			joindlg.setVisible(false);
		} // ȸ������ ���̾�α��� ���

		else if (e.getSource() == loginbt) { // �α���
			// tp.setVisible(false);
			logindlg.setVisible(true);
		} // �α���

		else if (e.getSource() == loginok) { // �α��� ���̾�α��� �Ϸ�
			loginMember();
			
		} // �α��� ���̾�α��� �Ϸ�

		else if (e.getSource() == loginx) { // �α��� ���̾�α��� ���
			loginidtf.setText("");
			loginpwtf.setText(" ");
			loginpwtf.setText("");
			logindlg.setVisible(false);

		} // �α��� ���̾�α��� ���

		else if (e.getSource() == joinxbt) { // ȸ������ ���д��̾�α� Ȯ�ι�ư
			joinxdlg.setVisible(false);
			joinpwtf.setText("");
			joinpwoktf.setText(" ");
			joinpwoktf.setText("");
		} // ȸ������ ���д��̾�α� Ȯ�ι�ư

		else if (e.getSource() == loginokdlgbt) { // �α��� ���� ���̾�α� Ȯ�ι�ư
					
			loginidtf.setText("");
			loginpwtf.setText(" ");
			loginpwtf.setText("");
			loginokdlg.setVisible(false);
			logindlg.setVisible(false);

			loginbt.setVisible(false);
			joinbt.setVisible(false);
			lb.setVisible(true);

			logoutbt.setVisible(true);
			mypagebt.setVisible(true);
		} // �α��� ���� ���̾�α� Ȯ�ι�ư

		else if (e.getSource() == loginxdlgbt) { // �α��� ���� ���̾�α� Ȯ�ι�ư
			loginidtf.setText("");
			loginpwtf.setText(" ");
			loginpwtf.setText("");
			loginxdlg.setVisible(false);
		} // �α��� ���� ���̾�α� Ȯ�ι�ư

		else if (e.getSource() == logoutbt) { // �α׾ƿ� ��ư
			lb.setVisible(false);
			lb.setText(" ");
			lb.setText("");
			tpmain.setVisible(true);
			logoutbt.setVisible(false);
			mypagebt.setVisible(false);
			loginbt.setVisible(true);
			joinbt.setVisible(true);
			mypagep.setVisible(false);
			loginokdlglb1.setText(" ");
			loginokdlglb1.setText("");
			adminbt.setVisible(false);
			logoutMember();
		} // �α׾ƿ� ��ư

		else if (e.getSource() == btnCancle) { // ��� ��ư
			rsvDlg.setVisible(false);
		} 
		
		else if (e.getSource() == btnSeatSelect) { // �¼� ���� �κ� ������ ��
			// �޺� �ڽ� ��¥ ���� ��
			cbSelectedDate = cbDay.getSelectedItem().toString();

			if(loginokdlglb1.getText()==""){
				loginplzdlg.setVisible(true);
				return;
			}else{
				try { // DB�� �ִ� �¼� �ҷ�����
					showSeat(selectedSid, cbSelectedDate);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}sltSeatDlg.setVisible(true);
			}
		} 
		
		else if (e.getSource() == loginplzok){	//�α��� �ش޶�� ��ư
			loginplzdlg.setVisible(false);
		} 
		
		else if (e.getSource() == btnFinSeat) { // �¼� ���� �Ϸ�
			lbToPay.setText(String.valueOf(Integer.parseInt(strPersonCnt) * showPrice));
			payDlg.setVisible(true);
		} 
		
		else if (e.getSource() == btnPayDlgCancle) {
			payDlg.setVisible(false);
		} 
		
		else if (e.getSource() == btnReselect) { // �¼� �ٽ� ����
			try {
				showSeat(selectedSid, cbSelectedDate);
				seatClear();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
		
		else if (e.getSource() == mypagebt) { // ����������
			tpmain.setVisible(false);
			srchresult.setVisible(false);
			BuyerP.setVisible(false);
			mypagep.setVisible(true);
		} // ����������
		
		
		else if (e.getSource() == check) { // ���������� ���� Ȯ�ι�ư
			updateMember();
			updateokdlg.setVisible(true);

		} // ���������� ���� Ȯ�ι�ư
		else if (e.getSource() == cancel) { // ���������� ���� ��ҹ�ư
			tphone.setText(phone2.getText().trim());
			tname.setText(nname2.getText().trim());
			tmail.setText(email2.getText().trim());

		} // ���������� ���� ��ҹ�ư

		else if (e.getSource() == updateokbt) { // ���� �Ϸ� ���̾�α� Ȯ�ι�ư
			updateokdlg.setVisible(false);

		} // ���� �Ϸ� ���̾�α� Ȯ�ι�ư

		///////////////////////////////////////////////////////// ����Ʈ

		else if (e.getSource() == charge) {
			chargedlg.setVisible(true);
		} // ����Ʈ ������ư
		
		else if (e.getSource() == chargebt) {
			chargepoint();
			chargedlg.setVisible(false);
		}
		///////////////////////////////////////////////////// �������� �������

		else if (e.getSource() == canceltk) {
			if (cancelcb.getState() == true) {
				canceldlg.setSize(200, 200);
				canceldlg.setVisible(true);
			}
		} 
		
		else if (e.getSource() == cancelokbt) {
			if (cancelcb.getState() == true) {
				t11.setVisible(false);
				canceldlg.setVisible(false);
			}
			if (cancelcb2.getState() == true) {
				t21.setVisible(false);
				canceldlg.setVisible(false);
			}
			if (cancelcb3.getState() == true) {
				t31.setVisible(false);
				canceldlg.setVisible(false);
			}
		} 
		
		else if (e.getSource() == cancelnobt) {
			canceldlg.setVisible(false);
		}
		
		else if (e.getSource() == ltdate){ // ��¥�� ���� ����Ʈ
			if (ltdate.getSelectedItem().equals("�輱��and�巹���� ���ϸ��")) {
				saveshowname = mv[0].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}
			
			if (ltdate.getSelectedItem().equals("LA LA LAND IN CONCERT WORLD TOUR")) {
				saveshowname = mv[1].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}
			
			if (ltdate.getSelectedItem().equals("������ �帲���� ���� ����")) {
				saveshowname = mv[2].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}
			
			if (ltdate.getSelectedItem().equals("2017 ���� �븮�� �����ص���� �佺Ƽ��")) {
				saveshowname = mv[3].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}
			
			if (ltdate.getSelectedItem().equals("����[�ϴ÷� �������� ���྾�̾߱�]")) {
				saveshowname = mv[4].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}
			
			if (ltdate.getSelectedItem().equals("��ȭ[���� Ÿ�� ����] OST�ܼ�Ʈ")) {
				saveshowname = mv[5].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}
			
			
			/*for (int i = 0; i < num; i++) {
				if (e.getSource() == mvc[i]) {
					saveshowname = mv[i].getText().trim();
					detshow();
					rsvDlg.setVisible(true);
				}
			}*/
			
		} // ��¥�� ���� ����Ʈ
		
		else if (e.getSource() == ltingi){ // best ���� ����Ʈ
			if (ltingi.getSelectedItem().equals("�輱��and�巹���� ���ϸ��")) {
				saveshowname = mv[0].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}
			
			if (ltingi.getSelectedItem().equals("LA LA LAND IN CONCERT WORLD TOUR")) {
				saveshowname = mv[1].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}
			
			if (ltingi.getSelectedItem().equals("������ �帲���� ���� ����")) {
				saveshowname = mv[2].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}
			
			if (ltingi.getSelectedItem().equals("2017 ���� �븮�� �����ص���� �佺Ƽ��")) {
				saveshowname = mv[3].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}
			
			if (ltingi.getSelectedItem().equals("����[�ϴ÷� �������� ���྾�̾߱�]")) {
				saveshowname = mv[4].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}
			
			if (ltingi.getSelectedItem().equals("��ȭ[���� Ÿ�� ����] OST�ܼ�Ʈ")) {
				saveshowname = mv[5].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}
			
			
			/*for (int i = 0; i < num; i++) {
				if (e.getSource() == mvc[i]) {
					saveshowname = mv[i].getText().trim();
					detshow();
					rsvDlg.setVisible(true);
				}
			}*/
			
		} // best ���� ����Ʈ
		
		else if(e.getSource() == adminbt){ // ������ ���
			admindlg.setVisible(true);
			
		} // ������ ���
		
		else if(e.getSource() == customerinbt){ // ������ �߰� ��ư
			addcustomer();
		} // ������ �߰� ��ư
		
		else if(e.getSource() == customerupbt){ // ������ ���� ��ư
			updatecustomer();
		} // ������ ���� ��ư
		
		else if(e.getSource() == customerdebt){ // ������ ���� ��ư
			deletecustomer();
		} // ������ ���� ��ư
		
		else if(e.getSource() == shoinbt){ // �������� �߰� ��ư
			try {
				addshow();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} // �������� �߰� ��ư
		
		else if(e.getSource() == shoupbt){ // �������� ���� ��ư
			try {
				updateshow();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} // �������� ���� ��ư
		
		else if(e.getSource() == shodebt){ // �������� ���� ��ư
			try {
				deleteshow();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} // �������� ���� ��ư
		
		

		// �¼� �޺� �ڽ� ����ŭ ����
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 16; j++) {
				// TODO : �¼� ��ư Ȱ��ȭ ��Ű��
				if (cnt == Integer.parseInt(strPersonCnt)) {
					btnFinSeat.setEnabled(true);
					break;
				} else
					btnFinSeat.setEnabled(false);
				
				if (e.getSource() == btnNotSelected[i][j]) {
					btnNotSelected[i][j].setBackground(Color.RED);
					cnt++;
					lbSltSeat.setText(Integer.toString(cnt));
				}
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == customertableView){
			updatecustomerRow = customertableView.getSelectedRow();
			ctf1.setText((String) customertableView.getValueAt(updatecustomerRow, 0));
			ctf2.setText((String) customertableView.getValueAt(updatecustomerRow, 1));
			ctf3.setText((String) customertableView.getValueAt(updatecustomerRow, 2));
			ctf4.setText((String) customertableView.getValueAt(updatecustomerRow, 3));
			ctf5.setText((String) customertableView.getValueAt(updatecustomerRow, 4));
			ctf6.setText((String) customertableView.getValueAt(updatecustomerRow, 5));
			ctf7.setText((String) customertableView.getValueAt(updatecustomerRow, 6));
		}
		
		else if(e.getSource() == showtableView){
			updateshowRow = showtableView.getSelectedRow();
			sstf1.setText((String) showtableView.getValueAt(updateshowRow, 0));
			sstf2.setText((String) showtableView.getValueAt(updateshowRow, 1));
			sstf3.setText((String) showtableView.getValueAt(updateshowRow, 2));
			sstf4.setText((String) showtableView.getValueAt(updateshowRow, 3));
			sstf5.setText((String) showtableView.getValueAt(updateshowRow, 4));
			sstf6.setText((String) showtableView.getValueAt(updateshowRow, 5));
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == homebt) { // Ȩ��ư
			tpmain.add("Center", tp);
			tp.setVisible(true);
			tpmain.setVisible(true);
			srchresult.setVisible(false);
			BuyerP.setVisible(false);
			mypagep.setVisible(false);
			searchtf.setText("���� �Ǵ� ��¥ �˻�");
		} // Ȩ��ư
		
		for (int i = 0; i < num; i++) { // ������ �̹��� ������ ��
			if (e.getSource() == mv[i]) {
				saveshowname = mv[i].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
				// cbDay.removeAllItems();
			}
		}
		
		for (int i = 0; i < num; i++) {
			if (e.getSource() == mvc[i]) {
				saveshowname = mv[i].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}
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
		if (e.getSource() == searchtf) {
			searchtf.setText("");
		}
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
