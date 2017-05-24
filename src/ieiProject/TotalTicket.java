package ieiProject;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class TotalTicket {
	public static void main(String[] ar) {
		TotalTicket_sub12345 ex = new TotalTicket_sub12345();
	}
}

class TotalTicket_sub12345 extends JFrame implements ActionListener, MouseListener, KeyListener, FocusListener {

	ImageIcon home = new ImageIcon("..\\ieiProject\\image\\home2.jpg");
	private boolean state = true;// ���������� ���º�ȭ
	private int imgcount = 0;
	private int mok = 0;
	private int mok2 = 0;
	int count = 0;
	private JPanel tpmaincard = new JPanel(new CardLayout());
	private String saveshowname = null;
	private Container con;
	// �޴�ȭ��
	private BorderLayout bl = new BorderLayout(5, 5);
	private JPanel pagep = new JPanel(new FlowLayout());
	private JButton rightbt = new JButton(">>");
	private JButton leftbt = new JButton("<<");
	private JButton joinbt = new JButton("ȸ������");
	private JButton loginbt = new JButton("�α���");
	private JButton mypagebt = new JButton("����������");
	private JPanel MainP = new JPanel(new CardLayout());
	JPanel sp1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel sp2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

	private JLabel lb = new JLabel("");
	private JButton logoutbt = new JButton("�α׾ƿ�");
	private JButton adminbt = new JButton("������ ���");
	private String userId;

	// ������ ���
	private Container admincon;
	private JDialog admindlg = new JDialog(this, "������ ���", true);
	protected JTabbedPane adminPane = new JTabbedPane();
	protected Panel customerP = new Panel(new BorderLayout());
	protected Panel showP = new Panel(new BorderLayout());
	protected Panel couponP = new Panel(new BorderLayout());
	protected Panel couponckP = new Panel(new BorderLayout());

	// ������ ��� ������
	int updatecustomerRow;
	int updateshowRow;
	int updatecouponRow;
	int updatecouponckRow;
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
	JPanel cp1 = new JPanel(new GridLayout(2, 1));
	JPanel cp2 = new JPanel(new GridLayout(2, 1));
	JPanel cp3 = new JPanel(new GridLayout(2, 1));
	JPanel cp4 = new JPanel(new GridLayout(2, 1));
	JPanel cp5 = new JPanel(new GridLayout(2, 1));
	JPanel cp6 = new JPanel(new GridLayout(2, 1));
	JPanel cp7 = new JPanel(new GridLayout(2, 1));
	JPanel cus = new JPanel(new FlowLayout());
	JButton customerinbt = new JButton("�߰�");
	JButton customerupbt = new JButton("����");
	JButton customerdebt = new JButton("����");

	// ������ ��� ���� ����
	DefaultTableModel showdatamodel;
	JTable showtableView;
	JScrollPane showscrollList;

	String showcolName[] = { "SID", "SNAME", "SLOC", "SPRICE", "SIMG", "DTDATE", };
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

	JPanel ssp1 = new JPanel(new GridLayout(2, 1));
	JPanel ssp2 = new JPanel(new GridLayout(2, 1));
	JPanel ssp3 = new JPanel(new GridLayout(2, 1));
	JPanel ssp4 = new JPanel(new GridLayout(2, 1));
	JPanel ssp5 = new JPanel(new GridLayout(2, 1));
	JPanel ssp6 = new JPanel(new GridLayout(2, 1));

	JPanel sho = new JPanel(new FlowLayout());
	JButton shoinbt = new JButton("�߰�");
	JButton shoupbt = new JButton("����");
	JButton shodebt = new JButton("����");

	// �����ڸ�� ���� ����â
	DefaultTableModel couponmodel;
	JTable couponView;
	JScrollPane couponscrollList;
	DefaultTableModel coupondatamodel;

	String couponName[] = { "COUPONNM", "COUPONVALUE", "ROF", "ID" };
	Vector<Vector<String>> coupondata;
	Vector<String> couponNames;

	JPanel cppn = new JPanel(new FlowLayout());
	JLabel cplb1 = new JLabel("COUPONNM", JLabel.CENTER);
	JLabel cplb2 = new JLabel("COUPONVALUE", JLabel.CENTER);
	JLabel cplb3 = new JLabel("ROF", JLabel.CENTER);
	JLabel cplb4 = new JLabel("ID", JLabel.CENTER);
	JTextField cptf1 = new JTextField(16);
	JTextField cptf2 = new JTextField(10);
	JTextField cptf3 = new JTextField(10);
	JTextField cptf4 = new JTextField(10);

	JPanel cpp1 = new JPanel(new GridLayout(2, 1));
	JPanel cpp2 = new JPanel(new GridLayout(2, 1));
	JPanel cpp3 = new JPanel(new GridLayout(2, 1));
	JPanel cpp4 = new JPanel(new GridLayout(2, 1));

	JPanel cpo = new JPanel(new FlowLayout());
	JButton cpmkbt = new JButton("��ȣ ����");
	JButton cpinbt = new JButton("�߰�");
	JButton cpupbt = new JButton("����");
	JButton cpdebt = new JButton("����");

	// �����ڸ�� ����üũ Ȯ��â
	DefaultTableModel couponckmodel;
	JTable couponckView;
	JScrollPane couponckscrollList;
	DefaultTableModel couponckdatamodel;

	String couponckName[] = { "COUPONNM", "ID" };
	Vector<Vector<String>> couponckdata;
	Vector<String> couponckNames;

	JPanel ckpn = new JPanel(new FlowLayout());
	JLabel cklb1 = new JLabel("COUPONNM", JLabel.CENTER);
	JLabel cklb2 = new JLabel("ID", JLabel.CENTER);
	JTextField cktf1 = new JTextField(16);
	JTextField cktf2 = new JTextField(10);

	JPanel ckp1 = new JPanel(new GridLayout(2, 1));
	JPanel ckp2 = new JPanel(new GridLayout(2, 1));

	JPanel cko = new JPanel(new FlowLayout());
	JButton ckinbt = new JButton("�߰�");
	JButton ckupbt = new JButton("����");
	JButton ckdebt = new JButton("����");

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
	JPanel tp[];// = new JPanel(new GridLayout(2, 3, 3, 3));
	JScrollPane[] scroller;// = new JScrollPane(tp);

	JPanel mp = new JPanel(new BorderLayout(3, 3));

	private int num = 6;
	ImageIcon[] image;// = new ImageIcon[num];

	private JLabel homebt = new JLabel(home);
	// ���� ��
	private JLabel[] mv;// = new JLabel[num];

	// copy ��
	private JLabel[] mvc;// = new JLabel[num];

	// �̺�Ʈ ���̾�α�
	Container eventCon;
	JDialog eventdlg = new JDialog(this, "�̺�Ʈ", false);
	JPanel eventpn = new JPanel(new BorderLayout());
	JPanel eventpn2 = new JPanel(new GridLayout(4, 1));
	JPanel eventlbpn = new JPanel(new FlowLayout());
	JPanel eventlbpn2 = new JPanel(new FlowLayout());
	JPanel eventlbpn3 = new JPanel(new FlowLayout());
	JPanel eventbtpn = new JPanel(new FlowLayout());
	JLabel eventlb6m = new JLabel(new ImageIcon("..\\ieiProject\\image\\ȣ�������Ǵ�.png"));
	JLabel eventalarm = new JLabel("ȣ�������� ���� �����Ͽ� �߰� ����Ʈ ����!");
	JLabel eventguid = new JLabel("(����������) => (�����Է�)�� ����â�� ������ȣ�� �Է��� �ּ���. ");
	JLabel eventcoupon = new JLabel("0000-0000-0000-0001");
	JButton eventlgbt = new JButton("�α���");
	JButton eventclbt = new JButton("�ݱ�");

	String[] mvst;// = new String[num];
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
	/////////////// ���̵� �� ��й�ȣ ã��
	private Container Findcon;
	private JDialog Finddlg = new JDialog(this, "ã��", true);

	private JPanel Findidp = new JPanel(new BorderLayout(5, 5));
	private JPanel Findidbtp = new JPanel(new FlowLayout());

	private JPanel Findpwp = new JPanel(new GridLayout(2, 1));
	private JPanel Findbtp = new JPanel(new FlowLayout());
	private JButton Findid = new JButton("���̵�ã��");
	private JButton Findpw = new JButton("��й�ȣã��");
	private JButton Findid_fid = new JButton("ã��");
	private JButton Findid_fpw = new JButton("��й�ȣ ã��");

	private Container Finderrcon;
	private JDialog Finderrdlg = new JDialog(this, "����", true);
	private JPanel Finderrp = new JPanel(new BorderLayout(5, 5));
	private JLabel Finderrimgib = new JLabel(new ImageIcon("..\\ieiProject\\image\\error.jpg"));
	private JLabel Finderrlb = new JLabel("�߸� �Է��Ͽ����ϴ�.");
	private JButton Finddlgbt = new JButton("Ȯ��");

	private JButton Findpwbt = new JButton("��й�ȣã��");
	private JButton Findpokbt = new JButton("Ȯ��");
	private JButton Findippclbt = new JButton("���");
	private JButton FindShowIDpclbt = new JButton("���");
	private JButton FindShowPWpcokt = new JButton("Ȯ��");

	private JPanel FindShowIDp = new JPanel(new GridLayout(3, 1));
	private JPanel FindShowPWp = new JPanel(new GridLayout(2, 1));

	private JDialog Findiddlg = new JDialog(this, "���̵�ã��", false);
	private TextField Findidtf = new TextField(20);
	private TextField Findpwtf = new TextField(20);
	private JLabel FindShowid = new JLabel();
	private JLabel FindShowpw = new JLabel();

	// 1:1���� ���̾�α�
	private JButton btnFAQ = new JButton("1:1 �����ϱ�");

	// 170518 ������ ���� ����Ʈ ���� ���� ���̾�α�
	private Container addPointcon;
	private JDialog addPointDlg = new JDialog(this, "����Ʈ ���� �ʿ�", true);
	private JLabel lbaddPoint = new JLabel("����Ʈ�� �����մϴ�.", JLabel.CENTER);
	private JButton btnAddPoint = new JButton("���� �ϱ�");

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

	// 170518 ������ ���� ���� ����
	String strChargePoint;
	String cntChargePoint[] = { "0", "10000", "20000", "50000", "100000" };
	private JComboBox cbChargePoint = new JComboBox(cntChargePoint);

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
	private JLabel lbPoint = new JLabel("���� ����Ʈ");
	private JLabel lbMyPoint = new JLabel("0");
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
	protected Panel coupon = new Panel(new BorderLayout());

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
	private Label point2 = new Label("10,000�� ����: 5,00p ����");
	private Label point3 = new Label("20,000�� ����: 2,000p ����");
	private Label point4 = new Label("30,000�� ����: 3,000p ����");
	private Label point5 = new Label("50,000�� ����: 5,000p ����");
	private Label point6 = new Label("100,000�� ���� : 10,000p ����");

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

	private int numb;

	private JPanel[] mytkp;// = new JPanel[numb];//(new BorderLayout(3, 3));
	private JPanel[] tkall;// = new JPanel[numb];//(new FlowLayout());
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

	// ���� �Է� ����
	JPanel couponlbpn = new JPanel(new FlowLayout());
	JPanel coupontfpn = new JPanel(new FlowLayout());
	JPanel couponbtpn = new JPanel(new FlowLayout());
	JLabel couponlb = new JLabel("���� ��ȣ�� �Է��� �ּ���");
	JTextField coupon1 = new JTextField(5);
	JLabel coupon11 = new JLabel(" - ");
	JTextField coupon2 = new JTextField(5);
	JLabel coupon22 = new JLabel(" - ");
	JTextField coupon3 = new JTextField(5);
	JLabel coupon33 = new JLabel(" - ");
	JTextField coupon4 = new JTextField(5);
	JButton couponok = new JButton("Ȯ��");
	////////////////////////////////////////////// �������������� ���� �������ִ� ����
	String shownum = null;
	String showname = null;
	String showloc = null;
	String showdate = null;
	String showseat = null;
	/////////////////////////////////////////////////////////// �ı⺸��&�ۼ�
	FlowLayout fl = new FlowLayout();
	JButton bt = new JButton("�ı� ����");
	JButton btnReview = new JButton("�ı� �ۼ�");

	JDialog jdlg = new JDialog(this, "�۾���", true);
	Container dlgcon;
	JLabel dlglb = new JLabel("�ۼ��� : ", JLabel.RIGHT);
	JLabel dlglb1 = new JLabel("���� : ", JLabel.RIGHT);
	JLabel dlglb2 = new JLabel("��й�ȣ : ", JLabel.RIGHT);
	JLabel dlgtf = new JLabel();
	JTextField dlgtf1 = new JTextField();
	JPasswordField dlgtf2 = new JPasswordField();
	JTextArea dlgta = new JTextArea();
	JScrollPane dlgjsp = new JScrollPane(dlgta);
	JButton dlgbt = new JButton("����");
	JButton dlgbt1 = new JButton("���");

	// �ۼ��� ���
	private JDialog adlg = new JDialog(this, "����Ʈ���", true);
	private Container adlgcon;
	private JLabel adlglb = new JLabel("����Ʈ���", JLabel.CENTER);
	private Vector adlgvc = new Vector();
	private JList adlgli = new JList();
	private JScrollPane adlgjsp = new JScrollPane(adlgli);
	private JButton adlgbt = new JButton("�����׸񺸱�");
	private JButton adlgbt1 = new JButton("�ݱ�");

	// ���õ� ���� ��ü ���� ����
	private JDialog bdlg = new JDialog(this, "�ۺ���", true);
	private Container bdlgcon;
	private JLabel bdlglb = new JLabel("���� : ", JLabel.RIGHT);
	private JLabel bdlglb1 = new JLabel("�ۼ��� : ", JLabel.RIGHT);
	private JLabel bdlgtf = new JLabel();
	private JLabel bdlgtf1 = new JLabel();
	private JTextArea bdlgta = new JTextArea();
	private JScrollPane bdlgjsp = new JScrollPane(bdlgta);
	private JButton bdlgbt = new JButton("Ȯ��");

	public void FindidDB() {// DB���� ID ã��
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			String query = "select distinct ID from customer where email=? or tel=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, Findidtf.getText().trim());
			pstmt.setString(2, Findidtf.getText().trim());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				FindShowid.setText(rs.getString("ID"));
			}

			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.err.println("Error : " + e.toString());
		}

	}// DB���� ID ã��

	public void FindpwDB() { // DB���� PW ã��
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			String query = "select distinct pw from customer where ID=? ";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, Findidtf.getText().trim());

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				FindShowpw.setText(rs.getString("PW"));
			}

			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.err.println("Error : " + e.toString());
		}

	}// DB���� PW ã��

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

	// ����â���� ����Ʈ DB���� �ҷ�����
	public void readPoint() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "select * from customer where id=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginokdlglb1.getText());

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				point1.setText(rs.getString("POINT"));
				lbMyPoint.setText(point1.getText());
			}

			rs.close();
			pstmt.close();
		} catch (ClassNotFoundException eee) {
			System.out.println("����Ʈ �������� ����");
		} catch (SQLException e) {
			System.err.println("����Ʈ �������� SQL ����");
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
				JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�!", "�α��� ����", JOptionPane.WARNING_MESSAGE);
				rs.close();
				pstmt.close();
			}

			else if (rs.getString("ID").equals("asd") && rs.getString("PW").equals("1")) {
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
				viewcoupon();
				viewcouponck();

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
				dlgtf.setText(rs.getString("id"));
				loginokdlg.setVisible(true);
				userId = rs.getString("id");
				btnReview.setVisible(true);

				rs.close();
				pstmt.close();
			}
		} catch (ClassNotFoundException eee) {

		} catch (SQLException e) {
			System.err.println("�α��� ����!!!");
		}
	}

	// �α׾ƿ� (DB���� ����)
	public void logoutMember() {
		try {
			conn.close();
		} catch (SQLException e) {
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

	// ���� 170518 ����Ʈ ����DB
	public void chargepoint(String strChargePoint) {

		int addPrice = 0;
		int price = Integer.parseInt(strChargePoint);

		switch (price) {
		case 10000:
			price += 500;
			break;
		case 20000:
			price += 2000;
			break;
		case 30000:
			price += 3000;
			break;
		case 50000:
			price += 5000;
			break;
		case 100000:
			price += 10000;
			break;
		default:
			// 170518 ������ ����
			price = 0;
		}

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);

			// ����Ʈ �����ϱ�
			String query = "update customer set POINT=? where ID=?";
			PreparedStatement pstmt = conn.prepareStatement(query);

			System.out.println("** ���� �ܾ��� :" + point1.getText());
			addPrice = Integer.parseInt(point1.getText()) + price;
			System.out.println("** ���� �� �ܾ��� : " + addPrice);

			pstmt.setString(1, String.valueOf(addPrice));
			pstmt.setString(2, id2.getText().trim());

			pstmt.executeUpdate();

			readPoint();
			// chargetf.setText("");

			pstmt.close();
			System.out.println("��������!");

		} catch (ClassNotFoundException eee) {
			System.err.println("���� ����!");
		} catch (SQLException e) {
			System.err.println("���� ����!");
		}

	}

	// 170518 ������ ���� ����Ʈ �����ϱ� DB�� �����ϱ�
	public void minusPoint(int toPay, int myPoint) {
		int finalPoint = myPoint - toPay;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);

			String query = "update customer set POINT=? where id=?";
			PreparedStatement pstmt = conn.prepareStatement(query);

			// ������ �ݾ����� �� ����
			lbMyPoint.setText(String.valueOf(finalPoint));
			point1.setText(String.valueOf(finalPoint));
			System.out.println("����Ʈ �����غ��ϴ� �ܿ��ݾ��� " + lbMyPoint.getText());

			pstmt.setString(1, lbMyPoint.getText());
			pstmt.setString(2, id2.getText().trim());

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

	// coupon �Է�
	public void coupon() { // �ߺ� �Ұ���(��ȭ��ǰ��)
		String couponNumber = coupon1.getText().trim() + coupon2.getText().trim() + coupon3.getText().trim()
				+ coupon4.getText().trim();
		int nowpoint = Integer.parseInt(point1.getText());
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "select * from COUPON where COUPONNM=? and ROF=0 and ID is null";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, couponNumber);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				coupon2();
			} else {
				coupon1.setText("");
				coupon2.setText("");
				coupon3.setText("");
				coupon4.setText("");

				String value = rs.getString("COUPONVALUE");
				System.out.println(value);

				String query2 = "update customer set POINT=? where ID=?";
				PreparedStatement pstmt2 = conn.prepareStatement(query2);
				int addPrice = nowpoint + Integer.parseInt(value);
				pstmt2.setString(1, String.valueOf(addPrice));
				pstmt2.setString(2, id2.getText().trim());
				pstmt2.executeUpdate();
				pstmt2.close();
				readPoint();

				String usercousql = "update COUPON set ID = ? where COUPONNM=?";
				PreparedStatement pstmt3 = conn.prepareStatement(usercousql);
				pstmt3.setString(1, userId);
				pstmt3.setString(2, couponNumber);
				pstmt3.executeUpdate();
				System.out.println("���� ����");
				JOptionPane.showMessageDialog(null, "�����Է� �Ϸ�! ���� ����Ʈ : " + point1.getText(), "���� �Է� �Ϸ�",
						JOptionPane.INFORMATION_MESSAGE);

			}
			rs.close();
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();

		} catch (Exception ee) {
			System.err.println("���� �Է� ����");
		}
	}

	public void coupon2() { // �ߺ� ����(�̺�Ʈ - ����Ʈ �Ѹ���)
		String couponNumber = coupon1.getText().trim() + coupon2.getText().trim() + coupon3.getText().trim()
				+ coupon4.getText().trim();
		int nowpoint = Integer.parseInt(point1.getText());
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "select * from COUPON where ROF=1 and COUPONNM=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, couponNumber);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				JOptionPane.showMessageDialog(null, "����� �� ���� ���� ��ȣ�Դϴ�!", "������ȣ ����", JOptionPane.WARNING_MESSAGE);
			} else {
				String query2 = "select * from COUPONCHECK where ID=? and COUPONNM=?";
				PreparedStatement pstmt2 = conn.prepareStatement(query2);
				pstmt2.setString(1, userId);
				pstmt2.setString(2, couponNumber);
				ResultSet rs2 = pstmt2.executeQuery();
				if (!rs2.next()) {
					coupon1.setText("");
					coupon2.setText("");
					coupon3.setText("");
					coupon4.setText("");
					String value = rs.getString("COUPONVALUE");
					System.out.println(value + "-2");
					String query3 = "update customer set POINT=? where ID=?";
					PreparedStatement pstmt3 = conn.prepareStatement(query3);
					int addPrice = nowpoint + Integer.parseInt(value);
					pstmt3.setString(1, String.valueOf(addPrice));
					pstmt3.setString(2, userId);
					pstmt3.executeUpdate();
					pstmt3.close();

					String query4 = "insert into COUPONCHECK VALUES (?,?)";
					PreparedStatement pstmt4 = conn.prepareStatement(query4);
					pstmt4.setString(1, couponNumber);
					pstmt4.setString(2, userId);
					pstmt4.executeUpdate();
					pstmt4.close();

					readPoint();
					System.out.println("���� ����");
					JOptionPane.showMessageDialog(null, "�����Է� �Ϸ�! ���� ����Ʈ : " + point1.getText(), "���� �Է� �Ϸ�",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "�̹� ����� ���� ��ȣ�Դϴ�!", "������ȣ ����", JOptionPane.WARNING_MESSAGE);
				}
				rs2.close();
				pstmt2.executeUpdate();
				pstmt2.close();
			}
			pstmt.executeUpdate();
			pstmt.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("���� �Է� ����(2)");
		}
	}

	// �� ����
	public void viewcustomer() {
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

		} catch (ClassNotFoundException eee) {
			System.out.println("�� �ҷ����� ����");
		} catch (SQLException e) {
			System.err.println("�� �ҷ����� ����2");
		}
	}

	// �� �߰�
	public void addcustomer() {
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
	public void updatecustomer() {
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
	public void deletecustomer() {
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
	public void viewshow() {
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

		} catch (ClassNotFoundException eee) {
			System.out.println("���� �ҷ����� ����");
		} catch (SQLException e) {
			System.err.println("���� �ҷ����� ����2");
		}
	}

	// ���� �߰�
	public void addshow() throws ParseException {
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
	public void updateshow() throws ParseException {
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
	public void deleteshow() throws ParseException {
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

	// ���� ����
	public void viewcoupon() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "select * from COUPON";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Vector<String> vector = new Vector<String>();
				vector.add(rs.getString("COUPONNM"));
				vector.add(rs.getString("COUPONVALUE"));
				vector.add(rs.getString("ROF"));
				vector.add(rs.getString("ID"));
				coupondata.addElement(vector);
			}
			rs.close();
			pstmt.close();

		} catch (ClassNotFoundException eee) {
			System.out.println("���� �ҷ����� ����");
		} catch (SQLException e) {
			System.err.println("���� �ҷ����� ����2");
		}
	}

	// ���� ��ȣ ����
	public void couponnm() {
		int couponSize = 1;

		final char[] possibleCharacters = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'A', 'B', 'C', 'D', 'E',
				'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
				'Z' };

		final int possibleCharacterCount = possibleCharacters.length;
		String[] arr = new String[couponSize];
		Random rnd = new Random();
		int currentIndex = 0;
		int i = 0;
		while (currentIndex < couponSize) {
			StringBuffer buf = new StringBuffer(16);
			// i�� 8�ڸ��� �������� �ǹ�
			for (i = 16; i > 0; i--) {
				buf.append(possibleCharacters[rnd.nextInt(possibleCharacterCount)]);
			}
			String couponnum = buf.toString();
			System.out.println("couponnum==>" + couponnum);
			arr[currentIndex] = couponnum;
			currentIndex++;
			cptf1.setText(couponnum);
		}
	}

	// ���� �߰�
	public void addcoupon() throws ParseException {
		Vector<String> vector = new Vector<String>();
		vector.add(cptf1.getText());
		vector.add(cptf2.getText());
		vector.add(cptf3.getText());
		vector.add(cptf4.getText());
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "insert into coupon values(?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cptf1.getText());
			pstmt.setInt(2, Integer.parseInt(cptf2.getText()));
			pstmt.setString(3, cptf3.getText());
			pstmt.setString(4, cptf4.getText());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (ClassNotFoundException eee) {

		} catch (SQLException ee) {
			System.err.println("���� �߰� ����!!!");
		}
		cptf1.setText(null);
		cptf2.setText(null);
		cptf3.setText(null);
		cptf4.setText(null);
		cptf1.requestFocus();
		coupondata.addElement(vector);
		coupondatamodel.fireTableDataChanged();
	}

	// ���� ����
	public void updatecoupon() throws ParseException {
		updatecouponRow = couponView.getSelectedRow();
		couponView.setValueAt(cptf1.getText(), updatecouponRow, 0);
		couponView.setValueAt(cptf2.getText(), updatecouponRow, 1);
		couponView.setValueAt(cptf3.getText(), updatecouponRow, 2);
		couponView.setValueAt(cptf4.getText(), updatecouponRow, 3);

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "update coupon set couponnm=?, couponvalue=?, rof=?, id=? where couponnm=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cptf1.getText());
			pstmt.setInt(2, Integer.parseInt(cptf2.getText()));
			pstmt.setString(3, cptf3.getText());
			pstmt.setString(4, cptf4.getText());
			pstmt.setString(5, cptf1.getText());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (ClassNotFoundException eee) {

		} catch (SQLException ee) {
			System.err.println(ee.toString());
		}
		cptf1.setText(null);
		cptf2.setText(null);
		cptf3.setText(null);
		cptf4.setText(null);
		cptf1.requestFocus();
	}

	// ���� ����
	public void deletecoupon() throws ParseException {
		JTable cb = couponView;
		int deleteRow = cb.getSelectedRow(); // ���� �������� �ʾ������ -1�� �����Ѵ�.
		if (deleteRow == -1) {
			return;
		}
		DefaultTableModel model = (DefaultTableModel) cb.getModel();
		model.removeRow(deleteRow);

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "delete from coupon where couponnm=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cptf1.getText().trim());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (ClassNotFoundException eee) {

		} catch (SQLException ee) {
			System.err.println(ee.toString());
		}
		cptf1.setText(null);
		cptf2.setText(null);
		cptf3.setText(null);
		cptf4.setText(null);
		cptf1.requestFocus();

	}

	// ����üũ ����
	public void viewcouponck() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "select * from COUPONCHECK";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Vector<String> vector = new Vector<String>();
				vector.add(rs.getString("COUPONNM"));
				vector.add(rs.getString("ID"));
				couponckdata.addElement(vector);
			}
			rs.close();
			pstmt.close();

		} catch (ClassNotFoundException eee) {
			System.out.println("����üũ �ҷ����� ����");
		} catch (SQLException e) {
			System.err.println("����üũ �ҷ����� ����2");
		}
	}

	// ����üũ �߰�
	public void addcouponck() throws ParseException {
		Vector<String> vector = new Vector<String>();
		vector.add(cktf1.getText());
		vector.add(cktf2.getText());
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "insert into couponcheck values(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cktf1.getText());
			pstmt.setString(2, cktf2.getText());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (ClassNotFoundException eee) {

		} catch (SQLException ee) {
			System.err.println("����üũ �߰� ����!!!");
		}
		cktf1.setText(null);
		cktf2.setText(null);
		cktf1.requestFocus();
		couponckdata.addElement(vector);
		couponckdatamodel.fireTableDataChanged();
	}

	// ����üũ ����
	public void updatecouponck() throws ParseException {
		updatecouponckRow = couponckView.getSelectedRow();
		couponckView.setValueAt(cktf1.getText(), updatecouponckRow, 0);
		couponckView.setValueAt(cktf2.getText(), updatecouponckRow, 1);

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "update couponcheck set couponnm=?, id=? where couponnm=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cktf1.getText());
			pstmt.setString(2, cktf2.getText());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (ClassNotFoundException eee) {

		} catch (SQLException ee) {
			System.err.println(ee.toString());
		}
		cktf1.setText(null);
		cktf2.setText(null);
		cktf1.requestFocus();
	}

	// ����üũ ����
	public void deletecouponck() throws ParseException {
		JTable ck = couponckView;
		int deleteRow = ck.getSelectedRow(); // ���� �������� �ʾ������ -1�� �����Ѵ�.
		if (deleteRow == -1) {
			return;
		}
		DefaultTableModel model = (DefaultTableModel) ck.getModel();
		model.removeRow(deleteRow);

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "delete from couponcheck where couponnm=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cktf1.getText().trim());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (ClassNotFoundException eee) {

		} catch (SQLException ee) {
			System.err.println(ee.toString());
		}
		cktf1.setText(null);
		cktf2.setText(null);
		cktf1.requestFocus();

	}

	public void ShowMyTicket() {// ��Ƽ�ϳ��� �����ֱ�(5/17 ����) Ƽ�Ϲ�ȣ(Ƽ��) �����̸�(show)
								// ���(show) ��¥(Ƽ��) �¼�(Ƽ��)
		// id �� 1�� reservation �� Ƽ�Ϲ�ȣ - ticket�� Ƽ�Ϲ�ȣ �̾��� ticket�� �����̸�- show��
		// �����̸�

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "select * from show inner join ticket on show.sid = ticket.sid inner join reservation on ticket.tnum=reservation.tnum where reservation.id=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id2.getText().trim());

			ResultSet rs = pstmt.executeQuery();

			numb = 0;
			while (rs.next()) {
				mytknum1[numb].setText(rs.getString("TNUM"));
				mytkname1[numb].setText(rs.getString("SNAME"));
				mytkloc1[numb].setText(rs.getString("SLOC"));
				mytkdate1[numb].setText(rs.getString("DTDATE"));
				mytkseat1[numb].setText(rs.getString("SEATID"));
				mytkimg[numb].setIcon(new ImageIcon(rs.getString("SIMG")));
				numb++;

			}

			rs.close();
			pstmt.close();
		} catch (ClassNotFoundException eee) {
			System.out.println("Class ����");
		} catch (SQLException ee) {
			System.err.println("SQL ����" + ee.toString());
		}
	}

	public int countShowMyTicket() {// ID���� ���� Ƚ�� COUNT

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "select count(*) cccnt from show inner join ticket on show.sid = ticket.sid inner join reservation on ticket.tnum=reservation.tnum where reservation.id=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id2.getText().trim());

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				numb = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (ClassNotFoundException eee) {
			System.out.println("Class ����");
		} catch (SQLException ee) {
			System.err.println("SQL ����" + ee.toString());
		}
		return numb;
	}

	public void saveMyticket() {
		for (int i = 0; i < numb; i++) {
			if (cancelcb[i].getState() == true) {
				shownum = mytknum1[i].getText().trim();
				showname = mytkname1[i].getText().trim();
				showloc = mytkloc1[i].getText().trim();
				showdate = mytkdate1[i].getText().trim();
				showseat = mytkseat1[i].getText().trim();
			}
		}
	}

	public void DeleteMyTicket() {// ��Ƽ�ϳ��� ����(5/18 ����) Ƽ�Ϲ�ȣ(Ƽ��) �����̸�(show)
									// ���(show) ��¥(Ƽ��) �¼�(Ƽ��)
		// 1. id �� 1�� reservation �� Ƽ�Ϲ�ȣ - ticket�� Ƽ�Ϲ�ȣ �̾���
		// 2. ticket�� �����̸�,��¥- show�� �����̸�,��¥ �̾ �ΰ� ���̺� ������
		// 3. seat- ticket tnum �̾ seat ����

		// saveMyticket();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "delete from seat where exists(select * from ticket where seat.sid=ticket.sid and seat.dtdate=ticket.dtdate and seat.seatid=?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, showseat);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (ClassNotFoundException eee) {
			System.out.println("Class ����1");
		} catch (SQLException ee) {
			System.err.println("SQL ����1" + ee.toString());
		}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "delete from reservation where TNUM=?";
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1, shownum);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (ClassNotFoundException eee) {
			System.out.println("Class ����2");
		} catch (SQLException ee) {
			System.err.println("SQL ����3" + ee.toString());
		}
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "delete from ticket where tnum=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, shownum);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (ClassNotFoundException eee) {
			System.out.println("Class ����");
		} catch (SQLException ee) {
			System.err.println("SQL ����" + ee.toString());
		}
	}

	public TotalTicket_sub12345() {
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
		Calendar cal = Calendar.getInstance();
		int today = cal.get(Calendar.DATE);
		if (today == 18 || today == 19 || today == today) // �̺�Ʈ �ϰ���� ��¥
			eventdlg.setVisible(true);

	}

	private void start() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/////////////// ���̵�� ��й�ȣ ã��
		Findid.addActionListener(this);
		Findpw.addActionListener(this);

		Findidtf.addMouseListener(this);
		Findpwtf.addMouseListener(this);
		Findidtf.addFocusListener(this);
		Findpwtf.addFocusListener(this);

		Findid_fid.addActionListener(this);
		Findid_fpw.addActionListener(this);
		Findpwbt.addActionListener(this);
		Findpokbt.addActionListener(this);
		Findippclbt.addActionListener(this);
		FindShowIDpclbt.addActionListener(this);
		FindShowPWpcokt.addActionListener(this);
		Finddlgbt.addActionListener(this);
		Findidtf.addFocusListener(this);
		Findpwtf.addFocusListener(this);
		///////////////////////////////////////////

		// 170518 ������ ����
		btnFAQ.addActionListener(this);
		rightbt.addActionListener(this);
		leftbt.addActionListener(this);
		joinbt.addActionListener(this);
		loginbt.addActionListener(this);
		mypagebt.addActionListener(this);
		homebt.addMouseListener(this);
		joinok.addActionListener(this);
		joinx.addActionListener(this);
		couponok.addActionListener(this);
		eventlgbt.addActionListener(this);
		eventclbt.addActionListener(this);
		loginok.addActionListener(this);
		loginx.addActionListener(this);
		joinokbt.addActionListener(this);
		joinxbt.addActionListener(this);
		loginokdlgbt.addActionListener(this);
		logoutbt.addActionListener(this);
		for (int i = 0; i < imgcount; i++) {
			mv[i].addMouseListener(this);
			mvc[i].addMouseListener(this);
		}

		btnCancle.addActionListener(this);
		btnSeatSelect.addActionListener(this);
		btnFinSeat.addActionListener(this);
		btnPayDlgCancle.addActionListener(this);
		cbCount.addActionListener(this);// 170518 ������ ����
		cbChargePoint.addActionListener(this);
		cbDay.addActionListener(this);
		btnReselect.addActionListener(this);
		searchtf.addFocusListener(this);
		searchtf.addMouseListener(this);
		searchbt.addActionListener(this);
		ltdate.addActionListener(this);
		ltingi.addActionListener(this);
		btnAddPoint.addActionListener(this);
		adminbt.addActionListener(this);
		customerinbt.addActionListener(this);
		customerupbt.addActionListener(this);
		customerdebt.addActionListener(this);
		shoinbt.addActionListener(this);
		shoupbt.addActionListener(this);
		shodebt.addActionListener(this);
		cpmkbt.addActionListener(this);
		cpinbt.addActionListener(this);
		cpupbt.addActionListener(this);
		cpdebt.addActionListener(this);
		ckinbt.addActionListener(this);
		ckupbt.addActionListener(this);
		ckdebt.addActionListener(this);
		customertableView.addMouseListener(this);
		showtableView.addMouseListener(this);
		couponView.addMouseListener(this);
		couponckView.addMouseListener(this);

		btnReview.addActionListener(this);// �۾����ư...
		dlgbt.addActionListener(this);// �۾��� ���̾�α��� �����ư...
		dlgbt1.addActionListener(this);// �۾��� ���̾�α��� ��ҹ�ư...
		bt.addActionListener(this);// ����Ʈ��� ��ư..
		adlgbt1.addActionListener(this);// ����Ʈ��� ���̾�α��� �ݱ��ư...
		adlgli.addMouseListener(this);// ����Ʈ��� ���̾�α��� ����Ʈ���...
		adlgbt.addActionListener(this);// ����Ʈ��� ���̾�α��� ���ø�Ϻ��� ��ư...
		bdlgbt.addActionListener(this);// �ۺ��� ���̾�α��� Ȯ�ι�ư...

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

		// 170518 ������ ���� ���� ���� �׳� â �ݾ��� ��
		chargedlg.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg) {
				cbChargePoint.setSelectedItem("0");
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

						// �¼��� �ش��ϴ� TNUM �ޱ�
						pstmt2.setString(1, btnSelected[i][j].getText());
						rs2 = pstmt2.executeQuery();
						while (rs2.next()) {
							System.out.println("Ƽ�� ��ȣ�� ����غ��� " + rs2.getString("TNUM"));
							// ���� ���̺� �ֱ�
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

	// seat ���̺� �߰��ϱ�
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
		// 170518 ������ ����
		sp1.add(btnFAQ);
		sp1.add(bt);
		sp1.add(btnReview);
		btnReview.setVisible(false);
		mp.add("West", sp1);

		// DB�� �ִ� ���� ���� ī��Ʈ
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "select * from show";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				imgcount++;
			}
			rs.close();
			pstmt.close();
		} catch (ClassNotFoundException eee) {
			System.err.println("");
		} catch (SQLException e) {
			System.err.println("");
		}
		mv = new JLabel[12];
		mvc = new JLabel[12];
		mvst = new String[12];
		image = new ImageIcon[12];

		for (int i = 0; i < imgcount; i++) {
			mv[i] = new JLabel();
			mvc[i] = new JLabel();
			mvst[i] = new String();
			image[i] = new ImageIcon();
		}

		mok = imgcount / 6;
		mok2 = imgcount % 6;

		pagep.add(leftbt);
		pagep.add(rightbt);

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
			System.err.println("Error : " + eee.toString());
		} catch (SQLException e) {
			System.err.println("Error : " + e.toString());
		}

		tp = new JPanel[mok + 1];

		for (int a = 0; a < mok + 1; a++) {
			tp[a] = new JPanel(new GridLayout(2, 3, 3, 3));
			for (int b = a * 6; b < (a + 1) * 6; b++) {
				if (b < mok2 + 6) {
					mv[b].setVerticalTextPosition(SwingConstants.BOTTOM);
					mv[b].setHorizontalTextPosition(SwingConstants.CENTER);
					tp[a].add(mv[b]);
				}
			}
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

		for (int i = 0; i < mok + 1; i++) {
			tpmaincard.add(tp[i]);
		}

		tpmain.add("East", tklistpn);
		tpmain.add("North", search);
		tpmain.add("Center", tpmaincard);
		tpmain.add("South", pagep);

		con.add("North", mp);
		con.add("Center", tpmain);

		// �̺�Ʈ ���̾�α�
		eventCon = eventdlg.getContentPane();
		eventCon.setLayout(new BorderLayout());
		eventdlg.setSize(500, 500);
		eventdlg.setResizable(false);
		eventpn.add("Center", eventlb6m);
		eventlbpn.add(eventalarm);
		eventlbpn2.add(eventguid);
		eventlbpn3.add(eventcoupon);
		eventbtpn.add(eventlgbt);
		eventbtpn.add(eventclbt);
		eventpn2.add(eventlbpn);
		eventpn2.add(eventlbpn2);
		eventpn2.add(eventlbpn3);
		eventpn2.add(eventbtpn);

		eventCon.add("Center", eventpn);
		eventCon.add("South", eventpn2);

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

		////////////// ���̵� �� ��й�ȣ ã�� ȭ�鱸��
		Findbtp.add(Findid);
		Findbtp.add(Findpw);

		Findcon = Finddlg.getContentPane();
		Findcon.setLayout(new CardLayout());
		Finddlg.setSize(200, 100);
		;
		Dimension diFinddlg = Finddlg.getSize();
		Finddlg.setLocation((int) (di.getWidth() / 2 - diFinddlg.getWidth() / 2),
				(int) (di.getHeight() / 2 - diFinddlg.getHeight() / 2));

		Findidbtp.add(Findid_fid);
		Findidbtp.add(Findippclbt);
		Findidp.add("North", Findidtf);
		Findidp.add("South", Findidbtp);

		Findpwp.add(Findpwtf);
		Findpwp.add(Findpwbt);

		FindShowIDp.add(FindShowid);
		FindShowIDp.add(Findid_fpw);
		FindShowIDp.add(FindShowIDpclbt);
		FindShowPWp.add(FindShowpw);
		FindShowPWp.add(FindShowPWpcokt);

		Findidp.setVisible(false);
		Findpwp.setVisible(false);

		Finddlg.add(Findidp);
		Finddlg.add(Findpwp);
		Finddlg.add(FindShowIDp);
		Finddlg.add(FindShowPWp);

		Finderrcon = Finderrdlg.getContentPane();
		Finderrcon.setLayout(new BorderLayout(5, 5));
		Finderrdlg.setSize(250, 100);
		Finderrdlg.add("West", Finderrimgib);
		Finderrdlg.add("Center", Finderrlb);
		Finderrdlg.add("South", Finddlgbt);

		Findbtp.setVisible(true);

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

		//// ���̵� �� ��й�ȣ ã�� ���� ����
		JPanel login = new JPanel(new FlowLayout());
		login.add(loginp);

		JPanel loginbtp = new JPanel(new BorderLayout());
		JPanel loginbtp_s_p = new JPanel(new FlowLayout());
		loginbtp_s_p.add(loginok);
		loginbtp_s_p.add(loginx);

		loginbtp.add("South", loginbtp_s_p);
		loginbtp.add("North", Findbtp);

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
		adminPane.add("���� ����", couponP);
		adminPane.add("����üũ ����", couponckP);
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

		cp1.add(clb1);
		cp1.add(ctf1);
		cp2.add(clb2);
		cp2.add(ctf2);
		cp3.add(clb3);
		cp3.add(ctf3);
		cp4.add(clb4);
		cp4.add(ctf4);
		cp5.add(clb5);
		cp5.add(ctf5);
		cp6.add(clb6);
		cp6.add(ctf6);
		cp7.add(clb7);
		cp7.add(ctf7);

		cp.add(cp1);
		cp.add(cp2);
		cp.add(cp3);
		cp.add(cp4);
		cp.add(cp5);
		cp.add(cp6);
		cp.add(cp7);

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

		ssp1.add(sslb1);
		ssp1.add(sstf1);
		ssp2.add(sslb2);
		ssp2.add(sstf2);
		ssp3.add(sslb3);
		ssp3.add(sstf3);
		ssp4.add(sslb4);
		ssp4.add(sstf4);
		ssp5.add(sslb5);
		ssp5.add(sstf5);
		ssp6.add(sslb6);
		ssp6.add(sstf6);

		ssp.add(ssp1);
		ssp.add(ssp2);
		ssp.add(ssp3);
		ssp.add(ssp4);
		ssp.add(ssp5);
		ssp.add(ssp6);

		showP.add("Center", ssp);

		sho.add(shoinbt);
		sho.add(shoupbt);
		sho.add(shodebt);

		showP.add("South", sho);

		admincon.add(adminPane);

		// ������ ��� ���� ���� ����
		coupondata = new Vector<Vector<String>>();
		couponNames = new Vector<String>();

		for (int i = 0; i < couponName.length; i++) {
			couponNames.add(couponName[i]);
		}

		coupondatamodel = new DefaultTableModel(coupondata, couponNames);
		couponView = new JTable(coupondatamodel);
		couponscrollList = new JScrollPane(couponView);

		couponP.add("North", couponscrollList);

		cpp1.add(cplb1);
		cpp1.add(cptf1);
		cpp2.add(cplb2);
		cpp2.add(cptf2);
		cpp3.add(cplb3);
		cpp3.add(cptf3);
		cpp4.add(cplb4);
		cpp4.add(cptf4);

		cppn.add(cpp1);
		cppn.add(cpp2);
		cppn.add(cpp3);
		cppn.add(cpp4);

		couponP.add("Center", cppn);

		cpo.add(cpmkbt);
		cpo.add(cpinbt);
		cpo.add(cpupbt);
		cpo.add(cpdebt);

		couponP.add("South", cpo);

		admincon.add(adminPane);

		// ������ ��� ����üũ ���� ����
		couponckdata = new Vector<Vector<String>>();
		couponckNames = new Vector<String>();

		for (int i = 0; i < couponckName.length; i++) {
			couponckNames.add(couponckName[i]);
		}

		couponckdatamodel = new DefaultTableModel(couponckdata, couponckNames);
		couponckView = new JTable(couponckdatamodel);
		couponckscrollList = new JScrollPane(couponckView);

		couponckP.add("North", couponckscrollList);

		ckp1.add(cklb1);
		ckp1.add(cktf1);
		ckp2.add(cklb2);
		ckp2.add(cktf2);

		ckpn.add(ckp1);
		ckpn.add(ckp2);

		couponckP.add("Center", ckpn);

		cko.add(ckinbt);
		cko.add(ckupbt);
		cko.add(ckdebt);

		couponckP.add("South", cko);

		admincon.add(adminPane);

		// 170518 ������ ���� ����Ʈ ���� ���̾�α� ����
		addPointcon = addPointDlg.getContentPane();
		addPointDlg.setLayout(new BorderLayout());
		addPointDlg.setSize(300, 200);
		Dimension dimPoint = addPointDlg.getSize();
		addPointDlg.setLocation((int) (di.getWidth() / 2 - dimPoint.getWidth() / 2),
				(int) (di.getHeight() / 2 - dimPoint.getHeight() / 2));

		JPanel plPoint1 = new JPanel(new FlowLayout());
		JPanel plPoint2 = new JPanel(new FlowLayout());

		plPoint1.add(lbaddPoint);
		plPoint2.add(btnAddPoint);

		addPointcon.add("Center", plPoint1);
		addPointcon.add("South", plPoint2);

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
		payDlg.setSize(350, 220);

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

		JPanel plFlow = new JPanel(new FlowLayout());
		JPanel pl12 = new JPanel(new BorderLayout());

		JPanel pla = new JPanel(new GridLayout(2, 1, 5, 5));
		pla.add(lbPay);
		pla.add(lbPoint);

		JPanel plb = new JPanel(new GridLayout(2, 1, 5, 5));
		plb.add(lbToPay);
		plb.add(lbMyPoint);

		pl12.add("West", pla);
		pl12.add("Center", plb);

		plFlow.add(pl12);

		JPanel pl13 = new JPanel(new FlowLayout());
		pl13.add(btnPayDlgPay);
		pl13.add(btnPayDlgCancle);

		rsvCon.add("Center", pl5);
		rsvCon.add("South", pl7);
		lbScreen.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

		sltSeatCon.add("North", lbScreen);
		sltSeatCon.add("Center", plAllSection);
		sltSeatCon.add("South", p11);

		payCon.add("North", plFlow);
		payCon.add("Center", pl13);

		// ����������
		tPane.setPreferredSize(new Dimension(1000, 550));
		tPane.add("����������", myP);
		tPane.add("����", edit);
		tPane.add("����Ʈ", pointp);
		tPane.add("��������", show);
		tPane.add("�����Է�", coupon);
		tPane.setTabPlacement(JTabbedPane.LEFT);

		//////////// ���� ����
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

		//////////////////////// ����Ʈ ���� ����
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
		constraint.gridx = 0;
		constraint.gridy = 5;
		pointP1.add(point6, constraint);
		constraint.gridx = 0;
		constraint.gridy = 6;

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
		// 170518 ������ ����
		chargep.add(cbChargePoint);
		chargep.add(chargebt);

		chargecon.add("Center", chargep);
		///////////////////////////////////////// ����Ʈ ���� ��, �������� ����
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

		//////////////////////////////////// ������Ҵ��̾�α�

		canceldlgp.add(cancelokbt);
		canceldlgp.add(cancelnobt);

		realcancelp.add("North", realcancel);
		realcancelp.add("Center", canceldlgp);
		canceldlg.add(realcancelp);

		// �����Է� ����
		couponlbpn.add(couponlb);
		coupontfpn.add(coupon1);
		coupontfpn.add(coupon11);
		coupontfpn.add(coupon2);
		coupontfpn.add(coupon22);
		coupontfpn.add(coupon3);
		coupontfpn.add(coupon33);
		coupontfpn.add(coupon4);
		couponbtpn.add(couponok);
		coupon.add("North", couponlbpn);
		coupon.add("Center", coupontfpn);
		coupon.add("South", couponbtpn);

		/////////////////////////////////////////////////// ????

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

		// �۾��� ���̾�α׸� �����մϴ�.
		dlgcon = jdlg.getContentPane();
		dlgcon.setLayout(new BorderLayout(5, 5));
		JPanel dlgjp1 = new JPanel(new BorderLayout());
		dlgjp1.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED), "�⺻����"));
		JPanel dlgjp2 = new JPanel(new GridLayout(3, 1));
		dlgjp2.add(dlglb);
		dlgjp2.add(dlglb1);
		dlgjp2.add(dlglb2);
		dlgjp1.add("West", dlgjp2);
		JPanel dlgjp3 = new JPanel(new GridLayout(3, 1));

		dlgjp3.add(dlgtf);
		dlgjp3.add(dlgtf1);
		dlgjp3.add(dlgtf2);
		dlgjp1.add("Center", dlgjp3);
		dlgcon.add("North", dlgjp1);
		dlgjsp.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED), "�����ۼ�"));
		dlgcon.add("Center", dlgjsp);
		JPanel dlgjp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		dlgbt.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		dlgbt1.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		dlgjp.add(dlgbt);
		dlgjp.add(dlgbt1);
		dlgcon.add("South", dlgjp);
		jdlg.setSize(400, 400);
		jdlg.setResizable(false);
		Toolkit dlgtk = Toolkit.getDefaultToolkit();
		Dimension dlgdi = dlgtk.getScreenSize();
		Dimension dlgdi1 = jdlg.getSize();
		jdlg.setLocation((int) (dlgdi.getWidth() / 2 - dlgdi1.getWidth() / 2),
				(int) (dlgdi.getHeight() / 2 - dlgdi1.getHeight() / 2));
		// �۾��� ���̾�α� ������..

		// ����Ʈ ��� ���̾�α� ����...
		adlgcon = adlg.getContentPane();
		adlgcon.setLayout(new BorderLayout(5, 5));
		adlgcon.add("North", adlglb);
		adlgcon.add("Center", adlgjsp);
		JPanel adlgjp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		adlgjp.add(adlgbt);
		adlgjp.add(adlgbt1);
		adlgcon.add("South", adlgjp);
		adlg.setSize(400, 400);
		adlg.setResizable(false);
		Toolkit adlgtk = Toolkit.getDefaultToolkit();
		Dimension adlgdi = adlgtk.getScreenSize();
		Dimension adlgdi1 = adlg.getSize();
		adlg.setLocation((int) (adlgdi.getWidth() / 2 - adlgdi1.getWidth() / 2),
				(int) (adlgdi.getHeight() / 2 - adlgdi1.getHeight() / 2));
		// ����Ʈ ��� ���̾�α� ������...

		// �ۺ��� ���̾�α� ����..
		bdlgcon = bdlg.getContentPane();
		bdlgcon.setLayout(new BorderLayout());
		JPanel bdlgjp1 = new JPanel(new BorderLayout());
		JPanel bdlgjp2 = new JPanel(new GridLayout(2, 1));
		bdlgjp2.add(bdlglb);
		bdlgjp2.add(bdlglb1);
		bdlgjp1.add("West", bdlgjp2);
		JPanel bdlgjp3 = new JPanel(new GridLayout(2, 1));
		bdlgjp3.add(bdlgtf);
		bdlgjp3.add(bdlgtf1);
		bdlgjp1.add("Center", bdlgjp3);
		bdlgcon.add("North", bdlgjp1);
		bdlgcon.add("Center", bdlgjsp);
		JPanel bdlgjp = new JPanel(new FlowLayout());
		bdlgjp.add(bdlgbt);
		bdlgcon.add("South", bdlgjp);
		bdlgta.setEnabled(false);
		bdlgta.setDisabledTextColor(Color.black);
		bdlg.setSize(300, 200);
		bdlg.setResizable(false);
		Toolkit bdlgtk = Toolkit.getDefaultToolkit();
		Dimension bdlgdi = bdlgtk.getScreenSize();
		Dimension bdlgdi1 = bdlg.getSize();
		bdlg.setLocation((int) (bdlgdi.getWidth() / 2 - bdlgdi1.getWidth() / 2),
				(int) (bdlgdi.getHeight() / 2 - bdlgdi1.getHeight() / 2));
		// �ۺ��� ���̾�α� ������...

		tpmain.setVisible(true);
		bestshow();
		dateshow();
	}

	int cnt = 0;

	@Override
	public void actionPerformed(ActionEvent e) {
		///////////// ���̵� �� ��й�ȣ ã��
		if (e.getSource() == Findid) {
			Finddlg.setVisible(true);
			Findidtf.setText("����Ͻ� E-mail�̳� ��ȭ��ȣ�� �Է��ϼ���");
			Findpwp.setVisible(false);
			FindShowIDp.setVisible(false);
			FindShowPWp.setVisible(false);

		}
		if (e.getSource() == Findpw) {
			Finddlg.setVisible(true);
			Findpwtf.setText("����Ͻ�ID�� �Է��ϼ���");
			Findidp.setVisible(false);
			Findpwp.setVisible(true);
			FindShowIDp.setVisible(false);
			FindShowPWp.setVisible(false);

		}

		if (e.getSource() == Findid_fid) {
			if (!Findidtf.getText().trim().equals("")) {

				Findidp.setVisible(false);
				Findpwp.setVisible(false);
				FindShowIDp.setVisible(true);
				FindShowPWp.setVisible(false);
				FindidDB();
			} else if (Findidtf.getText().trim().equals("")) {
				Toolkit tk = Toolkit.getDefaultToolkit();
				Dimension di = tk.getScreenSize();
				Dimension di1 = Finderrdlg.getSize();
				Finderrdlg.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
						(int) (di.getHeight() / 2 - di1.getHeight() / 2));
				Finderrdlg.setVisible(true);
			}

		}

		if (e.getSource() == Findid_fpw) {

			Findidp.setVisible(false);
			Findpwp.setVisible(true);
			FindShowIDp.setVisible(false);
			FindShowPWp.setVisible(false);
			Findpwtf.setText("����Ͻ�ID�� �Է��ϼ���");
		}

		if (e.getSource() == Findpwbt) {
			if (!Findpwtf.getText().trim().equals("")) {

				Findidp.setVisible(false);
				Findpwp.setVisible(false);
				FindShowIDp.setVisible(false);
				FindShowPWp.setVisible(true);
				FindpwDB();
			} else if (Findpwtf.getText().trim().equals("")) {
				Toolkit tk = Toolkit.getDefaultToolkit();
				Dimension di = tk.getScreenSize();
				Dimension di1 = Finderrdlg.getSize();
				Finderrdlg.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
						(int) (di.getHeight() / 2 - di1.getHeight() / 2));
				Finderrdlg.setVisible(true);
			}

		}

		if (e.getSource() == Findpokbt) {

			Findidp.setVisible(false);
			Findpwp.setVisible(false);
			FindShowIDp.setVisible(false);
			FindShowPWp.setVisible(false);
		}

		if (e.getSource() == Findippclbt) {
			Finddlg.setVisible(false);
			;
		}

		if (e.getSource() == FindShowIDpclbt) {
			Finddlg.setVisible(false);
			;
			/*
			 * Findidp.setVisible(false); Findpwp.setVisible(false);
			 * FindShowIDp.setVisible(false); FindShowPWp.setVisible(false);
			 */
		}

		if (e.getSource() == FindShowPWpcokt) {
			Finddlg.setVisible(false);
			;
			/*
			 * Findidp.setVisible(false); Findpwp.setVisible(false);
			 * FindShowIDp.setVisible(false); FindShowPWp.setVisible(false);
			 */
		}

		if (e.getSource() == Finddlgbt) {
			Finderrdlg.setVisible(false);
		}
		// 170518 ������ ���� ������ ���� �ܾ� ����
		strChargePoint = cbChargePoint.getSelectedItem().toString();

		// �޺� �ڽ� ���� �ο���
		strPersonCnt = cbCount.getSelectedItem().toString();
		lbAllSeat.setText(strPersonCnt);

		// 170519 ������ ����
		if (e.getSource() == btnFAQ) { // 1:1 �����ϱ�
			// FAQServer faqserver = new FAQServer();
			MultiChatClient mclient = new MultiChatClient(loginokdlglb1.getText());
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
			tpmaincard.setVisible(false);
			tpmain.add("Center", srchresult);
			searchtf.setText("���� �Ǵ� ��¥(�� : 7�� 4�� => 07/04 �Ǵ� 7/04) �Է�");
			srchresult.setVisible(true);
			System.out.println(lbct);

			if (lbct == 0 || lbct == num) {
				srchresult.removeAll();
				JOptionPane.showMessageDialog(null, "�˻� ����� �����ϴ�!", "�˻� ��� ����", JOptionPane.ERROR_MESSAGE);
				tpmain.add("Center", tpmaincard);
				tpmaincard.setVisible(true);
				srchresult.setVisible(false);

			}

		} else if (e.getSource() == eventlgbt) { // �̺�Ʈ ���̾�α� �α��ι�ư
			logindlg.setVisible(true);
		} else if (e.getSource() == eventclbt) { // �̺�Ʈ ���̾�α� �ݱ��ư
			eventdlg.setVisible(false);
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
			eventlgbt.setVisible(false);
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
			btnReview.setVisible(false);
			logoutMember();
			eventlgbt.setVisible(true);
		} // �α׾ƿ� ��ư

		else if (e.getSource() == btnCancle) { // ��� ��ư
			rsvDlg.setVisible(false);
		} else if (e.getSource() == btnSeatSelect) { // �¼� ���� �κ� ������ ��
			// �޺� �ڽ� ��¥ ���� ��
			cbSelectedDate = cbDay.getSelectedItem().toString();

			if (loginokdlglb1.getText() == "") {
				JOptionPane.showMessageDialog(null, "�α����� ���ּ���!", "��α��� ����", JOptionPane.WARNING_MESSAGE);
				logindlg.setVisible(true);
				return;
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
			lbToPay.setForeground(Color.RED);
			readPoint();
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
		} else if (e.getSource() == btnReview) {
			jdlg.setVisible(true);
		} else if (e.getSource() == dlgbt) {
			String str = dlgtf.getText().trim();// �ۼ���
			String str1 = dlgtf1.getText().trim();// ����
			String str2 = new String(dlgtf2.getPassword());// ��й�ȣ
			String str3 = dlgta.getText().trim();// �۳���
			FreeBoard_Sub1 ess = new FreeBoard_Sub1(str, str1, str2, str3);
			File dir = new File("C:\\workspace\\data");
			String[] files = dir.list();
			File file = null;
			if (files.length == 0) {
				file = new File(dir, "1.myfile");
				ess.setNumber(1);
			} else {
				int[] ii = new int[files.length];
				for (int i = 0; i < files.length; i++) {
					ii[i] = Integer.parseInt(files[i].substring(0, files[i].indexOf(".")));
				}
				int kkk = -1;
				for (int i = 0; i < ii.length; i++) {
					if (kkk < ii[i]) {
						kkk = ii[i];
					}
				}
				file = new File(dir, kkk + 1 + ".myfile");
				ess.setNumber(kkk + 1);
			}
			try {
				FileOutputStream fo = new FileOutputStream(file);
				BufferedOutputStream bo = new BufferedOutputStream(fo);
				ObjectOutputStream oos = new ObjectOutputStream(bo);
				oos.writeObject(ess);
				oos.close();
				bo.close();
				fo.close();
			} catch (FileNotFoundException ee) {
			} catch (IOException ee) {
			}
			dlgtf1.setText("");
			dlgtf2.setText("");
			dlgta.setText(" ");
			dlgta.setText("");
			jdlg.setVisible(false);
		} else if (e.getSource() == dlgbt1) {
			dlgtf1.setText("");
			dlgtf2.setText("");
			dlgta.setText(" ");
			dlgta.setText("");
			jdlg.setVisible(false);
		} else if (e.getSource() == bt) {
			// ����Ʈ�� �����־�� �Ѵ�.
			File dir = new File("C:\\workspace\\data");
			adlgvc.clear();
			String[] files = dir.list();
			for (int i = 0; i < files.length; i++) {
				File file = new File(dir, files[i]);
				try {
					FileInputStream fi = new FileInputStream(file);
					BufferedInputStream bi = new BufferedInputStream(fi);
					ObjectInputStream ois = new ObjectInputStream(bi);
					FreeBoard_Sub1 ess = (FreeBoard_Sub1) ois.readObject();
					ois.close();
					bi.close();
					fi.close();
					String imsi = "";
					imsi += files[i].substring(0, files[i].indexOf("."));
					imsi += " : ";
					imsi += ess.getTitle();
					imsi += " : ";
					imsi += ess.getName();
					imsi += " : ";
					imsi += ess.getDate();
					imsi += " : ";
					imsi += ess.getSearchnum();
					adlgvc.add(imsi);
				} catch (FileNotFoundException ee) {
				} catch (IOException ee) {
				} catch (ClassNotFoundException ee) {
				}
			}
			adlgli.setListData(adlgvc);
			// adlgli.updateUI();
			adlg.setVisible(true);
		} else if (e.getSource() == adlgbt1) {
			adlg.setVisible(false);
		} else if (e.getSource() == adlgbt) {
			view();
		} else if (e.getSource() == bdlgbt) {
			bdlg.setVisible(false);
			File dir = new File("C:\\workspace\\data");
			adlgvc.clear();
			String[] files = dir.list();
			for (int i = 0; i < files.length; i++) {
				File file = new File(dir, files[i]);
				try {
					FileInputStream fi = new FileInputStream(file);
					BufferedInputStream bi = new BufferedInputStream(fi);
					ObjectInputStream ois = new ObjectInputStream(bi);
					FreeBoard_Sub1 ess = (FreeBoard_Sub1) ois.readObject();
					ois.close();
					bi.close();
					fi.close();
					String imsi = "";
					imsi += files[i].substring(0, files[i].indexOf("."));
					imsi += " : ";
					imsi += ess.getTitle();
					imsi += " : ";
					imsi += ess.getName();
					imsi += " : ";
					imsi += ess.getDate();
					imsi += " : ";
					imsi += ess.getSearchnum();
					adlgvc.add(imsi);
				} catch (FileNotFoundException ee) {
				} catch (IOException ee) {
				} catch (ClassNotFoundException ee) {
				}
			}
			adlgli.setListData(adlgvc);
			// adlgli.updateUI();
			adlg.setVisible(true);
		}

		else if (e.getSource() == mypagebt) { // ����������
			coupon1.setText("");
			coupon2.setText("");
			coupon3.setText("");
			coupon4.setText("");
			tpmain.setVisible(false);
			srchresult.setVisible(false);
			BuyerP.setVisible(false);
                        mypagep.setVisible(true); 	
			
  		if (state) {				
				numb = countShowMyTicket();
				mytkp = new JPanel[numb];
				tkall = new JPanel[numb];
				cancelcb = new Checkbox[numb];

				mytkinfp = new Panel[numb];
				tknump = new Panel[numb];
				mytknum = new JLabel[numb];
				mytknum1 = new JLabel[numb];

				tknamep = new Panel[numb];
				mytkname = new JLabel[numb];
				mytkname1 = new JLabel[numb];

				tklocp = new Panel[numb];
				mytkloc = new JLabel[numb];
				mytkloc1 = new JLabel[numb];

				tkdatep = new Panel[numb];
				mytkdate = new JLabel[numb];
				mytkdate1 = new JLabel[numb];

				tkseatp = new Panel[numb];
				mytkseat = new JLabel[numb];
				mytkseat1 = new JLabel[numb];

				img = new ImageIcon[numb];
				cimg = new Image[numb];
				mytkimg = new JLabel[numb];
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

				ShowMyTicket();// (5/17)
				state = false;

			} else {
			}
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
		else if (e.getSource() == couponok) { // ������ȣ Ȯ�� ��ư
			coupon();
		}

		///////////////////////////////////////////////////////// ����Ʈ

		else if (e.getSource() == charge) {
			chargedlg.setVisible(true);
		} // ����Ʈ ������ư

		else if (e.getSource() == chargebt) { // 170519 ������ �������������� ����Ʈ ������ ��
			chargepoint(strChargePoint);
			chargedlg.setVisible(false);
		} else if (e.getSource() == btnAddPoint) { // 170519 ������ ���� �ݾ� ������ ����Ʈ
													// ����
			addPointDlg.setVisible(false);
			chargedlg.setVisible(true);
		} else if (e.getSource() == btnPayDlgPay) { // Ƽ�ϵ� ���� �Ϸ��
			// 170518 ������ ���� ���� �ݾ��� ������ �� ���� ���ϱ�
			if (Integer.parseInt(lbToPay.getText()) > Integer.parseInt(lbMyPoint.getText())) {
				System.out.println("���� �ݾ��� ����");
				addPointDlg.setVisible(true);
			} else { // 170518 ������ ���� ���� �ݾ��� ���� ��
				minusPoint(Integer.parseInt(lbToPay.getText()), Integer.parseInt(lbMyPoint.getText()));
				try {
					addSeat(selectedSid, cbSelectedDate);
					addTicket(selectedSid, cbSelectedDate);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				payDlg.setVisible(false);
				sltSeatDlg.setVisible(false);
				rsvDlg.setVisible(false);
			}
		}
		///////////////////////////////////////////////////// �������� �������

		else if (e.getSource() == canceltk) {
			for (int i = 0; i < numb; i++) {
				if (cancelcb[i].getState() == true) {
					canceldlg.setSize(200, 200);
					canceldlg.setVisible(true);

				}
			}
		} else if (e.getSource() == cancelokbt) {

			for (int i = 0; i < numb; i++) {
				if (cancelcb[i].getState() == true) {
					saveMyticket();
					DeleteMyTicket();
					/*
					 * tkall[i].setVisible(false); canceldlg.setVisible(false);
					 */
					showtkp.remove(tkall[i]);
					// showtkp.remove(mytkp[i]);
					// tkall[i].removeAll();

					canceldlg.setVisible(false);

					mypagep.setVisible(false);
					mypagep.setVisible(true);
					// countShowMyTicket();
					// ShowMyTicket();
				}
			}
		} else if (e.getSource() == cancelnobt) {
			canceldlg.setVisible(false);
		} else if (e.getSource() == btnPayDlgPay) { // Ƽ�ϵ� ���� �Ϸ��
			// TODO : ���� �ݾ��� ������ �� ���� ���ϱ�
			if (Integer.parseInt(lbToPay.getText()) > Integer.parseInt(lbMyPoint.getText())) {
				System.out.println("���� �ݾ��� ����");
				addPointDlg.setVisible(true);
			} else {
				try {
					addSeat(selectedSid, cbSelectedDate);
					addTicket(selectedSid, cbSelectedDate);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				payDlg.setVisible(false);
				sltSeatDlg.setVisible(false);
				rsvDlg.setVisible(false);
			}
		} else if (e.getSource() == btnPayDlgCancle) {
			payDlg.setVisible(false);
			sltSeatDlg.setVisible(false);
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

		else if (e.getSource() == adminbt) { // ������ ���
			admindlg.setVisible(true);

		} // ������ ���

		else if (e.getSource() == customerinbt) { // ������ �߰� ��ư
			addcustomer();
		} // ������ �߰� ��ư

		else if (e.getSource() == customerupbt) { // ������ ���� ��ư
			updatecustomer();
		} // ������ ���� ��ư

		else if (e.getSource() == customerdebt) { // ������ ���� ��ư
			deletecustomer();
		} // ������ ���� ��ư

		else if (e.getSource() == shoinbt) { // �������� �߰� ��ư
			try {
				addshow();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} // �������� �߰� ��ư

		else if (e.getSource() == shoupbt) { // �������� ���� ��ư
			try {
				updateshow();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} // �������� ���� ��ư

		else if (e.getSource() == shodebt) { // �������� ���� ��ư
			try {
				deleteshow();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} // �������� ���� ��ư

		else if (e.getSource() == cpmkbt) { // ���� ���� ��ư
			couponnm();
		} else if (e.getSource() == cpinbt) { // ���� �߰� ��ư
			try {
				addcoupon();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == cpupbt) { // ���� ���� ��ư
			try {
				updatecoupon();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == cpdebt) { // ���� ���� ��ư
			try {
				deletecoupon();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == ckinbt) { // ����üũ �߰� ��ư
			try {
				addcouponck();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == ckupbt) { // ����üũ ���� ��ư
			try {
				updatecouponck();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == ckdebt) { // ����üũ ���� ��ư
			try {
				deletecouponck();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		else if (e.getSource() == rightbt) {
			if (count == mok) {
				System.out.println("������ ������ �Դϴ�.");
			} else {
				tpmaincard.add(tp[count + 1]);
				tp[count + 1].setVisible(true);
				tp[count].setVisible(false);
				count++;
			}

			/*
			 * tpmaincard.add(tp[1]); tp[1].setVisible(true);
			 * tp[0].setVisible(false);
			 */
		} else if (e.getSource() == leftbt) {
			if (count == 1) {
				tpmaincard.add(tp[0]);
				tp[0].setVisible(true);
				tp[1].setVisible(false);
				count = 0;
			} else if (count == 0) {
				System.out.println("ù������ �Դϴ�.");
			} else {
				tpmaincard.add(tp[count - 1]);
				tp[count - 1].setVisible(true);
				tp[count - 2].setVisible(false);
				count--;
			}
		}

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
		if (e.getSource() == customertableView) {
			updatecustomerRow = customertableView.getSelectedRow();
			ctf1.setText((String) customertableView.getValueAt(updatecustomerRow, 0));
			ctf2.setText((String) customertableView.getValueAt(updatecustomerRow, 1));
			ctf3.setText((String) customertableView.getValueAt(updatecustomerRow, 2));
			ctf4.setText((String) customertableView.getValueAt(updatecustomerRow, 3));
			ctf5.setText((String) customertableView.getValueAt(updatecustomerRow, 4));
			ctf6.setText((String) customertableView.getValueAt(updatecustomerRow, 5));
			ctf7.setText((String) customertableView.getValueAt(updatecustomerRow, 6));
		}

		else if (e.getSource() == showtableView) {
			updateshowRow = showtableView.getSelectedRow();
			sstf1.setText((String) showtableView.getValueAt(updateshowRow, 0));
			sstf2.setText((String) showtableView.getValueAt(updateshowRow, 1));
			sstf3.setText((String) showtableView.getValueAt(updateshowRow, 2));
			sstf4.setText((String) showtableView.getValueAt(updateshowRow, 3));
			sstf5.setText((String) showtableView.getValueAt(updateshowRow, 4));
			sstf6.setText((String) showtableView.getValueAt(updateshowRow, 5));
		} else if (e.getSource() == couponView) {
			updatecouponRow = couponView.getSelectedRow();
			cptf1.setText((String) couponView.getValueAt(updatecouponRow, 0));
			cptf2.setText((String) couponView.getValueAt(updatecouponRow, 1));
			cptf3.setText((String) couponView.getValueAt(updatecouponRow, 2));
			cptf4.setText((String) couponView.getValueAt(updatecouponRow, 3));
		} else if (e.getSource() == couponckView) {
			updatecouponckRow = couponckView.getSelectedRow();
			cktf1.setText((String) couponckView.getValueAt(updatecouponckRow, 0));
			cktf2.setText((String) couponckView.getValueAt(updatecouponckRow, 1));
		}
		///////////// ���̵� �� ��й�ȣã��
		if (e.getSource() == Findidtf) {
			Findidtf.setText("");
		}
		if (e.getSource() == Findpwtf) {
			Findpwtf.setText("");
		}

		else if (e.getSource() == adlgli) {
			if (e.getClickCount() == 2) {// ����Ŭ��....
				view();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == homebt) { // Ȩ��ư
			tpmaincard.add(tp[0]);
			tp[0].setVisible(true);
			tp[1].setVisible(false);
			tpmain.setVisible(true);
			srchresult.setVisible(false);
			BuyerP.setVisible(false);
			mypagep.setVisible(false);
			searchtf.setText("���� �Ǵ� ��¥(�� : 7�� 4�� => 07/04 �Ǵ� 7/04) �Է�");
		} // Ȩ��ư

		for (int i = 0; i < imgcount; i++) { // ������ �̹��� ������ ��
			if (e.getSource() == mv[i]) {
				saveshowname = mv[i].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
				// cbDay.removeAllItems();
			}
		}

		for (int i = 0; i < imgcount; i++) { // ī�� ������ �̹��� ������ ��
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
		////////// ���̵�� ��й�ȣ ã��
		if (e.getSource() == Findidtf) {
			Findidtf.setText("����Ͻ� E-mail�̳� ��ȭ��ȣ�� �Է��ϼ���");
		}
		if (e.getSource() == Findpwtf) {
			Findpwtf.setText("����Ͻ�ID�� �Է��ϼ���");
		}
		//////////////////////////
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

	private void view() {
		// System.out.println((String)adlgli.getSelectedValue());
		File dir = new File("C:\\workspace\\data");
		String str = (String) adlgli.getSelectedValue();
		String imsi = str.substring(0, str.indexOf(":"));
		imsi = imsi.trim();
		File file = new File(dir, imsi + ".myfile");
		FreeBoard_Sub1 ess = null;
		try {
			FileInputStream fi = new FileInputStream(file);
			BufferedInputStream bi = new BufferedInputStream(fi);
			ObjectInputStream ois = new ObjectInputStream(bi);
			ess = (FreeBoard_Sub1) ois.readObject();
			ois.close();
			bi.close();
			fi.close();
		} catch (FileNotFoundException ee) {
		} catch (IOException ee) {
		} catch (ClassNotFoundException ee) {
		}
		bdlgtf.setText(ess.getTitle());
		bdlgtf1.setText(ess.getName());
		bdlgta.setText(ess.getMemo());
		adlg.setVisible(false);
		int iii = ess.getSearchnum();
		iii++;
		ess.setSearchnum(iii);
		try {
			FileOutputStream fo = new FileOutputStream(file);
			BufferedOutputStream bo = new BufferedOutputStream(fo);
			ObjectOutputStream oos = new ObjectOutputStream(bo);
			oos.writeObject(ess);
			oos.close();
			bo.close();
			fo.close();
		} catch (FileNotFoundException ee) {
		} catch (IOException ee) {
		}
		bdlg.setVisible(true);
	}
}

class FreeBoard_Sub1 implements Serializable {
	String name;
	String title;
	String password;
	String memo;
	int number;
	String date;
	int searchnum;

	public FreeBoard_Sub1(String a, String b, String c, String d) {
		name = a;
		title = b;
		password = c;
		memo = d;
		// �ѹ��� üũ�Ѵ�..����...
		Calendar ca = Calendar.getInstance();
		int year = (int) ca.get(Calendar.YEAR);
		int month = (int) ca.get(Calendar.MONTH) + 1;
		int day = (int) ca.get(Calendar.DAY_OF_MONTH);
		date = year + "/" + month + "/" + day;
		searchnum = 0;
	}

	public String getName() {
		return name;
	}

	public void setTitle(String a) {
		title = a;
	}

	public String getTitle() {
		return title;
	}

	public void setPassword(String a) {
		password = a;
	}

	public String getPassword() {
		return password;
	}

	public void setMemo(String a) {
		memo = a;
	}

	public String getMemo() {
		return memo;
	}

	public void setDate(String a) {
		date = a;
	}

	public String getDate() {
		return date;
	}

	public void setSearchnum(int a) {
		searchnum = a;
	}

	public int getSearchnum() {
		return searchnum;
	}

	public void setNumber(int a) {
		number = a;
	}

	public int getNumber() {
		return number;
	}

}