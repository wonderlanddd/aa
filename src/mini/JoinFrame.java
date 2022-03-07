package mini;

import java.awt.EventQueue;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class JoinFrame extends JFrame{

	protected static final String memid = null;
	private JPanel contentPane;
	private JTextField idtf, pwtf, nametf, phonetf, addrtf;
	

	Connection con = null; // DB와 연결하는 객체
	PreparedStatement pstmt = null; // SQL문을 DB에 전송하는 객체
	ResultSet rs = null; // SQL문 실행 결과를 가지고 있는 객체
	String sql; // SQL문을 저장하는 문자열 변수

	DefaultTableModel model;
	private JTextField birthtf;

	public JoinFrame() {
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 405, 461);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel jl0 = new JLabel("회원가입");
		JLabel jl2 = new JLabel("아이디");
		JLabel jl3 = new JLabel("비밀번호");
		JLabel jl5 = new JLabel("이름*");
		JLabel jl6 = new JLabel("연락처*");
		JLabel jl7 = new JLabel("주소");
		jl0.setFont(new Font("휴먼둥근헤드라인", Font.ITALIC, 28));
		jl0.setBounds(128, 12, 126, 56);
		contentPane.add(jl0);

		jl2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
		jl3.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
		jl5.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
		jl6.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
		jl7.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));

		jl2.setBounds(60, 80, 47, 29);
		contentPane.add(jl2);

		jl3.setBounds(46, 115, 76, 29);
		contentPane.add(jl3);

		jl5.setBounds(70, 190, 38, 29);
		contentPane.add(jl5);

		jl6.setBounds(59, 260, 53, 29);
		contentPane.add(jl6);

		jl7.setBounds(70, 295, 36, 29);
		contentPane.add(jl7);

		idtf = new JTextField();
		idtf.setBounds(120, 80, 134, 24);
		contentPane.add(idtf);
		idtf.setColumns(10);

		pwtf = new JPasswordField();
		pwtf.setColumns(10);
		pwtf.setBounds(120, 115, 134, 24);
		contentPane.add(pwtf);

		nametf = new JTextField();
		nametf.setColumns(10);
		nametf.setBounds(120, 190, 134, 24);
		contentPane.add(nametf);

		phonetf = new JTextField();
		phonetf.setColumns(10);
		phonetf.setBounds(120, 260, 134, 24);
		contentPane.add(phonetf);

		addrtf = new JTextField();
		addrtf.setColumns(10);
		addrtf.setBounds(120, 295, 134, 24);
		contentPane.add(addrtf);

		JLabel jl0_1 = new JLabel("필수항목(*)");
		jl0_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
		jl0_1.setBounds(25, 155, 97, 24);
		contentPane.add(jl0_1);

		JLabel jl8 = new JLabel("생년월일*");
		jl8.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
		jl8.setBounds(45, 225, 68, 29);
		contentPane.add(jl8);

		birthtf = new JTextField();
		birthtf.setColumns(10);
		birthtf.setBounds(120, 225, 134, 24);
		contentPane.add(birthtf);

		setVisible(true);

		JButton checkBtn = new JButton("중복확인");
		checkBtn.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));


		checkBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String memid = idtf.getText();
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "web";
				String password = "1234";
				
				try {
					con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "web", "1234");
					sql = "select * from pcmember where memid = '" + memid + "'";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery(); 
					if(rs.next()) {
						if(rs.getString("memid").equals(memid))
						JOptionPane.showMessageDialog(null, "중복된 아이디입니다");
					} else {
						JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		checkBtn.setBounds(266, 80, 105, 27);
		contentPane.add(checkBtn);

		JButton cancleBtn = new JButton("취소");
		cancleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login();
				dispose();
			}
		});
		cancleBtn.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
		cancleBtn.setBounds(266, 350, 105, 29);
		contentPane.add(cancleBtn);

		JButton regiBtn = new JButton("등록");
		regiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
				insert();

				new LoginFrame();
				dispose();
			}
		});
		regiBtn.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
		regiBtn.setBounds(159, 350, 105, 29);
		contentPane.add(regiBtn);

	}

	void connect() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "web";
		String password = "1234";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
	} // connect() end

	void select() {
		try {
			// 1. 오라클 데이터베이스로 전송할 SQL문 작성
			sql = "select * from pcmember order by memid";
			pstmt = con.prepareStatement(sql);

			// 2. 오라클 데이터베이스에 SQL문 전송 및 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String memid = rs.getString("memid");
				String pwd = rs.getString("pwd");
				String memname = rs.getString("memname");
				String birthdate = rs.getString("birthdate").substring(0, 10);
				String memphone = rs.getString("memphone");
				String addr = rs.getString("addr");
				String regdate = rs.getString("regdate").substring(0, 10);

				Object[] data = { memid, pwd, memname, birthdate, memphone, addr, regdate };

				// 저장된 한 개의 레코드(데이터)를 model에 추가해주면 됨.
				model.addRow(data);

			}
			// 3. 오라클데이터베이스에 연결되어 있던 객체 종료
			rs.close();
			pstmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// select() 메서드 end

	void insert() {
		try {
			// 1. 오라클 데이터베이스에 전송할 SQL문 작성
			sql = "insert into pcmember values(?, ?, ?, ?, ?, ?, sysdate)";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, idtf.getText());
			pstmt.setString(2, pwtf.getText());
			pstmt.setString(3, nametf.getText());
			pstmt.setString(4, birthtf.getText());
			pstmt.setString(5, phonetf.getText());
			pstmt.setString(6, addrtf.getText());

			// 2. 오라클 데이터베이스에 SQL문 전송 및 실행
			int res = pstmt.executeUpdate();

			if (res > 0) {
				JOptionPane.showMessageDialog(null, "회원 등록 성공");
			} else {
				JOptionPane.showMessageDialog(null, "회원 등록 실패");
			}

			// 3. 오라클 데이터베이스에 연결되어 있던 자원 종료
			pstmt.close(); // con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	}

