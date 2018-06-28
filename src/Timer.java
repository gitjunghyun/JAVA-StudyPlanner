import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Timer extends JFrame implements Runnable {
	JLabel TimerLabel; // 남은 시간을 보여주는 JLabel
	JPanel TimerPanel; // 줄바꿈에 쓰일 패널
	String H, M, S; // 텍스트필드의 내용을 받아 올 변수 선언
	static int Hour = 0, Minute = 0, Second = 0; // 스레드에서 사용되는 시간 변수들
	static boolean start, stop, re; // 스레드의 상태를 조작하기 위한 변수들

	public Timer(JLabel t) { // 매개변수로 JLabel을 계속 받아오기 위해서 생성자를 만듭니다
		this.TimerLabel = t;
	}

	public void run() { // 스레드가 실행되는 메소드, 스레드의 시작과 일시정지등을 구현합니다.
		while (true) {
			try { // try문에는 시간이 줄어드는 조건을 만들었는데 만들다보니 복잡해졌습니다.
				if (start == true) {
					if (Hour == 0 && Minute == 0 && Second == 0)
						return;
					else if (Second == 00) {
						Second = 60;
						if (Minute > 0)
							Minute -= 1;
						else if (Minute == 00) {
							if (Hour > 0) {
								Minute = 59;
								Hour -= 1;
							}
						}
					}

					Second--;
					TimerLabel.setText(Integer.toString(Hour) + " : " + Integer.toString(Minute) + " : "
							+ Integer.toString(Second));

					Thread.sleep(1000);
				}
				
				else if (stop == true) {
					while (re != true) {
						System.out.println("");
					}
					re = false;
				}
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	public Timer() {
		setTitle("타이머");
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		TimerLabel = new JLabel();
		TimerLabel.setFont(new Font("Gothic", Font.PLAIN, 80));

		Timer T = new Timer(TimerLabel);
		
		// JFrame에 들어가는 텍스트필드와 버튼과 줄바꾸는 패널들을 만들어줍니다.
		TimerLabel.setText( // 처음에 타이머는 0 : 0 : 0으로 보여줍니다.
				Integer.toString(Hour) + " : " + Integer.toString(Minute) + " : " + Integer.toString(Second));
		JButton btn_start = new JButton("시작");
		JButton btn_pause = new JButton("일시정지");
		JButton btn_restart = new JButton("다시시작");
		JButton btn_stop = new JButton("정지");
		JTextField Time_H = new JTextField("00", 2);
		JTextField Time_M = new JTextField("00", 2);
		JTextField Time_S = new JTextField("00", 2);
		JLabel info = new JLabel("타이머를 설정해주세요!", SwingConstants.CENTER);
		info.setFont(new Font("Gothic", Font.PLAIN, 30));
		JLabel Time_h = new JLabel("시간");
		Time_h.setFont(new Font("Gothic", Font.PLAIN, 15));
		JLabel Time_m = new JLabel("분");
		Time_m.setFont(new Font("Gothic", Font.PLAIN, 15));
		JLabel Time_s = new JLabel("초");
		Time_s.setFont(new Font("Gothic", Font.PLAIN, 15));
		JPanel ww1 = new JPanel();
		ww1.setLayout(new FlowLayout(FlowLayout.LEFT, 300, 0)); // 정렬방향,좌우간격,상하간격
		ww1.add(new JLabel()); // 화면 배치를 비슷하게 하기 위한 레이블
		JPanel ww2 = new JPanel();
		ww2.setLayout(new FlowLayout(FlowLayout.LEFT, 300, 0)); // 정렬방향,좌우간격,상하간격
		ww2.add(new JLabel()); // 화면 배치를 비슷하게 하기 위한 레이블
		
		// 위에서 만든 것들을 컨테이너에 추가합니다.
		c.add(info);
		add(ww1);
		c.add(Time_H);
		c.add(Time_h);
		c.add(Time_M);
		c.add(Time_m);
		c.add(Time_S);
		c.add(Time_s);
		add(ww2);
		c.add(btn_start);
		c.add(btn_pause);
		c.add(btn_restart);
		c.add(btn_stop);
		c.add(TimerLabel);

		// 스레드를 실행
		btn_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_start.setEnabled(false);
				
				Thread th = new Thread(T);
				
				start = true;
				H = Time_H.getText();
				M = Time_M.getText();
				S = Time_S.getText();
				Hour = Integer.parseInt(H);
				Minute = Integer.parseInt(M);
				Second = Integer.parseInt(S);
				
				th.start();
			}
		});

		// 스레드를 일시정지
		btn_pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start = false;
				stop = true;
			}
		});

		// 스레드 다시시작
		btn_restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				re = true;
				stop = false;
				start = true;
			}
		});
		
		// 스레드를 정지하고 텍스트필드에서 받아온 값들도 초기화
		btn_stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hour = 0;
				Minute = 0;
				Second = 0;

				start = false;
				stop = true;

				TimerLabel.setText(
						Integer.toString(Hour) + " : " + Integer.toString(Minute) + " : " + Integer.toString(Second));

				btn_start.setEnabled(true);
			}
		});
		setSize(400, 270);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Timer();
	}
}