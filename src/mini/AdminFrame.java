package mini;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class AdminFrame extends JFrame{

	JPanel contentPane;
	JTable table;
	JTextField idf = new JTextField();
	JPasswordField pwdf = new JPasswordField();
	JButton loginBtn = new JButton("로그인");
	JButton backBtn = new JButton("초기화면");
	
	JLabel jl1 = new JLabel("아이디");
	JLabel jl2 = new JLabel("비밀번호");

	
	public AdminFrame() {
		
		setTitle("ID찾기");
		setBounds(100, 100, 383, 292);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("관리자 로그인");
		title.setFont(new Font("휴먼둥근헤드라인", Font.ITALIC, 30));
		title.setBounds(75, 12, 203, 73);
		
		
		jl1.setBounds(31, 93, 45, 29);
		jl2.setBounds(31, 134, 72, 18);
		
		jl2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
		jl1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
	
		
		contentPane.add(jl1);
		contentPane.add(jl2);
		
		
		idf.setColumns(10);
		pwdf.setColumns(10);
		
		idf.setBounds(104, 95, 134, 24);
		pwdf.setBounds(104, 131, 134, 24);
		loginBtn.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));

		
		
		loginBtn.setBounds(252, 93, 82, 62);
		backBtn.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
		backBtn.setBounds(123, 190, 105, 27);

		contentPane.add(idf);
		contentPane.add(pwdf);
		contentPane.add(title);
		contentPane.add(loginBtn);
		contentPane.add(backBtn);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idf.getText();

				if (e.getSource().equals(loginBtn)) {
					char[] pass = pwdf.getPassword();
					String password = new String(pass);

					if (id.equals("") || password.equals("")) {
						JOptionPane.showMessageDialog(null, "빈칸을 모두 채워주세요");
					} else {
						boolean existLogin = Admin.admin(id, password);

						if (existLogin) {
							JOptionPane.showMessageDialog(null, "로그인 성공");
						} else {
							JOptionPane.showMessageDialog(null, "로그인 실패");
						}
						
				
			}
			
		}
			}
		});
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginFrame();
				dispose();
			
			}
		});
	}


	
		
	}
	

