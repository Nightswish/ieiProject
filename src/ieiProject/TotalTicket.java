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
	private boolean state = true;// 마이페이지 상태변화
	private int imgcount = 0;
	private int mok = 0;
	private int mok2 = 0;
	int count = 0;
	private JPanel tpmaincard = new JPanel(new CardLayout());
	private String saveshowname = null;
	private Container con;
	// 메뉴화면
	private BorderLayout bl = new BorderLayout(5, 5);
	private JPanel pagep = new JPanel(new FlowLayout());
	private JButton rightbt = new JButton(">>");
	private JButton leftbt = new JButton("<<");
	private JButton joinbt = new JButton("회원가입");
	private JButton loginbt = new JButton("로그인");
	private JButton mypagebt = new JButton("마이페이지");
	private JPanel MainP = new JPanel(new CardLayout());
	JPanel sp1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel sp2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

	private JLabel lb = new JLabel("");
	private JButton logoutbt = new JButton("로그아웃");
	private JButton adminbt = new JButton("관리자 모드");
	private String userId;

	// 관리자 모드
	private Container admincon;
	private JDialog admindlg = new JDialog(this, "관리자 모드", true);
	protected JTabbedPane adminPane = new JTabbedPane();
	protected Panel customerP = new Panel(new BorderLayout());
	protected Panel showP = new Panel(new BorderLayout());
	protected Panel couponP = new Panel(new BorderLayout());
	protected Panel couponckP = new Panel(new BorderLayout());

	// 관리자 모드 고객관리
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
	JButton customerinbt = new JButton("추가");
	JButton customerupbt = new JButton("수정");
	JButton customerdebt = new JButton("삭제");

	// 관리자 모드 공연 관리
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
	JButton shoinbt = new JButton("추가");
	JButton shoupbt = new JButton("수정");
	JButton shodebt = new JButton("삭제");

	// 관리자모드 쿠폰 관리창
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
	JButton cpmkbt = new JButton("번호 생성");
	JButton cpinbt = new JButton("추가");
	JButton cpupbt = new JButton("수정");
	JButton cpdebt = new JButton("삭제");

	// 관리자모드 쿠폰체크 확인창
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
	JButton ckinbt = new JButton("추가");
	JButton ckupbt = new JButton("수정");
	JButton ckdebt = new JButton("삭제");

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
	JPanel tp[];// = new JPanel(new GridLayout(2, 3, 3, 3));
	JScrollPane[] scroller;// = new JScrollPane(tp);

	JPanel mp = new JPanel(new BorderLayout(3, 3));

	private int num = 6;
	ImageIcon[] image;// = new ImageIcon[num];

	private JLabel homebt = new JLabel(home);
	// 원본 라벨
	private JLabel[] mv;// = new JLabel[num];

	// copy 라벨
	private JLabel[] mvc;// = new JLabel[num];

	// 이벤트 다이얼로그
	Container eventCon;
	JDialog eventdlg = new JDialog(this, "이벤트", false);
	JPanel eventpn = new JPanel(new BorderLayout());
	JPanel eventpn2 = new JPanel(new GridLayout(4, 1));
	JPanel eventlbpn = new JPanel(new FlowLayout());
	JPanel eventlbpn2 = new JPanel(new FlowLayout());
	JPanel eventlbpn3 = new JPanel(new FlowLayout());
	JPanel eventbtpn = new JPanel(new FlowLayout());
	JLabel eventlb6m = new JLabel(new ImageIcon("..\\ieiProject\\image\\호국보훈의달.png"));
	JLabel eventalarm = new JLabel("호국보훈의 달을 맞이하여 추가 포인트 증정!");
	JLabel eventguid = new JLabel("(마이페이지) => (쿠폰입력)의 쿠폰창에 쿠폰번호를 입력해 주세요. ");
	JLabel eventcoupon = new JLabel("0000-0000-0000-0001");
	JButton eventlgbt = new JButton("로그인");
	JButton eventclbt = new JButton("닫기");

	String[] mvst;// = new String[num];
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
	/////////////// 아이디 및 비밀번호 찾기
	private Container Findcon;
	private JDialog Finddlg = new JDialog(this, "찾기", true);

	private JPanel Findidp = new JPanel(new BorderLayout(5, 5));
	private JPanel Findidbtp = new JPanel(new FlowLayout());

	private JPanel Findpwp = new JPanel(new GridLayout(2, 1));
	private JPanel Findbtp = new JPanel(new FlowLayout());
	private JButton Findid = new JButton("아이디찾기");
	private JButton Findpw = new JButton("비밀번호찾기");
	private JButton Findid_fid = new JButton("찾기");
	private JButton Findid_fpw = new JButton("비밀번호 찾기");

	private Container Finderrcon;
	private JDialog Finderrdlg = new JDialog(this, "오류", true);
	private JPanel Finderrp = new JPanel(new BorderLayout(5, 5));
	private JLabel Finderrimgib = new JLabel(new ImageIcon("..\\ieiProject\\image\\error.jpg"));
	private JLabel Finderrlb = new JLabel("잘못 입력하였습니다.");
	private JButton Finddlgbt = new JButton("확인");

	private JButton Findpwbt = new JButton("비밀번호찾기");
	private JButton Findpokbt = new JButton("확인");
	private JButton Findippclbt = new JButton("취소");
	private JButton FindShowIDpclbt = new JButton("취소");
	private JButton FindShowPWpcokt = new JButton("확인");

	private JPanel FindShowIDp = new JPanel(new GridLayout(3, 1));
	private JPanel FindShowPWp = new JPanel(new GridLayout(2, 1));

	private JDialog Findiddlg = new JDialog(this, "아이디찾기", false);
	private TextField Findidtf = new TextField(20);
	private TextField Findpwtf = new TextField(20);
	private JLabel FindShowid = new JLabel();
	private JLabel FindShowpw = new JLabel();

	// 1:1문의 다이얼로그
	private JButton btnFAQ = new JButton("1:1 문의하기");

	// 170518 이유미 수정 포인트 충전 권유 다이얼로그
	private Container addPointcon;
	private JDialog addPointDlg = new JDialog(this, "포인트 충전 필요", true);
	private JLabel lbaddPoint = new JLabel("포인트가 부족합니다.", JLabel.CENTER);
	private JButton btnAddPoint = new JButton("충전 하기");

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

	// 170518 이유미 수정 충전 단위
	String strChargePoint;
	String cntChargePoint[] = { "0", "10000", "20000", "50000", "100000" };
	private JComboBox cbChargePoint = new JComboBox(cntChargePoint);

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
	private JLabel lbPoint = new JLabel("나의 포인트");
	private JLabel lbMyPoint = new JLabel("0");
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
	protected Panel coupon = new Panel(new BorderLayout());

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
	private Label point2 = new Label("10,000원 충전: 5,00p 지급");
	private Label point3 = new Label("20,000원 충전: 2,000p 지급");
	private Label point4 = new Label("30,000원 충전: 3,000p 지급");
	private Label point5 = new Label("50,000원 충전: 5,000p 지급");
	private Label point6 = new Label("100,000원 충전 : 10,000p 지급");

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

	private int numb;

	private JPanel[] mytkp;// = new JPanel[numb];//(new BorderLayout(3, 3));
	private JPanel[] tkall;// = new JPanel[numb];//(new FlowLayout());
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

	// 쿠폰 입력 텝팬
	JPanel couponlbpn = new JPanel(new FlowLayout());
	JPanel coupontfpn = new JPanel(new FlowLayout());
	JPanel couponbtpn = new JPanel(new FlowLayout());
	JLabel couponlb = new JLabel("쿠폰 번호를 입력해 주세요");
	JTextField coupon1 = new JTextField(5);
	JLabel coupon11 = new JLabel(" - ");
	JTextField coupon2 = new JTextField(5);
	JLabel coupon22 = new JLabel(" - ");
	JTextField coupon3 = new JTextField(5);
	JLabel coupon33 = new JLabel(" - ");
	JTextField coupon4 = new JTextField(5);
	JButton couponok = new JButton("확인");
	////////////////////////////////////////////// 공연내역삭제를 위해 저장해주는 변수
	String shownum = null;
	String showname = null;
	String showloc = null;
	String showdate = null;
	String showseat = null;
	/////////////////////////////////////////////////////////// 후기보기&작성
	FlowLayout fl = new FlowLayout();
	JButton bt = new JButton("후기 보기");
	JButton btnReview = new JButton("후기 작성");

	JDialog jdlg = new JDialog(this, "글쓰기", true);
	Container dlgcon;
	JLabel dlglb = new JLabel("작성자 : ", JLabel.RIGHT);
	JLabel dlglb1 = new JLabel("제목 : ", JLabel.RIGHT);
	JLabel dlglb2 = new JLabel("비밀번호 : ", JLabel.RIGHT);
	JLabel dlgtf = new JLabel();
	JTextField dlgtf1 = new JTextField();
	JPasswordField dlgtf2 = new JPasswordField();
	JTextArea dlgta = new JTextArea();
	JScrollPane dlgjsp = new JScrollPane(dlgta);
	JButton dlgbt = new JButton("저장");
	JButton dlgbt1 = new JButton("취소");

	// 작성글 목록
	private JDialog adlg = new JDialog(this, "리스트목록", true);
	private Container adlgcon;
	private JLabel adlglb = new JLabel("리스트목록", JLabel.CENTER);
	private Vector adlgvc = new Vector();
	private JList adlgli = new JList();
	private JScrollPane adlgjsp = new JScrollPane(adlgli);
	private JButton adlgbt = new JButton("선택항목보기");
	private JButton adlgbt1 = new JButton("닫기");

	// 선택된 글의 전체 내용 보기
	private JDialog bdlg = new JDialog(this, "글보기", true);
	private Container bdlgcon;
	private JLabel bdlglb = new JLabel("제목 : ", JLabel.RIGHT);
	private JLabel bdlglb1 = new JLabel("작성자 : ", JLabel.RIGHT);
	private JLabel bdlgtf = new JLabel();
	private JLabel bdlgtf1 = new JLabel();
	private JTextArea bdlgta = new JTextArea();
	private JScrollPane bdlgjsp = new JScrollPane(bdlgta);
	private JButton bdlgbt = new JButton("확인");

	public void FindidDB() {// DB에서 ID 찾기
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

	}// DB에서 ID 찾기

	public void FindpwDB() { // DB에서 PW 찾기
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

	}// DB에서 PW 찾기

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

	// 결제창에서 포인트 DB에서 불러오기
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
			System.out.println("포인트 가져오기 실패");
		} catch (SQLException e) {
			System.err.println("포인트 가져오기 SQL 실패");
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
				JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 틀렸습니다!", "로그인 오류", JOptionPane.WARNING_MESSAGE);
				rs.close();
				pstmt.close();
			}

			else if (rs.getString("ID").equals("asd") && rs.getString("PW").equals("1")) {
				loginokdlglb1.setText("관리자");
				id2.setText(rs.getString("id"));
				nname2.setText(rs.getString("nik"));
				phone2.setText(rs.getString("tel"));
				email2.setText(rs.getString("email"));
				eID1.setText(rs.getString("id"));
				tphone.setText(rs.getString("tel"));
				tmail.setText(rs.getString("email"));
				tname.setText(rs.getString("nik"));
				point1.setText(rs.getString("POINT"));// 포인트 추가(2017.5.10)
				lb.setText("관리자" + " 님 ");
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
				point1.setText(rs.getString("POINT"));// 포인트 추가(2017.5.10)
				lb.setText(rs.getString("id") + " 님 ");
				dlgtf.setText(rs.getString("id"));
				loginokdlg.setVisible(true);
				userId = rs.getString("id");
				btnReview.setVisible(true);

				rs.close();
				pstmt.close();
			}
		} catch (ClassNotFoundException eee) {

		} catch (SQLException e) {
			System.err.println("로그인 실패!!!");
		}
	}

	// 로그아웃 (DB연결 끊기)
	public void logoutMember() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("로그아웃 실패");
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

	// 수정 170518 포인트 충전DB
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
			// 170518 이유미 수정
			price = 0;
		}

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);

			// 포인트 충전하기
			String query = "update customer set POINT=? where ID=?";
			PreparedStatement pstmt = conn.prepareStatement(query);

			System.out.println("** 현재 잔액은 :" + point1.getText());
			addPrice = Integer.parseInt(point1.getText()) + price;
			System.out.println("** 충전 후 잔액은 : " + addPrice);

			pstmt.setString(1, String.valueOf(addPrice));
			pstmt.setString(2, id2.getText().trim());

			pstmt.executeUpdate();

			readPoint();
			// chargetf.setText("");

			pstmt.close();
			System.out.println("충전성공!");

		} catch (ClassNotFoundException eee) {
			System.err.println("충전 실패!");
		} catch (SQLException e) {
			System.err.println("충전 실패!");
		}

	}

	// 170518 이유미 수정 포인트 차감하기 DB도 연결하기
	public void minusPoint(int toPay, int myPoint) {
		int finalPoint = myPoint - toPay;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);

			String query = "update customer set POINT=? where id=?";
			PreparedStatement pstmt = conn.prepareStatement(query);

			// 차감한 금액으로 라벨 변경
			lbMyPoint.setText(String.valueOf(finalPoint));
			point1.setText(String.valueOf(finalPoint));
			System.out.println("포인트 차감해봅니다 잔여금액은 " + lbMyPoint.getText());

			pstmt.setString(1, lbMyPoint.getText());
			pstmt.setString(2, id2.getText().trim());

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

	// coupon 입력
	public void coupon() { // 중복 불가능(문화상품권)
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
				System.out.println("충전 성공");
				JOptionPane.showMessageDialog(null, "쿠폰입력 완료! 현재 포인트 : " + point1.getText(), "쿠폰 입력 완료",
						JOptionPane.INFORMATION_MESSAGE);

			}
			rs.close();
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();

		} catch (Exception ee) {
			System.err.println("쿠폰 입력 오류");
		}
	}

	public void coupon2() { // 중복 가능(이벤트 - 포인트 뿌리기)
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
				JOptionPane.showMessageDialog(null, "사용할 수 없는 쿠폰 번호입니다!", "쿠폰번호 오류", JOptionPane.WARNING_MESSAGE);
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
					System.out.println("충전 성공");
					JOptionPane.showMessageDialog(null, "쿠폰입력 완료! 현재 포인트 : " + point1.getText(), "쿠폰 입력 완료",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "이미 사용한 쿠폰 번호입니다!", "쿠폰번호 오류", JOptionPane.WARNING_MESSAGE);
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
			System.out.println("쿠폰 입력 오류(2)");
		}
	}

	// 고객 보기
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
			System.out.println("고객 불러오기 실패");
		} catch (SQLException e) {
			System.err.println("고객 불러오기 실패2");
		}
	}

	// 고객 추가
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
			System.err.println("고객 추가 실패!!!");
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

	// 고객 수정
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

	// 고객 삭제
	public void deletecustomer() {
		JTable tb = customertableView;
		int deleteRow = tb.getSelectedRow(); // 행을 선택하지 않았을경우 -1을 리턴한다.
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

	// 공연 보기
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
			System.out.println("공연 불러오기 실패");
		} catch (SQLException e) {
			System.err.println("공연 불러오기 실패2");
		}
	}

	// 공연 추가
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
			System.err.println("공연 추가 실패!!!");
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
			System.err.println("공연 추가 실패!!!");
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

	// 공연 수정
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
			System.err.println("공연 수정 실패!!!");
		}
		sstf1.setText(null);
		sstf2.setText(null);
		sstf3.setText(null);
		sstf4.setText(null);
		sstf5.setText(null);
		sstf6.setText(null);
		sstf1.requestFocus();
	}

	// 공연 삭제
	public void deleteshow() throws ParseException {
		JTable tb = showtableView;
		int deleteRow = tb.getSelectedRow(); // 행을 선택하지 않았을경우 -1을 리턴한다.
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

	// 쿠폰 보기
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
			System.out.println("쿠폰 불러오기 실패");
		} catch (SQLException e) {
			System.err.println("쿠폰 불러오기 실패2");
		}
	}

	// 쿠폰 번호 생성
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
			// i는 8자리의 랜덤값을 의미
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

	// 쿠폰 추가
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
			System.err.println("쿠폰 추가 실패!!!");
		}
		cptf1.setText(null);
		cptf2.setText(null);
		cptf3.setText(null);
		cptf4.setText(null);
		cptf1.requestFocus();
		coupondata.addElement(vector);
		coupondatamodel.fireTableDataChanged();
	}

	// 쿠폰 수정
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

	// 쿠폰 삭제
	public void deletecoupon() throws ParseException {
		JTable cb = couponView;
		int deleteRow = cb.getSelectedRow(); // 행을 선택하지 않았을경우 -1을 리턴한다.
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

	// 쿠폰체크 보기
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
			System.out.println("쿠폰체크 불러오기 실패");
		} catch (SQLException e) {
			System.err.println("쿠폰체크 불러오기 실패2");
		}
	}

	// 쿠폰체크 추가
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
			System.err.println("쿠폰체크 추가 실패!!!");
		}
		cktf1.setText(null);
		cktf2.setText(null);
		cktf1.requestFocus();
		couponckdata.addElement(vector);
		couponckdatamodel.fireTableDataChanged();
	}

	// 쿠폰체크 수정
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

	// 쿠폰체크 삭제
	public void deletecouponck() throws ParseException {
		JTable ck = couponckView;
		int deleteRow = ck.getSelectedRow(); // 행을 선택하지 않았을경우 -1을 리턴한다.
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

	public void ShowMyTicket() {// 내티켓내역 보여주기(5/17 다은) 티켓번호(티켓) 공연이름(show)
								// 장소(show) 날짜(티켓) 좌석(티켓)
		// id 가 1인 reservation 의 티켓번호 - ticket의 티켓번호 이어줌 ticket의 공연이름- show의
		// 공연이름

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
			System.out.println("Class 오류");
		} catch (SQLException ee) {
			System.err.println("SQL 오류" + ee.toString());
		}
	}

	public int countShowMyTicket() {// ID마다 공연 횟수 COUNT

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
			System.out.println("Class 오류");
		} catch (SQLException ee) {
			System.err.println("SQL 오류" + ee.toString());
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

	public void DeleteMyTicket() {// 내티켓내역 삭제(5/18 다은) 티켓번호(티켓) 공연이름(show)
									// 장소(show) 날짜(티켓) 좌석(티켓)
		// 1. id 가 1인 reservation 의 티켓번호 - ticket의 티켓번호 이어줌
		// 2. ticket의 공연이름,날짜- show의 공연이름,날짜 이어서 두개 테이블 지워줌
		// 3. seat- ticket tnum 이어서 seat 삭제

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
			System.out.println("Class 오류1");
		} catch (SQLException ee) {
			System.err.println("SQL 오류1" + ee.toString());
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
			System.out.println("Class 오류2");
		} catch (SQLException ee) {
			System.err.println("SQL 오류3" + ee.toString());
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
			System.out.println("Class 오류");
		} catch (SQLException ee) {
			System.err.println("SQL 오류" + ee.toString());
		}
	}

	public TotalTicket_sub12345() {
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
		Calendar cal = Calendar.getInstance();
		int today = cal.get(Calendar.DATE);
		if (today == 18 || today == 19 || today == today) // 이벤트 하고싶은 날짜
			eventdlg.setVisible(true);

	}

	private void start() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/////////////// 아이디및 비밀번호 찾기
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

		// 170518 이유미 수정
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
		cbCount.addActionListener(this);// 170518 이유미 수정
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

		btnReview.addActionListener(this);// 글쓰기버튼...
		dlgbt.addActionListener(this);// 글쓰기 다이얼로그의 저장버튼...
		dlgbt1.addActionListener(this);// 글쓰기 다이얼로그의 취소버튼...
		bt.addActionListener(this);// 리스트목록 버튼..
		adlgbt1.addActionListener(this);// 리스트목록 다이얼로그의 닫기버튼...
		adlgli.addMouseListener(this);// 리스트목록 다이얼로그의 리스트목록...
		adlgbt.addActionListener(this);// 리스트목록 다이얼로그의 선택목록보기 버튼...
		bdlgbt.addActionListener(this);// 글보기 다이얼로그의 확인버튼...

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

		// 170518 이유미 수정 결제 도중 그냥 창 닫았을 때
		chargedlg.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg) {
				cbChargePoint.setSelectedItem("0");
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

						// 좌석에 해당하는 TNUM 받기
						pstmt2.setString(1, btnSelected[i][j].getText());
						rs2 = pstmt2.executeQuery();
						while (rs2.next()) {
							System.out.println("티켓 번호를 출력해보자 " + rs2.getString("TNUM"));
							// 예약 테이블에 넣기
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

	// seat 테이블에 추가하기
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
		// 170518 이유미 수정
		sp1.add(btnFAQ);
		sp1.add(bt);
		sp1.add(btnReview);
		btnReview.setVisible(false);
		mp.add("West", sp1);

		// DB에 있는 공연 갯수 카운트
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

		for (int i = 0; i < mok + 1; i++) {
			tpmaincard.add(tp[i]);
		}

		tpmain.add("East", tklistpn);
		tpmain.add("North", search);
		tpmain.add("Center", tpmaincard);
		tpmain.add("South", pagep);

		con.add("North", mp);
		con.add("Center", tpmain);

		// 이벤트 다이얼로그
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

		////////////// 아이디 및 비밀번호 찾기 화면구성
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

		//// 아이디 및 비밀번호 찾기 위해 변경
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

		// 관리자 모드 다이얼로그 구성
		admincon = admindlg.getContentPane();
		admindlg.setLayout(new FlowLayout());
		admindlg.setSize(1200, 600);
		Dimension di8 = admindlg.getSize();
		admindlg.setLocation((int) (di.getWidth() / 2 - di8.getWidth() / 2),
				(int) (di.getHeight() / 2 - di8.getHeight() / 2));
		adminPane.setPreferredSize(new Dimension(1000, 550));
		adminPane.add("고객 관리", customerP);
		adminPane.add("공연 관리", showP);
		adminPane.add("쿠폰 관리", couponP);
		adminPane.add("쿠폰체크 관리", couponckP);
		adminPane.setTabPlacement(JTabbedPane.LEFT);

		// 관리자 모드 고객 관리 구성
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

		// 관리자 모드 공연 관리 구성
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

		// 관리자 모드 쿠폰 관리 구성
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

		// 관리자 모드 쿠폰체크 관리 구성
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

		// 170518 이유미 수정 포인트 충전 다이얼로그 구성
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
		payDlg.setSize(350, 220);

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

		// 마이페이지
		tPane.setPreferredSize(new Dimension(1000, 550));
		tPane.add("마이페이지", myP);
		tPane.add("수정", edit);
		tPane.add("포인트", pointp);
		tPane.add("공연내역", show);
		tPane.add("쿠폰입력", coupon);
		tPane.setTabPlacement(JTabbedPane.LEFT);

		//////////// 수정 텝팬
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

		//////////////////////// 포인트 충전 텝팬
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
		// 170518 이유미 수정
		chargep.add(cbChargePoint);
		chargep.add(chargebt);

		chargecon.add("Center", chargep);
		///////////////////////////////////////// 포인트 충전 끝, 공연내역 시작
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

		//////////////////////////////////// 예매취소다이어로그

		canceldlgp.add(cancelokbt);
		canceldlgp.add(cancelnobt);

		realcancelp.add("North", realcancel);
		realcancelp.add("Center", canceldlgp);
		canceldlg.add(realcancelp);

		// 쿠폰입력 탭팬
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

		// 글쓰기 다이얼로그를 구성합니다.
		dlgcon = jdlg.getContentPane();
		dlgcon.setLayout(new BorderLayout(5, 5));
		JPanel dlgjp1 = new JPanel(new BorderLayout());
		dlgjp1.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED), "기본정보"));
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
		dlgjsp.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED), "내용작성"));
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
		// 글쓰기 다이얼로그 구성끝..

		// 리스트 목록 다이얼로그 구성...
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
		// 리스트 목록 다이얼로그 구성끝...

		// 글보기 다이얼로그 구성..
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
		// 글보기 다이얼로그 구성끝...

		tpmain.setVisible(true);
		bestshow();
		dateshow();
	}

	int cnt = 0;

	@Override
	public void actionPerformed(ActionEvent e) {
		///////////// 아이디 및 비밀번호 찾기
		if (e.getSource() == Findid) {
			Finddlg.setVisible(true);
			Findidtf.setText("사용하신 E-mail이나 전화번호를 입력하세요");
			Findpwp.setVisible(false);
			FindShowIDp.setVisible(false);
			FindShowPWp.setVisible(false);

		}
		if (e.getSource() == Findpw) {
			Finddlg.setVisible(true);
			Findpwtf.setText("사용하신ID를 입력하세요");
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
			Findpwtf.setText("사용하신ID를 입력하세요");
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
		// 170518 이유미 수정 선택한 충전 잔액 저장
		strChargePoint = cbChargePoint.getSelectedItem().toString();

		// 콤보 박스 예매 인원수
		strPersonCnt = cbCount.getSelectedItem().toString();
		lbAllSeat.setText(strPersonCnt);

		// 170519 이유미 수정
		if (e.getSource() == btnFAQ) { // 1:1 문의하기
			// FAQServer faqserver = new FAQServer();
			MultiChatClient mclient = new MultiChatClient(loginokdlglb1.getText());
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
			tpmaincard.setVisible(false);
			tpmain.add("Center", srchresult);
			searchtf.setText("제목 또는 날짜(예 : 7월 4일 => 07/04 또는 7/04) 입력");
			srchresult.setVisible(true);
			System.out.println(lbct);

			if (lbct == 0 || lbct == num) {
				srchresult.removeAll();
				JOptionPane.showMessageDialog(null, "검색 결과가 없습니다!", "검색 결과 없음", JOptionPane.ERROR_MESSAGE);
				tpmain.add("Center", tpmaincard);
				tpmaincard.setVisible(true);
				srchresult.setVisible(false);

			}

		} else if (e.getSource() == eventlgbt) { // 이벤트 다이얼로그 로그인버튼
			logindlg.setVisible(true);
		} else if (e.getSource() == eventclbt) { // 이벤트 다이얼로그 닫기버튼
			eventdlg.setVisible(false);
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
			eventlgbt.setVisible(false);
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
			loginokdlglb1.setText(" ");
			loginokdlglb1.setText("");
			adminbt.setVisible(false);
			btnReview.setVisible(false);
			logoutMember();
			eventlgbt.setVisible(true);
		} // 로그아웃 버튼

		else if (e.getSource() == btnCancle) { // 취소 버튼
			rsvDlg.setVisible(false);
		} else if (e.getSource() == btnSeatSelect) { // 좌석 선택 부분 눌렀을 때
			// 콤보 박스 날짜 선택 값
			cbSelectedDate = cbDay.getSelectedItem().toString();

			if (loginokdlglb1.getText() == "") {
				JOptionPane.showMessageDialog(null, "로그인을 해주세요!", "비로그인 상태", JOptionPane.WARNING_MESSAGE);
				logindlg.setVisible(true);
				return;
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
			lbToPay.setForeground(Color.RED);
			readPoint();
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
		} else if (e.getSource() == btnReview) {
			jdlg.setVisible(true);
		} else if (e.getSource() == dlgbt) {
			String str = dlgtf.getText().trim();// 작성자
			String str1 = dlgtf1.getText().trim();// 제목
			String str2 = new String(dlgtf2.getPassword());// 비밀번호
			String str3 = dlgta.getText().trim();// 글내용
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
			// 리스트를 보여주어야 한다.
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

		else if (e.getSource() == mypagebt) { // 마이페이지
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
		else if (e.getSource() == couponok) { // 쿠폰번호 확인 버튼
			coupon();
		}

		///////////////////////////////////////////////////////// 포인트

		else if (e.getSource() == charge) {
			chargedlg.setVisible(true);
		} // 포인트 충전버튼

		else if (e.getSource() == chargebt) { // 170519 이유미 마이페이지에서 포인트 충전할 때
			chargepoint(strChargePoint);
			chargedlg.setVisible(false);
		} else if (e.getSource() == btnAddPoint) { // 170519 이유미 결제 금액 부족시 포인트
													// 충전
			addPointDlg.setVisible(false);
			chargedlg.setVisible(true);
		} else if (e.getSource() == btnPayDlgPay) { // 티켓들 결제 완료시
			// 170518 이유미 수정 결제 금액이 부족할 때 충전 권하기
			if (Integer.parseInt(lbToPay.getText()) > Integer.parseInt(lbMyPoint.getText())) {
				System.out.println("결제 금액이 부족");
				addPointDlg.setVisible(true);
			} else { // 170518 이유미 수정 결제 금액이 맞을 때
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
		///////////////////////////////////////////////////// 공연내역 예매취소

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
		} else if (e.getSource() == btnPayDlgPay) { // 티켓들 결제 완료시
			// TODO : 결제 금액이 부족할 때 충전 권하기
			if (Integer.parseInt(lbToPay.getText()) > Integer.parseInt(lbMyPoint.getText())) {
				System.out.println("결제 금액이 부족");
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

		else if (e.getSource() == adminbt) { // 관리자 모드
			admindlg.setVisible(true);

		} // 관리자 모드

		else if (e.getSource() == customerinbt) { // 고객관리 추가 버튼
			addcustomer();
		} // 고객관리 추가 버튼

		else if (e.getSource() == customerupbt) { // 고객관리 수정 버튼
			updatecustomer();
		} // 고객관리 수정 버튼

		else if (e.getSource() == customerdebt) { // 고객관리 삭제 버튼
			deletecustomer();
		} // 고객관리 삭제 버튼

		else if (e.getSource() == shoinbt) { // 공연관리 추가 버튼
			try {
				addshow();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} // 공연관리 추가 버튼

		else if (e.getSource() == shoupbt) { // 공연관리 수정 버튼
			try {
				updateshow();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} // 공연관리 수정 버튼

		else if (e.getSource() == shodebt) { // 공연관리 삭제 버튼
			try {
				deleteshow();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} // 공연관리 삭제 버튼

		else if (e.getSource() == cpmkbt) { // 쿠폰 생성 버튼
			couponnm();
		} else if (e.getSource() == cpinbt) { // 쿠폰 추가 버튼
			try {
				addcoupon();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == cpupbt) { // 쿠폰 수정 버튼
			try {
				updatecoupon();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == cpdebt) { // 쿠폰 삭제 버튼
			try {
				deletecoupon();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == ckinbt) { // 쿠폰체크 추가 버튼
			try {
				addcouponck();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == ckupbt) { // 쿠폰체크 수정 버튼
			try {
				updatecouponck();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == ckdebt) { // 쿠폰체크 삭제 버튼
			try {
				deletecouponck();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		else if (e.getSource() == rightbt) {
			if (count == mok) {
				System.out.println("마지막 페이지 입니다.");
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
				System.out.println("첫페이지 입니다.");
			} else {
				tpmaincard.add(tp[count - 1]);
				tp[count - 1].setVisible(true);
				tp[count - 2].setVisible(false);
				count--;
			}
		}

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
		///////////// 아이디 및 비밀번호찾기
		if (e.getSource() == Findidtf) {
			Findidtf.setText("");
		}
		if (e.getSource() == Findpwtf) {
			Findpwtf.setText("");
		}

		else if (e.getSource() == adlgli) {
			if (e.getClickCount() == 2) {// 더블클릭....
				view();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == homebt) { // 홈버튼
			tpmaincard.add(tp[0]);
			tp[0].setVisible(true);
			tp[1].setVisible(false);
			tpmain.setVisible(true);
			srchresult.setVisible(false);
			BuyerP.setVisible(false);
			mypagep.setVisible(false);
			searchtf.setText("제목 또는 날짜(예 : 7월 4일 => 07/04 또는 7/04) 입력");
		} // 홈버튼

		for (int i = 0; i < imgcount; i++) { // 포스터 이미지 눌렀을 때
			if (e.getSource() == mv[i]) {
				saveshowname = mv[i].getText().trim();
				detshow();
				rsvDlg.setVisible(true);
				// cbDay.removeAllItems();
			}
		}

		for (int i = 0; i < imgcount; i++) { // 카피 포스터 이미지 눌렀을 때
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
		////////// 아이디및 비밀번호 찾기
		if (e.getSource() == Findidtf) {
			Findidtf.setText("사용하신 E-mail이나 전화번호를 입력하세요");
		}
		if (e.getSource() == Findpwtf) {
			Findpwtf.setText("사용하신ID를 입력하세요");
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
		// 넘버를 체크한다..저장...
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