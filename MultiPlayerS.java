import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.text.*;
import javax.swing.border.*;
import java.util.*;
import java.util.Date; 
import java.util.Timer; 
import java.util.TimerTask; 


class MultiPlayerS extends JFrame implements KeyListener,Runnable
	 {
  		JLabel l_road,l_road1,l_car,l_car1,l_bg,l_word,l_word1,l_flag,l_flag1,l_player,l_player1;
		Image i,i2;
  		JTextArea ta_para;   
 		JTextField tf_type;
		Highlighter hilite;
  		static JFrame frame = new JFrame("Turbo Writer");
  		int high_in=0,high_fin=10;
		static int time=0, count=0;
		public static int x_car=370,x_car1=370,wpm=0;
 		private Highlighter.HighlightPainter redPainter;
		Server ser;
		static Thread thread;

  		String para1[] = {"Paragraphs", "are", "the", "building", "blocks", "of", "papers.", "Many", "students", "define" ,"paragraphs","in" ,"terms" ,"of", "length:", "a", "paragraph", "is", "a", "group", "of" ,"at" ,"least", "five" ,"sentences,", "a", "paragraph", "is", "half", "a" ,"page", "long," ,"etc." ,"In", "reality,", "though,","the", "unity", "and" ,"coherence", "of", "ideas", "among", "sentences", "is", "what", "constitutes", "a" ,"paragraph."};

  		String para = "Paragraphs are the building blocks of papers. Many students define paragraphs in terms of length: a paragraph is a group of at least five sentences, a paragraph is half a page long, etc. In reality, though, the unity and coherence of ideas among sentences is what constitutes a paragraph.";

		public MultiPlayerS()
		{
			this.cal();
			ser = new Server();
			this.type();
			thread = new Thread(this);
			thread.start();
		}
 
		public static void main(String args[])
 		{
  			MultiPlayerS p = new MultiPlayerS();
 		}
		public void cal()
		{
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask(){ public void run() { time++; } }, new Date(), 1000); 
		}
		public void run()
		{
				while(true)
				{
				l_word1.setText("wpm = "+Server.wppm);
				l_car1.setBounds(Server.location,175,57,25);
				}
		}
		public void type()
 		{
  			ImageIcon imageIcon = new ImageIcon("textbg.jpg"); 					// load the image to a imageIcon
  			Image image = imageIcon.getImage(); // transform it 
  			Image newimg = image.getScaledInstance(1366, 768,  java.awt.Image.SCALE_SMOOTH); 	// scale it the smooth way  
  			imageIcon = new ImageIcon(newimg); 							 // transform i
  			l_bg = new JLabel(imageIcon);
  			frame.setContentPane(l_bg);
  
  			redPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.red);		//Highlighter for text area.

  			l_road = new JLabel(new ImageIcon("road.jpg"));
			l_road1 = new JLabel(new ImageIcon("road.jpg"));
  	
  			ta_para = new JTextArea(para,6,55)							//Here disable the background of text area		
   			{
     				{
					setOpaque(false);
   				}
   			};										//text area
  			ta_para.setEditable(false);								// working on text area
  			ta_para.setForeground(Color.WHITE);
  			ta_para.setLineWrap(true);
  			ta_para.setWrapStyleWord(true);
  			ta_para.setBounds(370,250,610,150); 							//x , y , l_x , l_y
  			Font f = new Font("SansSarif",Font.BOLD,22);
  			ta_para.setFont(f); 
  
  			try {										//implements the highlighter on text area.
          				ta_para.getHighlighter().addHighlight(high_in,high_fin, redPainter);	
        			      } catch (BadLocationException ble) {
        			      } 
    			l_road.setBounds(370,50,600,100); 
			l_road1.setBounds(370,150,600,100); 


  			l_car = new JLabel(new ImageIcon("car1.png"));						// labels
			l_car1 = new JLabel(new ImageIcon("car3.png"));
  			l_car.setBounds(x_car,75,57,25);
  			l_bg.add(l_car);		
			l_car1.setBounds(x_car,175,57,25);	
			l_bg.add(l_car1);						// car labels


  			l_word = new JLabel();
  			l_word.setBounds(1000,50,100,100);  
			l_word.setText("wpm = "+wpm);
			l_word1 = new JLabel();
  			l_word1.setBounds(1000,150,100,100);  
			l_word1.setText("wpm = "+wpm);

			l_player = new JLabel();
			l_player.setText("(Your Car)");
			l_player.setBounds(300,35,100,100);  
			l_player1 = new JLabel();
			l_player1.setText("(Rival Car)");
			l_player1.setBounds(300,135,100,100);  

  			l_flag = new JLabel(new ImageIcon("flag.png"));
  			l_flag.setBounds(50,4,1870,150);
  			l_bg.add(l_flag);
			l_flag1 = new JLabel(new ImageIcon("flag.png"));
  			l_flag1.setBounds(50,104,1870,150);
  			l_bg.add(l_flag1);

  			tf_type = new JTextField(50);								//text field
  			tf_type.setBounds(370,500,600,30);
  			tf_type.requestFocus();
  	
  													//adding the listeners
  			tf_type.addKeyListener(this);

  			l_bg.setLayout(null);
  			l_bg.add(l_road);
			l_bg.add(l_road1);
  			l_bg.add(ta_para);
  			l_bg.add(tf_type);
  			l_bg.add(l_word);
			l_bg.add(l_word1);
			l_bg.add(l_player);
			l_bg.add(l_player1);

  			frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
  			frame.setSize(1366,768); //683,384
  			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
  			frame.setVisible(true);
 		}
 		
 		public void keyPressed(KeyEvent e)
 		{
			wpm = (count+1)*60/time;
			l_word.setText("wpm = "+wpm);
			if (e.getKeyCode() == e.VK_SPACE)
	 		{
				
	   			String word = tf_type.getText().trim();
	   			if(word.equals(para1[count]))
	    			{
					tf_type.setBackground(Color.white);
	 				x_car+=11;
	    	 			l_car.setBounds(x_car,75,57,25);
	     				tf_type.setText("");	
	     				word="";
	     				count++;						//count = (number of words typed-1)
					if(count==para1.length)
					{
						tf_type.setEditable(false);
						tf_type.setText("Congratulation Your score = "+wpm);
					}
	     				ta_para.getHighlighter().removeAllHighlights();
	     				high_in = high_fin + 1;
  	     				high_fin = high_fin+para1[count].length()+1;
	      			try 
				{											
         	         				ta_para.getHighlighter().addHighlight(high_in,high_fin, redPainter);	
        	       			} 
	       			catch (BadLocationException ble) {} 
	    		}
			else
			{
				tf_type.setBackground(Color.yellow);
			}
				
			}
		 }
		
 		public void keyReleased(KeyEvent e) {}
 		public void keyTyped(KeyEvent e) {}

}
