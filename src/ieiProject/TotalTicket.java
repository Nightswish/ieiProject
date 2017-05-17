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

//소스수정 170511      

public class TotalTicket {
	public static void main(String[] ar) {
		TotalTicket_sub123 ex = new TotalTicket_sub123();
	}
}

class TotalTicket_sub123 extends JFrame implements ActionListener, MouseListener, KeyListener, FocusListener {

	private String saveshowname = null;
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

	private TextField searchtf = new TextField("제목 또는 날짜(예 : 7월 4일 => 07/04 또는 7/04) 입력", 80);
	private JButton searchbt = new JButton("검색");

	// 검색 결과
	JPanel srchresult = new JPanel(new GridLayout(2, 4, 3, 3));
	int lbct = 0;

	// 인기순, 날짜순
	JPanel tklistpn = new JPanel(new GridLayout(2, 1, 5, 5));
	JPanel tlingi = new JPanel(new BorderLayout(3, 3));
	JPanel tldate = new JPanel(new BorderLayout(3, 3));
	JLabel lbingi = new JLabel("이달의 Best Ticket!");
	JLabel lbdate = new JLabel("날짜순");
	List ltingi = new List();
	List ltdate = new List();

	// 티켓화면
	JPanel tp = new JPanel(new GridLayout(2, 3, 3, 3));
	JScrollPane scroller = new JScrollPane(tp);

	JPanel mp = new JPanel(new BorderLayout(3, 3));

	ImageIcon home = new ImageIcon("..\\ieiProject\\image\\home2.jpg");
	private int num = 6;
	ImageIcon[] image = new ImageIcon[num];

	private JLabel homebt = new JLabel(home);
	// 원본 라벨
	private JLabel[] mv = new JLabel[num];
	// copy 라벨
	private JLabel[] mvc = new JLabel[num];

	String[] mvst = new String[num];
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

	// 공연 아이디
	String selectedSid;

	// 예매 버튼 클릭 구성
	private Container rsvCon;
	private JDialog rsvDlg = new JDialog(this, "예매 하기", true);
	private JLabel lbName = new JLabel("공연 이름  ");
	private JLabel lbNameDB = new JLabel();
	private JLabel lbLoc = new JLabel("공연 장소  ");
	private JLabel lbLocDB = new JLabel();
	private JLabel lbPrice = new JLabel("가격  ");
	private int showPrice = 0;
	private JLabel lbPriceDB = new JLabel();
	private JLabel lbTicketNum = new JLabel("티켓 번호  ");
	private JLabel lbTicketNumDB = new JLabel("201704220000001");
	private ImageIcon imgPoster = new ImageIcon("..\\ieiProject\\image\\poster.jpg");
	private JLabel imgPosterLb = new JLabel(imgPoster);
	private JButton btnSeatSelect = new JButton("좌석 선택");
	private JButton btnCancle = new JButton("취소");
	private JLabel lbSelectPerson = new JLabel("인원수 선택");

	String cntPerson[] = { "1", "2", "3", "4", "5" };
	private JComboBox cbCount = new JComboBox(cntPerson);

	// 날짜 콤보 박스 설정
	private JLabel lbDay = new JLabel("공연 날짜 ");
	String cbSelectedDate;
	private JComboBox cbDay = new JComboBox();

	// 좌석 선택 버튼 클릭 구성
	private Container sltSeatCon;
	private JDialog sltSeatDlg = new JDialog(this, "좌석 선택", true);
	private JLabel lbNowSltSeat = new JLabel("현재 좌석 선택 수");
	String strPersonCnt;
	private JLabel lbAllSeat = new JLabel();
	private JLabel lbSlash = new JLabel("/");
	private JLabel lbSltSeat = new JLabel("0");
	private JButton btnFinSeat = new JButton("선택 완료");
	private JButton btnReselect = new JButton("다시 선택");

	// 좌석 버튼
	private JButton btnNotSelected[][] = new JButton[6][16];
	private JButton btnSelected[][] = new JButton[6][16];

	// 결제창
	private Container payCon;
	private JDialog payDlg = new JDialog(this, "결제 하기", true);
	private JLabel lbPay = new JLabel("결제 금액");
	private JLabel lbToPay = new JLabel("0");
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
	private JLabel[] mytknum = new JLabel[numb];// ("티켓번호: ");
	private JLabel[] mytknum1 = new JLabel[numb];

	private Panel[] tknamep = new Panel[numb];// (new
												// FlowLayout(FlowLayout.LEFT));
	private JLabel[] mytkname = new JLabel[numb];// ("공연이름: ");
	private JLabel[] mytkname1 = new JLabel[numb];

	private Panel[] tklocp = new Panel[numb];// (new
												// FlowLayout(FlowLayout.LEFT));
	private JLabel[] mytkloc = new JLabel[numb];// ("공연장소: ");
	private JLabel[] mytkloc1 = new JLabel[numb];// ();

	private Panel[] tkdatep = new Panel[numb];// (new
												// FlowLayout(FlowLayout.LEFT));
	private JLabel[] mytkdate = new JLabel[numb];// ("공연날짜: ");
	private JLabel[] mytkdate1 = new JLabel[numb];// ();

	private Panel[] tkseatp = new Panel[numb];// (new
												// FlowLayout(FlowLayout.LEFT));
	private JLabel[] mytkseat = new JLabel[numb];// ("좌석: ");
	private JLabel[] mytkseat1 = new JLabel[numb];// ();

	ImageIcon[] img = new ImageIcon[numb];// ("..\\ieiProject\\image\\드레스덴.jpg");
	Image[] cimg = new Image[numb]; // img.getImage().getScaledInstance(100,150,
									// Image.SCALE_SMOOTH);
	private JLabel[] mytkimg = new JLabel[numb];// (img);

	private Button canceltk = new Button("예매취소");
	/////////////////// 예매취소 다이아로그
	private JDialog canceldlg = new JDialog(this, "예매취소", true);
	private Panel realcancelp = new Panel(new BorderLayout());
	private Label realcancel = new Label("예약을 취소하시겠습니까?");
	private Panel canceldlgp = new Panel(new FlowLayout());
	private Button cancelokbt = new Button("확인");
	private Button cancelnobt = new Button("취소");
	////////////////////////////////////////////// 결제누르면 티켓보이기dialog(5/15)

	private JDialog tkdlg = new JDialog(this, "티켓", true);
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
			// member에 point추가해서 쿼리문 바꿨습니다(2017.5.10)
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
			String query = "update customer set POINT=? where ID=?";
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

	// 세부 공연 DB로 부터 불러오기
	public void detshow() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);

			// 공연 정보 불러오기
			String query = "select * from show where sname=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, saveshowname);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// if (rs.getString("SNAME").equals("특별시민")) {
				lbNameDB.setText(rs.getString("SNAME"));
				lbLocDB.setText(rs.getString("SLOC"));
				lbPriceDB.setText(rs.getString("SPRICE"));
				showPrice = Integer.parseInt(lbPriceDB.getText());

				// 포스터 이미지 재설정
				imgPosterLb.setIcon(new ImageIcon(rs.getString("SIMG")));
			}

			rs.close();
			pstmt.close();

			// 콤보박스 (날짜 불러오기)
			String dayQuery = "select * from detshow where sid like (select sid from show where sname=?)";
			PreparedStatement cbpstmt = conn.prepareStatement(dayQuery);
			cbpstmt.setString(1, saveshowname);
			ResultSet cbrs = cbpstmt.executeQuery();

			// 날짜 형식 변환하여 저장하기
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일 kk:mm");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd kk:mm");

			cbDay.removeAllItems();

			// DB로부터 날짜 콤보 박스 불러오기
			while (cbrs.next()) {
				selectedSid = cbrs.getString("SID");
				cbDay.addItem(sdf.format(cbrs.getTimestamp("DTDATE")));
			}
			cbrs.close();
			cbpstmt.close();
		} catch (ClassNotFoundException eee) {
			System.out.println("세부 공연 불러오기 실패");
		} catch (SQLException e) {
			System.err.println("세부 공연 불러오기 실패");
		}
	}

	// 날짜별 순위
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
			System.out.println("날짜별 순위 불러오기 실패");
		} catch (SQLException e) {
			System.err.println("날짜별 순위 불러오기 실패2");
		}
	}

	// best 순위
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
			System.out.println("날짜별 순위 불러오기 실패");
		} catch (SQLException e) {
			System.err.println("날짜별 순위 불러오기 실패2");
		}
	}

	public void myTicket() {// 결제누르면 티켓정보 보이기(5/15)
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
			System.out.println("Class 오류");
		} catch (SQLException ee) {
			System.err.println("SQL 오류" + ee.toString());
		} catch (ParseException eee) {
			System.err.println("오류11111111" + eee.toString());
		}
	}

	public TotalTicket_sub123() {
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

		// 좌석 다이얼로그 종료됐을 때 좌석 초기화시키기
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

		// 공연내역 취소버튼
		canceltk.addActionListener(this);
		cancelokbt.addActionListener(this);
		cancelnobt.addActionListener(this);

		// 포인트(2017.5.10)
		charge.addActionListener(this);
		chargebt.addActionListener(this);

		btnPayDlgPay.addActionListener(this);
		btnPayDlgCancle.addActionListener(this);
	}

	public void btnSelectedNull() {
		// 버튼 초기화
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 16; j++) {
				btnSelected[i][j].setText(null);
			}
		}
	}

	// 결제 완료되면 티켓 정보 DB에 넣기
	public void addTicket(String showId, String SDate) throws ParseException {

		// String형식의 time을 Timestamp 형식으로 변환
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd kk:mm");
		Date parsedDate = sdf.parse(SDate);
		Timestamp writeDate = new Timestamp(parsedDate.getTime());

		// 오늘 날짜 출력
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		String today = formatter.format(new Date());

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);

			// TICKET 테이블에 티켓 추가
			String query = "insert into TICKET values (? + ticket_num_seq.nextval,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, today + 100000);
			pstmt.setString(2, showId);
			pstmt.setTimestamp(3, writeDate);
			ResultSet rs = null;

			// TICKET 테이블에 있는 TNUM 찾는 쿼리문
			String findTicketQuery = "select * from TICKET where SEATID = ? and DTDATE = ? and SID = ?";
			PreparedStatement pstmt2 = conn.prepareStatement(findTicketQuery);
			pstmt2.setTimestamp(2, writeDate);
			pstmt2.setString(3, showId);
			ResultSet rs2 = null;
			;

			// RERSERVATION 테이블에 추가하기
			String addRsvQuery = "insert into RESERVATION values (?,?)";
			PreparedStatement pstmt3 = conn.prepareStatement(addRsvQuery);
			pstmt3.setString(1, loginokdlglb1.getText());
			ResultSet rs3 = null;

			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 16; j++) {
					if (btnSelected[i][j].getText() != null) {
						System.out.println("출력출력출력 : " + btnSelected[i][j].getText());
						pstmt.setString(4, btnSelected[i][j].getText());
						rs = pstmt.executeQuery();

						// TODO : 좌석에 해당하는 TNUM 받기
						pstmt2.setString(1, btnSelected[i][j].getText());
						rs2 = pstmt2.executeQuery();
						while (rs2.next()) {
							System.out.println("티켓 번호를 출력해보자 " + rs2.getString("TNUM"));
							// TODO : 예약 테이블에 넣기
							pstmt3.setString(2, rs2.getString("TNUM"));
							rs3 = pstmt3.executeQuery();
						}

					}
				}
			}

			// 버튼 초기화
			btnSelectedNull();

			rs.close();
			pstmt.close();

			rs2.close();
			pstmt2.close();

			rs3.close();
			pstmt3.close();

		} catch (ClassNotFoundException eee) {
			System.out.println("예약 테이블 추가 오류");
		} catch (SQLException ee) {
			System.err.println("예약 테이블 추가 SQL 오류");
		}

	}

	// TODO : seat 테이블에 추가하기
	public void addSeat(String SId, String SDate) throws ParseException {

		// String형식의 time을 Timestamp 형식으로 변환
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
						System.out.println("좌석 추가염 " + btnSelected[i][j].getText());
						pstmt.setString(1, btnSelected[i][j].getText());
						rs = pstmt.executeQuery();
					}
				}
			}

			rs.close();
			pstmt.close();

		} catch (ClassNotFoundException eee) {
			System.out.println("좌석 추가 오류");
		} catch (SQLException ee) {
			System.err.println("좌석 추가 SQL 오류");
		}

	}

	// DB에서 이미 차있는 좌석은 X표시로 가져오기
	public void showSeat(String seat, String time) throws ParseException {

		// String형식의 time을 Timestamp 형식으로 변환
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
			// 시간 출력 포맷 확인 코드
			// System.out.println("바뀐 시간 출력 " + sdf.format(parsedDate));
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
			System.out.println("Class 오류");
		} catch (SQLException ee) {
			System.err.println("SQL 오류");
		}

	}

	// 좌석 초기화하기
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

		for (int i = 0; i < num; i++) {
			mv[i] = new JLabel();
			mvc[i] = new JLabel();
			mvst[i] = null;
			image[i] = null;
		}

		// DB에서 이미지 가져오는것 수정
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
			System.err.println("로그인 실패?!!!");
		} catch (SQLException e) {
			System.err.println("로그인 실패?!!!");
		}

		// 메인화면 구성 - 중앙 티켓화면 구성
		tpmain.setBorder(new BevelBorder(BevelBorder.RAISED));

		// 검색창(North)
		ch1.add("----------");
		ch1.add("티켓이름");
		ch1.add("공연날짜");

		search.add("West", ch1);
		search.add("Center", searchtf);
		search.add("East", searchbt);

		// 검색 결과창

		tpmain.add("Center", srchresult);

		// 인기순, 날짜순(East)
		tlingi.add("North", lbingi);
		tlingi.add("Center", ltingi);
		tklistpn.add(tlingi);
		tldate.add("North", lbdate);
		tldate.add("Center", ltdate);
		tklistpn.add(tldate);

		// 중앙의 티켓화면 구성(Center)
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
		JLabel t1lb = new JLabel(image[0]);
		JPanel t2in = new JPanel(new BorderLayout(3, 3));
		JPanel t2show = new JPanel(new BorderLayout(3, 3));
		JPanel t2infop = new JPanel(new GridLayout(2, 1));
		Checkbox buycb1 = new Checkbox();
		Label t2name = new Label("아빠는딸", Label.CENTER);
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

		con.add("North", mp); // 메인
		con.add("Center", MainP);

		// 예매 컨테이너 구성
		rsvCon = rsvDlg.getContentPane();
		rsvDlg.setLayout(new BorderLayout());
		rsvDlg.setSize(600, 400);
		rsvDlg.setResizable(false);

		// 좌석 선택 컨테이너 구성
		sltSeatCon = sltSeatDlg.getContentPane();
		sltSeatDlg.setLayout(new BorderLayout(50, 50));
		sltSeatDlg.setSize(1000, 450);

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

		// btnSelected 객체화 시키기
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 16; j++) {
				btnSelected[i][j] = new JButton();
			}
		}

		// 좌석 섹션 그리기
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

		/* 결제 다이얼로그 구성 */
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
			mytknum[i].setText("티켓번호: ");
			tknamep[i].setLayout((new FlowLayout(FlowLayout.LEFT)));
			mytkname[i].setText("공연이름: ");
			tklocp[i].setLayout((new FlowLayout(FlowLayout.LEFT)));
			mytkloc[i].setText("장소: ");
			tkdatep[i].setLayout((new FlowLayout(FlowLayout.LEFT)));
			mytkdate[i].setText("날짜: ");
			tkseatp[i].setLayout((new FlowLayout(FlowLayout.LEFT)));
			mytkseat[i].setText("좌석: ");

			img[i] = new ImageIcon("..\\ieiProject\\image\\8마일.jpg");
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
		bestshow();
		dateshow();
	}

	int cnt = 0;

	@Override
	public void actionPerformed(ActionEvent e) {

		// 콤보 박스 예매 인원수
		strPersonCnt = cbCount.getSelectedItem().toString();
		lbAllSeat.setText(strPersonCnt);

		if (e.getSource() == Buyer_bt) { // 구매자 끼리
			tpmain.setVisible(false);
			srchresult.setVisible(false);
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
			lbct = 0;
			if (ch1.getSelectedItem() == "----------") { // 네루미가 -----상태일때
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
					System.err.println("검색 결과창 띄우기 실패");
				}
			} else if (ch1.getSelectedItem() == "공연이름") { // 네루미가 공연이름 상태일때
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
					System.out.println("검색 결과창 띄우기 실패");
				}
			} else if (ch1.getSelectedItem() == "공연날짜") { // 네루미가 공연날짜 상태일떄
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
					System.out.println("검색 결과창 띄우기 실패");
				}

			}
			tp.setVisible(false);
			tpmain.add("Center", srchresult);
			searchtf.setText("제목 또는 날짜(예 : 7월 4일 => 07/04 또는 7/04) 입력");
			srchresult.setVisible(true);
			System.out.println(lbct);

			if (lbct == 0 || lbct == num) {
				srchresult.removeAll();
				JOptionPane.showMessageDialog(null, "검색 결과가 없습니다!", "검색 결과 없음", JOptionPane.ERROR_MESSAGE);
				tpmain.add("Center", tp);
				tp.setVisible(true);
				srchresult.setVisible(false);

			}

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
			loginokdlglb1.setText(" ");
			loginokdlglb1.setText("");
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
			// 콤보 박스 날짜 선택 값
			cbSelectedDate = cbDay.getSelectedItem().toString();
			// 로그인 해달라는 에러창, 로그인창 띄우기
			if (loginokdlglb1.getText() == "") {
				JOptionPane.showMessageDialog(null, "로그인을 해주세요!", "비로그인 상태", JOptionPane.WARNING_MESSAGE);
				logindlg.setVisible(true);
			} else {
				try { // DB에 있는 좌석 불러오기
					showSeat(selectedSid, cbSelectedDate);
					seatClear();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				sltSeatDlg.setVisible(true);
			}
		} else if (e.getSource() == btnFinSeat) { // 좌석 선택 완료
			lbToPay.setText(String.valueOf(Integer.parseInt(strPersonCnt) * showPrice));
			payDlg.setVisible(true);
		} else if (e.getSource() == btnPayDlgCancle) {
			payDlg.setVisible(false);
		} else if (e.getSource() == btnReselect) { // 좌석 다시 선택
			try {
				showSeat(selectedSid, cbSelectedDate);
				seatClear();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == mypagebt) { // 마이페이지
			tpmain.setVisible(false);
			srchresult.setVisible(false);
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
/*수정중
		else if (e.getSource() == canceltk) {/// 수정해야함
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
		}*/ else if (e.getSource() == btnPayDlgPay) { // (5/15) 결제눌면 티켓정보

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

		else if (e.getSource() == ltdate) { // 날짜별 순위 리스트
			if (ltdate.getSelectedItem().equals("김선욱and드레스덴 필하모닉")) {
				saveshowname = mv[0].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}

			if (ltdate.getSelectedItem().equals("LA LA LAND IN CONCERT WORLD TOUR")) {
				saveshowname = mv[1].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}

			if (ltdate.getSelectedItem().equals("뮤지컬 드림걸즈 최초 내한")) {
				saveshowname = mv[2].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}

			if (ltdate.getSelectedItem().equals("2017 지산 밸리록 뮤직앤드아츠 페스티벌")) {
				saveshowname = mv[3].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}

			if (ltdate.getSelectedItem().equals("연극[하늘로 가지못한 선녀씨이야기]")) {
				saveshowname = mv[4].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}

			if (ltdate.getSelectedItem().equals("영화[마차 타고 고래고래] OST콘서트")) {
				saveshowname = mv[5].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}

			/*
			 * for (int i = 0; i < num; i++) { if (e.getSource() == mvc[i]) {
			 * saveshowname = mv[i].getText().trim(); detshow();
			 * rsvDlg.setVisible(true); } }
			 */

		} // 날짜별 순위 리스트

		else if (e.getSource() == ltingi) { // best 순위 리스트
			if (ltingi.getSelectedItem().equals("김선욱and드레스덴 필하모닉")) {
				saveshowname = mv[0].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}

			if (ltingi.getSelectedItem().equals("LA LA LAND IN CONCERT WORLD TOUR")) {
				saveshowname = mv[1].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}

			if (ltingi.getSelectedItem().equals("뮤지컬 드림걸즈 최초 내한")) {
				saveshowname = mv[2].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}

			if (ltingi.getSelectedItem().equals("2017 지산 밸리록 뮤직앤드아츠 페스티벌")) {
				saveshowname = mv[3].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}

			if (ltingi.getSelectedItem().equals("연극[하늘로 가지못한 선녀씨이야기]")) {
				saveshowname = mv[4].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}

			if (ltingi.getSelectedItem().equals("영화[마차 타고 고래고래] OST콘서트")) {
				saveshowname = mv[5].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
			}

			/*
			 * for (int i = 0; i < num; i++) { if (e.getSource() == mvc[i]) {
			 * saveshowname = mv[i].getText().trim(); detshow();
			 * rsvDlg.setVisible(true); } }
			 */

		} // best 순위 리스트

		// 좌석 콤보 박스 수만큼 선택
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 16; j++) {
				// 선택한 인원수에 맞으면 좌석 버튼 활성화 시키기
				if (cnt == Integer.parseInt(strPersonCnt)) {
					btnFinSeat.setEnabled(true);
					break;
				} else
					btnFinSeat.setEnabled(false);

				// 선택된 버튼 색 바꾸기
				if (e.getSource() == btnNotSelected[i][j]) {
					btnSelected[i][j].setText(btnNotSelected[i][j].getText());
					System.out.println("선택된 좌석 출력 " + btnSelected[i][j].getText());
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
		if (e.getSource() == homebt) { // 홈버튼
			tpmain.add("Center", tp);
			tp.setVisible(true);
			tpmain.setVisible(true);
			srchresult.setVisible(false);
			BuyerP.setVisible(false);
			mypagep.setVisible(false);
			searchtf.setText("제목 또는 날짜(예 : 7월 4일 => 07/04 또는 7/04) 입력");
		} // 홈버튼

		for (int i = 0; i < num; i++) { // 포스터 이미지 눌렀을 때
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
