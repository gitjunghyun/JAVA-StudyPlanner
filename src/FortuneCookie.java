import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class FortuneCookie extends JFrame {

	FortuneCookie() {
		setTitle("포춘쿠키 프레임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		ImageIcon close = new ImageIcon("fortune/포춘쿠키.png");
		ImageIcon open = new ImageIcon("fortune/포춘쿠키(open).png");
		JButton click = new JButton(close);
		click.setRolloverIcon(open);// 프레임에 마우스를 올릴 떄 이미지
		cp.add(click, BorderLayout.CENTER);
		click.setBorderPainted(false);
		click.setContentAreaFilled(false);
		click.setFocusPainted(false);// 버튼 투명하게 설정

		setSize(700, 400);
		setVisible(true);
		click.addActionListener(new ActionListener() {
			// 확인 버튼을 누르는 Action를 취할 때
			public void actionPerformed(ActionEvent e) // Action이벤트가 실행될
														// 때(익명클래스로 선언)
			{
				telling tell = new telling();
				// telling클래스의 프레임이 실행
			}
		});
	}

	class telling extends JFrame {
		int num = (int) (Math.random() * 30 + 1);

		telling() {
			setTitle("오늘의 응원");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			Container cp2 = getContentPane();
			cp2.setLayout(new BorderLayout());

			// 30개의 Image생성!
			ImageIcon[] image = { new ImageIcon("fortune/1.jpg"), new ImageIcon("fortune/2.jpg"),
					new ImageIcon("fortune/3.jpg"), new ImageIcon("fortune/4.jpg"), new ImageIcon("fortune/5.jpg"),
					new ImageIcon("fortune/6.jpg"), new ImageIcon("fortune/7.jpg"), new ImageIcon("fortune/8.jpg"),
					new ImageIcon("fortune/9.jpg"), new ImageIcon("fortune/10.jpg"), new ImageIcon("fortune/11.jpg"),
					new ImageIcon("fortune/12.jpg"), new ImageIcon("fortune/13.jpg"), new ImageIcon("fortune/14.jpg"),
					new ImageIcon("fortune/15.jpg"), new ImageIcon("fortune/16.jpg"), new ImageIcon("fortune/17.jpg"),
					new ImageIcon("fortune/18.jpg"), new ImageIcon("fortune/19.jpg"), new ImageIcon("fortune/20.jpg"),
					new ImageIcon("fortune/21.jpg"), new ImageIcon("fortune/22.jpg"), new ImageIcon("fortune/23.jpg"),
					new ImageIcon("fortune/24.jpg"), new ImageIcon("fortune/25.jpg"), new ImageIcon("fortune/26.jpg"),
					new ImageIcon("fortune/27.jpg"), new ImageIcon("fortune/28.jpg"), new ImageIcon("fortune/29.jpg"),
					new ImageIcon("fortune/30.jpg"), };

			JLabel image_label = new JLabel();

			switch (num)// 스위치를 이용해서 랜덤 숫자를 받으면 해당하는 그림 출력
			{
			case 1:
				image_label.setIcon(image[0]);// 라벨에 그림 붙이기
				break;
			case 2:
				image_label.setIcon(image[1]);
				break;
			case 3:
				image_label.setIcon(image[2]);
				break;
			case 4:
				image_label.setIcon(image[3]);
				break;
			case 5:
				image_label.setIcon(image[4]);
				break;
			case 6:
				image_label.setIcon(image[5]);
				break;
			case 7:
				image_label.setIcon(image[6]);
				break;
			case 8:
				image_label.setIcon(image[7]);
				break;
			case 9:
				image_label.setIcon(image[8]);
				break;
			case 10:
				image_label.setIcon(image[9]);
				break;
			case 11:
				image_label.setIcon(image[10]);
				break;
			case 12:
				image_label.setIcon(image[11]);
				break;
			case 13:
				image_label.setIcon(image[12]);
				break;
			case 14:
				image_label.setIcon(image[13]);
				break;
			case 15:
				image_label.setIcon(image[14]);
				break;
			case 16:
				image_label.setIcon(image[15]);
				break;
			case 17:
				image_label.setIcon(image[16]);
				break;
			case 18:
				image_label.setIcon(image[17]);
				break;
			case 19:
				image_label.setIcon(image[18]);
				break;
			case 20:
				image_label.setIcon(image[19]);
				break;
			case 21:
				image_label.setIcon(image[20]);
				break;
			case 22:
				image_label.setIcon(image[21]);
				break;
			case 23:
				image_label.setIcon(image[22]);
				break;
			case 24:
				image_label.setIcon(image[23]);
				break;
			case 25:
				image_label.setIcon(image[24]);
				break;
			case 26:
				image_label.setIcon(image[25]);
				break;
			case 27:
				image_label.setIcon(image[26]);
				break;
			case 28:
				image_label.setIcon(image[27]);
				break;
			case 29:
				image_label.setIcon(image[28]);
				break;
			case 30:
				image_label.setIcon(image[29]);
				break;
			}
			// planner 버튼
			ImageIcon planner = new ImageIcon("fortune/PLANNER.jpg");
			ImageIcon planner_open = new ImageIcon("fortune/PLANNER(2).jpg");
			JButton click_planner = new JButton(planner);
			click_planner.setRolloverIcon(planner_open);
			click_planner.setBorderPainted(false);
			click_planner.setContentAreaFilled(false);
			click_planner.setFocusPainted(false);
			cp2.add(click_planner, BorderLayout.SOUTH);
			cp2.add(image_label, BorderLayout.CENTER);
			setSize(716, 450);
			setVisible(true);

			click_planner.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CalendarClass c = new CalendarClass();
				}

			});

		}
	}

	public static void main(String[] args) {
		new FortuneCookie();
	}
}
