import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.*;
import java.io.*;

public class CalendarClass extends JFrame implements ActionListener {
	String[] days = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

	int year, month, day;
	static String Date;

	Font font, btn;
	Calendar today;
	Calendar cal;
	JButton Before, After;

	JPanel pn1 = new JPanel();// before,after�κ�
	JPanel pn2 = new JPanel();// ���ڳ�, ���������� ��ġ������(��¥ ��� ��)

	JButton[] cal_button = new JButton[49];// ��¥,���Ϲ�ư

	JPanel pn3;
	JLabel txtMonth, txtYear;

	public CalendarClass() {
		today = Calendar.getInstance();
		cal = new GregorianCalendar();
		year = today.get(Calendar.YEAR);
		month = today.get(Calendar.MONTH) + 1;// 1���� ���� 0�̱� ������ +1
		pn1.add(Before = new JButton("<"));
		Before.setForeground(Color.PINK);
		Before.setBorderPainted(false);
		Before.setContentAreaFilled(false);
		Before.setFocusPainted(false);

		pn1.add(txtYear = new JLabel(year + "��"));
		pn1.add(txtMonth = new JLabel(month + "��"));
		pn1.add(After = new JButton(">"));
		After.setForeground(Color.PINK);
		After.setBorderPainted(false);
		After.setContentAreaFilled(false);
		After.setFocusPainted(false);

		font = new Font("���ü", Font.BOLD, 18);
		txtYear.setFont(font);
		txtMonth.setFont(font);
		btn = new Font("Sherif", Font.PLAIN, 35);
		Before.setFont(btn);
		After.setFont(btn);
		add(pn1, "North");

		pn2.setLayout(new GridLayout(7, 7));// ��¥
		font = new Font("Sherif", Font.BOLD, 12);
		gridInit();
		calSet();
		hideInit();
		add(pn2, "Center");

		Before.addActionListener(this);
		After.addActionListener(this);

		setTitle("�޷� ������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(716, 450);
		setVisible(true);
	}

	public void calSet() {

		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, (month - 1));// (0~11)
		cal.set(Calendar.DATE, 1);// 1�Ϸ� ����
		int week = cal.get(Calendar.DAY_OF_WEEK);// �������
		int j = 0;

		cal_button[0].setForeground(Color.RED);// Sun - ������
		cal_button[6].setForeground(Color.BLUE);// Sat - �Ķ���

		for (int i = cal.getFirstDayOfWeek(); i < week; i++) {
			j++; // �Ͽ��Ϻ��� �� ���� ù ������ ��ĭ
		}

		for (int k = 1; k < j; k++) {
			cal_button[k + 6].setText("");// ��¥�� ���� ��ư�� ���ڸ� ������ �ʴ´�.
			// k+6�� �� ������ 0~6�� ������ ��Ÿ���� ������
		}
		for (int i = cal.getMinimum(Calendar.DAY_OF_MONTH); i <= cal.getMaximum(Calendar.DAY_OF_MONTH); i++) {
			cal.set(Calendar.DATE, i);
			// 1�Ϻ��� ������ ������ ���� �����ϰ� �� ���� ������ ����.
			/*
			 * if(cal.get(Calendar.MONTH) !=month-1) { break; }//���� �Ѿ�� �ʵ���!
			 */

			cal_button[i + 6 + j].setForeground(Color.BLACK);

			if ((i + j - 1) % 7 == 0) {// �Ͽ���
				cal_button[i + 6 + j].setForeground(Color.RED);
			}
			if ((i + j) % 7 == 0) {// �����
				cal_button[i + 6 + j].setForeground(Color.BLUE);
			}
			cal_button[i + 6 + j].setText((i) + "");

		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Before) {
			this.pn2.removeAll();
			calInput(-1);
			gridInit();
			panelInit();
			calSet();
			hideInit();
			this.txtYear.setText(year + "��");
			this.txtMonth.setText(month + "��");
		} else if (e.getSource() == After)// object�ݳ�
		{

			this.pn2.removeAll();
			calInput(1);
			gridInit();
			panelInit();
			calSet();
			hideInit();
			this.txtYear.setText(year + "��");
			this.txtMonth.setText(month + "��");
		} else if (1 <= Integer.parseInt(e.getActionCommand()) && Integer.parseInt(e.getActionCommand()) <= 31) {// �������¸�
																													// ����������
																													// ��ȯ�Ͽ�
																													// Ŭ����
																													// ��¥��
																													// �ݳ�
			int y = year;
			int m = month;
			int d = Integer.parseInt(e.getActionCommand());

			String D = y + "." + m + "." + d;

			Main planner = new Main(D);
		}
	}

	public void hideInit()//
	{
		for (int i = 0; i < cal_button.length; i++) {
			if ((cal_button[i].getText()).equals(""))
				cal_button[i].setEnabled(false);

		}
	}

	public void gridInit() {

		for (int i = 0; i < days.length; i++) {
			pn2.add(cal_button[i] = new JButton(days[i]));// Sun~Sat������ ��ư ���̱�
			cal_button[i].setContentAreaFilled(false);
			cal_button[i].setBorderPainted(false);
		}
		for (int i = days.length; i < 49; i++) {
			pn2.add(cal_button[i] = new JButton("")); // ��¥ ��ư ���̰� ���ڿ� ���鸸��� (��ư
														// 42��)
			cal_button[i].addActionListener(this);
			cal_button[i].setFocusPainted(false);
		}
	}

	public void panelInit() {
		pn2.setLayout(new GridLayout(7, 7));
	}

	public void calInput(int gap)// gap�� 1�̸� ������, -1�̸� �� ��
	{
		month += (gap);

		if (month <= 0) {// �۳�
			month = 12;
			year = year - 1;
		} else if (month >= 13) {// ����
			month = 1;
			year = year + 1;
		}
	}

	public static void main(String[] args) {
		new FortuneCookie();
	}

}
