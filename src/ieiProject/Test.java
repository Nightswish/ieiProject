package ieiProject;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.sql.*;
import java.util.Vector;
import java.util.regex.*;

import javax.swing.*;
import javax.swing.border.*;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

//소스수정 170511      

public class Test {
	public static void main(String[] ar) {
		TotalTicket_sub ex = new TotalTicket_sub();
	}
}

class TotalTicket_sub extends JFrame implements ActionListener, MouseListener, KeyListener, FocusListener {
	private Container con;
	// 메뉴화면
	private BorderLayout bl = new BorderLayout(5, 5);
	private JButton Buyer_bt = new JButton("구매자끼리");
	private JButton joinbt = new JButton("회원가입");
	private JButton loginbt = new JButton("로그인");
	private JButton mypagebt = new JButton("마이페이지");
	private JPanel MainP = new JPanel(new CardLayout());
	JPanel sp1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel sp2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

	private JLabel lb = new JLabel("");
	private JButton logoutbt = new JButton("로그아웃");

	// 검색창
	Choice ch1 = new Choice();
	JPanel tpmain = new JPanel(new BorderLayout(3, 3));
	JPanel search = new JPanel(new FlowLayout(FlowLayout.LEFT));

	Choice ch11 = new Choice();
	JPanel tpmain1 = new JPanel(new BorderLayout(3, 3));
	JPanel search1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
	private TextField searchtf = new TextField("제목 또는 날짜 입력",80);
	private JButton searchbt = new JButton("검색");
	private TextField searchtf1 = new TextField("제목 또는 날짜 입력",80);
	private JButton searchbt1 = new JButton("검색");
	
	// 검색 결과
	JPanel srchrslt = new JPanel(new BorderLayout(3, 3));
	JPanel srchresult = new JPanel(new GridLayout(2,4,3,3));

	// 인기순, 날짜순
	JPanel tklistpn = new JPanel(new GridLayout(2, 1, 5, 5));
	JPanel tlingi = new JPanel(new BorderLayout(3, 3));
	JPanel tldate = new JPanel(new BorderLayout(3, 3));
	JLabel lbingi = new JLabel("이달의 Best Ticket!");
	JLabel lbdate = new JLabel("날짜순");
	JList ltingi = new JList();
	JList ltdate = new JList();

	// 티켓화면
	JPanel tp = new JPanel(new GridLayout(2, 4, 3, 3));
	JScrollPane scroller = new JScrollPane(tp);

	JPanel mp = new JPanel(new BorderLayout(3, 3));

	ImageIcon home = new ImageIcon("..\\ieiProject\\image\\home2.jpg");
	ImageIcon image1 = new ImageIcon("..\\ieiProject\\image\\특별시민.jpg");
	ImageIcon image2 = new ImageIcon("..\\ieiProject\\image\\아빠는딸.jpg");
	ImageIcon image3 = new ImageIcon("..\\ieiProject\\image\\분노의질주.jpg");
	ImageIcon image4 = new ImageIcon("..\\ieiProject\\image\\미녀와야수.jpg");
	ImageIcon image5 = new ImageIcon("..\\ieiProject\\image\\스머프.jpg");
	ImageIcon image6 = new ImageIcon("..\\ieiProject\\image\\스톰 인사이드.jpg");
	ImageIcon image7 = new ImageIcon("..\\ieiProject\\image\\악마는프라다.jpg");
	ImageIcon image8 = new ImageIcon("..\\ieiProject\\image\\8마일.jpg");
	ImageIcon image9 = new ImageIcon("..\\ieiProject\\image\\인카네이트.jpg");

	private JLabel homebt = new JLabel(home);
	// 원본 라벨
	JLabel mv1 = new JLabel("특별시민  2017-04-23", image1, JLabel.CENTER);
	JLabel mv2 = new JLabel("아빠는딸  2017-04-23", image2, JLabel.CENTER);
	JLabel mv3 = new JLabel("분노의질주  2017-04-24", image3, JLabel.CENTER);
	JLabel mv4 = new JLabel("미녀와야수  2017-04-24", image4, JLabel.CENTER);
	JLabel mv5 = new JLabel("스머프  2017-04-25", image5, JLabel.CENTER);
	JLabel mv6 = new JLabel("스톰 인사이드  2017-04-25", image6, JLabel.CENTER);
	JLabel mv7 = new JLabel("악마는 프라다를 입는다  2017-04-26", image7, JLabel.CENTER);
	JLabel mv8 = new JLabel("8마일  2017-04-26", image8, JLabel.CENTER);
	//copy 라벨 
	JLabel mv1c = new JLabel("특별시민  2017-04-23", image1, JLabel.CENTER);
	JLabel mv2c = new JLabel("아빠는딸  2017-04-23", image2, JLabel.CENTER);
	JLabel mv3c = new JLabel("분노의질주  2017-04-24", image3, JLabel.CENTER);
	JLabel mv4c = new JLabel("미녀와야수  2017-04-24", image4, JLabel.CENTER);
	JLabel mv5c = new JLabel("스머프  2017-04-25", image5, JLabel.CENTER);
	JLabel mv6c = new JLabel("스톰 인사이드  2017-04-25", image6, JLabel.CENTER);
	JLabel mv7c = new JLabel("악마는 프라다를 입는다  2017-04-26", image7, JLabel.CENTER);
	JLabel mv8c = new JLabel("8마일  2017-04-26", image8, JLabel.CENTER);
	



	// 회원가입 다이얼로그
	private Container joincon;
	private JDialog joindlg = new JDialog(this, "회원가입", true);
	private JLabel joinidlb = new JLabel("아이디 : ", JLabel.RIGHT);
	private JLabel joinpwlb = new JLabel("비밀번호 : ", JLabel.RIGHT);
	private JLabel joinpwoklb = new JLabel("비밀번호 확인 : ", JLabel.RIGHT);
	private JLabel jointellb = new JLabel("전화번호 : ", JLabel.RIGHT);
	private JLabel joinniklb = new JLabel("닉네임 : ", JLabel.RIGHT);
	private JLabel joinemaillb = new JLabel("이메일 : ", JLabel.RIGHT);

	private JTextField joinidtf = new JTextField(10);
	private JPasswordField joinpwtf = new JPasswordField(10);
	private JPasswordField joinpwoktf = new JPasswordField(10);
	private JTextField jointeltf = new JTextField(10);
	private JTextField joinniktf = new JTextField(10);
	private JTextField joinemailtf = new JTextField(10);

	private JButton joinok = new JButton("확인");
	private JButton joinx = new JButton("취소");

	// 회원가입 완료 다이얼로그
	private Container joinokcon;
	private JDialog joinokdlg = new JDialog(joindlg, "가입완료", true);

	private JLabel joinokidlb = new JLabel("아이디 : ", JLabel.RIGHT);
	private JLabel joinoktellb = new JLabel("전화번호 : ", JLabel.RIGHT);
	private JLabel joinokniklb = new JLabel("닉네임 : ", JLabel.RIGHT);
	private JLabel joinokemaillb = new JLabel("이메일 : ", JLabel.RIGHT);

	private JLabel joinokidtf = new JLabel("", JLabel.LEFT);
	private JLabel joinokteltf = new JLabel("", JLabel.LEFT);
	private JLabel joinokniktf = new JLabel("", JLabel.LEFT);
	private JLabel joinokemailtf = new JLabel("", JLabel.LEFT);

	private JButton joinokbt = new JButton("완료");

	// 회원가입 취소 다이얼로그
	private Container joinxcon;
	private JDialog joinxdlg = new JDialog(joindlg, "비밀번호 오류", true);

	private JLabel joinxlb = new JLabel("비밀번호를 똑같이 입력하세요");
	private JButton joinxbt = new JButton("확인");

	// 로그인 다이얼로그
	private Container logincon;
	private JDialog logindlg = new JDialog(this, "로그인", true);
	private JLabel loginidlb = new JLabel("아이디 : ", JLabel.RIGHT);
	private JLabel loginpwlb = new JLabel("비밀번호 : ", JLabel.RIGHT);

	private JTextField loginidtf = new JTextField(10);
	private JPasswordField loginpwtf = new JPasswordField(10);

	private JButton loginok = new JButton("확인");
	private JButton loginx = new JButton("취소");

	private Vector vc = new Vector();

	// 로그인 완료 다이얼로그
	private Container loginokcon;
	private JDialog loginokdlg = new JDialog(logindlg, "로그인 완료", true);

	private JLabel loginokdlglb1 = new JLabel("");
	private JLabel loginokdlglb2 = new JLabel("님 환영합니다.");
	private JButton loginokdlgbt = new JButton("확인");

	// 로그인 실패 다이얼로그
	private Container loginxcon;
	private JDialog loginxdlg = new JDialog(logindlg, "로그인 실패", true);
	private JLabel loginxdlglb = new JLabel("아이디나 비밀번호가 틀립니다!!!!");
	private JButton loginxdlgbt = new JButton("이전화면");

	// 구매자 끼리
	private JPanel BuyerP = new JPanel(new BorderLayout(5, 5));

	private Label Buyerlb = new Label("구매자끼리 티켓매매");
	private JPanel buyerjp = new JPanel(new BorderLayout(3, 3));
	private JPanel tbuy = new JPanel();
	private JScrollPane jstbuy = new JScrollPane(tbuy);
	private JPanel buyer_Top_btp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private JPanel buyer_Top_btp1 = new JPanel(new FlowLayout());
	private JPanel buyer_Top_btp2 = new JPanel(new FlowLayout());
	private JButton tupbt = new JButton("표 올리기");
	private JButton tbuybt = new JButton("구 매");

	private JPanel t1in = new JPanel(new BorderLayout(3, 3));
	private JPanel t1show = new JPanel(new BorderLayout(3, 3));
	private JPanel t1infop = new JPanel(new GridLayout(2, 1));
	private Checkbox buycb = new Checkbox();
	private Label t1name = new Label("특별시민", Label.CENTER);
	private Label t1date = new Label("2017-04-23", Label.CENTER);
	private JPanel t1 = new JPanel();
	private JPanel t3in = new JPanel(new GridLayout(1, 1));
	private JPanel t3ini = new JPanel(new BorderLayout(3, 3));
	private JPanel t3 = new JPanel();
	private JLabel t3lb = new JLabel(new ImageIcon("..\\ieiProject\\image\\ticket.png"));

	// 예매 버튼 클릭 구성
	private Container rsvCon;
	private JDialog rsvDlg = new JDialog(this, "예매 하기", true);
	private JLabel lbName = new JLabel("공연 이름  ");
	private JLabel lbNameDB = new JLabel("윤한 투어 콘서트");
	private JLabel lbLoc = new JLabel("공연 장소  ");
	private JLabel lbLocDB = new JLabel("kucca 경복궁점");
	private JLabel lbPrice = new JLabel("가격  ");
	private JLabel lbPriceDB = new JLabel("50,000");
	private JLabel lbTicketNum = new JLabel("티켓 번호  ");
	private JLabel lbTicketNumDB = new JLabel("201704220000001");
	private ImageIcon imgPoster = new ImageIcon("/Users/youmeelee/Desktop/poster.jpg");
	private Image originImg = imgPoster.getImage();
	private Image changedImg = originImg.getScaledInstance(150, 200, Image.SCALE_SMOOTH);
	private ImageIcon poster = new ImageIcon(changedImg);
	private JLabel imgPosterLb = new JLabel(poster);
	private JButton btnSeatSelect = new JButton("좌석 선택");
	private JButton btnCancle = new JButton("취소");
	private JLabel lbSelectPerson = new JLabel("인원수 선택");

	String cntPerson[] = { "1", "2", "3", "4", "5" };
	private JComboBox cbCount = new JComboBox(cntPerson);

	private JLabel lbDay = new JLabel("공연 날짜 ");
	String day[] = { "2017년 5월 25일 17:30", "2017년 5월 25일 10:30" };
	private JComboBox cbDay = new JComboBox(day);

	// 좌석 선택 버튼 클릭 구성
	private Container sltSeatCon;
	private JDialog sltSeatDlg = new JDialog(this, "좌석 선택", true);
	private JLabel lbNowSltSeat = new JLabel("현재 좌석 선택 수");
	private JLabel lbAllSeat = new JLabel();
	private JLabel lbSlash = new JLabel("/");
	private JLabel lbSltSeat = new JLabel("0");
	private JButton btnFinSeat = new JButton("선택 완료");
	private JButton btnReselect = new JButton("다시 선택");

	// 좌석 버튼
	private JButton btnSelected[][] = new JButton[6][16];
	private JButton btnNotSelected[][] = new JButton[6][16];
	private JButton btnNotAble[][] = new JButton[6][16];

	// 결제창
	private Container payCon;
	private JDialog payDlg = new JDialog(this, "결제 하기", true);
	private JLabel lbPay = new JLabel("결제 금액");
	private JTextField txToPay = new JTextField(7);
	private JButton btnPayDlgPay = new JButton("결제");
	private JButton btnPayDlgCancle = new JButton("취소");

	// 마이페이지
	private GridBagLayout gridb = new GridBagLayout();
	private GridBagConstraints constraint = new GridBagConstraints();
	private JPanel mypagep = new JPanel(new FlowLayout());
	protected JTabbedPane tPane = new JTabbedPane();
	protected Panel myP = new Panel();
	protected Panel edit = new Panel(new BorderLayout());
	protected Panel pointp = new Panel(new BorderLayout());
	protected Panel show = new Panel(new BorderLayout());

	/////////////////////////////// 마이페이지창
	private Panel p4 = new Panel(new GridLayout(5, 1));
	private Panel p5 = new Panel(new GridLayout(5, 1));
	private Panel p4_1 = new Panel(new GridLayout(1, 2));
	private Label id1 = new Label("아이디 ");
	private Label id2 = new Label();
	private Panel p4_2 = new Panel(new GridLayout(1, 2));
	private Label phone1 = new Label("전화번호 ");
	private Label phone2 = new Label();
	private Panel p4_3 = new Panel(new GridLayout(1, 2));
	private Label nname1 = new Label("닉네임 ");
	private Label nname2 = new Label();
	private Panel p4_4 = new Panel(new GridLayout(1, 2));
	private Label email1 = new Label("이메일 ");
	private Label email2 = new Label();
	private Panel p4_5 = new Panel(new GridLayout(1, 2));
	private Label level1 = new Label("등급 ");
	private Label level2 = new Label();

	//////////////////////////////// 수정창
	private Panel p22_2 = new Panel();
	private Panel p22 = new Panel(new GridLayout(5, 1));
	private Panel p2_1 = new Panel(new GridLayout(1, 2));
	private Label eID = new Label("아이디 ");
	private Label eID1 = new Label();
	private Panel p2_2 = new Panel(new GridLayout(1, 2));
	private Label ephone = new Label("전화번호 ");
	private TextField tphone = new TextField(10);
	private Panel p2_3 = new Panel(new GridLayout(1, 2));
	private Label ename = new Label("닉네임 ");
	private TextField tname = new TextField(10);
	private Panel p2_4 = new Panel(new GridLayout(1, 2));
	private Label eemail = new Label("이메일 ");
	private TextField tmail = new TextField(10);
	private Panel p2_5 = new Panel(new GridLayout(1, 2));
	private Label elevel = new Label("등급 ");
	private Label elevel1 = new Label();
	private Panel p2_6_2 = new Panel(new FlowLayout());
	private Panel p2_6 = new Panel(new GridLayout(1, 2));

	private Button check = new Button("확인");
	private Button cancel = new Button("취소");

	/////////////////////////////////////// 수정 완료 다이얼로그
	private Container updateokcon;
	private JDialog updateokdlg = new JDialog(this, "수정완료", true);

	private JLabel updateokidlb = new JLabel("아이디 : ", JLabel.RIGHT);
	private JLabel updateoktellb = new JLabel("전화번호 : ", JLabel.RIGHT);
	private JLabel updateokniklb = new JLabel("닉네임 : ", JLabel.RIGHT);
	private JLabel updateokemaillb = new JLabel("이메일 : ", JLabel.RIGHT);

	private JLabel updateokidtf = new JLabel("", JLabel.LEFT);
	private JLabel updateokteltf = new JLabel("", JLabel.LEFT);
	private JLabel updateokniktf = new JLabel("", JLabel.LEFT);
	private JLabel updateokemailtf = new JLabel("", JLabel.LEFT);
	private JButton updateokbt = new JButton("확인");

	//////////////////////////////////////// 포인트창
	private Panel pointP = new Panel(new FlowLayout());

	private Label point = new Label("잔여 포인트: ");
	private Label point1 = new Label("0");

	private Panel pointP1 = new Panel();
	private Label point2 = new Label("1000원 충전: 1100p 지급");
	private Label point3 = new Label("5000원 충전: 5600p 지급");
	private Label point4 = new Label("10000원 충전: 12000p 지급");
	private Label point5 = new Label("50000원 충전: 53000p 지급");

	private Panel chargeP = new Panel(new FlowLayout());
	private Button charge = new Button("포인트 충전");

	/////////////////////////////////////////////// 포인트 충전창
	private Container chargecon;
	private JDialog chargedlg = new JDialog(this, "포인트 충전", true);

	private Panel chargep = new Panel(new FlowLayout());
	private Label chargelb = new Label("충전: ");
	private TextField chargetf = new TextField(10);
	private Button chargebt = new Button("충전");
	////////////////////////////////////// 공연내역창

	// private BoxLayout box = new BoxLayout();

	// private JScrollPane showfpsc = new JScrollPane(show);

	private JPanel showtkp = new JPanel();
	private JPanel t1in1 = new JPanel(new GridLayout(1, 1));//
	private JPanel t1ini1 = new JPanel(new BorderLayout(3, 3));//
	private JPanel t11 = new JPanel();
	private Checkbox cancelcb = new Checkbox();
	private JLabel t1lb1 = new JLabel(new ImageIcon("C:\\Users\\Public\\Pictures\\Sample Pictures\\desert.jpg"));
	private JButton tbuybt1 = new JButton("상세보기1");

	private JPanel t2in1 = new JPanel(new GridLayout(1, 1));//
	private JPanel t2ini1 = new JPanel(new BorderLayout(3, 3));//
	private JPanel t21 = new JPanel();
	private Checkbox cancelcb2 = new Checkbox();
	private JLabel t2lb1 = new JLabel(new ImageIcon("C:\\Users\\Public\\Pictures\\Sample Pictures\\desert.jpg"));
	private JButton tbuybt21 = new JButton("상세보기2");

	private JPanel t3in1 = new JPanel(new GridLayout(1, 1));//
	private JPanel t3ini1 = new JPanel(new BorderLayout(3, 3));//
	private JPanel t31 = new JPanel();
	private Checkbox cancelcb3 = new Checkbox();
	private JLabel t3lb1 = new JLabel(new ImageIcon("C:\\Users\\Public\\Pictures\\Sample Pictures\\desert.jpg"));
	private JButton tbuybt31 = new JButton("상세보기3");

	private Button canceltk = new Button("예매취소");
	/////////////////// 예매취소 다이아로그
	private JDialog canceldlg = new JDialog(this, "예매취소", true);
	private Panel realcancelp = new Panel(new BorderLayout());
	private Label realcancel = new Label("예약을 취소하시겠습니까?");
	private Panel canceldlgp = new Panel(new FlowLayout());
	private Button cancelokbt = new Button("확인");
	private Button cancelnobt = new Button("취소");

	// DB 연결
	Connection conn;
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String id = "scott";
	String pass = "tiger";

	// DB 회원가입
	public void joinMember() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
			String query = "insert into customer(ID,PW,TEL,NIK,EMAIL) values(?,?,?,?,?)";
			//member에 point추가해서 쿼리문 바꿨습니다(2017.5.10)
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
			System.err.println("회원 가입 실패!!!");
		}
	}

	// DB 로그인
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
				point1.setText(rs.getString("POINT"));// 포인트 추가(2017.5.10)
				lb.setText(rs.getString("id") + " 님 ");
				loginokdlg.setVisible(true);

				rs.close();
				pstmt.close();
			}
		} catch (ClassNotFoundException eee) {

		} catch (SQLException e) {
			System.err.println("로그인 실패!!!");
		}
	}

	// DB 수정
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
			System.err.println("수정 실패!!!");
		}

	}


	//////////////////////////////// 포인트 충전DB(2017.5.10)
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
			String query = "update member set POINT=? where ID=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, point1.getText().trim());
			pstmt.setString(2, id2.getText().trim());
			pstmt.executeUpdate();
			pstmt.close();

			System.out.println("충전성공11111");

		} catch (ClassNotFoundException eee) {
			System.err.println("충전 실패!!!11111");
		} catch (SQLException e) {
			System.err.println("충전 실패!!!2222222");
		}

	}


	public TotalTicket_sub() {
		super("메인");
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
		mv1.addMouseListener(this);
		mv2.addMouseListener(this);
		mv3.addMouseListener(this);
		mv4.addMouseListener(this);
		mv5.addMouseListener(this);
		mv6.addMouseListener(this);
		mv7.addMouseListener(this);
		mv8.addMouseListener(this);
		mv1c.addMouseListener(this);
		mv2c.addMouseListener(this);
		mv3c.addMouseListener(this);
		mv4c.addMouseListener(this);
		mv5c.addMouseListener(this);
		mv6c.addMouseListener(this);
		mv7c.addMouseListener(this);
		mv8c.addMouseListener(this);
		btnCancle.addActionListener(this);
		btnSeatSelect.addActionListener(this);
		btnFinSeat.addActionListener(this);
		btnPayDlgCancle.addActionListener(this);
		cbCount.addActionListener(this);
		btnReselect.addActionListener(this);
		searchtf.addFocusListener(this);
		searchtf.addMouseListener(this);
		searchtf1.addFocusListener(this);
		searchtf1.addMouseListener(this);
		searchbt.addActionListener(this);
		searchbt1.addActionListener(this);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 16; j++) {
				btnNotSelected[i][j].addActionListener(this);
			}
		}

		check.addActionListener(this);
		cancel.addActionListener(this);
		updateokbt.addActionListener(this);

		// 공연내역 취소버튼
		canceltk.addActionListener(this);
		cancelokbt.addActionListener(this);
		cancelnobt.addActionListener(this);

		// 포인트(2017.5.10)
		charge.addActionListener(this);
		chargebt.addActionListener(this);

		// 좌석 다이얼로그 종료됐을 때 좌석 초기화시키기
		sltSeatDlg.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				seatClear();
			}
		});
	}

	public void seatClear() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 16; j++) {
				btnNotSelected[i][j].setEnabled(true);
				btnNotSelected[i][j].setBackground(null);
			}
		}
		cnt = 0;
		lbSltSeat.setText(Integer.toString(cnt));
	}

	private void init() {
		// 메인화면 구성 - 상단 메뉴바
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

		// 메인화면 구성 - 중앙 티켓화면 구성
		tpmain.setBorder(new BevelBorder(BevelBorder.RAISED));

		// 검색창(North)
		ch1.add("----------");
		ch1.add("티켓이름");
		ch1.add("공연날짜");

		search.add("West", ch1);
		search.add("Center", searchtf);
		search.add("East", searchbt);
		
		// 검색창(North)(Copy)
		ch11.add("----------");
		ch11.add("티켓이름");
		ch11.add("공연날짜");
				
		search1.add("West", ch11);
		search1.add("Center", searchtf1);
		search1.add("East", searchbt1);

		// 인기순, 날짜순(East)
		tlingi.add("North", lbingi);
		tlingi.add("Center", ltingi);
		tklistpn.add(tlingi);
		tldate.add("North", lbdate);
		tldate.add("Center", ltdate);
		tklistpn.add(tldate);

		// 중앙의 티켓화면 구성(Center)
		// tp.setBorder(new BevelBorder(BevelBorder.RAISED));

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
		con.add("Center", tpmain);

		
		//검색 결과창
		srchrslt.add("North", search1);
		srchrslt.add("Center", srchresult);
		
		MainP.add(tpmain);
		MainP.add(srchrslt);
		


		// 검색 결과창

		MainP.add(tpmain);
		MainP.add(srchresult);

		con.add("North", mp);

		con.add("Center", MainP);

		// 회원가입 다이얼로그 구성
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

		// 회원가입 완료 다이얼로그 구성
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

		// 회원가입 실패 다이얼로그 구성
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

		// 로그인 다이얼로그 구성
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

		// 로그인 완료 다이얼로그 구성
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

		// 로그인 실패 다이얼로그 구성
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


		// 검색창

		MainP.add(tpmain);
		MainP.add(srchresult);

		con.add("North", mp);
		con.add("Center", MainP);

		////////////////////// 구매자 끼리

		tbuy.setLayout(new BoxLayout(tbuy, BoxLayout.Y_AXIS));

		t3lb.setPreferredSize(new Dimension(300, 60));

		JPanel t1in = new JPanel(new BorderLayout(3, 3));
		JPanel t1show = new JPanel(new BorderLayout(3, 3));
		JPanel t1infop = new JPanel(new GridLayout(2, 1));
		Checkbox buycb = new Checkbox();
		Label t1name = new Label("특별시민", Label.CENTER);
		Label t1date = new Label("2017-04-23", Label.CENTER);
		JPanel t1 = new JPanel();
		JLabel t1lb = new JLabel(image1);
		JPanel t2in = new JPanel(new BorderLayout(3, 3));
		JPanel t2show = new JPanel(new BorderLayout(3, 3));
		JPanel t2infop = new JPanel(new GridLayout(2, 1));
		Checkbox buycb1 = new Checkbox();
		Label t2name = new Label("아빠는딸", Label.CENTER);
		Label t2date = new Label("2017-04-12", Label.CENTER);
		JPanel t2 = new JPanel();
		JLabel t2lb = new JLabel(image2);

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

		con.add("North", mp); // 메인
		con.add("Center", MainP);

		// 예매 컨테이너 구성
		rsvCon = rsvDlg.getContentPane();
		rsvDlg.setLayout(new BorderLayout());
		rsvDlg.setSize(600, 350);
		// rsvDlg.setResizable(false);

		// 좌석 선택 컨테이너 구성
		sltSeatCon = sltSeatDlg.getContentPane();
		sltSeatDlg.setLayout(new BorderLayout(50, 50));
		sltSeatDlg.setSize(880, 450);

		// 결제 컨테이너 구성
		payCon = payDlg.getContentPane();
		payDlg.setLayout(new BorderLayout(40, 40));
		payDlg.setSize(350, 150);

		// 위치 구성
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

		/* 예매 다이얼로그 */
		// 왼쪽 라벨 구성
		JPanel pl1 = new JPanel(new GridLayout(5, 1));
		pl1.add(lbTicketNum);
		pl1.add(lbName);
		pl1.add(lbLoc);
		pl1.add(lbPrice);
		pl3.add("West", pl1);

		// 오른쪽 라벨 구성
		JPanel pl2 = new JPanel(new GridLayout(5, 1));
		pl2.add(lbTicketNumDB);
		pl2.add(lbNameDB);
		pl2.add(lbLocDB);
		pl2.add(lbPriceDB);
		pl3.add("Center", pl2);
		pl3.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

		JPanel pl5 = new JPanel(new BorderLayout());

		// 위쪽 구성 (포스터 사진, 공연 정보)
		pl5.add("Center", pl3);
		pl5.add("West", imgPosterLb);
		// pl5.setBorder(new TitledBorder(new
		// SoftBevelBorder(SoftBevelBorder.RAISED), "공연 정보"));

		// 왼쪽은 달력, 오른쪽은 인원수, 좌석선택, 취소버튼 추가
		JPanel pl7 = new JPanel(new GridLayout(1, 2));

		// 인원수, 좌석 선택, 취소 버튼
		JPanel pl6 = new JPanel(new GridLayout(2, 1));

		// 인원수 선택
		JPanel pl8 = new JPanel(new FlowLayout());
		pl8.add(lbSelectPerson);
		pl8.add(cbCount);

		// 좌석 선택, 취소 버튼
		JPanel pl4 = new JPanel(new FlowLayout());
		pl4.add(btnSeatSelect);
		pl4.add(btnCancle);

		// 콤보박스와 좌석 선택, 취소 버튼 추가
		pl6.add(pl8);
		pl6.add(pl4);

		// 날짜 선택 콤보박스
		JPanel p13 = new JPanel(new FlowLayout());
		p13.add(lbDay);
		p13.add(cbDay);

		pl7.add(p13);
		pl7.add(pl6);

		/* 좌석 선택 다이얼로그 */
		// 보더의 아래쪽 (현재 좌석과 좌석 선택)
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

		// 좌석 그리드 만들기 imgSelected, imgNotSelected, imgNotAble
		JLabel lbScreen = new JLabel("무 대", JLabel.CENTER);
		lbScreen.setFont(new Font("Sherif", Font.PLAIN, 30));

		JPanel plAllSection = new JPanel(new GridLayout(1, 4, 7, 7));
		JPanel plSection1 = new JPanel(new GridLayout(6, 4));
		JPanel plSection2 = new JPanel(new GridLayout(6, 4));
		JPanel plSection3 = new JPanel(new GridLayout(6, 4));
		JPanel plSection4 = new JPanel(new GridLayout(6, 4));

		// 첫번째 섹션 그리기
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				switch (i) {
				case 0:
					btnNotSelected[i][j] = new JButton("A" + (j + 1));
					btnSelected[i][j] = new JButton("A" + (j + 1));
					btnNotAble[i][j] = new JButton("A" + (j + 1));
					break;
				case 1:
					btnNotSelected[i][j] = new JButton("B" + (j + 1));
					btnSelected[i][j] = new JButton("B" + (j + 1));
					btnNotAble[i][j] = new JButton("B" + (j + 1));
					break;
				case 2:
					btnNotSelected[i][j] = new JButton("C" + (j + 1));
					btnSelected[i][j] = new JButton("C" + (j + 1));
					btnNotAble[i][j] = new JButton("C" + (j + 1));
					break;
				case 3:
					btnNotSelected[i][j] = new JButton("D" + (j + 1));
					btnSelected[i][j] = new JButton("D" + (j + 1));
					btnNotAble[i][j] = new JButton("D" + (j + 1));
					break;
				case 4:
					btnNotSelected[i][j] = new JButton("E" + (j + 1));
					btnSelected[i][j] = new JButton("E" + (j + 1));
					btnNotAble[i][j] = new JButton("E" + (j + 1));
					break;
				case 5:
					btnNotSelected[i][j] = new JButton("F" + (j + 1));
					btnSelected[i][j] = new JButton("F" + (j + 1));
					btnNotAble[i][j] = new JButton("F" + (j + 1));
					break;
				}
				btnNotSelected[i][j].setSize(50, 50);
				plSection1.add(btnNotSelected[i][j]);
			}
		}

		// 두번째 섹션 그리기
		for (int i = 0; i < 6; i++) {
			for (int j = 4; j < 8; j++) {
				switch (i) {
				case 0:
					btnNotSelected[i][j] = new JButton("A" + (j + 1));
					btnSelected[i][j] = new JButton("A" + (j + 1));
					btnNotAble[i][j] = new JButton("A" + (j + 1));
					break;
				case 1:
					btnNotSelected[i][j] = new JButton("B" + (j + 1));
					btnSelected[i][j] = new JButton("B" + (j + 1));
					btnNotAble[i][j] = new JButton("B" + (j + 1));
					break;
				case 2:
					btnNotSelected[i][j] = new JButton("C" + (j + 1));
					btnSelected[i][j] = new JButton("C" + (j + 1));
					btnNotAble[i][j] = new JButton("C" + (j + 1));
					break;
				case 3:
					btnNotSelected[i][j] = new JButton("D" + (j + 1));
					btnSelected[i][j] = new JButton("D" + (j + 1));
					btnNotAble[i][j] = new JButton("D" + (j + 1));
					break;
				case 4:
					btnNotSelected[i][j] = new JButton("E" + (j + 1));
					btnSelected[i][j] = new JButton("E" + (j + 1));
					btnNotAble[i][j] = new JButton("E" + (j + 1));
					break;
				case 5:
					btnNotSelected[i][j] = new JButton("F" + (j + 1));
					btnSelected[i][j] = new JButton("F" + (j + 1));
					btnNotAble[i][j] = new JButton("F" + (j + 1));
					break;
				}
				btnNotSelected[i][j].setSize(50, 50);
				plSection2.add(btnNotSelected[i][j]);
			}
		}

		// 3번째 섹션 그리기
		for (int i = 0; i < 6; i++) {
			for (int j = 8; j < 12; j++) {
				switch (i) {
				case 0:
					btnNotSelected[i][j] = new JButton("A" + (j + 1));
					btnSelected[i][j] = new JButton("A" + (j + 1));
					btnNotAble[i][j] = new JButton("A" + (j + 1));
					break;
				case 1:
					btnNotSelected[i][j] = new JButton("B" + (j + 1));
					btnSelected[i][j] = new JButton("B" + (j + 1));
					btnNotAble[i][j] = new JButton("B" + (j + 1));
					break;
				case 2:
					btnNotSelected[i][j] = new JButton("C" + (j + 1));
					btnSelected[i][j] = new JButton("C" + (j + 1));
					btnNotAble[i][j] = new JButton("C" + (j + 1));
					break;
				case 3:
					btnNotSelected[i][j] = new JButton("D" + (j + 1));
					btnSelected[i][j] = new JButton("D" + (j + 1));
					btnNotAble[i][j] = new JButton("D" + (j + 1));
					break;
				case 4:
					btnNotSelected[i][j] = new JButton("E" + (j + 1));
					btnSelected[i][j] = new JButton("E" + (j + 1));
					btnNotAble[i][j] = new JButton("E" + (j + 1));
					break;
				case 5:
					btnNotSelected[i][j] = new JButton("F" + (j + 1));
					btnSelected[i][j] = new JButton("F" + (j + 1));
					btnNotAble[i][j] = new JButton("F" + (j + 1));
					break;
				}
				btnNotSelected[i][j].setSize(50, 50);
				plSection3.add(btnNotSelected[i][j]);
			}
		}

		// 4번째 섹션 그리기
		for (int i = 0; i < 6; i++) {
			for (int j = 12; j < 16; j++) {
				switch (i) {
				case 0:
					btnNotSelected[i][j] = new JButton("A" + (j + 1));
					btnSelected[i][j] = new JButton("A" + (j + 1));
					btnNotAble[i][j] = new JButton("A" + (j + 1));
					break;
				case 1:
					btnNotSelected[i][j] = new JButton("B" + (j + 1));
					btnSelected[i][j] = new JButton("B" + (j + 1));
					btnNotAble[i][j] = new JButton("B" + (j + 1));
					break;
				case 2:
					btnNotSelected[i][j] = new JButton("C" + (j + 1));
					btnSelected[i][j] = new JButton("C" + (j + 1));
					btnNotAble[i][j] = new JButton("C" + (j + 1));
					break;
				case 3:
					btnNotSelected[i][j] = new JButton("D" + (j + 1));
					btnSelected[i][j] = new JButton("D" + (j + 1));
					btnNotAble[i][j] = new JButton("D" + (j + 1));
					break;
				case 4:
					btnNotSelected[i][j] = new JButton("E" + (j + 1));
					btnSelected[i][j] = new JButton("E" + (j + 1));
					btnNotAble[i][j] = new JButton("E" + (j + 1));
					break;
				case 5:
					btnNotSelected[i][j] = new JButton("F" + (j + 1));
					btnSelected[i][j] = new JButton("F" + (j + 1));
					btnNotAble[i][j] = new JButton("F" + (j + 1));
					break;
				}
				btnNotSelected[i][j].setSize(50, 50);
				plSection4.add(btnNotSelected[i][j]);
			}
		}

		plAllSection.add(plSection1);
		plAllSection.add(plSection2);
		plAllSection.add(plSection3);
		plAllSection.add(plSection4);

		/* 결제 다이얼로그 구성 */
		JPanel pl12 = new JPanel(new FlowLayout());
		pl12.add(lbPay);
		pl12.add(txToPay);

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

		// 마이페이지
		tPane.setPreferredSize(new Dimension(1000, 550));
		tPane.add("마이페이지", myP);
		tPane.add("수정", edit);
		tPane.add("포인트", pointp);
		tPane.add("공연내역", show);
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

		////////// 포인트충전(2017.5.10)
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

		///////////////////////////////// 공연내역

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
		//////////////////////////////////// 예매취소다이어로그

		canceldlgp.add(cancelokbt);
		canceldlgp.add(cancelnobt);

		realcancelp.add("North", realcancel);
		realcancelp.add("Center", canceldlgp);
		canceldlg.add(realcancelp);

		///////////////////////////////////////////////////

		myP.add("Center", p5);
		mypagep.add(tPane);
		MainP.add(mypagep);

		// 수정 완료 다이얼로그
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
	}

	int cnt = 0;

	@Override
	public void actionPerformed(ActionEvent e) {

		// 콤보 박스 예매 인원수 선택 출력
		String strPersonCnt = cbCount.getSelectedItem().toString();
		lbAllSeat.setText(strPersonCnt);

		if (e.getSource() == Buyer_bt) { // 구매자 끼리
			tpmain.setVisible(false);
			srchrslt.setVisible(false);
			BuyerP.setVisible(true);
		} // 구매자 끼리

		if (e.getSource() == tbuybt) {
			if (buycb.getState() == true) {
				System.out.println("???");
				t1.setVisible(false);
			}
		}
		// 검색버튼
		else if (e.getSource() == searchbt) {
			srchresult.removeAll();
			String rslt = searchtf.getText().trim();
			String mvst1 = mv1.getText().trim();
			String mvst2 = mv2.getText().trim();
			String mvst3 = mv3.getText().trim();
			String mvst4 = mv4.getText().trim();
			String mvst5 = mv5.getText().trim();
			String mvst6 = mv6.getText().trim();
			String mvst7 = mv7.getText().trim();
			String mvst8 = mv8.getText().trim();
			try {

				if (mvst1.matches(".*" + rslt + ".*")) {
					mv1c.setVerticalTextPosition(SwingConstants.BOTTOM);
					mv1c.setHorizontalTextPosition(SwingConstants.CENTER);
					srchresult.add(mv1c);
				}
				if (mvst2.matches(".*" + rslt + ".*")) {
					mv2c.setVerticalTextPosition(SwingConstants.BOTTOM);
					mv2c.setHorizontalTextPosition(SwingConstants.CENTER);
					srchresult.add(mv2c);
				}
				if (mvst3.matches(".*" + rslt + ".*")) {
					mv3c.setVerticalTextPosition(SwingConstants.BOTTOM);
					mv3c.setHorizontalTextPosition(SwingConstants.CENTER);
					srchresult.add(mv3c);
				}
				if (mvst4.matches(".*" + rslt + ".*")) {
					mv4c.setVerticalTextPosition(SwingConstants.BOTTOM);
					mv4c.setHorizontalTextPosition(SwingConstants.CENTER);
					srchresult.add(mv4c);
				}
				if (mvst5.matches(".*" + rslt + ".*")) {
					mv5c.setVerticalTextPosition(SwingConstants.BOTTOM);
					mv5c.setHorizontalTextPosition(SwingConstants.CENTER);
					srchresult.add(mv5c);
				}
				if (mvst6.matches(".*" + rslt + ".*")) {
					mv6c.setVerticalTextPosition(SwingConstants.BOTTOM);
					mv6c.setHorizontalTextPosition(SwingConstants.CENTER);
					srchresult.add(mv6c);
				}
				if (mvst7.matches(".*" + rslt + ".*")) {
					mv7c.setVerticalTextPosition(SwingConstants.BOTTOM);
					mv7c.setHorizontalTextPosition(SwingConstants.CENTER);
					srchresult.add(mv7c);
				}
				if (mvst8.matches(".*" + rslt + ".*")) {
					mv8c.setVerticalTextPosition(SwingConstants.BOTTOM);
					mv8c.setHorizontalTextPosition(SwingConstants.CENTER);
					srchresult.add(mv8c);
				}

			} catch (PatternSyntaxException ee) {
				System.err.println(ee);
			}
			searchtf.setText("제목 또는 날짜 검색");
			tpmain.setVisible(false);
			srchrslt.setVisible(true);
		}

		//검색버튼(Copy)
		else if(e.getSource() == searchbt1){
			srchresult.removeAll();
			String rslt = searchtf1.getText().trim();
			String mvst1 = mv1.getText().trim();
			String mvst2 = mv2.getText().trim();
			String mvst3 = mv3.getText().trim();
			String mvst4 = mv4.getText().trim();
			String mvst5 = mv5.getText().trim();
			String mvst6 = mv6.getText().trim();
			String mvst7 = mv7.getText().trim();
			String mvst8 = mv8.getText().trim();
			try{

				if(mvst1.matches(".*"+rslt+".*")){
					mv1c.setVerticalTextPosition(SwingConstants.BOTTOM);
					mv1c.setHorizontalTextPosition(SwingConstants.CENTER);
					srchresult.add(mv1c);}
				if(mvst2.matches(".*"+rslt+".*")){
					mv2c.setVerticalTextPosition(SwingConstants.BOTTOM);
					mv2c.setHorizontalTextPosition(SwingConstants.CENTER);
					srchresult.add(mv2c);}
				if(mvst3.matches(".*"+rslt+".*")){
					mv3c.setVerticalTextPosition(SwingConstants.BOTTOM);
					mv3c.setHorizontalTextPosition(SwingConstants.CENTER);
					srchresult.add(mv3c);}
				if(mvst4.matches(".*"+rslt+".*")){
					mv4c.setVerticalTextPosition(SwingConstants.BOTTOM);
					mv4c.setHorizontalTextPosition(SwingConstants.CENTER);
					srchresult.add(mv4c);}
				if(mvst5.matches(".*"+rslt+".*")){
					mv5c.setVerticalTextPosition(SwingConstants.BOTTOM);
					mv5c.setHorizontalTextPosition(SwingConstants.CENTER);
					srchresult.add(mv5c);}
				if(mvst6.matches(".*"+rslt+".*")){
					mv6c.setVerticalTextPosition(SwingConstants.BOTTOM);
					mv6c.setHorizontalTextPosition(SwingConstants.CENTER);
					srchresult.add(mv6c);}
				if(mvst7.matches(".*"+rslt+".*")){
					mv7c.setVerticalTextPosition(SwingConstants.BOTTOM);
					mv7c.setHorizontalTextPosition(SwingConstants.CENTER);
					srchresult.add(mv7c);}
				if(mvst8.matches(".*"+rslt+".*")){
					mv8c.setVerticalTextPosition(SwingConstants.BOTTOM);
					mv8c.setHorizontalTextPosition(SwingConstants.CENTER);
					srchresult.add(mv8c);}
				}
			catch(PatternSyntaxException ee){
				System.err.println(ee);
			}
			searchtf1.setText("제목 또는 날짜 검색");
			tpmain.setVisible(false);
			srchrslt.setVisible(true);
		}		

		

		else if (e.getSource() == joinbt) { // 회원가입
			// tp.setVisible(false);
			joindlg.setVisible(true);
		} // 회원가입

		else if (e.getSource() == joinok) { // 회원가입 다이얼로그의 완료
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
				/*
				 * TotalTicket_sub1 sss = new TotalTicket_sub1(a1, b1, c1, d1,
				 * e1, f1); vc.addElement(sss); File file = new
				 * File("C:\\workspace\\data\\info.txt"); try{ FileOutputStream
				 * fo = new FileOutputStream(file); BufferedOutputStream bo =
				 * new BufferedOutputStream(fo); ObjectOutputStream oos = new
				 * ObjectOutputStream(bo); oos.writeObject(vc); // 반드시 직렬화되어 있어야
				 * 한다. oos.close(); bo.close(); fo.close(); }catch(Exception
				 * ee){ System.err.println("Error = " + ee.toString()); }
				 */
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

		} // 회원가입 다이얼로그의 완료

		else if (e.getSource() == joinokbt) { // 회원가입 다이얼로그의 완료 다이얼로그의 확인버튼
			joinokidtf.setText("");
			joinokteltf.setText("");
			joinokniktf.setText("");
			joinokemailtf.setText(" ");
			joinokemailtf.setText("");
			joinokdlg.setVisible(false);
		} // 회원가입 다이얼로그의 완료 다이얼로그의 확인버튼

		else if (e.getSource() == joinx) { // 회원가입 다이얼로그의 취소
			joinidtf.setText("");
			joinpwtf.setText("");
			joinpwoktf.setText("");
			jointeltf.setText("");
			joinniktf.setText("");
			joinemailtf.setText(" ");
			joinemailtf.setText("");
			joindlg.setVisible(false);
		} // 회원가입 다이얼로그의 취소

		else if (e.getSource() == loginbt) { // 로그인
			// tp.setVisible(false);
			logindlg.setVisible(true);
		} // 로그인

		else if (e.getSource() == loginok) { // 로그인 다이얼로그의 완료
			loginMember();
			/*
			 * File file = new File("C:\\workspace\\data\\info.txt"); try{
			 * FileInputStream fi = new FileInputStream(file);
			 * BufferedInputStream bi = new BufferedInputStream(fi);
			 * ObjectInputStream ois = new ObjectInputStream(bi); vc.clear(); vc
			 * = (Vector)ois.readObject(); ois.close(); bi.close(); fi.close();
			 * }catch(Exception ee){ ee.printStackTrace();; } for(int
			 * i=0;i<vc.size();i++){ TotalTicket_sub1 imsi =
			 * (TotalTicket_sub1)vc.elementAt(i);
			 * if(imsi.getId().equals(loginidtf.getText().trim()) &&
			 * imsi.getPw().equals(new String(loginpwtf.getPassword()))){
			 * loginokdlglb1.setText(imsi.getId()); id2.setText(imsi.getId());
			 * nname2.setText(imsi.getNik()); phone2.setText(imsi.getTel());
			 * email2.setText(imsi.getEmail()); eID1.setText(imsi.getId());
			 * tphone.setText(imsi.getTel()); tmail.setText(imsi.getEmail());
			 * tname.setText(imsi.getNik()); loginokdlg.setVisible(true); }
			 * else{ loginxdlg.setVisible(true); } }
			 */

		} // 로그인 다이얼로그의 완료

		else if (e.getSource() == loginx) { // 로그인 다이얼로그의 취소
			loginidtf.setText("");
			loginpwtf.setText(" ");
			loginpwtf.setText("");
			logindlg.setVisible(false);

		} // 로그인 다이얼로그의 취소

		else if (e.getSource() == joinxbt) { // 회원가입 실패다이얼로그 확인버튼
			joinxdlg.setVisible(false);
			joinpwtf.setText("");
			joinpwoktf.setText(" ");
			joinpwoktf.setText("");
		} // 회원가입 실패다이얼로그 확인버튼

		else if (e.getSource() == loginokdlgbt) { // 로그인 성공 다이얼로그 확인버튼
			loginidtf.setText("");
			loginpwtf.setText(" ");
			loginpwtf.setText("");
			loginokdlg.setVisible(false);
			logindlg.setVisible(false);

			loginbt.setVisible(false);
			joinbt.setVisible(false);
			lb.setVisible(true);

			/*
			 * File file = new File("C:\\workspace\\data\\info.txt"); try{
			 * FileInputStream fi = new FileInputStream(file);
			 * BufferedInputStream bi = new BufferedInputStream(fi);
			 * ObjectInputStream ois = new ObjectInputStream(bi); vc.clear(); vc
			 * = (Vector)ois.readObject(); ois.close(); bi.close(); fi.close();
			 * }catch(Exception ee){ ee.printStackTrace();; } for(int
			 * i=0;i<vc.size();i++){ TotalTicket_sub1 imsi =
			 * (TotalTicket_sub1)vc.elementAt(i); lb.setText(imsi.getId()); }
			 */

			logoutbt.setVisible(true);
			mypagebt.setVisible(true);
		} // 로그인 성공 다이얼로그 확인버튼

		else if (e.getSource() == loginxdlgbt) { // 로그인 실패 다이얼로그 확인버튼
			loginidtf.setText("");
			loginpwtf.setText(" ");
			loginpwtf.setText("");
			loginxdlg.setVisible(false);
		} // 로그인 실패 다이얼로그 확인버튼

		else if (e.getSource() == logoutbt) { // 로그아웃 버튼
			lb.setVisible(false);
			lb.setText(" ");
			lb.setText("");
			tpmain.setVisible(true);
			logoutbt.setVisible(false);
			mypagebt.setVisible(false);
			loginbt.setVisible(true);
			joinbt.setVisible(true);
			mypagep.setVisible(false);
		} // 로그아웃 버튼

		else if (e.getSource() == btnCancle) { // 취소 버튼
			rsvDlg.setVisible(false);
		} else if (e.getSource() == btnSeatSelect) { // 좌석 선택 부분 눌렀을 때
			sltSeatDlg.setVisible(true);
		} else if (e.getSource() == btnFinSeat) { // 좌석 선택 완료
			payDlg.setVisible(true);
		} else if (e.getSource() == btnPayDlgCancle) {
			payDlg.setVisible(false);
		} else if (e.getSource() == btnReselect) { // 좌석 다시 선택
			seatClear();
		} else if (e.getSource() == mypagebt) { // 마이페이지
			tpmain.setVisible(false);
			srchrslt.setVisible(false);
			BuyerP.setVisible(false);
			mypagep.setVisible(true);
		} // 마이페이지
		else if (e.getSource() == check) { // 마이페이지 수정 확인버튼
			updateMember();
			updateokdlg.setVisible(true);

		} // 마이페이지 수정 확인버튼
		else if (e.getSource() == cancel) { // 마이페이지 수정 취소버튼
			tphone.setText(phone2.getText().trim());
			tname.setText(nname2.getText().trim());
			tmail.setText(email2.getText().trim());

		} // 마이페이지 수정 취소버튼

		else if (e.getSource() == updateokbt) { // 수정 완료 다이얼로그 확인버튼
			updateokdlg.setVisible(false);

		} // 수정 완료 다이얼로그 확인버튼



		///////////////////////////////////////////////////////// 포인트

		else if (e.getSource() == charge) {

			chargedlg.setVisible(true);
		} // 포인트 충전버튼
		else if (e.getSource() == chargebt) {
			chargepoint();
			chargedlg.setVisible(false);
		}
		///////////////////////////////////////////////////// 공연내역 예매취소

		else if (e.getSource() == canceltk) {
			if (cancelcb.getState() == true) {
				canceldlg.setSize(200, 200);
				canceldlg.setVisible(true);
			}
		} else if (e.getSource() == cancelokbt) {
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
		} else if (e.getSource() == cancelnobt) {
			canceldlg.setVisible(false);
		}

		// 좌석 콤보 박스 수만큼 선택
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 16; j++) {
				if (e.getSource() == btnNotSelected[i][j]) {
					btnNotSelected[i][j].setBackground(Color.RED);
					btnNotSelected[i][j].setEnabled(false);
					cnt++;
					lbSltSeat.setText(Integer.toString(cnt));
				}

				if (cnt == Integer.parseInt(strPersonCnt))
					break;
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
		if (e.getSource() == homebt) { // 홈버튼
			tpmain.setVisible(true);
			srchrslt.setVisible(false);
			BuyerP.setVisible(false);
			mypagep.setVisible(false);
			searchtf.setText("제목 또는 날짜 검색");
			searchtf1.setText("제목 또는 날짜 검색");
		} // 홈버튼

		else if (e.getSource() == mv1)
			rsvDlg.setVisible(true);
		else if (e.getSource() == mv2)
			rsvDlg.setVisible(true);
		else if (e.getSource() == mv3)
			rsvDlg.setVisible(true);
		else if (e.getSource() == mv4)
			rsvDlg.setVisible(true);
		else if (e.getSource() == mv5)
			rsvDlg.setVisible(true);
		else if (e.getSource() == mv6)
			rsvDlg.setVisible(true);
		else if (e.getSource() == mv7)
			rsvDlg.setVisible(true);
		else if (e.getSource() == mv8)
			rsvDlg.setVisible(true);
		else if (e.getSource() == mv1c)
			rsvDlg.setVisible(true);
		else if (e.getSource() == mv2c)
			rsvDlg.setVisible(true);
		else if (e.getSource() == mv3c)
			rsvDlg.setVisible(true);
		else if (e.getSource() == mv4c)
			rsvDlg.setVisible(true);
		else if (e.getSource() == mv5c)
			rsvDlg.setVisible(true);
		else if (e.getSource() == mv6c)
			rsvDlg.setVisible(true);
		else if (e.getSource() == mv7c)
			rsvDlg.setVisible(true);
		else if (e.getSource() == mv8c)
			rsvDlg.setVisible(true);

		else if (e.getSource() == searchtf){
			searchtf.setText("");
		}
		else if(e.getSource()==searchtf1)
			searchtf1.setText("");
		

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
		}else if(e.getSource()==searchtf1){
			searchtf1.setText("");
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

/*
 * class TotalTicket_sub1 implements Serializable { private String id; private
 * String pw; private String pwok; private String tel; private String nik;
 * private String email;
 * 
 * public TotalTicket_sub1(String a, String b, String c, String d, String e,
 * String f) { id = a; pw = b; pwok = c; tel = d; nik = e; email = f; }
 * 
 * public String getId() { return id; }
 * 
 * public void setId(String id) { this.id = id; }
 * 
 * public String getPw() { return pw; }
 * 
 * public void setPw(String pw) { this.pw = pw; }
 * 
 * public String getPwok() { return pwok; }
 * 
 * public void setPwok(String pwok) { this.pwok = pwok; }
 * 
 * public String getTel() { return tel; }
 * 
 * public void setTel(String tel) { this.tel = tel; }
 * 
 * public String getNik() { return nik; }
 * 
 * public void setNik(String nik) { this.nik = nik; }
 * 
 * public String getEmail() { return email; }
 * 
 * public void setEmail(String email) { this.email = email; } }
 */