package mini;

import javax.swing.*;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Color;

public class findPwd extends JFrame{
	private JTextField idtf;
	private JTextField birthtf;
	private JTextField phonetf;
	private JLabel titleL;
	private JButton findPwd;
	
	Connection con = null;
	PreparedStatement pstmt =null;
	ResultSet rs =null;

	public findPwd() {
		setTitle("비밀번호 찾기");
		
		getContentPane().setBackground(Color.GRAY);
		
		getContentPane().setLayout(null);
		titleL = new JLabel("비밀번호 찾기");
		titleL.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 26));
		titleL.setBounds(92, 12, 175, 43);
		getContentPane().add(titleL);
		
		JLabel id = new JLabel("아이디 :");
		JLabel phone = new JLabel("연락처 :");
		JLabel birth = new JLabel("생년월일 : ");

		
		id.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
		phone.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
		birth.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));

		
		id.setBounds(67, 70, 62, 18);
		phone.setBounds(67, 151, 62, 18);
		birth.setBounds(51, 108, 86, 21);
		
		
		idtf = new JTextField();
		birthtf = new JTextField();
		phonetf = new JTextField();
		
		idtf.setBounds(143, 67, 116, 24);
		birthtf.setBounds(143, 107, 116, 24);
		phonetf.setBounds(143, 148, 116, 24);

		idtf.setColumns(10);
		birthtf.setColumns(10);
		phonetf.setColumns(10);
		
		findPwd = new JButton("비밀번호 찾기");
		findPwd.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		findPwd.setBounds(224, 195, 127, 39);
		
		getContentPane().add(id);
		getContentPane().add(phone);
		getContentPane().add(birth);
		
		getContentPane().add(idtf);
		getContentPane().add(birthtf);
		getContentPane().add(phonetf);
		getContentPane().add(findPwd);
		
		setBounds(300, 300, 390, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}

}
