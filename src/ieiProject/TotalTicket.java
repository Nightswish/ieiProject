package ieiProject;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

//�ҽ����� 170511      

public class TotalTicket {
	public static void main(String[] ar) {
		TotalTicket_sub123 ex = new TotalTicket_sub123();
	}
}

class TotalTicket_sub123 extends JFrame implements ActionListener, MouseListener, KeyListener, FocusListener {

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

	// �˻�â
	Choice ch1 = new Choice();
	JPanel tpmain = new JPanel(new BorderLayout(3, 3));
	JPanel search = new JPanel(new FlowLayout(FlowLayout.LEFT));

	private TextField searchtf = new TextField("���� �Ǵ� ��¥(�� : 7�� 4�� => 07/04 �Ǵ� 7/04) �Է�", 80);
	private JButton searchbt = new JButton("�˻�");

	// �˻� ���
	JPanel srchresult = new JPanel(new GridLayout(2, 4, 3, 3));
	int lbct = 0;

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
	private int num = 6;
	ImageIcon[] image = new ImageIcon[num];

	private JLabel homebt = new JLabel(home);
	// ���� ��
	private JLabel[] mv = new JLabel[num];
	// copy ��
	private JLabel[] mvc = new JLabel[num];

	String[] mvst = new String[num];
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

	// �¼� ��ư
	private JButton btnNotSelected[][] = new JButton[6][16];
	private JButton btnSelected[][] = new JButton[6][16];

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

	private JPanel t11 = new JPanel();
	private JScrollPane showfpsc = new JScrollPane(t11);
	private JPanel showtkp = new JPanel();

	private int numb = 5;

	private JPanel[] mytkp = new JPanel[numb];// (new BorderLayout(3, 3));
	private JPanel[] tkall = new JPanel[numb];// (new FlowLayout());
	private Checkbox[] cancelcb = new Checkbox[numb];

	private Panel[] mytkinfp = new Panel[numb];// (new GridLayout(5,1));
	private Panel[] tknump = new Panel[numb];// (new
												// FlowLayout(FlowLayout.LEFT));
	private JLabel[] mytknum = new JLabel[numb];// ("Ƽ�Ϲ�ȣ: ");
	private JLabel[] mytknum1 = new JLabel[numb];

	private Panel[] tknamep = new Panel[numb];// (new
												// FlowLayout(FlowLayout.LEFT));
	private JLabel[] mytkname = new JLabel[numb];// ("�����̸�: ");
	private JLabel[] mytkname1 = new JLabel[numb];

	private Panel[] tklocp = new Panel[numb];// (new
												// FlowLayout(FlowLayout.LEFT));
	private JLabel[] mytkloc = new JLabel[numb];// ("�������: ");
	private JLabel[] mytkloc1 = new JLabel[numb];// ();

	private Panel[] tkdatep = new Panel[numb];// (new
												// FlowLayout(FlowLayout.LEFT));
	private JLabel[] mytkdate = new JLabel[numb];// ("������¥: ");
	private JLabel[] mytkdate1 = new JLabel[numb];// ();

	private Panel[] tkseatp = new Panel[numb];// (new
												// FlowLayout(FlowLayout.LEFT));
	private JLabel[] mytkseat = new JLabel[numb];// ("�¼�: ");
	private JLabel[] mytkseat1 = new JLabel[numb];// ();

	ImageIcon[] img = new ImageIcon[numb];// ("..\\ieiProject\\image\\�巹����.jpg");
	Image[] cimg = new Image[numb]; // img.getImage().getScaledInstance(100,150,
									// Image.SCALE_SMOOTH);
	private JLabel[] mytkimg = new JLabel[numb];// (img);

	private Button canceltk = new Button("�������");
	/////////////////// ������� ���̾Ʒα�
	private JDialog canceldlg = new JDialog(this, "�������", true);
	private Panel realcancelp = new Panel(new BorderLayout());
	private Label realcancel = new Label("������ ����Ͻðڽ��ϱ�?");
	private Panel canceldlgp = new Panel(new FlowLayout());
	private Button cancelokbt = new Button("Ȯ��");
	private Button cancelnobt = new Button("���");
	////////////////////////////////////////////// ���������� Ƽ�Ϻ��̱�dialog(5/15)

	private JDialog tkdlg = new JDialog(this, "Ƽ��", true);
	private JPanel tkin = new JPanel(new BorderLayout(3, 3));
	private JPanel tkshow = new JPanel(new BorderLayout(3, 3));
	private JPanel tkinfop = new JPanel(new GridLayout(4, 1));
	private JPanel tkp = new JPanel();

	private Label tkname = new Label();
	private Label tkloc = new Label();
	private Label tkdate = new Label();
	private Label tkseat = new Label("A1", Label.CENTER);
	private ImageIcon tkimg = new ImageIcon();
	private JLabel tklb = new JLabel(tkimg);
	///////////////////////////////////////////////////////////
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
			String query = "insert into customer(ID,PW,TEL,NIK,EMAIL) values(?,?,?,?,?)";
			// member�� point�߰��ؼ� ������ �ٲ���ϴ�(2017.5.10)
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, joinidtf.getText().trim());
			pstmt.setString(2, new String(joinpwtf.getPassword()));
			pstmt.setString(3, jointeltf.getText().trim());
			pstmt.setString(4, joinniktf.getText().trim());
			pstmt.setString(5, joinemailtf.getText().trim());
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
			} else {
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
	public void dateshow() {
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

		} catch (ClassNotFoundException eee) {
			System.out.println("��¥�� ���� �ҷ����� ����");
		} catch (SQLException e) {
			System.err.println("��¥�� ���� �ҷ����� ����2");
		}
	}

	// best ����
	public void bestshow() {
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

		} catch (ClassNotFoundException eee) {
			System.out.println("��¥�� ���� �ҷ����� ����");
		} catch (SQLException e) {
			System.err.println("��¥�� ���� �ҷ����� ����2");
		}
	}

	public void myTicket() {// ���������� Ƽ������ ���̱�(5/15)
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "select * from detshow,show where show.sid=detshow.sid and show.sname =? and detshow.dtdate=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd kk:mm");
			pstmt.setString(1, lbNameDB.getText().trim());
			String time = cbDay.getSelectedItem().toString().trim();
			Date parseDate = sdf.parse(time);
			Timestamp day = new Timestamp(parseDate.getTime());
			pstmt.setTimestamp(2, day);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				tkname.setText(rs.getString("SNAME"));
				tkdate.setText(sdf.format(rs.getTimestamp("DTDATE")));
				tkloc.setText(rs.getString("SLOC"));
				tklb.setIcon(new ImageIcon(rs.getString("SIMG")));

			}
			rs.close();
			pstmt.close();
		} catch (ClassNotFoundException eee) {
			System.out.println("Class ����");
		} catch (SQLException ee) {
			System.err.println("SQL ����" + ee.toString());
		} catch (ParseException eee) {
			System.err.println("����11111111" + eee.toString());
		}
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

		btnPayDlgPay.addActionListener(this);
		btnPayDlgCancle.addActionListener(this);
	}

	public void btnSelectedNull() {
		// ��ư �ʱ�ȭ
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 16; j++) {
				btnSelected[i][j].setText(null);
			}
		}
	}

	// ���� �Ϸ�Ǹ� Ƽ�� ���� DB�� �ֱ�
	public void addTicket(String showId, String SDate) throws ParseException {

		// String������ time�� Timestamp �������� ��ȯ
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd kk:mm");
		Date parsedDate = sdf.parse(SDate);
		Timestamp writeDate = new Timestamp(parsedDate.getTime());

		// ���� ��¥ ���
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		String today = formatter.format(new Date());

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);

			// TICKET ���̺� Ƽ�� �߰�
			String query = "insert into TICKET values (? + ticket_num_seq.nextval,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, today + 100000);
			pstmt.setString(2, showId);
			pstmt.setTimestamp(3, writeDate);
			ResultSet rs = null;

			// TICKET ���̺� �ִ� TNUM ã�� ������
			String findTicketQuery = "select * from TICKET where SEATID = ? and DTDATE = ? and SID = ?";
			PreparedStatement pstmt2 = conn.prepareStatement(findTicketQuery);
			pstmt2.setTimestamp(2, writeDate);
			pstmt2.setString(3, showId);
			ResultSet rs2 = null;
			;

			// RERSERVATION ���̺� �߰��ϱ�
			String addRsvQuery = "insert into RESERVATION values (?,?)";
			PreparedStatement pstmt3 = conn.prepareStatement(addRsvQuery);
			pstmt3.setString(1, loginokdlglb1.getText());
			ResultSet rs3 = null;

			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 16; j++) {
					if (btnSelected[i][j].getText() != null) {
						System.out.println("��������� : " + btnSelected[i][j].getText());
						pstmt.setString(4, btnSelected[i][j].getText());
						rs = pstmt.executeQuery();

						// TODO : �¼��� �ش��ϴ� TNUM �ޱ�
						pstmt2.setString(1, btnSelected[i][j].getText());
						rs2 = pstmt2.executeQuery();
						while (rs2.next()) {
							System.out.println("Ƽ�� ��ȣ�� ����غ��� " + rs2.getString("TNUM"));
							// TODO : ���� ���̺� �ֱ�
							pstmt3.setString(2, rs2.getString("TNUM"));
							rs3 = pstmt3.executeQuery();
						}

					}
				}
			}

			// ��ư �ʱ�ȭ
			btnSelectedNull();

			rs.close();
			pstmt.close();

			rs2.close();
			pstmt2.close();

			rs3.close();
			pstmt3.close();

		} catch (ClassNotFoundException eee) {
			System.out.println("���� ���̺� �߰� ����");
		} catch (SQLException ee) {
			System.err.println("���� ���̺� �߰� SQL ����");
		}

	}

	// TODO : seat ���̺� �߰��ϱ�
	public void addSeat(String SId, String SDate) throws ParseException {

		// String������ time�� Timestamp �������� ��ȯ
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd kk:mm");
		Date parsedDate = sdf.parse(SDate);
		Timestamp writeDate = new Timestamp(parsedDate.getTime());

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);

			String query = "insert into SEAT values (?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(2, SId);
			pstmt.setTimestamp(3, writeDate);
			ResultSet rs = null;

			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 16; j++) {
					if (btnSelected[i][j].getText() != null) {
						System.out.println("�¼� �߰��� " + btnSelected[i][j].getText());
						pstmt.setString(1, btnSelected[i][j].getText());
						rs = pstmt.executeQuery();
					}
				}
			}

			rs.close();
			pstmt.close();

		} catch (ClassNotFoundException eee) {
			System.out.println("�¼� �߰� ����");
		} catch (SQLException ee) {
			System.err.println("�¼� �߰� SQL ����");
		}

	}

	// DB���� �̹� ���ִ� �¼��� Xǥ�÷� ��������
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
				btnSelected[i][j].setText(null);
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
		mypagebt.setVisible(false);
		lb.setVisible(false);
		logoutbt.setVisible(false);
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
		logindlg.setSize(300, 170);
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

		// ���� �����̳� ����
		rsvCon = rsvDlg.getContentPane();
		rsvDlg.setLayout(new BorderLayout());
		rsvDlg.setSize(600, 400);
		rsvDlg.setResizable(false);

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

		// btnSelected ��üȭ ��Ű��
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 16; j++) {
				btnSelected[i][j] = new JButton();
			}
		}

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
		for (int i = 0; i < numb; i++) {

			mytkp[i] = new JPanel();
			tkall[i] = new JPanel();
			mytkinfp[i] = new Panel();

			tknump[i] = new Panel();
			mytknum[i] = new JLabel();
			mytknum1[i] = new JLabel();

			tknamep[i] = new Panel();
			mytkname[i] = new JLabel();
			mytkname1[i] = new JLabel();

			tklocp[i] = new Panel();
			mytkloc[i] = new JLabel();
			mytkloc1[i] = new JLabel();

			tkdatep[i] = new Panel();
			mytkdate[i] = new JLabel();
			mytkdate1[i] = new JLabel();

			tkseatp[i] = new Panel();
			mytkseat[i] = new JLabel();
			mytkseat1[i] = new JLabel();

			cancelcb[i] = new Checkbox();
			mytkimg[i] = new JLabel();

		}
		showtkp.setLayout(new BoxLayout(showtkp, BoxLayout.Y_AXIS));

		for (int i = 0; i < numb; i++) {

			mytkp[i].setLayout(new BorderLayout(3, 3));
			mytkp[i].setBorder(new BevelBorder(BevelBorder.RAISED));

			tkall[i].setLayout(new FlowLayout());
			mytkinfp[i].setLayout((new GridLayout(5, 1)));
			tknump[i].setLayout((new FlowLayout(FlowLayout.LEFT)));
			mytknum[i].setText("Ƽ�Ϲ�ȣ: ");
			tknamep[i].setLayout((new FlowLayout(FlowLayout.LEFT)));
			mytkname[i].setText("�����̸�: ");
			tklocp[i].setLayout((new FlowLayout(FlowLayout.LEFT)));
			mytkloc[i].setText("���: ");
			tkdatep[i].setLayout((new FlowLayout(FlowLayout.LEFT)));
			mytkdate[i].setText("��¥: ");
			tkseatp[i].setLayout((new FlowLayout(FlowLayout.LEFT)));
			mytkseat[i].setText("�¼�: ");

			img[i] = new ImageIcon("..\\ieiProject\\image\\8����.jpg");
			cimg[i] = img[i].getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
			img[i].setImage(cimg[i]);
			System.out.println(img[i]);
			mytkimg[i] = new JLabel(img[i]);

			tknump[i].add(mytknum[i]);
			tknump[i].add(mytknum1[i]);
			tknamep[i].add(mytkname[i]);
			tknamep[i].add(mytkname1[i]);
			tklocp[i].add(mytkloc[i]);
			tklocp[i].add(mytkloc1[i]);
			tkdatep[i].add(mytkdate[i]);
			tkdatep[i].add(mytkdate1[i]);
			tkseatp[i].add(mytkseat[i]);
			tkseatp[i].add(mytkseat1[i]);

			mytkinfp[i].add(tknump[i]);
			mytkinfp[i].add(tknamep[i]);
			mytkinfp[i].add(tklocp[i]);
			mytkinfp[i].add(tkdatep[i]);
			mytkinfp[i].add(tkseatp[i]);

			mytkp[i].add("Center", mytkimg[i]);
			mytkp[i].add("East", mytkinfp[i]);

			tkall[i].add(cancelcb[i]);
			tkall[i].add(mytkp[i]);
			showtkp.add(tkall[i]);
		}

		t11.add(showtkp);
		show.add("North", canceltk);
		show.add("Center", showfpsc);
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
			srchresult.removeAll();
			lbct = 0;
			if (ch1.getSelectedItem() == "----------") { // �׷�̰� -----�����϶�
				String rslt = searchtf.getText().trim();
				for (int i = 0; i < num; i++) {
					mvst[i] = mv[i].getText().trim();
				}
				ArrayList<String> list = new ArrayList<String>();
				String[] toColumnNm = rslt.split("\\s");
				for (int i = 0; i < toColumnNm.length; i++) {
					list.add(toColumnNm[i]);
				}
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection(url, id, pass);
					for (String srchlist : list) {
						String query = "select distinct sname from show natural join detshow where sname like '%' || ? || '%' or dtdate like '%' || ? || '%'";
						PreparedStatement pstmt = conn.prepareStatement(query);
						pstmt.setString(1, srchlist);
						pstmt.setString(2, srchlist);
						ResultSet rs = pstmt.executeQuery();
						while (rs.next()) {
							for (int i = 0; i < num; i++) {
								if (mvst[i].equals(rs.getString("SNAME"))) {
									mvc[i].setVerticalTextPosition(SwingConstants.BOTTOM);
									mvc[i].setHorizontalTextPosition(SwingConstants.CENTER);
									srchresult.add(mvc[i]);
									++lbct;
								}
							}
						}
					}
				} catch (Exception ee) {
					System.err.println("�˻� ���â ���� ����");
				}
			} else if (ch1.getSelectedItem() == "�����̸�") { // �׷�̰� �����̸� �����϶�
				String rslt = searchtf.getText().trim();
				for (int i = 0; i < num; i++) {
					mvst[i] = mv[i].getText().trim();
				}

				ArrayList<String> list = new ArrayList<String>();
				String[] toColumnNm = rslt.split("\\s");
				for (int i = 0; i < toColumnNm.length; i++) {
					list.add(toColumnNm[i]);
				}
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection(url, id, pass);
					for (String srchlist : list) {
						String query = "select distinct sname from show where sname like '%' || ? || '%'";
						PreparedStatement pstmt = conn.prepareStatement(query);
						pstmt.setString(1, srchlist);
						ResultSet rs = pstmt.executeQuery();

						while (rs.next()) {
							for (int i = 0; i < num; i++) {
								if (mvst[i].equals(rs.getString("SNAME"))) {
									mvc[i].setVerticalTextPosition(SwingConstants.BOTTOM);
									mvc[i].setHorizontalTextPosition(SwingConstants.CENTER);
									srchresult.add(mvc[i]);
									++lbct;
								}
							}
						}
					}
				} catch (Exception ee) {
					System.out.println("�˻� ���â ���� ����");
				}
			} else if (ch1.getSelectedItem() == "������¥") { // �׷�̰� ������¥ �����ϋ�
				String rslt = searchtf.getText().trim();
				for (int i = 0; i < num; i++) {
					mvst[i] = mv[i].getText().trim();
				}

				ArrayList<String> list = new ArrayList<String>();
				String[] toColumnNm = rslt.split("\\s");
				for (int i = 0; i < toColumnNm.length; i++) {
					list.add(toColumnNm[i]);
				}

				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection(url, id, pass);
					for (String srchlist : list) {
						String query = "select distinct sname from show natural join detshow where dtdate like '%' || ? || '%'";
						PreparedStatement pstmt = conn.prepareStatement(query);
						pstmt.setString(1, srchlist);
						ResultSet rs = pstmt.executeQuery();

						while (rs.next()) {
							for (int i = 0; i < num; i++) {
								if (mvst[i].equals(rs.getString("SNAME"))) {
									mvc[i].setVerticalTextPosition(SwingConstants.BOTTOM);
									mvc[i].setHorizontalTextPosition(SwingConstants.CENTER);
									srchresult.add(mvc[i]);
									++lbct;
								}
							}
						}
					}
				} catch (Exception ee) {
					System.out.println("�˻� ���â ���� ����");
				}

			}
			tp.setVisible(false);
			tpmain.add("Center", srchresult);
			searchtf.setText("���� �Ǵ� ��¥(�� : 7�� 4�� => 07/04 �Ǵ� 7/04) �Է�");
			srchresult.setVisible(true);
			System.out.println(lbct);

			if (lbct == 0 || lbct == num) {
				srchresult.removeAll();
				JOptionPane.showMessageDialog(null, "�˻� ����� �����ϴ�!", "�˻� ��� ����", JOptionPane.ERROR_MESSAGE);
				tpmain.add("Center", tp);
				tp.setVisible(true);
				srchresult.setVisible(false);

			}

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
			loginokdlglb1.setText(" ");
			loginokdlglb1.setText("");
			tpmain.setVisible(true);
			logoutbt.setVisible(false);
			mypagebt.setVisible(false);
			loginbt.setVisible(true);
			joinbt.setVisible(true);
			mypagep.setVisible(false);
		} // �α׾ƿ� ��ư

		else if (e.getSource() == btnCancle) { // ��� ��ư
			rsvDlg.setVisible(false);
		} else if (e.getSource() == btnSeatSelect) { // �¼� ���� �κ� ������ ��
			// �޺� �ڽ� ��¥ ���� ��
			cbSelectedDate = cbDay.getSelectedItem().toString();
			// �α��� �ش޶�� ����â, �α���â ����
			if (loginokdlglb1.getText() == "") {
				JOptionPane.showMessageDialog(null, "�α����� ���ּ���!", "��α��� ����", JOptionPane.WARNING_MESSAGE);
				logindlg.setVisible(true);
			} else {
				try { // DB�� �ִ� �¼� �ҷ�����
					showSeat(selectedSid, cbSelectedDate);
					seatClear();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				sltSeatDlg.setVisible(true);
			}
		} else if (e.getSource() == btnFinSeat) { // �¼� ���� �Ϸ�
			lbToPay.setText(String.valueOf(Integer.parseInt(strPersonCnt) * showPrice));
			payDlg.setVisible(true);
		} else if (e.getSource() == btnPayDlgCancle) {
			payDlg.setVisible(false);
		} else if (e.getSource() == btnReselect) { // �¼� �ٽ� ����
			try {
				showSeat(selectedSid, cbSelectedDate);
				seatClear();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == mypagebt) { // ����������
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
/*������
		else if (e.getSource() == canceltk) {/// �����ؾ���
			if (cancelcb[1].getState() == true) {
				canceldlg.setSize(200, 200);
				canceldlg.setVisible(true);
			}
		}

		else if (e.getSource() == cancelokbt) {
			if (cancelcb[1].getState() == true) {
				tkall[1].setVisible(false);
				canceldlg.setVisible(false);
			}
			
			 * if (cancelcb2.getState() == true) { t21.setVisible(false);
			 * canceldlg.setVisible(false); } if (cancelcb3.getState() == true)
			 * { t31.setVisible(false); canceldlg.setVisible(false); }
			 
		}

		else if (e.getSource() == cancelnobt) {
			canceldlg.setVisible(false);
		}*/ else if (e.getSource() == btnPayDlgPay) { // (5/15) �������� Ƽ������

			myTicket();

			tkinfop.add(tkname);
			tkinfop.add(tkloc);
			tkinfop.add(tkdate);
			tkinfop.add(tkseat);
			tkshow.add("Center", tklb);
			tkshow.add("East", tkinfop);
			tkp.add(tkshow);
			tkdlg.add(tkp);

			tkdlg.setSize(500, 500);
			tkdlg.setVisible(true);
		}

		else if (e.getSource() == ltdate) { // ��¥�� ���� ����Ʈ
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

			/*
			 * for (int i = 0; i < num; i++) { if (e.getSource() == mvc[i]) {
			 * saveshowname = mv[i].getText().trim(); detshow();
			 * rsvDlg.setVisible(true); } }
			 */

		} // ��¥�� ���� ����Ʈ

		else if (e.getSource() == ltingi) { // best ���� ����Ʈ
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

			/*
			 * for (int i = 0; i < num; i++) { if (e.getSource() == mvc[i]) {
			 * saveshowname = mv[i].getText().trim(); detshow();
			 * rsvDlg.setVisible(true); } }
			 */

		} // best ���� ����Ʈ

		// �¼� �޺� �ڽ� ����ŭ ����
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 16; j++) {
				// ������ �ο����� ������ �¼� ��ư Ȱ��ȭ ��Ű��
				if (cnt == Integer.parseInt(strPersonCnt)) {
					btnFinSeat.setEnabled(true);
					break;
				} else
					btnFinSeat.setEnabled(false);

				// ���õ� ��ư �� �ٲٱ�
				if (e.getSource() == btnNotSelected[i][j]) {
					btnSelected[i][j].setText(btnNotSelected[i][j].getText());
					System.out.println("���õ� �¼� ��� " + btnSelected[i][j].getText());
					btnNotSelected[i][j].setBackground(Color.RED);
					cnt++;
					lbSltSeat.setText(Integer.toString(cnt));
				}
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

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
			searchtf.setText("���� �Ǵ� ��¥(�� : 7�� 4�� => 07/04 �Ǵ� 7/04) �Է�");
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
