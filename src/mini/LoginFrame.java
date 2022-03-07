package mini;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class LoginFrame extends JFrame implements ActionListener {

	// 메인
	public static void main(String[] args) {
		new LoginFrame();
	}

	/* Panel */
	JPanel basePanel = new JPanel();

	JPanel southPanel = new JPanel();

	/* Label */
	JLabel idL = new JLabel("아이디");
	JLabel pwL = new JLabel("비밀번호");
	JLabel titleL = new JLabel("자바피시방");

	/* TextField */
	JTextField idf = new JTextField();
	JPasswordField pwf = new JPasswordField();

	/* Button */
	JButton loginBtn = new JButton("로그인");
	JButton joinBtn = new JButton("회원가입");
	JButton exitBtn = new JButton("프로그램 종료");
	JButton nmemBtn = new JButton("비회원");
	JButton adminBtn = new JButton("관리자");
	JButton idPwBtn = new JButton("ID/PW찾기");

	// 생성자
	public LoginFrame() {
		setTitle("로그인 화면");

		/* Panel 크기 작업 */
		southPanel.setBackground(Color.GRAY);

		southPanel.setBounds(0, 227, 402, 176);
		southPanel.setPreferredSize(new Dimension(290, 40));

		/* Label 크기 작업 */
		titleL.setBounds(122, 12, 140, 30);
		idL.setBounds(58, 135, 50, 30);
		idL.setPreferredSize(new Dimension(50, 30));
		pwL.setBounds(38, 177, 70, 30);
		pwL.setPreferredSize(new Dimension(50, 30));

		titleL.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 24));
		/* TextField 크기 작업 */
		idf.setBounds(122, 135, 140, 30);
		idf.setPreferredSize(new Dimension(140, 30));
		pwf.setBounds(122, 174, 140, 30);
		pwf.setPreferredSize(new Dimension(140, 30));

		/* Button 크기 작업 */
		loginBtn.setBounds(276, 141, 75, 63);
		loginBtn.setPreferredSize(new Dimension(75, 63));
		exitBtn.setBounds(263, 5, 125, 30);
		exitBtn.setPreferredSize(new Dimension(125, 30));

		nmemBtn.setBounds(51, 93, 125, 30);
		adminBtn.setBounds(205, 93, 125, 30);
		nmemBtn.setPreferredSize(new Dimension(125, 30));
		adminBtn.setPreferredSize(new Dimension(125, 30));
		joinBtn.setBounds(14, 5, 125, 30);
		joinBtn.setPreferredSize(new Dimension(125, 30));
		idPwBtn.setPreferredSize(new Dimension(125, 30));
		idPwBtn.setBounds(139, 5, 125, 30);

		/* Panel 추가 작업 */
		basePanel.setBackground(Color.GRAY);
		setContentPane(basePanel); // panel을 기본 컨테이너로 설정
		basePanel.setLayout(null);

		basePanel.add(southPanel);

		basePanel.setLayout(null);
		basePanel.add(titleL);
		basePanel.add(idL);
		basePanel.add(idf);
		basePanel.add(pwL);
		basePanel.add(pwf);

		basePanel.setLayout(null);
		basePanel.add(loginBtn);
		basePanel.add(nmemBtn);
		basePanel.add(adminBtn);
		southPanel.setLayout(null);

		southPanel.add(joinBtn);
		southPanel.add(exitBtn);
		southPanel.add(idPwBtn);
		
		

		loginBtn.addActionListener(this);
		joinBtn.addActionListener(this);

		setSize(408, 438);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		joinBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new JoinFrame();
				dispose();
				
			}
		});
	
	exitBtn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
	});
	adminBtn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			new AdminFrame();
			dispose();
		}
	});
	
	idPwBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new findId();
		}
	});
	nmemBtn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int result = JOptionPane.showConfirmDialog(null, "회원 가입 시 이용요금 할인!!!\n" 
		+ "비회원으로 시작하시겠습니까?", "확인",
					JOptionPane.YES_NO_OPTION);
			dispose();
			if (result == JOptionPane.YES_OPTION) {
				new nmemFrame();

				// new LoginFrame();

			} else if (result == JOptionPane.NO_OPTION) {
				new JoinFrame();
			}
		}
	});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String id = idf.getText();
		
		if (e.getSource().equals(loginBtn)) {
			char[] pass = pwf.getPassword();
			String password = new String(pass);

			if (id.equals("") || password.equals("")) {
				JOptionPane.showMessageDialog(null, "빈칸을 모두 채워주세요");
			} else {
				boolean existLogin = Login.login(id, password);

				if (existLogin) {
					JOptionPane.showMessageDialog(null, "로그인 성공");
				} else {
					JOptionPane.showMessageDialog(null, "로그인 실패");
				}
				
		}
		
	}
	
}
}
