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

	JPanel pn1 = new JPanel();// before,after부분
	JPanel pn2 = new JPanel();// 격자나, 눈금형태의 배치관리자(날짜 띄울 때)

	JButton[] cal_button = new JButton[49];// 날짜,요일버튼

	JPanel pn3;
	JLabel txtMonth, txtYear;

	public CalendarClass() {
		today = Calendar.getInstance();
		cal = new GregorianCalendar();
		year = today.get(Calendar.YEAR);
		month = today.get(Calendar.MONTH) + 1;// 1월의 값이 0이기 때문에 +1
		pn1.add(Before = new JButton("<"));
		Before.setForeground(Color.PINK);
		Before.setBorderPainted(false);
		Before.setContentAreaFilled(false);
		Before.setFocusPainted(false);

		pn1.add(txtYear = new JLabel(year + "년"));
		pn1.add(txtMonth = new JLabel(month + "월"));
		pn1.add(After = new JButton(">"));
		After.setForeground(Color.PINK);
		After.setBorderPainted(false);
		After.setContentAreaFilled(false);
		After.setFocusPainted(false);

		font = new Font("고딕체", Font.BOLD, 18);
		txtYear.setFont(font);
		txtMonth.setFont(font);
		btn = new Font("Sherif", Font.PLAIN, 35);
		Before.setFont(btn);
		After.setFont(btn);
		add(pn1, "North");

		pn2.setLayout(new GridLayout(7, 7));// 날짜
		font = new Font("Sherif", Font.BOLD, 12);
		gridInit();
		calSet();
		hideInit();
		add(pn2, "Center");

		Before.addActionListener(this);
		After.addActionListener(this);

		setTitle("달력 프레임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(716, 450);
		setVisible(true);
	}

	public void calSet() {

		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, (month - 1));// (0~11)
		cal.set(Calendar.DATE, 1);// 1일로 기준
		int week = cal.get(Calendar.DAY_OF_WEEK);// 현재요일
		int j = 0;

		cal_button[0].setForeground(Color.RED);// Sun - 빨간색
		cal_button[6].setForeground(Color.BLUE);// Sat - 파란색

		for (int i = cal.getFirstDayOfWeek(); i < week; i++) {
			j++; // 일요일부터 그 달의 첫 날까지 빈칸
		}

		for (int k = 1; k < j; k++) {
			cal_button[k + 6].setText("");// 날짜가 없는 버튼은 문자를 붙이지 않는다.
			// k+6을 한 이유는 0~6이 요일을 나타내기 때문에
		}
		for (int i = cal.getMinimum(Calendar.DAY_OF_MONTH); i <= cal.getMaximum(Calendar.DAY_OF_MONTH); i++) {
			cal.set(Calendar.DATE, i);
			// 1일부터 마지막 날까지 새로 설정하고 각 날의 요일을 구함.
			/*
			 * if(cal.get(Calendar.MONTH) !=month-1) { break; }//달이 넘어가지 않도록!
			 */

			cal_button[i + 6 + j].setForeground(Color.BLACK);

			if ((i + j - 1) % 7 == 0) {// 일요일
				cal_button[i + 6 + j].setForeground(Color.RED);
			}
			if ((i + j) % 7 == 0) {// 토요일
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
			this.txtYear.setText(year + "년");
			this.txtMonth.setText(month + "월");
		} else if (e.getSource() == After)// object반납
		{

			this.pn2.removeAll();
			calInput(1);
			gridInit();
			panelInit();
			calSet();
			hideInit();
			this.txtYear.setText(year + "년");
			this.txtMonth.setText(month + "월");
		} else if (1 <= Integer.parseInt(e.getActionCommand()) && Integer.parseInt(e.getActionCommand()) <= 31) {// 문자형태를
																													// 정수형으로
																													// 변환하여
																													// 클릭한
																													// 날짜를
																													// 반납
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
			pn2.add(cal_button[i] = new JButton(days[i]));// Sun~Sat까지의 버튼 붙이기
			cal_button[i].setContentAreaFilled(false);
			cal_button[i].setBorderPainted(false);
		}
		for (int i = days.length; i < 49; i++) {
			pn2.add(cal_button[i] = new JButton("")); // 날짜 버튼 붙이고 문자열 공백만들기 (버튼
														// 42개)
			cal_button[i].addActionListener(this);
			cal_button[i].setFocusPainted(false);
		}
	}

	public void panelInit() {
		pn2.setLayout(new GridLayout(7, 7));
	}

	public void calInput(int gap)// gap이 1이면 다음달, -1이면 전 달
	{
		month += (gap);

		if (month <= 0) {// 작년
			month = 12;
			year = year - 1;
		} else if (month >= 13) {// 내년
			month = 1;
			year = year + 1;
		}
	}

	public static void main(String[] args) {
		new FortuneCookie();
	}

}
