import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {
	JPanel contentPane;
	JLabel textField;
	JTextField textField_1;
	JLabel textField_3;
	JTextField textField_4;
	JLabel textField_6;
	JTextField textField_7;
	JTextField textField_9;
	JTextField textField_10;

	String a1, a2, a3, a4, a5;
	String b1, b2, b3, b4, b5;

	static Statement stmt;
	static ResultSet rs;
	static String D;

	public Main(String s) {
		this.D = s;

		setTitle("학습내용");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://203.255.177.208:3306/java6", "java6",
					"test1234");
			stmt = conn.createStatement();

			rs = stmt.executeQuery("select goal1, goal2, goal3, memo, think from TPPlanner where Date='" + D + "'");
			rs.next();

			b1 = rs.getString("goal1");
			b2 = rs.getString("goal2");
			b3 = rs.getString("goal3");
			b4 = rs.getString("memo");
			b5 = rs.getString("think");

			rs.close();
		} catch (ClassNotFoundException e1) {
		} catch (SQLException e1) {
		}

		JButton btnNewButton = new JButton("D-day");
		panel.add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Dday_main();
			}
		});

		JButton btnNewButton_1 = new JButton("타이머");
		panel.add(btnNewButton_1);

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Timer();
			}
		});

		JButton btnNewButton_2 = new JButton("저장");
		panel.add(btnNewButton_2);

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a1 = textField_1.getText();
				a2 = textField_4.getText();
				a3 = textField_7.getText();
				a4 = textField_9.getText();
				a5 = textField_10.getText();

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://203.255.177.208:3306/java6", "java6",
							"test1234");
					Statement stmt = conn.createStatement();

					stmt.executeUpdate("insert into TPPlanner (Date, goal1, goal2 ,goal3, memo, think) " + "values('"
							+ D + "','" + a1 + "','" + a2 + "','" + a3 + "','" + a4 + "','" + a5 + "')");
				} catch (ClassNotFoundException e1) {
				} catch (SQLException e1) {
				}
			}
		});

		JButton btnNewButton_3 = new JButton("업데이트");
		panel.add(btnNewButton_3);

		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a1 = textField_1.getText();
				a2 = textField_4.getText();
				a3 = textField_7.getText();
				a4 = textField_9.getText();
				a5 = textField_10.getText();
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://203.255.177.208:3306/java6", "java6",
							"test1234");
					Statement stmt = conn.createStatement();

					stmt.executeUpdate("update TPPlanner set goal1='" + a1 + "',goal2='" + a2 + "',goal3='" + a3
							+ "',memo='" + a4 + "',think='" + a5 + "'where Date='" + D + "'");
				} catch (ClassNotFoundException e1) {
				} catch (SQLException e1) {
				}
			}
		});

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel(" 순서");
		lblNewLabel_1.setBounds(32, 25, 75, 15);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("                내용");
		lblNewLabel_2.setBounds(129, 25, 156, 15);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("     확인");
		lblNewLabel_3.setBounds(329, 25, 75, 15);
		panel_1.add(lblNewLabel_3);

		textField = new JLabel("1");
		textField.setBounds(32, 50, 57, 21);
		panel_1.add(textField);

		textField_1 = new JTextField(b1);
		textField_1.setBounds(101, 50, 205, 21);
		panel_1.add(textField_1);
		textField_1.setColumns(10);

		textField_3 = new JLabel("2");
		textField_3.setBounds(32, 81, 57, 21);
		panel_1.add(textField_3);

		textField_4 = new JTextField(b2);
		textField_4.setBounds(101, 81, 205, 21);
		panel_1.add(textField_4);
		textField_4.setColumns(10);

		textField_6 = new JLabel("3");
		textField_6.setBounds(32, 114, 57, 21);
		panel_1.add(textField_6);

		textField_7 = new JTextField(b3);
		textField_7.setBounds(101, 114, 205, 21);
		panel_1.add(textField_7);
		textField_7.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("메모 & 마음정리");
		lblNewLabel_4.setBounds(166, 145, 103, 15);
		panel_1.add(lblNewLabel_4);

		textField_9 = new JTextField(b4);
		textField_9.setBounds(32, 170, 182, 21);
		panel_1.add(textField_9);
		textField_9.setColumns(10);

		textField_10 = new JTextField(b5);
		textField_10.setBounds(226, 170, 178, 21);
		panel_1.add(textField_10);
		textField_10.setColumns(10);

		JCheckBox chckbxNewCheckBox = new JCheckBox("확인!!");
		chckbxNewCheckBox.setBounds(329, 49, 75, 23);
		panel_1.add(chckbxNewCheckBox);

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("확인!!");
		chckbxNewCheckBox_1.setBounds(329, 80, 75, 23);
		panel_1.add(chckbxNewCheckBox_1);

		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("확인!!");
		chckbxNewCheckBox_2.setBounds(329, 113, 75, 23);
		panel_1.add(chckbxNewCheckBox_2);

		JEditorPane editorPane = new JEditorPane();
		editorPane.setText("\uC624\uB298\uC758 \uD560 \uC77C!!");
		editorPane.setBounds(140, 0, 115, 21);
		panel_1.add(editorPane);

		setVisible(true);
	}

	public static void main(String[] args) {

		new Main(D);
	}
}
