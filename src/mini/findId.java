package mini;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class findId extends JFrame{
	private JTextField idtf;
	private JTextField phonetf;
	private JLabel titleL;
	private JButton idBtn;

	Connection con = null;
	PreparedStatement pstmt =null;
	ResultSet rs =null;
	DefaultTableModel model;
	String sql;
	private JButton pwdBtn_1;

public findId() {
		setTitle("아이디 찾기");

		getContentPane().setBackground(Color.GRAY);
		
		getContentPane().setLayout(null);
		titleL = new JLabel("아이디 찾기");
		titleL.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 26));
		titleL.setBounds(104, 29, 155, 43);
		
		JLabel id = new JLabel("이  름 :");
		JLabel phone = new JLabel("연락처 :");
		
		id.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
		phone.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));

		
		id.setBounds(67, 99, 62, 18);
		phone.setBounds(67, 135, 62, 18);
		
		idtf = new JTextField();
		phonetf = new JTextField();
		
		idtf.setBounds(143, 97, 116, 24);
		phonetf.setBounds(143, 133, 116, 24);

		idtf.setColumns(10);
		phonetf.setColumns(10);
		
		idBtn = new JButton("아이디 찾기");
		idBtn.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		idBtn.setBounds(96, 194, 127, 39);
		
		
		pwdBtn_1 = new JButton("비밀번호 찾기");
		pwdBtn_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		pwdBtn_1.setBounds(225, 194, 127, 39);
		
		getContentPane().add(titleL);
		getContentPane().add(id);
		getContentPane().add(phone);
		getContentPane().add(idtf);
		getContentPane().add(phonetf);
		getContentPane().add(idBtn);
		getContentPane().add(pwdBtn_1);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 390, 300);
		setVisible(true);
		
		
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
	}