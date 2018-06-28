import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Calendar;
public class Dday_main extends JFrame{
   
	Calendar current = Calendar.getInstance();
	Calendar event = Calendar.getInstance();
	public float Year=current.get(Calendar.YEAR);//현재년도
	public float Month=current.get(Calendar.MONTH+1);//현재월(0-11이기 때문에 +1)
	public float Date=current.get(Calendar.DATE);//현재날짜

 
Dday_main(){
	
  setTitle("D-day 설정 프레임");
	
	 
  JPanel pn1 = new JPanel();
  JPanel pn2 = new JPanel();
  JPanel pn3 = new JPanel();
  JPanel result = new JPanel();

  
  JLabel result_txt = new JLabel();
  result_txt.setVisible(false);
  
    
    pn1.setLayout(new BorderLayout());
  	
  //JPanel pn1
  JLabel title = new JLabel("날짜를 설정해주세요.",SwingConstants.CENTER);
  title.setFont(new Font("고딕체",Font.PLAIN,15));
  
  //JPanel pn2

  JLabel year = new JLabel("년");
  JTextField day_year = new JTextField(4);
 
  
  JLabel month = new JLabel("월");
  JTextField day_month = new JTextField(2);
  
  
  JLabel day = new JLabel("일");
  JTextField day_day = new JTextField(2);//d-day설정날짜를 TextField를 이용해서 받기
  
  
   
   pn2.add(day_year);
   pn2.add(year);
   pn2.add(day_month);
   pn2.add(month);
   pn2.add(day_day);
   pn2.add(day);
   
   
   //"확인"버튼
   JButton ok = new JButton("확인");
   pn3.add(ok);
   
   //확인 버튼이 눌리는 이벤트가 작동할 때
   ok.addActionListener(new ActionListener()
   {
	   public void actionPerformed(ActionEvent e)
	   {
		  	//text로 받은 d-day를 int형으로 바꾸기
		   event.set(Integer.parseInt(day_year.getText()),Integer.parseInt(day_month.getText())-1,Integer.parseInt(day_day.getText()));//목표일을 calendar에 set
		  	
		  	long diffDay=(current.getTimeInMillis()-event.getTimeInMillis())/(1000*60*60*24);//현재시간에서 d-day날짜까지 남은 시간 계산 (밀리세컨드로*초*분*시간)
		
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
		  	else if(diffDay>=0)//d-day가 지났을 때
		  	{
			   result_txt.setText("D+"+diffDay);
			   result_txt.setFont(new Font("serif",Font.BOLD,28));
			   result_txt.setVisible(true);
			  
		  	}	   	  
	   }
   });
   //취소버튼
   JButton x = new JButton("취소");
   pn3.add(x);
   ///취소버튼이 눌리는 이벤트가 발생할 때
   x.addActionListener(new ActionListener()
   {
	   public void actionPerformed(ActionEvent e)
	   {
		   String reset="";
		   day_year.setText("");
		   day_month.setText("");
		   day_day.setText("");//텍스트필드를 공백으로 
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
