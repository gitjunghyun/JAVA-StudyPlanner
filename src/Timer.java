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
	JLabel TimerLabel; // ���� �ð��� �����ִ� JLabel
	JPanel TimerPanel; // �ٹٲ޿� ���� �г�
	String H, M, S; // �ؽ�Ʈ�ʵ��� ������ �޾� �� ���� ����
	static int Hour = 0, Minute = 0, Second = 0; // �����忡�� ���Ǵ� �ð� ������
	static boolean start, stop, re; // �������� ���¸� �����ϱ� ���� ������

	public Timer(JLabel t) { // �Ű������� JLabel�� ��� �޾ƿ��� ���ؼ� �����ڸ� ����ϴ�
		this.TimerLabel = t;
	}

	public void run() { // �����尡 ����Ǵ� �޼ҵ�, �������� ���۰� �Ͻ��������� �����մϴ�.
		while (true) {
			try { // try������ �ð��� �پ��� ������ ������µ� ����ٺ��� �����������ϴ�.
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
		setTitle("Ÿ�̸�");
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		TimerLabel = new JLabel();
		TimerLabel.setFont(new Font("Gothic", Font.PLAIN, 80));

		Timer T = new Timer(TimerLabel);
		
		// JFrame�� ���� �ؽ�Ʈ�ʵ�� ��ư�� �ٹٲٴ� �гε��� ������ݴϴ�.
		TimerLabel.setText( // ó���� Ÿ�̸Ӵ� 0 : 0 : 0���� �����ݴϴ�.
				Integer.toString(Hour) + " : " + Integer.toString(Minute) + " : " + Integer.toString(Second));
		JButton btn_start = new JButton("����");
		JButton btn_pause = new JButton("�Ͻ�����");
		JButton btn_restart = new JButton("�ٽý���");
		JButton btn_stop = new JButton("����");
		JTextField Time_H = new JTextField("00", 2);
		JTextField Time_M = new JTextField("00", 2);
		JTextField Time_S = new JTextField("00", 2);
		JLabel info = new JLabel("Ÿ�̸Ӹ� �������ּ���!", SwingConstants.CENTER);
		info.setFont(new Font("Gothic", Font.PLAIN, 30));
		JLabel Time_h = new JLabel("�ð�");
		Time_h.setFont(new Font("Gothic", Font.PLAIN, 15));
		JLabel Time_m = new JLabel("��");
		Time_m.setFont(new Font("Gothic", Font.PLAIN, 15));
		JLabel Time_s = new JLabel("��");
		Time_s.setFont(new Font("Gothic", Font.PLAIN, 15));
		JPanel ww1 = new JPanel();
		ww1.setLayout(new FlowLayout(FlowLayout.LEFT, 300, 0)); // ���Ĺ���,�¿찣��,���ϰ���
		ww1.add(new JLabel()); // ȭ�� ��ġ�� ����ϰ� �ϱ� ���� ���̺�
		JPanel ww2 = new JPanel();
		ww2.setLayout(new FlowLayout(FlowLayout.LEFT, 300, 0)); // ���Ĺ���,�¿찣��,���ϰ���
		ww2.add(new JLabel()); // ȭ�� ��ġ�� ����ϰ� �ϱ� ���� ���̺�
		
		// ������ ���� �͵��� �����̳ʿ� �߰��մϴ�.
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

		// �����带 ����
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

		// �����带 �Ͻ�����
		btn_pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start = false;
				stop = true;
			}
		});

		// ������ �ٽý���
		btn_restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				re = true;
				stop = false;
				start = true;
			}
		});
		
		// �����带 �����ϰ� �ؽ�Ʈ�ʵ忡�� �޾ƿ� ���鵵 �ʱ�ȭ
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