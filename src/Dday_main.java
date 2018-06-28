import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Calendar;
public class Dday_main extends JFrame{
   
	Calendar current = Calendar.getInstance();
	Calendar event = Calendar.getInstance();
	public float Year=current.get(Calendar.YEAR);//����⵵
	public float Month=current.get(Calendar.MONTH+1);//�����(0-11�̱� ������ +1)
	public float Date=current.get(Calendar.DATE);//���糯¥

 
Dday_main(){
	
  setTitle("D-day ���� ������");
	
	 
  JPanel pn1 = new JPanel();
  JPanel pn2 = new JPanel();
  JPanel pn3 = new JPanel();
  JPanel result = new JPanel();

  
  JLabel result_txt = new JLabel();
  result_txt.setVisible(false);
  
    
    pn1.setLayout(new BorderLayout());
  	
  //JPanel pn1
  JLabel title = new JLabel("��¥�� �������ּ���.",SwingConstants.CENTER);
  title.setFont(new Font("���ü",Font.PLAIN,15));
  
  //JPanel pn2

  JLabel year = new JLabel("��");
  JTextField day_year = new JTextField(4);
 
  
  JLabel month = new JLabel("��");
  JTextField day_month = new JTextField(2);
  
  
  JLabel day = new JLabel("��");
  JTextField day_day = new JTextField(2);//d-day������¥�� TextField�� �̿��ؼ� �ޱ�
  
  
   
   pn2.add(day_year);
   pn2.add(year);
   pn2.add(day_month);
   pn2.add(month);
   pn2.add(day_day);
   pn2.add(day);
   
   
   //"Ȯ��"��ư
   JButton ok = new JButton("Ȯ��");
   pn3.add(ok);
   
   //Ȯ�� ��ư�� ������ �̺�Ʈ�� �۵��� ��
   ok.addActionListener(new ActionListener()
   {
	   public void actionPerformed(ActionEvent e)
	   {
		  	//text�� ���� d-day�� int������ �ٲٱ�
		   event.set(Integer.parseInt(day_year.getText()),Integer.parseInt(day_month.getText())-1,Integer.parseInt(day_day.getText()));//��ǥ���� calendar�� set
		  	
		  	long diffDay=(current.getTimeInMillis()-event.getTimeInMillis())/(1000*60*60*24);//����ð����� d-day��¥���� ���� �ð� ��� (�и��������*��*��*�ð�)
		
		  	result_txt.setText("D"+diffDay);
		  	result_txt.setFont(new Font("serif",Font.BOLD,28));
		  	result_txt.setVisible(true);//d count
		   
		  	if(diffDay==0)//d-day
		  	{
			   result_txt.setText("D-DAY");
			   result_txt.setFont(new Font("serif",Font.BOLD,28));
			   result_txt.setForeground(new Color(255,0,0));
			   result_txt.setVisible(true);
		  	}
		  	else if(diffDay>=0)//d-day�� ������ ��
		  	{
			   result_txt.setText("D+"+diffDay);
			   result_txt.setFont(new Font("serif",Font.BOLD,28));
			   result_txt.setVisible(true);
			  
		  	}	   	  
	   }
   });
   //��ҹ�ư
   JButton x = new JButton("���");
   pn3.add(x);
   ///��ҹ�ư�� ������ �̺�Ʈ�� �߻��� ��
   x.addActionListener(new ActionListener()
   {
	   public void actionPerformed(ActionEvent e)
	   {
		   String reset="";
		   day_year.setText("");
		   day_month.setText("");
		   day_day.setText("");//�ؽ�Ʈ�ʵ带 �������� 
		   result_txt.setText(reset);
		   result_txt.setVisible(false);
		   
	   }
	   });
   
   JPanel pn4 = new JPanel();
    pn4.add(result);
    result.add(result_txt,BorderLayout.CENTER);
  	pn1.add(title,BorderLayout.NORTH);
	pn1.add(pn2, BorderLayout.CENTER); 
	pn1.add(pn3, BorderLayout.SOUTH);
	this.add(pn1,BorderLayout.NORTH);
	this.add(pn4,BorderLayout.CENTER);
	setSize(200,190);
	setVisible(true);
 }

public static void main(String args[])
{
	new Dday_main();
}
} 
