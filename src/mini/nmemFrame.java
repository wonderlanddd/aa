package mini;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.*;

public class nmemFrame extends JFrame{
	private JTextField jtf1_1;
	Connection con = null; // DB와 연결하는 객체
	PreparedStatement pstmt; // SQL문을 DB에 전송하는 객체
	ResultSet rs; // SQL문 실행 결과를 가지고 있는 객체
	String sql = null; // SQL문을 저장하는 문자열 변수
	
	public nmemFrame() {
		
		getContentPane().setLayout(null);
		
				
		JPanel container1 = new JPanel();
		JLabel jl1 = new JLabel();
		
		JButton randBtn = new JButton("임시 번호 발급");
		JLabel inputL = new JLabel("번호 입력 :");
		JTextField jtf1 = new JTextField();
		JButton startBtn = new JButton("비회원 시작");
		JButton cancleBtn = new JButton("취소");

		
		jtf1_1 = new JTextField();
		jtf1_1.setEditable(false);
		
		inputL.setBounds(113, 122, 80, 18);
		
		
		startBtn.setBounds(177, 202, 116, 27);
		cancleBtn.setBounds(302, 202, 116, 27);

		jtf1_1.setBounds(191, 119, 116, 24);
		randBtn.setBounds(150, 49, 143, 27);
		
		jtf1_1.setColumns(10);
		
		getContentPane().add(inputL);
		getContentPane().add(jtf1_1);
		getContentPane().add(startBtn);
		getContentPane().add(cancleBtn);
		getContentPane().add(randBtn);
	
		setVisible(true);
		setBounds(100, 100, 459, 305);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		randBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();
				
				int firstNumber;
				firstNumber = rand.nextInt(999);	
				
				jtf1_1.setText(Integer.toString(firstNumber));
			}
		});
		cancleBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginFrame();
			}
		});
	
		startBtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				connect();
				insert();
			}
		});
		
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
	
	void insert() {
		try {
			// 1. 오라클 데이터베이스에 전송할 SQL문 작성
			sql = "insert into nmember values(?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, jtf1_1.getText());
			

			// 2. 오라클 데이터베이스에 SQL문 전송 및 실행
			int res = pstmt.executeUpdate();

			if (res > 0) {
				JOptionPane.showMessageDialog(null, "비회원 등록 성공");
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

